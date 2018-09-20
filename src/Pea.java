import javax.swing.*;
import java.awt.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class Pea {

    public int posX;
    protected GamePanel gp;
    public int myLane;

    public Pea(GamePanel parent,int lane,int startX){
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance(){
        Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
        for (int i = 0; i < gp.laneZombies.get(myLane).size(); i++) {
            Zombie z = gp.laneZombies.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
            if(pRect.intersects(zRect)){
                z.health -= 300;
                boolean exit = false;
                if(z.health < 0){
                    System.out.println("ZOMBIE DIE");
                    
                    gp.laneZombies.get(myLane).remove(i);
                    GamePanel.setProgress(10);
                    exit = true;
                }
                gp.lanePeas.get(myLane).remove(this);
                if(exit) break;
            }
        }
        /*if(posX > 2000){
            gp.lanePeas.get(myLane).remove(this);
        }*/
        posX += 15;
    }

}
