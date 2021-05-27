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

    @Override
    public int getPower() {
        return 300;
    }

    protected void setImage() {
        peaImage = new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
    }

    public void advance() {
        setXPosition(getXPosition() + 15);
    }
}
