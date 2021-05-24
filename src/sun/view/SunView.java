package sun.view;

import Game.view.GamePanel;
import sun.presenter.SunPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SunView extends JPanel {
    private Image sunImage;

    public SunView(int x, int y){
        setSize(80, 80);
        setOpaque(false);
        setLocation(x,y);
        sunImage = new ImageIcon(this.getClass().getResource("../../images/sun.png")).getImage();
    }

    public void init(SunPresenter sunPresenter){
        bindTo(sunPresenter);
        this.setVisible(true);
        GamePanel.getInstance().add(this,1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(sunImage,0,0,null);
    }

    private void bindTo(final SunPresenter p){
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                p.mouseReleased();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void remove(){
        GamePanel.getInstance().remove(this);
        GamePanel.getInstance().setSunScore(GamePanel.getInstance().getSunScore() + 25);
    }
}
