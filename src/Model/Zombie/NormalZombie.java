package Model.Zombie;

import View.Game.GamePanel;
import View.Zombie.NormalZombieView;

/**
 * Created by Armin on 6/25/2016.
 */
public class NormalZombie extends Zombie {

    public NormalZombie(GamePanel parent, int lane) {
        super(parent, lane, new NormalZombieView());
    }

}
