package hiking.ml;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Random Forest Regression (bagged CART regression trees with random feature subsets).
 * Features: bootstrap sampling, per-split feature sub-sampling, variance-reduction splits,
 * out-of-bag (OOB) R-squared, impurity-based feature importance, deterministic seeds and
 * a compact array-based tree format for fast save / load.
 */
public class RandomForestRegression implements Regressor {
    public int nTrees = 100, maxDepth = 12, minLeaf = 3, maxFeatures = 0;
    public long seed = 42L;
    public double oobR2 = Double.NaN;

    private int nFeatures;
    private Tree[] trees;
    private double[] imp;

    static final class Tree {
        int[] feat; double[] thr; int[] left, right; double[] val; int size;
        double predict(double[] x) {
            int n = 0;
            while (feat[n] >= 0) n = x[feat[n]] <= thr[n] ? left[n] : right[n];
            return val[n];
        }
    }

    public RandomForestRegression() {}
    public RandomForestRegression(int trees, int depth, int minLeaf, long seed) { this.nTrees = trees; this.maxDepth = depth; this.minLeaf = minLeaf; this.seed = seed; }

    @Override public String type() { return "RF"; }

    @Override public void fit(double[][] X, double[] y, String[] names) {
        final int n = y.length; nFeatures = names.length;
        final int mtry = maxFeatures > 0 ? maxFeatures : Math.max(1, (int) Math.round(nFeatures / 3.0));
        // rank matrix (ties share a rank) so node sorting can use primitive long sorts
        final int[][] rank = new int[nFeatures][n];
        for (int f = 0; f < nFeatures; f++) {
            final int ff = f; Integer[] o = new Integer[n]; for (int i = 0; i < n; i++) o[i] = i;
            Arrays.sort(o, Comparator.comparingDouble(i -> X[i][ff]));
            int r = 0; for (int k = 0; k < n; k++) { if (k > 0 && X[o[k]][f] != X[o[k - 1]][f]) r++; rank[f][o[k]] = r; }
        }
        trees = new Tree[nTrees];
        final boolean[][] inBag = new boolean[nTrees][n];
        final double[][] treeImp = new double[nTrees][nFeatures];
        IntStream.range(0, nTrees).parallel().forEach(t -> {
            Random rnd = new Random(seed * 1_000_003L + t * 7919L);
            int[] idx = new int[n];
            for (int i = 0; i < n; i++) { idx[i] = rnd.nextInt(n); inBag[t][idx[i]] = true; }
            Builder b = new Builder(X, y, rank, mtry, rnd, treeImp[t]);
            trees[t] = b.build(idx);
        });
        imp = new double[nFeatures]; double tot = 0;
        for (double[] ti : treeImp) for (int f = 0; f < nFeatures; f++) { imp[f] += ti[f]; tot += ti[f]; }
        if (tot > 0) for (int f = 0; f < nFeatures; f++) imp[f] /= tot;
        // out-of-bag score
        double[] sum = new double[n]; int[] cnt = new int[n];
        for (int t = 0; t < nTrees; t++) for (int i = 0; i < n; i++) if (!inBag[t][i]) { sum[i] += trees[t].predict(X[i]); cnt[i]++; }
        double ym = SimpleLinearRegression.mean(y), ssr = 0, sst = 0; int m = 0;
        for (int i = 0; i < n; i++) if (cnt[i] > 0) { double p = sum[i] / cnt[i]; ssr += (y[i] - p) * (y[i] - p); sst += (y[i] - ym) * (y[i] - ym); m++; }
        oobR2 = m > 0 && sst > 0 ? 1 - ssr / sst : Double.NaN;
    }

    private final class Builder {
        final double[][] X; final double[] y; final int[][] rank; final int mtry; final Random rnd; final double[] impOut;
        int[] feat = new int[64]; double[] thr = new double[64]; int[] left = new int[64], right = new int[64]; double[] val = new double[64]; int size;
        Builder(double[][] X, double[] y, int[][] rank, int mtry, Random rnd, double[] impOut) { this.X = X; this.y = y; this.rank = rank; this.mtry = mtry; this.rnd = rnd; this.impOut = impOut; }

