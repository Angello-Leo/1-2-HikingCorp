package hiking.ui;

public class ReceiptDialog extends javax.swing.JDialog {

    public ReceiptDialog(java.awt.Frame parent, boolean modal, int saleId) {
        super(parent, modal);
        initComponents();
        postInit();
        load(saleId);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        scrReceipt = new javax.swing.JScrollPane();
        txtReceipt = new javax.swing.JTextArea();
        pnlButtons = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("Receipt");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 14, 18));
        pnlBody.setLayout(new java.awt.BorderLayout());

        scrReceipt.setPreferredSize(new java.awt.Dimension(430, 400));

        txtReceipt.setColumns(40);
        txtReceipt.setRows(20);
        txtReceipt.setFont(new java.awt.Font("Consolas", 0, 13));
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(true);
        txtReceipt.setEditable(false);
        txtReceipt.setLineWrap(false);

        pnlButtons.setOpaque(false);
        pnlButtons.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        btnPrint.setText("Print");
        btnPrint.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnPrint.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint.setForeground(new java.awt.Color(214, 110, 46));
        btnPrint.setContentAreaFilled(false);
        btnPrint.setBorderPainted(false);
        btnPrint.setFocusPainted(false);
        btnPrint.setOpaque(true);
        btnPrint.setPreferredSize(new java.awt.Dimension(100, 38));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnClose.setBackground(new java.awt.Color(214, 110, 46));
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setContentAreaFilled(false);
        btnClose.setBorderPainted(false);
        btnClose.setFocusPainted(false);
        btnClose.setOpaque(true);
        btnClose.setPreferredSize(new java.awt.Dimension(100, 38));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        scrReceipt.setViewportView(txtReceipt);
        pnlButtons.add(btnPrint);
        pnlButtons.add(btnClose);
        pnlBody.add(scrReceipt, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlButtons, java.awt.BorderLayout.SOUTH);
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        try {
            txtReceipt.print();
        } catch (java.awt.print.PrinterException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Could not print: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed


    /** Opens the dialog with a sale you already have in memory (used right after checkout). */
    public ReceiptDialog(java.awt.Frame parent, boolean modal, hiking.model.SaleRecord sale) {
        super(parent, modal);
        initComponents();
        postInit();
        txtReceipt.setText(build(sale));
        txtReceipt.setCaretPosition(0);
    }

    private void postInit() {
        getContentPane().setBackground(java.awt.Color.WHITE);
        txtReceipt.setBackground(java.awt.Color.WHITE);
        txtReceipt.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 18, 14, 18));
        Util.skin(this);
        getRootPane().setDefaultButton(btnClose);
        pack();
        setLocationRelativeTo(getParent());
    }

    /** Loads a sale of the signed-in user by id (used by Sales History). */
    private void load(int saleId) {
        txtReceipt.setText("Loading...");
        Util.async(() -> {
            for (hiking.model.SaleRecord s : hiking.dao.SaleDao.history(java.time.LocalDate.of(2000, 1, 1), java.time.LocalDate.now())) {
                if (s.id == saleId) { hiking.dao.SaleDao.loadLines(s); return s; }
            }
            throw new IllegalArgumentException("Receipt not found.");
        }, s -> { txtReceipt.setText(build(s)); txtReceipt.setCaretPosition(0); }, ex -> txtReceipt.setText(Util.message(ex)));
    }

    private static String clip(String s, int n) { return s.length() <= n ? s : s.substring(0, n - 1) + "~"; }

    /** Plain-text receipt layout (37 characters wide). */
    public static String build(hiking.model.SaleRecord s) {
        StringBuilder sb = new StringBuilder();
        sb.append("        1/2 HI KING CORP\n      Restaurant & Fast Food\n--------------------------------------\n");
        sb.append("Receipt : ").append(s.receiptNo).append('\n');
        sb.append("Date    : ").append(s.date.format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a"))).append('\n');
        sb.append("Cashier : ").append(s.cashier).append("\n--------------------------------------\n");
        for (String[] l : s.lines) sb.append(String.format("%-22s %3s %11s%n", clip(l[0], 22), l[1] + "x", String.format("%,.2f", Double.parseDouble(l[3]))));
        sb.append("--------------------------------------\n").append(String.format("%-26s %11s%n", "Subtotal", String.format("%,.2f", s.subtotal)));
        if (s.discount > 0) sb.append(String.format("%-26s %11s%n", "Discount", "-" + String.format("%,.2f", s.discount)));
        sb.append(String.format("%-26s %11s%n", "TOTAL", String.format("%,.2f", s.total)));
        sb.append(String.format("%-26s %11s%n", s.payment, String.format("%,.2f", s.tendered)));
        sb.append(String.format("%-26s %11s%n", "Change", String.format("%,.2f", Math.max(0, s.tendered - s.total))));
        sb.append("--------------------------------------\n       Thank you! Come again.\n");
        return sb.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JScrollPane scrReceipt;

    private javax.swing.JTextArea txtReceipt;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnPrint;

    private javax.swing.JButton btnClose;

    // End of variables declaration//GEN-END:variables
}
