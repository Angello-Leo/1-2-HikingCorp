package hiking.ml;

import hiking.AppPaths;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

/** Loads (or trains + saves) the SLR and RF models of every task and serves predictions. */
public final class AnalysisService {
    public interface Progress { void update(String message, double fraction); }
    public static final String SLR = "SLR", RF = "RF";
    public static final class Result { public final double value; public final String label; Result(double v, String l) { value = v; label = l; } }

    private static final AnalysisService INSTANCE = new AnalysisService();
    public static AnalysisService get() { return INSTANCE; }
    private AnalysisService() {}

    private final Map<Task, Map<String, ModelBundle>> models = new EnumMap<>(Task.class);
    private volatile boolean ready;

    public boolean isReady() { return ready; }

    /** Loads all saved models; trains + saves the missing / outdated ones. Safe to call from a worker thread. */
    public synchronized void ensureReady(Progress p) throws IOException {
        Task[] ts = Task.values(); int i = 0;
        for (Task t : ts) {
            double base = (double) i++ / ts.length;
            if (p != null) p.update("Loading " + t.title + " models...", base);
            Map<String, ModelBundle> m = new HashMap<>();
            boolean ok = true;
            try {
                String sha = Dataset.sha(java.nio.file.Files.readAllBytes(new File(AppPaths.data(), t.csv).toPath()));
                for (String type : new String[]{SLR, RF}) {
                    ModelBundle b = ModelStore.load(t, type);
                    if (!sha.equals(b.eval.datasetSha)) throw new IOException("dataset changed");
                    m.put(type, b);
                }
            } catch (Exception ex) { ok = false; }
            if (!ok) { m = train(t, p, base, 1.0 / ts.length); }
            models.put(t, m);
        }
        ready = true;
        if (p != null) p.update("Ready", 1);
    }

    public synchronized void retrainAll(Progress p) throws IOException {
        Task[] ts = Task.values(); int i = 0;
        for (Task t : ts) { models.put(t, train(t, p, (double) i++ / ts.length, 1.0 / ts.length)); }
        ready = true;
    }

    public ModelBundle bundle(Task t, String type) { Map<String, ModelBundle> m = models.get(t); return m == null ? null : m.get(type); }

    /** The model type with the lowest hold-out RMSE. */
    public String bestType(Task t) {
        ModelBundle a = bundle(t, SLR), b = bundle(t, RF);
        if (a == null || b == null) return RF;
        return b.eval.test.rmse <= a.eval.test.rmse ? RF : SLR;
    }

    public Result predict(Task t, String type, double[] x) {
        ModelBundle b = bundle(t, type);
        if (b == null) throw new IllegalStateException("Models are not loaded yet.");
        double[] c = x.clone();
        for (int i = 0; i < c.length; i++) c[i] = Math.min(t.fields[i].kind == Task.Kind.DERIVED ? 1e9 : t.fields[i].max * 5, c[i]);
        double v = Math.max(0, b.model.predict(c));
        return new Result(v, label(t, v, b));
    }
    public static String label(Task t, double v, ModelBundle b) {
        double thr = b.eval.classThreshold;
        if (t.mode == Task.Mode.SLOW) return v <= thr ? "SLOW-MOVING" : "Normal";
        if (t.mode == Task.Mode.BEST) return v >= thr ? "BEST-SELLER" : "Regular";
        return "";
    }
    public Dataset dataset(Task t) throws IOException { return Dataset.load(new File(AppPaths.data(), t.csv), t.featureNames(), t.target); }

