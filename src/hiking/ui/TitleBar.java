package hiking.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/** Custom window bar for the borderless frame: drag to move, double-click to maximize, minimize / maximize / close buttons. */
public final class TitleBar extends JPanel {
    private static final int H = 40;
    private final JFrame frame; private Point dragFrom; private final boolean maxButtons;

    public TitleBar(JFrame frame, boolean maxButtons) {
        super(null); this.frame = frame; this.maxButtons = maxButtons; setOpaque(true); setBackground(Theme.SIDEBAR); setPreferredSize(new Dimension(100, H));
        setLayout(new BorderLayout());
        JPanel brand = new JPanel() { @Override protected void paintComponent(Graphics g0) { Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); Brand.logo(g, 12, 9, 22); g.setColor(new Color(255, 255, 255, 215)); g.setFont(Theme.bold(12.5f)); g.drawString("1/2 HI KING CORP", 44, 25); g.setColor(new Color(255, 255, 255, 110)); g.setFont(Theme.plain(12)); g.drawString("Restaurant Management & Knowledge Analysis", 170, 25); g.dispose(); } };
        brand.setOpaque(false); add(brand);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0)); btns.setOpaque(false);
        btns.add(new WinBtn(0)); if (maxButtons) btns.add(new WinBtn(1)); btns.add(new WinBtn(2)); add(btns, BorderLayout.EAST);

        MouseAdapter ma = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) { dragFrom = e.getPoint(); }
            @Override public void mouseDragged(MouseEvent e) {
                if (dragFrom == null || maximized()) return; Point p = e.getLocationOnScreen(); frame.setLocation(p.x - dragFrom.x, p.y - dragFrom.y);
            }
            @Override public void mouseClicked(MouseEvent e) { if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) toggleMax(); }
        };
        brand.addMouseListener(ma); brand.addMouseMotionListener(ma);
        frame.addWindowStateListener(e -> repaint());
        installFallbackClose();
    }

    /**
     * Safety net: if the title bar is ever not visible (window taller than the screen, odd scaling, ...), a small X
     * appears in the top-right corner of the window content so the program can always be closed.
     */
    private void installFallbackClose() {
        JButton x = new JButton("\u2715");
        x.setFocusPainted(false); x.setBorderPainted(false); x.setContentAreaFilled(false); x.setOpaque(false); x.setForeground(Theme.TEXT); x.setFont(Theme.bold(18f)); x.setToolTipText("Close"); x.setVisible(false);
        x.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        x.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        frame.getLayeredPane().add(x, JLayeredPane.POPUP_LAYER);
        Runnable check = () -> SwingUtilities.invokeLater(() -> {
            boolean ok = false;
            try {
                GraphicsConfiguration gc = frame.getGraphicsConfiguration();
                ok = isShowing() && getHeight() >= H - 2 && (gc == null || getLocationOnScreen().y >= gc.getBounds().y - 1);
            } catch (IllegalComponentStateException ignored) { }
            x.setBounds(frame.getLayeredPane().getWidth() - 52, 6, 44, 36); x.setVisible(!ok);
        });
        frame.addComponentListener(new ComponentAdapter() { @Override public void componentResized(ComponentEvent e) { check.run(); } @Override public void componentMoved(ComponentEvent e) { check.run(); } @Override public void componentShown(ComponentEvent e) { check.run(); } });
        frame.addWindowListener(new WindowAdapter() { @Override public void windowOpened(WindowEvent e) { check.run(); } });
    }

    private boolean maxed; private Rectangle restore;
    private boolean maximized() { return maxed; }
    /** Usable area of the monitor the window is on (screen minus taskbar), so the bar can never end up above the top edge. */
    private Rectangle screen() {
        GraphicsConfiguration gc = frame.getGraphicsConfiguration();
        if (gc == null) return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        Rectangle b = gc.getBounds(); Insets i = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        return new Rectangle(b.x + i.left, b.y + i.top, b.width - i.left - i.right, b.height - i.top - i.bottom);
    }
    /** Fills the usable screen area (respects the taskbar). Done by bounds so it works on every window manager. */
    public void maximize() { if (!maxed) restore = frame.getBounds(); maxed = true; frame.setBounds(screen()); repaint(); }
    public void toggleMax() {
        if (maxed) {
            maxed = false; Rectangle r = screen(); int w = (int) (r.width * .82), h = (int) (r.height * .85);
            frame.setBounds(restore != null && restore.width > 400 && !restore.equals(r) ? restore : new Rectangle(r.x + (r.width - w) / 2, r.y + (r.height - h) / 2, w, h)); repaint();
        } else maximize();
    }

    /** kind: 0 minimize, 1 maximize / restore, 2 close. */
    private final class WinBtn extends JComponent {
        private final int kind; private boolean hover;
        WinBtn(int kind) { this.kind = kind; setPreferredSize(new Dimension(52, H)); setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setToolTipText(kind == 0 ? "Minimize" : kind == 1 ? "Maximize / Restore" : "Close");
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { hover = true; repaint(); } public void mouseExited(MouseEvent e) { hover = false; repaint(); }
                public void mouseClicked(MouseEvent e) {
                    if (kind == 0) frame.setExtendedState(frame.getExtendedState() | JFrame.ICONIFIED);
                    else if (kind == 1) toggleMax(); else frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                } });
        }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight();
            if (hover) { g.setColor(kind == 2 ? new Color(0xE81123) : new Color(255, 255, 255, 28)); g.fillRect(0, 0, w, h); }
            g.setColor(Color.WHITE); g.setStroke(new BasicStroke(1.4f)); int cx = w / 2, cy = h / 2;
            if (kind == 0) g.drawLine(cx - 5, cy + 1, cx + 5, cy + 1);
            else if (kind == 1) { if (maximized()) { g.drawRect(cx - 5, cy - 2, 7, 7); g.drawLine(cx - 3, cy - 4, cx + 5, cy - 4); g.drawLine(cx + 5, cy - 4, cx + 5, cy + 3); } else g.drawRect(cx - 5, cy - 5, 10, 10); }
            else { g.setStroke(new BasicStroke(1.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); g.drawLine(cx - 5, cy - 5, cx + 5, cy + 5); g.drawLine(cx + 5, cy - 5, cx - 5, cy + 5); }
            g.dispose();
        }
    }
}
