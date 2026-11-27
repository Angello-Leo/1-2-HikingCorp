package hiking.ui;

public class DbSetupDialog extends javax.swing.JDialog {

    public DbSetupDialog(java.awt.Frame parent, boolean modal, String error) {
        super(parent, modal);
        initComponents();
        postInit(); if (error != null) Util.banner(lblError, error, false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlHeadText = new javax.swing.JPanel();
        lblMsg = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        lblHost = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        lblPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lblDb = new javax.swing.JLabel();
        txtDb = new javax.swing.JTextField();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("Database connection");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(214, 223, 234));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 18, 26));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlHead.setBackground(new java.awt.Color(214, 223, 234));
        pnlHead.setLayout(new java.awt.BorderLayout());

        lblTitle.setText("MySQL connection");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));
        lblTitle.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        pnlHeadText.setBackground(new java.awt.Color(214, 223, 234));
        pnlHeadText.setLayout(new java.awt.BorderLayout());

        lblMsg.setText("<html><body style=\"width:420px\">The app could not reach the database. Check that MySQL is running, then confirm the settings.</body></html>");
        lblMsg.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblMsg.setForeground(new java.awt.Color(63, 75, 95));
        lblMsg.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 8, 0));

        lblError.setText(" ");
        lblError.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblError.setForeground(new java.awt.Color(220, 38, 38));

        pnlFields.setBackground(new java.awt.Color(214, 223, 234));
        pnlFields.setPreferredSize(new java.awt.Dimension(460, 420));
        pnlFields.setLayout(new java.awt.GridLayout(0, 1, 0, 4));

        lblHost.setText("Host");
        lblHost.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblHost.setForeground(new java.awt.Color(63, 75, 95));

        txtHost.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblPort.setText("Port");
        lblPort.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPort.setForeground(new java.awt.Color(63, 75, 95));

        txtPort.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblUser.setText("User");
        lblUser.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblUser.setForeground(new java.awt.Color(63, 75, 95));

        txtUser.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblPass.setText("Password");
        lblPass.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblPass.setForeground(new java.awt.Color(63, 75, 95));

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 14));

        lblDb.setText("Database name (created automatically)");
        lblDb.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblDb.setForeground(new java.awt.Color(63, 75, 95));

        txtDb.setFont(new java.awt.Font("Segoe UI", 0, 14));

        pnlButtons.setBackground(new java.awt.Color(214, 223, 234));
        pnlButtons.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        pnlButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        btnCancel.setText("Quit");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnCancel.setBackground(new java.awt.Color(232, 216, 198));
        btnCancel.setForeground(new java.awt.Color(31, 41, 55));
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

        btnSave.setText("Save & connect");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSave.setBackground(new java.awt.Color(214, 110, 46));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        btnSave.setPreferredSize(new java.awt.Dimension(150, 38));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        pnlHeadText.add(lblMsg, java.awt.BorderLayout.NORTH);
        pnlHeadText.add(lblError, java.awt.BorderLayout.SOUTH);
        pnlHead.add(lblTitle, java.awt.BorderLayout.NORTH);
        pnlHead.add(pnlHeadText, java.awt.BorderLayout.CENTER);
        pnlFields.add(lblHost);
        pnlFields.add(txtHost);
        pnlFields.add(lblPort);
        pnlFields.add(txtPort);
        pnlFields.add(lblUser);
        pnlFields.add(txtUser);
        pnlFields.add(lblPass);
        pnlFields.add(txtPass);
        pnlFields.add(lblDb);
        pnlFields.add(txtDb);
        pnlButtons.add(btnCancel);
        pnlButtons.add(btnSave);
        pnlBody.add(pnlHead, java.awt.BorderLayout.NORTH);
        pnlBody.add(pnlFields, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlButtons, java.awt.BorderLayout.SOUTH);
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            hiking.db.Database.save(txtHost.getText(), txtPort.getText(), txtUser.getText(), new String(txtPass.getPassword()), txtDb.getText());
            saved = true; dispose();
        } catch (Exception ex) { Util.banner(lblError, String.valueOf(ex.getMessage()), false); }
    }//GEN-LAST:event_btnSaveActionPerformed


    public boolean saved;

    private void postInit() {
        getContentPane().setBackground(Theme.BG);
        txtHost.setText(hiking.db.Database.get("host")); txtPort.setText(hiking.db.Database.get("port")); txtUser.setText(hiking.db.Database.get("user"));
        txtPass.setText(hiking.db.Database.get("password")); txtDb.setText(hiking.db.Database.get("database"));
        Util.skin(this); pack(); setLocationRelativeTo(getParent());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JPanel pnlHeadText;

    private javax.swing.JLabel lblMsg;

    private javax.swing.JLabel lblError;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JLabel lblHost;

    private javax.swing.JTextField txtHost;

    private javax.swing.JLabel lblPort;

    private javax.swing.JTextField txtPort;

    private javax.swing.JLabel lblUser;

    private javax.swing.JTextField txtUser;

    private javax.swing.JLabel lblPass;

    private javax.swing.JPasswordField txtPass;

    private javax.swing.JLabel lblDb;

    private javax.swing.JTextField txtDb;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnCancel;

    private javax.swing.JButton btnSave;

    // End of variables declaration//GEN-END:variables
}
