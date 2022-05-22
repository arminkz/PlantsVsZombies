package Model.Plant;

import View.Game.GamePanel;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    private Timer shootTimer;

    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createShootTimer(y);
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}