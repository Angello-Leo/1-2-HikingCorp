package hiking.ui;

public class AuditPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public AuditPanel() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlToolbar = new javax.swing.JPanel();
        txtFilter = new javax.swing.JTextField();
        btnRefresh = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        scrAudit = new javax.swing.JScrollPane();
        tblAudit = new javax.swing.JTable();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setOpaque(false);
        pnlTop.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));

        pnlToolbar.setOpaque(false);
        pnlToolbar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));

        txtFilter.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFilter.setPreferredSize(new java.awt.Dimension(360, 40));
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRefresh.setBackground(new java.awt.Color(255, 255, 255));
        btnRefresh.setForeground(new java.awt.Color(214, 110, 46));
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusPainted(false);
        btnRefresh.setOpaque(true);
        btnRefresh.setPreferredSize(new java.awt.Dimension(100, 40));
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        pnlCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCard.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 6, 6, 6));
        pnlCard.setLayout(new java.awt.BorderLayout());


        tblAudit.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblAudit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "User", "Action", "Details"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        pnlToolbar.add(txtFilter);
        pnlToolbar.add(btnRefresh);
        pnlTop.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlTop.add(pnlToolbar, java.awt.BorderLayout.CENTER);
        scrAudit.setViewportView(tblAudit);
        pnlCard.add(scrAudit, java.awt.BorderLayout.CENTER);
        add(pnlTop, java.awt.BorderLayout.NORTH);
        add(pnlCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        fill();
    }//GEN-LAST:event_txtFilterKeyReleased

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        onShow();
    }//GEN-LAST:event_btnRefreshActionPerformed


    private java.util.List<String[]> all = new java.util.ArrayList<>();

    private void postInit() {
        Util.clearBanner(lblBanner);
        tblAudit.setRowHeight(26);

        javax.swing.table.TableColumnModel cols = tblAudit.getColumnModel();

        // Time
        cols.getColumn(0).setMinWidth(150);
        cols.getColumn(0).setPreferredWidth(190);
        cols.getColumn(0).setMaxWidth(240);

        // User
        cols.getColumn(1).setMinWidth(120);
        cols.getColumn(1).setPreferredWidth(160);
        cols.getColumn(1).setMaxWidth(220);

        // Action
        cols.getColumn(2).setMinWidth(160);
        cols.getColumn(2).setPreferredWidth(220);
        cols.getColumn(2).setMaxWidth(300);

        // Details gets whatever horizontal space remains
        cols.getColumn(3).setPreferredWidth(450);
        Util.skin(this);
    }

    @Override public String title() { return "Audit Log"; }
    @Override public String subtitle() { return "Sign-ins, stock changes, sales, user changes and model actions (latest 500)."; }
    @Override public void onShow() { Util.async(lblBanner, () -> hiking.dao.AuditDao.recent(500), rows -> { all = rows; fill(); }); }

    private void fill() {
        String q = txtFilter.getText().trim().toLowerCase();
        javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblAudit.getModel(); m.setRowCount(0);
        for (String[] r : all) {
            String s = String.join(" ", r[1] == null ? "" : r[1], r[2], r[3] == null ? "" : r[3]).toLowerCase();
            if (q.isEmpty() || s.contains(q)) m.addRow(new Object[]{r[0], r[1], r[2], r[3]});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTop;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlToolbar;

    private javax.swing.JTextField txtFilter;

    private javax.swing.JButton btnRefresh;

    private javax.swing.JPanel pnlCard;

    private javax.swing.JScrollPane scrAudit;

    private javax.swing.JTable tblAudit;

    // End of variables declaration//GEN-END:variables
}
