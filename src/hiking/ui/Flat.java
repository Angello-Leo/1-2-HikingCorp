package hiking.ui;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * The flat, rounded look: card panels, text-box borders, buttons, combo boxes, tabs, check boxes and scroll bars.
 * {@link Util#skin(Container)} applies these to the plain Swing components of every form at run time.
 * Radii, line colours and paddings are the constants at the top of each class - change them here to restyle the whole app.
 */
public final class Flat {
    private Flat() {}
    public static final int CARD_ARC = 16, FIELD_ARC = 20, BUTTON_ARC = 14;
    public static final Color LINE = new Color(0xD8C7B2), LINE_HOVER = new Color(0xC9B59C);
    /** Fill colour of the card a component sits in (white, or beige on the table pages). */
    public static Color fillFor(Component c) { for (Container p = c instanceof Container ? c.getParent() : null; p != null; p = p.getParent()) if (p instanceof JComponent && ((JComponent) p).getBorder() instanceof CardBorder) return ((CardBorder) ((JComponent) p).getBorder()).fill; return null; }
    /** Fill used for new cards; Util.skin sets it to beige for the table-style pages. */
    static Color pageCard = Color.WHITE;

    static void aa(Graphics2D g) { g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE); }
    static double lum(Color c) { return (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()) / 255.0; }
    static Color mix(Color c, Color to, double t) { return new Color((int) (c.getRed() + (to.getRed() - c.getRed()) * t), (int) (c.getGreen() + (to.getGreen() - c.getGreen()) * t), (int) (c.getBlue() + (to.getBlue() - c.getBlue()) * t), c.getAlpha()); }

    // ------------------------------------------------------------------------------------------ cards
    /** Rounded white card with a hairline outline; the insets are the padding inside the card. */
    /** Fills the component with the sidebar gradient (top to bottom) and then draws {@code inner} (keeps the original padding). */
    public static final class GradientBorder extends AbstractBorder {
        private final javax.swing.border.Border inner;
        public GradientBorder(javax.swing.border.Border inner) { this.inner = inner; }
        @Override public void paintBorder(Component c, Graphics g0, int x, int y, int w, int h) {
            Graphics2D g = (Graphics2D) g0.create(); g.setPaint(new GradientPaint(0, 0, Theme.SIDEBAR, 0, h, Theme.SIDEBAR_END)); g.fillRect(x, y, w, h); g.dispose();
            if (inner != null) inner.paintBorder(c, g0, x, y, w, h);
        }
        @Override public Insets getBorderInsets(Component c) { return inner == null ? new Insets(0, 0, 0, 0) : inner.getBorderInsets(c); }
    }

    public static final class CardBorder extends AbstractBorder {
        private final Insets in; final Color fill;
        public CardBorder(Insets in) { this(in, pageCard); }
        public CardBorder(Insets in, Color fill) { this.in = in; this.fill = fill; }
        @Override public void paintBorder(Component c, Graphics g0, int x, int y, int w, int h) {
            Graphics2D g = (Graphics2D) g0.create(); aa(g);
            for (int i = 4; i >= 1; i--) { g.setColor(new Color(40, 60, 90, 9)); g.fill(new RoundRectangle2D.Double(x + 1 - i * .5, y + 1 + i * .8, w - 2 + i, h - 4, CARD_ARC + i, CARD_ARC + i)); }
            g.setColor(fill); g.fill(new RoundRectangle2D.Double(x + 1, y + 1, w - 2, h - 5, CARD_ARC, CARD_ARC)); g.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return (Insets) in.clone(); }
        @Override public Insets getBorderInsets(Component c, Insets i) { i.set(in.top, in.left, in.bottom, in.right); return i; }
        @Override public boolean isBorderOpaque() { return false; }
    }

    // ------------------------------------------------------------------------------------------ text boxes / combo boxes
    /** Rounded white input outline; turns orange while the component has the keyboard focus. */
    public static final class FieldBorder extends AbstractBorder {
        private final int v, h;
        public FieldBorder(int v, int h) { this.v = v; this.h = h; }
        @Override public void paintBorder(Component c, Graphics g0, int x, int y, int w, int hh) {
            Graphics2D g = (Graphics2D) g0.create(); aa(g);
            boolean focus = c.hasFocus() || (c instanceof JComboBox && ((JComboBox<?>) c).isPopupVisible());
            // the box itself is opaque; paint the page colour into the four corners so the rounding shows
            Color page = fillFor(c); Container p = c.getParent(); while (page == null && p != null && !p.isOpaque()) p = p.getParent();
            if (page == null && p != null) page = p.getBackground();
            if (page != null) { java.awt.geom.Area corners = new java.awt.geom.Area(new Rectangle(x, y, w, hh)); corners.subtract(new java.awt.geom.Area(new RoundRectangle2D.Double(x, y, w, hh, FIELD_ARC, FIELD_ARC))); g.setColor(page); g.fill(corners); }
            if (c instanceof javax.swing.text.JTextComponent && ((JComponent) c).getClientProperty("placeholder") != null && ((javax.swing.text.JTextComponent) c).getDocument().getLength() == 0) {
                g.setFont(c.getFont()); g.setColor(new Color(0x6B6459)); FontMetrics fm = g.getFontMetrics(); g.drawString(String.valueOf(((JComponent) c).getClientProperty("placeholder")), x + this.h, y + (hh + fm.getAscent() - fm.getDescent()) / 2);
            }
            g.setColor(focus ? Theme.PRIMARY : mix(c.getBackground(), Color.BLACK, 0.10)); g.setStroke(new BasicStroke(focus ? 1.6f : 1f)); g.draw(new RoundRectangle2D.Double(x + .8, y + .8, w - 1.6, hh - 1.6, FIELD_ARC, FIELD_ARC)); g.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(v, h, v, h); }
        @Override public boolean isBorderOpaque() { return true; }
    }

    public static void textField(javax.swing.text.JTextComponent f) {
        f.setOpaque(true); Color cf = fillFor(f); boolean onBeige = cf != null && cf.equals(Theme.BEIGE); f.setBackground(!f.isEnabled() ? (onBeige ? new Color(0xF3EBE0) : new Color(0xDCCDBB)) : onBeige ? Color.WHITE : Theme.BEIGE); f.setBorder(new FieldBorder(8, 16)); f.setForeground(Theme.TEXT); f.setCaretColor(Theme.PRIMARY); f.setSelectionColor(Theme.alpha(Theme.PRIMARY, 70));
        if (f.getClientProperty("flat.focus") == null) { f.putClientProperty("flat.focus", Boolean.TRUE); f.addFocusListener(new FocusAdapter() { public void focusGained(FocusEvent e) { f.repaint(); } public void focusLost(FocusEvent e) { f.repaint(); } }); }
    }

    public static void combo(JComboBox<?> cb) {
        cb.setOpaque(true); Color cf = fillFor(cb); boolean onBeige = cf != null && cf.equals(Theme.BEIGE); cb.setBackground(onBeige ? Color.WHITE : Theme.BEIGE); cb.setBorder(new FieldBorder(2, 10)); cb.setUI(new ComboUI()); cb.setMaximumRowCount(12); cb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cb.setRenderer(new DefaultListCellRenderer() {
            @Override public Component getListCellRendererComponent(JList<?> l, Object v, int i, boolean sel, boolean foc) {
                JLabel c = (JLabel) super.getListCellRendererComponent(l, v, i, sel, foc); c.setBorder(new EmptyBorder(7, 12, 7, 12)); c.setBackground(sel ? Theme.PRIMARY_LT : Theme.BEIGE); c.setForeground(Theme.TEXT); return c;
            }
        });
        cb.addFocusListener(new FocusAdapter() { public void focusGained(FocusEvent e) { cb.repaint(); } public void focusLost(FocusEvent e) { cb.repaint(); } });
    }
    static final class ComboUI extends BasicComboBoxUI {
        @Override protected JButton createArrowButton() {
            JButton b = new JButton() { @Override public void paint(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); aa(g); g.setColor(Theme.MUTED); g.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); int cx = getWidth() / 2, cy = getHeight() / 2; g.drawLine(cx - 4, cy - 2, cx, cy + 2); g.drawLine(cx, cy + 2, cx + 4, cy - 2); g.dispose(); } };
            b.setBorder(BorderFactory.createEmptyBorder()); b.setContentAreaFilled(false); b.setFocusPainted(false); b.setOpaque(false); b.setPreferredSize(new Dimension(30, 26)); return b;
        }
        @Override public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) { }
        @Override protected ComboPopup createPopup() { BasicComboPopup p = (BasicComboPopup) super.createPopup(); p.setBorder(BorderFactory.createLineBorder(LINE_HOVER)); return p; }
    }

    // ------------------------------------------------------------------------------------------ buttons
    public static void button(AbstractButton b) {
        if (!(b.getUI() instanceof ButtonUI)) b.setUI(new ButtonUI());
        b.setFocusPainted(false); b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); b.setRolloverEnabled(true);
    }
    /** Rounded button painted from its own background colour (set it in the Design tab); darker when pressed, lighter / darker on hover. */
    public static final class ButtonUI extends BasicButtonUI {
        @Override public void installUI(JComponent c) { super.installUI(c); AbstractButton b = (AbstractButton) c; b.setOpaque(false); b.setContentAreaFilled(false); b.setBorderPainted(false); b.setRolloverEnabled(true); }
        @Override public void paint(Graphics g0, JComponent c) {
            AbstractButton b = (AbstractButton) c; Graphics2D g = (Graphics2D) g0.create(); aa(g); ButtonModel m = b.getModel(); Color bg = b.getBackground(); boolean en = b.isEnabled(), outline = lum(bg) > 0.93;
            int w = c.getWidth(), h = c.getHeight(), arc = Math.min(BUTTON_ARC * 2, h - 2);
            if (outline) { bg = Theme.BEIGE; b.setForeground(Theme.SLATE); }
            else if (lum(bg) < 0.2 && bg.getAlpha() > 0 && !bg.equals(Theme.SLATE) && !Boolean.TRUE.equals(b.getClientProperty("nav"))) bg = Theme.PRIMARY;   // dark buttons of the old design become orange   // secondary buttons: beige with slate text
            Color fill = bg; boolean nav = Boolean.TRUE.equals(b.getClientProperty("nav")), slate = bg.equals(Theme.SLATE);
            if (!en) fill = outline ? mix(Theme.BEIGE, Color.WHITE, .4) : mix(bg, Color.WHITE, .55);
            else if (m.isPressed()) fill = nav ? new Color(0, 0, 0, 45) : mix(bg, Color.BLACK, 0.14);
            else if (m.isRollover()) fill = nav ? new Color(255, 255, 255, 48) : slate ? mix(bg, Color.WHITE, .08) : mix(bg, Color.BLACK, 0.06);
            if (bg.getAlpha() > 0 && fill.getAlpha() > 0 && !(nav && !m.isRollover() && !m.isPressed() && !b.isSelected())) {
                if (slate && en) { for (int i = 3; i >= 1; i--) { g.setColor(new Color(0, 0, 0, 22)); g.fill(new RoundRectangle2D.Double(0, i, w, h - 2, arc, arc)); } g.setColor(fill); g.fill(new RoundRectangle2D.Double(0, 0, w, h - 3, arc, arc)); }
                else { g.setColor(fill); g.fill(new RoundRectangle2D.Double(0, 0, w, h, arc, arc)); }
            }
            g.dispose(); super.paint(g0, c);
        }
        @Override protected void paintText(Graphics g, JComponent c, Rectangle r, String t) {
            AbstractButton b = (AbstractButton) c; FontMetrics fm = c.getFontMetrics(c.getFont()); g.setFont(c.getFont()); g.setColor(b.isEnabled() ? b.getForeground() : new Color(0x7A8494));
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); g.drawString(t, r.x, r.y + fm.getAscent());
        }
    }

    // ------------------------------------------------------------------------------------------ check boxes
    public static void check(JCheckBox c) { c.setIcon(box(false)); c.setSelectedIcon(box(true)); c.setIconTextGap(10); c.setOpaque(false); c.setFocusPainted(false); c.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); }
    private static Icon box(boolean sel) {
        return new Icon() {
            public int getIconWidth() { return 20; } public int getIconHeight() { return 20; }
            public void paintIcon(Component c, Graphics g0, int x, int y) {
                Graphics2D g = (Graphics2D) g0.create(); aa(g); g.translate(x, y);
                if (sel) { g.setColor(Theme.PRIMARY); g.fill(new RoundRectangle2D.Double(0, 0, 20, 20, 7, 7)); g.setColor(Color.WHITE); g.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); g.drawLine(5, 10, 8, 13); g.drawLine(8, 13, 15, 6); }
                else { g.setColor(Theme.BEIGE); g.fill(new RoundRectangle2D.Double(0, 0, 20, 20, 7, 7)); g.setColor(new Color(0xB9A58C)); g.setStroke(new BasicStroke(1.5f)); g.draw(new RoundRectangle2D.Double(.75, .75, 18.5, 18.5, 7, 7)); }
                g.dispose();
            }
        };
    }

    // ------------------------------------------------------------------------------------------ tabs
    public static void tabs(JTabbedPane t) {
        t.setUI(new TabsUI()); t.setOpaque(false); t.setFocusable(false);
        Runnable paint = () -> { for (int i = 0; i < t.getTabCount(); i++) t.setForegroundAt(i, i == t.getSelectedIndex() ? Theme.TEXT : Theme.MUTED); };
        paint.run(); t.addChangeListener(e -> paint.run());
    }
    /** Underline tabs: bold label, orange bar under the selected one and a hairline under the strip. */
    static final class TabsUI extends BasicTabbedPaneUI {
        @Override protected void installDefaults() { super.installDefaults(); tabInsets = new Insets(10, 18, 10, 18); selectedTabPadInsets = new Insets(0, 0, 0, 0); tabAreaInsets = new Insets(0, 0, 14, 0); contentBorderInsets = new Insets(0, 0, 0, 0); }
        @Override protected void paintTabBackground(Graphics g, int p, int i, int x, int y, int w, int h, boolean sel) { if (sel) { Graphics2D g2 = (Graphics2D) g.create(); aa(g2); g2.setColor(Theme.PRIMARY); g2.fill(new RoundRectangle2D.Double(x + 12, y + h - 3, w - 24, 3, 3, 3)); g2.dispose(); } }
        @Override protected void paintTabBorder(Graphics g, int p, int i, int x, int y, int w, int h, boolean sel) { }
        @Override protected void paintFocusIndicator(Graphics g, int p, Rectangle[] r, int i, Rectangle ir, Rectangle tr, boolean sel) { }
        @Override protected void paintContentBorderTopEdge(Graphics g, int p, int sel, int x, int y, int w, int h) { g.setColor(LINE); g.drawLine(x, y - 15, x + w, y - 15); }
        @Override protected void paintContentBorderLeftEdge(Graphics g, int p, int sel, int x, int y, int w, int h) { }
        @Override protected void paintContentBorderBottomEdge(Graphics g, int p, int sel, int x, int y, int w, int h) { }
        @Override protected void paintContentBorderRightEdge(Graphics g, int p, int sel, int x, int y, int w, int h) { }
        @Override protected int calculateTabHeight(int p, int i, int fh) { return super.calculateTabHeight(p, i, fh) + 2; }
    }

    // ------------------------------------------------------------------------------------------ scroll panes
    public static void scroll(JScrollPane sp, boolean inCard) {
        sp.setBorder(inCard ? BorderFactory.createEmptyBorder() : BorderFactory.createLineBorder(LINE)); { Color vf = fillFor(sp); sp.getViewport().setBackground(vf != null ? vf : Color.WHITE); } sp.getVerticalScrollBar().setUnitIncrement(24);
        sp.getVerticalScrollBar().setUI(new UI.Thin()); sp.getHorizontalScrollBar().setUI(new UI.Thin());
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10)); sp.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10)); sp.setOpaque(false);
    }
}
