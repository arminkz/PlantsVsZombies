package View.Pea;

import View.View;

import javax.swing.*;
import java.awt.*;

public class NormalPeaView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
    }
}
