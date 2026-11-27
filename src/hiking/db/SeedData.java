package hiking.db;

import hiking.ml.Cal;
import hiking.security.PasswordUtil;

import java.io.*;
import java.sql.*;
import java.time.*;
import java.util.*;

/** Demo data created once on an empty database: users, the menu, and ~90 days of sales history. */
final class SeedData {
    private SeedData() {}
    private static final double[] DOW = {.88, .86, .90, .95, 1.15, 1.28, 1.18};
    private static final double[] MON = {.95, .97, 1.0, 1.04, 1.06, .96, .94, .95, .97, 1.02, 1.05, 1.15};
    private static final String[] PAY = {"Cash", "Cash", "Cash", "GCash", "GCash", "Card"};

    static void run(Connection c, Database.Progress prog) throws SQLException, IOException {
        c.setAutoCommit(false);
        try {
            if (count(c, "users") == 0) {
                prog.update("Creating default user accounts...", 0.12);
                String[][] u = {{"admin", "System Administrator", "ADMIN", "Admin@123"}, {"clerk", "Ines Clerk", "INVENTORY_CLERK", "Clerk@123"},
                                {"sales", "Sam Sales", "SALES_PERSONNEL", "Sales@123"}, {"instructor", "Course Instructor", "INSTRUCTOR", "Instructor@123"}};
                try (PreparedStatement ps = c.prepareStatement("INSERT INTO users(username,full_name,password_hash,salt,role) VALUES(?,?,?,?,?)")) {
                    for (String[] r : u) { String salt = PasswordUtil.newSalt(); ps.setString(1, r[0]); ps.setString(2, r[1]); ps.setString(3, PasswordUtil.hash(r[3], salt)); ps.setString(4, salt); ps.setString(5, r[2]); ps.addBatch(); }
                    ps.executeBatch();
                }
                c.commit();
            }
            if (count(c, "products") == 0 && count(c, "sales") == 0) seedMenuAndSales(c, prog);
            c.commit();
        } catch (SQLException | IOException | RuntimeException e) { c.rollback(); throw e; }
        finally { c.setAutoCommit(true); }
    }

    private static int count(Connection c, String t) throws SQLException {
        try (Statement s = c.createStatement(); ResultSet r = s.executeQuery("SELECT COUNT(*) FROM " + t)) { r.next(); return r.getInt(1); }
    }

