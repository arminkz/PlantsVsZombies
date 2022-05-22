package Model.Plant;

import View.Game.GamePanel;
import View.Plant.FreezePeashooterView;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {
    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y, new FreezePeashooterView());
        createShootTimer(y);
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}