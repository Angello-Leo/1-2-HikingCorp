package hiking.ml;

import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Save / load of trained models in a custom binary format (.hkm) - deliberately NOT Java serialization
 * (which is unsafe to deserialize from files). The file carries a magic number, a version, the dataset
 * checksum and a trailing SHA-256 integrity hash; tampered / corrupt files are rejected.
 */
public final class ModelStore {
    private static final int MAGIC = 0x484B4D31, VERSION = 1;
    private ModelStore() {}

    public static File file(Task t, String type) { return new File(hiking.AppPaths.models(), t.key + "_" + type.toLowerCase() + ".hkm"); }

    public static void save(Task t, ModelBundle b) throws IOException {
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        try (DataOutputStream pd = new DataOutputStream(payload)) { b.model.write(pd); }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream o = new DataOutputStream(bos);
        o.writeInt(MAGIC); o.writeInt(VERSION); o.writeUTF(b.model.type()); o.writeUTF(t.key);
        b.eval.write(o);
        byte[] p = payload.toByteArray(); o.writeInt(p.length); o.write(p); o.flush();
        byte[] body = bos.toByteArray();
        File f = file(t, b.model.type()), tmp = new File(f.getPath() + ".tmp");
        try (FileOutputStream fo = new FileOutputStream(tmp)) { fo.write(body); fo.write(digest(body)); }
        if (f.exists() && !f.delete()) throw new IOException("Cannot replace " + f);
        if (!tmp.renameTo(f)) throw new IOException("Cannot write " + f);
    }

    public static ModelBundle load(Task t, String type) throws IOException {
        File f = file(t, type);
        byte[] all = java.nio.file.Files.readAllBytes(f.toPath());
        if (all.length < 64) throw new IOException("Model file too small");
        byte[] body = Arrays.copyOf(all, all.length - 32), sum = Arrays.copyOfRange(all, all.length - 32, all.length);
        if (!MessageDigest.isEqual(digest(body), sum)) throw new IOException("Model file failed integrity check (SHA-256 mismatch)");
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(body));
        if (in.readInt() != MAGIC || in.readInt() != VERSION) throw new IOException("Unsupported model file");
        String ty = in.readUTF(), key = in.readUTF();
        if (!ty.equals(type) || !key.equals(t.key)) throw new IOException("Model file does not match task");
        Evaluation ev = Evaluation.read(in);
        int len = in.readInt(); if (len < 0 || len > body.length) throw new IOException("Corrupt model payload");
        byte[] p = new byte[len]; in.readFully(p);
        DataInputStream pi = new DataInputStream(new ByteArrayInputStream(p));
        Regressor m = type.equals("SLR") ? SimpleLinearRegression.read(pi) : RandomForestRegression.read(pi);
        return new ModelBundle(m, ev);
    }
    private static byte[] digest(byte[] b) { try { return MessageDigest.getInstance("SHA-256").digest(b); } catch (Exception e) { throw new IllegalStateException(e); } }
}
