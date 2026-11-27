package hiking.ui;

public class AccountPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    public AccountPanel(hiking.model.User user) {
        initComponents();
        this.user = user;
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlWrap = new javax.swing.JPanel();
        pnlRow = new javax.swing.JPanel();
        pnlProfile = new javax.swing.JPanel();
        lblProfileTitle = new javax.swing.JLabel();
        pnlProfileBody = new javax.swing.JPanel();
        pnlInfoWrap = new javax.swing.JPanel();
        pnlInfo = new javax.swing.JPanel();
        lblNameKey = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblUserKey = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        lblLevelKey = new javax.swing.JLabel();
        lblLevel = new javax.swing.JLabel();
        lblLastKey = new javax.swing.JLabel();
        lblLast = new javax.swing.JLabel();
        pnlAccessBox = new javax.swing.JPanel();
        lblAccessTitle = new javax.swing.JLabel();
        pnlAccess = new javax.swing.JPanel();
        pnlPassword = new javax.swing.JPanel();
        lblPasswordTitle = new javax.swing.JLabel();
        pnlPwBody = new javax.swing.JPanel();
        pnlPwFields = new javax.swing.JPanel();
        lblCurKey = new javax.swing.JLabel();
        txtCurrent = new javax.swing.JPasswordField();
        lblNewKey = new javax.swing.JLabel();
        txtNew = new javax.swing.JPasswordField();
        lblConfirmKey = new javax.swing.JLabel();
        txtConfirm = new javax.swing.JPasswordField();
        chkShow = new javax.swing.JCheckBox();
        lblHint = new javax.swing.JLabel();
        pnlSave = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();

