package Model.Plant.Action;

import javax.swing.*;
import java.awt.*;

public abstract class PlantActionDecorator extends PlantAction {
    private PlantAction decoratedPlant;

    public PlantActionDecorator(PlantAction decoratedPlant) {
        this.decoratedPlant = decoratedPlant;
    }

    @Override
    public void action(Image image, int idx, Graphics graphics, Timer shootTimer) {
        graphics.drawImage(image, 60 + (idx % 9) * 100, 129 + (idx / 9) * 120, null);
    }
}
