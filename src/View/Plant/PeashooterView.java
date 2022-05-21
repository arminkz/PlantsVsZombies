package View.Plant;

import View.View;

import javax.swing.*;
import java.awt.*;

public class PeashooterView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
    }
}
