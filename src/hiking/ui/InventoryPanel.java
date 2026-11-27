package hiking.ui;

import hiking.dao.ProductDao;
import hiking.dao.StatsDao;
import hiking.ml.Task;
import hiking.model.Product;
import javax.swing.table.DefaultTableModel;

/**
 * Inventory clerk screen: products, stock in / wastage, stock movement log.
 */
public class InventoryPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public InventoryPanel() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlNorth = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlNorth2 = new javax.swing.JPanel();
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
        pnlStat4 = new javax.swing.JPanel();
        lblStatTitle4 = new javax.swing.JLabel();
        lblStatValue4 = new javax.swing.JLabel();
        lblStatSub4 = new javax.swing.JLabel();
        pnlBarWrap = new javax.swing.JPanel();
        pnlBar = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        chkLow = new javax.swing.JCheckBox();
        chkInactive = new javax.swing.JCheckBox();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnStockIn = new javax.swing.JButton();
        btnWaste = new javax.swing.JButton();
        tabMain = new javax.swing.JTabbedPane();
        pnlTabProducts = new javax.swing.JPanel();
        scrProducts = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        pnlTabMoves = new javax.swing.JPanel();
        scrMoves = new javax.swing.JScrollPane();
        tblMoves = new javax.swing.JTable();

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

        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 4, 16, 0));

        pnlStat1.setBackground(new java.awt.Color(255, 255, 255));
        pnlStat1.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlStat1.setLayout(new java.awt.BorderLayout());

        lblStatTitle1.setText("PRODUCTS");
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

        lblStatTitle2.setText("LOW STOCK");
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

        lblStatTitle3.setText("OUT OF STOCK");
        lblStatTitle3.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatTitle3.setForeground(new java.awt.Color(63, 75, 95));

        lblStatValue3.setText("-");
        lblStatValue3.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblStatValue3.setForeground(new java.awt.Color(31, 41, 55));

        lblStatSub3.setText(" ");
        lblStatSub3.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblStatSub3.setForeground(new java.awt.Color(63, 75, 95));

        pnlStat4.setBackground(new java.awt.Color(255, 255, 255));
        pnlStat4.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 18, 16, 18));
        pnlStat4.setLayout(new java.awt.BorderLayout());

        lblStatTitle4.setText("STOCK VALUE (AT COST)");
        lblStatTitle4.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatTitle4.setForeground(new java.awt.Color(63, 75, 95));

        lblStatValue4.setText("-");
        lblStatValue4.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblStatValue4.setForeground(new java.awt.Color(31, 41, 55));

        lblStatSub4.setText(" ");
        lblStatSub4.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblStatSub4.setForeground(new java.awt.Color(63, 75, 95));

        pnlBarWrap.setOpaque(false);
        pnlBarWrap.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 0, 4, 0));
        pnlBarWrap.setLayout(new java.awt.BorderLayout());

        pnlBar.setOpaque(false);
        pnlBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSearch.setColumns(18);
        txtSearch.setPreferredSize(new java.awt.Dimension(240, 38));
        txtSearch.setToolTipText("Search name or SKU");

        cmbCategory.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All categories" }));
        cmbCategory.setPreferredSize(new java.awt.Dimension(190, 38));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        chkLow.setText("Low stock only");
        chkLow.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkLow.setForeground(new java.awt.Color(31, 41, 55));
        chkLow.setOpaque(false);
        chkLow.setFocusPainted(false);
        chkLow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLowActionPerformed(evt);
            }
        });

        chkInactive.setText("Show inactive");
        chkInactive.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkInactive.setForeground(new java.awt.Color(31, 41, 55));
        chkInactive.setOpaque(false);
        chkInactive.setFocusPainted(false);
        chkInactive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInactiveActionPerformed(evt);
            }
        });

        btnAdd.setText("+  Add product");
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnAdd.setBackground(new java.awt.Color(214, 110, 46));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setFocusPainted(false);
        btnAdd.setOpaque(true);
        btnAdd.setPreferredSize(new java.awt.Dimension(140, 38));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnEdit.setBackground(new java.awt.Color(31, 41, 55));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setContentAreaFilled(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setOpaque(true);
        btnEdit.setPreferredSize(new java.awt.Dimension(80, 38));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnStockIn.setText("Stock in");
        btnStockIn.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnStockIn.setBackground(new java.awt.Color(22, 163, 74));
        btnStockIn.setForeground(new java.awt.Color(255, 255, 255));
        btnStockIn.setContentAreaFilled(false);
        btnStockIn.setBorderPainted(false);
        btnStockIn.setFocusPainted(false);
        btnStockIn.setOpaque(true);
        btnStockIn.setPreferredSize(new java.awt.Dimension(100, 38));
        btnStockIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStockInActionPerformed(evt);
            }
        });

        btnWaste.setText("Wastage / correction");
        btnWaste.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnWaste.setBackground(new java.awt.Color(232, 216, 198));
        btnWaste.setForeground(new java.awt.Color(31, 41, 55));
        btnWaste.setContentAreaFilled(false);
        btnWaste.setBorderPainted(false);
        btnWaste.setFocusPainted(false);
        btnWaste.setOpaque(true);
        btnWaste.setPreferredSize(new java.awt.Dimension(180, 38));
        btnWaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWasteActionPerformed(evt);
            }
        });

        tabMain.setFont(new java.awt.Font("Segoe UI", 1, 14));

        pnlTabProducts.setOpaque(false);
        pnlTabProducts.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));
        pnlTabProducts.setLayout(new java.awt.BorderLayout());


        tblProducts.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SKU", "Product", "Category", "Price", "Cost", "Stock", "Reorder at", "Shelf life (d)", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });

        pnlTabMoves.setOpaque(false);
        pnlTabMoves.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));
        pnlTabMoves.setLayout(new java.awt.BorderLayout());


        tblMoves.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblMoves.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Product", "Change", "Reason", "Note", "By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        pnlStat1.add(lblStatTitle1, java.awt.BorderLayout.NORTH);
        pnlStat1.add(lblStatValue1, java.awt.BorderLayout.CENTER);
        pnlStat1.add(lblStatSub1, java.awt.BorderLayout.SOUTH);
        pnlStat2.add(lblStatTitle2, java.awt.BorderLayout.NORTH);
        pnlStat2.add(lblStatValue2, java.awt.BorderLayout.CENTER);
        pnlStat2.add(lblStatSub2, java.awt.BorderLayout.SOUTH);
        pnlStat3.add(lblStatTitle3, java.awt.BorderLayout.NORTH);
        pnlStat3.add(lblStatValue3, java.awt.BorderLayout.CENTER);
        pnlStat3.add(lblStatSub3, java.awt.BorderLayout.SOUTH);
        pnlStat4.add(lblStatTitle4, java.awt.BorderLayout.NORTH);
        pnlStat4.add(lblStatValue4, java.awt.BorderLayout.CENTER);
        pnlStat4.add(lblStatSub4, java.awt.BorderLayout.SOUTH);
        pnlStats.add(pnlStat1);
        pnlStats.add(pnlStat2);
        pnlStats.add(pnlStat3);
        pnlStats.add(pnlStat4);
        pnlBar.add(txtSearch);
        pnlBar.add(cmbCategory);
        pnlBar.add(chkLow);
        pnlBar.add(chkInactive);
        pnlBar.add(btnAdd);
        pnlBar.add(btnEdit);
        pnlBar.add(btnStockIn);
        pnlBar.add(btnWaste);
        pnlBarWrap.add(pnlBar, java.awt.BorderLayout.CENTER);
        pnlNorth2.add(pnlStats, java.awt.BorderLayout.NORTH);
        pnlNorth2.add(pnlBarWrap, java.awt.BorderLayout.SOUTH);
        pnlNorth.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlNorth.add(pnlNorth2, java.awt.BorderLayout.CENTER);
        scrProducts.setViewportView(tblProducts);
        pnlTabProducts.add(scrProducts, java.awt.BorderLayout.CENTER);
        scrMoves.setViewportView(tblMoves);
        pnlTabMoves.add(scrMoves, java.awt.BorderLayout.CENTER);
        tabMain.addTab("Products", pnlTabProducts);
        tabMain.addTab("Stock movements", pnlTabMoves);
        add(pnlNorth, java.awt.BorderLayout.NORTH);
        add(tabMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        load();
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void chkLowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLowActionPerformed
        load();
    }//GEN-LAST:event_chkLowActionPerformed

    private void chkInactiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInactiveActionPerformed
        btnRestore.setVisible(chkInactive.isSelected());
        pnlBar.revalidate();
        pnlBar.repaint();
        load();
    }//GEN-LAST:event_chkInactiveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        openProduct(null);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Product p = selected(); if (p != null) openProduct(p);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnStockInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStockInActionPerformed
        Product p = selected(); if (p != null) openStock(p, 0);
    }//GEN-LAST:event_btnStockInActionPerformed

    private void btnWasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWasteActionPerformed
        Product p = selected(); if (p != null) openStock(p, 1);
    }//GEN-LAST:event_btnWasteActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        if (evt.getClickCount() == 2) { Product p = selected(); if (p != null) openProduct(p); }
    }//GEN-LAST:event_tblProductsMouseClicked


    private java.util.List<Product> list = new java.util.ArrayList<>();

    private void postInit() {
        Util.clearBanner(lblBanner);
        for (String c : Task.CATEGORIES) cmbCategory.addItem(c);
        txtSearch.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { load(); } public void removeUpdate(javax.swing.event.DocumentEvent e) { load(); } public void changedUpdate(javax.swing.event.DocumentEvent e) { load(); } });
        tabMain.addChangeListener(e -> { if (tabMain.getSelectedIndex() == 1) loadMoves(); });
        // "Remove item" button (created here, not in the Design tab, so it survives form regeneration)
        btnRemove = new javax.swing.JButton("Archive item");
        btnRemove.setFont(btnWaste.getFont());
        btnRemove.setBackground(Theme.SLATE);
        btnRemove.setForeground(java.awt.Color.WHITE);
        btnRemove.setContentAreaFilled(false);
        btnRemove.setBorderPainted(false);
        btnRemove.setFocusPainted(false);
        btnRemove.setOpaque(true);
        btnRemove.setPreferredSize(new java.awt.Dimension(130, 38));
        btnWaste.setText("Wastage");
        btnWaste.setPreferredSize(new java.awt.Dimension(110, 38));
        btnRemove.addActionListener(e -> archiveSelected());

        chkInactive.setText("Show archived");
        btnRestore = plainButton("Restore", Theme.OK);
        btnRestore.addActionListener(e -> restoreSelected());
        btnRestore.setVisible(false); 
        btnDelete = plainButton("Delete permanently", Theme.DANGER);
        btnDelete.addActionListener(e -> deleteSelected());
        btnDelete.setVisible(false);
        btnRequests = plainButton("Delete requests", Theme.WARN);
        btnRequests.addActionListener(e -> PurgeUi.openRequests(this, lblBanner, () -> {
            refreshRequestCount();
            load();
        }));
        btnRequests.setVisible(false);
        lblMode = new javax.swing.JLabel("  DELETE MODE: actions here are permanent  ");
        lblMode.setOpaque(true);
        lblMode.setBackground(Theme.DANGER);
        lblMode.setForeground(java.awt.Color.WHITE);
        lblMode.setFont(Theme.bold(13f));
        lblMode.setVisible(false);
        pnlBar.add(btnRemove);
        pnlBar.add(btnRestore);
        pnlBar.add(btnDelete);
        pnlBar.add(btnRequests);
        pnlBar.add(lblMode);

