package plant.model;

import Pea.model.FreezePea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    private static final int FreezePeashooter_Price = 175;
    private Image frozenPeashooterImage;
    private Timer shootTimer;


    public FreezePeashooter(int x, int y) {
        super(x, y);
        frozenPeashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getLane().getLaneZombies().get(y).size() > 0) {
                getLane().getLanePeas().get(y).add(new FreezePea(y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
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

}