    // ------------------------------------------------------------------ training
    private Map<String, ModelBundle> train(Task t, Progress p, double base, double span) throws IOException {
        if (p != null) p.update("Training " + t.title + "...", base);
        Dataset d = dataset(t);
        int n = d.size(); if (n < 200) throw new IOException(t.csv + " needs at least 200 valid rows");
        // reproducible 80/20 hold-out split
        int[] perm = new int[n]; for (int i = 0; i < n; i++) perm[i] = i;
        Random r = new Random(2026);
        for (int i = n - 1; i > 0; i--) { int j = r.nextInt(i + 1); int tmp = perm[i]; perm[i] = perm[j]; perm[j] = tmp; }
        int nTest = n / 5, nTrain = n - nTest;
        double[][] Xtr = new double[nTrain][], Xte = new double[nTest][]; double[] ytr = new double[nTrain], yte = new double[nTest];
        for (int i = 0; i < nTest; i++) { Xte[i] = d.X[perm[i]]; yte[i] = d.y[perm[i]]; }
        for (int i = 0; i < nTrain; i++) { Xtr[i] = d.X[perm[nTest + i]]; ytr[i] = d.y[perm[nTest + i]]; }
        double[] sorted = ytr.clone(); Arrays.sort(sorted);
        double thr = t.mode == Task.Mode.NONE ? Double.NaN : SimpleLinearRegression.quantile(sorted, t.quantile);
        String[] names = d.featureNames;

        // --- Simple Linear Regression
        SimpleLinearRegression slr = new SimpleLinearRegression(); slr.fit(Xtr, ytr, names);
        Evaluation e1 = evaluate(t, slr, d, Xtr, ytr, Xte, yte, thr, SimpleLinearRegression::new);
        e1.params = slr.describe();

        // --- Random Forest with grid-search hyper-parameter tuning on an inner validation split
        if (p != null) p.update("Tuning Random Forest for " + t.title + "...", base + span * 0.3);
        int nv = nTrain / 5, nt = nTrain - nv;
        double[][] Xa = Arrays.copyOfRange(Xtr, 0, nt), Xv = Arrays.copyOfRange(Xtr, nt, nTrain); double[] ya = Arrays.copyOfRange(ytr, 0, nt), yv = Arrays.copyOfRange(ytr, nt, nTrain);
        int bestDepth = 10, bestLeaf = 3; double bestRmse = Double.MAX_VALUE;
        for (int depth : new int[]{6, 10, 14}) for (int leaf : new int[]{2, 5, 10}) {
            RandomForestRegression g = new RandomForestRegression(60, depth, leaf, 7); g.fit(Xa, ya, names);
            double rm = Metrics.regression(yv, g.predict(Xv)).rmse;
            if (rm < bestRmse) { bestRmse = rm; bestDepth = depth; bestLeaf = leaf; }
        }
        final int fd = bestDepth, fl = bestLeaf;
        if (p != null) p.update("Fitting final Random Forest for " + t.title + "...", base + span * 0.6);
        RandomForestRegression rf = new RandomForestRegression(150, fd, fl, 42); rf.fit(Xtr, ytr, names);
        Evaluation e2 = evaluate(t, rf, d, Xtr, ytr, Xte, yte, thr, () -> new RandomForestRegression(60, fd, fl, 11));
        e2.oobR2 = rf.oobR2;
        e2.params = rf.describe() + "  | tuned by grid search (depth 6/10/14 x min-leaf 2/5/10)";

        Map<String, ModelBundle> out = new HashMap<>();
        out.put(SLR, new ModelBundle(slr, e1)); out.put(RF, new ModelBundle(rf, e2));
        ModelStore.save(t, out.get(SLR)); ModelStore.save(t, out.get(RF));
        return out;
    }

    private Evaluation evaluate(Task t, Regressor m, Dataset d, double[][] Xtr, double[] ytr, double[][] Xte, double[] yte, double thr, Supplier<Regressor> factory) {
        Evaluation e = new Evaluation();
        double[] ptr = clip(m.predict(Xtr)), pte = clip(m.predict(Xte));
        e.train = Metrics.regression(ytr, ptr); e.test = Metrics.regression(yte, pte);
        e.nTotal = d.size(); e.nTrain = ytr.length; e.nTest = yte.length; e.droppedRows = d.droppedRows;
        e.testActual = yte; e.testPred = pte; e.importance = m.importances(); e.featureNames = d.featureNames;
        e.datasetSha = d.sha256; e.trainedAt = System.currentTimeMillis(); e.classThreshold = thr;
        if (t.mode != Task.Mode.NONE) e.cls = Metrics.ClassMetrics.of(yte, pte, thr, t.mode == Task.Mode.SLOW);
        // 10-fold cross validation over the complete dataset
        int k = 10, n = d.size(); double sumRmse = 0, sumR2 = 0; double[] r2s = new double[k];
        int[] fold = new int[n]; { int[] o = new int[n]; for (int i = 0; i < n; i++) o[i] = i; Random fr = new Random(99);
            for (int i = n - 1; i > 0; i--) { int j = fr.nextInt(i + 1); int tmp = o[i]; o[i] = o[j]; o[j] = tmp; }
            for (int i = 0; i < n; i++) fold[o[i]] = i % k; }          // shuffled folds
        for (int f = 0; f < k; f++) {
            List<double[]> xa = new ArrayList<>(); List<Double> ya = new ArrayList<>(); List<double[]> xb = new ArrayList<>(); List<Double> yb = new ArrayList<>();
            for (int i = 0; i < n; i++) { if (fold[i] == f) { xb.add(d.X[i]); yb.add(d.y[i]); } else { xa.add(d.X[i]); ya.add(d.y[i]); } }
            Regressor c = factory.get(); c.fit(xa.toArray(new double[0][]), toArr(ya), d.featureNames);
            double[] pr = clip(c.predict(xb.toArray(new double[0][])));
            Metrics mm = Metrics.regression(toArr(yb), pr); sumRmse += mm.rmse; sumR2 += mm.r2; r2s[f] = mm.r2;
        }
        e.cvRmse = sumRmse / k; e.cvR2 = sumR2 / k;
        double var = 0; for (double v : r2s) var += (v - e.cvR2) * (v - e.cvR2); e.cvR2Std = Math.sqrt(var / k);
        return e;
    }
    private static double[] clip(double[] p) { for (int i = 0; i < p.length; i++) p[i] = Math.max(0, p[i]); return p; }
    private static double[] toArr(List<Double> l) { double[] a = new double[l.size()]; for (int i = 0; i < a.length; i++) a[i] = l.get(i); return a; }
}
