package hiking.ui;

public class PasswordDialog extends javax.swing.JDialog {

    public PasswordDialog(java.awt.Frame parent, boolean modal, hiking.model.User u) {
        super(parent, modal);
        initComponents();
        this.target = u;
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblFor = new javax.swing.JLabel();
        lblBanner = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblNewKey = new javax.swing.JLabel();
        txtNew = new javax.swing.JPasswordField();
        chkShow = new javax.swing.JCheckBox();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("Reset password");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 16, 22));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new java.awt.BorderLayout());

        lblTitle.setText("Reset password");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblFor.setText("Set a new password for the user.");
        lblFor.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblFor.setForeground(new java.awt.Color(63, 75, 95));

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));
        lblBanner.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 6, 0));

        pnlFields.setOpaque(false);
        pnlFields.setLayout(new java.awt.BorderLayout());

        lblNewKey.setText("NEW PASSWORD");
        lblNewKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblNewKey.setForeground(new java.awt.Color(63, 75, 95));
        lblNewKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtNew.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNew.setPreferredSize(new java.awt.Dimension(340, 40));

        chkShow.setText("Show password");
        chkShow.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkShow.setForeground(new java.awt.Color(31, 41, 55));
        chkShow.setOpaque(false);
        chkShow.setFocusPainted(false);
        chkShow.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });

        pnlButtons.setOpaque(false);
        pnlButtons.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 0, 0));
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10));

        btnCancel.setText("Cancel");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnCancel.setBackground(new java.awt.Color(255, 255, 255));
        btnCancel.setForeground(new java.awt.Color(214, 110, 46));
        btnCancel.setContentAreaFilled(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setOpaque(true);
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 38));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnReset.setBackground(new java.awt.Color(214, 110, 46));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.setPreferredSize(new java.awt.Dimension(100, 38));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        pnlHead.add(lblTitle, java.awt.BorderLayout.NORTH);
        pnlHead.add(lblFor, java.awt.BorderLayout.CENTER);
        pnlHead.add(lblBanner, java.awt.BorderLayout.SOUTH);
        pnlFields.add(lblNewKey, java.awt.BorderLayout.NORTH);
        pnlFields.add(txtNew, java.awt.BorderLayout.CENTER);
        pnlFields.add(chkShow, java.awt.BorderLayout.SOUTH);
        pnlButtons.add(btnCancel);
        pnlButtons.add(btnReset);
        pnlBody.add(pnlHead, java.awt.BorderLayout.NORTH);
        pnlBody.add(pnlFields, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlButtons, java.awt.BorderLayout.SOUTH);
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        txtNew.setEchoChar(chkShow.isSelected() ? (char) 0 : '\u2022');
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        try {
            hiking.dao.UserDao.resetPassword(target.id, new String(txtNew.getPassword()));
            saved = true;
            dispose();
        } catch (Exception ex) {
            Util.banner(lblBanner, Util.message(ex), false);
            pack();
        }
    }//GEN-LAST:event_btnResetActionPerformed


    /** True when the password was changed. */
    public boolean saved;
    private hiking.model.User target;

    private void postInit() {
        getContentPane().setBackground(java.awt.Color.WHITE);
        Util.clearBanner(lblBanner);
        lblFor.setText("New password for " + target.username + " (8+ characters, letters and numbers).");
        Util.skin(this);
        getRootPane().setDefaultButton(btnReset);
        pack();
        setLocationRelativeTo(getParent());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblFor;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JLabel lblNewKey;

    private javax.swing.JPasswordField txtNew;

    private javax.swing.JCheckBox chkShow;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnCancel;

    private javax.swing.JButton btnReset;

    // End of variables declaration//GEN-END:variables
}
