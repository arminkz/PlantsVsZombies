package sun.model;

import Game.view.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/27/2016.
 */
public class Sun {
    private int Xposition;
    private int YPosition;
    private int endYposition;

    private int destructTime = 200;

    public Sun(int startX, int startY, int endY) {
        this.endYposition = endY;
        Xposition = startX;
        YPosition = startY;
    }

    public void move() {
        if (YPosition < endYposition) {
            YPosition += 4;
        }
    }
    public int getXPosition(){
        return Xposition;
    }
    public int getYPosition(){
        return YPosition;
    }

}
