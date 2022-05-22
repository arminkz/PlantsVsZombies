package View.Plant;

import View.View;

import javax.swing.*;
import java.awt.*;

public class SunflowerView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
    }
}