// hidden shortcut: Ctrl + Shift + D
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke("ctrl shift D"), "deleteMode");
        getActionMap().put("deleteMode", new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                toggleDeleteMode();
            }
        });

        pnlBar.setLayout(new WrapLayout(java.awt.FlowLayout.LEFT, 10, 8));
        txtSearch.setPreferredSize(new java.awt.Dimension(140, txtSearch.getPreferredSize().height)); cmbCategory.setPreferredSize(new java.awt.Dimension(180, cmbCategory.getPreferredSize().height));
        Util.skin(this);
        Util.rightAlign(tblProducts, "Price", "Cost", "Stock", "Reorder at", "Shelf life (d)");
        Util.rightAlign(tblMoves, "Change");
    }
    @Override public String title() { return "Inventory"; }
    @Override public String subtitle() { return "Manage menu items, receive deliveries and record wastage."; }
    @Override public void onShow() { load(); }

    private javax.swing.JButton btnRemove, btnRestore, btnDelete, btnRequests;
    private javax.swing.JLabel lblMode;
    private boolean deleteMode;
    private javax.swing.Timer modeTimer;

    private static javax.swing.JButton plainButton(String text, java.awt.Color bg) {
        javax.swing.JButton b = new javax.swing.JButton(text);
        b.setFont(Theme.bold(14f));
        b.setBackground(bg);
        b.setForeground(java.awt.Color.WHITE);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setPreferredSize(new java.awt.Dimension(Math.max(110, b.getPreferredSize().width + 20), 38));
        return b;
    }

    /**
     * Archive = hide the selected item (nothing is lost).
     */
    private void archiveSelected() {
        Product p = selected();
        if (p == null) {
            return;
        }
        if (!p.active) {
            Util.banner(lblBanner, p.name + " is already archived.", false);
            return;
        }
        if (!Util.confirm(this, "Archive item", "Archive '" + p.name + "' (" + p.sku + ")?\nIt is hidden from the menu and the normal list. You can restore it any time.")) {
            return;
        }
        Util.async(lblBanner, () -> {
            ProductDao.archive(p.id);
            return Boolean.TRUE;
        },
                ok -> {
                    Util.banner(lblBanner, p.name + " was archived (tick \"Show archived\" to see it).", true);
                    load();
                });
    }

    private void restoreSelected() {
        Product p = selected();
        if (p == null) {
            return;
        }
        if (p.active) {
            Util.banner(lblBanner, p.name + " is not archived.", false);
            return;
        }
        Util.async(lblBanner, () -> {
            ProductDao.restore(p.id);
            return Boolean.TRUE;
        },
                ok -> {
                    Util.banner(lblBanner, p.name + " was restored.", true);
                    load();
                });
    }

