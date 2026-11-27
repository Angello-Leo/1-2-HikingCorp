package hiking.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

/** Factory + custom-painted widgets that give the app its modern flat look. */
public final class UI {
    private UI() {}

    static void aa(Graphics2D g) { g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); }

    // ------------------------------------------------------------------ labels
    public static JLabel label(String text, float size, boolean bold, Color c) { JLabel l = new JLabel(text); l.setFont(bold ? Theme.bold(size) : Theme.plain(size)); l.setForeground(c); return l; }
    public static JLabel h1(String t) { return label(t, 26, true, Theme.TEXT); }
    public static JLabel h2(String t) { return label(t, 17, true, Theme.TEXT); }
    public static JLabel muted(String t) { return label(t, 13, false, Theme.MUTED); }
    public static JLabel fieldLabel(String t) { JLabel l = label(t, 12, true, Theme.MUTED); l.setBorder(new EmptyBorder(10, 2, 4, 0)); return l; }
    public static JPanel transparent(LayoutManager lm) { JPanel p = new JPanel(lm); p.setOpaque(false); return p; }

    // ------------------------------------------------------------------ card
    public static class Card extends JPanel {
        private final int pad;
        public Card(LayoutManager lm) { this(lm, 20); }
        public Card(LayoutManager lm, int pad) { super(lm); this.pad = pad; setOpaque(false); setBorder(new EmptyBorder(pad + 3, pad + 3, pad + 5, pad + 3)); }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); aa(g); int w = getWidth(), h = getHeight();
            g.setColor(Theme.CARD); g.fill(new RoundRectangle2D.Double(.5, .5, w - 1, h - 1, Flat.CARD_ARC, Flat.CARD_ARC)); g.setColor(Flat.LINE); g.draw(new RoundRectangle2D.Double(.5, .5, w - 1.5, h - 1.5, Flat.CARD_ARC, Flat.CARD_ARC)); g.dispose();
        }
    }

    // ------------------------------------------------------------------ buttons
    public enum BtnStyle { PRIMARY, OUTLINE, DANGER, SUCCESS, GHOST, DARK }
    public static class Btn extends JButton {
        private final BtnStyle style; private boolean hover, press;
        public Btn(String text, BtnStyle s) {
            super(text); style = s; setFont(Theme.bold(13.5f)); setFocusPainted(false); setBorderPainted(false); setContentAreaFilled(false); setOpaque(false); setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); setBorder(new EmptyBorder(10, 20, 10, 20));
            addMouseListener(new MouseAdapter() { public void mouseEntered(MouseEvent e) { hover = true; repaint(); } public void mouseExited(MouseEvent e) { hover = false; press = false; repaint(); } public void mousePressed(MouseEvent e) { press = true; repaint(); } public void mouseReleased(MouseEvent e) { press = false; repaint(); } });
        }
        public Btn(String text) { this(text, BtnStyle.PRIMARY); }
        public Btn(String text, BtnStyle s, Icon icon) { this(text, s); setIcon(icon); setIconTextGap(8); }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); aa(g); int w = getWidth(), h = getHeight(); boolean en = isEnabled(); Color bg, fg, border = null;
            switch (style) {
                case PRIMARY: bg = press ? Theme.PRIMARY_DK : hover ? new Color(0xF0653E) : Theme.PRIMARY; fg = Color.WHITE; break;
                case DANGER: bg = press ? new Color(0xB91C1C) : hover ? new Color(0xEF4444) : Theme.DANGER; fg = Color.WHITE; break;
                case SUCCESS: bg = press ? new Color(0x15803D) : hover ? new Color(0x22C55E) : Theme.OK; fg = Color.WHITE; break;
                case DARK: bg = press ? Color.BLACK : hover ? new Color(0x374151) : new Color(0x1F2937); fg = Color.WHITE; break;
                case GHOST: bg = hover ? Theme.alpha(Theme.TEXT, 14) : new Color(0, 0, 0, 0); fg = Theme.TEXT; break;
                default: bg = hover ? Theme.PRIMARY_LT : Color.WHITE; fg = Theme.PRIMARY; border = Theme.alpha(Theme.PRIMARY, 140);
            }
            if (!en) { bg = style == BtnStyle.OUTLINE || style == BtnStyle.GHOST ? Color.WHITE : new Color(0xD1D5DB); fg = new Color(0x9CA3AF); border = style == BtnStyle.OUTLINE ? Theme.BORDER : null; }
            g.setColor(bg); g.fill(new RoundRectangle2D.Double(0, 0, w, h, 12, 12));
            if (border != null) { g.setColor(border); g.setStroke(new BasicStroke(1.4f)); g.draw(new RoundRectangle2D.Double(.7, .7, w - 1.4, h - 1.4, 12, 12)); }
            setForeground(fg); g.dispose(); super.paintComponent(g0);
        }
    }

    // ------------------------------------------------------------------ text fields
    private static void fieldBg(Graphics2D g, JComponent c, boolean focus) {
        aa(g); int w = c.getWidth(), h = c.getHeight();
        g.setColor(c.isEnabled() ? Color.WHITE : new Color(0xF3F4F6)); g.fill(new RoundRectangle2D.Double(0, 0, w, h, 12, 12));
        g.setColor(focus ? Theme.PRIMARY : Theme.BORDER); g.setStroke(new BasicStroke(focus ? 2f : 1.2f)); g.draw(new RoundRectangle2D.Double(1, 1, w - 2, h - 2, 12, 12));
    }
    public static class Field extends JTextField {
        private String hint = "";
        public Field(int cols) { super(cols); style(this); }
        public Field(String text) { super(text); style(this); }
        public Field() { this(12); }
        public Field hint(String h) { hint = h; return this; }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); fieldBg(g, this, hasFocus()); g.dispose(); super.paintComponent(g0);
            if (getText().isEmpty() && !hint.isEmpty() && !hasFocus()) { Graphics2D h = (Graphics2D) g0.create(); aa(h); h.setColor(new Color(0x9CA3AF)); h.setFont(getFont()); Insets in = getInsets(); h.drawString(hint, in.left, (getHeight() + h.getFontMetrics().getAscent() - h.getFontMetrics().getDescent()) / 2); h.dispose(); }
        }
    }
    public static class PassField extends JPasswordField {
        public PassField(int cols) { super(cols); style(this); }
        @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); fieldBg(g, this, hasFocus()); g.dispose(); super.paintComponent(g0); }
    }
    private static void style(JTextField f) {
        f.setOpaque(false); f.setFont(Theme.plain(14.5f)); f.setForeground(Theme.TEXT); f.setCaretColor(Theme.PRIMARY); f.setBorder(new EmptyBorder(10, 14, 10, 14)); f.setSelectionColor(Theme.alpha(Theme.PRIMARY, 60));
        f.addFocusListener(new FocusAdapter() { public void focusGained(FocusEvent e) { f.repaint(); } public void focusLost(FocusEvent e) { f.repaint(); } });
    }

    // ------------------------------------------------------------------ combo box
    public static class Combo<T> extends JComboBox<T> {
        public Combo(T[] items) { super(items); init(); }
        public Combo() { super(); init(); }
        private void init() {
            setOpaque(false); setFont(Theme.plain(14.5f)); setForeground(Theme.TEXT); setBorder(new EmptyBorder(0, 6, 0, 0)); setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); setMaximumRowCount(12);
            setUI(new BasicComboBoxUI() {
                @Override protected JButton createArrowButton() {
                    JButton b = new JButton() { @Override public void paint(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(Theme.MUTED); g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); int cx = getWidth() / 2, cy = getHeight() / 2; g.drawLine(cx - 4, cy - 2, cx, cy + 2); g.drawLine(cx, cy + 2, cx + 4, cy - 2); g.dispose(); } };
                    b.setBorder(BorderFactory.createEmptyBorder()); b.setContentAreaFilled(false); b.setFocusPainted(false); b.setPreferredSize(new Dimension(34, 30)); return b;
                }
                @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) { }
                @Override protected ComboPopup createPopup() { BasicComboPopup p = (BasicComboPopup) super.createPopup(); p.setBorder(BorderFactory.createLineBorder(Theme.BORDER)); return p; }
            });
            setRenderer(new DefaultListCellRenderer() {
                @Override public Component getListCellRendererComponent(JList<?> l, Object v, int i, boolean sel, boolean foc) {
                    JLabel c = (JLabel) super.getListCellRendererComponent(l, v, i, sel, foc); c.setBorder(new EmptyBorder(8, 12, 8, 12)); c.setFont(Theme.plain(14.5f));
                    c.setBackground(sel ? Theme.PRIMARY_LT : Color.WHITE); c.setForeground(Theme.TEXT); return c;
                }
            });
            addFocusListener(new FocusAdapter() { public void focusGained(FocusEvent e) { repaint(); } public void focusLost(FocusEvent e) { repaint(); } });
            setPreferredSize(new Dimension(160, 42));
        }
        @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); fieldBg(g, this, hasFocus()); g.dispose(); super.paintComponent(g0); }
    }

    // ------------------------------------------------------------------ check box
    public static class Check extends JCheckBox {
        public Check(String text) {
            super(text); setOpaque(false); setFont(Theme.plain(14)); setForeground(Theme.TEXT); setFocusPainted(false); setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); setIconTextGap(10);
            Icon off = box(false), on = box(true); setIcon(off); setSelectedIcon(on);
        }
        private static Icon box(boolean sel) {
            return new Icon() {
                public int getIconWidth() { return 20; } public int getIconHeight() { return 20; }
                public void paintIcon(Component c, Graphics g0, int x, int y) {
                    Graphics2D g = (Graphics2D) g0.create(); aa(g); g.translate(x, y);
                    if (sel) { g.setColor(Theme.PRIMARY); g.fill(new RoundRectangle2D.Double(0, 0, 20, 20, 7, 7)); g.setColor(Color.WHITE); g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); g.drawLine(5, 10, 9, 14); g.drawLine(9, 14, 15, 6); }
                    else { g.setColor(Color.WHITE); g.fill(new RoundRectangle2D.Double(0, 0, 20, 20, 7, 7)); g.setColor(new Color(0xCBD5E1)); g.setStroke(new BasicStroke(1.5f)); g.draw(new RoundRectangle2D.Double(.75, .75, 18.5, 18.5, 7, 7)); }
                    g.dispose();
                }
            };
        }
    }

    // ------------------------------------------------------------------ badge / banner / stat card
    public static class Badge extends JLabel {
        private Color color;
        public Badge(String text, Color c) { super(text, CENTER); color = c; setFont(Theme.bold(11.5f)); setForeground(c); setBorder(new EmptyBorder(3, 10, 3, 10)); setOpaque(false); }
        public void set(String text, Color c) { setText(text); color = c; setForeground(c); repaint(); }
        @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(Theme.alpha(color, 28)); g.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), getHeight(), getHeight())); g.dispose(); super.paintComponent(g0); }
    }

    /** Inline message strip (replaces pop-up dialogs for feedback). */
    public static class Banner extends JPanel {
        private final JLabel text = new JLabel(); private Color color = Theme.INFO; private final javax.swing.Timer hide;
        public Banner() { super(new BorderLayout()); setOpaque(false); setBorder(new EmptyBorder(10, 16, 10, 16)); text.setFont(Theme.bold(13.5f)); add(text); setVisible(false); hide = new javax.swing.Timer(7000, e -> setVisible(false)); hide.setRepeats(false); }
        public void show(String msg, boolean ok) { color = ok ? Theme.OK : Theme.DANGER; text.setForeground(color); text.setText("<html>" + msg.replace("<", "&lt;").replace("\n", "<br>") + "</html>"); setVisible(true); hide.restart(); revalidate(); repaint(); }
        public void clear() { setVisible(false); }
        @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(Theme.alpha(color, 24)); g.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12)); g.setColor(color); g.fill(new RoundRectangle2D.Double(0, 0, 5, getHeight(), 5, 5)); g.dispose(); }
    }

    public static class StatCard extends Card {
        private final JLabel value = label("-", 26, true, Theme.TEXT), sub = muted("");
        public StatCard(String title, Icons.N icon, Color accent) {
            super(new BorderLayout(14, 0), 16);
            JLabel ic = new JLabel(Icons.get(icon, 22, accent), SwingConstants.CENTER) { @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(Theme.alpha(accent, 30)); g.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 16, 16)); g.dispose(); super.paintComponent(g0); } };
            ic.setPreferredSize(new Dimension(48, 48)); JPanel iw = transparent(new GridBagLayout()); iw.add(ic); add(iw, BorderLayout.WEST);
            JPanel t = transparent(new GridLayout(3, 1, 0, 0)); JLabel tl = label(title, 12.5f, true, Theme.MUTED); t.add(tl); t.add(value); t.add(sub); add(t);
        }
        public void set(String v, String s) { value.setText(v); sub.setText(s == null ? " " : s); }
    }

    // ------------------------------------------------------------------ tabs
    public static class Tabs extends JPanel {
        private final JPanel bar = transparent(new FlowLayout(FlowLayout.LEFT, 8, 0)), body = transparent(new CardLayout()); private final java.util.List<JToggleButton> btns = new java.util.ArrayList<>(); private final ButtonGroup grp = new ButtonGroup();
        public Consumer<String> onChange;
        public Tabs() { super(new BorderLayout(0, 14)); setOpaque(false); bar.setBorder(new EmptyBorder(0, 0, 0, 0)); add(bar, BorderLayout.NORTH); add(body); }
        public void add(String title, JComponent c) {
            JToggleButton b = new JToggleButton(title) {
                @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(isSelected() ? Theme.PRIMARY : Color.WHITE); g.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), getHeight(), getHeight())); if (!isSelected()) { g.setColor(Theme.BORDER); g.draw(new RoundRectangle2D.Double(.5, .5, getWidth() - 1, getHeight() - 1, getHeight(), getHeight())); } setForeground(isSelected() ? Color.WHITE : Theme.TEXT); g.dispose(); super.paintComponent(g0); }
            };
            b.setFont(Theme.bold(13.5f)); b.setFocusPainted(false); b.setBorderPainted(false); b.setContentAreaFilled(false); b.setOpaque(false); b.setBorder(new EmptyBorder(9, 20, 9, 20)); b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.addActionListener(e -> { ((CardLayout) body.getLayout()).show(body, title); if (onChange != null) onChange.accept(title); });
            grp.add(b); btns.add(b); bar.add(b); body.add(c, title); if (btns.size() == 1) b.setSelected(true);
        }
        public void show(String title) { for (JToggleButton b : btns) if (b.getText().equals(title)) b.doClick(); }
    }

    // ------------------------------------------------------------------ tables / scrolling
    public static JTable table(TableModel m) {
        JTable t = new JTable(m); t.setRowHeight(40); t.setShowGrid(false); t.setIntercellSpacing(new Dimension(0, 0)); t.setFillsViewportHeight(true); t.setFont(Theme.plain(13.5f)); t.setForeground(Theme.TEXT);
        t.setSelectionBackground(Theme.PRIMARY_LT); t.setSelectionForeground(Theme.TEXT); t.setFocusable(true); t.setAutoCreateRowSorter(true); t.getTableHeader().setReorderingAllowed(false);
        JTableHeader h = t.getTableHeader(); h.setPreferredSize(new Dimension(100, 40)); h.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean s, boolean f, int r, int c) {
                JLabel l = (JLabel) super.getTableCellRendererComponent(tb, v, s, f, r, c); l.setFont(Theme.bold(12)); l.setForeground(Theme.MUTED); l.setBackground(new Color(0xF1F4F9)); l.setBorder(new EmptyBorder(0, 12, 0, 12));
                l.setHorizontalAlignment(tb.getColumnModel().getColumn(c).getHeaderValue() != null && rightAlign.contains(tb.getColumnModel().getColumn(c).getHeaderValue().toString()) ? SwingConstants.RIGHT : SwingConstants.LEFT); l.setText(v == null ? "" : v.toString().toUpperCase()); return l; } });
        t.setDefaultRenderer(Object.class, new RowRenderer());
        return t;
    }
    private static final java.util.Set<String> rightAlign = new java.util.HashSet<>();
    /** Marks columns (by header title) that should be right-aligned, e.g. money and quantities. */
    public static void right(JTable t, String... headers) {
        for (String h : headers) rightAlign.add(h);
    }
    public static class RowRenderer extends DefaultTableCellRenderer {
        @Override public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, boolean foc, int r, int c) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(t, v, sel, false, r, c); l.setBorder(new EmptyBorder(0, 12, 0, 12)); l.setFont(Theme.plain(13.5f));
            l.setBackground(sel ? Theme.PRIMARY_LT : r % 2 == 0 ? Color.WHITE : Theme.ROW_ALT); l.setForeground(Theme.TEXT);
            Object hv = t.getColumnModel().getColumn(c).getHeaderValue(); boolean right = hv != null && rightAlign.contains(hv.toString()); l.setHorizontalAlignment(right ? SwingConstants.RIGHT : SwingConstants.LEFT);
            String s = v == null ? "" : v.toString();
            if (s.equals("LOW") || s.equals("REORDER") || s.equals("SLOW-MOVING") || s.equals("OUT")) { l.setForeground(s.equals("LOW") ? Theme.WARN : Theme.DANGER); l.setFont(Theme.bold(12.5f)); }
            else if (s.equals("OK") || s.equals("BEST-SELLER")) { l.setForeground(Theme.OK); l.setFont(Theme.bold(12.5f)); }
            else if (s.startsWith("+") && s.length() > 1 && Character.isDigit(s.charAt(1))) l.setForeground(Theme.OK);
            else if (s.startsWith("-") && s.length() > 1 && Character.isDigit(s.charAt(1)) && right) l.setForeground(Theme.DANGER);
            return l;
        }
    }
    public static DefaultTableModel model(String... cols) { return new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } }; }

    public static JScrollPane scroll(Component c) {
        JScrollPane sp = new JScrollPane(c); sp.setBorder(BorderFactory.createEmptyBorder()); sp.getViewport().setBackground(Color.WHITE); sp.getVerticalScrollBar().setUnitIncrement(24); sp.getVerticalScrollBar().setUI(new Thin()); sp.getHorizontalScrollBar().setUI(new Thin());
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10)); sp.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10)); return sp;
    }
    public static JScrollPane tableScroll(JTable t) { JScrollPane sp = scroll(t); sp.setBorder(BorderFactory.createLineBorder(Theme.BORDER)); return sp; }
    static class Thin extends BasicScrollBarUI {
        @Override protected void configureScrollBarColors() { thumbColor = new Color(0xCBD2DC); trackColor = new Color(0, 0, 0, 0); }
        @Override protected JButton createDecreaseButton(int o) { return zero(); } @Override protected JButton createIncreaseButton(int o) { return zero(); }
        private JButton zero() { JButton b = new JButton(); b.setPreferredSize(new Dimension(0, 0)); return b; }
        @Override protected void paintThumb(Graphics g0, JComponent c, Rectangle r) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(thumbColor); g.fillRoundRect(r.x + 2, r.y + 2, r.width - 4, r.height - 4, 8, 8); g.dispose(); }
        @Override protected void paintTrack(Graphics g, JComponent c, Rectangle r) { }
    }

    // ------------------------------------------------------------------ layout helpers
    public static JPanel form(JComponent... pairs) { JPanel p = transparent(new GridBagLayout()); return p; }
    public static GridBagConstraints gbc(int x, int y, double wx, double wy) { GridBagConstraints c = new GridBagConstraints(); c.gridx = x; c.gridy = y; c.weightx = wx; c.weighty = wy; c.fill = GridBagConstraints.BOTH; c.insets = new Insets(0, 0, 0, 0); return c; }
    public static JPanel vbox() { JPanel p = transparent(null); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); return p; }
    public static <T extends JComponent> T left(T c) { c.setAlignmentX(Component.LEFT_ALIGNMENT); return c; }
    public static Dimension size(JComponent c, int w, int h) { c.setPreferredSize(new Dimension(w, h)); return c.getPreferredSize(); }

    /** Flow layout that wraps to the container width (used for the POS menu tiles). */
    public static class WrapLayout extends FlowLayout {
        public WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }
        @Override public Dimension preferredLayoutSize(Container t) { return size(t, true); }
        @Override public Dimension minimumLayoutSize(Container t) { Dimension d = size(t, false); d.width -= getHgap() + 1; return d; }
        private Dimension size(Container target, boolean pref) {
            synchronized (target.getTreeLock()) {
                int tw = target.getSize().width; Container c = target; while (c.getSize().width == 0 && c.getParent() != null) c = c.getParent(); tw = c.getSize().width; if (tw == 0) tw = Integer.MAX_VALUE;
                Insets in = target.getInsets(); int hg = getHgap(), vg = getVgap(), max = tw - (in.left + in.right + hg * 2);
                Dimension dim = new Dimension(0, 0); int rw = 0, rh = 0;
                for (int i = 0; i < target.getComponentCount(); i++) {
                    Component m = target.getComponent(i); if (!m.isVisible()) continue; Dimension d = pref ? m.getPreferredSize() : m.getMinimumSize();
                    if (rw + d.width > max && rw > 0) { dim.width = Math.max(dim.width, rw); dim.height += rh + vg; rw = 0; rh = 0; }
                    if (rw != 0) rw += hg; rw += d.width; rh = Math.max(rh, d.height);
                }
                dim.width = Math.max(dim.width, rw); dim.height += rh; dim.width += in.left + in.right + hg * 2; dim.height += in.top + in.bottom + vg * 2; return dim;
            }
        }
    }
    public static class WrapPanel extends JPanel implements Scrollable {
        public WrapPanel(int hg, int vg) { super(new WrapLayout(FlowLayout.LEFT, hg, vg)); setOpaque(false); }
        public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        public int getScrollableUnitIncrement(Rectangle r, int o, int d) { return 24; } public int getScrollableBlockIncrement(Rectangle r, int o, int d) { return 120; }
        public boolean getScrollableTracksViewportWidth() { return true; } public boolean getScrollableTracksViewportHeight() { return false; }
    }
}
