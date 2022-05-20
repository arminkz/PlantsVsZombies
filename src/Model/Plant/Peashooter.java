package Model.Plant;

import Model.Pea.Pea;
import Model.Zombie.Zombie;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
