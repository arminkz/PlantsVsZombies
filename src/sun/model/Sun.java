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
    private int myX;
    private int myY;
    private int endY;

    private int destruct = 200;

    public Sun(int startX, int startY, int endY) {
        this.endY = endY;
        myX = startX;
        myY = startY;
    }

    public void advance() {
        if (myY < endY) {
            myY += 4;
        }
    }
    public int getXPosition(){
        return myX;
    }
    public int getYPosition(){
        return myY;
    }

}
