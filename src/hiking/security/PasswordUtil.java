package hiking.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/** Salted PBKDF2-HMAC-SHA256 password hashing (JDK built-in, no external library). */
public final class PasswordUtil {
    private static final int ITER = 120_000, BITS = 256;
    private static final SecureRandom RND = new SecureRandom();
    private PasswordUtil() {}

    public static String newSalt() { byte[] s = new byte[16]; RND.nextBytes(s); return Base64.getEncoder().encodeToString(s); }
    public static String hash(String password, String salt) {
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return Base64.getEncoder().encodeToString(f.generateSecret(new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), ITER, BITS)).getEncoded());
        } catch (Exception e) { throw new IllegalStateException("Password hashing failed", e); }
    }
    public static boolean verify(String password, String salt, String expected) {
        return MessageDigest.isEqual(hash(password, salt).getBytes(), expected.getBytes());
    }
    /** @return null when OK, otherwise the reason the password is rejected. */
    public static String policyError(String p) {
        if (p == null || p.length() < 8) return "Password must be at least 8 characters.";
        if (p.length() > 64) return "Password is too long (max 64).";
        boolean letter = false, digit = false;
        for (char c : p.toCharArray()) { if (Character.isLetter(c)) letter = true; if (Character.isDigit(c)) digit = true; }
        return letter && digit ? null : "Password must contain letters and numbers.";
    }
}
