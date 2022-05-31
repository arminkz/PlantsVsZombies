package Model.Zombie;

import View.Game.GamePanel;

import javax.swing.ImageIcon;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends ZombieBase {
	
    public ConeHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        setHealth(1800);
        setImage("../../images/zombies/zombie2.png");
    }

}
