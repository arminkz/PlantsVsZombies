package Model.Pea;

import Model.Zombie.Zombie;
import View.Game.GamePanel;
import View.Pea.NormalPeaView;

import java.awt.*;

public class NormalPea extends Pea {
    public NormalPea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX, new NormalPeaView());
    }

    
    @Override
    public void advance() {
        Rectangle peaRectangle = new Rectangle(positionX, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gamePanel.getLaneZombies().get(myLane).size(); i++) {
            Zombie zombie = gamePanel.getLaneZombies().get(myLane).get(i);
            Rectangle zombieRectangle = new Rectangle(zombie.getPosX(), 109 + myLane * 120, 400, 120);
            if (!peaRectangle.intersects(zombieRectangle)) continue;
            zombie.setHealth(zombie.getHealth() - 300);
            if (zombie.getHealth() > 0) continue;
            System.out.println("ZOMBIE DIED");
            gamePanel.getLaneZombies().get(myLane).remove(i);
            GamePanel.setProgress(10);
        }
        positionX += 15;
    }

	@Override
	public int getPower() {
        return 300;
    }
}
