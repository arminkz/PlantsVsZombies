package View.Zombie;

import View.View;

import javax.swing.*;
import java.awt.*;

public class ConeHeadZombieView extends View {
    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/zombies/zombie2.png")).getImage();
    }
}
