import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    private Timer sunProduceTimer;
    Image image = new ImageIcon(this.getClass().getResource("images/plants/sunflower.gif")).getImage();
    public Sunflower(GamePanel parent, int x, int y) {
        super(parent, x, y);
        super.image = this.image;
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {
            Sun sta = new Sun(getGp(), 60 + x * 100, 110 + y * 120, 130 + y * 120);
            getGp().getActiveSuns().add(sta);
            getGp().add(sta, new Integer(1));
        });
        sunProduceTimer.start();
    }

}
