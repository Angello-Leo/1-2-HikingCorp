package hiking.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/** Small vector icons drawn with Java2D (no image files needed). */
public final class Icons {
    private Icons() {}
    public enum N { DASHBOARD, BOX, CART, RECEIPT, USERS, SHIELD, KEY, LOGOUT, TREND, CLOCK, STAR, BAG, SPARK, PLUS, SEARCH, ALERT, COIN, BOLT }

    public static Icon get(N n, int size, Color c) {
        return new Icon() {
            @Override public int getIconWidth() { return size; } @Override public int getIconHeight() { return size; }
            @Override public void paintIcon(Component comp, Graphics g0, int x, int y) {
                Graphics2D g = (Graphics2D) g0.create(); g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                g.translate(x, y); g.scale(size / 24.0, size / 24.0); g.setColor(c); g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); draw(g, n); g.dispose();
            }
        };
    }
    private static void rr(Graphics2D g, double x, double y, double w, double h, double r) { g.draw(new RoundRectangle2D.Double(x, y, w, h, r, r)); }
    private static void fr(Graphics2D g, double x, double y, double w, double h, double r) { g.fill(new RoundRectangle2D.Double(x, y, w, h, r, r)); }
    private static void ln(Graphics2D g, double a, double b, double c, double d) { g.draw(new Line2D.Double(a, b, c, d)); }

    private static void draw(Graphics2D g, N n) {
        switch (n) {
            case DASHBOARD: fr(g, 3, 3, 8, 10, 2.5); fr(g, 13, 3, 8, 6, 2.5); fr(g, 13, 11, 8, 10, 2.5); fr(g, 3, 15, 8, 6, 2.5); break;
            case BOX: g.draw(new Path2D.Double() {{ moveTo(12, 3); lineTo(20, 7.5); lineTo(20, 16.5); lineTo(12, 21); lineTo(4, 16.5); lineTo(4, 7.5); closePath(); }}); ln(g, 4, 7.5, 12, 12); ln(g, 20, 7.5, 12, 12); ln(g, 12, 12, 12, 21); break;
            case CART: g.draw(new Path2D.Double() {{ moveTo(2.5, 4); lineTo(5, 4); lineTo(7, 15); lineTo(18, 15); lineTo(20, 7); lineTo(6, 7); }}); g.fill(new Ellipse2D.Double(8, 18, 3, 3)); g.fill(new Ellipse2D.Double(15, 18, 3, 3)); break;
            case RECEIPT: g.draw(new Path2D.Double() {{ moveTo(5, 3); lineTo(19, 3); lineTo(19, 21); lineTo(16.5, 19); lineTo(14, 21); lineTo(12, 19); lineTo(10, 21); lineTo(7.5, 19); lineTo(5, 21); closePath(); }}); ln(g, 8.5, 8, 15.5, 8); ln(g, 8.5, 12, 15.5, 12); break;
            case USERS: g.draw(new Ellipse2D.Double(8, 3.5, 7, 7)); g.draw(new Arc2D.Double(4, 13, 15, 14, 0, 180, Arc2D.OPEN)); break;
            case SHIELD: g.draw(new Path2D.Double() {{ moveTo(12, 3); lineTo(20, 6); lineTo(20, 12); curveTo(20, 17, 16, 20, 12, 21.5); curveTo(8, 20, 4, 17, 4, 12); lineTo(4, 6); closePath(); }}); ln(g, 8.5, 12, 11, 14.5); ln(g, 11, 14.5, 16, 9); break;
            case KEY: g.draw(new Ellipse2D.Double(3.5, 12, 7, 7)); ln(g, 9, 13, 19, 4); ln(g, 15.5, 7.5, 18, 10); break;
            case LOGOUT: g.draw(new Path2D.Double() {{ moveTo(10, 4); lineTo(4, 4); lineTo(4, 20); lineTo(10, 20); }}); ln(g, 9, 12, 20, 12); ln(g, 16, 8, 20, 12); ln(g, 16, 16, 20, 12); break;
            case TREND: g.draw(new Path2D.Double() {{ moveTo(3, 17); lineTo(9, 11); lineTo(13, 15); lineTo(21, 6); }}); ln(g, 15.5, 6, 21, 6); ln(g, 21, 6, 21, 11.5); break;
            case CLOCK: g.draw(new Ellipse2D.Double(3, 3, 18, 18)); ln(g, 12, 7, 12, 12); ln(g, 12, 12, 16, 14); break;
            case STAR: { Path2D.Double p = new Path2D.Double(); for (int i = 0; i < 10; i++) { double a = -Math.PI / 2 + i * Math.PI / 5, r = i % 2 == 0 ? 9.5 : 4.2; double px = 12 + r * Math.cos(a), py = 12.5 + r * Math.sin(a); if (i == 0) p.moveTo(px, py); else p.lineTo(px, py); } p.closePath(); g.draw(p); break; }
            case BAG: rr(g, 4, 8, 16, 13, 3); g.draw(new Arc2D.Double(8, 3, 8, 10, 0, 180, Arc2D.OPEN)); break;
            case SPARK: g.fill(new Path2D.Double() {{ moveTo(12, 2.5); curveTo(12.8, 8.5, 15.5, 11.2, 21.5, 12); curveTo(15.5, 12.8, 12.8, 15.5, 12, 21.5); curveTo(11.2, 15.5, 8.5, 12.8, 2.5, 12); curveTo(8.5, 11.2, 11.2, 8.5, 12, 2.5); closePath(); }}); break;
            case PLUS: ln(g, 12, 5, 12, 19); ln(g, 5, 12, 19, 12); break;
            case SEARCH: g.draw(new Ellipse2D.Double(4, 4, 11, 11)); ln(g, 13, 13, 20, 20); break;
            case ALERT: g.draw(new Path2D.Double() {{ moveTo(12, 3.5); lineTo(21.5, 20); lineTo(2.5, 20); closePath(); }}); ln(g, 12, 9.5, 12, 14.5); g.fill(new Ellipse2D.Double(11, 16.3, 2, 2)); break;
            case COIN: g.draw(new Ellipse2D.Double(3, 3, 18, 18)); g.draw(new Path2D.Double() {{ moveTo(15, 9); curveTo(13, 7, 9, 8, 9.5, 10.5); curveTo(10, 13, 14.5, 11.5, 14.5, 14); curveTo(14.5, 16.5, 10.5, 17, 9, 15); }}); ln(g, 12, 6, 12, 7.5); ln(g, 12, 16.5, 12, 18); break;
            case BOLT: g.fill(new Path2D.Double() {{ moveTo(13.5, 2.5); lineTo(5, 13.5); lineTo(11, 13.5); lineTo(10, 21.5); lineTo(19, 10); lineTo(12.8, 10); closePath(); }}); break;
        }
    }
}
