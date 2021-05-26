package sun.producer;

import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RandomSunProducer implements SunProducer{

    public RandomSunProducer(){
    }

    @Override
    public void createSunView() {
        Random rnd = new Random();
        Sun sta = new Sun(rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
        SunView sunView = new SunView(sta.getXPosition(), sta.getYPosition());
        SunPresenter sunPresenter = new SunPresenter(sunView,sta);
        sunView.init(sunPresenter);
        sunPresenter.start();
    }
}
