package hiking.ui;

import hiking.dao.ProductDao;
import hiking.model.Product;

/**
 * Stock in (mode 0) or wastage / correction (mode 1) for one product. Check `saved` after it closes.
 */
public class StockDialog extends javax.swing.JDialog {

    public StockDialog(java.awt.Frame parent, boolean modal, hiking.model.Product p, int mode) {
        super(parent, modal);
        initComponents();
        this.product = p; this.mode = mode; postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        pnlRow_txtProduct = new javax.swing.JPanel();
        lblProduct = new javax.swing.JLabel();
        txtProduct = new javax.swing.JTextField();
        pnlRow_cmbType = new javax.swing.JPanel();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
        pnlRow_txtQty = new javax.swing.JPanel();
        lblQty = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        pnlRow_txtNote = new javax.swing.JPanel();
        lblNote = new javax.swing.JLabel();
        txtNote = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("Stock");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(214, 223, 234));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 24, 16, 24));
        pnlBody.setPreferredSize(new java.awt.Dimension(420, 420));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new java.awt.BorderLayout());

        lblTitle.setText("Stock in");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblError.setText(" ");
        lblError.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblError.setForeground(new java.awt.Color(220, 38, 38));
        lblError.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));

        pnlFields.setOpaque(false);
        pnlFields.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 4, 0));
        pnlFields.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

        pnlRow_txtProduct.setOpaque(false);
        pnlRow_txtProduct.setLayout(new java.awt.BorderLayout());

        lblProduct.setText("PRODUCT");
        lblProduct.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblProduct.setForeground(new java.awt.Color(63, 75, 95));
        lblProduct.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtProduct.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtProduct.setEditable(false);

        pnlRow_cmbType.setOpaque(false);
        pnlRow_cmbType.setLayout(new java.awt.BorderLayout());

        lblType.setText("TYPE");
        lblType.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblType.setForeground(new java.awt.Color(63, 75, 95));
        lblType.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        cmbType.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stock in (supplier delivery)", "Stock in (customer return)" }));

        pnlRow_txtQty.setOpaque(false);
        pnlRow_txtQty.setLayout(new java.awt.BorderLayout());

        lblQty.setText("QUANTITY");
        lblQty.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblQty.setForeground(new java.awt.Color(63, 75, 95));
        lblQty.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlRow_txtNote.setOpaque(false);
        pnlRow_txtNote.setLayout(new java.awt.BorderLayout());

        lblNote.setText("NOTE (optional)");
        lblNote.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblNote.setForeground(new java.awt.Color(63, 75, 95));
        lblNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtNote.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlButtons.setOpaque(false);
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 6));

        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnCancel.setBackground(new java.awt.Color(232, 216, 198));
        btnCancel.setForeground(new java.awt.Color(31, 41, 55));
        btnCancel.setContentAreaFilled(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setOpaque(true);
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSave.setBackground(new java.awt.Color(214, 110, 46));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        btnSave.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        pnlHead.add(lblTitle, java.awt.BorderLayout.NORTH);
        pnlHead.add(lblError, java.awt.BorderLayout.CENTER);
        pnlRow_txtProduct.add(lblProduct, java.awt.BorderLayout.NORTH);
        pnlRow_txtProduct.add(txtProduct, java.awt.BorderLayout.CENTER);
        pnlRow_cmbType.add(lblType, java.awt.BorderLayout.NORTH);
        pnlRow_cmbType.add(cmbType, java.awt.BorderLayout.CENTER);
        pnlRow_txtQty.add(lblQty, java.awt.BorderLayout.NORTH);
        pnlRow_txtQty.add(txtQty, java.awt.BorderLayout.CENTER);
        pnlRow_txtNote.add(lblNote, java.awt.BorderLayout.NORTH);
        pnlRow_txtNote.add(txtNote, java.awt.BorderLayout.CENTER);
        pnlFields.add(pnlRow_txtProduct);
        pnlFields.add(pnlRow_cmbType);
        pnlFields.add(pnlRow_txtQty);
        pnlFields.add(pnlRow_txtNote);
        pnlButtons.add(btnCancel);
        pnlButtons.add(btnSave);
        pnlBody.add(pnlHead, java.awt.BorderLayout.NORTH);
        pnlBody.add(pnlFields, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlButtons, java.awt.BorderLayout.SOUTH);
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            int q = Util.integer(txtQty, "Quantity"); int delta; String reason;
            if (mode == 0) { if (q <= 0) throw new IllegalArgumentException("Quantity must be greater than zero."); delta = q; reason = cmbType.getSelectedIndex() == 0 ? "STOCK_IN" : "RETURN"; }
            else if (cmbType.getSelectedIndex() == 0) { if (q <= 0) throw new IllegalArgumentException("Quantity must be greater than zero."); delta = -q; reason = "WASTAGE"; }
            else { delta = q; reason = "CORRECTION"; }
            ProductDao.adjustStock(product.id, delta, reason, txtNote.getText());
            saved = true; dispose();
        } catch (Exception ex) { lblError.setText(Util.message(ex)); lblError.setVisible(true); pack(); }
    }//GEN-LAST:event_btnSaveActionPerformed


    public boolean saved;
    private Product product; private int mode;

    private void postInit() {
        lblTitle.setText(mode == 0 ? "Stock in" : "Wastage / correction"); setTitle(lblTitle.getText()); lblError.setVisible(false);
        txtProduct.setText(product.name + "  (on hand: " + product.stockQty + ")"); txtProduct.setEnabled(false);
        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(mode == 0 ? new String[]{"Stock in (supplier delivery)", "Stock in (customer return)"} : new String[]{"Wastage / spoilage", "Correction (+ adds, - removes)"}));
        Util.skin(this); getContentPane().setBackground(Theme.BG); getRootPane().setDefaultButton(btnSave);
        pack(); setLocationRelativeTo(getParent());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblError;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JPanel pnlRow_txtProduct;

    private javax.swing.JLabel lblProduct;

    private javax.swing.JTextField txtProduct;

    private javax.swing.JPanel pnlRow_cmbType;

    private javax.swing.JLabel lblType;

    private javax.swing.JComboBox<String> cmbType;

    private javax.swing.JPanel pnlRow_txtQty;

    private javax.swing.JLabel lblQty;

    private javax.swing.JTextField txtQty;

    private javax.swing.JPanel pnlRow_txtNote;

    private javax.swing.JLabel lblNote;

    private javax.swing.JTextField txtNote;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnCancel;

    private javax.swing.JButton btnSave;

    // End of variables declaration//GEN-END:variables
}
