package Model.Plant;

import Model.Plant.Action.NormalShootDecorator;
import Model.Plant.Action.PlantAction;
import Model.Plant.Action.PlantActionDecorator;
import View.Game.GamePanel;
import View.View;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    protected int x;
    protected int y;
    protected Timer timer;

    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(int idx, Graphics graphics);
    protected abstract Image getImage();

    public void stop() {
        timer.stop();
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) { this.health = health; }
    public int getX() { return x; }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
}