// ------------------------------------------------------------------ DELETE MODE (secret code)
    private void toggleDeleteMode() {
        if (deleteMode) {
            leaveDeleteMode();
            return;
        }
        String code = PurgeUi.ask(this, "Delete mode", "Enter the secret code.");
        if (code == null) {
            return;
        }
        Util.async(lblBanner, () -> {
            hiking.security.Secure.confirmSecretCode(code);
            return Boolean.TRUE;
        }, ok -> enterDeleteMode());
    }

    private void enterDeleteMode() {
        deleteMode = true;
        chkInactive.setSelected(true);
        lblMode.setVisible(true);
        btnDelete.setVisible(true);
        btnRemove.setVisible(false);
        btnRestore.setVisible(true);
        btnRequests.setVisible(hiking.security.Session.can(hiking.model.Perm.PRODUCT_PURGE));
        refreshRequestCount();
        if (modeTimer != null) {
            modeTimer.stop();
        }
        modeTimer = new javax.swing.Timer(120_000, e -> leaveDeleteMode());   // switches itself off after 2 minutes
        modeTimer.setRepeats(false);
        modeTimer.start();
        Util.banner(lblBanner, "DELETE MODE is ON for 2 minutes. Only archived items without sales can be deleted.", false);
        load();
    }

    /**
     * Called when the timer ends, the shortcut is pressed again, or the user
     * opens another page.
     */
    public void leaveDeleteMode() {
        if (!deleteMode) {
            return;
        }
        deleteMode = false;
        if (modeTimer != null) {
            modeTimer.stop();
        }
        lblMode.setVisible(false);
        btnDelete.setVisible(false);
        btnRequests.setVisible(false);
        btnRemove.setVisible(true);
        btnRestore.setVisible(chkInactive.isSelected());
        Util.clearBanner(lblBanner);
        pnlBar.revalidate();
        pnlBar.repaint();
    }

    private void refreshRequestCount() {
        if (!hiking.security.Session.can(hiking.model.Perm.PRODUCT_PURGE)) {
            return;
        }
        Util.async(lblBanner, () -> ProductDao.pendingRequests().size(), n -> btnRequests.setText("Delete requests (" + n + ")"));
    }

    /**
     * Admin: password, then deleted. Clerk: password, then a request goes to
     * the admin.
     */
    private void deleteSelected() {
        if (!deleteMode) {
            return;
        }
        Product p = selected();
        if (p == null) {
            return;
        }
        if (p.active) {
            Util.banner(lblBanner, "Archive the item first. Only archived items can be deleted.", false);
            return;
        }
        boolean admin = hiking.security.Session.can(hiking.model.Perm.PRODUCT_PURGE);
        String pw = PurgeUi.ask(this, "Confirm", (admin ? "Permanently delete" : "Ask the admin to delete") + " <b>" + p.name + "</b> (" + p.sku + ")?<br>Enter YOUR password to continue.");
        if (pw == null) {
            return;
        }
        if (admin) {
            Util.async(lblBanner, () -> {
                ProductDao.purgeAsAdmin(p.id, pw);
                return Boolean.TRUE;
            },
                    ok -> {
                        Util.banner(lblBanner, p.name + " was deleted permanently.", true);
                        load();
                    });
        } else {
            Util.async(lblBanner, () -> {
                ProductDao.requestPurge(p.id, pw);
                return Boolean.TRUE;
            },
                    ok -> Util.banner(lblBanner, "Sent to the admin for approval.", true));
        }
    }

    private Product selected() {
        int r = tblProducts.getSelectedRow();
        if (r < 0) { Util.banner(lblBanner, "Select a product first.", false); return null; }
        return list.get(tblProducts.convertRowIndexToModel(r));
    }
    private void load() {
        String c = cmbCategory.getSelectedIndex() <= 0 ? null : (String) cmbCategory.getSelectedItem();
        String text = txtSearch.getText(); boolean lowOnly = chkLow.isSelected(), inactive = chkInactive.isSelected();
        Util.async(lblBanner, () -> new Object[]{ProductDao.search(text, c, lowOnly, inactive), StatsDao.cards()}, o -> {
            @SuppressWarnings("unchecked") java.util.List<Product> l = (java.util.List<Product>) o[0]; StatsDao.Cards k = (StatsDao.Cards) o[1];
            list = l; DefaultTableModel m = (DefaultTableModel) tblProducts.getModel(); m.setRowCount(0);
            for (Product p : l) m.addRow(new Object[]{p.sku, p.name, p.category, Theme.money(p.unitPrice), Theme.money(p.costPrice), p.stockQty, p.reorderLevel, p.shelfLifeDays,
                !p.active ? "ARCHIVED" : p.stockQty == 0 ? "OUT" : p.lowStock() ? "LOW" : "OK"});
            lblStatValue1.setText(String.valueOf(k.products)); lblStatSub1.setText("active menu items");
            lblStatValue2.setText(String.valueOf(k.lowStock)); lblStatSub2.setText("at or below reorder level");
            lblStatValue3.setText(String.valueOf(k.outOfStock)); lblStatSub3.setText("need immediate restock");
            lblStatValue4.setText(Theme.money(k.stockValue)); lblStatSub4.setText("cost of goods on hand");
        });
    }
    private void loadMoves() {
        Util.async(lblBanner, () -> ProductDao.movements(300), rows -> { DefaultTableModel m = (DefaultTableModel) tblMoves.getModel(); m.setRowCount(0); for (String[] r : rows) m.addRow(r); });
    }
    private void openProduct(Product p) {
        ProductDialog d = new ProductDialog(Util.frame(this), true, p); d.setVisible(true);
        if (d.saved) { Util.banner(lblBanner, p == null ? "Product added." : "Product updated.", true); load(); }
    }
    /** mode 0 = stock in, 1 = wastage / correction. */
    private void openStock(Product p, int mode) {
        StockDialog d = new StockDialog(Util.frame(this), true, p, mode); d.setVisible(true);
        if (d.saved) { Util.banner(lblBanner, "Stock updated for " + p.name + ".", true); load(); }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlNorth;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlNorth2;

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

    private javax.swing.JPanel pnlStat4;

    private javax.swing.JLabel lblStatTitle4;

    private javax.swing.JLabel lblStatValue4;

    private javax.swing.JLabel lblStatSub4;

    private javax.swing.JPanel pnlBarWrap;

    private javax.swing.JPanel pnlBar;

    private javax.swing.JTextField txtSearch;

    private javax.swing.JComboBox<String> cmbCategory;

    private javax.swing.JCheckBox chkLow;

    private javax.swing.JCheckBox chkInactive;

    private javax.swing.JButton btnAdd;

    private javax.swing.JButton btnEdit;

    private javax.swing.JButton btnStockIn;

    private javax.swing.JButton btnWaste;

    private javax.swing.JTabbedPane tabMain;

    private javax.swing.JPanel pnlTabProducts;

    private javax.swing.JScrollPane scrProducts;

    private javax.swing.JTable tblProducts;

    private javax.swing.JPanel pnlTabMoves;

    private javax.swing.JScrollPane scrMoves;

    private javax.swing.JTable tblMoves;

    // End of variables declaration//GEN-END:variables
}
