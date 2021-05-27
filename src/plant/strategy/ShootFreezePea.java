package plant.strategy;

import Lane.model.Lane;
import Pea.model.FreezePea;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShootFreezePea implements PlantShootingStrategy {
    private Timer shootTimer;

    @Override
    public void shoot(int x, int y, Lane lanes) {
        shootFreezePea(x, y, lanes);
    }

    private void shootFreezePea(int x, int y, Lane lanes) {
        lanes = lanes.getInstance();
        Lane finalLanes = lanes;
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (finalLanes.getLaneZombies().get(y).size() > 0) {
                finalLanes.getLanePeas().get(y).add(new FreezePea(y, 103 + x * 100));
            }
        });
        shootTimer.start();
    }

}
