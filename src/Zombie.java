import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Zombie {

    public int health = 1000;
    public int speed = 1;

    private GamePanel gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;

    public Zombie(GamePanel parent,int lane){
        this.gp = parent;
        myLane = lane;
    }

    public void advance(){
        if(isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.colliders[i].assignedPlant != null && gp.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.colliders[i];
                }
            }
            if (!isCollides) {
                if(slowInt>0){
                    if(slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
                }else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.health -= 10;
                if (collided.assignedPlant.health < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                GameWindow.gw.dispose();
                GameWindow.gw = new GameWindow();
            }
        }
    }

    int slowInt = 0;
    public void slow(){
        slowInt = 1000;
    }
    public static Zombie getZombie(String type,GamePanel parent, int lane) {
        Zombie z = new Zombie(parent,lane);
       switch(type) {
           case "NormalZombie" : z = new NormalZombie(parent,lane);
                                 break;
           case "ConeHeadZombie" : z = new ConeHeadZombie(parent,lane);
                                 break;
    }
       return z;
    }

}
