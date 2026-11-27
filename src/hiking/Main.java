package hiking;

import hiking.ui.LoginFrame;
import hiking.ui.Theme;

import javax.swing.SwingUtilities;

/** Entry point: installs the look & feel and opens the sign-in window. */
public final class Main {
    private Main() {}
    public static void main(String[] args) {
        hiking.ui.AppIcon.install();
        Theme.install();
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
