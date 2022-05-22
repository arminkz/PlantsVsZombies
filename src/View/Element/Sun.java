package View.Element;

import javax.swing.*;

import View.Game.GamePanel;

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
    private Image sunImage;

    private int positionX;
    private int positionY;
    private int endPositonY;

    private int destructTime = 200;

    public Sun(GamePanel parent, int startX, int startY, int endY) {
        this.gamePanel = parent;
        this.endPositonY = endY;
        setSize(80, 80);
        setOpaque(false);
        positionX = startX;
        positionY = startY;
        setLocation(positionX, positionY);
        sunImage = new ImageIcon(this.getClass().getResource("images/sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(sunImage, START_X, START_Y, null);
    }

    public void advance() {
        position();
    }
    
	private void position() {
		if (positionY < endPositonY) {
            positionY += 4;
        } else {
            destructTime--;
            if (destructTime < 0) {
                gamePanel.remove(this);
                gamePanel.getActiveSuns().remove(this);
            }
        }
        setLocation(positionX, positionY);
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