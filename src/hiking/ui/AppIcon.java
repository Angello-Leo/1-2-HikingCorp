/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hiking.ui;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowEvent;
/**
 *
 * @author Leo Angello
 */
public class AppIcon {
    private AppIcon() {}

    public static void install() {
        final Image logo = Brand.image();          
        if (logo == null) return;

        // 1) taskbar icon (works where the system supports it)
        try {
            if (Taskbar.isTaskbarSupported()
                    && Taskbar.getTaskbar().isSupported(Taskbar.Feature.ICON_IMAGE))
                Taskbar.getTaskbar().setIconImage(logo);
        } catch (Throwable ignored) { }

        // 2) every window that opens (login, sign-up, main, dialogs) gets the logo
        Toolkit.getDefaultToolkit().addAWTEventListener((AWTEventListener) ev -> {
            if (ev.getID() == WindowEvent.WINDOW_OPENED) {
                Window w = ((WindowEvent) ev).getWindow();
                if (w != null) w.setIconImage(logo);
            }
        }, AWTEvent.WINDOW_EVENT_MASK);
    }
}
