package hiking.ui;

/**
 * Sign-in window. Open it in the Design tab to change the layout, texts, colours and fonts.
 */
public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
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
        lblWelcome = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        lblBanner = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        chkShow = new javax.swing.JCheckBox();
        btnSignIn = new javax.swing.JButton();
        btnSignUp = new javax.swing.JButton();
        lblNote = new javax.swing.JLabel();

        setDefaultCloseOperation(3);
        setTitle("1/2 HI KING CORP - Restaurant Management & Knowledge Analysis");
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
        pnlRight.setBorder(javax.swing.BorderFactory.createEmptyBorder(110, 90, 40, 90));
        pnlRight.setLayout(new java.awt.BorderLayout());

        pnlForm.setBackground(new java.awt.Color(214, 223, 234));
        pnlForm.setPreferredSize(new java.awt.Dimension(420, 616));
        pnlForm.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        lblWelcome.setText("Welcome back");
        lblWelcome.setFont(new java.awt.Font("Segoe UI", 1, 30));
        lblWelcome.setForeground(new java.awt.Color(31, 41, 55));

        lblSub.setText("Log in to your account to continue");
        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lblSub.setForeground(new java.awt.Color(63, 75, 95));

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));

        lblUsername.setText("Username");
        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblUsername.setForeground(new java.awt.Color(63, 75, 95));
        lblUsername.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 0, 0));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        lblPassword.setText("Password");
        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPassword.setForeground(new java.awt.Color(63, 75, 95));
        lblPassword.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 0, 0));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });

        chkShow.setText("Show password");
        chkShow.setFont(new java.awt.Font("Segoe UI", 0, 14));
        chkShow.setForeground(new java.awt.Color(31, 41, 55));
        chkShow.setOpaque(false);
        chkShow.setFocusPainted(false);
        chkShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkShowActionPerformed(evt);
            }
        });

        btnSignIn.setText("Log In");
        btnSignIn.setFont(new java.awt.Font("Segoe UI", 1, 15));
        btnSignIn.setBackground(new java.awt.Color(214, 110, 46));
        btnSignIn.setForeground(new java.awt.Color(255, 255, 255));
        btnSignIn.setContentAreaFilled(false);
        btnSignIn.setBorderPainted(false);
        btnSignIn.setFocusPainted(false);
        btnSignIn.setOpaque(true);
        btnSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignInActionPerformed(evt);
            }
        });

        btnSignUp.setText("New here? Create an account");
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSignUp.setBackground(new java.awt.Color(255, 255, 255));
        btnSignUp.setForeground(new java.awt.Color(214, 110, 46));
        btnSignUp.setContentAreaFilled(false);
        btnSignUp.setBorderPainted(false);
        btnSignUp.setFocusPainted(false);
        btnSignUp.setOpaque(true);
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        lblNote.setText("Authorized personnel only. All sign-ins are logged.");
        lblNote.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblNote.setForeground(new java.awt.Color(63, 75, 95));
        lblNote.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 0, 0, 0));

        pnlBrandText.add(lblLogo);
        pnlBrandText.add(lblBrand1);
        pnlBrandText.add(lblBrand2);
        pnlBrandText.add(lblTagline1);
        pnlBrandText.add(lblTagline2);
        pnlBrand.add(pnlBrandText, java.awt.BorderLayout.NORTH);
        pnlForm.add(lblWelcome);
        pnlForm.add(lblSub);
        pnlForm.add(lblBanner);
        pnlForm.add(lblUsername);
        pnlForm.add(txtUsername);
        pnlForm.add(lblPassword);
        pnlForm.add(txtPassword);
        pnlForm.add(chkShow);
        pnlForm.add(btnSignIn);
        pnlForm.add(btnSignUp);
        pnlForm.add(lblNote);
        pnlRight.add(pnlForm, java.awt.BorderLayout.NORTH);
        pnlSplit.add(pnlBrand);
        pnlSplit.add(pnlRight);
        getContentPane().add(pnlTitleHost, java.awt.BorderLayout.NORTH);
        getContentPane().add(pnlSplit, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        txtPassword.requestFocusInWindow();
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignInActionPerformed
        if (!ready) return;
        final String u = txtUsername.getText().trim(), p = new String(txtPassword.getPassword());
        if (u.isEmpty() || p.isEmpty()) { Util.banner(lblBanner, "Enter your username and password.", false); return; }
        btnSignIn.setEnabled(false); btnSignIn.setText("Logging in...");
        Util.async(() -> hiking.dao.UserDao.authenticate(u, p), res -> {
            btnSignIn.setEnabled(true); btnSignIn.setText("Log In");
            if (res.user == null) { Util.banner(lblBanner, res.error, false); txtPassword.setText(""); txtPassword.requestFocusInWindow(); }
            else { txtPassword.setText(""); hiking.security.Session.start(res.user); new MainFrame(res.user).setVisible(true); dispose(); }
        }, ex -> { btnSignIn.setEnabled(true); btnSignIn.setText("Log In"); Util.banner(lblBanner, Util.message(ex), false); });
    }//GEN-LAST:event_btnSignInActionPerformed

    private void chkShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkShowActionPerformed
        txtPassword.setEchoChar(chkShow.isSelected() ? (char) 0 : '\u2022');
    }//GEN-LAST:event_chkShowActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        if (!ready) return;
        setVisible(false);
        new SignupFrame(this).setVisible(true);
    }//GEN-LAST:event_btnSignUpActionPerformed


    private TitleBar titleBar;
    private boolean ready;

    private void postInit() {
        if (Brand.image() != null) setIconImage(Brand.image());
        // left: beige panel with the design's shapes, headline and the company logo; right: the form on blue-grey
        pnlSplit.removeAll(); pnlSplit.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints sc = new java.awt.GridBagConstraints(); sc.fill = java.awt.GridBagConstraints.BOTH; sc.weighty = 1;
        AuthSide side = new AuthSide("Restaurant management, inventory, point of sale\nand sales forecasting, all in one place.");
        side.setPreferredSize(new java.awt.Dimension(1, 1)); pnlRight.setPreferredSize(new java.awt.Dimension(1, 1));
        sc.gridx = 0; sc.weightx = 0.36; pnlSplit.add(side, sc);
        sc.gridx = 1; sc.weightx = 0.64; pnlSplit.add(pnlRight, sc);
        pnlRight.removeAll(); pnlRight.setLayout(new java.awt.GridBagLayout()); pnlRight.setBackground(Theme.BG); pnlRight.setBorder(null);
        pnlForm.setOpaque(false); pnlForm.setPreferredSize(new java.awt.Dimension(640, 600)); pnlRight.add(pnlForm);
        titleBar = new TitleBar(this, false);
        pnlTitleHost.add(titleBar, java.awt.BorderLayout.CENTER);
        getContentPane().setBackground(Theme.BG);
        Util.clearBanner(lblBanner);
        txtPassword.setEchoChar('\u2022');
        Util.skin(this);
        pnlForm.removeAll();
        pnlForm.setLayout(new javax.swing.BoxLayout(pnlForm, javax.swing.BoxLayout.Y_AXIS));
        pnlForm.add(javax.swing.Box.createVerticalGlue());
        lblWelcome.setText("Welcome Back!"); lblWelcome.setFont(Theme.bold(46f)); lblSub.setFont(Theme.plain(18f)); lblSub.setForeground(Theme.TEXT);
        txtUsername.putClientProperty("placeholder", "Enter your Username"); txtPassword.putClientProperty("placeholder", "Enter your Password");
        txtUsername.setFont(Theme.plain(18f)); txtPassword.setFont(Theme.plain(18f)); chkShow.setFont(Theme.plain(16f)); chkShow.setForeground(Theme.TEXT);
        btnSignIn.setText("Log In"); btnSignIn.setFont(Theme.plain(26f));
        btnSignUp.setText("Don't have an account?   Sign-Up"); btnSignUp.setFont(Theme.plain(18f)); btnSignUp.setBackground(new java.awt.Color(0, 0, 0, 0)); btnSignUp.setForeground(Theme.PRIMARY);
        lblNote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); lblNote.setForeground(Theme.MUTED);
        formRow(lblWelcome, 62, 0);
        formRow(lblSub, 30, 0);
        formRow(lblBanner, 40, 6);
        formRow(txtUsername, 62, 34);
        formRow(txtPassword, 62, 22);
        formRow(chkShow, 36, 10);
        formCenter(btnSignIn, 210, 62, 22);
        formCenter(btnSignUp, 420, 44, 22);
        formRow(lblNote, 30, 6);
        pnlForm.add(javax.swing.Box.createVerticalGlue());
        titleBar.maximize();
        btnSignIn.setEnabled(false);
        lblSub.setText("Connecting to the database...");
        connect();
        txtUsername.requestFocusInWindow();
    }


    /** A centred, fixed-size row (the Log In button and the sign-up link). */
    private void formCenter(javax.swing.JComponent c, int w, int h, int gapBefore) {
        if (gapBefore > 0) pnlForm.add(javax.swing.Box.createVerticalStrut(gapBefore));
        c.setAlignmentX(0.5f); c.setPreferredSize(new java.awt.Dimension(w, h)); c.setMinimumSize(new java.awt.Dimension(w, h)); c.setMaximumSize(new java.awt.Dimension(w, h)); pnlForm.add(c);
    }

    /** One row of the sign-in form: fixed height, full width, optional gap above. */
    private void formRow(javax.swing.JComponent c, int h, int gapBefore) {
        if (gapBefore > 0) pnlForm.add(javax.swing.Box.createVerticalStrut(gapBefore));
        c.setAlignmentX(0.5f);
        c.setPreferredSize(new java.awt.Dimension(100, h));
        c.setMinimumSize(new java.awt.Dimension(0, h));
        c.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, h));
        pnlForm.add(c);
    }

    /** Connects to MySQL (creating and seeding the database on first run), then loads the saved prediction models in the background. */
    private void connect() {
        Util.async(() -> { hiking.db.Database.init((m, f) -> javax.swing.SwingUtilities.invokeLater(() -> lblSub.setText(m))); return Boolean.TRUE; },
            ok -> {
                ready = true; btnSignIn.setEnabled(true); lblSub.setText("Log in to your account to continue");
                Thread t = new Thread(() -> { try { hiking.ml.AnalysisService.get().ensureReady(null); } catch (Exception ex) { System.err.println("Model loading failed: " + ex); } }, "model-loader");
                t.setDaemon(true); t.start();
            },
            ex -> {
                lblSub.setText("The database is not reachable.");
                DbSetupDialog dlg = new DbSetupDialog(this, true, Util.message(ex));
                dlg.setVisible(true);
                if (dlg.saved) connect();
            });
    }

    /** Called by the sign-up window after it created an account. */
    public void accountCreated(String username) {
        txtUsername.setText(username); txtPassword.setText("");
        Util.banner(lblBanner, "Account created. You can sign in now as Staff.", true);
        txtPassword.requestFocusInWindow();
    }

    /** Called after a successful sign-out / idle timeout to show a message. */
    public void showMessage(String msg) { Util.banner(lblBanner, msg, false); }

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

    private javax.swing.JLabel lblWelcome;

    private javax.swing.JLabel lblSub;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JLabel lblUsername;

    private javax.swing.JTextField txtUsername;

    private javax.swing.JLabel lblPassword;

    private javax.swing.JPasswordField txtPassword;

    private javax.swing.JCheckBox chkShow;

    private javax.swing.JButton btnSignIn;

    private javax.swing.JButton btnSignUp;

    private javax.swing.JLabel lblNote;

    // End of variables declaration//GEN-END:variables
}
