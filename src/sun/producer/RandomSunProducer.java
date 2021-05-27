package sun.producer;

import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

import java.util.Random;

public class RandomSunProducer implements SunProducer{

    @Override
    public void createSunView() {
        Random rnd = new Random();
        Sun sta = new Sun(rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
        SunView sunView = new SunView(sta.getXPosition(), sta.getyPosition());
        SunPresenter sunPresenter = new SunPresenter(sunView,sta);
        sunView.init(sunPresenter);
        sunPresenter.start();
    }
}
