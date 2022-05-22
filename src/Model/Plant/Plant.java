package Model.Plant;

import Model.Pea.NormalPea;
import Model.Pea.Pea;
import Model.Zombie.Zombie;
import View.Game.GamePanel;
import View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    private int health = 200;

    private int x;
    private int y;

    private View view;
    private GamePanel gamePanel;
    public Timer shootTimer;

    public static final int STARTING_POSITION_OF_PEA = 103;
    public static final int SHOOT_DELAY = 2000;

    public Plant(GamePanel gamePanel, int x, int y, View view) {
        this.x = x;
        this.y = y;
        this.view = view;
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

    protected void createShootTimer(int y) {
        shootTimer = new Timer(SHOOT_DELAY, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            ArrayList<Zombie> laneZombie = getGamePanel().getLaneZombies().get(y);
            ArrayList<Pea> lanePea = getGamePanel().getLanePeas().get(y);

            if (laneZombie.size() > 0) {
                lanePea.add(new NormalPea(getGamePanel(), y, STARTING_POSITION_OF_PEA + this.getX() * 100));
            }
        });
    }
}
