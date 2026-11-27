package hiking.dao;

import hiking.db.Database;
import hiking.model.*;
import hiking.security.Session;

import java.sql.*;
import java.time.*;
import java.util.*;

public final class SaleDao {
    private SaleDao() {}
    public static final String[] PAYMENTS = {"Cash", "GCash", "Card"};

    private static double r2(double v) { return Math.round(v * 100) / 100.0; }

    /** Completes a sale in ONE transaction. Prices/stock are re-read from the database (never trusted from the screen). */
    public static SaleRecord checkout(List<CartLine> cart, double discountPct, String payment, double tendered) throws SQLException {
        Session.require(Perm.SALES_CREATE);
        if (cart == null || cart.isEmpty()) throw new IllegalArgumentException("The order is empty.");
        if (discountPct < 0 || discountPct > 100) throw new IllegalArgumentException("Discount must be between 0 and 100%.");
        if (!Arrays.asList(PAYMENTS).contains(payment)) throw new IllegalArgumentException("Invalid payment method.");
        SaleRecord s = new SaleRecord(); LocalDateTime now = LocalDateTime.now();
        try (Connection c = Database.get()) {
            c.setAutoCommit(false);
            try {
                double sub = 0; List<double[]> priced = new ArrayList<>();   // {productId, qty, price, line}
                for (CartLine l : cart) {
                    if (l.qty < 1 || l.qty > 999) throw new IllegalArgumentException("Invalid quantity for " + l.product.name);
                    try (PreparedStatement ps = c.prepareStatement("SELECT name,unit_price,stock_qty FROM products WHERE id=? AND active=1 FOR UPDATE")) {
                        ps.setInt(1, l.product.id);
                        try (ResultSet r = ps.executeQuery()) {
                            if (!r.next()) throw new IllegalArgumentException(l.product.name + " is no longer available.");
                            if (r.getInt(3) < l.qty) throw new IllegalArgumentException("Not enough stock for " + r.getString(1) + " (only " + r.getInt(3) + " left).");
                            double price = r.getDouble(2), line = r2(price * l.qty); sub += line; priced.add(new double[]{l.product.id, l.qty, price, line}); s.lines.add(new String[]{r.getString(1), String.valueOf(l.qty), String.format("%.2f", price), String.format("%.2f", line)}); s.itemCount += l.qty;
                        }
                    }
                }
                sub = r2(sub); double disc = r2(sub * discountPct / 100), total = r2(sub - disc);
                if (tendered + 0.001 < total) throw new IllegalArgumentException("Amount tendered is less than the total.");
                int id;
                try (PreparedStatement ps = c.prepareStatement("INSERT INTO sales(receipt_no,cashier_id,sale_date,subtotal,discount,total,payment_method,amount_tendered) VALUES(?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, "TMP-" + UUID.randomUUID()); ps.setInt(2, Session.id()); ps.setTimestamp(3, Timestamp.valueOf(now)); ps.setDouble(4, sub); ps.setDouble(5, disc); ps.setDouble(6, total); ps.setString(7, payment); ps.setDouble(8, tendered);
                    ps.executeUpdate(); try (ResultSet k = ps.getGeneratedKeys()) { k.next(); id = k.getInt(1); }
                }
                String receipt = String.format("HK-%s-%06d", now.toLocalDate().toString().replace("-", ""), id);
                try (PreparedStatement ps = c.prepareStatement("UPDATE sales SET receipt_no=? WHERE id=?")) { ps.setString(1, receipt); ps.setInt(2, id); ps.executeUpdate(); }
                for (double[] l : priced) {
                    try (PreparedStatement ps = c.prepareStatement("INSERT INTO sale_items(sale_id,product_id,quantity,unit_price,line_total) VALUES(?,?,?,?,?)")) { ps.setInt(1, id); ps.setInt(2, (int) l[0]); ps.setInt(3, (int) l[1]); ps.setDouble(4, l[2]); ps.setDouble(5, l[3]); ps.executeUpdate(); }
                    try (PreparedStatement ps = c.prepareStatement("UPDATE products SET stock_qty = stock_qty - ? WHERE id=? AND stock_qty >= ?")) { ps.setInt(1, (int) l[1]); ps.setInt(2, (int) l[0]); ps.setInt(3, (int) l[1]); if (ps.executeUpdate() == 0) throw new IllegalArgumentException("Stock changed while processing. Please retry."); }
                    ProductDao.movement(c, (int) l[0], -(int) l[1], "SALE", receipt);
                }
                c.commit();
                s.id = id; s.receiptNo = receipt; s.date = now; s.subtotal = sub; s.discount = disc; s.total = total; s.payment = payment; s.tendered = tendered; s.cashier = Session.user().fullName;
            } catch (SQLException | RuntimeException e) { c.rollback(); throw e; }
        }
        AuditDao.log("SALE", s.receiptNo + " total " + String.format("%.2f", s.total));
        return s;
    }

    /** Sales in a date range. Sales personnel only ever see their own sales. */
    public static List<SaleRecord> history(LocalDate from, LocalDate to) throws SQLException {
        Session.require(Perm.SALES_VIEW);
        List<SaleRecord> out = new ArrayList<>();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement(
                "SELECT s.id,s.receipt_no,s.sale_date,u.full_name,s.subtotal,s.discount,s.total,s.payment_method,s.amount_tendered," +
                "(SELECT COALESCE(SUM(quantity),0) FROM sale_items i WHERE i.sale_id=s.id) FROM sales s JOIN users u ON u.id=s.cashier_id " +
                "WHERE s.sale_date>=? AND s.sale_date<? AND s.cashier_id=? ORDER BY s.sale_date DESC LIMIT 5000")) {
            ps.setTimestamp(1, Timestamp.valueOf(from.atStartOfDay())); ps.setTimestamp(2, Timestamp.valueOf(to.plusDays(1).atStartOfDay())); ps.setInt(3, Session.id());
            try (ResultSet r = ps.executeQuery()) {
                while (r.next()) { SaleRecord s = new SaleRecord(); s.id = r.getInt(1); s.receiptNo = r.getString(2); s.date = r.getTimestamp(3).toLocalDateTime(); s.cashier = r.getString(4); s.subtotal = r.getDouble(5); s.discount = r.getDouble(6); s.total = r.getDouble(7); s.payment = r.getString(8); s.tendered = r.getDouble(9); s.itemCount = r.getInt(10); out.add(s); }
            }
        }
        return out;
    }

    public static void loadLines(SaleRecord s) throws SQLException {
        Session.require(Perm.SALES_VIEW); s.lines.clear();
        try (Connection c = Database.get(); PreparedStatement ps = c.prepareStatement("SELECT p.name,i.quantity,i.unit_price,i.line_total FROM sale_items i JOIN products p ON p.id=i.product_id JOIN sales s ON s.id=i.sale_id WHERE i.sale_id=? AND s.cashier_id=?")) {
            ps.setInt(1, s.id); ps.setInt(2, Session.id());
            try (ResultSet r = ps.executeQuery()) { while (r.next()) s.lines.add(new String[]{r.getString(1), String.valueOf(r.getInt(2)), String.format("%.2f", r.getDouble(3)), String.format("%.2f", r.getDouble(4))}); }
        }
    }
}
