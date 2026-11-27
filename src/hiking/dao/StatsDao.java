package hiking.dao;

import hiking.db.Database;
import hiking.model.*;
import hiking.security.Session;

import java.sql.*;
import java.time.*;
import java.util.*;

/** Read-only aggregates for the dashboards and for the machine-learning inputs. */
public final class StatsDao {
    private StatsDao() {}
    private static Timestamp ts(LocalDateTime t) { return Timestamp.valueOf(t); }
    private static void needLogin() { if (Session.user() == null) throw new SecurityException("Please sign in first."); }

    public static final class Cards {
        public double todaySales, monthSales, stockValue, avgBasket; public int todayTrans, lowStock, outOfStock, products, activeUsers, movementsToday;
        public double mySalesToday, myMonthSales; public int myTransToday;
    }

    public static Cards cards() throws SQLException {
        needLogin(); Cards k = new Cards();
        LocalDateTime today = LocalDate.now().atStartOfDay(), month = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        try (Connection c = Database.get()) {
            try (PreparedStatement ps = c.prepareStatement("SELECT COALESCE(SUM(total),0),COUNT(*) FROM sales WHERE sale_date>=?")) { ps.setTimestamp(1, ts(today)); try (ResultSet r = ps.executeQuery()) { r.next(); k.todaySales = r.getDouble(1); k.todayTrans = r.getInt(2); } }
            try (PreparedStatement ps = c.prepareStatement("SELECT COALESCE(SUM(total),0) FROM sales WHERE sale_date>=?")) { ps.setTimestamp(1, ts(month)); try (ResultSet r = ps.executeQuery()) { r.next(); k.monthSales = r.getDouble(1); } }
            try (PreparedStatement ps = c.prepareStatement("SELECT COALESCE(SUM(total),0),COUNT(*) FROM sales WHERE sale_date>=? AND cashier_id=?")) { ps.setTimestamp(1, ts(today)); ps.setInt(2, Session.id()); try (ResultSet r = ps.executeQuery()) { r.next(); k.mySalesToday = r.getDouble(1); k.myTransToday = r.getInt(2); } }
            try (PreparedStatement ps = c.prepareStatement("SELECT COALESCE(SUM(total),0) FROM sales WHERE sale_date>=? AND cashier_id=?")) { ps.setTimestamp(1, ts(month)); ps.setInt(2, Session.id()); try (ResultSet r = ps.executeQuery()) { r.next(); k.myMonthSales = r.getDouble(1); } }
            try (Statement s = c.createStatement(); ResultSet r = s.executeQuery("SELECT COUNT(*),COALESCE(SUM(stock_qty<=reorder_level),0),COALESCE(SUM(stock_qty=0),0),COALESCE(SUM(stock_qty*cost_price),0) FROM products WHERE active=1")) { r.next(); k.products = r.getInt(1); k.lowStock = r.getInt(2); k.outOfStock = r.getInt(3); k.stockValue = r.getDouble(4); }
            try (Statement s = c.createStatement(); ResultSet r = s.executeQuery("SELECT COUNT(*) FROM users WHERE active=1")) { r.next(); k.activeUsers = r.getInt(1); }
            try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM stock_movements WHERE created_at>=?")) { ps.setTimestamp(1, ts(today)); try (ResultSet r = ps.executeQuery()) { r.next(); k.movementsToday = r.getInt(1); } }
        }
        k.avgBasket = k.todayTrans == 0 ? 0 : k.todaySales / k.todayTrans;
        return k;
    }

