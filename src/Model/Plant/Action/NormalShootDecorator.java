package Model.Plant.Action;

import Model.Lane.Lane;
import Model.Pea.NormalPea;
import View.Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NormalShootDecorator extends PlantActionDecorator {

    public NormalShootDecorator(PlantAction decoratedPlant) {
        super(decoratedPlant);
    }

    @Override
    public void action(Image image, int idx, Graphics graphics, Timer shootTimer) {
        super.action(image, idx, graphics, shootTimer);
        if(shootTimer == null) return;
        NormalShootAction(idx, shootTimer);
    }

    private void NormalShootAction(int idx, Timer shootTimer) {
        int x = idx % 9;
        int y = idx / 9;
        shootTimer = new Timer(2000, (ActionEvent e) -> {
            final boolean isExistZombie = Lane.getInstance().getLaneZombies().get(y).size() > 0;
            if (!isExistZombie) return;
            Lane.getInstance().getLanePeas().get(y).add(new NormalPea(GamePanel.getInstance(), y, 103 + x * 100));
        });
        shootTimer.start();
    }
}
