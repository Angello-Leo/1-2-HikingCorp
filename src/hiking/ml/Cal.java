package hiking.ml;

import java.time.DayOfWeek;
import java.time.LocalDate;

/** Calendar helpers shared by the datasets, the forecasts and the input forms (Philippine holidays). */
public final class Cal {
    private Cal() {}
    private static final int[][] HOLIDAYS = {{1,1},{4,9},{5,1},{6,12},{8,21},{11,1},{11,30},{12,24},{12,25},{12,30},{12,31}};

    public static boolean isHoliday(LocalDate d) { for (int[] h : HOLIDAYS) if (h[0] == d.getMonthValue() && h[1] == d.getDayOfMonth()) return true; return false; }
    public static boolean isPayday(LocalDate d) { return d.getDayOfMonth() == 15 || d.getDayOfMonth() == 30 || (d.getMonthValue() == 2 && d.getDayOfMonth() == 28); }
    public static boolean isWeekend(LocalDate d) { return d.getDayOfWeek() == DayOfWeek.SATURDAY || d.getDayOfWeek() == DayOfWeek.SUNDAY; }
    /** Typical Philippine temperature by month (used as a default input). */
    public static double typicalTemp(int month) { return new double[]{26,27,29,31,32,29,28,28,28,28,27,26}[month - 1]; }

    /** Fills the calendar-derived features of the vector for the given date. */
    public static void fill(Task t, double[] x, LocalDate d) {
        set(t, x, "day_of_week", d.getDayOfWeek().getValue()); set(t, x, "month", d.getMonthValue());
        set(t, x, "is_weekend", isWeekend(d) ? 1 : 0); set(t, x, "is_holiday", isHoliday(d) ? 1 : 0); set(t, x, "is_payday", isPayday(d) ? 1 : 0);
    }
    public static void set(Task t, double[] x, String name, double v) { int i = t.indexOf(name); if (i >= 0) x[i] = v; }
}
