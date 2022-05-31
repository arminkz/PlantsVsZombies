package View.Element;

import javax.swing.*;

import View.Game.GamePanel;
import View.Element.SunState;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/27/2016.
 */
public class Sun extends JPanel implements MouseListener {

    private static final int START_Y = 0;
	private static final int START_X = 0;
	private GamePanel gamePanel;
    private SunState sunState;
    private Image sunImage;

    public Sun(GamePanel parent, int startX, int startY, int endY) {
        this.gamePanel = parent;
        setSize(80, 80);
        setOpaque(false);
        addMouseListener(this);

        sunState = new SunState(startX, startY, endY);
        sunImage = new ImageIcon(this.getClass().getResource("../../images/sun.png")).getImage();
        setLocation(this.sunState.getPositionX(), this.sunState.getPositionY());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(sunImage, START_X, START_Y, null);
    }

    public void advance() {
        updateLocation();
    }
    
    public void updateLocation() {
    	this.sunState.updateState();
    	
    	if (this.sunState.getDestructionTime() < 0) {
    		gamePanel.remove(this);
            gamePanel.getActiveSuns().remove(this);
    	}
    	setLocation(this.sunState.getPositionX(), this.sunState.getPositionY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gamePanel.setSunScore(gamePanel.getSunScore() + 25);
        gamePanel.remove(this);
        gamePanel.getActiveSuns().remove(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}