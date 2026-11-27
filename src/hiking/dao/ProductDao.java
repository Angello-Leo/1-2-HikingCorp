package hiking.dao;

import hiking.db.Database;
import hiking.model.*;
import hiking.security.Secure;
import hiking.security.Session;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class ProductDao {
    private ProductDao() {}
    private static final String COLS = "id,sku,name,category,unit_price,cost_price,stock_qty,reorder_level,shelf_life_days,active";

    private static Product map(ResultSet r) throws SQLException {
        Product p = new Product(); p.id = r.getInt(1); p.sku = r.getString(2); p.name = r.getString(3); p.category = r.getString(4); p.unitPrice = r.getDouble(5); p.costPrice = r.getDouble(6);
        p.stockQty = r.getInt(7); p.reorderLevel = r.getInt(8); p.shelfLifeDays = r.getInt(9); p.active = r.getBoolean(10); return p;
    }

    /** Inventory list (inventory clerk) - filters are bound as parameters (no SQL injection).
     *  includeInactive = true also shows ARCHIVED items. */
    public static List<Product> search(String text, String category, boolean lowOnly, boolean includeInactive) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE);
        return query(text, category, lowOnly, includeInactive);
    }
    /** Active menu for the point-of-sale screen. */
    public static List<Product> forSale() throws SQLException { Session.require(Perm.SALES_CREATE); return query("", null, false, false); }

    private static List<Product> query(String text, String category, boolean lowOnly, boolean inactive) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT " + COLS + " FROM products WHERE 1=1");
        List<Object> args = new ArrayList<>();
        if (text != null && !text.trim().isEmpty()) { sql.append(" AND (name LIKE ? OR sku LIKE ?)"); String t = "%" + text.trim().replace("%", "").replace("_", "") + "%"; args.add(t); args.add(t); }
        if (category != null && !category.isEmpty()) { sql.append(" AND category=?"); args.add(category); }
        if (lowOnly) sql.append(" AND stock_qty<=reorder_level");
        if (!inactive) sql.append(" AND active=1");
        sql.append(" ORDER BY category, name");
        List<Product> out = new ArrayList<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement(sql.toString())) {
            for (int i = 0; i < args.size(); i++) ps.setObject(i + 1, args.get(i));
            try (ResultSet r = ps.executeQuery()) { while (r.next()) out.add(map(r)); }
        }
        return out;
    }

    private static void validate(Product p) {
        if (p.sku == null || !p.sku.trim().matches("[A-Za-z0-9\\-]{1,20}")) throw new IllegalArgumentException("SKU: 1-20 letters, numbers or dash.");
        if (p.name == null || p.name.trim().isEmpty() || p.name.length() > 100) throw new IllegalArgumentException("Product name is required.");
        if (p.category == null || p.category.isEmpty()) throw new IllegalArgumentException("Choose a category.");
        if (p.unitPrice < 0 || p.costPrice < 0) throw new IllegalArgumentException("Prices cannot be negative.");
        if (p.unitPrice < p.costPrice) throw new IllegalArgumentException("Selling price is lower than cost price.");
        if (p.stockQty < 0 || p.reorderLevel < 0) throw new IllegalArgumentException("Stock and reorder level cannot be negative.");
        if (p.shelfLifeDays < 1) throw new IllegalArgumentException("Shelf life must be at least 1 day.");
    }

    public static void create(Product p) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE); validate(p);
        try (Connection c = Database.get()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO products(sku,name,category,unit_price,cost_price,stock_qty,reorder_level,shelf_life_days) VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.sku.trim()); ps.setString(2, p.name.trim()); ps.setString(3, p.category); ps.setDouble(4, p.unitPrice); ps.setDouble(5, p.costPrice); ps.setInt(6, p.stockQty); ps.setInt(7, p.reorderLevel); ps.setInt(8, p.shelfLifeDays);
                ps.executeUpdate();
                try (ResultSet k = ps.getGeneratedKeys()) { k.next(); p.id = k.getInt(1); }
                if (p.stockQty > 0) movement(c, p.id, p.stockQty, "INITIAL", "Opening stock");
                c.commit();
            } catch (SQLIntegrityConstraintViolationException e) { c.rollback(); throw new IllegalArgumentException("SKU '" + p.sku + "' already exists."); }
            catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
        AuditDao.log("PRODUCT_CREATE", p.sku + " " + p.name);
    }

    public static void update(Product p) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE); validate(p);
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("UPDATE products SET sku=?,name=?,category=?,unit_price=?,cost_price=?,reorder_level=?,shelf_life_days=?,active=? WHERE id=?")) {
            ps.setString(1, p.sku.trim()); ps.setString(2, p.name.trim()); ps.setString(3, p.category); ps.setDouble(4, p.unitPrice); ps.setDouble(5, p.costPrice); ps.setInt(6, p.reorderLevel); ps.setInt(7, p.shelfLifeDays); ps.setBoolean(8, p.active); ps.setInt(9, p.id);
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) { throw new IllegalArgumentException("SKU '" + p.sku + "' already exists."); }
        AuditDao.log("PRODUCT_UPDATE", p.sku + " " + p.name + (p.active ? "" : " (archived)"));
    }

    // =====================================================================================
    //  ARCHIVE / RESTORE  (hides an item, nothing is lost)
    // =====================================================================================

    private static String labelOf(Connection c, int productId) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT sku, name FROM products WHERE id=?")) {
            ps.setInt(1, productId);
            try (ResultSet r = ps.executeQuery()) {
                if (!r.next()) throw new IllegalArgumentException("Product not found.");
                return r.getString(1) + " " + r.getString(2);
            }
        }
    }

    /** Hides an item from the menu and the normal list (active = 0). */
    public static void archive(int productId) throws SQLException { setArchived(productId, true); }

    /** Brings an archived item back (active = 1). */
    public static void restore(int productId) throws SQLException { setArchived(productId, false); }

    private static void setArchived(int productId, boolean archived) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE);
        String label;
        try (Connection c = Database.get()) {
            label = labelOf(c, productId);
            try (PreparedStatement ps = c.prepareStatement("UPDATE products SET active=? WHERE id=?")) {
                ps.setBoolean(1, !archived); ps.setInt(2, productId); ps.executeUpdate();
            }
        }
        AuditDao.log(archived ? "PRODUCT_ARCHIVE" : "PRODUCT_RESTORE", label);
    }

    // =====================================================================================
    //  PERMANENT DELETE  (secret code + password; clerk needs the admin's approval)
    // =====================================================================================

    /** The real delete. Only an ARCHIVED item that was NEVER sold may be deleted. Runs inside the caller's transaction. */
    private static String purge(Connection c, int productId) throws SQLException {
        String label = labelOf(c, productId);
        try (PreparedStatement ps = c.prepareStatement(
                "SELECT active, (SELECT COUNT(*) FROM sale_items WHERE product_id=?) FROM products WHERE id=?")) {
            ps.setInt(1, productId); ps.setInt(2, productId);
            try (ResultSet r = ps.executeQuery()) {
                r.next();
                if (r.getBoolean(1)) throw new IllegalStateException("Archive the item first.");
                if (r.getInt(2) > 0) throw new IllegalStateException("This item has sales history, so it can only stay archived.");
            }
        }
        try (PreparedStatement ps = c.prepareStatement("DELETE FROM stock_movements WHERE product_id=?")) { ps.setInt(1, productId); ps.executeUpdate(); }
        try (PreparedStatement ps = c.prepareStatement("DELETE FROM products WHERE id=?")) { ps.setInt(1, productId); ps.executeUpdate(); }
        return label;
    }

    /** ADMIN: confirms with their own password, then the item is deleted immediately. */
    public static void purgeAsAdmin(int productId, String password) throws SQLException {
        Session.require(Perm.PRODUCT_PURGE);
        Secure.confirmPassword(password);
        String label;
        try (Connection c = Database.get()) {
            c.setAutoCommit(false);
            try { label = purge(c, productId); c.commit(); }
            catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
        AuditDao.log("PRODUCT_PURGED", label + " (by admin)");
    }

    /** CLERK: confirms with their own password, then a PENDING request is saved for the admin. Nothing is deleted yet. */
    public static void requestPurge(int productId, String password) throws SQLException {
        Session.require(Perm.PURGE_REQUEST);
        Secure.confirmPassword(password);
        String label;
        try (Connection c = Database.get()) {
            label = labelOf(c, productId);
            try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM delete_requests WHERE product_id=? AND status='PENDING'")) {
                ps.setInt(1, productId);
                try (ResultSet r = ps.executeQuery()) { r.next(); if (r.getInt(1) > 0) throw new IllegalStateException("A delete request for this item is already waiting for the admin."); }
            }
            try (PreparedStatement ps = c.prepareStatement("INSERT INTO delete_requests(product_id,product_label,requested_by) VALUES(?,?,?)")) {
                ps.setInt(1, productId); ps.setString(2, label); ps.setInt(3, Session.id()); ps.executeUpdate();
            }
        }
        AuditDao.log("PURGE_REQUESTED", label);
    }

    /** ADMIN: the waiting delete requests. @return rows of {id, item, requested by, time} */
    public static List<String[]> pendingRequests() throws SQLException {
        Session.require(Perm.PRODUCT_PURGE);
        List<String[]> out = new ArrayList<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement(
                "SELECT r.id, r.product_label, u.username, r.requested_at FROM delete_requests r JOIN users u ON u.id=r.requested_by WHERE r.status='PENDING' ORDER BY r.id");
             ResultSet r = ps.executeQuery()) {
            while (r.next()) out.add(new String[]{String.valueOf(r.getInt(1)), r.getString(2), r.getString(3), r.getTimestamp(4).toString().substring(0, 16)});
        }
        return out;
    }

    /** ADMIN: approve (needs the admin's password, then the item is deleted) or reject (item stays archived). */
    public static void decide(int requestId, boolean approve, String password) throws SQLException {
        Session.require(Perm.PRODUCT_PURGE);
        if (approve) Secure.confirmPassword(password);
        String label;
        try (Connection c = Database.get()) {
            c.setAutoCommit(false);
            try {
                int productId;
                try (PreparedStatement ps = c.prepareStatement("SELECT product_id, product_label FROM delete_requests WHERE id=? AND status='PENDING' FOR UPDATE")) {
                    ps.setInt(1, requestId);
                    try (ResultSet r = ps.executeQuery()) {
                        if (!r.next()) throw new IllegalStateException("That request was already handled.");
                        productId = r.getInt(1); label = r.getString(2);
                    }
                }
                if (approve) purge(c, productId);
                try (PreparedStatement ps = c.prepareStatement("UPDATE delete_requests SET status=?, decided_by=?, decided_at=NOW() WHERE id=?")) {
                    ps.setString(1, approve ? "APPROVED" : "REJECTED"); ps.setInt(2, Session.id()); ps.setInt(3, requestId); ps.executeUpdate();
                }
                c.commit();
            } catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
        AuditDao.log(approve ? "PURGE_APPROVED" : "PURGE_REJECTED", label);
    }

    // =====================================================================================
    //  STOCK
    // =====================================================================================

    /** Positive = stock in, negative = wastage / correction. Refuses to take stock below zero. */
    public static void adjustStock(int productId, int delta, String reason, String note) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE);
        if (delta == 0) throw new IllegalArgumentException("Quantity cannot be zero.");
        if (Math.abs(delta) > 100000) throw new IllegalArgumentException("Quantity is too large.");
        try (Connection c = Database.get()) {
            c.setAutoCommit(false);
            try (PreparedStatement ps = c.prepareStatement("UPDATE products SET stock_qty = stock_qty + ? WHERE id=? AND stock_qty + ? >= 0")) {
                ps.setInt(1, delta); ps.setInt(2, productId); ps.setInt(3, delta);
                if (ps.executeUpdate() == 0) throw new IllegalArgumentException("Not enough stock for that adjustment.");
                movement(c, productId, delta, reason, note); c.commit();
            } catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
        AuditDao.log("STOCK_" + reason, "product id " + productId + " qty " + delta);
    }

    static void movement(Connection c, int productId, int qty, String reason, String note) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO stock_movements(product_id,qty_change,reason,note,user_id,created_at) VALUES(?,?,?,?,?,?)")) {
            ps.setInt(1, productId); ps.setInt(2, qty); ps.setString(3, reason); ps.setString(4, note == null ? null : note.substring(0, Math.min(200, note.length()))); ps.setInt(5, Session.id()); ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now())); ps.executeUpdate();
        }
    }

    /** @return rows of {time, product, change, reason, note, user} */
    public static List<String[]> movements(int limit) throws SQLException {
        Session.require(Perm.INVENTORY_MANAGE);
        List<String[]> out = new ArrayList<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT m.created_at,p.name,m.qty_change,m.reason,m.note,u.username FROM stock_movements m JOIN products p ON p.id=m.product_id LEFT JOIN users u ON u.id=m.user_id ORDER BY m.id DESC LIMIT ?")) {
            ps.setInt(1, limit);
            try (ResultSet r = ps.executeQuery()) { while (r.next()) out.add(new String[]{r.getTimestamp(1).toString().substring(0, 16), r.getString(2), (r.getInt(3) > 0 ? "+" : "") + r.getInt(3), r.getString(4), r.getString(5) == null ? "" : r.getString(5), r.getString(6) == null ? "" : r.getString(6)}); }
        }
        return out;
    }
}
