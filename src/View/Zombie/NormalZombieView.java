package View.Zombie;

import View.View;

import javax.swing.*;
import java.awt.*;

public class NormalZombieView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
    }
}
