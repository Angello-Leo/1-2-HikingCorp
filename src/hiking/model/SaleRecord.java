package hiking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class SaleRecord {
    public int id, itemCount; public String receiptNo, cashier, payment; public LocalDateTime date; public double subtotal, discount, total, tendered;
    public final List<String[]> lines = new ArrayList<>();   // {name, qty, unit price, line total}
}
