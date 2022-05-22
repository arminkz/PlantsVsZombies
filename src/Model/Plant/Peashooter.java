package Model.Plant;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Model.Pea.Pea;
import View.Game.GamePanel;


/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public static final int MOVE_PER_FRAME = 100;
    public static final int STARTING_POSITION_OF_PEA = 103;
    public static final int SHOOT_DELAY = 2000;

    public Timer shootTimer;


    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createPea(y);
        shootTimer.start();
    }

    private void createPea(int y) {
        shootTimer = new Timer(SHOOT_DELAY, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            final boolean isExistZombie = getGamePanel().getLaneZombies().get(y).size() > 0;
            
            if (isExistZombie) {
                getGamePanel().getLanePeas().get(y).add(new Pea(getGamePanel(), y, STARTING_POSITION_OF_PEA + this.getX() * MOVE_PER_FRAME));
            }
        });
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
