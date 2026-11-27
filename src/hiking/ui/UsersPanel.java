package hiking.ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Pattern;

public class UsersPanel extends javax.swing.JPanel implements hiking.ui.PageView {

    private static final String SEARCH_PLACEHOLDER = "Search username, name, role, or status...";
    private static final Color PLACEHOLDER_COLOR = new Color(148, 163, 184);
    private static final Color TEXT_COLOR = new Color(31, 41, 55);
    private javax.swing.JTextField txtSearch;

    private TableRowSorter<DefaultTableModel> sorter;
    
    public UsersPanel() {
        initComponents();
        postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblBanner = new javax.swing.JLabel();
        pnlToolbar = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        pnlCard = new javax.swing.JPanel();
        scrUsers = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();

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

        btnAdd.setText("+  Add user");
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnAdd.setBackground(new java.awt.Color(214, 110, 46));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setContentAreaFilled(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setFocusPainted(false);
        btnAdd.setOpaque(true);
        btnAdd.setPreferredSize(new java.awt.Dimension(130, 40));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setForeground(new java.awt.Color(214, 110, 46));
        btnEdit.setContentAreaFilled(false);
        btnEdit.setBorderPainted(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setOpaque(true);
        btnEdit.setPreferredSize(new java.awt.Dimension(90, 40));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnReset.setText("Reset password");
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setForeground(new java.awt.Color(214, 110, 46));
        btnReset.setContentAreaFilled(false);
        btnReset.setBorderPainted(false);
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.setPreferredSize(new java.awt.Dimension(150, 40));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete user");
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnDelete.setBackground(new java.awt.Color(220, 38, 38));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setOpaque(true);
        btnDelete.setPreferredSize(new java.awt.Dimension(120, 40));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 13));
        btnRefresh.setBackground(new java.awt.Color(232, 216, 198));
        btnRefresh.setForeground(new java.awt.Color(31, 41, 55));
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


        tblUsers.setFont(new java.awt.Font("Segoe UI", 0, 13));
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Full name", "User level", "Status", "Last sign-in", "Created"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsersMouseClicked(evt);
            }
        });

        pnlToolbar.add(btnAdd);
        pnlToolbar.add(btnEdit);
        pnlToolbar.add(btnReset);
        pnlToolbar.add(btnDelete);
        pnlToolbar.add(btnRefresh);
        pnlTop.add(lblBanner, java.awt.BorderLayout.NORTH);
        pnlTop.add(pnlToolbar, java.awt.BorderLayout.CENTER);
        scrUsers.setViewportView(tblUsers);
        pnlCard.add(scrUsers, java.awt.BorderLayout.CENTER);
        add(pnlTop, java.awt.BorderLayout.NORTH);
        add(pnlCard, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        UserDialog d = new UserDialog(Util.frame(this), true, null);
        d.setVisible(true);
        if (d.saved) { Util.banner(lblBanner, "User created.", true); onShow(); }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        hiking.model.User u = selected();
        if (u != null) editUser(u);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        hiking.model.User u = selected();
        if (u == null) return;
        PasswordDialog d = new PasswordDialog(Util.frame(this), true, u);
        d.setVisible(true);
        if (d.saved) Util.banner(lblBanner, "Password reset for " + u.username + ".", true);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        final hiking.model.User u = selected();
        if (u == null) return;

        javax.swing.JPasswordField pwdField = new javax.swing.JPasswordField(20);
        Object[] message = {
            "Permanently delete the account '" + u.username + "' (" + u.fullName + ")?\n"
                + "This cannot be undone. To keep their history, disable the account instead.\n\n"
                + "Enter your password to confirm:",
            pwdField
        };

        int choice = javax.swing.JOptionPane.showConfirmDialog(
            Util.frame(this),
            message,
            "Confirm Delete User",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
        if (choice != javax.swing.JOptionPane.OK_OPTION) {
            return;
        }

        char[] entered = pwdField.getPassword();
        if (entered.length == 0) {
            java.util.Arrays.fill(entered, '\0');
            Util.banner(lblBanner, "Password is required to delete a user.", false);
            return;
        }

        final String pwd = new String(entered);
        java.util.Arrays.fill(entered, '\0');

        final String currentUsername = resolveCurrentUsername();
        if (currentUsername == null || currentUsername.isBlank()) {
            Util.banner(lblBanner, "No active session user found.", false);
            return;
        }

        Util.async(
            () -> {
                if (!isDeletePasswordValid(currentUsername, pwd)) {
                    return Boolean.FALSE;
                }
                hiking.dao.UserDao.delete(u.id);
                return Boolean.TRUE;
            },
            ok -> {
                if (!Boolean.TRUE.equals(ok)) {
                    Util.banner(lblBanner, "Incorrect password. Deletion cancelled.", false);
                    return;
                }
                Util.banner(lblBanner, "User '" + u.username + "' deleted.", true);
                onShow();
            },
            ex -> Util.banner(lblBanner, Util.message(ex), false)
        );
    }//GEN-LAST:event_btnDeleteActionPerformed

    private boolean isDeletePasswordValid(String username, String password) throws java.sql.SQLException {
        var auth = hiking.dao.UserDao.authenticate(username, password);
        return auth != null && auth.user != null;
    }
    
    private String resolveCurrentUsername() {
        try {
            Class<?> cls = Class.forName("hiking.security.Session");
            for (String methodName : new String[]{"get", "current", "currentUser", "getUser", "user"}) {
                try {
                    java.lang.reflect.Method m = cls.getMethod(methodName);
                    Object val = m.invoke(null);
                    String extracted = extractUsername(val);
                    if (extracted != null) return extracted;
                } catch (NoSuchMethodException ignored) {
                }
            }
            for (String fieldName : new String[]{"currentUser", "user", "CURRENT_USER"}) {
                try {
                    java.lang.reflect.Field f = cls.getField(fieldName);
                    Object val = f.get(null);
                    String extracted = extractUsername(val);
                    if (extracted != null) return extracted;
                } catch (NoSuchFieldException ignored) {
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private String extractUsername(Object obj) {
        if (obj == null) return null;
        if (obj instanceof hiking.model.User) {
            return ((hiking.model.User) obj).username;
        }
        try {
            java.lang.reflect.Field f = obj.getClass().getField("username");
            Object v = f.get(obj);
            return v != null ? v.toString() : null;
        } catch (Exception ignored) {
        }
        try {
            java.lang.reflect.Method m = obj.getClass().getMethod("getUsername");
            Object v = m.invoke(obj);
            return v != null ? v.toString() : null;
        } catch (Exception ignored) {
        }
        return null;
    }
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        onShow();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsersMouseClicked
        if (evt.getClickCount() == 2) {
            hiking.model.User u = selected();
            if (u != null) editUser(u);
        }
    }//GEN-LAST:event_tblUsersMouseClicked


    private java.util.List<hiking.model.User> users = new java.util.ArrayList<>();
    private static final java.time.format.DateTimeFormatter F = java.time.format.DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a");

    private void postInit() {
        Util.clearBanner(lblBanner);
        createSearchField();
        Util.skin(this);
        bindSearchFilter();
    }
    
    private void createSearchField() {
        txtSearch = new javax.swing.JTextField();
        txtSearch.setPreferredSize(new Dimension(280, 40));
        txtSearch.setToolTipText("Filter users by username, full name, role, or status");
        pnlToolbar.add(txtSearch);
        pnlToolbar.revalidate();
        pnlToolbar.repaint();
    }

    private void bindSearchFilter() {
        // Set initial placeholder after Util.skin(this) so skin colors don't hide placeholder state
        txtSearch.setForeground(PLACEHOLDER_COLOR);
        txtSearch.setText(SEARCH_PLACEHOLDER);

        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (isPlaceholderActive()) {
                    txtSearch.setText("");
                    txtSearch.setForeground(TEXT_COLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().trim().isEmpty()) {
                    txtSearch.setForeground(PLACEHOLDER_COLOR);
                    txtSearch.setText(SEARCH_PLACEHOLDER);
                }
            }
        });

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { applyFilter(); }
            @Override public void removeUpdate(DocumentEvent e) { applyFilter(); }
            @Override public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        applyFilter();
    }
    
    private boolean isPlaceholderActive() {
        return txtSearch != null
            && PLACEHOLDER_COLOR.equals(txtSearch.getForeground())
            && SEARCH_PLACEHOLDER.equals(txtSearch.getText());
    }

    private void applyFilter() {
        if (txtSearch == null || !(tblUsers.getModel() instanceof DefaultTableModel)) return;
        DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
        if (sorter == null || sorter.getModel() != model || tblUsers.getRowSorter() != sorter) {
            sorter = new TableRowSorter<>(model);
            tblUsers.setRowSorter(sorter);
        }

        String raw = isPlaceholderActive() ? "" : txtSearch.getText().trim();
        if (raw.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + Pattern.quote(raw)));
        }
    }

    @Override public String title() { return "User Management"; }
    @Override public String subtitle() { return "Create staff accounts and control their user level."; }
    @Override public void onShow() {
        Util.async(lblBanner, hiking.dao.UserDao::list, l -> {
            users = l;
            javax.swing.table.DefaultTableModel m = (javax.swing.table.DefaultTableModel) tblUsers.getModel(); m.setRowCount(0);
            for (hiking.model.User u : l) m.addRow(new Object[]{u.username, u.fullName, u.role.label, u.active ? "Active" : "Disabled",
                u.lastLogin == null ? "-" : u.lastLogin.format(F), u.createdAt == null ? "-" : u.createdAt.format(F)});
            applyFilter();
        });
    }
    private hiking.model.User selected() {
        int r = tblUsers.getSelectedRow();
        if (r < 0) { Util.banner(lblBanner, "Select a user first.", false); return null; }
        return users.get(tblUsers.convertRowIndexToModel(r));
    }
    private void editUser(hiking.model.User u) {
        UserDialog d = new UserDialog(Util.frame(this), true, u); d.setVisible(true);
        if (d.saved) { Util.banner(lblBanner, "User updated.", true); onShow(); }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTop;

    private javax.swing.JLabel lblBanner;

    private javax.swing.JPanel pnlToolbar;

    private javax.swing.JButton btnAdd;

    private javax.swing.JButton btnEdit;

    private javax.swing.JButton btnReset;

    private javax.swing.JButton btnDelete;

    private javax.swing.JButton btnRefresh;

    private javax.swing.JPanel pnlCard;

    private javax.swing.JScrollPane scrUsers;

    private javax.swing.JTable tblUsers;

    // End of variables declaration//GEN-END:variables
}
