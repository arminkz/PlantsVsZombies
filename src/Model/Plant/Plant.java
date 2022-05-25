package Model.Plant;

import View.Game.GamePanel;
import View.View;

import java.awt.*;


/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    protected int x;
    protected int y;

    private GamePanel gamePanel;
    protected Image image;

    public Plant(GamePanel gamePanel, int x, int y) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        setImage();
    }

    public void draw(int x, int y, Graphics graphics) {
        graphics.drawImage(image, x, y, null);
    }

    protected abstract void setImage();

    public void stop() {}

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
