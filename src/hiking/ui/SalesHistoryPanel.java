package hiking.ui;

import hiking.dao.SaleDao;
import hiking.model.SaleRecord;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

/**
 * Sales personnel: own sales by date range with receipt detail.
 */
public class SalesHistoryPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public SalesHistoryPanel() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlNorth = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlNorth2 = new javax.swing.JPanel();
        pnlBar = new javax.swing.JPanel();
        lblFrom = new javax.swing.JLabel();
        txtFrom = new javax.swing.JTextField();
        lblTo = new javax.swing.JLabel();
        txtTo = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnToday = new javax.swing.JButton();
        btnWeek = new javax.swing.JButton();
        btnMonth = new javax.swing.JButton();
        btnReceipt = new javax.swing.JButton();
        pnlStatsWrap = new javax.swing.JPanel();
        pnlStats = new javax.swing.JPanel();
        pnlStat1 = new javax.swing.JPanel();
        lblStatTitle1 = new javax.swing.JLabel();
        lblStatValue1 = new javax.swing.JLabel();
        lblStatSub1 = new javax.swing.JLabel();
        pnlStat2 = new javax.swing.JPanel();
        lblStatTitle2 = new javax.swing.JLabel();
        lblStatValue2 = new javax.swing.JLabel();
        lblStatSub2 = new javax.swing.JLabel();
        pnlStat3 = new javax.swing.JPanel();
        lblStatTitle3 = new javax.swing.JLabel();
        lblStatValue3 = new javax.swing.JLabel();
        lblStatSub3 = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        scrSales = new javax.swing.JScrollPane();
        tblSales = new javax.swing.JTable();
        pnlDetail = new javax.swing.JPanel();
        pnlDetailHead = new javax.swing.JPanel();
        lblDetailTitle = new javax.swing.JLabel();
        lblDetailInfo = new javax.swing.JLabel();
        scrLines = new javax.swing.JScrollPane();
        tblLines = new javax.swing.JTable();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlNorth.setOpaque(false);
        pnlNorth.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(37, 99, 235));

        pnlNorth2.setOpaque(false);
        pnlNorth2.setLayout(new java.awt.BorderLayout());

        pnlBar.setOpaque(false);
        pnlBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));

        lblFrom.setText("From");
        lblFrom.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblFrom.setForeground(new java.awt.Color(63, 75, 95));

        txtFrom.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFrom.setColumns(9);
        txtFrom.setPreferredSize(new java.awt.Dimension(130, 38));

        lblTo.setText("To");
        lblTo.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblTo.setForeground(new java.awt.Color(63, 75, 95));

        txtTo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTo.setColumns(9);
        txtTo.setPreferredSize(new java.awt.Dimension(130, 38));

        btnSearch.setText("Search");
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSearch.setBackground(new java.awt.Color(214, 110, 46));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setContentAreaFilled(false);
        btnSearch.setBorderPainted(false);
        btnSearch.setFocusPainted(false);
        btnSearch.setOpaque(true);
        btnSearch.setPreferredSize(new java.awt.Dimension(100, 38));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnToday.setText("Today");
        btnToday.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnToday.setBackground(new java.awt.Color(232, 216, 198));
        btnToday.setForeground(new java.awt.Color(31, 41, 55));
        btnToday.setContentAreaFilled(false);
        btnToday.setBorderPainted(false);
        btnToday.setFocusPainted(false);
        btnToday.setOpaque(true);
        btnToday.setPreferredSize(new java.awt.Dimension(80, 38));
        btnToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodayActionPerformed(evt);
            }
        });

        btnWeek.setText("Last 7 days");
        btnWeek.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnWeek.setBackground(new java.awt.Color(232, 216, 198));
        btnWeek.setForeground(new java.awt.Color(31, 41, 55));
        btnWeek.setContentAreaFilled(false);
        btnWeek.setBorderPainted(false);
        btnWeek.setFocusPainted(false);
        btnWeek.setOpaque(true);
        btnWeek.setPreferredSize(new java.awt.Dimension(110, 38));
        btnWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWeekActionPerformed(evt);
            }
        });

        btnMonth.setText("Last 30 days");
        btnMonth.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnMonth.setBackground(new java.awt.Color(232, 216, 198));
        btnMonth.setForeground(new java.awt.Color(31, 41, 55));
        btnMonth.setContentAreaFilled(false);
        btnMonth.setBorderPainted(false);
        btnMonth.setFocusPainted(false);
        btnMonth.setOpaque(true);
        btnMonth.setPreferredSize(new java.awt.Dimension(120, 38));
        btnMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonthActionPerformed(evt);
            }
        });

        btnReceipt.setText("View receipt");
        btnReceipt.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnReceipt.setBackground(new java.awt.Color(31, 41, 55));
        btnReceipt.setForeground(new java.awt.Color(255, 255, 255));
        btnReceipt.setContentAreaFilled(false);
        btnReceipt.setBorderPainted(false);
        btnReceipt.setFocusPainted(false);
        btnReceipt.setOpaque(true);
        btnReceipt.setPreferredSize(new java.awt.Dimension(120, 38));
        btnReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReceiptActionPerformed(evt);
            }
        });

        pnlStatsWrap.setOpaque(false);
        pnlStatsWrap.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 14, 0));
        pnlStatsWrap.setLayout(new java.awt.BorderLayout());

        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 3, 16, 0));

        pnlStat1.setBackground(new java.awt.Color(255, 255, 255));
        pnlStat1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlStat1.setLayout(new java.awt.BorderLayout());

        lblStatTitle1.setText("TRANSACTIONS");
        lblStatTitle1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatTitle1.setForeground(new java.awt.Color(63, 75, 95));

        lblStatValue1.setText("-");
        lblStatValue1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblStatValue1.setForeground(new java.awt.Color(31, 41, 55));

        lblStatSub1.setText(" ");
        lblStatSub1.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblStatSub1.setForeground(new java.awt.Color(63, 75, 95));

        pnlStat2.setBackground(new java.awt.Color(255, 255, 255));
        pnlStat2.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlStat2.setLayout(new java.awt.BorderLayout());

        lblStatTitle2.setText("TOTAL SALES");
        lblStatTitle2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatTitle2.setForeground(new java.awt.Color(63, 75, 95));

        lblStatValue2.setText("-");
        lblStatValue2.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblStatValue2.setForeground(new java.awt.Color(31, 41, 55));

        lblStatSub2.setText(" ");
        lblStatSub2.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblStatSub2.setForeground(new java.awt.Color(63, 75, 95));

        pnlStat3.setBackground(new java.awt.Color(255, 255, 255));
        pnlStat3.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlStat3.setLayout(new java.awt.BorderLayout());

        lblStatTitle3.setText("ITEMS SOLD");
        lblStatTitle3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatTitle3.setForeground(new java.awt.Color(63, 75, 95));

        lblStatValue3.setText("-");
        lblStatValue3.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblStatValue3.setForeground(new java.awt.Color(31, 41, 55));

        lblStatSub3.setText(" ");
        lblStatSub3.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblStatSub3.setForeground(new java.awt.Color(63, 75, 95));

        pnlBody.setOpaque(false);
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlLeft.setOpaque(false);
        pnlLeft.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 16));
        pnlLeft.setLayout(new java.awt.BorderLayout());


        tblSales.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblSales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Receipt", "Date & time", "Cashier", "Items", "Subtotal", "Discount", "Total", "Payment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSalesMouseClicked(evt);
            }
        });

        pnlDetail.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetail.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlDetail.setPreferredSize(new java.awt.Dimension(440, 100));
        pnlDetail.setLayout(new java.awt.BorderLayout());

        pnlDetailHead.setOpaque(false);
        pnlDetailHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlDetailHead.setLayout(new java.awt.BorderLayout());

        lblDetailTitle.setText("Receipt detail");
        lblDetailTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblDetailTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblDetailInfo.setText("Select a sale to see its items.");
        lblDetailInfo.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblDetailInfo.setForeground(new java.awt.Color(63, 75, 95));


        tblLines.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Qty", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        pnlBar.add(lblFrom);
        pnlBar.add(txtFrom);
        pnlBar.add(lblTo);
        pnlBar.add(txtTo);
        pnlBar.add(btnSearch);
        pnlBar.add(btnToday);
        pnlBar.add(btnWeek);
        pnlBar.add(btnMonth);
        pnlBar.add(btnReceipt);
        pnlStat1.add(lblStatTitle1, java.awt.BorderLayout.NORTH);
        pnlStat1.add(lblStatValue1, java.awt.BorderLayout.CENTER);
        pnlStat1.add(lblStatSub1, java.awt.BorderLayout.SOUTH);
        pnlStat2.add(lblStatTitle2, java.awt.BorderLayout.NORTH);
        pnlStat2.add(lblStatValue2, java.awt.BorderLayout.CENTER);
        pnlStat2.add(lblStatSub2, java.awt.BorderLayout.SOUTH);
        pnlStat3.add(lblStatTitle3, java.awt.BorderLayout.NORTH);
        pnlStat3.add(lblStatValue3, java.awt.BorderLayout.CENTER);
        pnlStat3.add(lblStatSub3, java.awt.BorderLayout.SOUTH);
        pnlStats.add(pnlStat1);
        pnlStats.add(pnlStat2);
        pnlStats.add(pnlStat3);
        pnlStatsWrap.add(pnlStats, java.awt.BorderLayout.CENTER);
        pnlNorth2.add(pnlBar, java.awt.BorderLayout.NORTH);
        pnlNorth2.add(pnlStatsWrap, java.awt.BorderLayout.CENTER);
        pnlNorth.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlNorth.add(pnlNorth2, java.awt.BorderLayout.CENTER);
        scrSales.setViewportView(tblSales);
        pnlLeft.add(scrSales, java.awt.BorderLayout.CENTER);
        pnlDetailHead.add(lblDetailTitle, java.awt.BorderLayout.NORTH);
        pnlDetailHead.add(lblDetailInfo, java.awt.BorderLayout.CENTER);
        scrLines.setViewportView(tblLines);
        pnlDetail.add(pnlDetailHead, java.awt.BorderLayout.NORTH);
        pnlDetail.add(scrLines, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlLeft, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlDetail, java.awt.BorderLayout.EAST);
        add(pnlNorth, java.awt.BorderLayout.NORTH);
        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        load();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodayActionPerformed
        range(0);
    }//GEN-LAST:event_btnTodayActionPerformed

    private void btnWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWeekActionPerformed
        range(6);
    }//GEN-LAST:event_btnWeekActionPerformed

    private void btnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonthActionPerformed
        range(29);
    }//GEN-LAST:event_btnMonthActionPerformed

    private void btnReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReceiptActionPerformed
        openReceipt();
    }//GEN-LAST:event_btnReceiptActionPerformed

    private void tblSalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSalesMouseClicked
        if (evt.getClickCount() == 2) openReceipt();
    }//GEN-LAST:event_tblSalesMouseClicked


    private java.util.List<SaleRecord> list = new java.util.ArrayList<>();
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("MMM d, yyyy  h:mm a");

    private void postInit() {
        Util.clearBanner(lblBanner);
        txtFrom.setText(LocalDate.now().minusDays(6).toString()); txtTo.setText(LocalDate.now().toString());
        tblSales.getSelectionModel().addListSelectionListener(e -> { if (!e.getValueIsAdjusting()) detail(); });
        Util.skin(this);
        Util.rightAlign(tblSales, "Items", "Subtotal", "Discount", "Total"); Util.rightAlign(tblLines, "Qty", "Price", "Total");
    }
    @Override public String title() { return "Sales History"; }
    @Override public String subtitle() { return "Your completed sales and receipts."; }
    @Override public void onShow() { load(); }

    private void range(int days) { txtFrom.setText(LocalDate.now().minusDays(days).toString()); txtTo.setText(LocalDate.now().toString()); load(); }
    private SaleRecord selected() {
        int r = tblSales.getSelectedRow(); if (r < 0) return null; return list.get(tblSales.convertRowIndexToModel(r));
    }
    private void load() {
        LocalDate a, b;
        try { a = Util.date(txtFrom, "From"); b = Util.date(txtTo, "To"); if (b.isBefore(a)) throw new IllegalArgumentException("'To' must not be before 'From'."); }
        catch (IllegalArgumentException ex) { Util.banner(lblBanner, ex.getMessage(), false); return; }
        final LocalDate from = a, to = b;
        Util.async(lblBanner, () -> SaleDao.history(from, to), l -> {
            list = l; DefaultTableModel m = (DefaultTableModel) tblSales.getModel(); m.setRowCount(0); double tot = 0; int items = 0;
            for (SaleRecord s : l) { m.addRow(new Object[]{s.receiptNo, s.date.format(F), s.cashier, s.itemCount, Theme.money(s.subtotal), s.discount > 0 ? "-" + Theme.money(s.discount) : "-", Theme.money(s.total), s.payment}); tot += s.total; items += s.itemCount; }
            lblStatValue1.setText(String.valueOf(l.size())); lblStatSub1.setText(from + " to " + to);
            lblStatValue2.setText(Theme.money(tot)); lblStatSub2.setText(l.isEmpty() ? " " : "avg " + Theme.money(tot / l.size()) + " per sale");
            lblStatValue3.setText(String.valueOf(items)); lblStatSub3.setText(" ");
            ((DefaultTableModel) tblLines.getModel()).setRowCount(0); lblDetailTitle.setText("Receipt detail"); lblDetailInfo.setText("Select a sale to see its items.");
        });
    }
    private void detail() {
        SaleRecord s = selected(); if (s == null) return;
        Util.async(lblBanner, () -> { SaleDao.loadLines(s); return s; }, x -> {
            DefaultTableModel m = (DefaultTableModel) tblLines.getModel(); m.setRowCount(0);
            for (String[] l : x.lines) m.addRow(new Object[]{l[0], l[1], Theme.money(Double.parseDouble(l[2])), Theme.money(Double.parseDouble(l[3]))});
            lblDetailTitle.setText(x.receiptNo);
            lblDetailInfo.setText("<html>" + x.date.format(F) + "  •  " + x.payment + "<br>Tendered " + Theme.money(x.tendered) + "  •  Change " + Theme.money(Math.max(0, x.tendered - x.total)) + "</html>");
        });
    }
    private void openReceipt() {
        SaleRecord s = selected(); if (s == null) { Util.banner(lblBanner, "Select a sale first.", false); return; }
        new ReceiptDialog(Util.frame(this), true, s.id).setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlNorth;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlNorth2;

    private javax.swing.JPanel pnlBar;

    private javax.swing.JLabel lblFrom;

    private javax.swing.JTextField txtFrom;

    private javax.swing.JLabel lblTo;

    private javax.swing.JTextField txtTo;

    private javax.swing.JButton btnSearch;

    private javax.swing.JButton btnToday;

    private javax.swing.JButton btnWeek;

    private javax.swing.JButton btnMonth;

    private javax.swing.JButton btnReceipt;

    private javax.swing.JPanel pnlStatsWrap;

    private javax.swing.JPanel pnlStats;

    private javax.swing.JPanel pnlStat1;

    private javax.swing.JLabel lblStatTitle1;

    private javax.swing.JLabel lblStatValue1;

    private javax.swing.JLabel lblStatSub1;

    private javax.swing.JPanel pnlStat2;

    private javax.swing.JLabel lblStatTitle2;

    private javax.swing.JLabel lblStatValue2;

    private javax.swing.JLabel lblStatSub2;

    private javax.swing.JPanel pnlStat3;

    private javax.swing.JLabel lblStatTitle3;

    private javax.swing.JLabel lblStatValue3;

    private javax.swing.JLabel lblStatSub3;

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlLeft;

    private javax.swing.JScrollPane scrSales;

    private javax.swing.JTable tblSales;

    private javax.swing.JPanel pnlDetail;

    private javax.swing.JPanel pnlDetailHead;

    private javax.swing.JLabel lblDetailTitle;

    private javax.swing.JLabel lblDetailInfo;

    private javax.swing.JScrollPane scrLines;

    private javax.swing.JTable tblLines;

    // End of variables declaration//GEN-END:variables
}
