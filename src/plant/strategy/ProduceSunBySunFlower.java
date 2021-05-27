package plant.strategy;

import sun.producer.SunFlowerSunProducer;
import sun.producer.SunProducer;

public class ProduceSunBySunFlower implements PlantProduceSunStrategy{
    private SunProducer sunProducer;
    @Override
    public void produceSun(int x, int y) {
        sunProducer = new SunFlowerSunProducer(15000,x,y);
        sunProducer.start();
    }
}
