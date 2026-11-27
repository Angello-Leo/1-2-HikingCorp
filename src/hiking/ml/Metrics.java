package hiking.ml;

import java.io.*;
import java.util.*;

/** Regression metrics (+ optional classification metrics derived from a threshold). */
public final class Metrics {
    public double mae, mse, rmse, r2, wape; public int n;

    public static Metrics regression(double[] a, double[] p) {
        Metrics m = new Metrics(); m.n = a.length;
        double ae = 0, se = 0, sa = 0, mean = SimpleLinearRegression.mean(a), sst = 0;
        for (int i = 0; i < a.length; i++) { double e = a[i] - p[i]; ae += Math.abs(e); se += e * e; sa += Math.abs(a[i]); sst += (a[i] - mean) * (a[i] - mean); }
        m.mae = ae / a.length; m.mse = se / a.length; m.rmse = Math.sqrt(m.mse);
        m.r2 = sst > 0 ? 1 - se / sst : 0; m.wape = sa > 0 ? 100.0 * ae / sa : 0;
        return m;
    }
    void write(DataOutputStream o) throws IOException { o.writeInt(n); for (double d : new double[]{mae, mse, rmse, r2, wape}) o.writeDouble(d); }
    static Metrics read(DataInputStream in) throws IOException { Metrics m = new Metrics(); m.n = in.readInt(); m.mae = in.readDouble(); m.mse = in.readDouble(); m.rmse = in.readDouble(); m.r2 = in.readDouble(); m.wape = in.readDouble(); return m; }

    /** Classification quality when predictions are turned into labels with a threshold. */
    public static final class ClassMetrics {
        public double accuracy, precision, recall, f1; public int tp, fp, tn, fn;
        /** positiveBelow = true: positive class means value <= threshold (slow moving); false: value >= threshold (best seller). */
        public static ClassMetrics of(double[] a, double[] p, double thr, boolean positiveBelow) {
            ClassMetrics c = new ClassMetrics();
            for (int i = 0; i < a.length; i++) {
                boolean ta = positiveBelow ? a[i] <= thr : a[i] >= thr, tp = positiveBelow ? p[i] <= thr : p[i] >= thr;
                if (ta && tp) c.tp++; else if (!ta && tp) c.fp++; else if (!ta) c.tn++; else c.fn++;
            }
            int n = a.length; c.accuracy = n == 0 ? 0 : (double) (c.tp + c.tn) / n;
            c.precision = c.tp + c.fp == 0 ? 0 : (double) c.tp / (c.tp + c.fp);
            c.recall = c.tp + c.fn == 0 ? 0 : (double) c.tp / (c.tp + c.fn);
            c.f1 = c.precision + c.recall == 0 ? 0 : 2 * c.precision * c.recall / (c.precision + c.recall);
            return c;
        }
        void write(DataOutputStream o) throws IOException { for (double d : new double[]{accuracy, precision, recall, f1}) o.writeDouble(d); for (int v : new int[]{tp, fp, tn, fn}) o.writeInt(v); }
        static ClassMetrics read(DataInputStream in) throws IOException { ClassMetrics c = new ClassMetrics(); c.accuracy = in.readDouble(); c.precision = in.readDouble(); c.recall = in.readDouble(); c.f1 = in.readDouble(); c.tp = in.readInt(); c.fp = in.readInt(); c.tn = in.readInt(); c.fn = in.readInt(); return c; }
    }

    /** Metric glossary shown in the Evaluation tab: {abbr, name, formula, meaning, how to read}. */
    public static final String[][] GLOSSARY = {
        {"MAE", "Mean Absolute Error", "(1/n) Σ |actual − predicted|", "Average size of the prediction error, in the same unit as the target.", "Lower is better. 0 = perfect."},
        {"MSE", "Mean Squared Error", "(1/n) Σ (actual − predicted)²", "Average squared error. Big mistakes are punished more than small ones.", "Lower is better. Unit is squared."},
        {"RMSE", "Root Mean Squared Error", "√MSE", "Typical error size in the target's own unit; sensitive to outliers.", "Lower is better. Compare with MAE: RMSE >> MAE means a few large errors."},
        {"R²", "Coefficient of Determination", "1 − SSres / SStot", "Share of the variation in the target that the model explains.", "1 = perfect, 0 = no better than predicting the average, <0 = worse than the average."},
        {"WAPE", "Weighted Absolute Percentage Error", "Σ|error| / Σ|actual| × 100", "Total error as a percentage of total actual volume (stable even when some actual values are near zero).", "Lower is better. 10% = predictions are off by about 10% of volume."},
        {"CV RMSE / CV R²", "10-fold Cross-Validation", "mean over 10 folds", "The data is split into 10 parts; each part is used once as the test set. Shows whether the score is stable and not just luck of one split.", "Should be close to the hold-out test score. A big gap means over-fitting."},
        {"OOB R²", "Out-of-Bag R² (Random Forest)", "R² on samples left out of each tree's bootstrap", "Free internal validation score of the forest.", "Should be close to test R²."},
        {"Accuracy", "Classification Accuracy", "(TP + TN) / total", "Share of items whose class (slow-moving / best-seller or not) is predicted correctly.", "Higher is better."},
        {"Precision", "Precision", "TP / (TP + FP)", "Of the items flagged as slow-moving / best-seller, how many truly are.", "Higher = fewer false alarms."},
        {"Recall", "Recall (Sensitivity)", "TP / (TP + FN)", "Of all truly slow-moving / best-selling items, how many were found.", "Higher = fewer missed items."},
        {"F1", "F1 Score", "2 × P × R / (P + R)", "Balance between precision and recall.", "Higher is better. 1 = perfect."},
    };
}
