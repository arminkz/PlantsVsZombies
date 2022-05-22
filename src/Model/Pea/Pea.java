package Model.Pea;

import View.Game.GamePanel;
import View.View;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Pea {
    protected int positionX;
    protected GamePanel gamePanel;
    protected int myLane;
    protected View view;

    public Pea(GamePanel parent, int lane, int startPositionX, View view) {
        this.gamePanel = parent;
        this.myLane = lane;
        this.positionX = startPositionX;
        this.view = view;
    }

    public abstract void advance();

    public View getView() {
        return view;
    }

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
