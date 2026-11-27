package hiking.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;
import java.util.function.DoubleFunction;

/** Lightweight custom charts (no external library): horizontal bars, lines, scatter and donut. */
public final class Charts {
    private Charts() {}

    static double niceMax(double v) {
        if (v <= 0) return 1; double e = Math.pow(10, Math.floor(Math.log10(v))), f = v / e;
        double n = f <= 1 ? 1 : f <= 2 ? 2 : f <= 2.5 ? 2.5 : f <= 5 ? 5 : 10; return n * e;
    }
    static void tip(Graphics2D g, String text, int x, int y, int w, int h) {
        g.setFont(Theme.bold(12)); FontMetrics fm = g.getFontMetrics(); String[] lines = text.split("\n"); int tw = 0; for (String s : lines) tw = Math.max(tw, fm.stringWidth(s)); int th = lines.length * (fm.getHeight()) + 10; tw += 20;
        int bx = Math.min(Math.max(4, x + 12), w - tw - 4), by = Math.min(Math.max(4, y - th - 8), h - th - 4);
        g.setColor(new Color(0x111827)); g.fill(new RoundRectangle2D.Double(bx, by, tw, th, 10, 10)); g.setColor(Color.WHITE);
        for (int i = 0; i < lines.length; i++) g.drawString(lines[i], bx + 10, by + 5 + fm.getAscent() + i * fm.getHeight());
    }
    static void empty(Graphics2D g, int w, int h, String msg) { g.setColor(Theme.MUTED); g.setFont(Theme.plain(13)); FontMetrics fm = g.getFontMetrics(); g.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2); }

    // ================================================================== horizontal bars
    public static class HBar extends JComponent {
        private List<String> labels = new ArrayList<>(); private List<Double> values = new ArrayList<>(); private List<Color> colors = new ArrayList<>();
        private DoubleFunction<String> fmt = v -> String.format("%,.0f", v); private int hover = -1; private Color base = Theme.PRIMARY;
        public HBar() { setPreferredSize(new Dimension(300, 260)); addMouseMotionListener(new MouseMotionAdapter() { public void mouseMoved(MouseEvent e) { int i = rowAt(e.getY()); if (i != hover) { hover = i; repaint(); } } }); addMouseListener(new MouseAdapter() { public void mouseExited(MouseEvent e) { hover = -1; repaint(); } }); }
        public HBar format(DoubleFunction<String> f) { fmt = f; return this; }
        public HBar color(Color c) { base = c; return this; }
        public void setData(Map<String, Double> data) { setData(data, null); }
        public void setData(Map<String, Double> data, java.util.function.Function<Double, Color> colorer) {
            labels = new ArrayList<>(data.keySet()); values = new ArrayList<>(data.values()); colors = new ArrayList<>();
            for (Double v : values) colors.add(colorer == null ? base : colorer.apply(v)); repaint();
        }
        private int rowAt(int y) { int n = labels.size(); if (n == 0) return -1; int rh = (getHeight() - 8) / n; int i = (y - 4) / Math.max(1, rh); return i >= 0 && i < n ? i : -1; }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight(), n = labels.size();
            if (n == 0) { empty(g, w, h, "No data yet"); g.dispose(); return; }
            g.setFont(Theme.plain(12.5f)); FontMetrics fm = g.getFontMetrics(); int lw = 0; for (String s : labels) lw = Math.max(lw, fm.stringWidth(s)); lw = Math.min(lw + 12, w / 2);
            double max = 0; for (double v : values) max = Math.max(max, v); if (max <= 0) max = 1;
            int rh = Math.min(38, (h - 8) / n), bh = Math.max(10, rh - 14), top = 4; int vw = fm.stringWidth(fmt.apply(max)) + 14;
            for (int i = 0; i < n; i++) {
                int y = top + i * rh; String lab = labels.get(i); while (fm.stringWidth(lab) > lw - 8 && lab.length() > 3) lab = lab.substring(0, lab.length() - 2);
                if (!lab.equals(labels.get(i))) lab += "…";
                g.setColor(i == hover ? Theme.TEXT : Theme.MUTED); g.drawString(lab, 0, y + (rh + fm.getAscent() - fm.getDescent()) / 2);
                double bw = Math.max(4, (w - lw - vw) * (values.get(i) / max)); Color c = colors.get(i);
                g.setColor(Theme.alpha(c, 30)); g.fill(new RoundRectangle2D.Double(lw, y + (rh - bh) / 2.0, w - lw - vw, bh, bh, bh));
                g.setColor(i == hover ? c.darker() : c); g.fill(new RoundRectangle2D.Double(lw, y + (rh - bh) / 2.0, bw, bh, bh, bh));
                g.setColor(Theme.TEXT); g.setFont(Theme.bold(12.5f)); g.drawString(fmt.apply(values.get(i)), (int) (lw + (w - lw - vw) + 8), y + (rh + fm.getAscent() - fm.getDescent()) / 2); g.setFont(Theme.plain(12.5f));
            }
            g.dispose();
        }
    }

    // ================================================================== line chart
    public static class Line extends JComponent {
        public static final class Series { final String name; final Double[] v; final Color color; final boolean dashed, fill; public Series(String n, Double[] v, Color c, boolean dashed, boolean fill) { name = n; this.v = v; color = c; this.dashed = dashed; this.fill = fill; } }
        private List<Series> series = new ArrayList<>(); private String[] xs = new String[0]; private DoubleFunction<String> fmt = Theme::moneyShort; private int hover = -1; private double ymin0 = 0;
        public Line() { setPreferredSize(new Dimension(500, 280)); addMouseMotionListener(new MouseMotionAdapter() { public void mouseMoved(MouseEvent e) { int n = xs.length; if (n == 0) return; int l = 64, r = 16; int i = (int) Math.round((e.getX() - l) / Math.max(1.0, (getWidth() - l - r) / (double) Math.max(1, n - 1))); i = Math.max(0, Math.min(n - 1, i)); if (i != hover) { hover = i; repaint(); } } }); addMouseListener(new MouseAdapter() { public void mouseExited(MouseEvent e) { hover = -1; repaint(); } }); }
        public Line format(DoubleFunction<String> f) { fmt = f; return this; }
        public void setData(String[] xLabels, Series... s) { xs = xLabels; series = Arrays.asList(s); hover = -1; repaint(); }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight(), n = xs.length;
            if (n == 0 || series.isEmpty()) { empty(g, w, h, "No data yet"); g.dispose(); return; }
            double max = 0, min = Double.MAX_VALUE; for (Series s : series) for (Double d : s.v) if (d != null) { max = Math.max(max, d); min = Math.min(min, d); }
            double top = niceMax(max * 1.05); int l = 64, r = 16, t = series.size() > 1 ? 30 : 12, b = 30; double pw = w - l - r, ph = h - t - b;
            g.setFont(Theme.plain(11.5f)); FontMetrics fm = g.getFontMetrics();
            for (int i = 0; i <= 4; i++) { double v = top * i / 4; int y = (int) (t + ph - ph * i / 4); g.setColor(new Color(0xEDF0F5)); g.drawLine(l, y, w - r, y); g.setColor(Theme.MUTED); String s = fmt.apply(v); g.drawString(s, l - 8 - fm.stringWidth(s), y + 4); }
            int step = Math.max(1, (int) Math.ceil(n / Math.max(1.0, pw / 62))); for (int i = 0; i < n; i += step) { double x = l + (n == 1 ? pw / 2 : pw * i / (n - 1)); g.setColor(Theme.MUTED); g.drawString(xs[i], (int) x - fm.stringWidth(xs[i]) / 2, h - 10); }
            int lx = w - r; for (int si = series.size() - 1; si >= 0 && series.size() > 1; si--) { Series s = series.get(si); int sw = fm.stringWidth(s.name); lx -= sw; g.setColor(Theme.TEXT); g.drawString(s.name, lx, 16); lx -= 16; g.setColor(s.color); g.fill(new RoundRectangle2D.Double(lx, 8, 10, 10, 4, 4)); lx -= 18; }
            for (Series s : series) {
                Path2D.Double path = new Path2D.Double(); boolean started = false; double lastX = 0, firstX = 0;
                for (int i = 0; i < n; i++) { Double d = s.v[i]; if (d == null) continue; double x = l + (n == 1 ? pw / 2 : pw * i / (n - 1)), y = t + ph - ph * d / top; if (!started) { path.moveTo(x, y); firstX = x; started = true; } else path.lineTo(x, y); lastX = x; }
                if (!started) continue;
                if (s.fill) { Path2D.Double a = new Path2D.Double(path); a.lineTo(lastX, t + ph); a.lineTo(firstX, t + ph); a.closePath(); g.setPaint(new GradientPaint(0, t, Theme.alpha(s.color, 70), 0, t + (float) ph, Theme.alpha(s.color, 0))); g.fill(a); }
                g.setColor(s.color); g.setStroke(s.dashed ? new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{7, 6}, 0) : new BasicStroke(2.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); g.draw(path); g.setStroke(new BasicStroke(1));
                if (n <= 16) for (int i = 0; i < n; i++) { Double d = s.v[i]; if (d == null) continue; double x = l + (n == 1 ? pw / 2 : pw * i / (n - 1)), y = t + ph - ph * d / top; g.setColor(Color.WHITE); g.fill(new Ellipse2D.Double(x - 4, y - 4, 8, 8)); g.setColor(s.color); g.setStroke(new BasicStroke(2f)); g.draw(new Ellipse2D.Double(x - 4, y - 4, 8, 8)); }
            }
            if (hover >= 0) {
                double x = l + (n == 1 ? pw / 2 : pw * hover / (n - 1)); g.setColor(new Color(0xCBD2DC)); g.setStroke(new BasicStroke(1f)); g.draw(new Line2D.Double(x, t, x, t + ph)); StringBuilder sb = new StringBuilder(xs[hover]); double ty = t + ph;
                for (Series s : series) { Double d = s.v[hover]; if (d == null) continue; double y = t + ph - ph * d / top; ty = Math.min(ty, y); g.setColor(Color.WHITE); g.fill(new Ellipse2D.Double(x - 5, y - 5, 10, 10)); g.setColor(s.color); g.setStroke(new BasicStroke(2.5f)); g.draw(new Ellipse2D.Double(x - 5, y - 5, 10, 10)); sb.append("\n").append(series.size() > 1 ? s.name + ": " : "").append(fmt.apply(d)); }
                tip(g, sb.toString(), (int) x, (int) ty, w, h);
            }
            g.dispose();
        }
    }

    // ================================================================== scatter (actual vs predicted)
    public static class Scatter extends JComponent {
        private double[] a = new double[0], p = new double[0]; private Color color = Theme.PRIMARY; private int hover = -1;
        public Scatter() { setPreferredSize(new Dimension(380, 300)); addMouseMotionListener(new MouseMotionAdapter() { public void mouseMoved(MouseEvent e) { int best = -1; double bd = 100; double[] sc = scale(); for (int i = 0; i < a.length; i++) { double dx = px(a[i], sc) - e.getX(), dy = py(p[i], sc) - e.getY(); double d = dx * dx + dy * dy; if (d < bd) { bd = d; best = i; } } if (best != hover) { hover = best; repaint(); } } }); }
        public void setData(double[] actual, double[] pred, Color c) { a = actual; p = pred; color = c; hover = -1; repaint(); }
        private double[] scale() { double mx = 0; for (double v : a) mx = Math.max(mx, v); for (double v : p) mx = Math.max(mx, v); return new double[]{niceMax(mx * 1.02)}; }
        private double px(double v, double[] sc) { return 56 + (getWidth() - 56 - 14) * v / sc[0]; }
        private double py(double v, double[] sc) { return 12 + (getHeight() - 12 - 40) * (1 - v / sc[0]); }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight(); if (a.length == 0) { empty(g, w, h, "No data"); g.dispose(); return; }
            double[] sc = scale(); g.setFont(Theme.plain(11.5f)); FontMetrics fm = g.getFontMetrics();
            for (int i = 0; i <= 4; i++) { double v = sc[0] * i / 4; String s = v >= 1000 ? String.format("%,.0f", v) : String.format("%.1f", v); int y = (int) py(v, sc), x = (int) px(v, sc); g.setColor(new Color(0xEDF0F5)); g.drawLine(56, y, w - 14, y); g.drawLine(x, 12, x, h - 40); g.setColor(Theme.MUTED); g.drawString(s, 50 - fm.stringWidth(s), y + 4); g.drawString(s, x - fm.stringWidth(s) / 2, h - 24); }
            g.setColor(Theme.MUTED); g.setFont(Theme.bold(11.5f)); g.drawString("Actual", w / 2 - 14, h - 6); Graphics2D r = (Graphics2D) g.create(); r.rotate(-Math.PI / 2, 12, h / 2 - 20); r.drawString("Predicted", 12 - 20, h / 2 - 16); r.dispose();
            g.setColor(new Color(0x94A3B8)); g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1f, new float[]{6, 5}, 0)); g.draw(new Line2D.Double(px(0, sc), py(0, sc), px(sc[0], sc), py(sc[0], sc))); g.setStroke(new BasicStroke(1));
            g.setColor(Theme.alpha(color, 110)); for (int i = 0; i < a.length; i++) g.fill(new Ellipse2D.Double(px(a[i], sc) - 3.5, py(p[i], sc) - 3.5, 7, 7));
            if (hover >= 0) { g.setColor(color); g.fill(new Ellipse2D.Double(px(a[hover], sc) - 5, py(p[hover], sc) - 5, 10, 10)); tip(g, String.format("Actual: %,.2f\nPredicted: %,.2f", a[hover], p[hover]), (int) px(a[hover], sc), (int) py(p[hover], sc), w, h); }
            g.dispose();
        }
    }

    // ================================================================== donut
    public static class Donut extends JComponent {
        private List<String> labels = new ArrayList<>(); private List<Double> values = new ArrayList<>(); private double total; private int hover = -1; private String centerTitle = "Total"; private DoubleFunction<String> fmt = Theme::moneyShort;
        public Donut() { setPreferredSize(new Dimension(300, 240)); addMouseMotionListener(new MouseMotionAdapter() { public void mouseMoved(MouseEvent e) { int i = sliceAt(e.getX(), e.getY()); if (i != hover) { hover = i; repaint(); } } }); addMouseListener(new MouseAdapter() { public void mouseExited(MouseEvent e) { hover = -1; repaint(); } }); }
        public Donut format(DoubleFunction<String> f, String center) { fmt = f; centerTitle = center; return this; }
        public void setData(Map<String, Double> d) { labels = new ArrayList<>(d.keySet()); values = new ArrayList<>(d.values()); total = 0; for (double v : values) total += v; repaint(); }
        private Rectangle2D ring() { int s = Math.min(getHeight() - 16, (int) (getWidth() * 0.5)); return new Rectangle2D.Double(10, (getHeight() - s) / 2.0, s, s); }
        private int sliceAt(int x, int y) {
            if (total <= 0) return -1; Rectangle2D r = ring(); double cx = r.getCenterX(), cy = r.getCenterY(), dx = x - cx, dy = y - cy, d = Math.hypot(dx, dy); if (d > r.getWidth() / 2 || d < r.getWidth() / 2 * 0.58) return -1;
            double ang = Math.toDegrees(Math.atan2(-dy, dx)); ang = (90 - ang + 360) % 360; double acc = 0; for (int i = 0; i < values.size(); i++) { acc += values.get(i) / total * 360; if (ang <= acc) return i; } return -1;
        }
        @Override protected void paintComponent(Graphics g0) {
            Graphics2D g = (Graphics2D) g0.create(); UI.aa(g); int w = getWidth(), h = getHeight(); if (total <= 0) { empty(g, w, h, "No data yet"); g.dispose(); return; }
            Rectangle2D r = ring(); double start = 90; int n = values.size();
            for (int i = 0; i < n; i++) {
                double ext = -values.get(i) / total * 360; Color c = Theme.SERIES[i % Theme.SERIES.length]; double grow = i == hover ? 4 : 0;
                Arc2D arc = new Arc2D.Double(r.getX() - grow, r.getY() - grow, r.getWidth() + 2 * grow, r.getHeight() + 2 * grow, start, ext, Arc2D.PIE); g.setColor(c); g.fill(arc); start += ext;
            }
            double in = r.getWidth() * 0.58; g.setColor(Theme.CARD); g.fill(new Ellipse2D.Double(r.getCenterX() - in / 2, r.getCenterY() - in / 2, in, in));
            g.setColor(Theme.MUTED); g.setFont(Theme.plain(11.5f)); FontMetrics fm = g.getFontMetrics(); String t1 = hover >= 0 ? labels.get(hover) : centerTitle; g.drawString(t1, (int) (r.getCenterX() - fm.stringWidth(t1) / 2.0), (int) r.getCenterY() - 4);
            g.setColor(Theme.TEXT); g.setFont(Theme.bold(16)); fm = g.getFontMetrics(); String t2 = fmt.apply(hover >= 0 ? values.get(hover) : total); g.drawString(t2, (int) (r.getCenterX() - fm.stringWidth(t2) / 2.0), (int) r.getCenterY() + 17);
            int lx = (int) (r.getMaxX() + 22), ly = Math.max(8, (int) (h / 2 - n * 12.5)); g.setFont(Theme.plain(12.5f)); fm = g.getFontMetrics();
            for (int i = 0; i < n; i++) {
                int y = ly + i * 25; g.setColor(Theme.SERIES[i % Theme.SERIES.length]); g.fill(new RoundRectangle2D.Double(lx, y, 11, 11, 4, 4)); g.setColor(i == hover ? Theme.TEXT : Theme.MUTED);
                String s = labels.get(i) + "  " + Math.round(values.get(i) / total * 100) + "%"; while (fm.stringWidth(s) > w - lx - 20 && s.length() > 6) s = s.substring(0, s.length() - 2); g.drawString(s, lx + 18, y + 10);
            }
            g.dispose();
        }
    }

    /** Titled card wrapping a chart. */
    public static UI.Card card(String title, String subtitle, JComponent chart) {
        UI.Card c = new UI.Card(new BorderLayout(0, 8)); JPanel head = UI.transparent(new GridLayout(subtitle == null ? 1 : 2, 1)); head.add(UI.h2(title)); if (subtitle != null) head.add(UI.muted(subtitle)); c.add(head, BorderLayout.NORTH); c.add(chart); return c;
    }
}
