package Pea.model;

import Game.view.GamePanel;
import zombie.model.Zombie;

import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {

    public FreezePea(int lane, int startX) {
        super(lane, startX);
    }

    @Override
    public void advance() {
        setXPosition(getXPosition() - 15);
    }

}
