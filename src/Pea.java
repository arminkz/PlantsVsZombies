import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    private int xPosition;
    protected GamePanel gamePanel;
    private int myLane;

    public Pea(GamePanel parent, int lane, int startX) {
        this.gamePanel = parent;
        this.myLane = lane;
        xPosition = startX;
    }

    public void advance() {
        Rectangle pRect = new Rectangle(xPosition, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gamePanel.getLaneZombies().get(myLane).size(); i++) {
            Zombie z = gamePanel.getLaneZombies().get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.getPosX(), 109 + myLane * 120, 400, 120);
            if (pRect.intersects(zRect)) {
                z.setHealth(z.getHealth() - 300);
                boolean exit = false;
                if (z.getHealth() < 0) {
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

    public int getPosX() {
        return xPosition;
    }

    public void setPosX(int posX) {
        this.xPosition = posX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setMyLane(int myLane) {
        this.myLane = myLane;
    }
}
