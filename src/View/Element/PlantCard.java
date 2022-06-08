package View.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/28/2016.
 */
public class PlantCard extends JPanel implements MouseListener {

    private Image img;
    private ActionListener actionListener;

    public static final int PLANT_CARD_WIDTH = 64;
    public static final int PLANT_CARD_HEIGHT = 90;

    public PlantCard(Image img) {
        setSize(PLANT_CARD_WIDTH, PLANT_CARD_HEIGHT);
        this.img = img;
        addMouseListener(this);
    }

    public void setAction(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (actionListener != null) {
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
