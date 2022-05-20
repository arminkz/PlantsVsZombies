package Model.Plant;

import Model.Pea.FreezePea;
import Model.Pea.Pea;
import Model.Plant.Plant;
import Model.Zombie.Zombie;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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