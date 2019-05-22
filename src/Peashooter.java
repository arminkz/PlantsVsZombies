import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Timer shootTimer;
    Image image = new ImageIcon(this.getClass().getResource("images/plants/peashooter.gif")).getImage();

    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        super.image = this.image;
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGp().getLaneZombies().get(y).size() > 0) {
                getGp().getLanePeas().get(y).add(new Pea(getGp(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
