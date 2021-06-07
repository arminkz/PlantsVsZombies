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

    private Timer shootTimer;
    private Lane lanes;
    

    @Override
    protected void setImage() {
      plantImage = new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
    }
    
    /**
     * gamepanel�뿉�꽌 lane�쓣 遺덈윭�삤�뒗 寃껋씠 �븘�땶,
     * lane class�뿉�꽌 �떛湲��넠�쑝濡� getInstance瑜� �븳�떎.
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
