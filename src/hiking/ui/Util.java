package hiking.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Small helpers shared by all forms: background work, inline banners, input parsing and the "skin" that gives
 * plain Swing components (the ones you drop from the Palette) the flat look of the application.
 * Change the look of tables, text boxes and banners here.
 */
public final class Util {
    private Util() {}

    // ------------------------------------------------------------------ background work
    /** Runs {@code work} off the UI thread and delivers the result (or the error) on the UI thread. */
    public static <T> void async(Callable<T> work, Consumer<T> ok, Consumer<Exception> err) {
        new SwingWorker<T, Void>() {
            @Override protected T doInBackground() throws Exception { return work.call(); }
            @Override protected void done() {
                try { ok.accept(get()); }
                catch (java.util.concurrent.ExecutionException e) { Throwable c = e.getCause() == null ? e : e.getCause(); err.accept(c instanceof Exception ? (Exception) c : new RuntimeException(c)); }
                catch (InterruptedException e) { err.accept(e); }
            }
        }.execute();
    }
    /** Same, but errors are shown in the page banner label. */
    public static <T> void async(JLabel banner, Callable<T> work, Consumer<T> ok) { async(work, ok, ex -> banner(banner, message(ex), false)); }

    public static String message(Exception ex) {
        if (ex instanceof IllegalArgumentException || ex instanceof SecurityException) return ex.getMessage();
        if (ex instanceof SQLException) return "Database error: " + ex.getMessage();
        return ex.getClass().getSimpleName() + ": " + ex.getMessage();
    }

    // ------------------------------------------------------------------ banner (a JLabel on each page)
    private static final java.util.Map<JLabel, javax.swing.Timer> TIMERS = new java.util.WeakHashMap<>();
    /** Shows a green (ok) or red message strip in the given label; hides itself after 7 seconds. */
    public static void banner(JLabel l, String msg, boolean ok) {
        Color c = ok ? Theme.OK : Theme.DANGER;
        l.setText("<html>" + msg.replace("&", "&amp;").replace("<", "&lt;").replace("\n", "<br>") + "</html>"); l.setForeground(c); l.setBackground(Theme.alpha(c, 28)); l.setOpaque(true);
        l.setBorder(new EmptyBorder(10, 14, 10, 14)); l.setFont(Theme.bold(13.5f)); l.setVisible(true);
        javax.swing.Timer t = TIMERS.get(l); if (t == null) { t = new javax.swing.Timer(7000, e -> clearBanner(l)); t.setRepeats(false); TIMERS.put(l, t); } t.restart();
        l.revalidate(); l.repaint();
    }
    public static void clearBanner(JLabel l) { l.setText(" "); l.setOpaque(false); l.setBorder(null); l.setVisible(false); l.revalidate(); }

    // ------------------------------------------------------------------ parsing with friendly errors
    public static double num(JTextField f, String name) {
        try { double v = Double.parseDouble(f.getText().trim().replace(",", "")); if (Double.isNaN(v) || Double.isInfinite(v)) throw new NumberFormatException(); return v; }
        catch (NumberFormatException e) { throw new IllegalArgumentException(name + " must be a number."); }
    }
    public static int integer(JTextField f, String name) {
        try { return Integer.parseInt(f.getText().trim().replace(",", "")); } catch (NumberFormatException e) { throw new IllegalArgumentException(name + " must be a whole number."); }
    }
    public static java.time.LocalDate date(JTextField f, String name) {
        try { return java.time.LocalDate.parse(f.getText().trim()); } catch (Exception e) { throw new IllegalArgumentException(name + " must be a date like 2026-10-31."); }
    }

    // ------------------------------------------------------------------ window helpers
    public static Frame frame(Component c) { Window w = SwingUtilities.getWindowAncestor(c); while (w != null && !(w instanceof Frame)) w = w.getOwner(); return (Frame) w; }
    public static boolean confirm(Component parent, String title, String message) {
        return JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION;
    }
    /** Puts {@code c} as the only child of {@code host} (host gets a BorderLayout). Used for charts and other code-built parts. */
    public static void fill(JPanel host, JComponent c) { host.removeAll(); host.setLayout(new BorderLayout()); host.add(c, BorderLayout.CENTER); host.revalidate(); host.repaint(); }

