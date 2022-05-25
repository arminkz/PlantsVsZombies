package Model.Plant;

import Model.Pea.NormalPea;
import Model.Pea.Pea;
import View.Game.GamePanel;

public class NormalPeashooter extends Peashooter{
    public NormalPeashooter(GamePanel parent, int x, int y) {
        super(parent, x, y);
        createPea(y);
        shootTimer.start();
    }

    @Override
    protected Pea getPea() {
        return new NormalPea(GamePanel.getInstance(), y, STARTING_POSITION_OF_PEA + this.getX() * MOVE_PER_FRAME);
    }
}
