package Model.Plant;

import View.Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

import Model.Pea.FreezePea;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Peashooter {

    public FreezePeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createFreezePea(y);
        shootTimer.start();
    }

    private void createFreezePea(int y) {
        shootTimer = new Timer(SHOOT_DELAY, (ActionEvent e) -> {
            //System.out.println("SHOOT");
            final boolean isExistZombie = getGamePanel().getLaneZombies().get(y).size() > 0;
            
            if ( isExistZombie ) {
                getGamePanel().getLanePeas().get(y).add(new FreezePea(getGamePanel(), y, STARTING_POSITION_OF_PEA + this.getX() * MOVE_PER_FRAME));
            }
        });
    }

    @Override
    public void stop() {
        shootTimer.stop();
    }

}