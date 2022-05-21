package Model.Zombie;

import View.Game.GamePanel;
import View.Zombie.ConeHeadZombieView;

/**
 * Created by Armin on 6/29/2016.
 */
public class ConeHeadZombie extends Zombie {

    public ConeHeadZombie(GamePanel parent, int lane) {
        super(parent, lane, new ConeHeadZombieView());
        setHealth(1800);
    }
}
