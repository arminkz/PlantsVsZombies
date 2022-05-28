package Model.Zombie;

import View.Game.GamePanel;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends Zombie {

    public NormalZombie(GamePanel parent, int lane) {
        super(parent, lane);
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
    }
}
