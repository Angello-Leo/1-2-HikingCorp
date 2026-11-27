package hiking.ml;

import java.io.*;
import java.util.Arrays;

/**
 * Simple Linear Regression:  y = b0 + b1 * x   (ONE predictor).
 * Improvements over the textbook version:
 *  1. Automatic best-predictor selection (the feature with the highest R-squared is used).
 *  2. IQR outlier winsorising before fitting (fences = Q1-1.5*IQR, Q3+1.5*IQR).
 *  3. Input clamping + output clamping to the observed training range (no wild extrapolation).
 */
public class SimpleLinearRegression implements Regressor {
    private int nFeatures, featureIdx;
    private String featureName = "";
    private double b0, b1, xLo, xHi, yMin, yMax, trainR2;

    @Override public String type() { return "SLR"; }

    @Override public void fit(double[][] X, double[] y, String[] names) {
        int n = y.length; nFeatures = names.length;
        double[] yc = winsor(y);
        double yMean = mean(yc), syy = 0;
        for (double v : yc) syy += (v - yMean) * (v - yMean);
        double bestR2 = -1; int best = 0; double bs = 0, bi = 0, bxl = 0, bxh = 0;
        for (int j = 0; j < nFeatures; j++) {
            double[] col = new double[n];
            for (int i = 0; i < n; i++) col[i] = X[i][j];
            double[] xc = winsor(col);
            double xMean = mean(xc), sxx = 0, sxy = 0;
            for (int i = 0; i < n; i++) { sxx += (xc[i] - xMean) * (xc[i] - xMean); sxy += (xc[i] - xMean) * (yc[i] - yMean); }
            if (sxx < 1e-12 || syy < 1e-12) continue;
            double r2 = (sxy * sxy) / (sxx * syy);
            if (r2 > bestR2) {
                bestR2 = r2; best = j; bs = sxy / sxx; bi = yMean - bs * xMean;
                bxl = Arrays.stream(xc).min().getAsDouble(); bxh = Arrays.stream(xc).max().getAsDouble();
            }
        }
        featureIdx = best; featureName = names[best]; b1 = bs; b0 = bi; xLo = bxl; xHi = bxh; trainR2 = Math.max(0, bestR2);
        yMin = Arrays.stream(y).min().getAsDouble(); yMax = Arrays.stream(y).max().getAsDouble();
    }

    @Override public double predict(double[] x) {
        double v = Math.min(xHi, Math.max(xLo, x[featureIdx]));
        return Math.min(yMax, Math.max(yMin, b0 + b1 * v));
    }
    @Override public double[] importances() { double[] imp = new double[nFeatures]; if (nFeatures > 0) imp[featureIdx] = 1; return imp; }
    public String featureName() { return featureName; }
    @Override public String describe() {
        return String.format("y = %.4f %s %.4f * %s     (selected predictor: %s, training R² = %.3f)", b0, b1 >= 0 ? "+" : "-", Math.abs(b1), featureName, featureName, trainR2);
    }

    static double[] winsor(double[] v) {
        double[] s = v.clone(); Arrays.sort(s);
        double q1 = quantile(s, .25), q3 = quantile(s, .75), iqr = q3 - q1;
        double lo = q1 - 1.5 * iqr, hi = q3 + 1.5 * iqr;
        double[] o = new double[v.length];
        for (int i = 0; i < o.length; i++) o[i] = iqr == 0 ? v[i] : Math.min(hi, Math.max(lo, v[i]));
        return o;
    }
    static double quantile(double[] sorted, double q) {
        double pos = q * (sorted.length - 1); int lo = (int) Math.floor(pos), hi = (int) Math.ceil(pos);
        return sorted[lo] + (sorted[hi] - sorted[lo]) * (pos - lo);
    }
    static double mean(double[] a) { double s = 0; for (double v : a) s += v; return s / a.length; }

    @Override public void write(DataOutputStream o) throws IOException {
        o.writeInt(nFeatures); o.writeInt(featureIdx); o.writeUTF(featureName);
        for (double d : new double[]{b0, b1, xLo, xHi, yMin, yMax, trainR2}) o.writeDouble(d);
    }
    public static SimpleLinearRegression read(DataInputStream in) throws IOException {
        SimpleLinearRegression m = new SimpleLinearRegression();
        m.nFeatures = in.readInt(); m.featureIdx = in.readInt(); m.featureName = in.readUTF();
        m.b0 = in.readDouble(); m.b1 = in.readDouble(); m.xLo = in.readDouble(); m.xHi = in.readDouble();
        m.yMin = in.readDouble(); m.yMax = in.readDouble(); m.trainR2 = in.readDouble();
        return m;
    }
}
