package plant.model;

import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.producer.SunFlowerSunProducer;
import sun.producer.SunProducer;
import sun.view.SunView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    private SunProducer sunProducer;
    public Sunflower(int x, int y) {
        super(x, y);
        sunProducer = new SunFlowerSunProducer(15000,x,y);
        sunProducer.start();
    }

}
