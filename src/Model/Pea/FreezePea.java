package Model.Pea;

import Model.Zombie.Zombie;
import View.Game.GamePanel;
import View.Pea.FreezePeaView;
import View.View;

import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {
    private int positionX;
    protected GamePanel gamePanel;
    private int myLane;
    private View view;

    public FreezePea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX, new FreezePeaView());
    }

    @Override
    public void advance() {
        Rectangle peaRectangle = new Rectangle(getPositionX(), 130 + getMyLane() * 120, 28, 28);
        for (int i = 0; i < gamePanel.getLaneZombies().get(getMyLane()).size(); i++) {
            Zombie zombie = gamePanel.getLaneZombies().get(getMyLane()).get(i);
            Rectangle zombieRectangle = new Rectangle(zombie.getPosX(), 109 + getMyLane() * 120, 400, 120);
            if (peaRectangle.intersects(zombieRectangle)) {
                zombie.setHealth(zombie.getHealth() - 300);
                zombie.slow();
                boolean exit = false;
                if (zombie.getHealth() < 0) {
                    System.out.println("ZOMBIE DIE");
                    gamePanel.getLaneZombies().get(myLane).remove(i);
                    GamePanel.setProgress(10);
                    exit = true;
                }
                gamePanel.getLanePeas().get(myLane).remove(this);
                if (exit) break;
            }
        }

        positionX += 15;
    }

    public View getView() { return view; }

    public int getPositionX() {
        return positionX;
    }

    public int getMyLane() {
        return myLane;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
}
