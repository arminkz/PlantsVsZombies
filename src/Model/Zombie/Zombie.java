package Model.Zombie;

import View.Collider;
import View.Game.GameFrame;
import View.Game.GamePanel;

import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Zombie {

    private int health = 1000;
    private int speed = 1;

    private GamePanel gamepanel;

    private int positionX = 1000;
    private int myLane;
    private boolean isMoving = true;

    public Zombie(GamePanel parent, int lane) {
        this.gamepanel = parent;
        myLane = lane;
    }

    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collidedPlants = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gamepanel.getColliders()[i].assignedPlant != null && gamepanel.getColliders()[i].isInsideCollider(positionX)) {
                    isCollides = true;
                    collidedPlants = gamepanel.getColliders()[i];
                }
            }
            if (!isCollides) {
                moving();
            } else {
                getAttack(collidedPlants);
            }
            if (positionX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gamepanel, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                GameFrame.gameFrame.dispose();
                GameFrame.gameFrame = new GameFrame();
            }
        }
    }

	private void getAttack(Collider collidedPlants) {
		collidedPlants.assignedPlant.setHealth(collidedPlants.assignedPlant.getHealth() - 10);
		if (collidedPlants.assignedPlant.getHealth() < 0) {
		    collidedPlants.removePlant();
		}
	}

	private void moving() {
		if (slowInt > 0) {
		    if (slowInt % 2 == 0) {
		        positionX--;
		    }
		    slowInt--;
		} else {
		    positionX -= 1;
		}
	}

    int slowInt = 0;

    public void slow() {
        slowInt = 1000;
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        Zombie zombie = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                zombie = new NormalZombie(parent, lane);
                break;
            case "ConeHeadZombie":
                zombie = new ConeHeadZombie(parent, lane);
                break;
        }
        return zombie;
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
        return gamepanel;
    }

    public void setGp(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    public int getPosX() {
        return positionX;
    }

    public void setPosX(int positionX) {
        this.positionX = positionX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
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
