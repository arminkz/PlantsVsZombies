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
        yPosition = startY;
    }

    public void move() {
        if (yPosition < endYPosition) {
            yPosition += 4;
        }
    }
    public int getXPosition(){
        return xPosition;
    }
    public int getyPosition(){
        return yPosition;
    }

}
