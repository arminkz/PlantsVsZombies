package plant.model;

import Lane.model.Lane;

import Pea.model.FreezePea;
import plant.strategy.ShootFreezePea;
import plant.strategy.ShootNormalPea;

import javax.swing.*;

import java.awt.Image;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    private static final int FreezePeashooter_Price = 175;
    private Image frozenPeashooterImage;
    private Timer shootTimer;
    private Lane lanes;

    @Override
    public void setImage() {
      plantImage = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
    }

    public FreezePeashooter(int x, int y) {
        super(x, y);

        frozenPeashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
        lanes = lanes.getInstance();
        this.setPlantShootingStrategy(new ShootFreezePea());
        shoot();
    }   

    @Override
    public int getPrice() {
        return FreezePeashooter_Price;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frozenPeashooterImage, 60 + 100 * getX(),129 + 120 * getY(),null);
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