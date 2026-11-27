package hiking.ml;

import java.io.DataOutputStream;
import java.io.IOException;

public interface Regressor {
    String type();                                   // "SLR" or "RF"
    void fit(double[][] X, double[] y, String[] featureNames);
    double predict(double[] x);
    double[] importances();                          // normalised, sums to 1
    String describe();                               // equation / hyper-parameters
    void write(DataOutputStream out) throws IOException;

    default double[] predict(double[][] X) { double[] p = new double[X.length]; for (int i = 0; i < p.length; i++) p[i] = predict(X[i]); return p; }
}
