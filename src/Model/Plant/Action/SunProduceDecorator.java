package Model.Plant.Action;

import View.Element.Sun;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SunProduceDecorator extends PlantActionDecorator {
    public SunProduceDecorator(PlantAction decoratedPlant) {
        super(decoratedPlant);
    }

    @Override
    public void action(Image image, int idx, Graphics graphics, Timer sunProduceTimer) {
        super.action(image, idx, graphics, sunProduceTimer);
        if(sunProduceTimer == null) return;
        SunProduceAction(idx, sunProduceTimer);
    }

    private void SunProduceAction(int idx, Timer sunProduceTimer) {
        int x = idx % 9;
        int y = idx / 9;
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {
            Sun sta = new Sun(GamePanel.getInstance(), 60 + x * 100, 110 + y * 120, 130 + y * 120);
            GamePanel.getInstance().getActiveSuns().add(sta);
            GamePanel.getInstance().add(sta, new Integer(1));
        });
        sunProduceTimer.start();
    }
}
