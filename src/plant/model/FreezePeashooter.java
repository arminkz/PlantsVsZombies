package plant.model;

import Lane.model.Lane;
import Pea.model.FreezePea;
import plant.strategy.ShootFreezePea;
import plant.strategy.ShootNormalPea;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {
    private Timer shootTimer;
    private Lane lanes;


    public FreezePeashooter(int x, int y) {
        super(x, y);
        lanes = lanes.getInstance();
        this.setPlantShootingStrategy(new ShootFreezePea());
        shoot();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

    @Override
    public void shoot() {
        super.shoot();
    }

}