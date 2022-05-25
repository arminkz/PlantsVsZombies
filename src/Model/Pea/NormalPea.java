package Model.Pea;

import View.Game.GamePanel;

import javax.swing.*;

public class NormalPea extends Pea {
    public NormalPea(GamePanel parent, int lane, int startPositionX) {
        super(parent, lane, startPositionX);
    }

    @Override
    protected void setImage() {
        this.image = new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
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
