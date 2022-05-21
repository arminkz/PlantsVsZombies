package View.Pea;

import View.View;

import javax.swing.*;
import java.awt.*;

public class FreezePeaView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/freezepea.png")).getImage();
    }
}
