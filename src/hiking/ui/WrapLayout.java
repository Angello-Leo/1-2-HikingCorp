package hiking.ui;

import java.awt.*;

/** A FlowLayout that wraps onto extra rows when the window is narrow and reports the correct height (a plain FlowLayout hides the wrapped rows). */
public class WrapLayout extends FlowLayout {
    public WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }
    @Override public Dimension preferredLayoutSize(Container t) { return size(t, true); }
    @Override public Dimension minimumLayoutSize(Container t) { Dimension d = size(t, false); d.width -= getHgap() + 1; return d; }
    private Dimension size(Container target, boolean preferred) {
        synchronized (target.getTreeLock()) {
            int tw = target.getSize().width; if (tw == 0 && target.getParent() != null) tw = target.getParent().getWidth(); if (tw == 0) tw = Integer.MAX_VALUE;
            Insets in = target.getInsets(); int hg = getHgap(), vg = getVgap(), max = tw - (in.left + in.right + hg * 2);
            Dimension dim = new Dimension(0, 0); int rowW = 0, rowH = 0;
            for (Component m : target.getComponents()) {
                if (!m.isVisible()) continue;
                Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();
                if (rowW + d.width > max && rowW > 0) { dim.width = Math.max(dim.width, rowW); dim.height += rowH + vg; rowW = 0; rowH = 0; }
                if (rowW != 0) rowW += hg; rowW += d.width; rowH = Math.max(rowH, d.height);
            }
            dim.width = Math.max(dim.width, rowW); dim.height += rowH;
            dim.width += in.left + in.right + hg * 2; dim.height += in.top + in.bottom + vg * 2;
            return dim;
        }
    }
}
