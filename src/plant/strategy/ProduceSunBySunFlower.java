package plant.strategy;

import sun.producer.SunFlowerSunProducer;
import sun.producer.SunProducer;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class ProduceSunBySunFlower {
    private SunProducer sunProducer;
    private Timer sunProducerTimer;

    private void ProduceSunBySunFlower(int x, int y) {
        sunProducer = new SunFlowerSunProducer(x,y);
        sunProducerTimer = new Timer(15000,(ActionEvent e)->{sunProducer.createSunView();});
        sunProducerTimer.start();
    }

    public void produceSun(int x, int y) {
        ProduceSunBySunFlower(x,y);
    }
}
