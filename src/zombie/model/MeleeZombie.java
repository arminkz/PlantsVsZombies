package zombie.model;

import Game.Collider;

public abstract class MeleeZombie extends Zombie {

	public MeleeZombie(int lane) {
		super(lane);
		// TODO Auto-generated constructor stub
	}
	protected void attackPlant(Collider collidedPlant) {
		collidedPlant.getPlant().setHealth(collidedPlant.getPlant().getHealth() - attackPower);
	}
}
