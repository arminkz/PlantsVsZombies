package zombie.model;

import Game.Collider;
import Game.view.GamePanel;

import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Zombie {

	private int health = 1000;
    private int speed = 1;
    protected int attackPower = 10;

    private static Image zombieImage;
    private int slowInt = 0;
    private int XPosition = 1000;
    private int YPosition;
    private int myLane;
    private boolean alive = true;	// isMoving -> Alive

    public Zombie(int lane) {
        myLane = lane;
        setYPosition(lane * 120 + 109);
    }

    protected void setImage(Image image){ zombieImage = image; }
    
    public static Image getImage() {
        return zombieImage;
    }
    
    public void advance() {

        Collider collidedPlant = getCollidedPlant();

        if (collidedPlant == null) {
            move();
        } 
        else {
            attackPlant(collidedPlant);
        }
        
        if (getHealth() <= 0 || XPosition < 0) { alive = false; }
    }

    private Collider getCollidedPlant() {
    	Collider collidedPlant = null;

        for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
            boolean isPlantExist = GamePanel.getInstance().getColliders()[i].getPlant() != null;
			boolean isInsideCollider = GamePanel.getInstance().getColliders()[i].isInsideCollider(XPosition);

			if (isPlantExist && isInsideCollider) {
                collidedPlant = GamePanel.getInstance().getColliders()[i];
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
		        XPosition--;
		    }
		    slowInt--;
		} else {
		    XPosition -= 1;
		}
	}
	
    public void slow() {
        slowInt = 1000;
    }

    public void draw(Graphics g){
        g.drawImage(zombieImage,getXPosition(),getYPosition(),null);
    }

    public static Zombie getZombie(String type,  int lane) {
        //Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "zombie.model.NormalZombie":
            	Zombie normalZombie = new NormalZombie(lane);
            	return normalZombie;
            case "zombie.model.ConeHeadZombie":
            	Zombie conHeadZombie = new ConeHeadZombie( lane);
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

    public int getXPosition() {
        return XPosition;
    }

    public void setXPosition(int XPosition) {
        this.XPosition = XPosition;
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

    public int getYPosition() {
        return YPosition;
    }

    public void setYPosition(int YPosition) {
        this.YPosition = YPosition;
    }
}
