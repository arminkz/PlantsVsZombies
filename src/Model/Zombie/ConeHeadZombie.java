package Model.Zombie;

import View.Game.GamePanel;

import javax.swing.*;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends Zombie {
    public ConeHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        setHealth(1800);
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie2.png")).getImage();
    }
}
