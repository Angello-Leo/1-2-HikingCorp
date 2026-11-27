package hiking.model;

public final class CartLine {
    public final Product product; public int qty;
    public CartLine(Product p, int q) { product = p; qty = q; }
    public double total() { return Math.round(product.unitPrice * qty * 100) / 100.0; }
}
