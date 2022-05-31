package Model.Zombie;

import View.Game.GamePanel;

import javax.swing.ImageIcon;

/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends ZombieBase {

    public NormalZombie(GamePanel parent, int lane) {
        super(parent, lane);
    }

	@Override
	protected void setImage() {
		this.zImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
	}

}
