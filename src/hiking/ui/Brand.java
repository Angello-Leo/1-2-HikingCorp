package hiking.ui;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public final class Brand {
    private Brand() {}
    private static java.awt.image.BufferedImage ink;
    private static boolean inkDone;
    private static Image tiny;
    private static int tinyS;
    private static Image img;
    static {
        String[] names = {"logo.png", "HiKingLogo.png"};
        for (String n : names) {
            try {
                java.net.URL u = Brand.class.getResource("/hiking/ui/" + n);
                if (u != null) { img = javax.imageio.ImageIO.read(u); }
                if (img == null) {
                    java.io.File f = new java.io.File("src/hiking/ui/" + n);
                    if (f.exists()) img = javax.imageio.ImageIO.read(f);
                }
                if (img != null) break;
            } catch (Exception e) { System.err.println("Brand: could not read " + n + ": " + e); }
        }
        if (img == null) System.err.println("Brand: logo image NOT found");
    }
    public static Image image() { return img; }

    public static java.awt.image.BufferedImage ink() {
        if (inkDone) return ink;
        inkDone = true;
        Image src = image();
        if (src == null) return null;
        int w = src.getWidth(null), h = src.getHeight(null);
        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.dispose();
        int minX = w, minY = h, maxX = -1, maxY = -1;
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++) {
            int p = bi.getRGB(x, y);
            int sum = ((p >> 16) & 255) + ((p >> 8) & 255) + (p & 255);
            if ((p >>> 24) > 40 && sum < 240) { if (x < minX) minX = x; if (x > maxX) maxX = x; if (y < minY) minY = y; if (y > maxY) maxY = y; }
        }
        if (maxX < 0) return null;
        int m = 6;
        minX = Math.max(0, minX - m); minY = Math.max(0, minY - m);
        maxX = Math.min(w - 1, maxX + m); maxY = Math.min(h - 1, maxY + m);
        ink = bi.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
        return ink;
    }

    private static java.awt.image.BufferedImage inkT;
    /** The logo's black line art on a transparent background (the orange field of the picture becomes see-through), for light panels. */
    public static java.awt.image.BufferedImage inkTransparent() {
        if (inkT != null) return inkT;
        java.awt.image.BufferedImage c = ink(); if (c == null) return null;
        java.awt.image.BufferedImage o = new java.awt.image.BufferedImage(c.getWidth(), c.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < c.getHeight(); y++) for (int x = 0; x < c.getWidth(); x++) {
            int p = c.getRGB(x, y), r = (p >> 16) & 255, a = (p >>> 24);
            int alpha = Math.max(0, Math.min(255, (255 - r) * 255 / 225)) * a / 255;   // black = opaque, orange (r=255) = transparent
            o.setRGB(x, y, (alpha << 24) | 0x111111);
        }
        return inkT = o;
    }

    public static javax.swing.ImageIcon logoIcon(int width) {
        java.awt.image.BufferedImage c = ink();
        if (c == null) return null;
        int h = Math.max(1, c.getHeight() * width / c.getWidth());
        return new javax.swing.ImageIcon(c.getScaledInstance(width, h, Image.SCALE_SMOOTH));
    }

    public static void logo(Graphics2D g, int x, int y, int s) {
        java.awt.image.BufferedImage c = ink();
        if (c != null) {
            if (tiny == null || tinyS != s) {
                int box = s - 2 * Math.max(2, s / 8);
                int w = c.getWidth() >= c.getHeight() ? box : Math.max(1, box * c.getWidth() / c.getHeight());
                int h = c.getWidth() >= c.getHeight() ? Math.max(1, box * c.getHeight() / c.getWidth()) : box;
                tiny = new javax.swing.ImageIcon(c.getScaledInstance(w, h, Image.SCALE_SMOOTH)).getImage();
                tinyS = s;
            }
            g.setColor(Theme.BEIGE);
            g.fillRoundRect(x, y, s, s, s / 4, s / 4);
            g.drawImage(tiny, x + (s - tiny.getWidth(null)) / 2, y + (s - tiny.getHeight(null)) / 2, null);
            return;
        }
        g.setPaint(new GradientPaint(x, y, new Color(0xFF7A45), x + s, y + s, Theme.PRIMARY_DK));
        g.fill(new RoundRectangle2D.Double(x, y, s, s, s * .3, s * .3));
        g.setColor(Color.WHITE); g.setFont(Theme.bold(s * .62f));
        FontMetrics fm = g.getFontMetrics(); String t = "½";
        g.drawString(t, x + (s - fm.stringWidth(t)) / 2, y + (s + fm.getAscent() - fm.getDescent()) / 2 - 1);
    }
}
