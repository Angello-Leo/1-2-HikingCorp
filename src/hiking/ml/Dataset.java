package hiking.ml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

/** A CSV dataset: raw rows for preview + cleaned numeric matrix for training. */
public final class Dataset {
    public final String fileName;
    public final String[] header;
    public final List<String[]> rows = new ArrayList<>();
    public final String[] featureNames;
    public final String target;
    public double[][] X;
    public double[] y;
    public int droppedRows;
    public String sha256;

    private Dataset(String fileName, String[] header, String[] features, String target) {
        this.fileName = fileName; this.header = header; this.featureNames = features; this.target = target;
    }

    public static Dataset load(File csv, String[] features, String target) throws IOException {
        byte[] bytes = java.nio.file.Files.readAllBytes(csv.toPath());
        List<String[]> all = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) { if (!line.trim().isEmpty()) all.add(parse(line)); }
        }
        if (all.size() < 2) throw new IOException("Dataset is empty: " + csv.getName());
        String[] header = all.get(0);
        for (int i = 0; i < header.length; i++) header[i] = header[i].trim().replace("﻿", "");
        Dataset d = new Dataset(csv.getName(), header, features, target);
        d.sha256 = sha(bytes);
        int[] col = new int[features.length];
        for (int i = 0; i < features.length; i++) col[i] = indexOf(header, features[i]);
        int tcol = indexOf(header, target);
        List<double[]> xs = new ArrayList<>(); List<Double> ys = new ArrayList<>();
        for (int r = 1; r < all.size(); r++) {
            String[] row = all.get(r);
            d.rows.add(row);
            try {   // data cleaning: drop rows with missing / non numeric / NaN values
                double[] x = new double[features.length];
                for (int i = 0; i < x.length; i++) { x[i] = Double.parseDouble(row[col[i]].trim()); if (Double.isNaN(x[i]) || Double.isInfinite(x[i])) throw new NumberFormatException(); }
                double yv = Double.parseDouble(row[tcol].trim());
                if (Double.isNaN(yv) || Double.isInfinite(yv)) throw new NumberFormatException();
                xs.add(x); ys.add(yv);
            } catch (RuntimeException ex) { d.droppedRows++; }
        }
        d.X = xs.toArray(new double[0][]);
        d.y = new double[ys.size()];
        for (int i = 0; i < d.y.length; i++) d.y[i] = ys.get(i);
        return d;
    }

    public int size() { return y.length; }

    /** Summary statistics per numeric column: {name, min, max, mean, std}. */
    public List<String[]> summary() {
        List<String[]> out = new ArrayList<>();
        for (int c = 0; c < header.length; c++) {
            double min = Double.MAX_VALUE, max = -Double.MAX_VALUE, sum = 0, sq = 0; int n = 0; boolean numeric = true;
            for (String[] r : rows) {
                try { double v = Double.parseDouble(r[c].trim()); min = Math.min(min, v); max = Math.max(max, v); sum += v; sq += v * v; n++; }
                catch (RuntimeException e) { numeric = false; break; }
            }
            if (!numeric || n == 0) { out.add(new String[]{header[c], "text/date", "", "", ""}); continue; }
            double mean = sum / n, std = Math.sqrt(Math.max(0, sq / n - mean * mean));
            out.add(new String[]{header[c], f(min), f(max), f(mean), f(std)});
        }
        return out;
    }
    private static String f(double v) { return String.format("%.2f", v); }

    private static int indexOf(String[] h, String name) throws IOException {
        for (int i = 0; i < h.length; i++) if (h[i].equalsIgnoreCase(name)) return i;
        throw new IOException("Column '" + name + "' not found in dataset header.");
    }
    static String[] parse(String line) {
        List<String> out = new ArrayList<>(); StringBuilder sb = new StringBuilder(); boolean q = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (q) { if (c == '"') { if (i + 1 < line.length() && line.charAt(i + 1) == '"') { sb.append('"'); i++; } else q = false; } else sb.append(c); }
            else if (c == '"') q = true; else if (c == ',') { out.add(sb.toString()); sb.setLength(0); } else sb.append(c);
        }
        out.add(sb.toString());
        return out.toArray(new String[0]);
    }
    public static String sha(byte[] b) {
        try { byte[] h = MessageDigest.getInstance("SHA-256").digest(b); StringBuilder s = new StringBuilder(); for (byte x : h) s.append(String.format("%02x", x)); return s.toString(); }
        catch (Exception e) { throw new IllegalStateException(e); }
    }
}
