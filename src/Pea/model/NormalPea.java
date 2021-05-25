package Pea.model;

import Game.view.GamePanel;
import zombie.model.Zombie;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class NormalPea extends Pea {

    public NormalPea(int lane, int startX) {
        super(lane, startX);
    }

    protected void setImage() {
        peaImage = new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
    }

    public Image getImage() {
        return peaImage;
    }

    public void advance() {
        setXPosition(getXPosition() + 15);
    }


}
