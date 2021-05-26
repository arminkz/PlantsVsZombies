package zombie.model;

import javax.swing.ImageIcon;

import Game.view.GamePanel;

/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends Zombie {

    public NormalZombie(GamePanel parent, int lane) {
        super(parent, lane);
    }
    
    protected void setImage() {
    	zombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
    }
}