    private static void seedMenuAndSales(Connection c, Database.Progress prog) throws SQLException, IOException {
        prog.update("Loading the menu / inventory...", 0.2);
        List<String[]> menu = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(SeedData.class.getResourceAsStream("seed_products.csv")))) {
            br.readLine(); String l; while ((l = br.readLine()) != null) if (!l.trim().isEmpty()) menu.add(l.split(","));
        }
        int n = menu.size(); int clerk = userId(c, "clerk"), cashier = userId(c, "sales");
        LocalDate startDay = LocalDate.now().minusDays(90); LocalDateTime t0 = startDay.atTime(6, 0);
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO products(id,sku,name,category,unit_price,cost_price,stock_qty,reorder_level,shelf_life_days,created_at) VALUES(?,?,?,?,?,?,?,?,?,?)");
             PreparedStatement mv = c.prepareStatement("INSERT INTO stock_movements(product_id,qty_change,reason,note,user_id,created_at) VALUES(?,?,?,?,?,?)")) {
            for (String[] m : menu) {
                int id = Integer.parseInt(m[0]);
                ps.setInt(1, id); ps.setString(2, m[1]); ps.setString(3, m[2]); ps.setString(4, m[3]); ps.setDouble(5, Double.parseDouble(m[4])); ps.setDouble(6, Double.parseDouble(m[5]));
                ps.setInt(7, Integer.parseInt(m[8])); ps.setInt(8, Integer.parseInt(m[9])); ps.setInt(9, Integer.parseInt(m[7])); ps.setTimestamp(10, Timestamp.valueOf(t0)); ps.addBatch();
                mv.setInt(1, id); mv.setInt(2, Integer.parseInt(m[8])); mv.setString(3, "INITIAL"); mv.setString(4, "Opening stock"); mv.setInt(5, clerk); mv.setTimestamp(6, Timestamp.valueOf(t0)); mv.addBatch();
            }
            ps.executeBatch(); mv.executeBatch();
        }
        int[] stock = new int[n]; double[] pop = new double[n], price = new double[n]; int[] reorder = new int[n], restock = new int[n]; double popSum = 0;
        for (int i = 0; i < n; i++) { String[] m = menu.get(i); stock[i] = Integer.parseInt(m[8]); pop[i] = Double.parseDouble(m[6]); price[i] = Double.parseDouble(m[4]); reorder[i] = Integer.parseInt(m[9]); restock[i] = Integer.parseInt(m[8]); popSum += pop[i]; }

        Random rnd = new Random(77); int saleId = 0;
        LocalDateTime now = LocalDateTime.now();
        try (PreparedStatement sp = c.prepareStatement("INSERT INTO sales(id,receipt_no,cashier_id,sale_date,subtotal,discount,total,payment_method,amount_tendered) VALUES(?,?,?,?,?,?,?,?,?)");
             PreparedStatement ip = c.prepareStatement("INSERT INTO sale_items(sale_id,product_id,quantity,unit_price,line_total) VALUES(?,?,?,?,?)");
             PreparedStatement mv = c.prepareStatement("INSERT INTO stock_movements(product_id,qty_change,reason,note,user_id,created_at) VALUES(?,?,?,?,?,?)")) {
            for (int d = 0; d <= 90; d++) {
                LocalDate day = startDay.plusDays(d);
                prog.update("Generating sales history (" + day + ")...", 0.25 + 0.7 * d / 90.0);
                double f = DOW[day.getDayOfWeek().getValue() - 1] * MON[day.getMonthValue() - 1] * (1 + .14 * (Cal.isHoliday(day) ? 1 : 0)) * (1 + .10 * (Cal.isPayday(day) ? 1 : 0))
                         * (1 + .2 * (rnd.nextDouble() < .18 ? 1 : 0)) * (1 + 0.00018 * Duration.between(LocalDate.of(2023, 7, 1).atStartOfDay(), day.atStartOfDay()).toDays()) * Math.exp(rnd.nextGaussian() * .06);
                int trans = (int) Math.round(popSum * f / 2.05);
                for (int t = 0; t < trans; t++) {
                    int hour = pickHour(rnd); LocalDateTime when = day.atTime(hour, rnd.nextInt(60), rnd.nextInt(60));
                    if (when.isAfter(now)) continue;
                    int k = pickCount(rnd); Set<Integer> used = new HashSet<>(); List<int[]> lines = new ArrayList<>(); double sub = 0;
                    for (int j = 0; j < k; j++) {
                        int p = pickProduct(rnd, pop, popSum); if (!used.add(p)) continue;
                        int q = rnd.nextDouble() < .78 ? 1 : 2;
                        if (stock[p] < Math.max(q, reorder[p] / 3)) {   // restock before the shelf is empty
                            stock[p] += restock[p];
                            mv.setInt(1, p + 1); mv.setInt(2, restock[p]); mv.setString(3, "STOCK_IN"); mv.setString(4, "Supplier delivery"); mv.setInt(5, clerk); mv.setTimestamp(6, Timestamp.valueOf(day.atTime(6, 30))); mv.addBatch();
                        }
                        stock[p] -= q; lines.add(new int[]{p, q}); sub += price[p] * q;
                    }
                    double disc = rnd.nextDouble() < .05 ? Math.round(sub * .20 * 100) / 100.0 : 0; double total = Math.round((sub - disc) * 100) / 100.0;
                    String pay = PAY[rnd.nextInt(PAY.length)]; saleId++;
                    sp.setInt(1, saleId); sp.setString(2, String.format("HK-%s-%06d", day.toString().replace("-", ""), saleId)); sp.setInt(3, cashier); sp.setTimestamp(4, Timestamp.valueOf(when));
                    sp.setDouble(5, sub); sp.setDouble(6, disc); sp.setDouble(7, total); sp.setString(8, pay); sp.setDouble(9, pay.equals("Cash") ? Math.ceil(total / 50) * 50 : total); sp.addBatch();
                    for (int[] l : lines) { ip.setInt(1, saleId); ip.setInt(2, l[0] + 1); ip.setInt(3, l[1]); ip.setDouble(4, price[l[0]]); ip.setDouble(5, price[l[0]] * l[1]); ip.addBatch(); }
                }
                if (d % 10 == 9) { sp.executeBatch(); ip.executeBatch(); mv.executeBatch(); }
            }
            sp.executeBatch(); ip.executeBatch(); mv.executeBatch();
        }
        try (PreparedStatement up = c.prepareStatement("UPDATE products SET stock_qty=? WHERE id=?")) {
            for (int i = 0; i < n; i++) { up.setInt(1, stock[i]); up.setInt(2, i + 1); up.addBatch(); }
            up.executeBatch();
        }
        prog.update("Done", 1.0);
    }

    private static int userId(Connection c, String name) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement("SELECT id FROM users WHERE username=?")) { ps.setString(1, name); try (ResultSet r = ps.executeQuery()) { r.next(); return r.getInt(1); } }
    }
    private static int pickHour(Random r) {
        double u = r.nextDouble();
        if (u < .08) return 8 + r.nextInt(2); if (u < .40) return 11 + r.nextInt(3); if (u < .55) return 14 + r.nextInt(3); if (u < .90) return 17 + r.nextInt(4); return 21 + r.nextInt(2);
    }
    private static int pickCount(Random r) { double u = r.nextDouble(); return u < .35 ? 1 : u < .70 ? 2 : u < .90 ? 3 : 4; }
    private static int pickProduct(Random r, double[] pop, double sum) { double u = r.nextDouble() * sum; for (int i = 0; i < pop.length; i++) { u -= pop[i]; if (u <= 0) return i; } return pop.length - 1; }
}
