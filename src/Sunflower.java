import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    Timer sunProduceTimer;

    public Sunflower(GamePanel parent,int x,int y) {
        super(parent, x, y);
        sunProduceTimer = new Timer(15000,(ActionEvent e) -> {
            Sun sta = new Sun(gp,60 + x*100,110 + y*120,130 + y*120);
            gp.activeSuns.add(sta);
            gp.add(sta,new Integer(1));
        });
        sunProduceTimer.start();
    }

}
