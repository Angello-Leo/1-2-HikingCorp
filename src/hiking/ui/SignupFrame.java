package hiking.ui;

/**
 * Sign-up window (same size and style as the sign-in window).
 */
public class SignupFrame extends javax.swing.JFrame {

    public SignupFrame() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTitleHost = new javax.swing.JPanel();
        pnlSplit = new javax.swing.JPanel();
        pnlBrand = new javax.swing.JPanel();
        pnlBrandText = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblBrand1 = new javax.swing.JLabel();
        lblBrand2 = new javax.swing.JLabel();
        lblTagline1 = new javax.swing.JLabel();
        lblTagline2 = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlForm = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lblConfirm = new javax.swing.JLabel();
        txtConfirm = new javax.swing.JPasswordField();
        chkShow = new javax.swing.JCheckBox();
        btnCreate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("1/2 HI KING CORP - Create an account");
        setMinimumSize(new java.awt.Dimension(1000, 640));
        setUndecorated(true);

        pnlTitleHost.setBackground(new java.awt.Color(214, 110, 46));
        pnlTitleHost.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTitleHost.setLayout(new java.awt.BorderLayout());

        pnlSplit.setBackground(new java.awt.Color(214, 223, 234));
        pnlSplit.setLayout(new java.awt.GridLayout(1, 2, 0, 0));

