package Game.view;

import plantCard.model.PlantCard;
import sun.producer.RandomSunProducer;
import sun.producer.SunProducer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class GameWindow extends JFrame {

    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }
    private GamePanel gp;
    private static SunProducer sunProducer;

    //PlantType activePlantingBrush = PlantType.None;

    public GameWindow() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        addPlantCard();
        sunProducer = new RandomSunProducer(5000);
    }

    private void addPlantCard() {
        gp = GamePanel.getInstance();
        gp.setLocation(0, 0);
        getLayeredPane().add(gp, new Integer(0));

        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110, 8);
        sunflower.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175, 8);
        peashooter.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        PlantCard freezepeashooter = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_freezepeashooter.png")).getImage());
        freezepeashooter.setLocation(240, 8);
        freezepeashooter.setAction((ActionEvent e) -> {
            gp.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezepeashooter, new Integer(3));
    }

    public GameWindow(boolean b) {
        Menu menu = new Menu();
        menu.setLocation(0, 0);
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menu, new Integer(0));
        menu.repaint();
        setResizable(false);
        setVisible(true);
    }

    static public GameWindow gw;

    public static void begin() {
        gw.dispose();
        gw = new GameWindow();
        sunProducer.start();
    }

    public static void main(String[] args) {
        gw = new GameWindow(true);
    }

}
