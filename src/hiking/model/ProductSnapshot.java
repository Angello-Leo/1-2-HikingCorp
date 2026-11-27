package hiking.model;

import hiking.ml.Task;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/** A product with the recent-sales statistics used as machine-learning inputs. */
public final class ProductSnapshot {
    public int id; public String sku, name, category; public double price, cost; public int stock, reorder, shelfLife; public double u7, u30, u90; public LocalDateTime lastSale;
    public int code() { try { return Integer.parseInt(sku.replaceAll("\\D", "")); } catch (Exception e) { return id; } }
    public int categoryCode() { for (int i = 0; i < Task.CATEGORIES.length; i++) if (Task.CATEGORIES[i].equalsIgnoreCase(category)) return i + 1; return 1; }
    public double margin() { return price <= 0 ? 0 : Math.round((price - cost) / price * 1000) / 10.0; }
    public double avg7() { return Math.round(u7 / 7 * 100) / 100.0; }
    public double avg30() { return Math.round(u30 / 30 * 100) / 100.0; }
    public int daysSinceLastSale() { return lastSale == null ? 90 : (int) Math.min(90, ChronoUnit.DAYS.between(lastSale, LocalDateTime.now())); }
    @Override public String toString() { return name; }
}
