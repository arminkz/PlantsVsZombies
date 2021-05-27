package plant.strategy;

import Lane.model.Lane;
import Pea.model.NormalPea;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShootNormalPea implements PlantShootingStrategy {
    private Timer shootTimer;

    @Override
    public void shoot(int x, int y, Lane lanes) {
        shootNormalPea(x, y, lanes);
    }

    private void shootNormalPea(int x, int y, Lane lanes) {
        lanes = lanes.getInstance();
        Lane finalLanes = lanes;
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (finalLanes.getLaneZombies().get(y).size() > 0) {
                finalLanes.getLanePeas().get(y).add(new NormalPea(y, 103 + x * 100));
            }
        });
        shootTimer.start();
    }
}
