package Model.Plant;

import View.Game.GamePanel;


/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createShootTimer(y);
        shootTimer.start();
    }


    @Override
    public void stop() {
        shootTimer.stop();
    }

}
