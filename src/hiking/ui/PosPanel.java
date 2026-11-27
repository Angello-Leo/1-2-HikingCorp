package hiking.ui;

public class PosPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public PosPanel() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlFilter = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        pnlBody = new javax.swing.JPanel();
        pnlMenuWrap = new javax.swing.JPanel();
        pnlMenuCard = new javax.swing.JPanel();
        scrMenu = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        pnlOrder = new javax.swing.JPanel();
        pnlOrderHead = new javax.swing.JPanel();
        lblOrderTitle = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        pnlCart = new javax.swing.JPanel();
        scrCart = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        pnlQty = new javax.swing.JPanel();
        btnMinus = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        pnlPay = new javax.swing.JPanel();
        pnlTotals = new javax.swing.JPanel();
        lblSubtotalKey = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblDiscPctKey = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        lblDiscountKey = new javax.swing.JLabel();
        lblDiscount = new javax.swing.JLabel();
        lblTotalKey = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblPaymentKey = new javax.swing.JLabel();
        cmbPayment = new javax.swing.JComboBox<>();
        lblTenderedKey = new javax.swing.JLabel();
        txtTendered = new javax.swing.JTextField();
        lblChangeKey = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        pnlQuick = new javax.swing.JPanel();
        btnExact = new javax.swing.JButton();
        btn100 = new javax.swing.JButton();
        btn200 = new javax.swing.JButton();
        btn500 = new javax.swing.JButton();
        btn1000 = new javax.swing.JButton();
        btnCharge = new javax.swing.JButton();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setOpaque(false);
        pnlTop.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));

        pnlFilter.setOpaque(false);
        pnlFilter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSearch.setPreferredSize(new java.awt.Dimension(260, 40));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cmbCategory.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cmbCategory.setPreferredSize(new java.awt.Dimension(170, 40));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        pnlBody.setOpaque(false);
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlMenuWrap.setOpaque(false);
        pnlMenuWrap.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 16));
        pnlMenuWrap.setLayout(new java.awt.BorderLayout());

        pnlMenuCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlMenuCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        pnlMenuCard.setLayout(new java.awt.BorderLayout());


        pnlMenu.setOpaque(false);
        pnlMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 12, 12));

        pnlOrder.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrder.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 18, 18, 18));
        pnlOrder.setPreferredSize(new java.awt.Dimension(450, 100));
        pnlOrder.setLayout(new java.awt.BorderLayout());

        pnlOrderHead.setOpaque(false);
        pnlOrderHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));
        pnlOrderHead.setLayout(new java.awt.BorderLayout());

        lblOrderTitle.setText("Current order");
        lblOrderTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblOrderTitle.setForeground(new java.awt.Color(31, 41, 55));

        btnClear.setText("Clear");
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnClear.setBackground(new java.awt.Color(232, 216, 198));
        btnClear.setForeground(new java.awt.Color(31, 41, 55));
        btnClear.setContentAreaFilled(false);
        btnClear.setBorderPainted(false);
        btnClear.setFocusPainted(false);
        btnClear.setOpaque(true);
        btnClear.setPreferredSize(new java.awt.Dimension(70, 32));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        pnlCart.setOpaque(false);
        pnlCart.setLayout(new java.awt.BorderLayout());


        tblCart.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price", "Qty", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        pnlQty.setOpaque(false);
        pnlQty.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 8));

        btnMinus.setText("-");
        btnMinus.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnMinus.setBackground(new java.awt.Color(255, 255, 255));
        btnMinus.setForeground(new java.awt.Color(214, 110, 46));
        btnMinus.setContentAreaFilled(false);
        btnMinus.setBorderPainted(false);
        btnMinus.setFocusPainted(false);
        btnMinus.setOpaque(true);
        btnMinus.setPreferredSize(new java.awt.Dimension(52, 34));
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnPlus.setText("+");
        btnPlus.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnPlus.setBackground(new java.awt.Color(255, 255, 255));
        btnPlus.setForeground(new java.awt.Color(214, 110, 46));
        btnPlus.setContentAreaFilled(false);
        btnPlus.setBorderPainted(false);
        btnPlus.setFocusPainted(false);
        btnPlus.setOpaque(true);
        btnPlus.setPreferredSize(new java.awt.Dimension(52, 34));
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove");
        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRemove.setBackground(new java.awt.Color(255, 255, 255));
        btnRemove.setForeground(new java.awt.Color(214, 110, 46));
        btnRemove.setContentAreaFilled(false);
        btnRemove.setBorderPainted(false);
        btnRemove.setFocusPainted(false);
        btnRemove.setOpaque(true);
        btnRemove.setPreferredSize(new java.awt.Dimension(90, 34));
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        pnlPay.setOpaque(false);
        pnlPay.setLayout(new java.awt.BorderLayout());

        pnlTotals.setOpaque(false);
        pnlTotals.setLayout(new java.awt.GridLayout(7, 2, 10, 4));

        lblSubtotalKey.setText("Subtotal");
        lblSubtotalKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblSubtotalKey.setForeground(new java.awt.Color(63, 75, 95));

        lblSubtotal.setText("0.00");
        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblSubtotal.setForeground(new java.awt.Color(31, 41, 55));
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblDiscPctKey.setText("Discount %");
        lblDiscPctKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblDiscPctKey.setForeground(new java.awt.Color(63, 75, 95));

        txtDiscount.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtDiscount.setText("0");
        txtDiscount.setPreferredSize(new java.awt.Dimension(120, 36));
        txtDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblDiscountKey.setText("Discount");
        lblDiscountKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblDiscountKey.setForeground(new java.awt.Color(63, 75, 95));

        lblDiscount.setText("-0.00");
        lblDiscount.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblDiscount.setForeground(new java.awt.Color(220, 38, 38));
        lblDiscount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblTotalKey.setText("TOTAL");
        lblTotalKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTotalKey.setForeground(new java.awt.Color(63, 75, 95));

        lblTotal.setText("0.00");
        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 28));
        lblTotal.setForeground(new java.awt.Color(31, 41, 55));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblPaymentKey.setText("Payment");
        lblPaymentKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblPaymentKey.setForeground(new java.awt.Color(63, 75, 95));

        cmbPayment.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "GCash", "Card" }));
        cmbPayment.setPreferredSize(new java.awt.Dimension(120, 36));
        cmbPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaymentActionPerformed(evt);
            }
        });

        lblTenderedKey.setText("Amount tendered");
        lblTenderedKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblTenderedKey.setForeground(new java.awt.Color(63, 75, 95));

        txtTendered.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtTendered.setPreferredSize(new java.awt.Dimension(120, 36));
        txtTendered.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblChangeKey.setText("Change");
        lblChangeKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblChangeKey.setForeground(new java.awt.Color(63, 75, 95));

        lblChange.setText("0.00");
        lblChange.setFont(new java.awt.Font("Segoe UI", 1, 15));
        lblChange.setForeground(new java.awt.Color(22, 163, 74));
        lblChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        pnlQuick.setOpaque(false);
        pnlQuick.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 6, 6));

        btnExact.setText("Exact");
        btnExact.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnExact.setBackground(new java.awt.Color(255, 255, 255));
        btnExact.setForeground(new java.awt.Color(214, 110, 46));
        btnExact.setContentAreaFilled(false);
        btnExact.setBorderPainted(false);
        btnExact.setFocusPainted(false);
        btnExact.setOpaque(true);
        btnExact.setPreferredSize(new java.awt.Dimension(70, 32));
        btnExact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExactActionPerformed(evt);
            }
        });

        btn100.setText("100");
        btn100.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btn100.setBackground(new java.awt.Color(255, 255, 255));
        btn100.setForeground(new java.awt.Color(214, 110, 46));
        btn100.setContentAreaFilled(false);
        btn100.setBorderPainted(false);
        btn100.setFocusPainted(false);
        btn100.setOpaque(true);
        btn100.setPreferredSize(new java.awt.Dimension(60, 32));
        btn100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn100ActionPerformed(evt);
            }
        });

        btn200.setText("200");
        btn200.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btn200.setBackground(new java.awt.Color(255, 255, 255));
        btn200.setForeground(new java.awt.Color(214, 110, 46));
        btn200.setContentAreaFilled(false);
        btn200.setBorderPainted(false);
        btn200.setFocusPainted(false);
        btn200.setOpaque(true);
        btn200.setPreferredSize(new java.awt.Dimension(60, 32));
        btn200.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn200ActionPerformed(evt);
            }
        });

        btn500.setText("500");
        btn500.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btn500.setBackground(new java.awt.Color(255, 255, 255));
        btn500.setForeground(new java.awt.Color(214, 110, 46));
        btn500.setContentAreaFilled(false);
        btn500.setBorderPainted(false);
        btn500.setFocusPainted(false);
        btn500.setOpaque(true);
        btn500.setPreferredSize(new java.awt.Dimension(60, 32));
        btn500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn500ActionPerformed(evt);
            }
        });

        btn1000.setText("1000");
        btn1000.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btn1000.setBackground(new java.awt.Color(255, 255, 255));
        btn1000.setForeground(new java.awt.Color(214, 110, 46));
        btn1000.setContentAreaFilled(false);
        btn1000.setBorderPainted(false);
        btn1000.setFocusPainted(false);
        btn1000.setOpaque(true);
        btn1000.setPreferredSize(new java.awt.Dimension(66, 32));
        btn1000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1000ActionPerformed(evt);
            }
        });

        btnCharge.setText("Charge  0.00");
        btnCharge.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnCharge.setBackground(new java.awt.Color(22, 163, 74));
        btnCharge.setForeground(new java.awt.Color(255, 255, 255));
        btnCharge.setContentAreaFilled(false);
        btnCharge.setBorderPainted(false);
        btnCharge.setFocusPainted(false);
        btnCharge.setOpaque(true);
        btnCharge.setPreferredSize(new java.awt.Dimension(100, 52));
        btnCharge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChargeActionPerformed(evt);
            }
        });

        pnlFilter.add(txtSearch);
        pnlFilter.add(cmbCategory);
        pnlTop.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlTop.add(pnlFilter, java.awt.BorderLayout.CENTER);
        scrMenu.setViewportView(pnlMenu);
        pnlMenuCard.add(scrMenu, java.awt.BorderLayout.CENTER);
        pnlMenuWrap.add(pnlMenuCard, java.awt.BorderLayout.CENTER);
        pnlOrderHead.add(lblOrderTitle, java.awt.BorderLayout.WEST);
        pnlOrderHead.add(btnClear, java.awt.BorderLayout.EAST);
        scrCart.setViewportView(tblCart);
        pnlQty.add(btnMinus);
        pnlQty.add(btnPlus);
        pnlQty.add(btnRemove);
        pnlCart.add(scrCart, java.awt.BorderLayout.CENTER);
        pnlCart.add(pnlQty, java.awt.BorderLayout.SOUTH);
        pnlTotals.add(lblSubtotalKey);
        pnlTotals.add(lblSubtotal);
        pnlTotals.add(lblDiscPctKey);
        pnlTotals.add(txtDiscount);
        pnlTotals.add(lblDiscountKey);
        pnlTotals.add(lblDiscount);
        pnlTotals.add(lblTotalKey);
        pnlTotals.add(lblTotal);
        pnlTotals.add(lblPaymentKey);
        pnlTotals.add(cmbPayment);
        pnlTotals.add(lblTenderedKey);
        pnlTotals.add(txtTendered);
        pnlTotals.add(lblChangeKey);
        pnlTotals.add(lblChange);
        pnlQuick.add(btnExact);
        pnlQuick.add(btn100);
        pnlQuick.add(btn200);
        pnlQuick.add(btn500);
        pnlQuick.add(btn1000);
        pnlPay.add(pnlTotals, java.awt.BorderLayout.NORTH);
        pnlPay.add(pnlQuick, java.awt.BorderLayout.CENTER);
        pnlPay.add(btnCharge, java.awt.BorderLayout.SOUTH);
        pnlOrder.add(pnlOrderHead, java.awt.BorderLayout.NORTH);
        pnlOrder.add(pnlCart, java.awt.BorderLayout.CENTER);
        pnlOrder.add(pnlPay, java.awt.BorderLayout.SOUTH);
        pnlBody.add(pnlMenuWrap, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlOrder, java.awt.BorderLayout.EAST);
        add(pnlTop, java.awt.BorderLayout.NORTH);
        add(pnlBody, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        tiles();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        tiles();
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        cart.clear();
        refresh();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        bump(-1);
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        bump(1);
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int r = tblCart.getSelectedRow();
        if (r >= 0) { cart.remove(tblCart.convertRowIndexToModel(r)); refresh(); }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void cmbPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaymentActionPerformed
        calc();
    }//GEN-LAST:event_cmbPaymentActionPerformed

    private void btnExactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExactActionPerformed
        quick("Exact");
    }//GEN-LAST:event_btnExactActionPerformed

    private void btn100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100ActionPerformed
        quick("100");
    }//GEN-LAST:event_btn100ActionPerformed

    private void btn200ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn200ActionPerformed
        quick("200");
    }//GEN-LAST:event_btn200ActionPerformed

    private void btn500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn500ActionPerformed
        quick("500");
    }//GEN-LAST:event_btn500ActionPerformed

    private void btn1000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1000ActionPerformed
        quick("1000");
    }//GEN-LAST:event_btn1000ActionPerformed

    private void btnChargeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChargeActionPerformed
        try {
            checkout();
        } catch (IllegalArgumentException ex) {
            Util.banner(lblBanner, ex.getMessage(), false);
        }
    }//GEN-LAST:event_btnChargeActionPerformed


    private final java.util.List<hiking.model.CartLine> cart = new java.util.ArrayList<>();
    private java.util.List<hiking.model.Product> menu = new java.util.ArrayList<>();
    private final UI.WrapPanel tiles = new UI.WrapPanel(12, 12);

    private void postInit() {
        Util.clearBanner(lblBanner);
        scrMenu.setViewportView(tiles);
        cmbCategory.removeAllItems();
        cmbCategory.addItem("All");
        for (String c : hiking.ml.Task.CATEGORIES) cmbCategory.addItem(c);
        cmbPayment.removeAllItems();
        for (String p : hiking.dao.SaleDao.PAYMENTS) cmbPayment.addItem(p);
        Util.skin(this);
        scrMenu.setBorder(null); scrMenu.setOpaque(false); scrMenu.getViewport().setOpaque(false);
        Util.rightAlign(tblCart, "Price", "Qty", "Total");
        tblCart.getColumnModel().getColumn(0).setPreferredWidth(170);
        javax.swing.event.DocumentListener dl = new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { calc(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { calc(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { calc(); }
        };
        txtDiscount.getDocument().addDocumentListener(dl);
        txtTendered.getDocument().addDocumentListener(dl);
        refresh();
    }

    @Override public String title() { return "New Sale"; }
    @Override public String subtitle() { return "Tap items to add them to the order, then take payment."; }
    @Override public void onShow() { Util.async(lblBanner, hiking.dao.ProductDao::forSale, l -> { menu = l; tiles(); }); }

    private void tiles() {
        tiles.removeAll();
        String q = txtSearch.getText().trim().toLowerCase();
        String c = cmbCategory.getSelectedIndex() <= 0 ? null : (String) cmbCategory.getSelectedItem();
        for (hiking.model.Product p : menu) {
            if (c != null && !p.category.equals(c)) continue;
            if (!q.isEmpty() && !p.name.toLowerCase().contains(q) && !p.sku.toLowerCase().contains(q)) continue;
            tiles.add(new Tile(p));
        }
        tiles.revalidate(); tiles.repaint();
    }

    /** One tappable menu tile (painted by code because there is one per menu item). */
    private final class Tile extends javax.swing.JComponent {
        private final hiking.model.Product p; private boolean hover;
        Tile(hiking.model.Product p) {
            this.p = p; setPreferredSize(new java.awt.Dimension(172, 104));
            setCursor(java.awt.Cursor.getPredefinedCursor(p.stockQty > 0 ? java.awt.Cursor.HAND_CURSOR : java.awt.Cursor.DEFAULT_CURSOR));
            setToolTipText(p.name + " - " + p.stockQty + " in stock");
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { hover = true; repaint(); }
                public void mouseExited(java.awt.event.MouseEvent e) { hover = false; repaint(); }
                public void mouseClicked(java.awt.event.MouseEvent e) { addToCart(p); }
            });
        }
        @Override protected void paintComponent(java.awt.Graphics g0) {
            java.awt.Graphics2D g = (java.awt.Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight(); boolean out = p.stockQty <= 0;
            g.setColor(out ? new java.awt.Color(0xF3F4F6) : hover ? Theme.PRIMARY_LT : java.awt.Color.WHITE); g.fill(new java.awt.geom.RoundRectangle2D.Double(0, 0, w - 1, h - 1, 16, 16));
            g.setColor(hover && !out ? Theme.PRIMARY : Theme.BORDER); g.setStroke(new java.awt.BasicStroke(hover ? 2f : 1.2f)); g.draw(new java.awt.geom.RoundRectangle2D.Double(1, 1, w - 3, h - 3, 16, 16));
            java.awt.Color cc = Theme.SERIES[Math.max(0, indexOf(p.category)) % Theme.SERIES.length]; g.setColor(Theme.alpha(cc, out ? 40 : 255)); g.fill(new java.awt.geom.RoundRectangle2D.Double(12, 12, 8, 8, 8, 8));
            g.setFont(Theme.plain(11.5f)); g.setColor(Theme.MUTED); g.drawString(p.category.toUpperCase(), 26, 21);
            g.setFont(Theme.bold(14.5f)); g.setColor(out ? Theme.MUTED : Theme.TEXT); drawWrapped(g, p.name, 14, 42, w - 28, 2);
            g.setFont(Theme.bold(16)); g.setColor(out ? Theme.MUTED : Theme.PRIMARY); g.drawString(Theme.money(p.unitPrice), 14, h - 14);
            String s = out ? "SOLD OUT" : p.lowStock() ? p.stockQty + " left" : p.stockQty + " in stock";
            g.setFont(Theme.bold(11.5f)); g.setColor(out ? Theme.DANGER : p.lowStock() ? Theme.WARN : Theme.MUTED); g.drawString(s, w - 14 - g.getFontMetrics().stringWidth(s), h - 14);
            g.dispose();
        }
        private void drawWrapped(java.awt.Graphics2D g, String t, int x, int y, int maxW, int lines) {
            java.awt.FontMetrics fm = g.getFontMetrics(); String[] words = t.split(" "); StringBuilder cur = new StringBuilder(); int line = 0;
            for (String wd : words) {
                String test = cur.length() == 0 ? wd : cur + " " + wd;
                if (fm.stringWidth(test) > maxW && cur.length() > 0) { g.drawString(cur.toString(), x, y + line * 18); line++; cur = new StringBuilder(wd); if (line >= lines) return; }
                else cur = new StringBuilder(test);
            }
            if (line < lines) g.drawString(cur.toString(), x, y + line * 18);
        }
    }
    private static int indexOf(String c) { for (int i = 0; i < hiking.ml.Task.CATEGORIES.length; i++) if (hiking.ml.Task.CATEGORIES[i].equals(c)) return i; return 0; }

    private void addToCart(hiking.model.Product p) {
        if (p.stockQty <= 0) { Util.banner(lblBanner, p.name + " is sold out.", false); return; }
        for (hiking.model.CartLine l : cart) if (l.product.id == p.id) {
            if (l.qty + 1 > p.stockQty) { Util.banner(lblBanner, "Only " + p.stockQty + " " + p.name + " left.", false); return; }
            l.qty++; refresh(); return;
        }
        cart.add(new hiking.model.CartLine(p, 1)); refresh();
    }
    private void bump(int d) {
        int r = tblCart.getSelectedRow();
        if (r < 0) { Util.banner(lblBanner, "Select an order line first.", false); return; }
        int i = tblCart.convertRowIndexToModel(r); hiking.model.CartLine l = cart.get(i); int q = l.qty + d;
        if (q < 1) cart.remove(i);
        else if (q > l.product.stockQty) { Util.banner(lblBanner, "Only " + l.product.stockQty + " " + l.product.name + " left.", false); return; }
        else l.qty = q;
        refresh();
        if (i < tblCart.getRowCount()) tblCart.setRowSelectionInterval(i, i);
    }

    private double subtotal() { double s = 0; for (hiking.model.CartLine l : cart) s += l.total(); return Math.round(s * 100) / 100.0; }
    private double discPct() {
        try { double d = Double.parseDouble(txtDiscount.getText().trim()); return d < 0 || d > 100 ? Double.NaN : d; }
        catch (Exception e) { return txtDiscount.getText().trim().isEmpty() ? 0 : Double.NaN; }
    }
    private double total() { double d = discPct(); if (Double.isNaN(d)) d = 0; double s = subtotal(); return Math.round((s - Math.round(s * d) / 100.0) * 100) / 100.0; }

    private void refresh() {
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblCart.getModel(); m.setRowCount(0);
        for (hiking.model.CartLine l : cart) m.addRow(new Object[]{l.product.name, Theme.money(l.product.unitPrice), l.qty, Theme.money(l.total())});
        calc();
    }
    private void calc() {
        double s = subtotal(), d = discPct(); boolean bad = Double.isNaN(d); if (bad) d = 0;
        double disc = Math.round(s * d) / 100.0, t = total();
        lblSubtotal.setText(Theme.money(s)); lblDiscount.setText("-" + Theme.money(disc)); lblTotal.setText(Theme.money(t)); btnCharge.setText("Charge  " + Theme.money(t));
        boolean cash = cmbPayment.getSelectedIndex() == 0; txtTendered.setEnabled(cash);
        double tend = 0; try { tend = Double.parseDouble(txtTendered.getText().trim().replace(",", "")); } catch (Exception ignored) { }
        double ch = cash ? tend - t : 0; lblChange.setText(Theme.money(Math.max(0, ch))); lblChange.setForeground(cash && ch < 0 ? Theme.DANGER : Theme.OK);
        btnCharge.setEnabled(!cart.isEmpty() && !bad && (!cash || tend + 0.001 >= t));
    }
    private void quick(String q) { txtTendered.setText(q.equals("Exact") ? String.format("%.2f", total()) : q); calc(); }

    private void checkout() {
        double d = discPct(); String method = (String) cmbPayment.getSelectedItem(); double tend = total();
        if (cmbPayment.getSelectedIndex() == 0) tend = Util.num(txtTendered, "Amount tendered");
        final double tendF = tend; final java.util.List<hiking.model.CartLine> copy = new java.util.ArrayList<>(cart); btnCharge.setEnabled(false);
        Util.async(() -> hiking.dao.SaleDao.checkout(copy, d, method, tendF), s -> {
            cart.clear(); txtTendered.setText(""); txtDiscount.setText("0"); refresh(); onShow();
            new ReceiptDialog(Util.frame(this), true, s).setVisible(true);
        }, ex -> { Util.banner(lblBanner, Util.message(ex), false); calc(); });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTop;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlFilter;

    private javax.swing.JTextField txtSearch;

    private javax.swing.JComboBox<String> cmbCategory;

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlMenuWrap;

    private javax.swing.JPanel pnlMenuCard;

    private javax.swing.JScrollPane scrMenu;

    private javax.swing.JPanel pnlMenu;

    private javax.swing.JPanel pnlOrder;

    private javax.swing.JPanel pnlOrderHead;

    private javax.swing.JLabel lblOrderTitle;

    private javax.swing.JButton btnClear;

    private javax.swing.JPanel pnlCart;

    private javax.swing.JScrollPane scrCart;

    private javax.swing.JTable tblCart;

    private javax.swing.JPanel pnlQty;

    private javax.swing.JButton btnMinus;

    private javax.swing.JButton btnPlus;

    private javax.swing.JButton btnRemove;

    private javax.swing.JPanel pnlPay;

    private javax.swing.JPanel pnlTotals;

    private javax.swing.JLabel lblSubtotalKey;

    private javax.swing.JLabel lblSubtotal;

    private javax.swing.JLabel lblDiscPctKey;

    private javax.swing.JTextField txtDiscount;

    private javax.swing.JLabel lblDiscountKey;

    private javax.swing.JLabel lblDiscount;

    private javax.swing.JLabel lblTotalKey;

    private javax.swing.JLabel lblTotal;

    private javax.swing.JLabel lblPaymentKey;

    private javax.swing.JComboBox<String> cmbPayment;

    private javax.swing.JLabel lblTenderedKey;

    private javax.swing.JTextField txtTendered;

    private javax.swing.JLabel lblChangeKey;

    private javax.swing.JLabel lblChange;

    private javax.swing.JPanel pnlQuick;

    private javax.swing.JButton btnExact;

    private javax.swing.JButton btn100;

    private javax.swing.JButton btn200;

    private javax.swing.JButton btn500;

    private javax.swing.JButton btn1000;

    private javax.swing.JButton btnCharge;

    // End of variables declaration//GEN-END:variables
}
