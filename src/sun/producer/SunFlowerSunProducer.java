package sun.producer;


import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SunFlowerSunProducer implements SunProducer{
    private Timer sunProduceTimer;
    private int delay;
    private int xPosition;
    private int yPosition;

    public SunFlowerSunProducer(int delay, int xPosition, int yPosition){
        this.delay = delay;
        this.xPosition = xPosition;
        this.yPosition = yPosition;

        sunProduceTimer = new Timer(delay,(ActionEvent e) ->{createSunView();});
    }

    @Override
    public void start() {
        sunProduceTimer.start();
    }

    @Override
    public void createSunView() {
            Sun sta = new Sun(60 + xPosition * 100, 110 + yPosition * 120, 130 + yPosition * 120);
            SunView sunView = new SunView(sta.getXPosition(), sta.getYPosition());
            SunPresenter sunPresenter = new SunPresenter(sunView,sta);
            sunView.init(sunPresenter);
            sunPresenter.start();
    }
}