    /** Daily totals for the last N days (including today), zeros filled in. Sales personnel see their own sales. */
    public static LinkedHashMap<LocalDate, Double> dailySales(int days) throws SQLException {
        needLogin(); LinkedHashMap<LocalDate, Double> m = new LinkedHashMap<>();
        LocalDate start = LocalDate.now().minusDays(days - 1);
        for (int i = 0; i < days; i++) m.put(start.plusDays(i), 0.0);
        boolean mine = Session.user().role == Role.SALES_PERSONNEL;
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT DATE(sale_date),SUM(total) FROM sales WHERE sale_date>=?" + (mine ? " AND cashier_id=?" : "") + " GROUP BY DATE(sale_date)")) {
            ps.setTimestamp(1, ts(start.atStartOfDay())); if (mine) ps.setInt(2, Session.id());
            try (ResultSet r = ps.executeQuery()) { while (r.next()) m.put(r.getDate(1).toLocalDate(), r.getDouble(2)); }
        }
        return m;
    }

    /** All-cashier daily total of one date (used by the sales forecast as "yesterday"). */
    public static double salesOn(LocalDate d) throws SQLException {
        needLogin();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT COALESCE(SUM(total),0) FROM sales WHERE sale_date>=? AND sale_date<?")) {
            ps.setTimestamp(1, ts(d.atStartOfDay())); ps.setTimestamp(2, ts(d.plusDays(1).atStartOfDay())); try (ResultSet r = ps.executeQuery()) { r.next(); return r.getDouble(1); }
        }
    }

    public static LinkedHashMap<String, Double> topProducts(int days, int limit) throws SQLException {
        needLogin(); LinkedHashMap<String, Double> m = new LinkedHashMap<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT p.name,SUM(i.line_total) q FROM sale_items i JOIN sales s ON s.id=i.sale_id JOIN products p ON p.id=i.product_id WHERE s.sale_date>=? GROUP BY p.id,p.name ORDER BY q DESC LIMIT ?")) {
            ps.setTimestamp(1, ts(LocalDate.now().minusDays(days).atStartOfDay())); ps.setInt(2, limit);
            try (ResultSet r = ps.executeQuery()) { while (r.next()) m.put(r.getString(1), r.getDouble(2)); }
        }
        return m;
    }

    public static LinkedHashMap<String, Double> categorySales(int days) throws SQLException {
        needLogin(); LinkedHashMap<String, Double> m = new LinkedHashMap<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT p.category,SUM(i.line_total) t FROM sale_items i JOIN sales s ON s.id=i.sale_id JOIN products p ON p.id=i.product_id WHERE s.sale_date>=? GROUP BY p.category ORDER BY t DESC")) {
            ps.setTimestamp(1, ts(LocalDate.now().minusDays(days).atStartOfDay()));
            try (ResultSet r = ps.executeQuery()) { while (r.next()) m.put(r.getString(1), r.getDouble(2)); }
        }
        return m;
    }

    public static LinkedHashMap<String, Double> stockByCategory() throws SQLException {
        needLogin(); LinkedHashMap<String, Double> m = new LinkedHashMap<>();
        try (Connection c = Database.get(); Statement s = c.createStatement(); ResultSet r = s.executeQuery("SELECT category,SUM(stock_qty) FROM products WHERE active=1 GROUP BY category ORDER BY 2 DESC")) { while (r.next()) m.put(r.getString(1), r.getDouble(2)); }
        return m;
    }

    /** Items closest to / below their reorder level: label -> stock as % of reorder level. */
    public static LinkedHashMap<String, Double> lowStock(int limit) throws SQLException {
        needLogin(); LinkedHashMap<String, Double> m = new LinkedHashMap<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT name,stock_qty,reorder_level FROM products WHERE active=1 AND reorder_level>0 ORDER BY stock_qty/reorder_level ASC LIMIT ?")) {
            ps.setInt(1, limit); try (ResultSet r = ps.executeQuery()) { while (r.next()) m.put(r.getString(1) + " (" + r.getInt(2) + "/" + r.getInt(3) + ")", 100.0 * r.getInt(2) / r.getInt(3)); }
        }
        return m;
    }

    /** Active products with their recent sales statistics (7/30/90 days, last sale) - the inputs of the ML screens. */
    public static List<ProductSnapshot> snapshots() throws SQLException {
        needLogin(); List<ProductSnapshot> out = new ArrayList<>(); LocalDateTime now = LocalDateTime.now();
        String sql = "SELECT p.id,p.sku,p.name,p.category,p.unit_price,p.cost_price,p.stock_qty,p.reorder_level,p.shelf_life_days," +
            "COALESCE(SUM(CASE WHEN s.sale_date>=? THEN i.quantity END),0),COALESCE(SUM(CASE WHEN s.sale_date>=? THEN i.quantity END),0),COALESCE(SUM(CASE WHEN s.sale_date>=? THEN i.quantity END),0),MAX(s.sale_date) " +
            "FROM products p LEFT JOIN sale_items i ON i.product_id=p.id LEFT JOIN sales s ON s.id=i.sale_id AND s.sale_date>=? WHERE p.active=1 GROUP BY p.id,p.sku,p.name,p.category,p.unit_price,p.cost_price,p.stock_qty,p.reorder_level,p.shelf_life_days ORDER BY p.id";
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setTimestamp(1, ts(now.minusDays(7))); ps.setTimestamp(2, ts(now.minusDays(30))); ps.setTimestamp(3, ts(now.minusDays(90))); ps.setTimestamp(4, ts(now.minusDays(90)));
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) {
                    ProductSnapshot p = new ProductSnapshot(); p.id = r.getInt(1); p.sku = r.getString(2); p.name = r.getString(3); p.category = r.getString(4); p.price = r.getDouble(5); p.cost = r.getDouble(6); p.stock = r.getInt(7); p.reorder = r.getInt(8); p.shelfLife = r.getInt(9);
                    p.u7 = r.getDouble(10); p.u30 = r.getDouble(11); p.u90 = r.getDouble(12); Timestamp t = r.getTimestamp(13); p.lastSale = t == null ? null : t.toLocalDateTime(); out.add(p);
                }
            }
        }
        return out;
    }
}
