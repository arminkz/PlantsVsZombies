package Model.Plant;

import Model.Pea.Pea;
import Model.Zombie.Zombie;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Armin on 6/25/2016.
 */
public class Peashooter extends Plant {

    public Timer shootTimer;

    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        shootTimer = new Timer(SHOOT_DELAY, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            ArrayList<Zombie> laneZombie = getGamePanel().getLaneZombies().get(y);
            ArrayList<Pea> lanePea = getGamePanel().getLanePeas().get(y);

            if (laneZombie.size() > 0) {
                lanePea.add(new Pea(getGamePanel(), y, STARTING_POSITION_OF_PEA + this.getX() * 100));
            }
        });
        shootTimer.start();
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
