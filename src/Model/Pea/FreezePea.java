package Model.Pea;

import View.Game.GamePanel;

import javax.swing.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {
    public FreezePea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX);
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/freezepea.png")).getImage();
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
