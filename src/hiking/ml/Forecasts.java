package hiking.ml;

import hiking.dao.StatsDao;
import hiking.model.ProductSnapshot;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/** Turns live database data into model inputs and runs the four prediction tasks in batch. */
public final class Forecasts {
    private Forecasts() {}
    private static AnalysisService svc() { return AnalysisService.get(); }
    private static String type(String t) { return t == null ? svc().bestType(Task.DEMAND) : t; }

    // ------------------------------------------------------------------ feature vectors
    public static double[] demandVector(ProductSnapshot p, LocalDate d, boolean promo, double temp) {
        Task t = Task.DEMAND; double[] x = new double[t.fields.length];
        Cal.set(t, x, "product_code", p.code()); Cal.set(t, x, "category_code", p.categoryCode()); Cal.set(t, x, "unit_price", p.price);
        Cal.fill(t, x, d); Cal.set(t, x, "is_promo", promo ? 1 : 0); Cal.set(t, x, "temperature_c", temp); Cal.set(t, x, "prev_week_units", p.avg7());
        return x;
    }
    public static double[] slowVector(ProductSnapshot p, boolean promo) {
        Task t = Task.SLOW; double[] x = new double[t.fields.length];
        Cal.set(t, x, "category_code", p.categoryCode()); Cal.set(t, x, "unit_price", p.price); Cal.set(t, x, "stock_on_hand", p.stock); Cal.set(t, x, "days_since_last_sale", p.daysSinceLastSale());
        Cal.set(t, x, "avg_daily_units_30d", p.avg30()); Cal.set(t, x, "shelf_life_days", p.shelfLife); Cal.set(t, x, "is_promo", promo ? 1 : 0); Cal.set(t, x, "margin_pct", p.margin());
        return x;
    }
    public static double[] bestVector(ProductSnapshot p, int month, int promoDays) {
        Task t = Task.BEST; double[] x = new double[t.fields.length];
        Cal.set(t, x, "category_code", p.categoryCode()); Cal.set(t, x, "unit_price", p.price); Cal.set(t, x, "margin_pct", p.margin()); Cal.set(t, x, "target_month", month);
        Cal.set(t, x, "promo_days", promoDays); Cal.set(t, x, "units_prev_month", p.u30); Cal.set(t, x, "units_prev_3m_avg", p.u90 / 3);
        return x;
    }

    // ------------------------------------------------------------------ batch results
    public static final class DemandRow { public ProductSnapshot p; public double predicted; public String status; }
    public static final class SlowRow { public ProductSnapshot p; public double velocity, daysCover; public String label; }
    public static final class BestRow { public ProductSnapshot p; public double units; public int rank; public String label; }
    public static final class DayRow { public LocalDate date; public double value; public String note; }

    /** Expected units per menu item on one day, with a re-order hint (stock below ~2 days of demand). */
    public static List<DemandRow> demand(String type, LocalDate d, boolean promo, double temp) throws SQLException {
        List<DemandRow> out = new ArrayList<>();
        for (ProductSnapshot p : StatsDao.snapshots()) {
            DemandRow r = new DemandRow(); r.p = p; r.predicted = svc().predict(Task.DEMAND, type, demandVector(p, d, promo, temp)).value;
            r.status = p.stock < Math.ceil(r.predicted * 2) || p.stock <= p.reorder ? "REORDER" : "OK"; out.add(r);
        }
        out.sort((a, b) -> Double.compare(b.predicted, a.predicted));
        return out;
    }

    public static List<SlowRow> slow(String type, boolean promo) throws SQLException {
        List<SlowRow> out = new ArrayList<>(); ModelBundle b = svc().bundle(Task.SLOW, type);
        for (ProductSnapshot p : StatsDao.snapshots()) {
            SlowRow r = new SlowRow(); r.p = p; double v = svc().predict(Task.SLOW, type, slowVector(p, promo)).value; r.velocity = v;
            r.daysCover = v < 0.05 ? 999 : p.stock / v; r.label = AnalysisService.label(Task.SLOW, v, b); out.add(r);
        }
        out.sort(Comparator.comparingDouble(r -> r.velocity));
        return out;
    }

    public static List<BestRow> best(String type, int month, int promoDays) throws SQLException {
        List<BestRow> out = new ArrayList<>(); ModelBundle b = svc().bundle(Task.BEST, type);
        for (ProductSnapshot p : StatsDao.snapshots()) {
            BestRow r = new BestRow(); r.p = p; r.units = svc().predict(Task.BEST, type, bestVector(p, month, promoDays)).value; r.label = AnalysisService.label(Task.BEST, r.units, b); out.add(r);
        }
        out.sort((a, c) -> Double.compare(c.units, a.units));
        for (int i = 0; i < out.size(); i++) out.get(i).rank = i + 1;
        return out;
    }

    /** Day-by-day sales forecast. Yesterday's real sales seed the first day; later days chain on the model's own forecast. */
    public static List<DayRow> sales(String type, LocalDate start, int days, boolean promo, double rain, Double tempOverride) throws SQLException {
        Task t = Task.SALES; List<DayRow> out = new ArrayList<>();
        LocalDate begin = start.isBefore(LocalDate.now()) ? start : LocalDate.now();
        double prev = StatsDao.salesOn(begin.minusDays(1));
        if (prev <= 0) prev = Task.SALES.fields[8].def;
        for (LocalDate d = begin; !d.isAfter(start.plusDays(days - 1)); d = d.plusDays(1)) {
            double[] x = new double[t.fields.length]; Cal.fill(t, x, d);
            Cal.set(t, x, "is_promo", promo ? 1 : 0); Cal.set(t, x, "temperature_c", tempOverride != null ? tempOverride : Cal.typicalTemp(d.getMonthValue())); Cal.set(t, x, "rain_mm", rain); Cal.set(t, x, "prev_day_sales", prev);
            double v = svc().predict(t, type, x).value; prev = v;
            if (!d.isBefore(start)) {
                DayRow r = new DayRow(); r.date = d; r.value = v; StringBuilder n = new StringBuilder();
                if (Cal.isHoliday(d)) n.append("Holiday  "); if (Cal.isPayday(d)) n.append("Payday  "); if (Cal.isWeekend(d)) n.append("Weekend"); r.note = n.toString().trim(); out.add(r);
            }
        }
        return out;
    }
}
