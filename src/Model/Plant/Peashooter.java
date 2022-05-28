package Model.Plant;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Model.Lane.Lane;
import Model.Pea.NormalPea;
import Model.Pea.Pea;
import View.Game.GamePanel;


/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Peashooter extends Plant {

    public static final int MOVE_PER_FRAME = 100;
    public static final int STARTING_POSITION_OF_PEA = 103;
    public static final int SHOOT_DELAY = 2000;

    public Timer shootTimer;

    public Peashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createPea(y);
        shootTimer.start();
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
    }

    protected void createPea(int y) {
        shootTimer = new Timer(SHOOT_DELAY, (ActionEvent e) -> {
            final boolean isExistZombie = Lane.getInstance().getLaneZombies().get(y).size() > 0;
            
            if (isExistZombie) {
                Lane.getInstance().getLanePeas().get(y).add(getPea());
            }
        });
    }

    protected abstract Pea getPea();

    @Override
    public void stop() {
        shootTimer.stop();
    }

}
