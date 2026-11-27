package hiking.ui;

import java.awt.*;
import java.awt.geom.*;

/**
 * Left panel of the sign-in and sign-up windows: beige page with the slate and orange shapes of the design,
 * the headline, and the company logo. Everything is drawn in code, so it scales with the window.
 * Colours are in {@link Theme}; the shapes are the Path2D lines in {@link #paintComponent}.
 */
public final class AuthSide extends javax.swing.JPanel {
    private final String tagline;
    public AuthSide(String tagline) { this.tagline = tagline; setOpaque(true); setBackground(Theme.BEIGE); }

    private static Path2D path(double sx, double sy, double... p) {   // p: M x y, then groups of 6 (cubic) ; closed by caller
        Path2D.Double d = new Path2D.Double(); d.moveTo(p[0] * sx, p[1] * sy);
        for (int i = 2; i + 5 < p.length; i += 6) d.curveTo(p[i] * sx, p[i + 1] * sy, p[i + 2] * sx, p[i + 3] * sy, p[i + 4] * sx, p[i + 5] * sy);
        return d;
    }
    private void shape(Graphics2D g, Shape s, Color fill) {
        if (fill != null) { g.setColor(fill); g.fill(s); }
        g.setColor(Color.BLACK); g.setStroke(new BasicStroke(2.6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); g.draw(s);
    }

    @Override protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create(); UI.aa(g);
        int w = getWidth(), h = getHeight(); double sx = w / 506.0, sy = h / 1024.0;
        // slate blob, top-left
        Path2D a = path(sx, sy, 0, 0, 55, 10, 100, 50, 92, 100, 85, 150, 40, 190, 55, 250, 70, 310, 120, 360, 100, 385, 80, 410, 30, 430, 0, 432); a.closePath(); shape(g, a, Theme.SLATE);
        // orange blob, top-right + small slate dot
        Path2D b = path(sx, sy, 506, 0, 506, 0, 360, 0, 360, 0, 350, 60, 380, 100, 506, 80); b.lineTo(w, 0); b.closePath(); shape(g, b, Theme.PRIMARY);
        shape(g, new Ellipse2D.Double(366 * sx, 72 * sy, 64 * sx, 56 * sy), Theme.SLATE);
        // orange egg and thin orange ring
        shape(g, new Ellipse2D.Double(388 * sx, 442 * sy, 108 * sx, 92 * sy), Theme.PRIMARY);
        g.setColor(Theme.PRIMARY); g.setStroke(new BasicStroke(2.2f)); g.draw(new Ellipse2D.Double(-200 * sx, 660 * sy, 365 * sx, 400 * sy));
        // slate hill, bottom
        Path2D c = path(sx, sy, 0, 1024, 60, 990, 140, 930, 200, 870, 260, 800, 330, 760, 410, 768, 450, 772, 480, 780, 506, 790); c.lineTo(w, h); c.lineTo(0, h); c.closePath(); shape(g, c, Theme.SLATE);

        // headline
        float fs = Math.max(26f, Math.min(h * 0.048f, w * 0.082f)); Font f = Theme.bold(fs); g.setFont(f); FontMetrics fm = g.getFontMetrics();
        int x = (int) (88 * sx), y = (int) (232 * sy);   // below the orange blob and the small slate dot, so nothing touches the text
        g.setColor(Theme.TEXT); g.drawString("Get Started With", x, y);
        g.drawString("Your ", x, y + fm.getHeight() + 2); g.setColor(Theme.PRIMARY); g.drawString("Account", x + fm.stringWidth("Your "), y + fm.getHeight() + 2);
        // logo
        java.awt.image.BufferedImage logo = Brand.inkTransparent();
        int ly = (int) (360 * sy);
        if (logo != null) {
            int lw = (int) Math.min(w * 0.70, h * 0.34 * logo.getWidth() / logo.getHeight()), lh = lw * logo.getHeight() / logo.getWidth();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.drawImage(logo, (w - lw) / 2, ly, lw, lh, null); ly += lh;
        }
        if (tagline != null) {
            g.setFont(Theme.plain(Math.max(13f, fs * 0.30f))); fm = g.getFontMetrics(); g.setColor(Theme.TEXT);
            String[] lines = tagline.split("\n"); int ty = Math.max(ly + 24, (int) (640 * sy));
            for (String s : lines) { g.drawString(s, (w - fm.stringWidth(s)) / 2, ty); ty += fm.getHeight(); }
        }
        g.dispose();
    }
}
