package plant.model;

import Pea.model.FreezePea;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    public static final int FreezePeashooter_Price = 175;
    private Timer shootTimer;


    public FreezePeashooter(int x, int y) {
        super(x, y);
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
    public void stop() {
        shootTimer.stop();
    }

}