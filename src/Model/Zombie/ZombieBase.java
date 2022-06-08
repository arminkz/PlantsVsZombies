package Model.Zombie;

import javax.swing.ImageIcon;

import View.Collider;

import View.Game.GamePanel;

public abstract class ZombieBase extends Zombie {

	public ZombieBase(GamePanel parent, int lane) {
		super(parent, lane);
		// TODO Auto-generated constructor stub
	}
	
	protected void attackPlants(Collider collidedPlants) {
		collidedPlants.assignedPlant.setHealth(collidedPlants.assignedPlant.getHealth() - 200);
		if (collidedPlants.assignedPlant.getHealth() >= 0) return;
		collidedPlants.removePlant();
	}
	
	protected void setImage(String imgPath) {
		this.zImage = new ImageIcon(this.getClass().getResource(imgPath)).getImage();
	}
}
