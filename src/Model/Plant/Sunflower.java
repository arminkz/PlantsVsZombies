package Model.Plant;

import Model.Plant.Action.BasicPlantAction;
import Model.Plant.Action.PlantAction;
import Model.Plant.Action.SunProduceDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/28/2016.
 */
public class Sunflower extends Plant {
    private boolean isStartTimer = false;
    public static final int SUNFLOWER_COST = 50;

    public Sunflower(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(int idx, Graphics graphics) {
        PlantAction plantAction = new SunProduceDecorator(
                new BasicPlantAction()
        );
        if(!isStartTimer) {
            timer = new Timer(0, (ActionEvent e) -> {});
            isStartTimer = true;
        } else {
            timer = null;
        }
        plantAction.action(getImage(), idx, graphics, timer);
    }

    @Override
    protected Image getImage() {
        return new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
    }

    @Override
    public int getCost() {
        return SUNFLOWER_COST;
    }
}
