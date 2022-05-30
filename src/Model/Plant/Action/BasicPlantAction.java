package Model.Plant.Action;

import javax.swing.*;
import java.awt.*;

public class BasicPlantAction extends PlantAction {
    @Override
    public void action(Image image, int idx, Graphics graphics, Timer shootTimer) {
        graphics.drawImage(image, 60 + (idx % 9) * 100, 129 + (idx / 9) * 120, null);
    }
}
