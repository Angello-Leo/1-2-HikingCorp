package hiking.ui;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

/** Colours, fonts and global look & feel setup. */
public final class Theme {
    private Theme() {}
    /** Palette of the 2026 design: orange sidebar and buttons, blue-grey page, beige inputs, slate selected menu item. */
    public static final Color BG = new Color(0xD6DFEA), CARD = Color.WHITE, BEIGE = new Color(0xE8D8C6), SLATE = new Color(0x4A5F7E),
        SIDEBAR = new Color(0xDE7D3C), SIDEBAR_END = new Color(0xBE5C26), SIDEBAR_HI = SLATE,
        PRIMARY = new Color(0xD66E2E), PRIMARY_DK = new Color(0xB85A22), PRIMARY_LT = new Color(0xFAE8DA), ACCENT = new Color(0xFFB703),
        TEXT = new Color(0x141414), MUTED = new Color(0x3F4B5F), BORDER = new Color(0xB4C2D6), ROW_ALT = new Color(0xF1E6D8),
        OK = new Color(0x16A34A), WARN = new Color(0xD97706), DANGER = new Color(0xDC2626), INFO = new Color(0x2563EB), PURPLE = new Color(0x7C3AED), TEAL = new Color(0x0891B2);
    public static final Color[] SERIES = {PRIMARY, INFO, OK, ACCENT, PURPLE, TEAL, new Color(0xDB2777), new Color(0x64748B)};

    private static String family = "SansSerif"; private static boolean pesoOk;

    public static void install() {
        System.setProperty("awt.useSystemAAFontSettings", "on"); System.setProperty("swing.aatext", "true");
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); } catch (Exception ignored) { }
        loadBundledFonts();
        java.util.Set<String> have = new java.util.HashSet<>(java.util.Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        for (String f : new String[]{"Fredoka", "Quicksand", "Nunito", "Varela Round", "Comfortaa", "Segoe UI", "Inter", "SF Pro Text", "Helvetica Neue", "Roboto", "Noto Sans", "Ubuntu", "DejaVu Sans"}) if (have.contains(f)) { family = f; break; }
        pesoOk = new Font(family, Font.PLAIN, 12).canDisplay('₱');
        FontUIResource base = new FontUIResource(font(Font.PLAIN, 14));
        for (String k : new String[]{"Label.font", "TextField.font", "PasswordField.font", "TextArea.font", "ComboBox.font", "CheckBox.font", "Table.font", "List.font", "ToolTip.font", "Button.font", "TabbedPane.font"}) UIManager.put(k, base);
        UIManager.put("ToolTip.background", new Color(0x111827)); UIManager.put("ToolTip.foreground", Color.WHITE); UIManager.put("ToolTip.border", BorderFactory.createEmptyBorder(6, 10, 6, 10));
        UIManager.put("TextField.selectionBackground", PRIMARY_LT); UIManager.put("TextField.selectionForeground", TEXT);
        UIManager.put("Table.selectionBackground", PRIMARY_LT); UIManager.put("Table.selectionForeground", TEXT);
        UIManager.put("ScrollPane.border", BorderFactory.createEmptyBorder());
    }
    /** Any .ttf / .otf dropped in the project's "fonts" folder is registered, so the design's typeface can be added without code changes. */
    private static void loadBundledFonts() {
        try {
            java.io.File d = new java.io.File(hiking.AppPaths.base(), "fonts");
            java.io.File[] fs = d.listFiles((x, n) -> n.toLowerCase().endsWith(".ttf") || n.toLowerCase().endsWith(".otf"));
            if (fs != null) for (java.io.File f : fs) GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Font.createFont(Font.TRUETYPE_FONT, f));
        } catch (Exception ignored) { }
    }
    public static Font font(int style, float size) { return new Font(family, style, 12).deriveFont(style, size); }
    public static Font plain(float s) { return font(Font.PLAIN, s); }
    public static Font bold(float s) { return font(Font.BOLD, s); }
    public static Font mono(float s) { return new Font(Font.MONOSPACED, Font.PLAIN, 13).deriveFont(s); }
    public static Color alpha(Color c, int a) { return new Color(c.getRed(), c.getGreen(), c.getBlue(), a); }
    public static String peso() { return pesoOk ? "₱" : "PHP "; }
    public static String money(double v) { return peso() + String.format("%,.2f", v); }
    public static String moneyShort(double v) { return Math.abs(v) >= 1000 ? peso() + String.format("%,.1fk", v / 1000) : peso() + String.format("%,.0f", v); }
}
