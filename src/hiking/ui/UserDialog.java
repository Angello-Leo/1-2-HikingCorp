package hiking.ui;

public class UserDialog extends javax.swing.JDialog {

    public UserDialog(java.awt.Frame parent, boolean modal, hiking.model.User u) {
        super(parent, modal);
        initComponents();
        this.editing = u;
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlBody = new javax.swing.JPanel();
        pnlHead = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblBanner = new javax.swing.JLabel();
        pnlFields = new javax.swing.JPanel();
        pnlUsername = new javax.swing.JPanel();
        lblUsernameKey = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        pnlFullName = new javax.swing.JPanel();
        lblFullNameKey = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        pnlRole = new javax.swing.JPanel();
        lblRoleKey = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        pnlStatus = new javax.swing.JPanel();
        lblStatusKey = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        pnlPassword = new javax.swing.JPanel();
        lblPasswordKey = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        pnlButtons = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(2);
        setTitle("User");
        setResizable(false);

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 16, 22));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlHead.setOpaque(false);
        pnlHead.setLayout(new java.awt.BorderLayout());

        lblTitle.setText("Add user");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 17));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblBanner.setText(" ");
        lblBanner.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblBanner.setForeground(new java.awt.Color(220, 38, 38));
        lblBanner.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 0, 6, 0));

        pnlFields.setOpaque(false);
        pnlFields.setLayout(new java.awt.GridLayout(5, 1, 0, 2));

        pnlUsername.setOpaque(false);
        pnlUsername.setLayout(new java.awt.BorderLayout());

        lblUsernameKey.setText("USERNAME");
        lblUsernameKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblUsernameKey.setForeground(new java.awt.Color(63, 75, 95));
        lblUsernameKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtUsername.setPreferredSize(new java.awt.Dimension(340, 40));

        pnlFullName.setOpaque(false);
        pnlFullName.setLayout(new java.awt.BorderLayout());

        lblFullNameKey.setText("FULL NAME");
        lblFullNameKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblFullNameKey.setForeground(new java.awt.Color(63, 75, 95));
        lblFullNameKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtFullName.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtFullName.setPreferredSize(new java.awt.Dimension(340, 40));

        pnlRole.setOpaque(false);
        pnlRole.setLayout(new java.awt.BorderLayout());

        lblRoleKey.setText("USER LEVEL");
        lblRoleKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblRoleKey.setForeground(new java.awt.Color(63, 75, 95));
        lblRoleKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        cmbRole.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Inventory Clerk", "Sales Personnel", "Instructor", "Staff" }));
        cmbRole.setPreferredSize(new java.awt.Dimension(340, 40));

        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new java.awt.BorderLayout());

        lblStatusKey.setText("STATUS");
        lblStatusKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblStatusKey.setForeground(new java.awt.Color(63, 75, 95));
        lblStatusKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        cmbStatus.setFont(new java.awt.Font("Segoe UI", 0, 14));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Disabled" }));
        cmbStatus.setPreferredSize(new java.awt.Dimension(340, 40));

        pnlPassword.setOpaque(false);
        pnlPassword.setLayout(new java.awt.BorderLayout());

        lblPasswordKey.setText("PASSWORD (8+ characters, letters and numbers)");
        lblPasswordKey.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblPasswordKey.setForeground(new java.awt.Color(63, 75, 95));
        lblPasswordKey.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 2, 3, 0));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtPassword.setPreferredSize(new java.awt.Dimension(340, 40));

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

        btnSave.setText("Save");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnSave.setBackground(new java.awt.Color(214, 110, 46));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setOpaque(true);
        btnSave.setPreferredSize(new java.awt.Dimension(130, 38));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        pnlHead.add(lblTitle, java.awt.BorderLayout.NORTH);
        pnlHead.add(lblBanner, java.awt.BorderLayout.SOUTH);
        pnlUsername.add(lblUsernameKey, java.awt.BorderLayout.NORTH);
        pnlUsername.add(txtUsername, java.awt.BorderLayout.CENTER);
        pnlFullName.add(lblFullNameKey, java.awt.BorderLayout.NORTH);
        pnlFullName.add(txtFullName, java.awt.BorderLayout.CENTER);
        pnlRole.add(lblRoleKey, java.awt.BorderLayout.NORTH);
        pnlRole.add(cmbRole, java.awt.BorderLayout.CENTER);
        pnlStatus.add(lblStatusKey, java.awt.BorderLayout.NORTH);
        pnlStatus.add(cmbStatus, java.awt.BorderLayout.CENTER);
        pnlPassword.add(lblPasswordKey, java.awt.BorderLayout.NORTH);
        pnlPassword.add(txtPassword, java.awt.BorderLayout.CENTER);
        pnlFields.add(pnlUsername);
        pnlFields.add(pnlFullName);
        pnlFields.add(pnlRole);
        pnlFields.add(pnlStatus);
        pnlFields.add(pnlPassword);
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
            hiking.model.Role role = hiking.model.Role.values()[cmbRole.getSelectedIndex()];
            if (editing == null) hiking.dao.UserDao.create(txtUsername.getText(), txtFullName.getText(), role, new String(txtPassword.getPassword()));
            else hiking.dao.UserDao.update(editing.id, txtFullName.getText(), role, cmbStatus.getSelectedIndex() == 0);
            saved = true;
            dispose();
        } catch (Exception ex) {
            Util.banner(lblBanner, Util.message(ex), false);
            pack();
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    /** True when the user pressed Save and it worked. */
    public boolean saved;
    private hiking.model.User editing;

    private void postInit() {
        getContentPane().setBackground(java.awt.Color.WHITE);
        Util.clearBanner(lblBanner);
        if (editing == null) {
            lblTitle.setText("Add user"); btnSave.setText("Create user");
        } else {
            lblTitle.setText("Edit " + editing.username); btnSave.setText("Save");
            txtUsername.setText(editing.username); txtUsername.setEnabled(false);
            txtFullName.setText(editing.fullName);
            cmbRole.setSelectedIndex(editing.role.ordinal());
            cmbStatus.setSelectedIndex(editing.active ? 0 : 1);
            pnlPassword.setVisible(false);
        }
        pnlStatus.setVisible(editing != null);
        Util.skin(this);
        getRootPane().setDefaultButton(btnSave);
        pack();
        setLocationRelativeTo(getParent());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlHead;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlFields;

    private javax.swing.JPanel pnlUsername;

    private javax.swing.JLabel lblUsernameKey;

    private javax.swing.JTextField txtUsername;

    private javax.swing.JPanel pnlFullName;

    private javax.swing.JLabel lblFullNameKey;

    private javax.swing.JTextField txtFullName;

    private javax.swing.JPanel pnlRole;

    private javax.swing.JLabel lblRoleKey;

    private javax.swing.JComboBox<String> cmbRole;

    private javax.swing.JPanel pnlStatus;

    private javax.swing.JLabel lblStatusKey;

    private javax.swing.JComboBox<String> cmbStatus;

    private javax.swing.JPanel pnlPassword;

    private javax.swing.JLabel lblPasswordKey;

    private javax.swing.JPasswordField txtPassword;

    private javax.swing.JPanel pnlButtons;

    private javax.swing.JButton btnCancel;

    private javax.swing.JButton btnSave;

    // End of variables declaration//GEN-END:variables
}
