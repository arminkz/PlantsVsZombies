package Model.Plant;

import View.Game.GamePanel;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    private int x;
    private int y;

    private GamePanel gamePanel;

    public static final int STARTING_POSITION_OF_PEA = 103;
    public static final int SHOOT_DELAY = 2000;

    public Plant(GamePanel parent, int x, int y) {
        this.x = x;
        this.y = y;
        gamePanel = parent;
    }

    public void stop() {
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
