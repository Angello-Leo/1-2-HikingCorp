package hiking.ui;

/**
 * Main window: sidebar menu + header + page area. Each menu button is an editable button in the Design tab.
 * Pages are created in create(); permissions per role decide which buttons stay (setupNav()).
 */
public class MainFrame extends javax.swing.JFrame {

    public MainFrame(hiking.model.User user) {
        initComponents();
        this.user = user; postInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @SuppressWarnings("unchecked")
    private void initComponents() {

        pnlTitleHost = new javax.swing.JPanel();
        pnlBody = new javax.swing.JPanel();
        pnlSidebar = new javax.swing.JPanel();
        pnlBrand = new javax.swing.JPanel();
        lblBrand = new javax.swing.JLabel();
        pnlNavWrap = new javax.swing.JPanel();
        pnlNavList = new javax.swing.JPanel();
        lblSecMain = new javax.swing.JLabel();
        btnNavDashboard = new javax.swing.JButton();
        lblSecInventory = new javax.swing.JLabel();
        btnNavInventory = new javax.swing.JButton();
        lblSecSales = new javax.swing.JLabel();
        btnNavPos = new javax.swing.JButton();
        btnNavHistory = new javax.swing.JButton();
        lblSecAnalysis = new javax.swing.JLabel();
        btnNavDemand = new javax.swing.JButton();
        btnNavSalesPred = new javax.swing.JButton();
        btnNavSlow = new javax.swing.JButton();
        btnNavBest = new javax.swing.JButton();
        lblSecAdmin = new javax.swing.JLabel();
        btnNavUsers = new javax.swing.JButton();
        btnNavAudit = new javax.swing.JButton();
        lblSecAccount = new javax.swing.JLabel();
        btnNavAccount = new javax.swing.JButton();
        pnlFoot = new javax.swing.JPanel();
        pnlUser = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblUserRole = new javax.swing.JLabel();
        pnlSignOut = new javax.swing.JPanel();
        btnSignOut = new javax.swing.JButton();
        pnlRight = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        pnlTitles = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        pnlHeaderRight = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        btnFullscreen = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();

        setDefaultCloseOperation(3);
        setTitle("1/2 HI KING CORP");
        setMinimumSize(new java.awt.Dimension(1100, 680));
        setUndecorated(true);

        pnlTitleHost.setBackground(new java.awt.Color(214, 110, 46));
        pnlTitleHost.setPreferredSize(new java.awt.Dimension(100, 40));
        pnlTitleHost.setLayout(new java.awt.BorderLayout());

        pnlBody.setBackground(new java.awt.Color(214, 223, 234));
        pnlBody.setLayout(new java.awt.BorderLayout());

        pnlSidebar.setBackground(new java.awt.Color(214, 110, 46));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(270, 100));
        pnlSidebar.setLayout(new java.awt.BorderLayout());

        pnlBrand.setBackground(new java.awt.Color(214, 110, 46));
        pnlBrand.setPreferredSize(new java.awt.Dimension(100, 76));
        pnlBrand.setLayout(new java.awt.BorderLayout());

        lblBrand.setText("  1/2 Hi King Corp");
        lblBrand.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblBrand.setForeground(new java.awt.Color(255, 255, 255));
        lblBrand.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));

        pnlNavWrap.setBackground(new java.awt.Color(214, 110, 46));
        pnlNavWrap.setLayout(new java.awt.BorderLayout());

        pnlNavList.setBackground(new java.awt.Color(214, 110, 46));
        pnlNavList.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 14, 6, 14));
        pnlNavList.setLayout(new java.awt.GridLayout(0, 1, 0, 2));

        lblSecMain.setText("Main");
        lblSecMain.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecMain.setForeground(new java.awt.Color(63, 75, 95));
        lblSecMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavDashboard.setText("Dashboard");
        btnNavDashboard.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDashboard.setBackground(new java.awt.Color(214, 110, 46));
        btnNavDashboard.setContentAreaFilled(false);
        btnNavDashboard.setBorderPainted(false);
        btnNavDashboard.setFocusPainted(false);
        btnNavDashboard.setOpaque(true);
        btnNavDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDashboard.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavDashboard.setIconTextGap(14);
        btnNavDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavDashboardActionPerformed(evt);
            }
        });

        lblSecInventory.setText("Inventory");
        lblSecInventory.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecInventory.setForeground(new java.awt.Color(63, 75, 95));
        lblSecInventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavInventory.setText("Inventory");
        btnNavInventory.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavInventory.setForeground(new java.awt.Color(255, 255, 255));
        btnNavInventory.setBackground(new java.awt.Color(214, 110, 46));
        btnNavInventory.setContentAreaFilled(false);
        btnNavInventory.setBorderPainted(false);
        btnNavInventory.setFocusPainted(false);
        btnNavInventory.setOpaque(true);
        btnNavInventory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavInventory.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavInventory.setIconTextGap(14);
        btnNavInventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavInventoryActionPerformed(evt);
            }
        });

        lblSecSales.setText("Sales");
        lblSecSales.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecSales.setForeground(new java.awt.Color(63, 75, 95));
        lblSecSales.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavPos.setText("New Sale");
        btnNavPos.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavPos.setForeground(new java.awt.Color(255, 255, 255));
        btnNavPos.setBackground(new java.awt.Color(214, 110, 46));
        btnNavPos.setContentAreaFilled(false);
        btnNavPos.setBorderPainted(false);
        btnNavPos.setFocusPainted(false);
        btnNavPos.setOpaque(true);
        btnNavPos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavPos.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavPos.setIconTextGap(14);
        btnNavPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavPosActionPerformed(evt);
            }
        });

        btnNavHistory.setText("Sales History");
        btnNavHistory.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnNavHistory.setBackground(new java.awt.Color(214, 110, 46));
        btnNavHistory.setContentAreaFilled(false);
        btnNavHistory.setBorderPainted(false);
        btnNavHistory.setFocusPainted(false);
        btnNavHistory.setOpaque(true);
        btnNavHistory.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavHistory.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavHistory.setIconTextGap(14);
        btnNavHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavHistoryActionPerformed(evt);
            }
        });

        lblSecAnalysis.setText("Knowledge analysis");
        lblSecAnalysis.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecAnalysis.setForeground(new java.awt.Color(63, 75, 95));
        lblSecAnalysis.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavDemand.setText("Product Demand");
        btnNavDemand.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavDemand.setForeground(new java.awt.Color(255, 255, 255));
        btnNavDemand.setBackground(new java.awt.Color(214, 110, 46));
        btnNavDemand.setContentAreaFilled(false);
        btnNavDemand.setBorderPainted(false);
        btnNavDemand.setFocusPainted(false);
        btnNavDemand.setOpaque(true);
        btnNavDemand.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavDemand.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavDemand.setIconTextGap(14);
        btnNavDemand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavDemandActionPerformed(evt);
            }
        });

        btnNavSalesPred.setText("Sales Prediction");
        btnNavSalesPred.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavSalesPred.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSalesPred.setBackground(new java.awt.Color(214, 110, 46));
        btnNavSalesPred.setContentAreaFilled(false);
        btnNavSalesPred.setBorderPainted(false);
        btnNavSalesPred.setFocusPainted(false);
        btnNavSalesPred.setOpaque(true);
        btnNavSalesPred.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSalesPred.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavSalesPred.setIconTextGap(14);
        btnNavSalesPred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavSalesPredActionPerformed(evt);
            }
        });

        btnNavSlow.setText("Slow-Moving Items");
        btnNavSlow.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavSlow.setForeground(new java.awt.Color(255, 255, 255));
        btnNavSlow.setBackground(new java.awt.Color(214, 110, 46));
        btnNavSlow.setContentAreaFilled(false);
        btnNavSlow.setBorderPainted(false);
        btnNavSlow.setFocusPainted(false);
        btnNavSlow.setOpaque(true);
        btnNavSlow.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavSlow.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavSlow.setIconTextGap(14);
        btnNavSlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavSlowActionPerformed(evt);
            }
        });

        btnNavBest.setText("Best-Selling Items");
        btnNavBest.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavBest.setForeground(new java.awt.Color(255, 255, 255));
        btnNavBest.setBackground(new java.awt.Color(214, 110, 46));
        btnNavBest.setContentAreaFilled(false);
        btnNavBest.setBorderPainted(false);
        btnNavBest.setFocusPainted(false);
        btnNavBest.setOpaque(true);
        btnNavBest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavBest.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavBest.setIconTextGap(14);
        btnNavBest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavBestActionPerformed(evt);
            }
        });

        lblSecAdmin.setText("Administration");
        lblSecAdmin.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecAdmin.setForeground(new java.awt.Color(63, 75, 95));
        lblSecAdmin.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavUsers.setText("User Management");
        btnNavUsers.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavUsers.setForeground(new java.awt.Color(255, 255, 255));
        btnNavUsers.setBackground(new java.awt.Color(214, 110, 46));
        btnNavUsers.setContentAreaFilled(false);
        btnNavUsers.setBorderPainted(false);
        btnNavUsers.setFocusPainted(false);
        btnNavUsers.setOpaque(true);
        btnNavUsers.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavUsers.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavUsers.setIconTextGap(14);
        btnNavUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavUsersActionPerformed(evt);
            }
        });

        btnNavAudit.setText("Audit Log");
        btnNavAudit.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavAudit.setForeground(new java.awt.Color(255, 255, 255));
        btnNavAudit.setBackground(new java.awt.Color(214, 110, 46));
        btnNavAudit.setContentAreaFilled(false);
        btnNavAudit.setBorderPainted(false);
        btnNavAudit.setFocusPainted(false);
        btnNavAudit.setOpaque(true);
        btnNavAudit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavAudit.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavAudit.setIconTextGap(14);
        btnNavAudit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavAuditActionPerformed(evt);
            }
        });

        lblSecAccount.setText("Account");
        lblSecAccount.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblSecAccount.setForeground(new java.awt.Color(63, 75, 95));
        lblSecAccount.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 12, 4, 0));

        btnNavAccount.setText("My Account");
        btnNavAccount.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnNavAccount.setForeground(new java.awt.Color(255, 255, 255));
        btnNavAccount.setBackground(new java.awt.Color(214, 110, 46));
        btnNavAccount.setContentAreaFilled(false);
        btnNavAccount.setBorderPainted(false);
        btnNavAccount.setFocusPainted(false);
        btnNavAccount.setOpaque(true);
        btnNavAccount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNavAccount.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnNavAccount.setIconTextGap(14);
        btnNavAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNavAccountActionPerformed(evt);
            }
        });

        pnlFoot.setBackground(new java.awt.Color(214, 110, 46));
        pnlFoot.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 0, 16));
        pnlFoot.setLayout(new java.awt.BorderLayout());

        pnlUser.setBackground(new java.awt.Color(74, 95, 126));
        pnlUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 16, 8, 10));
        pnlUser.setPreferredSize(new java.awt.Dimension(100, 58));
        pnlUser.setLayout(new java.awt.GridLayout(2, 1, 0, 0));

        lblUserName.setText("User");
        lblUserName.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));

        lblUserRole.setText("Role");
        lblUserRole.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblUserRole.setForeground(new java.awt.Color(138, 147, 166));

        pnlSignOut.setBackground(new java.awt.Color(214, 110, 46));
        pnlSignOut.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 14, 14));
        pnlSignOut.setPreferredSize(new java.awt.Dimension(100, 66));
        pnlSignOut.setLayout(new java.awt.BorderLayout());

        btnSignOut.setText("Sign out");
        btnSignOut.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnSignOut.setForeground(new java.awt.Color(255, 255, 255));
        btnSignOut.setBackground(new java.awt.Color(214, 110, 46));
        btnSignOut.setContentAreaFilled(false);
        btnSignOut.setBorderPainted(false);
        btnSignOut.setFocusPainted(false);
        btnSignOut.setOpaque(true);
        btnSignOut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSignOut.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 10));
        btnSignOut.setIconTextGap(14);
        btnSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignOutActionPerformed(evt);
            }
        });

        pnlRight.setBackground(new java.awt.Color(214, 223, 234));
        pnlRight.setLayout(new java.awt.BorderLayout());

        pnlHeader.setBackground(new java.awt.Color(214, 223, 234));
        pnlHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 28, 8, 28));
        pnlHeader.setPreferredSize(new java.awt.Dimension(100, 84));
        pnlHeader.setLayout(new java.awt.BorderLayout());

        pnlTitles.setBackground(new java.awt.Color(214, 223, 234));
        pnlTitles.setLayout(new java.awt.GridLayout(2, 1, 0, 2));

        lblTitle.setText("Dashboard");
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 26));
        lblTitle.setForeground(new java.awt.Color(31, 41, 55));

        lblSubtitle.setText(" ");
        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13));
        lblSubtitle.setForeground(new java.awt.Color(63, 75, 95));

        pnlHeaderRight.setBackground(new java.awt.Color(214, 223, 234));
        pnlHeaderRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 12, 10));

        lblClock.setText(" ");
        lblClock.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblClock.setForeground(new java.awt.Color(63, 75, 95));

        lblRole.setText("Role");
        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lblRole.setForeground(new java.awt.Color(214, 110, 46));
        lblRole.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 12, 4, 12));

        btnFullscreen.setText("Full screen (F11)");
        btnFullscreen.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnFullscreen.setBackground(new java.awt.Color(255, 255, 255));
        btnFullscreen.setForeground(new java.awt.Color(214, 110, 46));
        btnFullscreen.setContentAreaFilled(false);
        btnFullscreen.setBorderPainted(false);
        btnFullscreen.setFocusPainted(false);
        btnFullscreen.setOpaque(true);
        btnFullscreen.setPreferredSize(new java.awt.Dimension(150, 34));
        btnFullscreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFullscreenActionPerformed(evt);
            }
        });

        pnlContent.setBackground(new java.awt.Color(214, 223, 234));
        pnlContent.setLayout(new java.awt.BorderLayout());

        pnlBrand.add(lblBrand, java.awt.BorderLayout.CENTER);
        pnlNavList.add(lblSecMain);
        pnlNavList.add(btnNavDashboard);
        pnlNavList.add(lblSecInventory);
        pnlNavList.add(btnNavInventory);
        pnlNavList.add(lblSecSales);
        pnlNavList.add(btnNavPos);
        pnlNavList.add(btnNavHistory);
        pnlNavList.add(lblSecAnalysis);
        pnlNavList.add(btnNavDemand);
        pnlNavList.add(btnNavSalesPred);
        pnlNavList.add(btnNavSlow);
        pnlNavList.add(btnNavBest);
        pnlNavList.add(lblSecAdmin);
        pnlNavList.add(btnNavUsers);
        pnlNavList.add(btnNavAudit);
        pnlNavList.add(lblSecAccount);
        pnlNavList.add(btnNavAccount);
        pnlNavWrap.add(pnlNavList, java.awt.BorderLayout.NORTH);
        pnlUser.add(lblUserName);
        pnlUser.add(lblUserRole);
        pnlSignOut.add(btnSignOut, java.awt.BorderLayout.CENTER);
        pnlFoot.add(pnlUser, java.awt.BorderLayout.NORTH);
        pnlFoot.add(pnlSignOut, java.awt.BorderLayout.SOUTH);
        pnlSidebar.add(pnlBrand, java.awt.BorderLayout.NORTH);
        pnlSidebar.add(pnlNavWrap, java.awt.BorderLayout.CENTER);
        pnlSidebar.add(pnlFoot, java.awt.BorderLayout.SOUTH);
        pnlTitles.add(lblTitle);
        pnlTitles.add(lblSubtitle);
        pnlHeaderRight.add(lblClock);
        pnlHeaderRight.add(lblRole);
        pnlHeaderRight.add(btnFullscreen);
        pnlHeader.add(pnlTitles, java.awt.BorderLayout.CENTER);
        pnlHeader.add(pnlHeaderRight, java.awt.BorderLayout.EAST);
        pnlRight.add(pnlHeader, java.awt.BorderLayout.NORTH);
        pnlRight.add(pnlContent, java.awt.BorderLayout.CENTER);
        pnlBody.add(pnlSidebar, java.awt.BorderLayout.WEST);
        pnlBody.add(pnlRight, java.awt.BorderLayout.CENTER);
        getContentPane().add(pnlTitleHost, java.awt.BorderLayout.NORTH);
        getContentPane().add(pnlBody, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNavDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavDashboardActionPerformed
        go("dashboard");
    }//GEN-LAST:event_btnNavDashboardActionPerformed

    private void btnNavInventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavInventoryActionPerformed
        go("inventory");
    }//GEN-LAST:event_btnNavInventoryActionPerformed

    private void btnNavPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavPosActionPerformed
        go("pos");
    }//GEN-LAST:event_btnNavPosActionPerformed

    private void btnNavHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavHistoryActionPerformed
        go("history");
    }//GEN-LAST:event_btnNavHistoryActionPerformed

    private void btnNavDemandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavDemandActionPerformed
        go("a_demand");
    }//GEN-LAST:event_btnNavDemandActionPerformed

    private void btnNavSalesPredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavSalesPredActionPerformed
        go("a_sales");
    }//GEN-LAST:event_btnNavSalesPredActionPerformed

    private void btnNavSlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavSlowActionPerformed
        go("a_slow");
    }//GEN-LAST:event_btnNavSlowActionPerformed

    private void btnNavBestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavBestActionPerformed
        go("a_best");
    }//GEN-LAST:event_btnNavBestActionPerformed

    private void btnNavUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavUsersActionPerformed
        go("users");
    }//GEN-LAST:event_btnNavUsersActionPerformed

    private void btnNavAuditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavAuditActionPerformed
        go("audit");
    }//GEN-LAST:event_btnNavAuditActionPerformed

    private void btnNavAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNavAccountActionPerformed
        go("account");
    }//GEN-LAST:event_btnNavAccountActionPerformed

    private void btnSignOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignOutActionPerformed
        logout(null);
    }//GEN-LAST:event_btnSignOutActionPerformed

    private void btnFullscreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFullscreenActionPerformed
        toggleFullScreen();
    }//GEN-LAST:event_btnFullscreenActionPerformed


    private hiking.model.User user;
    private TitleBar titleBar;
    private final java.awt.CardLayout cards = new java.awt.CardLayout();
    private final java.util.Map<String, PageView> pages = new java.util.HashMap<>();
    private final java.util.Map<String, javax.swing.JButton> buttons = new java.util.LinkedHashMap<>();
    private final java.util.Map<String, javax.swing.Icon[]> icons = new java.util.HashMap<>();
    private static final int IDLE_MINUTES = 15;
    private javax.swing.Timer idle;
    private final java.util.Map<String, hiking.ui.Icons.N> navIcon = new java.util.HashMap<>();

    private void postInit() {
        titleBar = new TitleBar(this, true);
        pnlTitleHost.add(titleBar, java.awt.BorderLayout.CENTER);
        getContentPane().setBackground(Theme.BG);
        pnlContent.setLayout(cards);
        lblUserName.setText(user.fullName);
        lblUserRole.setText(user.role.label);
        lblRole.setText(user.role.label);
        lblRole.setText(user.role.label.toUpperCase()); lblRole.setOpaque(false); lblRole.setForeground(Theme.PRIMARY); lblRole.setFont(Theme.bold(20f));
        pnlHeader.setPreferredSize(new java.awt.Dimension(100, 108)); pnlHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 28, 8, 28)); lblTitle.setFont(Theme.bold(30f)); lblTitle.setIconTextGap(14); lblClock.setFont(Theme.plain(18f)); lblClock.setForeground(Theme.TEXT);
        lblBrand.setIcon(brandIcon());
        setupNav();
        Util.skin(this);
        titleBar.maximize();
        // clock
        javax.swing.Timer tick = new javax.swing.Timer(15000, e -> updateClock()); tick.setInitialDelay(0); tick.start();
        // idle sign-out after IDLE_MINUTES without mouse / keyboard activity
        idle = new javax.swing.Timer(IDLE_MINUTES * 60000, e -> { if (hiking.security.Session.user() != null) logout("You were signed out after " + IDLE_MINUTES + " minutes of inactivity."); });
        idle.setRepeats(false); idle.start();
        java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(e -> idle.restart(), java.awt.AWTEvent.MOUSE_EVENT_MASK | java.awt.AWTEvent.KEY_EVENT_MASK | java.awt.AWTEvent.MOUSE_MOTION_EVENT_MASK);
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0), "fs");
        getRootPane().getActionMap().put("fs", new javax.swing.AbstractAction() { public void actionPerformed(java.awt.event.ActionEvent e) { toggleFullScreen(); } });
        go("dashboard");
    }

    private static javax.swing.Icon brandIcon() {
        java.awt.image.BufferedImage im = new java.awt.image.BufferedImage(40, 40, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = im.createGraphics(); UI.aa(g); Brand.logo(g, 0, 0, 40); g.dispose(); return new javax.swing.ImageIcon(im);
    }

    /** Paints the sidebar with a soft top-to-bottom gradient: the sidebar and its (orange) child panels become transparent and the gradient is drawn as the sidebar's border. */
    private void softenSidebar() {
        pnlSidebar.setOpaque(false); pnlSidebar.setBorder(new Flat.GradientBorder(pnlSidebar.getBorder()));
        java.util.ArrayDeque<java.awt.Container> q = new java.util.ArrayDeque<>(); q.add(pnlSidebar);
        while (!q.isEmpty()) for (java.awt.Component c : q.poll().getComponents()) {
            if (c instanceof javax.swing.JPanel && (Theme.PRIMARY.equals(c.getBackground()) || Theme.SIDEBAR.equals(c.getBackground()))) ((javax.swing.JPanel) c).setOpaque(false);
            if (c instanceof java.awt.Container) q.add((java.awt.Container) c);
        }
    }

    /** Shows only the menu entries the signed-in role may use (see hiking.model.Role) and wires the icons. */
    private void setupNav() {
        hiking.model.Role r = user.role;
        boolean inv = r.can(hiking.model.Perm.INVENTORY_MANAGE), sale = r.can(hiking.model.Perm.SALES_CREATE), hist = r.can(hiking.model.Perm.SALES_VIEW), an = r.can(hiking.model.Perm.ANALYSIS_PREDICT),
            usr = r.can(hiking.model.Perm.USERS_MANAGE), aud = r.can(hiking.model.Perm.AUDIT_VIEW);
        Object[][] map = {
            {btnNavDashboard, "dashboard", hiking.ui.Icons.N.DASHBOARD, true},
            {btnNavInventory, "inventory", hiking.ui.Icons.N.BOX, inv},
            {btnNavPos, "pos", hiking.ui.Icons.N.CART, sale},
            {btnNavHistory, "history", hiking.ui.Icons.N.RECEIPT, hist},
            {btnNavDemand, "a_demand", hiking.ui.Icons.N.BAG, an},
            {btnNavSalesPred, "a_sales", hiking.ui.Icons.N.TREND, an},
            {btnNavSlow, "a_slow", hiking.ui.Icons.N.CLOCK, an},
            {btnNavBest, "a_best", hiking.ui.Icons.N.STAR, an},
            {btnNavUsers, "users", hiking.ui.Icons.N.USERS, usr},
            {btnNavAudit, "audit", hiking.ui.Icons.N.SHIELD, aud},
            {btnNavAccount, "account", hiking.ui.Icons.N.KEY, true}
        };
        for (Object[] m : map) {
            javax.swing.JButton b = (javax.swing.JButton) m[0]; String id = (String) m[1]; hiking.ui.Icons.N icon = (hiking.ui.Icons.N) m[2]; boolean allowed = (Boolean) m[3];
            if (!allowed) { pnlNavList.remove(b); continue; }
            b.putClientProperty("nav", Boolean.TRUE); buttons.put(id, b); navIcon.put(id, icon); icons.put(id, new javax.swing.Icon[] { Icons.get(icon, 22, java.awt.Color.WHITE), Icons.get(icon, 22, java.awt.Color.WHITE) });
            b.setIcon(icons.get(id)[0]);
        }
        btnSignOut.putClientProperty("nav", Boolean.TRUE); lblUserRole.setForeground(new java.awt.Color(0xFFE9D8)); btnSignOut.setIcon(Icons.get(Icons.N.LOGOUT, 22, java.awt.Color.WHITE)); btnSignOut.setForeground(java.awt.Color.WHITE);
        // drop section headings that have no entries
        removeSection(lblSecInventory, inv); removeSection(lblSecSales, sale || hist); removeSection(lblSecAnalysis, an); removeSection(lblSecAdmin, usr || aud);
        pnlNavList.revalidate();
        softenSidebar();
    }
    private void removeSection(javax.swing.JLabel l, boolean keep) { if (!keep) pnlNavList.remove(l); }

    private PageView create(String id) {
        switch (id) {
            case "dashboard": return new DashboardPanel(user, this::go);
            case "inventory": return new InventoryPanel();
            case "pos": return new PosPanel();
            case "history": return new SalesHistoryPanel();
            case "a_demand": return new AnalysisPanel(hiking.ml.Task.DEMAND);
            case "a_sales": return new AnalysisPanel(hiking.ml.Task.SALES);
            case "a_slow": return new AnalysisPanel(hiking.ml.Task.SLOW);
            case "a_best": return new AnalysisPanel(hiking.ml.Task.BEST);
            case "users": return new UsersPanel();
            case "audit": return new AuditPanel();
            default: return new AccountPanel(user);
        }
    }

    /** Shows the page with the given id (creating it the first time). */
    public void go(String id) {
        if (!buttons.containsKey(id)) return;
        for (PageView old : pages.values()) if (old instanceof InventoryPanel) ((InventoryPanel) old).leaveDeleteMode();
        PageView p = pages.get(id);
        if (p == null) { p = create(id); pages.put(id, p); pnlContent.add((java.awt.Component) p, id); }
        cards.show(pnlContent, id);
        lblTitle.setText(p.title()); lblSubtitle.setText(p.subtitle()); lblTitle.setIcon(Icons.get(navIcon.get(id), 30, Theme.TEXT));
        for (java.util.Map.Entry<String, javax.swing.JButton> e : buttons.entrySet()) {
            boolean sel = e.getKey().equals(id); javax.swing.JButton b = e.getValue();
            b.setBackground(sel ? Theme.SLATE : Theme.SIDEBAR); b.setForeground(java.awt.Color.WHITE); b.setIcon(icons.get(e.getKey())[sel ? 1 : 0]);
        }
        updateClock(); p.onShow(); pnlContent.revalidate(); pnlContent.repaint();
    }

    private void updateClock() { lblClock.setText(java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("EEE, MMM d  \u2022  h:mm a"))); }

    private void logout(String message) {
        hiking.dao.AuditDao.log("LOGOUT", message == null ? "signed out" : "auto sign-out (idle)");
        hiking.security.Session.end(); idle.stop();
        LoginFrame lf = new LoginFrame(); lf.setVisible(true); if (message != null) lf.showMessage(message);
        dispose();
    }

    /** F11 / header button: exclusive full screen (hides the taskbar) and back to the maximized window. */
    public void toggleFullScreen() {
        java.awt.GraphicsDevice gd = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.getFullScreenWindow() == this) { gd.setFullScreenWindow(null); setBounds(java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); }
        else gd.setFullScreenWindow(this);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel pnlTitleHost;

    private javax.swing.JPanel pnlBody;

    private javax.swing.JPanel pnlSidebar;

    private javax.swing.JPanel pnlBrand;

    private javax.swing.JLabel lblBrand;

    private javax.swing.JPanel pnlNavWrap;

    private javax.swing.JPanel pnlNavList;

    private javax.swing.JLabel lblSecMain;

    private javax.swing.JButton btnNavDashboard;

    private javax.swing.JLabel lblSecInventory;

    private javax.swing.JButton btnNavInventory;

    private javax.swing.JLabel lblSecSales;

    private javax.swing.JButton btnNavPos;

    private javax.swing.JButton btnNavHistory;

    private javax.swing.JLabel lblSecAnalysis;

    private javax.swing.JButton btnNavDemand;

    private javax.swing.JButton btnNavSalesPred;

    private javax.swing.JButton btnNavSlow;

    private javax.swing.JButton btnNavBest;

    private javax.swing.JLabel lblSecAdmin;

    private javax.swing.JButton btnNavUsers;

    private javax.swing.JButton btnNavAudit;

    private javax.swing.JLabel lblSecAccount;

    private javax.swing.JButton btnNavAccount;

    private javax.swing.JPanel pnlFoot;

    private javax.swing.JPanel pnlUser;

    private javax.swing.JLabel lblUserName;

    private javax.swing.JLabel lblUserRole;

    private javax.swing.JPanel pnlSignOut;

    private javax.swing.JButton btnSignOut;

    private javax.swing.JPanel pnlRight;

    private javax.swing.JPanel pnlHeader;

    private javax.swing.JPanel pnlTitles;

    private javax.swing.JLabel lblTitle;

    private javax.swing.JLabel lblSubtitle;

    private javax.swing.JPanel pnlHeaderRight;

    private javax.swing.JLabel lblClock;

    private javax.swing.JLabel lblRole;

    private javax.swing.JButton btnFullscreen;

    private javax.swing.JPanel pnlContent;

    // End of variables declaration//GEN-END:variables
}
