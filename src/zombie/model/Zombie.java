package zombie.model;

import Game.Collider;
import Game.view.GamePanel;
import Game.view.GameWindow;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Zombie {

    private int health = 1000;
    private int speed = 1;
    private int slowInt = 0;
    private int attackPower = 10;

    private GamePanel gamePanel; 

    private int posX = 1000;
    private int myLane;
    private boolean alive = true;	// isMoving -> Alive

    public Zombie(GamePanel parent, int lane) {
        this.gamePanel = parent;
        myLane = lane;
    }

    public void advance() {

        boolean isCollided = false;
        Collider collidedPlant = null;
        for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
            if (gamePanel.getColliders()[i].getPlant() != null && gamePanel.getColliders()[i].isInsideCollider(posX)) {
                isCollided = true;
                collidedPlant = gamePanel.getColliders()[i];
            }
        }
        
        if (!isCollided) {
            move();
        } 
        else {	// attack plant
            attackPlant(collidedPlant);
        }
        
        if (health <= 0 || posX < 0) { alive = false; }

        
    }

	private void attackPlant(Collider collidedPlant) {
		collidedPlant.getPlant().setHealth(collidedPlant.getPlant().getHealth() - attackPower);
	}

	private void move() {
		if (slowInt > 0) {
		    if (slowInt % 2 == 0) {
		        posX--;
		    }
		    slowInt--;
		} else {
		    posX -= 1;
		}
	}
	
    public void slow() {
        slowInt = 1000;
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "zombie.model.NormalZombie":
                z = new NormalZombie(parent, lane);
                break;
            case "zombie.model.ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                break;
        }
        return z;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GamePanel getGp() {
        return gamePanel;
    }

    public void setGp(GamePanel gp) {
        this.gamePanel = gp;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setMoving(boolean moving) {
        alive = moving;
    }

    public int getSlowInt() {
        return slowInt;
    }

    public void setSlowInt(int slowInt) {
        this.slowInt = slowInt;
    }
}
