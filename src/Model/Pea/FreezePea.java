package Model.Pea;

import View.Game.GamePanel;
import View.Pea.FreezePeaView;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {
    public FreezePea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX, new FreezePeaView());
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
