package Game;

import plant.model.Plant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/25/2016.
 */
public class Collider extends JPanel implements MouseListener {
    //rename actionListner variable
    //private ActionListener al;
    private ActionListener actionListener;
    public Collider() {
        //setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        //setBackground(Color.green);
        setSize(100, 120);
    }

    private Plant assignedPlant;
    public Plant getPlant() {
        return assignedPlant;
    }
    /**
     * �썝�옒 肄붾뱶
     * public Plant assignedPlant;
     *
     * 諛붾�� 肄붾뱶
     * private Plant assignedPlant;
     *
     * private �쑝濡� 怨좎튂怨� getter setter 留뚮뱾�뼱 以섏빞 �븳�떎.
     * 臾몄젣�젏 : collider.assignedPlant瑜� �벐�뒗 �겢�옒�뒪(GamePanel.java, Zombie.java)�뿉�꽌 getter濡� 諛붽퓭 以섏빞 �븳�떎.
     */
    //turn public into private and make getter and setter

    public void setPlant(Plant p) {
        assignedPlant = p;
    }

    public void removePlant() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    public boolean isInsideCollider(int x) {
        boolean moreThanMin = x > getLocation().x;
        boolean lessThanMax = x < getLocation().x + 100;
        return moreThanMin && lessThanMax;
    }

    public void setAction(ActionListener actionListener) {
        this.actionListener = actionListener;
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
