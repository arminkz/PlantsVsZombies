package plant.model;

import Lane.model.Lane;
import Pea.model.NormalPea;
import plant.strategy.ShootNormalPea;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    private Timer shootTimer;
    private Lane lanes;

    /**
     * gamepanel에서 lane을 불러오는 것이 아닌,
     * lane class에서 싱글톤으로 getInstance를 한다.
     */
    public Peashooter(int x, int y) {
        super(x, y);
        lanes = lanes.getInstance();
        this.setPlantShootingStrategy(new ShootNormalPea());
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
