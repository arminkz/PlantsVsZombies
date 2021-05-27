package zombie.model;

import javax.swing.ImageIcon;

import Game.view.GamePanel;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        setHealth(1800);
    }
    
    protected void setImage() {
    	zombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie2.png")).getImage();
    }
}
