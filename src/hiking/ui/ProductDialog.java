package hiking.ui;

import hiking.dao.ProductDao;
import hiking.ml.Task;
import hiking.model.Product;

/**
 * Add / edit a product (pass null for a new one). Check `saved` after the dialog closes.
 */
public class ProductDialog extends javax.swing.JDialog {

    public ProductDialog(java.awt.Frame parent, boolean modal, hiking.model.Product p) {
        super(parent, modal);
        initComponents();
        this.product = p; postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        pnlRow_txtSku = new javax.swing.JPanel();
        lblSku = new javax.swing.JLabel();
        txtSku = new javax.swing.JTextField();
        pnlRow_txtName = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        pnlRow_cmbCategory = new javax.swing.JPanel();
        lblCategory = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        pnlRow_txtPrice = new javax.swing.JPanel();
        lblPrice = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        pnlRow_txtCost = new javax.swing.JPanel();
        lblCost = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        pnlRow_txtStock = new javax.swing.JPanel();
        lblStock = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        pnlRow_cmbStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        pnlRow_txtReorder = new javax.swing.JPanel();
        lblReorder = new javax.swing.JLabel();
        txtReorder = new javax.swing.JTextField();
        pnlRow_txtShelf = new javax.swing.JPanel();
        lblShelf = new javax.swing.JLabel();
        txtShelf = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("Product");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(214, 223, 234));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 24, 16, 24));
        pnlBody.setPreferredSize(new java.awt.Dimension(420, 700));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new java.awt.BorderLayout());

        lblTitle.setText("Add product");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 20));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblError.setText(" ");
        lblError.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblError.setForeground(new java.awt.Color(220, 38, 38));
        lblError.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));

        pnlFields.setOpaque(false);
        pnlFields.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 4, 0));
        pnlFields.setLayout(new java.awt.GridLayout(0, 1, 0, 0));

        pnlRow_txtSku.setOpaque(false);
        pnlRow_txtSku.setLayout(new java.awt.BorderLayout());

        lblSku.setText("SKU");
        lblSku.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSku.setForeground(new java.awt.Color(63, 75, 95));
        lblSku.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtSku.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSku.setColumns(24);

        pnlRow_txtName.setOpaque(false);
        pnlRow_txtName.setLayout(new java.awt.BorderLayout());

        lblName.setText("PRODUCT NAME");
        lblName.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblName.setForeground(new java.awt.Color(63, 75, 95));
        lblName.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlRow_cmbCategory.setOpaque(false);
        pnlRow_cmbCategory.setLayout(new java.awt.BorderLayout());

        lblCategory.setText("CATEGORY");
        lblCategory.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblCategory.setForeground(new java.awt.Color(63, 75, 95));
        lblCategory.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        cmbCategory.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Burgers" }));

        pnlRow_txtPrice.setOpaque(false);
        pnlRow_txtPrice.setLayout(new java.awt.BorderLayout());

        lblPrice.setText("SELLING PRICE (PHP)");
        lblPrice.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblPrice.setForeground(new java.awt.Color(63, 75, 95));
        lblPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtPrice.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlRow_txtCost.setOpaque(false);
        pnlRow_txtCost.setLayout(new java.awt.BorderLayout());

        lblCost.setText("COST PRICE (PHP)");
        lblCost.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblCost.setForeground(new java.awt.Color(63, 75, 95));
        lblCost.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtCost.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlRow_txtStock.setOpaque(false);
        pnlRow_txtStock.setLayout(new java.awt.BorderLayout());

        lblStock.setText("OPENING STOCK");
        lblStock.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStock.setForeground(new java.awt.Color(63, 75, 95));
        lblStock.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtStock.setText("0");

        pnlRow_cmbStatus.setOpaque(false);
        pnlRow_cmbStatus.setLayout(new java.awt.BorderLayout());

        lblStatus.setText("STATUS");
        lblStatus.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatus.setForeground(new java.awt.Color(63, 75, 95));
        lblStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive (hidden from sales)" }));

        pnlRow_txtReorder.setOpaque(false);
        pnlRow_txtReorder.setLayout(new java.awt.BorderLayout());

        lblReorder.setText("REORDER LEVEL");
        lblReorder.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblReorder.setForeground(new java.awt.Color(63, 75, 95));
        lblReorder.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtReorder.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtReorder.setText("20");

        pnlRow_txtShelf.setOpaque(false);
        pnlRow_txtShelf.setLayout(new java.awt.BorderLayout());

        lblShelf.setText("SHELF LIFE (DAYS)");
        lblShelf.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblShelf.setForeground(new java.awt.Color(63, 75, 95));
        lblShelf.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 4, 0));

        txtShelf.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtShelf.setText("7");

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
        pnlRow_txtSku.add(lblSku, java.awt.BorderLayout.NORTH);
        pnlRow_txtSku.add(txtSku, java.awt.BorderLayout.CENTER);
        pnlRow_txtName.add(lblName, java.awt.BorderLayout.NORTH);
        pnlRow_txtName.add(txtName, java.awt.BorderLayout.CENTER);
        pnlRow_cmbCategory.add(lblCategory, java.awt.BorderLayout.NORTH);
        pnlRow_cmbCategory.add(cmbCategory, java.awt.BorderLayout.CENTER);
        pnlRow_txtPrice.add(lblPrice, java.awt.BorderLayout.NORTH);
        pnlRow_txtPrice.add(txtPrice, java.awt.BorderLayout.CENTER);
        pnlRow_txtCost.add(lblCost, java.awt.BorderLayout.NORTH);
        pnlRow_txtCost.add(txtCost, java.awt.BorderLayout.CENTER);
        pnlRow_txtStock.add(lblStock, java.awt.BorderLayout.NORTH);
        pnlRow_txtStock.add(txtStock, java.awt.BorderLayout.CENTER);
        pnlRow_cmbStatus.add(lblStatus, java.awt.BorderLayout.NORTH);
        pnlRow_cmbStatus.add(cmbStatus, java.awt.BorderLayout.CENTER);
        pnlRow_txtReorder.add(lblReorder, java.awt.BorderLayout.NORTH);
        pnlRow_txtReorder.add(txtReorder, java.awt.BorderLayout.CENTER);
        pnlRow_txtShelf.add(lblShelf, java.awt.BorderLayout.NORTH);
        pnlRow_txtShelf.add(txtShelf, java.awt.BorderLayout.CENTER);
        pnlFields.add(pnlRow_txtSku);
        pnlFields.add(pnlRow_txtName);
        pnlFields.add(pnlRow_cmbCategory);
        pnlFields.add(pnlRow_txtPrice);
        pnlFields.add(pnlRow_txtCost);
        pnlFields.add(pnlRow_txtStock);
        pnlFields.add(pnlRow_cmbStatus);
        pnlFields.add(pnlRow_txtReorder);
        pnlFields.add(pnlRow_txtShelf);
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
            Product p = isNew ? new Product() : product;
            p.sku = txtSku.getText(); p.name = txtName.getText(); p.category = (String) cmbCategory.getSelectedItem();
            p.unitPrice = Util.num(txtPrice, "Selling price"); p.costPrice = Util.num(txtCost, "Cost price");
            p.reorderLevel = Util.integer(txtReorder, "Reorder level"); p.shelfLifeDays = Util.integer(txtShelf, "Shelf life");
            if (isNew) p.stockQty = Util.integer(txtStock, "Opening stock"); else p.active = cmbStatus.getSelectedIndex() == 0;
            if (isNew) ProductDao.create(p); else ProductDao.update(p);
            saved = true; dispose();
        } catch (Exception ex) { lblError.setText(Util.message(ex)); lblError.setVisible(true); pack(); }
    }//GEN-LAST:event_btnSaveActionPerformed


    public boolean saved;
    private Product product; private boolean isNew;

    private void postInit() {
        isNew = product == null; Product p = isNew ? new Product() : product;
        for (String c : Task.CATEGORIES) cmbCategory.addItem(c);
        lblTitle.setText(isNew ? "Add product" : "Edit product"); setTitle(isNew ? "Add product" : "Edit product");
        lblError.setVisible(false);
        txtSku.setText(p.sku == null ? "" : p.sku); txtName.setText(p.name == null ? "" : p.name);
        if (!isNew) { cmbCategory.setSelectedItem(p.category); txtPrice.setText(String.valueOf(p.unitPrice)); txtCost.setText(String.valueOf(p.costPrice)); cmbStatus.setSelectedIndex(p.active ? 0 : 1); }
        txtReorder.setText(String.valueOf(isNew ? 20 : p.reorderLevel)); txtShelf.setText(String.valueOf(p.shelfLifeDays));
        // new products have an opening stock; existing ones have a status instead
        pnlFields.remove(isNew ? pnlRow_txtStatus_ref() : pnlRow_txtStock_ref());
        ((java.awt.GridLayout) pnlFields.getLayout()).setRows(pnlFields.getComponentCount());
        Util.skin(this); getContentPane().setBackground(Theme.BG); getRootPane().setDefaultButton(btnSave);
        pack(); setLocationRelativeTo(getParent());
    }
    private java.awt.Component pnlRow_txtStatus_ref() { return cmbStatus.getParent(); }
    private java.awt.Component pnlRow_txtStock_ref() { return txtStock.getParent(); }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblError;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JPanel pnlRow_txtSku;

    private javax.swing.JLabel lblSku;

    private javax.swing.JTextField txtSku;

    private javax.swing.JPanel pnlRow_txtName;

    private javax.swing.JLabel lblName;

    private javax.swing.JTextField txtName;

    private javax.swing.JPanel pnlRow_cmbCategory;

    private javax.swing.JLabel lblCategory;

    private javax.swing.JComboBox<String> cmbCategory;

    private javax.swing.JPanel pnlRow_txtPrice;

    private javax.swing.JLabel lblPrice;

    private javax.swing.JTextField txtPrice;

    private javax.swing.JPanel pnlRow_txtCost;

    private javax.swing.JLabel lblCost;

    private javax.swing.JTextField txtCost;

    private javax.swing.JPanel pnlRow_txtStock;

    private javax.swing.JLabel lblStock;

    private javax.swing.JTextField txtStock;

    private javax.swing.JPanel pnlRow_cmbStatus;

    private javax.swing.JLabel lblStatus;

    private javax.swing.JComboBox<String> cmbStatus;

    private javax.swing.JPanel pnlRow_txtReorder;

    private javax.swing.JLabel lblReorder;

    private javax.swing.JTextField txtReorder;

    private javax.swing.JPanel pnlRow_txtShelf;

    private javax.swing.JLabel lblShelf;

    private javax.swing.JTextField txtShelf;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnCancel;

    private javax.swing.JButton btnSave;

    // End of variables declaration//GEN-END:variables
}
