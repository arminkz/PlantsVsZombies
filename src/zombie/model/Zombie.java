package zombie.model;

import Game.Collider;
import Game.view.GamePanel;
import Game.view.GameWindow;

import java.awt.Image;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Zombie {

	protected int health = 1000;
    protected int speed = 1;
    protected int attackPower = 10;
    
    private GamePanel gamePanel; 
    protected static Image zombieImage;

    private int slowInt = 0;
    private int posX = 1000;
    private int myLane;
    private boolean alive = true;	// isMoving -> Alive

    public Zombie(int lane) {
        myLane = lane;
        
        setImage();
    }

    protected abstract void setImage();
    
    public static Image getImage() {
        return zombieImage;
    }
    
    public void advance() {

        boolean isCollided = false;
        Collider collidedPlant = getCollidedPlant(isCollided);
        
        if (!isCollided) {
            move();
        } 
        else {
            attackPlant(collidedPlant);
        }
        
        if (health <= 0 || posX < 0) { alive = false; }
    }
   
    private Collider getCollidedPlant(boolean isCollided) {
    	Collider collidedPlant = null;
    	
        for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
            boolean isPlantExist = gamePanel.getColliders()[i].getPlant() != null;
			boolean isInsideCollider = gamePanel.getColliders()[i].isInsideCollider(posX);
			
			if (isPlantExist && isInsideCollider) {
                isCollided = true;
                collidedPlant = gamePanel.getColliders()[i];
            }
        }
    	return collidedPlant;
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
        //Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "zombie.model.NormalZombie":
            	Zombie normalZombie = new NormalZombie(parent, lane);
            	return normalZombie;
            case "zombie.model.ConeHeadZombie":
            	Zombie conHeadZombie = new ConeHeadZombie(parent, lane);
                return conHeadZombie;
        }
        return null;
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
