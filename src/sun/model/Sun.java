package sun.model;

/**
 * Created by Armin on 6/27/2016.
 */
public class Sun {
    private final int xPosition;
    private int yPosition;
    private final int endYPosition;

    private int destructTime = 200;

    public Sun(int startX, int startY, int endY) {
        this.endYPosition = endY;
        xPosition = startX;
        setyPosition(startY);
    }

    public void move() {
        if (getyPosition() < getEndYPosition()) {
            setyPosition(getyPosition() + 4);
        }
        else{
            setDestructTime(getDestructTime() - 1);
        }
    }
    public int getXPosition(){
        return getxPosition();
    }
    public int getyPosition(){
        return yPosition;
    }

    public int getDestructTime() {
        return destructTime;
    }

    public void setDestructTime(int destructTime) {
        this.destructTime = destructTime;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getEndYPosition() {
        return endYPosition;
    }
}
