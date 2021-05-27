package plant.model;

import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.producer.SunFlowerSunProducer;
import sun.producer.SunProducer;
import sun.view.SunView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {

    private static final int Sunflower_Price = 50;
    private SunProducer sunProducer;
    private Timer sunProducerTimer;
    private Image sunflowerImage;

    public Sunflower(int x, int y) {
        super(x, y);
        sunProducer = new SunFlowerSunProducer(x,y);
        sunProducerTimer = new Timer(15000,(ActionEvent e)->{sunProducer.createSunView();});
        sunProducerTimer.start();
        sunflowerImage = new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
    }

    @Override
    public int getPrice() {
        return Sunflower_Price;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sunflowerImage,60+getX()*100, 129 + getY()*120,null);
    }

}
