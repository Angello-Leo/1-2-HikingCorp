package hiking.ml;

import java.io.*;

/** Everything we know about how well a trained model performs (persisted next to the model). */
public final class Evaluation {
    public Metrics train, test;
    public double cvRmse, cvR2, cvR2Std, oobR2 = Double.NaN;
    public int nTotal, nTrain, nTest, droppedRows;
    public double[] testActual, testPred, importance;
    public String[] featureNames;
    public String params = "", datasetSha = "";
    public long trainedAt;
    public double classThreshold = Double.NaN;
    public Metrics.ClassMetrics cls;

    void write(DataOutputStream o) throws IOException {
        train.write(o); test.write(o);
        for (double d : new double[]{cvRmse, cvR2, cvR2Std, oobR2, classThreshold}) o.writeDouble(d);
        o.writeInt(nTotal); o.writeInt(nTrain); o.writeInt(nTest); o.writeInt(droppedRows);
        o.writeInt(testActual.length); for (int i = 0; i < testActual.length; i++) { o.writeDouble(testActual[i]); o.writeDouble(testPred[i]); }
        o.writeInt(importance.length); for (double d : importance) o.writeDouble(d);
        o.writeInt(featureNames.length); for (String s : featureNames) o.writeUTF(s);
        o.writeUTF(params); o.writeUTF(datasetSha); o.writeLong(trainedAt);
        o.writeBoolean(cls != null); if (cls != null) cls.write(o);
    }
    static Evaluation read(DataInputStream in) throws IOException {
        Evaluation e = new Evaluation(); e.train = Metrics.read(in); e.test = Metrics.read(in);
        e.cvRmse = in.readDouble(); e.cvR2 = in.readDouble(); e.cvR2Std = in.readDouble(); e.oobR2 = in.readDouble(); e.classThreshold = in.readDouble();
        e.nTotal = in.readInt(); e.nTrain = in.readInt(); e.nTest = in.readInt(); e.droppedRows = in.readInt();
        int n = in.readInt(); if (n < 0 || n > 1_000_000) throw new IOException("Corrupt evaluation");
        e.testActual = new double[n]; e.testPred = new double[n]; for (int i = 0; i < n; i++) { e.testActual[i] = in.readDouble(); e.testPred[i] = in.readDouble(); }
        n = in.readInt(); if (n < 0 || n > 10_000) throw new IOException("Corrupt evaluation"); e.importance = new double[n]; for (int i = 0; i < n; i++) e.importance[i] = in.readDouble();
        n = in.readInt(); if (n < 0 || n > 10_000) throw new IOException("Corrupt evaluation"); e.featureNames = new String[n]; for (int i = 0; i < n; i++) e.featureNames[i] = in.readUTF();
        e.params = in.readUTF(); e.datasetSha = in.readUTF(); e.trainedAt = in.readLong();
        if (in.readBoolean()) e.cls = Metrics.ClassMetrics.read(in);
        return e;
    }
}
