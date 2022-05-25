package Model.Plant;

import Model.Lane.Lane;
import Model.Pea.Pea;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Model.Pea.FreezePea;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Peashooter {
    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createPea(y);
        shootTimer.start();
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
    }

    @Override
    protected Pea getPea() {
        return new FreezePea(GamePanel.getInstance(), y, STARTING_POSITION_OF_PEA + this.getX() * MOVE_PER_FRAME);
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}