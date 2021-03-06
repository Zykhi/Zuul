package pkg_UI;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

/**
 * 
 */
public class RoundJTextArea extends JTextArea {
    private int aRadius;

    /**
     * Create a round JTextArea
     */
    public RoundJTextArea() {
        super(10, 20);
        setOpaque(false);
        setBorder(null);
        setRadius(20);
    }

    /**
     * 
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());
        super.paintComponent(g);
    }

    /**
     * 
     */
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(102, 102, 102));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getRadius(), getRadius());
    }

    /**
     * This method set the radius of the round JTextArea
     * 
     * @param pRadius radius of the round JTextArea
     */
    public void setRadius(int pRadius) {
        this.aRadius = pRadius;
        repaint();
    }

    /**
     * This function get the radius of the round JTextArea
     * 
     * @return the radius of the round JTextArea
     */
    public int getRadius() {
        return aRadius;
    }

    /**
     * This function get the insets of the round JTextArea
     * 
     * @return the insets of the round JTextArea
     */
    @Override
    public Insets getInsets() {
        int value = getRadius() / 2;
        return new Insets(value, value, value, value);
    }

}
