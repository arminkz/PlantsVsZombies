package plant.model;

import plant.strategy.ProduceSunBySunFlower;
import sun.producer.SunFlowerSunProducer;
import sun.producer.SunProducer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {
    private SunProducer sunProducer;
    private Timer sunProducerTimer;

    public Sunflower(int x, int y) {
        super(x, y);
        sunProducer = new SunFlowerSunProducer(x,y);
        sunProducerTimer = new Timer(15000,(ActionEvent e)->{sunProducer.createSunView();});
        sunProducerTimer.start();
    }
}
