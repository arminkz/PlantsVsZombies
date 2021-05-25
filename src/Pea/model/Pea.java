package Pea.model;

import Game.view.GamePanel;
import zombie.model.Zombie;

import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    private int xPosition;
    protected GamePanel gamePanel;
    private int myLane;

    public Pea(int lane, int startX) {
        gamePanel = gamePanel.getInstance();
        this.myLane = lane;
        xPosition = startX;
    }

    public void advance() {
        Rectangle peaRectangle = new Rectangle(xPosition, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gamePanel.getLaneZombies().get(myLane).size(); i++) {
            Zombie zombie = gamePanel.getLaneZombies().get(myLane).get(i);
            Rectangle zombieRectangle = new Rectangle(zombie.getPosX(), 109 + myLane * 120, 400, 120);
            if (peaRectangle.intersects(zombieRectangle)) {
                zombie.setHealth(zombie.getHealth() - 300);
                boolean exit = false;
                if (zombie.getHealth() < 0) {
                    System.out.println("ZOMBIE DIED");

                    gamePanel.getLaneZombies().get(myLane).remove(i);
                    GamePanel.setProgress(10);
                    exit = true;
                }
                gamePanel.getLaneZombies().get(myLane).remove(this);
                if (exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        xPosition += 15;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
