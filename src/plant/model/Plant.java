package plant.model;

import Game.view.GamePanel;
import Lane.model.Lane;
import plant.strategy.PlantProduceSunStrategy;
import plant.strategy.PlantShootingStrategy;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    private int x;
    private int y;

    private GamePanel gp;
    private Lane lane;

    private PlantShootingStrategy plantShootingStrategy;
    private PlantProduceSunStrategy plantProduceSunStrategy;


    public Plant(int x, int y) {
        this.x = x;
        this.y = y;
        gp = gp.getInstance();
        lane = lane.getInstance();
    }

    public void shoot() {
        plantShootingStrategy.shoot(x, y, lane);
    }

    public void produceSun() {plantProduceSunStrategy.produceSun(x, y);}

    public void setPlantShootingStrategy(PlantShootingStrategy plantShootingStrategy) {
        this.plantShootingStrategy = plantShootingStrategy;
    }
    public void setPlantProduceSunStrategy(PlantProduceSunStrategy plantProduceSunStrategy) {
        this.plantProduceSunStrategy = plantProduceSunStrategy;
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

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

}
