package plant.model;

import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    private Timer sunProduceTimer;

    public Sunflower(int x, int y) {
        super(x, y);
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {
            Sun sta = new Sun(60 + x * 100, 110 + y * 120, 130 + y * 120);
            SunView sunView = new SunView(sta.getXPosition(), sta.getYPosition());
            SunPresenter sunPresenter = new SunPresenter(sunView,sta);
            sunView.init(sunPresenter);
            sunPresenter.start();
            getGp().add(sunView, new Integer(1));
        });
        sunProduceTimer.start();
    }

}
