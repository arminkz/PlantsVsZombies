package Model.Plant.Action;

import javax.swing.*;
import java.awt.*;

public abstract class PlantAction {
    public abstract void action(Image image, int idx, Graphics graphics, Timer shootTimer);
}
