package sun.presenter;

import Game.view.GamePanel;
import Game.view.GameWindow;
import sun.model.Sun;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SunPresenter {
    Timer advancerTimer;
    SunView sunView;
    Sun sun;

    public SunPresenter(SunView sunView, Sun sun){
        this.sunView = sunView;
        this.sun = sun;
    }

    public void mouseReleased(){
        sunView.remove();
    }

    public void advance(){
        sun.advance();
        setSunViewLocation();
    }

    public void start(){
        Timer advanceTimer = new Timer(60,(ActionEvent e) -> advance());
        advanceTimer.start();
    }

    public void setSunViewLocation(){
        sunView.setLocation(sun.getXPosition(), sun.getYPosition());
    }
}
