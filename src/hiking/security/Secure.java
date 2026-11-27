/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hiking.security;

import hiking.dao.AuditDao;
import hiking.db.Database;
import hiking.model.Perm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leo Angello
 */
public class Secure {
    private Secure() {}

    private static int fails;
    private static long lockedUntil;

    /** Throws if the delete feature is locked after too many wrong tries. */
    private static void checkLock() {
        if (System.currentTimeMillis() < lockedUntil)
            throw new SecurityException("Locked for a few minutes. Try again later.");
    }

    /** Counts a wrong try, locks for 5 minutes after 3, and logs it. */
    private static void fail(String what) {
        AuditDao.log("DELETE_AUTH_FAILED", "wrong " + what);
        if (++fails >= 3) {
            lockedUntil = System.currentTimeMillis() + 5 * 60_000L;
            fails = 0;
        }
        throw new SecurityException("Incorrect " + what + ".");
    }

    /** Re-checks the password of the user who is logged in right now. */
    public static void confirmPassword(String password) throws SQLException {
        checkLock();
        if (password == null || password.isEmpty()) fail("password");
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT password_hash, salt FROM users WHERE id=?")) {
            ps.setInt(1, Session.id());
            try (ResultSet r = ps.executeQuery()) {
                if (!r.next() || !PasswordUtil.verify(password, r.getString(2), r.getString(1)))
                    fail("password");
            }
        }
        fails = 0;
    }

    /** Checks the secret code that unlocks delete mode (stored hashed in the settings table). */
    public static void confirmSecretCode(String code) throws SQLException {
    checkLock();
    if (code == null || code.isEmpty()) fail("secret code");
    try (Connection c = Database.get();
         PreparedStatement ps = c.prepareStatement("SELECT svalue, salt FROM settings WHERE skey='purge_code'");
         ResultSet r = ps.executeQuery()) {
        if (!r.next()) throw new IllegalStateException("No secret code has been set yet. The administrator sets it on the Account page.");
        if (!PasswordUtil.verify(code, r.getString(2), r.getString(1))) fail("secret code");
    }
    fails = 0;
    AuditDao.log("DELETE_MODE_ON", "secret code accepted");
}

    /** True once an administrator has set a secret code. */
    public static boolean hasSecretCode() throws SQLException {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT 1 FROM settings WHERE skey='purge_code'");
             ResultSet r = ps.executeQuery()) {
            return r.next();
        }
    }

    /** Administrator only: creates or changes the secret code. */
    public static void setSecretCode(String code) throws SQLException {
        Session.require(Perm.PRODUCT_PURGE);
        if (code == null || code.length() < 6)
            throw new IllegalArgumentException("The secret code must be at least 6 characters.");
        String salt = PasswordUtil.newSalt();
        String hash = PasswordUtil.hash(code, salt);
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("REPLACE INTO settings(skey, svalue, salt) VALUES('purge_code', ?, ?)")) {
            ps.setString(1, hash);
            ps.setString(2, salt);
            ps.executeUpdate();
        }
        AuditDao.log("PURGE_CODE_CHANGED", "secret code updated");
    }
}
