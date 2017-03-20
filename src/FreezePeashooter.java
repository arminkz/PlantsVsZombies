import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {

    public Timer shootTimer;


    public FreezePeashooter(GamePanel parent,int x,int y) {
        super(parent,x,y);
        shootTimer = new Timer(2000,(ActionEvent e) -> {
            //System.out.println("SHOOT");
            if(gp.laneZombies.get(y).size() > 0) {
                gp.lanePeas.get(y).add(new FreezePea(gp, y, 103 + this.x * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop(){
        shootTimer.stop();
    }

}