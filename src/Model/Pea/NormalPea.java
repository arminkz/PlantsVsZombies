package Model.Pea;

import View.Game.GamePanel;
import View.Pea.NormalPeaView;

public class NormalPea extends Pea {
    public NormalPea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX, new NormalPeaView());
    }

    
    @Override
    public void advance() {
    	setPositionX(getPositionX() + 15);
    }

	@Override
	public int getPower() {
        return 300;
    }
}
