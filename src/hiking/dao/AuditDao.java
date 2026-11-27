package hiking.dao;

import hiking.db.Database;
import hiking.security.Session;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class AuditDao {
    private AuditDao() {}
    /** Never throws - an audit failure must not break the action being audited. */
    public static void log(String action, String details) { log(Session.id() == 0 ? null : Session.id(), Session.name(), action, details); }
    public static void log(Integer userId, String username, String action, String details) {
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("INSERT INTO audit_log(user_id,username,action,details,created_at) VALUES(?,?,?,?,?)")) {
            if (userId == null) ps.setNull(1, Types.INTEGER); else ps.setInt(1, userId);
            ps.setString(2, username); ps.setString(3, action); ps.setString(4, details == null ? null : details.substring(0, Math.min(300, details.length()))); ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now())); ps.executeUpdate();
        } catch (Exception ignored) { }
    }
    /** @return rows of {time, user, action, details} */
    public static List<String[]> recent(int limit) throws SQLException {
        hiking.security.Session.require(hiking.model.Perm.AUDIT_VIEW);
        List<String[]> out = new ArrayList<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT created_at,username,action,details FROM audit_log ORDER BY id DESC LIMIT ?")) {
            ps.setInt(1, limit);
            try (ResultSet r = ps.executeQuery()) { while (r.next()) out.add(new String[]{r.getTimestamp(1).toString().substring(0, 19), r.getString(2), r.getString(3), r.getString(4)}); }
        }
        return out;
    }
}
