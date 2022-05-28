package Model.Plant;

import Model.Plant.Action.BasicPlantAction;
import Model.Plant.Action.FreezeShootDecorator;
import Model.Plant.Action.PlantAction;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class FreezePeashooter extends Plant {
    private boolean isStartTimer = false;

    public FreezePeashooter(int x, int y) { super(x, y); }

    @Override
    public void draw(int idx, Graphics graphics) {
        PlantAction plantAction = new FreezeShootDecorator(
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
        return new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
    }
}