        pnlBrand.setBackground(new java.awt.Color(199, 67, 31));
        pnlBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(180, 80, 120, 40));
        pnlBrand.setOpaque(true);
        pnlBrand.setLayout(new java.awt.BorderLayout());

        pnlBrandText.setBackground(new java.awt.Color(199, 67, 31));
        pnlBrandText.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlBrandText.setPreferredSize(new java.awt.Dimension(100, 300));
        pnlBrandText.setLayout(new java.awt.GridLayout(0, 1, 0, 2));

        lblLogo.setText("\u00bd");
        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 40));
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setPreferredSize(new java.awt.Dimension(64, 64));

        lblBrand1.setText("1/2 Hi King");
        lblBrand1.setFont(new java.awt.Font("Segoe UI", 1, 42));
        lblBrand1.setForeground(new java.awt.Color(255, 255, 255));

        lblBrand2.setText("Corp");
        lblBrand2.setFont(new java.awt.Font("Segoe UI", 0, 22));
        lblBrand2.setForeground(new java.awt.Color(255, 217, 199));

        lblTagline1.setText("Restaurant management");
        lblTagline1.setFont(new java.awt.Font("Segoe UI", 0, 17));
        lblTagline1.setForeground(new java.awt.Color(255, 232, 214));

        lblTagline2.setText("and knowledge analysis.");
        lblTagline2.setFont(new java.awt.Font("Segoe UI", 0, 17));
        lblTagline2.setForeground(new java.awt.Color(255, 232, 214));

        pnlRight.setBackground(new java.awt.Color(214, 223, 234));
        pnlRight.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 90, 30, 90));
        pnlRight.setLayout(new java.awt.BorderLayout());

        pnlForm.setBackground(new java.awt.Color(214, 223, 234));
        pnlForm.setPreferredSize(new java.awt.Dimension(420, 640));
        pnlForm.setLayout(new java.awt.GridLayout(0, 1, 0, 3));

        lblTitle.setText("Create your account");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 30));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblInfo.setText("New accounts start as Staff. An administrator can change your level later.");
        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblInfo.setForeground(new java.awt.Color(63, 75, 95));

        lblError.setText(" ");
        lblError.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblError.setForeground(new java.awt.Color(220, 38, 38));

        lblFullName.setText("Full name");
        lblFullName.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblFullName.setForeground(new java.awt.Color(63, 75, 95));
        lblFullName.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 2, 0, 0));

        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblUsername.setText("Username (letters, numbers, dot, underscore)");
        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblUsername.setForeground(new java.awt.Color(63, 75, 95));
        lblUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 2, 0, 0));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblPassword.setText("Password (8+ characters, letters and numbers)");
        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPassword.setForeground(new java.awt.Color(63, 75, 95));
        lblPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 2, 0, 0));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblConfirm.setText("Confirm password");
        lblConfirm.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblConfirm.setForeground(new java.awt.Color(63, 75, 95));
        lblConfirm.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 2, 0, 0));

        txtConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

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

        btnCreate.setText("Create account");
        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 15));
        btnCreate.setBackground(new java.awt.Color(214, 110, 46));
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        btnCreate.setContentAreaFilled(false);
        btnCreate.setBorderPainted(false);
        btnCreate.setFocusPainted(false);
        btnCreate.setOpaque(true);
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnBack.setText("Back to sign in");
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setForeground(new java.awt.Color(214, 110, 46));
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setOpaque(true);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        pnlBrandText.add(lblLogo);
        pnlBrandText.add(lblBrand1);
        pnlBrandText.add(lblBrand2);
        pnlBrandText.add(lblTagline1);
        pnlBrandText.add(lblTagline2);
        pnlBrand.add(pnlBrandText, java.awt.BorderLayout.NORTH);
        pnlForm.add(lblTitle);
        pnlForm.add(lblInfo);
        pnlForm.add(lblError);
        pnlForm.add(lblFullName);
        pnlForm.add(txtFullName);
        pnlForm.add(lblUsername);
        pnlForm.add(txtUsername);
        pnlForm.add(lblPassword);
        pnlForm.add(txtPassword);
        pnlForm.add(lblConfirm);
        pnlForm.add(txtConfirm);
        pnlForm.add(chkShow);
        pnlForm.add(btnCreate);
        pnlForm.add(btnBack);
        pnlRight.add(pnlForm, java.awt.BorderLayout.NORTH);
        pnlSplit.add(pnlBrand);
        pnlSplit.add(pnlRight);
        getContentPane().add(pnlTitleHost, java.awt.BorderLayout.NORTH);
        getContentPane().add(pnlSplit, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        final String full = txtFullName.getText().trim(), user = txtUsername.getText().trim(), pw = new String(txtPassword.getPassword());
        if (!pw.equals(new String(txtConfirm.getPassword()))) { Util.banner(lblError, "The two passwords do not match.", false); return; }
        btnCreate.setEnabled(false);
        Util.async(() -> { hiking.dao.UserDao.register(user, full, pw); return Boolean.TRUE; },
            ok -> { loginWindow.accountCreated(user); dispose(); },
            ex -> { btnCreate.setEnabled(true); Util.banner(lblError, Util.message(ex), false); });
    }//GEN-LAST:event_btnCreateActionPerformed

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        char c = chkShow.isSelected() ? (char) 0 : '\u2022';
        txtPassword.setEchoChar(c); txtConfirm.setEchoChar(c);
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed


    private LoginFrame loginWindow;

    /** Opens the sign-up window; the sign-in window is hidden meanwhile and shown again when this one closes. */
    public SignupFrame(LoginFrame loginWindow) {
        this();
        this.loginWindow = loginWindow;
        addWindowListener(new java.awt.event.WindowAdapter() { @Override public void windowClosed(java.awt.event.WindowEvent e) { SignupFrame.this.loginWindow.setVisible(true); } });
    }

    private void postInit() {
        TitleBar titleBar = new TitleBar(this, false);
        pnlTitleHost.add(titleBar, java.awt.BorderLayout.CENTER);
        getContentPane().setBackground(Theme.BG);
        txtPassword.setEchoChar('•'); txtConfirm.setEchoChar('•');

        // ---- left: beige panel with the design's shapes + logo; right: form on blue-grey ----
        if (Brand.image() != null) setIconImage(Brand.image());
        pnlSplit.removeAll(); pnlSplit.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints sc = new java.awt.GridBagConstraints(); sc.fill = java.awt.GridBagConstraints.BOTH; sc.weighty = 1;
        AuthSide side = new AuthSide("Restaurant management, inventory, point of sale\nand sales forecasting, all in one place.");
        side.setPreferredSize(new java.awt.Dimension(1, 1)); pnlRight.setPreferredSize(new java.awt.Dimension(1, 1));
        sc.gridx = 0; sc.weightx = 0.36; pnlSplit.add(side, sc);
        sc.gridx = 1; sc.weightx = 0.64; pnlSplit.add(pnlRight, sc);
        pnlRight.removeAll(); pnlRight.setLayout(new java.awt.GridBagLayout()); pnlRight.setBackground(Theme.BG); pnlRight.setBorder(null);
        pnlForm.setOpaque(false); pnlForm.setPreferredSize(new java.awt.Dimension(640, 680)); pnlRight.add(pnlForm);

        Util.clearBanner(lblError); Util.skin(this);

        // ---- form: fixed-height rows (numbers are pixel heights) ----
        pnlForm.removeAll();
        pnlForm.setLayout(new javax.swing.BoxLayout(pnlForm, javax.swing.BoxLayout.Y_AXIS));
        pnlForm.add(javax.swing.Box.createVerticalGlue());
        lblTitle.setText("Create Your Account"); lblTitle.setFont(Theme.bold(42f)); lblInfo.setText("Fill in Account Details to get started."); lblInfo.setFont(Theme.plain(17f)); lblInfo.setForeground(Theme.TEXT);
        String[] names = {"Full Name", "Username", "Password", "Confirm Password"};
        javax.swing.JLabel[] lbls = {lblFullName, lblUsername, lblPassword, lblConfirm};
        javax.swing.JTextField[] fields = {txtFullName, txtUsername, txtPassword, txtConfirm};
        for (int i = 0; i < 4; i++) {
            lbls[i].setText("<html>" + names[i] + "<font color='#E00000'>*</font></html>"); lbls[i].setFont(Theme.plain(20f)); lbls[i].setForeground(Theme.TEXT);
            fields[i].putClientProperty("placeholder", "Enter your " + names[i]); fields[i].setFont(Theme.plain(17f));
        }
        chkShow.setFont(Theme.plain(16f)); chkShow.setForeground(Theme.TEXT);
        btnCreate.setText("Create Account"); btnCreate.setFont(Theme.plain(24f));
        btnBack.setText("Already have an account?   Log-in"); btnBack.setFont(Theme.plain(18f)); btnBack.setBackground(new java.awt.Color(0, 0, 0, 0)); btnBack.setForeground(Theme.PRIMARY);
        formRow(lblTitle, 56, 0);
        formRow(lblInfo, 28, 0);
        formRow(lblError, 36, 4);
        formRow(lblFullName, 30, 8); formRow(txtFullName, 54, 0);
        formRow(lblUsername, 30, 8); formRow(txtUsername, 54, 0);
        formRow(lblPassword, 30, 8); formRow(txtPassword, 54, 0);
        formRow(lblConfirm, 30, 8); formRow(txtConfirm, 54, 0);
        formRow(chkShow, 34, 8);
        formCenter(btnCreate, 250, 58, 16);
        formCenter(btnBack, 440, 42, 12);
        pnlForm.add(javax.swing.Box.createVerticalGlue());
        for (javax.swing.JLabel l : lbls) l.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        titleBar.maximize();
        txtFullName.requestFocusInWindow();
    }

    private void formCenter(javax.swing.JComponent c, int w, int h, int gapBefore) {
        if (gapBefore > 0) pnlForm.add(javax.swing.Box.createVerticalStrut(gapBefore));
        c.setAlignmentX(0.5f); c.setPreferredSize(new java.awt.Dimension(w, h)); c.setMinimumSize(new java.awt.Dimension(w, h)); c.setMaximumSize(new java.awt.Dimension(w, h)); pnlForm.add(c);
    }

    /** One row of the form: fixed height, full width, optional gap above. */
    private void formRow(javax.swing.JComponent c, int h, int gapBefore) {
        if (gapBefore > 0) pnlForm.add(javax.swing.Box.createVerticalStrut(gapBefore));
        c.setAlignmentX(0.5f);
        c.setPreferredSize(new java.awt.Dimension(100, h));
        c.setMinimumSize(new java.awt.Dimension(0, h));
        c.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, h));
        pnlForm.add(c);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTitleHost;

    private javax.swing.JPanel pnlSplit;

    private javax.swing.JPanel pnlBrand;

    private javax.swing.JPanel pnlBrandText;

    private javax.swing.JLabel lblLogo;

    private javax.swing.JLabel lblBrand1;

    private javax.swing.JLabel lblBrand2;

    private javax.swing.JLabel lblTagline1;

    private javax.swing.JLabel lblTagline2;

    private javax.swing.JPanel pnlRight;

    private javax.swing.JPanel pnlForm;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblInfo;

    private javax.swing.JLabel lblError;

    private javax.swing.JLabel lblFullName;

    private javax.swing.JTextField txtFullName;

    private javax.swing.JLabel lblUsername;

    private javax.swing.JTextField txtUsername;

    private javax.swing.JLabel lblPassword;

    private javax.swing.JPasswordField txtPassword;

    private javax.swing.JLabel lblConfirm;

    private javax.swing.JPasswordField txtConfirm;

    private javax.swing.JCheckBox chkShow;

    private javax.swing.JButton btnCreate;

    private javax.swing.JButton btnBack;

    // End of variables declaration//GEN-END:variables
}
