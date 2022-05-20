package Model.Pea;

import Model.Zombie.Zombie;
import View.Game.GamePanel;

import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    private int positionX;
    protected GamePanel gamePanel;
    private int myLane;

    public Pea(GamePanel parent, int lane, int startPositionX) {
        this.gamePanel = parent;
        this.myLane = lane;
        positionX = startPositionX;
    }

    public void advance() {
        Rectangle peaRectangle = new Rectangle(positionX, 130 + myLane * 120, 28, 28);
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
        positionX += 15;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
