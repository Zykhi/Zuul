package pkg_UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;

/**
 * @author Twinuts
 *         https://codes-sources.commentcamarche.net/forum/affich-857479-bordure-arrondie-dans-un-jpanel
 */
public class HealthPanelBorder implements Border {

    /**
     * 
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);// ou autre chose cela dépend de si tu veux rendre parametrable
    }

    /**
     *
     */
    public boolean isBorderOpaque() {
        return false;// ou autre chose cela dépend de si tu veux rendre parametrable
    }

    /**
     *
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.darkGray);// ou une autre couleur que tu peux rendre paramétrable
        int arc = 25;// tu peux aussi le rendre configurable
        int adjustXY = 1;// pour ajuster le dessin en x et y
        int adjustWH = 2;// idem pour width et height
        // pour eviter les escalier sur l'arrondi
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(x + adjustXY, y + adjustXY, width - adjustWH, height - adjustWH, arc, arc);
    }
}
