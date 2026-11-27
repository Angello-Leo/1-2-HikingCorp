/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hiking.ui;

import hiking.dao.ProductDao;
import hiking.security.Secure;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;

/**
 *
 * @author Leo Angello
 */
public class PurgeUi {
    private PurgeUi() {}

    /** Asks for a secret (password or code) in a masked box. Returns null if the user cancels. */
    public static String ask(Component parent, String title, String message) {
        JPasswordField f = new JPasswordField(18);
        f.addAncestorListener(new AncestorListener() {
            public void ancestorAdded(AncestorEvent e) { SwingUtilities.invokeLater(f::requestFocusInWindow); }
            public void ancestorRemoved(AncestorEvent e) { }
            public void ancestorMoved(AncestorEvent e) { }
        });
        JLabel l = new JLabel("<html><div style='width:260px'>" + message + "</div></html>");
        int r = JOptionPane.showConfirmDialog(parent, new Object[]{l, f}, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (r != JOptionPane.OK_OPTION) return null;
        String s = new String(f.getPassword());
        return s.isEmpty() ? null : s;
    }

    /** Admin: list of delete requests from the inventory clerks, with Approve / Reject. */
    public static void openRequests(Component parent, JLabel banner, Runnable onChange) {
        Frame owner = Util.frame(parent);
        JDialog d = new JDialog(owner, "Delete requests", true);
        javax.swing.table.DefaultTableModel m = Util.model("ID", "Item", "Requested by", "Time");
        JTable t = new JTable(m); t.setRowHeight(30); t.setFont(Theme.plain(14f)); Util.styleTable(t);
        JButton approve = new JButton("Approve (delete)"), reject = new JButton("Reject"), close = new JButton("Close");
        approve.setBackground(Theme.DANGER); approve.setForeground(Color.WHITE); approve.setOpaque(true); approve.setBorderPainted(false); approve.setFocusPainted(false);
        JLabel msg = new JLabel(" "); msg.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        Runnable reload = () -> Util.async(ProductDao::pendingRequests, rows -> {
            m.setRowCount(0); for (String[] r : rows) m.addRow(r);
            msg.setText(rows.isEmpty() ? "No pending requests." : rows.size() + " waiting for your decision."); msg.setForeground(Theme.MUTED);
        }, ex -> { msg.setText(Util.message(ex)); msg.setForeground(Theme.DANGER); });
        approve.addActionListener(e -> {
            int r = t.getSelectedRow(); if (r < 0) { msg.setText("Select a request first."); msg.setForeground(Theme.DANGER); return; }
            int id = Integer.parseInt((String) m.getValueAt(r, 0)); String item = (String) m.getValueAt(r, 1);
            String pw = ask(d, "Approve delete", "Permanently delete <b>" + item + "</b>?<br>Enter YOUR password to approve."); if (pw == null) return;
            Util.async(() -> { ProductDao.decide(id, true, pw); return Boolean.TRUE; }, ok -> { msg.setText("Approved and deleted: " + item); msg.setForeground(Theme.OK); reload.run(); onChange.run(); },
                ex -> { msg.setText(Util.message(ex)); msg.setForeground(Theme.DANGER); });
        });
        reject.addActionListener(e -> {
            int r = t.getSelectedRow(); if (r < 0) { msg.setText("Select a request first."); msg.setForeground(Theme.DANGER); return; }
            int id = Integer.parseInt((String) m.getValueAt(r, 0)); String item = (String) m.getValueAt(r, 1);
            Util.async(() -> { ProductDao.decide(id, false, null); return Boolean.TRUE; }, ok -> { msg.setText("Rejected. " + item + " stays archived."); msg.setForeground(Theme.OK); reload.run(); onChange.run(); },
                ex -> { msg.setText(Util.message(ex)); msg.setForeground(Theme.DANGER); });
        });
        close.addActionListener(e -> d.dispose());
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8)); buttons.add(reject); buttons.add(approve); buttons.add(close);
        d.setLayout(new BorderLayout()); d.add(msg, BorderLayout.NORTH); d.add(new JScrollPane(t), BorderLayout.CENTER); d.add(buttons, BorderLayout.SOUTH);
        d.setSize(620, 380); d.setLocationRelativeTo(owner); reload.run(); d.setVisible(true);
    }

    /** Admin: create (first time) or change the secret code. Changing it needs the admin's password. */
    public static void setCode(Component parent, JLabel banner) {
        Util.async(Secure::hasSecretCode, has -> {
            if (has) {
                String pw = ask(parent, "Change delete code", "Enter YOUR password to change the secret code."); if (pw == null) return;
                Util.async(() -> { Secure.confirmPassword(pw); return Boolean.TRUE; }, ok -> askNewCode(parent, banner), ex -> Util.banner(banner, Util.message(ex), false));
            } else askNewCode(parent, banner);
        }, ex -> Util.banner(banner, Util.message(ex), false));
    }

    private static void askNewCode(Component parent, JLabel banner) {
        String a = ask(parent, "Set delete code", "Choose a secret code (at least 6 characters).<br>It unlocks Delete mode in Inventory."); if (a == null) return;
        String b = ask(parent, "Set delete code", "Type the same secret code again to confirm."); if (b == null) return;
        if (!a.equals(b)) { Util.banner(banner, "The two codes do not match.", false); return; }
        Util.async(() -> { Secure.setSecretCode(a); return Boolean.TRUE; }, ok -> Util.banner(banner, "Delete code saved.", true), ex -> Util.banner(banner, Util.message(ex), false));
    }
}
