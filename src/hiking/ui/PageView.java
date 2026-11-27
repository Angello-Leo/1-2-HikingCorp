package hiking.ui;

/** Implemented by every screen (JPanel form) that MainFrame shows in its content area. */
public interface PageView {
    /** Big title shown in the header of the main window. */
    String title();
    /** Grey sub-title under the title. */
    String subtitle();
    /** Called every time the page becomes visible (reload data here). */
    void onShow();
}
