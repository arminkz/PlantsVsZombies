package plant.model;

import Lane.model.Lane;
import Pea.model.NormalPea;
import plant.strategy.ShootNormalPea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    private static final int Peashooter_Price = 100;
    public Image peashooterImage;
    public Timer shootTimer;
    private Lane lanes;

    /**
     * gamepanel에서 lane을 불러오는 것이 아닌,
     * lane class에서 싱글톤으로 getInstance를 한다.
     */
    public Peashooter(int x, int y) {
        super(x, y);
        peashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
        lanes = lanes.getInstance();
        this.setPlantShootingStrategy(new ShootNormalPea());
        shoot();
    }

    @Override
    public int getPrice() {
        return Peashooter_Price;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(peashooterImage, 60 + getX() * 100,129 + 120 * getY(),null);
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
