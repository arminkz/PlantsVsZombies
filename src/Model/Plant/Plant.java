package Model.Plant;

import View.Game.GamePanel;
import View.View;


/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    private int x;
    private int y;

    private View view;
    private GamePanel gamePanel;

    public Plant(GamePanel gamePanel, int x, int y, View view) {
        this.x = x;
        this.y = y;
        this.view = view;
        this.gamePanel = gamePanel;
    }

    public Plant(GamePanel gamePanel, int x, int y) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }

    public View getPlantView() {
        return view;
    }

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
