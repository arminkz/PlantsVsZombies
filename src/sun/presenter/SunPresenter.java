package sun.presenter;

import Game.view.GamePanel;
import Game.view.GameWindow;
import sun.model.Sun;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SunPresenter {
    Timer moveTimer;
    SunView sunView;
    Sun sun;

    public SunPresenter(SunView sunView, Sun sun){
        this.sunView = sunView;
        this.sun = sun;
        moveTimer = new Timer(60,(ActionEvent e) -> move());
    }

    public void mouseReleased() {
        sunView.remove();
    }


    public void start() {
        moveTimer.start();
    }


    public void move() {
        sun.move();
        setSunViewLocation();
    }

    public void setSunViewLocation() {
        sunView.setLocation(sun.getXPosition(), sun.getYPosition());
    }
}