        setBackground(new java.awt.Color(214, 223, 234));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 28, 24, 28));
        setLayout(new java.awt.BorderLayout());

        pnlTop.setOpaque(false);
        pnlTop.setLayout(new java.awt.BorderLayout());

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));

        pnlWrap.setOpaque(false);
        pnlWrap.setLayout(new java.awt.BorderLayout());

        pnlRow.setOpaque(false);
        pnlRow.setLayout(new java.awt.GridLayout(1, 2, 18, 0));

        pnlProfile.setBackground(new java.awt.Color(255, 255, 255));
        pnlProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));
        pnlProfile.setLayout(new java.awt.BorderLayout());

        lblProfileTitle.setText("My profile");
        lblProfileTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblProfileTitle.setForeground(new java.awt.Color(31, 41, 55));

        pnlProfileBody.setOpaque(false);
        pnlProfileBody.setLayout(new java.awt.BorderLayout());

        pnlInfoWrap.setOpaque(false);
        pnlInfoWrap.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 0, 14, 0));
        pnlInfoWrap.setLayout(new java.awt.BorderLayout());

        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new java.awt.GridLayout(4, 2, 12, 10));

        lblNameKey.setText("Full name");
        lblNameKey.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblNameKey.setForeground(new java.awt.Color(63, 75, 95));

        lblName.setText("-");
        lblName.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblName.setForeground(new java.awt.Color(31, 41, 55));

        lblUserKey.setText("Username");
        lblUserKey.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblUserKey.setForeground(new java.awt.Color(63, 75, 95));

        lblUser.setText("-");
        lblUser.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblUser.setForeground(new java.awt.Color(31, 41, 55));

        lblLevelKey.setText("User level");
        lblLevelKey.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblLevelKey.setForeground(new java.awt.Color(63, 75, 95));

        lblLevel.setText("-");
        lblLevel.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblLevel.setForeground(new java.awt.Color(31, 41, 55));

        lblLastKey.setText("Last sign-in");
        lblLastKey.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblLastKey.setForeground(new java.awt.Color(63, 75, 95));

        lblLast.setText("-");
        lblLast.setFont(new java.awt.Font("Segoe UI", 0, 15));
        lblLast.setForeground(new java.awt.Color(31, 41, 55));

        pnlAccessBox.setOpaque(false);
        pnlAccessBox.setLayout(new java.awt.BorderLayout());

        lblAccessTitle.setText("What this level can access");
        lblAccessTitle.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblAccessTitle.setForeground(new java.awt.Color(63, 75, 95));
        lblAccessTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 6, 0));

        pnlAccess.setOpaque(false);
        pnlAccess.setLayout(new java.awt.GridLayout(0, 1, 0, 6));

        pnlPassword.setBackground(new java.awt.Color(255, 255, 255));
        pnlPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));
        pnlPassword.setLayout(new java.awt.BorderLayout());

        lblPasswordTitle.setText("Change password");
        lblPasswordTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblPasswordTitle.setForeground(new java.awt.Color(31, 41, 55));

        pnlPwBody.setOpaque(false);
        pnlPwBody.setLayout(new java.awt.BorderLayout());

        pnlPwFields.setOpaque(false);
        pnlPwFields.setLayout(new java.awt.GridLayout(7, 1, 0, 2));

        lblCurKey.setText("CURRENT PASSWORD");
        lblCurKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblCurKey.setForeground(new java.awt.Color(63, 75, 95));
        lblCurKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtCurrent.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtCurrent.setPreferredSize(new java.awt.Dimension(320, 40));

        lblNewKey.setText("NEW PASSWORD");
        lblNewKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblNewKey.setForeground(new java.awt.Color(63, 75, 95));
        lblNewKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtNew.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtNew.setPreferredSize(new java.awt.Dimension(320, 40));

        lblConfirmKey.setText("CONFIRM NEW PASSWORD");
        lblConfirmKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblConfirmKey.setForeground(new java.awt.Color(63, 75, 95));
        lblConfirmKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtConfirm.setPreferredSize(new java.awt.Dimension(320, 40));

        chkShow.setText("Show passwords");
        chkShow.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkShow.setForeground(new java.awt.Color(31, 41, 55));
        chkShow.setOpaque(false);
        chkShow.setFocusPainted(false);
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });

        lblHint.setText("At least 8 characters with letters and numbers.");
        lblHint.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblHint.setForeground(new java.awt.Color(63, 75, 95));
        lblHint.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 0, 0));

        pnlSave.setOpaque(false);
        pnlSave.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 12));

        btnUpdate.setText("Update password");
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnUpdate.setBackground(new java.awt.Color(214, 110, 46));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setOpaque(true);
        btnUpdate.setPreferredSize(new java.awt.Dimension(170, 40));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        pnlTop.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlInfo.add(lblNameKey);
        pnlInfo.add(lblName);
        pnlInfo.add(lblUserKey);
        pnlInfo.add(lblUser);
        pnlInfo.add(lblLevelKey);
        pnlInfo.add(lblLevel);
        pnlInfo.add(lblLastKey);
        pnlInfo.add(lblLast);
        pnlInfoWrap.add(pnlInfo, java.awt.BorderLayout.CENTER);
        pnlAccessBox.add(lblAccessTitle, java.awt.BorderLayout.NORTH);
        pnlAccessBox.add(pnlAccess, java.awt.BorderLayout.CENTER);
        pnlProfileBody.add(pnlInfoWrap, java.awt.BorderLayout.NORTH);
        pnlProfileBody.add(pnlAccessBox, java.awt.BorderLayout.CENTER);
        pnlProfile.add(lblProfileTitle, java.awt.BorderLayout.NORTH);
        pnlProfile.add(pnlProfileBody, java.awt.BorderLayout.CENTER);
        pnlPwFields.add(lblCurKey);
        pnlPwFields.add(txtCurrent);
        pnlPwFields.add(lblNewKey);
        pnlPwFields.add(txtNew);
        pnlPwFields.add(lblConfirmKey);
        pnlPwFields.add(txtConfirm);
        pnlPwFields.add(chkShow);
        pnlSave.add(btnUpdate);
        pnlPwBody.add(pnlPwFields, java.awt.BorderLayout.NORTH);
        pnlPwBody.add(lblHint, java.awt.BorderLayout.CENTER);
        pnlPwBody.add(pnlSave, java.awt.BorderLayout.SOUTH);
        pnlPassword.add(lblPasswordTitle, java.awt.BorderLayout.NORTH);
        pnlPassword.add(pnlPwBody, java.awt.BorderLayout.CENTER);
        pnlRow.add(pnlProfile);
        pnlRow.add(pnlPassword);
        pnlWrap.add(pnlRow, java.awt.BorderLayout.NORTH);
        add(pnlTop, java.awt.BorderLayout.NORTH);
        add(pnlWrap, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        char c = chkShow.isSelected() ? (char) 0 : '\u2022';
        txtCurrent.setEchoChar(c);
        txtNew.setEchoChar(c);
        txtConfirm.setEchoChar(c);
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        final String n = new String(txtNew.getPassword());
        if (!n.equals(new String(txtConfirm.getPassword()))) { Util.banner(lblBanner, "The new passwords do not match.", false); return; }
        final String old = new String(txtCurrent.getPassword());
        Util.async(lblBanner, () -> { hiking.dao.UserDao.changeOwnPassword(old, n); return Boolean.TRUE; }, ok -> {
            txtCurrent.setText(""); txtNew.setText(""); txtConfirm.setText("");
            Util.banner(lblBanner, "Password updated.", true);
        });
    }//GEN-LAST:event_btnUpdateActionPerformed


    private hiking.model.User user;

    private void postInit() {
        Util.clearBanner(lblBanner);
        lblName.setText(user.fullName); lblUser.setText(user.username); lblLevel.setText(user.role.label);
        lblLast.setText(user.lastLogin == null ? "first sign-in" : user.lastLogin.format(java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a")));
        pnlAccess.removeAll();
        for (String s : access(user.role)) {
            javax.swing.JLabel l = new javax.swing.JLabel("\u2022  " + s);
            l.setFont(new java.awt.Font("Segoe UI", 0, 14)); l.setForeground(Theme.TEXT); pnlAccess.add(l);
        }
        ((java.awt.GridLayout) pnlAccess.getLayout()).setRows(Math.max(1, pnlAccess.getComponentCount()));
        Util.skin(this);
        
        if (hiking.security.Session.can(hiking.model.Perm.PRODUCT_PURGE)) {
            javax.swing.JButton btnCode = new javax.swing.JButton("Set delete code");
            btnCode.setFont(Theme.bold(14f));
            btnCode.setPreferredSize(new java.awt.Dimension(170, 40));
            btnCode.addActionListener(e -> PurgeUi.setCode(this, lblBanner));
            javax.swing.JPanel row = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 16));
            row.setOpaque(false);
            row.add(btnCode);
            pnlWrap.add(row, java.awt.BorderLayout.CENTER);
        }
    }

    private static String[] access(hiking.model.Role r) {
        switch (r) {
            case ADMIN: return new String[]{"Dashboard (all cards and charts)", "Knowledge analysis: enter inputs and see predictions", "User management", "Audit log"};
            case INSTRUCTOR: return new String[]{"Dashboard (all cards and charts)", "Knowledge analysis: inputs, predictions, data sets, evaluation metrics and descriptions", "Retrain / reload the saved models"};
            case INVENTORY_CLERK: return new String[]{"Inventory: add / edit products", "Stock in, wastage and stock corrections", "Stock movement log and low-stock alerts"};
            default: return new String[]{"New sale (point of sale)", "Own sales history and receipts", "Own sales dashboard"};
        }
    }

    @Override public String title() { return "My Account"; }
    @Override public String subtitle() { return "Your profile, access and password."; }
    @Override public void onShow() { }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTop;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlWrap;

    private javax.swing.JPanel pnlRow;

    private javax.swing.JPanel pnlProfile;

    private javax.swing.JLabel lblProfileTitle;

    private javax.swing.JPanel pnlProfileBody;

    private javax.swing.JPanel pnlInfoWrap;

    private javax.swing.JPanel pnlInfo;

    private javax.swing.JLabel lblNameKey;

    private javax.swing.JLabel lblName;

    private javax.swing.JLabel lblUserKey;

    private javax.swing.JLabel lblUser;

    private javax.swing.JLabel lblLevelKey;

    private javax.swing.JLabel lblLevel;

    private javax.swing.JLabel lblLastKey;

    private javax.swing.JLabel lblLast;

    private javax.swing.JPanel pnlAccessBox;

    private javax.swing.JLabel lblAccessTitle;

    private javax.swing.JPanel pnlAccess;

    private javax.swing.JPanel pnlPassword;

    private javax.swing.JLabel lblPasswordTitle;

    private javax.swing.JPanel pnlPwBody;

    private javax.swing.JPanel pnlPwFields;

    private javax.swing.JLabel lblCurKey;

    private javax.swing.JPasswordField txtCurrent;

    private javax.swing.JLabel lblNewKey;

    private javax.swing.JPasswordField txtNew;

    private javax.swing.JLabel lblConfirmKey;

    private javax.swing.JPasswordField txtConfirm;

    private javax.swing.JCheckBox chkShow;

    private javax.swing.JLabel lblHint;

    private javax.swing.JPanel pnlSave;

    private javax.swing.JButton btnUpdate;

    // End of variables declaration//GEN-END:variables
}
