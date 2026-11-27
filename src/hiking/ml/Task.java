package hiking.ml;

import java.util.*;

/** The four knowledge-analysis tasks, their dataset, target and input form definition. */
public enum Task {
    DEMAND("demand", "Product Demand Prediction", "product_demand_dataset.csv", "units_sold", "units / day", Mode.NONE, 0,
        "Predicts how many units of one menu item will be sold on a given day, from the item, the calendar, the weather and the recent sales level.",
        new F[]{ F.prod("product_code", "Product"), F.cat("category_code", "Category"), F.num("unit_price", "Unit price (PHP)", 1, 2000, 89),
                 F.der("day_of_week"), F.der("month"), F.der("is_weekend"), F.der("is_holiday"),
                 F.bool("is_promo", "Promo day"), F.num("temperature_c", "Temperature (°C)", 15, 42, 29), F.num("prev_week_units", "Avg. units/day last 7 days", 0, 500, 6) }),
    SALES("sales", "Sales Prediction", "sales_dataset.csv", "total_sales", "PHP / day", Mode.NONE, 0,
        "Predicts the total sales (PHP) of a whole day from the calendar (weekday, month, holiday, payday), promo, weather and yesterday's sales.",
        new F[]{ F.der("day_of_week"), F.der("month"), F.der("is_weekend"), F.der("is_holiday"), F.der("is_payday"),
                 F.bool("is_promo", "Promo day"), F.num("temperature_c", "Temperature (°C)", 15, 42, 29), F.num("rain_mm", "Rainfall (mm)", 0, 300, 0),
                 F.num("prev_day_sales", "Yesterday's sales (PHP)", 0, 500000, 15000) }),
    SLOW("slow", "Slow-Moving Product Prediction", "slow_moving_dataset.csv", "velocity_next_14d", "units / day", Mode.SLOW, 0.30,
        "Predicts the selling speed (units/day) of a menu item over the next 14 days. Items predicted in the slowest 30% of the training range are classified as SLOW-MOVING.",
        new F[]{ F.cat("category_code", "Category"), F.num("unit_price", "Unit price (PHP)", 1, 2000, 89), F.num("stock_on_hand", "Stock on hand", 0, 5000, 100),
                 F.num("days_since_last_sale", "Days since last sale", 0, 90, 0), F.num("avg_daily_units_30d", "Avg. units/day (last 30 d)", 0, 500, 5),
                 F.num("shelf_life_days", "Shelf life (days)", 1, 365, 5), F.bool("is_promo", "On promo"), F.num("margin_pct", "Margin (%)", 0, 95, 55) }),
    BEST("best", "Best-Selling Product Prediction", "best_selling_dataset.csv", "units_next_month", "units / month", Mode.BEST, 0.75,
        "Predicts how many units an item will sell next month. Items predicted in the top 25% of the training range are classified as BEST-SELLERS.",
        new F[]{ F.cat("category_code", "Category"), F.num("unit_price", "Unit price (PHP)", 1, 2000, 89), F.num("margin_pct", "Margin (%)", 0, 95, 55),
                 F.mon("target_month", "Month to predict"), F.num("promo_days", "Planned promo days", 0, 31, 0),
                 F.num("units_prev_month", "Units sold last 30 days", 0, 20000, 150), F.num("units_prev_3m_avg", "Avg. units / 30 d (last 90 d)", 0, 20000, 150) });

    public enum Mode { NONE, SLOW, BEST }
    public enum Kind { NUMBER, BOOL, CATEGORY, MONTH, PRODUCT, DERIVED }

    /** Input field definition. */
    public static final class F {
        public final String name, label; public final Kind kind; public final double min, max, def;
        F(String n, String l, Kind k, double mn, double mx, double d) { name = n; label = l; kind = k; min = mn; max = mx; def = d; }
        static F num(String n, String l, double mn, double mx, double d) { return new F(n, l, Kind.NUMBER, mn, mx, d); }
        static F bool(String n, String l) { return new F(n, l, Kind.BOOL, 0, 1, 0); }
        static F cat(String n, String l) { return new F(n, l, Kind.CATEGORY, 1, 6, 1); }
        static F mon(String n, String l) { return new F(n, l, Kind.MONTH, 1, 12, 1); }
        static F prod(String n, String l) { return new F(n, l, Kind.PRODUCT, 1, 9999, 1); }
        static F der(String n) { return new F(n, n, Kind.DERIVED, 0, 0, 0); }
    }

    public final String key, title, csv, target, unit, about; public final Mode mode; public final double quantile; public final F[] fields;
    Task(String key, String title, String csv, String target, String unit, Mode mode, double q, String about, F[] fields) {
        this.key = key; this.title = title; this.csv = csv; this.target = target; this.unit = unit; this.mode = mode; this.quantile = q; this.about = about; this.fields = fields;
    }
    public String[] featureNames() { String[] n = new String[fields.length]; for (int i = 0; i < n.length; i++) n[i] = fields[i].name; return n; }
    public boolean usesDate() { for (F f : fields) if (f.kind == Kind.DERIVED) return true; return false; }
    public int indexOf(String name) { for (int i = 0; i < fields.length; i++) if (fields[i].name.equals(name)) return i; return -1; }

    public static final String[] CATEGORIES = {"Burgers", "Chicken", "Rice Meals", "Sides", "Beverages", "Desserts"};
    public static final String[] MONTHS = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    /** Plain-language column glossary for the dataset tab. */
    public static final Map<String, String> COLUMN_INFO = new LinkedHashMap<>();
    static {
        String[][] c = {
            {"date", "Calendar date of the record."}, {"snapshot_date", "Date the snapshot of the item was taken."}, {"product_name", "Menu item name (for reading only, not used by the models)."},
            {"product_code", "Numeric id of the menu item (P001 = 1 ...)."}, {"category_code", "1 Burgers, 2 Chicken, 3 Rice Meals, 4 Sides, 5 Beverages, 6 Desserts."},
            {"unit_price", "Selling price in PHP at that time."}, {"day_of_week", "1 = Monday ... 7 = Sunday."}, {"month", "Month number 1-12."},
            {"is_weekend", "1 if Saturday or Sunday."}, {"is_holiday", "1 if a public holiday."}, {"is_payday", "1 on the 15th / 30th (pay days)."}, {"is_promo", "1 if a promotion was running."},
            {"temperature_c", "Average air temperature in °C."}, {"rain_mm", "Rainfall in millimetres."}, {"prev_week_units", "Average units/day of the item in the previous 7 days."},
            {"prev_day_sales", "Total sales (PHP) of the previous day."}, {"units_sold", "TARGET - units sold that day."}, {"total_sales", "TARGET - total sales of the day (PHP)."},
            {"stock_on_hand", "Units in stock when the snapshot was taken."}, {"days_since_last_sale", "Days since the item last sold."}, {"avg_daily_units_30d", "Average units/day over the previous 30 days."},
            {"shelf_life_days", "Days before the item expires / must be discarded."}, {"margin_pct", "(price - cost) / price x 100."}, {"velocity_next_14d", "TARGET - units/day actually sold in the next 14 days."},
            {"target_month", "Month being predicted."}, {"promo_days", "Number of promo days planned in that month."}, {"units_prev_month", "Units sold in the previous 30 days."},
            {"units_prev_3m_avg", "Average units per 30 days over the previous 90 days."}, {"units_next_month", "TARGET - units sold in the next month."}};
        for (String[] r : c) COLUMN_INFO.put(r[0], r[1]);
    }
}
