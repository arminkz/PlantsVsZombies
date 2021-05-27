package Pea.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Armin on 6/28/2016.
 */
public class FreezePea extends Pea {

    public FreezePea(int lane, int startX) {
        super(lane, startX);
    }

    @Override
    public int getPower() {
        return 300;
    }

    protected void setImage() {
        peaImage = new ImageIcon(this.getClass().getResource("../../images/freezepea.png")).getImage();
    }

    @Override
    public void advance() {
        setXPosition(getXPosition() - 15);
    }

}