    // ------------------------------------------------------------------ skin
    /** Header titles that should be right-aligned in tables (money, quantities...). Set per table with {@link #rightAlign}. */
    private static final String RIGHT_KEY = "rightColumns";

    /**
     * Applies the application's flat look to the plain Swing components of a form: rounded cards (white panels with padding),
     * rounded buttons / text boxes / combo boxes, underline tabs, thin scroll bars and clean tables.
     * Call it as the last line of a form's constructor. The look itself lives in {@link Flat}.
     */
    public static void skin(Container root) {
        // table-style pages use beige cards (as in the design); the others keep white cards
        String n = root.getClass().getSimpleName();
        Flat.pageCard = (n.equals("UsersPanel") || n.equals("AuditPanel") || n.equals("AccountPanel") || n.equals("InventoryPanel") || n.equals("SalesHistoryPanel")) ? Theme.BEIGE : Color.WHITE;
        skin(root, false);
        Flat.pageCard = Color.WHITE;
    }

    private static void skin(Container parent, boolean inCard) {
        for (Component c : parent.getComponents()) {
            boolean card = false;
            if (c instanceof JPanel && !inCard) {
                JPanel p = (JPanel) c;
                if (p.getBorder() instanceof EmptyBorder && !(p.getBorder() instanceof Flat.CardBorder) && Color.WHITE.equals(p.getBackground()) && p.isOpaque() && p.getBorder().getBorderInsets(p).left >= 6) {
                    java.awt.Insets in = p.getBorder().getBorderInsets(p); p.setOpaque(false); p.setBorder(new Flat.CardBorder(new java.awt.Insets(in.top + 1, in.left + 1, in.bottom + 1, in.right + 1))); card = true;
                } else if (p.getBorder() instanceof Flat.CardBorder) card = true;
            }
            if (c instanceof javax.swing.text.JTextComponent && !(c instanceof JTextArea && !((JTextArea) c).isEditable())) {
                if (c instanceof JTextArea) { ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Flat.LINE), new EmptyBorder(8, 10, 8, 10))); }
                else if (!(c instanceof JEditorPane)) Flat.textField((javax.swing.text.JTextComponent) c);
            }
            else if (c instanceof JComboBox) Flat.combo((JComboBox<?>) c);
            else if (c instanceof JButton) Flat.button((AbstractButton) c);
            else if (c instanceof JCheckBox) Flat.check((JCheckBox) c);
            else if (c instanceof JTabbedPane) Flat.tabs((JTabbedPane) c);
            else if (c instanceof JTable) styleTable((JTable) c);
            else if (c instanceof JScrollPane) Flat.scroll((JScrollPane) c, inCard);
            else if (c instanceof JLabel && !(c instanceof UI.Badge)) sentenceCase((JLabel) c);
            if (c instanceof Container) skin((Container) c, inCard || card);
        }
    }

    private static final java.util.regex.Pattern CAPS = java.util.regex.Pattern.compile("^[A-Z0-9][A-Z0-9 ()%/&'.,:#-]*$");
    /** "FULL NAME" -> "Full name" (small ALL-CAPS captions look dated; real labels keep their own capitals, e.g. PHP, SKU). */
    private static void sentenceCase(JLabel l) {
        String t = l.getText(); if (t == null || t.length() < 4 || t.startsWith("<")) return;
        int p = t.indexOf('('); String head = p < 0 ? t : t.substring(0, p), tail = p < 0 ? "" : t.substring(p);
        if (!CAPS.matcher(head.trim()).matches() || head.replaceAll("[^A-Z]", "").length() < 4) return;
        String s = head.charAt(0) + head.substring(1).toLowerCase();
        for (String k : new String[]{"PHP", "SKU", "MAE", "MSE", "RMSE", "WAPE", "OOB", "SLR"}) s = s.replaceAll("(?i)\\b" + k + "\\b", k);
        if (!tail.matches(".*[a-z].*")) tail = tail.toLowerCase().replace("php", "PHP");
        l.setText(s + tail);
    }
    public static java.util.List<Component> all(Container c) { java.util.List<Component> l = new java.util.ArrayList<>(); for (Component k : c.getComponents()) { l.add(k); if (k instanceof Container) l.addAll(all((Container) k)); } return l; }

    /** Right-align the given table columns (by header title). Call after skin(). */
    public static void rightAlign(JTable t, String... headers) {
        Set<String> s = new HashSet<>(java.util.Arrays.asList(headers)); t.putClientProperty(RIGHT_KEY, s); t.getTableHeader().repaint(); t.repaint();
    }
    @SuppressWarnings("unchecked") private static boolean isRight(JTable t, int col) {
        Object s = t.getClientProperty(RIGHT_KEY); if (s == null) return false; Object h = t.getColumnModel().getColumn(col).getHeaderValue(); return h != null && ((Set<String>) s).contains(h.toString());
    }

    private static Color fill(Component c) { Color f = Flat.fillFor(c); return f == null ? Color.WHITE : f; }

    public static void styleTable(JTable t) {
        t.setRowHeight(44); t.setShowGrid(false); t.setShowHorizontalLines(false); t.setIntercellSpacing(new Dimension(0, 0)); t.setFillsViewportHeight(true); t.setForeground(Theme.TEXT);
        t.setSelectionBackground(Theme.PRIMARY_LT); t.setSelectionForeground(Theme.TEXT); t.setAutoCreateRowSorter(true); t.getTableHeader().setReorderingAllowed(false); t.setBackground(Color.WHITE);
        t.addHierarchyListener(e -> { Color f = Flat.fillFor(t); if (f != null) { t.setBackground(f); if (t.getParent() instanceof JViewport) t.getParent().setBackground(f); } });
        JTableHeader h = t.getTableHeader(); h.setPreferredSize(new Dimension(100, 40)); h.setOpaque(false);
        h.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(tb, v, s, f, r, c); l.setFont(Theme.bold(12.5f)); l.setForeground(Theme.TEXT); l.setBackground(fill(tb)); l.setOpaque(true);
                l.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Flat.LINE), new EmptyBorder(0, 12, 0, 12))); l.setHorizontalAlignment(isRight(tb, c) ? SwingConstants.RIGHT : SwingConstants.LEFT); l.setText(v == null ? "" : v.toString()); return l;
            }
        });
        t.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean sel, boolean foc, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(tb, v, sel, false, r, c);
                l.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0xD8C7B2)), new EmptyBorder(0, 12, 0, 12))); l.setFont(Theme.plain(13.5f));
                l.setBackground(sel ? Theme.PRIMARY_LT : fill(tb)); l.setForeground(Theme.TEXT); boolean right = isRight(tb, c); l.setHorizontalAlignment(right ? SwingConstants.RIGHT : SwingConstants.LEFT);
                String s = v == null ? "" : v.toString();
                if (s.equals("LOW") || s.equals("REORDER")) { l.setForeground(Theme.WARN); l.setFont(Theme.bold(12.5f)); }
                else if (s.equals("OUT") || s.equals("SLOW-MOVING") || s.equals("INACTIVE") || s.equals("FAILED") || s.equals("Disabled")) { l.setForeground(Theme.DANGER); l.setFont(Theme.bold(12.5f)); }
                else if (s.equals("OK") || s.equals("BEST-SELLER") || s.equals("ACTIVE") || s.equals("Active")) { l.setForeground(Theme.OK); l.setFont(Theme.bold(12.5f)); }
                else if (s.startsWith("+") && s.length() > 1 && Character.isDigit(s.charAt(1))) l.setForeground(Theme.OK);
                else if (s.startsWith("-") && s.length() > 1 && Character.isDigit(s.charAt(1)) && right) l.setForeground(Theme.DANGER);
                return l;
            }
        });
    }

    /** Empty, non-editable table model with the given column titles. */
    public static DefaultTableModel model(String... cols) { return new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } }; }
    /** Replaces the model of a form-designed table (keeps the columns you set in the Design tab when {@code cols} is empty). */
    public static DefaultTableModel resetModel(JTable t) {
        DefaultTableModel m = (DefaultTableModel) t.getModel(); m.setRowCount(0); return m;
    }
}
