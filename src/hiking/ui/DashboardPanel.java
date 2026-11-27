package hiking.ui;

import hiking.dao.StatsDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Role-aware dashboard: KPI cards + charts. Edit card/labels/colours in the Design tab.
 */
public class DashboardPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public DashboardPanel(hiking.model.User user, java.util.function.Consumer<String> go) {
        initComponents();
        this.user = user; this.go = go; postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlNorth = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlKpiRow = new javax.swing.JPanel();
        pnlKpi1 = new javax.swing.JPanel();
        lblKpiTitle1 = new javax.swing.JLabel();
        lblKpiValue1 = new javax.swing.JLabel();
        lblKpiSub1 = new javax.swing.JLabel();
        pnlKpi2 = new javax.swing.JPanel();
        lblKpiTitle2 = new javax.swing.JLabel();
        lblKpiValue2 = new javax.swing.JLabel();
        lblKpiSub2 = new javax.swing.JLabel();
        pnlKpi3 = new javax.swing.JPanel();
        lblKpiTitle3 = new javax.swing.JLabel();
        lblKpiValue3 = new javax.swing.JLabel();
        lblKpiSub3 = new javax.swing.JLabel();
        pnlKpi4 = new javax.swing.JPanel();
        lblKpiTitle4 = new javax.swing.JLabel();
        lblKpiValue4 = new javax.swing.JLabel();
        lblKpiSub4 = new javax.swing.JLabel();
        pnlKpi5 = new javax.swing.JPanel();
        lblKpiTitle5 = new javax.swing.JLabel();
        lblKpiValue5 = new javax.swing.JLabel();
        lblKpiSub5 = new javax.swing.JLabel();
        pnlKpi6 = new javax.swing.JPanel();
        lblKpiTitle6 = new javax.swing.JLabel();
        lblKpiValue6 = new javax.swing.JLabel();
        lblKpiSub6 = new javax.swing.JLabel();
        pnlSpacer1 = new javax.swing.JPanel();
        pnlBody = new javax.swing.JPanel();
        pnlRow1 = new javax.swing.JPanel();
        pnlCardA = new javax.swing.JPanel();
        pnlChartHeadA = new javax.swing.JPanel();
        lblChartTitleA = new javax.swing.JLabel();
        lblChartSubA = new javax.swing.JLabel();
        pnlChartA = new javax.swing.JPanel();
        pnlCardB = new javax.swing.JPanel();
        pnlChartHeadB = new javax.swing.JPanel();
        lblChartTitleB = new javax.swing.JLabel();
        lblChartSubB = new javax.swing.JLabel();
        pnlChartB = new javax.swing.JPanel();
        pnlRow2Wrap = new javax.swing.JPanel();
        pnlSpacer2 = new javax.swing.JPanel();
        pnlRow2 = new javax.swing.JPanel();
        pnlCardC = new javax.swing.JPanel();
        pnlChartHeadC = new javax.swing.JPanel();
        lblChartTitleC = new javax.swing.JLabel();
        lblChartSubC = new javax.swing.JLabel();
        pnlChartC = new javax.swing.JPanel();
        pnlCardD = new javax.swing.JPanel();
        pnlChartHeadD = new javax.swing.JPanel();
        lblChartTitleD = new javax.swing.JLabel();
        lblChartSubD = new javax.swing.JLabel();
        pnlChartD = new javax.swing.JPanel();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlNorth.setOpaque(false);
        pnlNorth.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(37, 99, 235));

        pnlKpiRow.setOpaque(false);
        pnlKpiRow.setLayout(new java.awt.GridLayout(1, 6, 16, 0));

        pnlKpi1.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi1.setLayout(new java.awt.BorderLayout());

        lblKpiTitle1.setText("KPI 1");
        lblKpiTitle1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle1.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue1.setText("-");
        lblKpiValue1.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue1.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub1.setText(" ");
        lblKpiSub1.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub1.setForeground(new java.awt.Color(63, 75, 95));

        pnlKpi2.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi2.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi2.setLayout(new java.awt.BorderLayout());

        lblKpiTitle2.setText("KPI 2");
        lblKpiTitle2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle2.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue2.setText("-");
        lblKpiValue2.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue2.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub2.setText(" ");
        lblKpiSub2.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub2.setForeground(new java.awt.Color(63, 75, 95));

        pnlKpi3.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi3.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi3.setLayout(new java.awt.BorderLayout());

        lblKpiTitle3.setText("KPI 3");
        lblKpiTitle3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle3.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue3.setText("-");
        lblKpiValue3.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue3.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub3.setText(" ");
        lblKpiSub3.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub3.setForeground(new java.awt.Color(63, 75, 95));

        pnlKpi4.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi4.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi4.setLayout(new java.awt.BorderLayout());

        lblKpiTitle4.setText("KPI 4");
        lblKpiTitle4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle4.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue4.setText("-");
        lblKpiValue4.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue4.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub4.setText(" ");
        lblKpiSub4.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub4.setForeground(new java.awt.Color(63, 75, 95));

        pnlKpi5.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi5.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi5.setLayout(new java.awt.BorderLayout());

        lblKpiTitle5.setText("KPI 5");
        lblKpiTitle5.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle5.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue5.setText("-");
        lblKpiValue5.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue5.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub5.setText(" ");
        lblKpiSub5.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub5.setForeground(new java.awt.Color(63, 75, 95));

        pnlKpi6.setBackground(new java.awt.Color(255, 255, 255));
        pnlKpi6.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlKpi6.setLayout(new java.awt.BorderLayout());

        lblKpiTitle6.setText("KPI 6");
        lblKpiTitle6.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblKpiTitle6.setForeground(new java.awt.Color(63, 75, 95));

        lblKpiValue6.setText("-");
        lblKpiValue6.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblKpiValue6.setForeground(new java.awt.Color(31, 41, 55));

        lblKpiSub6.setText(" ");
        lblKpiSub6.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblKpiSub6.setForeground(new java.awt.Color(63, 75, 95));

        pnlSpacer1.setOpaque(false);
        pnlSpacer1.setPreferredSize(new java.awt.Dimension(10, 16));
        pnlSpacer1.setLayout(new java.awt.BorderLayout());

        pnlBody.setOpaque(false);
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlRow1.setOpaque(false);
        pnlRow1.setLayout(new java.awt.GridLayout(1, 2, 16, 0));

        pnlCardA.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardA.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        pnlCardA.setLayout(new java.awt.BorderLayout());

        pnlChartHeadA.setOpaque(false);
        pnlChartHeadA.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlChartHeadA.setLayout(new java.awt.BorderLayout());

        lblChartTitleA.setText("Chart A");
        lblChartTitleA.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblChartTitleA.setForeground(new java.awt.Color(31, 41, 55));

        lblChartSubA.setText(" ");
        lblChartSubA.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblChartSubA.setForeground(new java.awt.Color(63, 75, 95));

        pnlChartA.setOpaque(false);
        pnlChartA.setLayout(new java.awt.BorderLayout());

        pnlCardB.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardB.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        pnlCardB.setLayout(new java.awt.BorderLayout());

        pnlChartHeadB.setOpaque(false);
        pnlChartHeadB.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlChartHeadB.setLayout(new java.awt.BorderLayout());

        lblChartTitleB.setText("Chart B");
        lblChartTitleB.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblChartTitleB.setForeground(new java.awt.Color(31, 41, 55));

        lblChartSubB.setText(" ");
        lblChartSubB.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblChartSubB.setForeground(new java.awt.Color(63, 75, 95));

        pnlChartB.setOpaque(false);
        pnlChartB.setLayout(new java.awt.BorderLayout());

        pnlRow2Wrap.setOpaque(false);
        pnlRow2Wrap.setPreferredSize(new java.awt.Dimension(10, 316));
        pnlRow2Wrap.setLayout(new java.awt.BorderLayout());

        pnlSpacer2.setOpaque(false);
        pnlSpacer2.setPreferredSize(new java.awt.Dimension(10, 16));
        pnlSpacer2.setLayout(new java.awt.BorderLayout());

        pnlRow2.setOpaque(false);
        pnlRow2.setPreferredSize(new java.awt.Dimension(10, 300));
        pnlRow2.setLayout(new java.awt.GridLayout(1, 2, 16, 0));

        pnlCardC.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardC.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        pnlCardC.setLayout(new java.awt.BorderLayout());

        pnlChartHeadC.setOpaque(false);
        pnlChartHeadC.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlChartHeadC.setLayout(new java.awt.BorderLayout());

        lblChartTitleC.setText("Chart C");
        lblChartTitleC.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblChartTitleC.setForeground(new java.awt.Color(31, 41, 55));

        lblChartSubC.setText(" ");
        lblChartSubC.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblChartSubC.setForeground(new java.awt.Color(63, 75, 95));

        pnlChartC.setOpaque(false);
        pnlChartC.setLayout(new java.awt.BorderLayout());

        pnlCardD.setBackground(new java.awt.Color(255, 255, 255));
        pnlCardD.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        pnlCardD.setLayout(new java.awt.BorderLayout());

        pnlChartHeadD.setOpaque(false);
        pnlChartHeadD.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlChartHeadD.setLayout(new java.awt.BorderLayout());

        lblChartTitleD.setText("Chart D");
        lblChartTitleD.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblChartTitleD.setForeground(new java.awt.Color(31, 41, 55));

        lblChartSubD.setText(" ");
        lblChartSubD.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblChartSubD.setForeground(new java.awt.Color(63, 75, 95));

        pnlChartD.setOpaque(false);
        pnlChartD.setLayout(new java.awt.BorderLayout());

        pnlKpi1.add(lblKpiTitle1, java.awt.BorderLayout.NORTH);
        pnlKpi1.add(lblKpiValue1, java.awt.BorderLayout.CENTER);
        pnlKpi1.add(lblKpiSub1, java.awt.BorderLayout.SOUTH);
        pnlKpi2.add(lblKpiTitle2, java.awt.BorderLayout.NORTH);
        pnlKpi2.add(lblKpiValue2, java.awt.BorderLayout.CENTER);
        pnlKpi2.add(lblKpiSub2, java.awt.BorderLayout.SOUTH);
        pnlKpi3.add(lblKpiTitle3, java.awt.BorderLayout.NORTH);
        pnlKpi3.add(lblKpiValue3, java.awt.BorderLayout.CENTER);
        pnlKpi3.add(lblKpiSub3, java.awt.BorderLayout.SOUTH);
        pnlKpi4.add(lblKpiTitle4, java.awt.BorderLayout.NORTH);
        pnlKpi4.add(lblKpiValue4, java.awt.BorderLayout.CENTER);
        pnlKpi4.add(lblKpiSub4, java.awt.BorderLayout.SOUTH);
        pnlKpi5.add(lblKpiTitle5, java.awt.BorderLayout.NORTH);
        pnlKpi5.add(lblKpiValue5, java.awt.BorderLayout.CENTER);
        pnlKpi5.add(lblKpiSub5, java.awt.BorderLayout.SOUTH);
        pnlKpi6.add(lblKpiTitle6, java.awt.BorderLayout.NORTH);
        pnlKpi6.add(lblKpiValue6, java.awt.BorderLayout.CENTER);
        pnlKpi6.add(lblKpiSub6, java.awt.BorderLayout.SOUTH);
        pnlKpiRow.add(pnlKpi1);
        pnlKpiRow.add(pnlKpi2);
        pnlKpiRow.add(pnlKpi3);
        pnlKpiRow.add(pnlKpi4);
        pnlKpiRow.add(pnlKpi5);
        pnlKpiRow.add(pnlKpi6);
        pnlNorth.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlNorth.add(pnlKpiRow, java.awt.BorderLayout.CENTER);
        pnlNorth.add(pnlSpacer1, java.awt.BorderLayout.SOUTH);
        pnlChartHeadA.add(lblChartTitleA, java.awt.BorderLayout.NORTH);
        pnlChartHeadA.add(lblChartSubA, java.awt.BorderLayout.SOUTH);
        pnlCardA.add(pnlChartHeadA, java.awt.BorderLayout.NORTH);
        pnlCardA.add(pnlChartA, java.awt.BorderLayout.CENTER);
        pnlChartHeadB.add(lblChartTitleB, java.awt.BorderLayout.NORTH);
        pnlChartHeadB.add(lblChartSubB, java.awt.BorderLayout.SOUTH);
        pnlCardB.add(pnlChartHeadB, java.awt.BorderLayout.NORTH);
        pnlCardB.add(pnlChartB, java.awt.BorderLayout.CENTER);
        pnlRow1.add(pnlCardA);
        pnlRow1.add(pnlCardB);
        pnlChartHeadC.add(lblChartTitleC, java.awt.BorderLayout.NORTH);
        pnlChartHeadC.add(lblChartSubC, java.awt.BorderLayout.SOUTH);
        pnlCardC.add(pnlChartHeadC, java.awt.BorderLayout.NORTH);
        pnlCardC.add(pnlChartC, java.awt.BorderLayout.CENTER);
        pnlChartHeadD.add(lblChartTitleD, java.awt.BorderLayout.NORTH);
        pnlChartHeadD.add(lblChartSubD, java.awt.BorderLayout.SOUTH);
        pnlCardD.add(pnlChartHeadD, java.awt.BorderLayout.NORTH);
        pnlCardD.add(pnlChartD, java.awt.BorderLayout.CENTER);
        pnlRow2.add(pnlCardC);
        pnlRow2.add(pnlCardD);
        pnlRow2Wrap.add(pnlSpacer2, java.awt.BorderLayout.NORTH);
        pnlRow2Wrap.add(pnlRow2, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlRow1, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlRow2Wrap, java.awt.BorderLayout.SOUTH);
        add(pnlNorth, java.awt.BorderLayout.NORTH);
        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    private hiking.model.User user;
    private java.util.function.Consumer<String> go;
    private boolean full, inv;
    private final hiking.ui.Charts.Line line = new hiking.ui.Charts.Line().format(Theme::moneyShort);
    private final hiking.ui.Charts.HBar top = new hiking.ui.Charts.HBar().format(Theme::moneyShort), lowBar = new hiking.ui.Charts.HBar().color(Theme.WARN);
    private final hiking.ui.Charts.Donut donut = new hiking.ui.Charts.Donut();

    /** Role-aware set-up: which KPI cards and which charts are shown. */
    private void postInit() {
        Util.clearBanner(lblBanner);
        full = user.role.can(hiking.model.Perm.DASH_FULL); inv = !full && user.role.can(hiking.model.Perm.DASH_INVENTORY);
        javax.swing.JLabel[] titles = {lblKpiTitle1, lblKpiTitle2, lblKpiTitle3, lblKpiTitle4, lblKpiTitle5, lblKpiTitle6};
        javax.swing.JPanel[] cards = {pnlKpi1, pnlKpi2, pnlKpi3, pnlKpi4, pnlKpi5, pnlKpi6};
        String[] t = full ? new String[]{"TODAY'S SALES", "TRANSACTIONS TODAY", "SALES THIS MONTH", "AVERAGE BASKET", "LOW-STOCK ITEMS", "STOCK VALUE"}
            : inv ? new String[]{"ACTIVE PRODUCTS", "LOW-STOCK ITEMS", "OUT OF STOCK", "STOCK VALUE", "MOVEMENTS TODAY"}
            : new String[]{"MY SALES TODAY", "MY TRANSACTIONS", "MY SALES THIS MONTH", "MY AVERAGE BASKET"};
        for (int i = 0; i < 6; i++) { if (i < t.length) titles[i].setText(t[i]); else pnlKpiRow.remove(cards[i]); }
        ((java.awt.GridLayout) pnlKpiRow.getLayout()).setColumns(t.length);
        donut.format(v -> String.format("%,.0f", v), "units");
        if (inv) {
            setChart('A', "Stock by category", "Units on hand", donut); setChart('B', "Needs restocking", "Lowest stock-to-reorder items", lowBar);
            lowBar.format(v -> String.format("%,.0f", v)); pnlRow2Wrap.setVisible(false);
        } else if (full) {
            donut.format(Theme::moneyShort, "PHP"); lowBar.format(v -> String.format("%,.0f left", v));
            setChart('A', "Sales - last 14 days", "Daily total in PHP", line); setChart('B', "Sales by category", "Last 30 days", donut);
            setChart('C', "Best sellers", "Top items by revenue, last 30 days", top); setChart('D', "Low stock", "Closest to selling out", lowBar);
        } else {
            setChart('A', "My sales - last 14 days", "Daily total in PHP", line); setChart('B', "Best sellers", "Top items by revenue, last 30 days", top); pnlRow2Wrap.setVisible(false);
        }
        Util.skin(this);
    }
    private void setChart(char k, String title, String sub, javax.swing.JComponent chart) {
        javax.swing.JLabel t = k == 'A' ? lblChartTitleA : k == 'B' ? lblChartTitleB : k == 'C' ? lblChartTitleC : lblChartTitleD;
        javax.swing.JLabel s = k == 'A' ? lblChartSubA : k == 'B' ? lblChartSubB : k == 'C' ? lblChartSubC : lblChartSubD;
        javax.swing.JPanel h = k == 'A' ? pnlChartA : k == 'B' ? pnlChartB : k == 'C' ? pnlChartC : pnlChartD;
        t.setText(title); s.setText(sub); Util.fill(h, chart);
    }
    private void setKpi(int i, String value, String sub) {
        javax.swing.JLabel[] v = {lblKpiValue1, lblKpiValue2, lblKpiValue3, lblKpiValue4, lblKpiValue5, lblKpiValue6}, s = {lblKpiSub1, lblKpiSub2, lblKpiSub3, lblKpiSub4, lblKpiSub5, lblKpiSub6};
        v[i].setText(value); s[i].setText(sub == null ? " " : sub);
    }

    @Override public String title() { return "Dashboard"; }
    @Override public String subtitle() { return "Welcome, " + user.fullName + ". Here is how the restaurant is doing."; }
    @Override public void onShow() {
        Util.async(lblBanner, () -> {
            Object[] r = new Object[6]; r[0] = StatsDao.cards();
            if (!inv) { r[1] = StatsDao.dailySales(14); r[2] = StatsDao.topProducts(30, 7); }
            if (full) r[3] = StatsDao.categorySales(30);
            if (full) r[4] = StatsDao.lowStock(7);
            if (inv) { r[3] = StatsDao.stockByCategory(); r[4] = StatsDao.lowStock(8); }
            return r;
        }, r -> {
            StatsDao.Cards k = (StatsDao.Cards) r[0];
            if (full) { setKpi(0, Theme.money(k.todaySales), "sales so far today"); setKpi(1, String.valueOf(k.todayTrans), "completed orders");
                String mo = LocalDate.now().getMonth().toString(); setKpi(2, Theme.money(k.monthSales), mo.substring(0, 1) + mo.substring(1).toLowerCase());
                setKpi(3, Theme.money(k.avgBasket), "per transaction today"); setKpi(4, String.valueOf(k.lowStock), k.outOfStock + " out of stock"); setKpi(5, Theme.moneyShort(k.stockValue), "at cost"); }
            else if (inv) { setKpi(0, String.valueOf(k.products), "on the menu"); setKpi(1, String.valueOf(k.lowStock), "at or below reorder level"); setKpi(2, String.valueOf(k.outOfStock), "need restock now");
                setKpi(3, Theme.moneyShort(k.stockValue), "at cost"); setKpi(4, String.valueOf(k.movementsToday), "stock changes recorded"); }
            else { setKpi(0, Theme.money(k.mySalesToday), "your sales today"); setKpi(1, String.valueOf(k.myTransToday), "orders you completed"); setKpi(2, Theme.money(k.myMonthSales), "this month");
                setKpi(3, Theme.money(k.myTransToday == 0 ? 0 : k.mySalesToday / k.myTransToday), "per order today"); }
            if (!inv) {
                @SuppressWarnings("unchecked") LinkedHashMap<LocalDate, Double> d = (LinkedHashMap<LocalDate, Double>) r[1]; String[] xs = new String[d.size()]; Double[] vs = new Double[d.size()]; int i = 0;
                for (Map.Entry<LocalDate, Double> e : d.entrySet()) { xs[i] = e.getKey().format(DateTimeFormatter.ofPattern("MMM d")); vs[i++] = e.getValue(); }
                line.setData(xs, new hiking.ui.Charts.Line.Series("Sales", vs, Theme.PRIMARY, false, true));
                @SuppressWarnings("unchecked") LinkedHashMap<String, Double> tp = (LinkedHashMap<String, Double>) r[2]; top.setData(tp);
            }
            @SuppressWarnings("unchecked") LinkedHashMap<String, Double> m3 = (LinkedHashMap<String, Double>) r[3]; if (m3 != null) donut.setData(m3);
            @SuppressWarnings("unchecked") LinkedHashMap<String, Double> m4 = (LinkedHashMap<String, Double>) r[4]; if (m4 != null) lowBar.setData(m4, v -> v <= 10 ? Theme.DANGER : Theme.WARN);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlNorth;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlKpiRow;

    private javax.swing.JPanel pnlKpi1;

    private javax.swing.JLabel lblKpiTitle1;

    private javax.swing.JLabel lblKpiValue1;

    private javax.swing.JLabel lblKpiSub1;

    private javax.swing.JPanel pnlKpi2;

    private javax.swing.JLabel lblKpiTitle2;

    private javax.swing.JLabel lblKpiValue2;

    private javax.swing.JLabel lblKpiSub2;

    private javax.swing.JPanel pnlKpi3;

    private javax.swing.JLabel lblKpiTitle3;

    private javax.swing.JLabel lblKpiValue3;

    private javax.swing.JLabel lblKpiSub3;

    private javax.swing.JPanel pnlKpi4;

    private javax.swing.JLabel lblKpiTitle4;

    private javax.swing.JLabel lblKpiValue4;

    private javax.swing.JLabel lblKpiSub4;

    private javax.swing.JPanel pnlKpi5;

    private javax.swing.JLabel lblKpiTitle5;

    private javax.swing.JLabel lblKpiValue5;

    private javax.swing.JLabel lblKpiSub5;

    private javax.swing.JPanel pnlKpi6;

    private javax.swing.JLabel lblKpiTitle6;

    private javax.swing.JLabel lblKpiValue6;

    private javax.swing.JLabel lblKpiSub6;

    private javax.swing.JPanel pnlSpacer1;

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlRow1;

    private javax.swing.JPanel pnlCardA;

    private javax.swing.JPanel pnlChartHeadA;

    private javax.swing.JLabel lblChartTitleA;

    private javax.swing.JLabel lblChartSubA;

    private javax.swing.JPanel pnlChartA;

    private javax.swing.JPanel pnlCardB;

    private javax.swing.JPanel pnlChartHeadB;

    private javax.swing.JLabel lblChartTitleB;

    private javax.swing.JLabel lblChartSubB;

    private javax.swing.JPanel pnlChartB;

    private javax.swing.JPanel pnlRow2Wrap;

    private javax.swing.JPanel pnlSpacer2;

    private javax.swing.JPanel pnlRow2;

    private javax.swing.JPanel pnlCardC;

    private javax.swing.JPanel pnlChartHeadC;

    private javax.swing.JLabel lblChartTitleC;

    private javax.swing.JLabel lblChartSubC;

    private javax.swing.JPanel pnlChartC;

    private javax.swing.JPanel pnlCardD;

    private javax.swing.JPanel pnlChartHeadD;

    private javax.swing.JLabel lblChartTitleD;

    private javax.swing.JLabel lblChartSubD;

    private javax.swing.JPanel pnlChartD;

    // End of variables declaration//GEN-END:variables
}
