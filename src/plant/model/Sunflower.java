package plant.model;

import plant.strategy.PlantProduceSunStrategy;
import plant.strategy.ProduceSunBySunFlower;
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

    public Sunflower(int x, int y) {
        super(x, y);
        this.setPlantProduceSunStrategy(new ProduceSunBySunFlower());
        produceSun();
    }

    @Override
    public void produceSun() {
        super.produceSun();
    }
}
