import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Timer shootTimer;


    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            if (getGamePanel().getLaneZombies().get(y).size() > 0) {
                getGamePanel().getLanePeas().get(y).add(new Pea(getGamePanel(), y, 103 + this.getX() * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
