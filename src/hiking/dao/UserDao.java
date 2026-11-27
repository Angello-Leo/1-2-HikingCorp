package hiking.dao;

import hiking.db.Database;
import hiking.model.*;
import hiking.security.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class UserDao {
    private UserDao() {}
    private static final int MAX_FAILS = 5, LOCK_MINUTES = 15;
    private static final String DUMMY_SALT = PasswordUtil.newSalt();

    public static final class LoginResult { public User user; public String error; }

    /** Verifies credentials with lockout after repeated failures. Never reveals which part was wrong. */
    public static LoginResult authenticate(String username, String password) throws SQLException {
        LoginResult res = new LoginResult();
        username = username == null ? "" : username.trim();
        try (Connection c = Database.get()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT id,username,full_name,password_hash,salt,role,active,failed_attempts,locked_until,last_login,created_at FROM users WHERE username=?")) {
                ps.setString(1, username);
                try (ResultSet r = ps.executeQuery()) {
                    if (!r.next()) { PasswordUtil.hash(password, DUMMY_SALT); res.error = "Invalid username or password."; AuditDao.log(null, username, "LOGIN_FAILED", "unknown user"); return res; }
                    int id = r.getInt("id"); Timestamp lock = r.getTimestamp("locked_until");
                    if (lock != null && lock.toLocalDateTime().isAfter(LocalDateTime.now())) { res.error = "Account locked after too many attempts. Try again after " + lock.toLocalDateTime().toLocalTime().withNano(0) + "."; return res; }
                    if (!r.getBoolean("active")) { res.error = "This account is disabled. Contact the administrator."; return res; }
                    if (!PasswordUtil.verify(password, r.getString("salt"), r.getString("password_hash"))) {
                        int fails = r.getInt("failed_attempts") + 1;
                        try (PreparedStatement up = c.prepareStatement("UPDATE users SET failed_attempts=?, locked_until=? WHERE id=?")) {
                            up.setInt(1, fails >= MAX_FAILS ? 0 : fails);
                            if (fails >= MAX_FAILS) up.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().plusMinutes(LOCK_MINUTES))); else up.setNull(2, Types.TIMESTAMP);
                            up.setInt(3, id); up.executeUpdate();
                        }
                        AuditDao.log(id, username, "LOGIN_FAILED", "bad password (" + fails + ")");
                        res.error = fails >= MAX_FAILS ? "Too many failed attempts. Account locked for " + LOCK_MINUTES + " minutes." : "Invalid username or password.";
                        return res;
                    }
                    User u = map(r);
                    try (PreparedStatement up = c.prepareStatement("UPDATE users SET failed_attempts=0, locked_until=NULL, last_login=? WHERE id=?")) { up.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now())); up.setInt(2, id); up.executeUpdate(); }
                    AuditDao.log(id, u.username, "LOGIN", "signed in as " + u.role);
                    res.user = u; return res;
                }
            }
        }
    }

    private static User map(ResultSet r) throws SQLException {
        User u = new User(); u.id = r.getInt("id"); u.username = r.getString("username"); u.fullName = r.getString("full_name"); u.role = Role.valueOf(r.getString("role")); u.active = r.getBoolean("active");
        Timestamp t = r.getTimestamp("last_login"); u.lastLogin = t == null ? null : t.toLocalDateTime(); u.createdAt = r.getTimestamp("created_at").toLocalDateTime(); return u;
    }

    public static List<User> list() throws SQLException {
        Session.require(Perm.USERS_MANAGE);
        List<User> out = new ArrayList<>();
        try (Connection c = Database.get(); Statement s = c.createStatement(); ResultSet r = s.executeQuery("SELECT id,username,full_name,role,active,last_login,created_at FROM users ORDER BY id")) { while (r.next()) out.add(map(r)); }
        return out;
    }

    public static void create(String username, String fullName, Role role, String password) throws SQLException {
        Session.require(Perm.USERS_MANAGE);
        if (username == null || !username.matches("[A-Za-z0-9_.]{3,30}")) throw new IllegalArgumentException("Username: 3-30 letters, numbers, dot or underscore.");
        if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 100) throw new IllegalArgumentException("Full name is required.");
        if (role == null) throw new IllegalArgumentException("Choose a user level.");
        String pe = PasswordUtil.policyError(password); if (pe != null) throw new IllegalArgumentException(pe);
        String salt = PasswordUtil.newSalt();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("INSERT INTO users(username,full_name,password_hash,salt,role) VALUES(?,?,?,?,?)")) {
            ps.setString(1, username.trim()); ps.setString(2, fullName.trim()); ps.setString(3, PasswordUtil.hash(password, salt)); ps.setString(4, salt); ps.setString(5, role.name()); ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) { throw new IllegalArgumentException("Username '" + username + "' already exists."); }
        AuditDao.log("USER_CREATE", username + " (" + role + ")");
    }

    /** Self-service sign-up from the login window: always creates an active STAFF account (no session needed). */
    public static void register(String username, String fullName, String password) throws SQLException {
        username = username == null ? "" : username.trim();
        if (!username.matches("[A-Za-z0-9_.]{3,30}")) throw new IllegalArgumentException("Username: 3-30 letters, numbers, dot or underscore.");
        if (fullName == null || fullName.trim().isEmpty() || fullName.length() > 100) throw new IllegalArgumentException("Full name is required.");
        String pe = PasswordUtil.policyError(password); if (pe != null) throw new IllegalArgumentException(pe);
        String salt = PasswordUtil.newSalt();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("INSERT INTO users(username,full_name,password_hash,salt,role) VALUES(?,?,?,?,?)")) {
            ps.setString(1, username); ps.setString(2, fullName.trim()); ps.setString(3, PasswordUtil.hash(password, salt)); ps.setString(4, salt); ps.setString(5, Role.STAFF.name()); ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) { throw new IllegalArgumentException("Username '" + username + "' is already taken."); }
        AuditDao.log(null, username, "SIGN_UP", "new account registered as STAFF");
    }

    public static void update(int id, String fullName, Role role, boolean active) throws SQLException {
        Session.require(Perm.USERS_MANAGE);
        if (fullName == null || fullName.trim().isEmpty()) throw new IllegalArgumentException("Full name is required.");
        if (id == Session.id() && (!active || role != Session.user().role)) throw new IllegalArgumentException("You cannot disable or change the level of your own account.");
        try (Connection c = Database.get()) {
            if (!active || role != Role.ADMIN) {   // keep at least one active administrator
                try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM users WHERE role='ADMIN' AND active=1 AND id<>?")) { ps.setInt(1, id); try (ResultSet r = ps.executeQuery()) { r.next();
                    if (r.getInt(1) == 0) { try (PreparedStatement q = c.prepareStatement("SELECT role FROM users WHERE id=?")) { q.setInt(1, id); try (ResultSet rr = q.executeQuery()) { if (rr.next() && rr.getString(1).equals("ADMIN")) throw new IllegalArgumentException("At least one active administrator is required."); } } } } }
            }
            try (PreparedStatement ps = c.prepareStatement("UPDATE users SET full_name=?, role=?, active=? WHERE id=?")) { ps.setString(1, fullName.trim()); ps.setString(2, role.name()); ps.setBoolean(3, active); ps.setInt(4, id); ps.executeUpdate(); }
        }
        AuditDao.log("USER_UPDATE", "user id " + id + " -> " + role + (active ? "" : " (disabled)"));
    }

    /** Permanently removes an account. Refused for your own account, the last active administrator, and users who already have recorded sales or stock movements (disable those instead). */
    public static void delete(int id) throws SQLException {
        Session.require(Perm.USERS_MANAGE);
        if (id == Session.id()) throw new IllegalArgumentException("You cannot delete your own account.");
        try (Connection c = Database.get()) {
            String name, role; boolean active;
            try (PreparedStatement ps = c.prepareStatement("SELECT username,role,active FROM users WHERE id=?")) {
                ps.setInt(1, id);
                try (ResultSet r = ps.executeQuery()) { if (!r.next()) throw new IllegalArgumentException("That user no longer exists."); name = r.getString(1); role = r.getString(2); active = r.getBoolean(3); }
            }
            if (role.equals(Role.ADMIN.name()) && active) {
                try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM users WHERE role='ADMIN' AND active=1 AND id<>?")) {
                    ps.setInt(1, id); try (ResultSet r = ps.executeQuery()) { r.next(); if (r.getInt(1) == 0) throw new IllegalArgumentException("You cannot delete the last active administrator."); }
                }
            }
            try (PreparedStatement ps = c.prepareStatement("DELETE FROM users WHERE id=?")) { ps.setInt(1, id); ps.executeUpdate(); }
            catch (SQLIntegrityConstraintViolationException e) { throw new IllegalArgumentException("'" + name + "' already has recorded sales or stock movements, so the account cannot be deleted without breaking those records. Disable it instead (Edit > Status)."); }
            AuditDao.log("USER_DELETE", name + " (" + role + ")");
        }
    }

    public static void resetPassword(int id, String newPassword) throws SQLException {
        Session.require(Perm.USERS_MANAGE);
        String pe = PasswordUtil.policyError(newPassword); if (pe != null) throw new IllegalArgumentException(pe);
        setPassword(id, newPassword); AuditDao.log("PASSWORD_RESET", "user id " + id);
    }

    public static void changeOwnPassword(String oldPw, String newPw) throws SQLException {
        if (Session.user() == null) throw new SecurityException("Please sign in first.");
        String pe = PasswordUtil.policyError(newPw); if (pe != null) throw new IllegalArgumentException(pe);
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT password_hash,salt FROM users WHERE id=?")) {
            ps.setInt(1, Session.id());
            try (ResultSet r = ps.executeQuery()) { if (!r.next() || !PasswordUtil.verify(oldPw, r.getString(2), r.getString(1))) throw new IllegalArgumentException("Current password is incorrect."); }
        }
        setPassword(Session.id(), newPw); AuditDao.log("PASSWORD_CHANGE", "own password");
    }

    private static void setPassword(int id, String pw) throws SQLException {
        String salt = PasswordUtil.newSalt();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("UPDATE users SET password_hash=?, salt=?, failed_attempts=0, locked_until=NULL WHERE id=?")) { ps.setString(1, PasswordUtil.hash(pw, salt)); ps.setString(2, salt); ps.setInt(3, id); ps.executeUpdate(); }
    }
}
