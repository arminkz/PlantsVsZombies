package sun.producer;


import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;

public class SunFlowerSunProducer implements SunProducer{
    private int xPosition;
    private int yPosition;

    public SunFlowerSunProducer(int xPosition, int yPosition){
        setXPosition(xPosition);
        setYPosition(yPosition);
    }

    @Override
    public void createSunView() {
            Sun sta = new Sun(60 + getXPosition() * 100, 110 + getYPosition() * 120, 130 + getYPosition() * 120);
            SunView sunView = new SunView(sta.getXPosition(), sta.getyPosition());
            SunPresenter sunPresenter = new SunPresenter(sunView,sta);
            sunView.init(sunPresenter);
            sunPresenter.start();
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
