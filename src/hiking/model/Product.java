package hiking.model;

public final class Product {
    public int id; public String sku, name, category; public double unitPrice, costPrice; public int stockQty, reorderLevel, shelfLifeDays = 7; public boolean active = true;
    public boolean lowStock() { return stockQty <= reorderLevel; }
    @Override public String toString() { return name; }
}
