package Pea.model;

import Game.view.GamePanel;
import zombie.model.Zombie;

import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    private int xPosition;
    protected GamePanel gamePanel;
    private int myLane;

    public Pea(int lane, int startX) {
        gamePanel = gamePanel.getInstance();
        this.myLane = lane;
        xPosition = startX;
    }

    public void advance() {
        setXPosition(getXPosition() + 15);
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
