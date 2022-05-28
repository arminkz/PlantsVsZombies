package Model.Pea;

import View.Game.GamePanel;
import View.View;

import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Pea implements PeaStrategy{
    protected int positionX;
    protected int myLane;
    protected Image image;

    public Pea(GamePanel parent, int lane, int startPositionX) {
        this.myLane = lane;
        this.positionX = startPositionX;
        setImage();
    }

    public void draw(int x, int y, Graphics graphics) {
        graphics.drawImage(this.image, x, y, null);
    }

    protected abstract void setImage();
    public abstract int getPower();
    public abstract void advance();

    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getMyLane() {
        return myLane;
    }
    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
