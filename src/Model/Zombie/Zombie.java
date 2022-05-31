package Model.Zombie;

import Model.Plant.Plant;
import View.Collider;
import View.Game.GameFrame;
import View.Game.GamePanel;
import View.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Zombie {

    private int health = 1000;
    private int speed = 1;

    protected Image zImage;

    private int positionX = 1000;
    private int lane;
    private boolean isMoving = true;

    public Zombie(GamePanel parent, int lane) {
        this.lane = lane;

    }

    protected abstract void setImage(String imgPath);    

    public void advance() {
        GamePanel gamePanel = GamePanel.getInstance();
        if (!isMoving) return;
        boolean isCollides = false;
        Collider collidedPlants = null;
        for (int i = lane * 9; i < (lane + 1) * 9; i++) {
            if (gamePanel.getColliders()[i].assignedPlant == null || !gamePanel.getColliders()[i].isInsideCollider(positionX)) continue;
            isCollides = true;
            collidedPlants = gamePanel.getColliders()[i];
        }
        if (!isCollides && positionX >= 0) {
            moving();
            return;
        }
        if (isCollides && positionX >= 0) {
            attackPlants(collidedPlants);
            return;
        }
		
        isMoving = false;
        gameOver();
    }

	private void gameOver() {
		JOptionPane.showMessageDialog(GamePanel.getInstance(), "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
        GameFrame.gameFrame.dispose();
        GameFrame.gameFrame = new GameFrame();
	}

	protected abstract void attackPlants(Collider collidedPlants);

	private void moving() {
        if(slowInt <= 0) {
            positionX -= 1;
            return;
        }
        if(slowInt % 2 == 0) {
            positionX--;
        }
        slowInt--;
	}

    int slowInt = 0;

    public void slow() {
        slowInt = 1000;
    }
    
    public void draw(int x, int y, Graphics graphics) {
        graphics.drawImage(zImage, x, y, null);
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        switch (type) {
            case "NormalZombie":
                return new NormalZombie(parent, lane);
            case "ConeHeadZombie":
                return new ConeHeadZombie(parent, lane);
            default:
                return null;
        }
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) { this.health = health; }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getPosX() {
        return positionX;
    }
    public void setPosX(int positionX) {
        this.positionX = positionX;
    }
    public int getLane() {
        return lane;
    }
    public void setLane(int lane) {
        this.lane = lane;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean moving) {
        isMoving = moving;
    }
    public int getSlowInt() {
        return slowInt;
    }
    public void setSlowInt(int slowInt) {
        this.slowInt = slowInt;
    }
}