        Tree build(int[] idx) {
            grow(idx, 0, idx.length, 0);
            Tree t = new Tree(); t.size = size;
            t.feat = Arrays.copyOf(feat, size); t.thr = Arrays.copyOf(thr, size); t.left = Arrays.copyOf(left, size); t.right = Arrays.copyOf(right, size); t.val = Arrays.copyOf(val, size);
            return t;
        }
        int newNode() {
            if (size == feat.length) { int c = size * 2; feat = Arrays.copyOf(feat, c); thr = Arrays.copyOf(thr, c); left = Arrays.copyOf(left, c); right = Arrays.copyOf(right, c); val = Arrays.copyOf(val, c); }
            return size++;
        }
        int grow(int[] idx, int lo, int hi, int depth) {
            int node = newNode(); int m = hi - lo;
            double s = 0, s2 = 0; for (int i = lo; i < hi; i++) { double v = y[idx[i]]; s += v; s2 += v * v; }
            val[node] = s / m; feat[node] = -1;
            if (depth >= maxDepth || m < 2 * minLeaf || s2 - s * s / m < 1e-9) return node;
            // choose random subset of features
            int[] fs = new int[nFeatures]; for (int i = 0; i < nFeatures; i++) fs[i] = i;
            for (int i = 0; i < mtry; i++) { int j = i + rnd.nextInt(nFeatures - i); int tmp = fs[i]; fs[i] = fs[j]; fs[j] = tmp; }
            double bestGain = 1e-9, bestThr = 0; int bestF = -1;
            long[] keys = new long[m]; double[] ys = new double[m]; double[] xs = new double[m];
            for (int k = 0; k < mtry; k++) {
                int f = fs[k];
                for (int i = 0; i < m; i++) keys[i] = ((long) rank[f][idx[lo + i]] << 32) | i;
                Arrays.sort(keys);
                for (int i = 0; i < m; i++) { int p = idx[lo + (int) (keys[i] & 0xffffffffL)]; ys[i] = y[p]; xs[i] = X[p][f]; }
                double sl = 0, tot = s;
                for (int i = 0; i < m - 1; i++) {
                    sl += ys[i];
                    int nl = i + 1, nr = m - nl;
                    if (nl < minLeaf || nr < minLeaf || xs[i] == xs[i + 1]) continue;
                    double sr = tot - sl;
                    double gain = sl * sl / nl + sr * sr / nr - tot * tot / m;
                    if (gain > bestGain) { bestGain = gain; bestF = f; bestThr = (xs[i] + xs[i + 1]) / 2; }
                }
            }
            if (bestF < 0) return node;
            impOut[bestF] += bestGain;
            int i = lo, j = hi - 1;
            while (i <= j) { if (X[idx[i]][bestF] <= bestThr) i++; else { int t = idx[i]; idx[i] = idx[j]; idx[j] = t; j--; } }
            if (i == lo || i == hi) return node;
            feat[node] = bestF; thr[node] = bestThr;
            int l = grow(idx, lo, i, depth + 1); int r = grow(idx, i, hi, depth + 1);
            left[node] = l; right[node] = r;
            return node;
        }
    }

    @Override public double predict(double[] x) { double s = 0; for (Tree t : trees) s += t.predict(x); return s / trees.length; }
    @Override public double[] importances() { return imp == null ? new double[0] : imp.clone(); }
    @Override public String describe() { return String.format("Random Forest: %d trees, max depth %d, min leaf size %d, features per split %d, OOB R² = %.3f", nTrees, maxDepth, minLeaf, maxFeatures > 0 ? maxFeatures : Math.max(1, (int) Math.round(nFeatures / 3.0)), oobR2); }

    @Override public void write(DataOutputStream o) throws IOException {
        o.writeInt(nFeatures); o.writeInt(nTrees); o.writeInt(maxDepth); o.writeInt(minLeaf); o.writeInt(maxFeatures); o.writeLong(seed); o.writeDouble(oobR2);
        for (double d : imp) o.writeDouble(d);
        for (Tree t : trees) {
            o.writeInt(t.size);
            for (int i = 0; i < t.size; i++) { o.writeInt(t.feat[i]); o.writeDouble(t.thr[i]); o.writeInt(t.left[i]); o.writeInt(t.right[i]); o.writeDouble(t.val[i]); }
        }
    }
    public static RandomForestRegression read(DataInputStream in) throws IOException {
        RandomForestRegression m = new RandomForestRegression();
        m.nFeatures = in.readInt(); m.nTrees = in.readInt(); m.maxDepth = in.readInt(); m.minLeaf = in.readInt(); m.maxFeatures = in.readInt(); m.seed = in.readLong(); m.oobR2 = in.readDouble();
        if (m.nTrees < 1 || m.nTrees > 5000 || m.nFeatures < 1 || m.nFeatures > 1000) throw new IOException("Corrupt model header");
        m.imp = new double[m.nFeatures]; for (int i = 0; i < m.nFeatures; i++) m.imp[i] = in.readDouble();
        m.trees = new Tree[m.nTrees];
        for (int k = 0; k < m.nTrees; k++) {
            Tree t = new Tree(); t.size = in.readInt();
            if (t.size < 1 || t.size > 5_000_000) throw new IOException("Corrupt tree");
            t.feat = new int[t.size]; t.thr = new double[t.size]; t.left = new int[t.size]; t.right = new int[t.size]; t.val = new double[t.size];
            for (int i = 0; i < t.size; i++) { t.feat[i] = in.readInt(); t.thr[i] = in.readDouble(); t.left[i] = in.readInt(); t.right[i] = in.readInt(); t.val[i] = in.readDouble();
                if (t.feat[i] >= m.nFeatures || (t.feat[i] >= 0 && (t.left[i] <= i || t.right[i] <= i || t.left[i] >= t.size || t.right[i] >= t.size))) throw new IOException("Corrupt tree node"); }
            m.trees[k] = t;
        }
        return m;
    }
}
