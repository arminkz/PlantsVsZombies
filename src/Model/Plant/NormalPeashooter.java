package Model.Plant;

import Model.Plant.Action.BasicPlantAction;
import Model.Plant.Action.NormalShootDecorator;
import Model.Plant.Action.PlantAction;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NormalPeashooter extends Plant{
    private boolean isStartTimer = false;
    public static final int NORMAL_PEASHOOTER_COST = 100;

    public NormalPeashooter(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(int idx, Graphics graphics) {
        PlantAction plantAction = new NormalShootDecorator(
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
        return new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
    }

    @Override
    public int getCost() {
        return NORMAL_PEASHOOTER_COST;
    }

    
}
