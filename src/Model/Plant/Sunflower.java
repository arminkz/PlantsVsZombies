package Model.Plant;

import View.Element.Sun;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    private Timer sunProduceTimer;

    public Sunflower(GamePanel parent, int x, int y) {
        super(parent, x, y);
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {
            Sun sta = new Sun(GamePanel.getInstance(), 60 + x * 100, 110 + y * 120, 130 + y * 120);
            GamePanel.getInstance().getActiveSuns().add(sta);
            GamePanel.getInstance().add(sta, new Integer(1));
        });
        sunProduceTimer.start();
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
    }
}
