package plant.model;

import java.awt.Image;
import Game.view.GamePanel;
import Lane.model.Lane;
import plant.strategy.PlantShootingStrategy;


import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    private int x;
    private int y;

    private Lane lane;
    private PlantShootingStrategy plantShootingStrategy;
    protected Image plantImage;


    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
        lane = lane.getInstance();
        setImage();
    }
    protected abstract void setImage();   
    public abstract int getPrice();
    public abstract void draw(Graphics g);
    public void shoot() {
        plantShootingStrategy.shoot(x, y, lane);
    }

    public Image getImage() {
      return plantImage;
    }


    public void setPlantShootingStrategy(PlantShootingStrategy plantShootingStrategy) {
        this.plantShootingStrategy = plantShootingStrategy;
    }

    public void stop() {
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

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
