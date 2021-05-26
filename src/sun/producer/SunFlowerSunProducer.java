package sun.producer;


import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SunFlowerSunProducer implements SunProducer{
    private int xPosition;
    private int yPosition;

    public SunFlowerSunProducer(int xPosition, int yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
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
