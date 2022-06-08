package View.Game;

import View.Element.PlantCard;
import View.Menu.MenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class GameFrame extends JFrame {
    public static final int WINDOW_WIDTH = 1012;
    public static final int WINDOW_HEIGHT = 785;
    public static final int SUNFLOWER_CARD_POS_X = 110;
    public static final int NORMALPEASHOOTER_CARD_POS_X = 175;
    public static final int FREEZEPEASHOOTER_CARD_POS_X = 240;
    public static final int PLANT_POS_Y = 8;

    private static MenuFrame menuFrame;

    enum PlantType { None, Sunflower, NormalPeashooter, FreezePeashooter }

    public GameFrame() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        GamePanel gamePanel = GamePanel.getInstance();

        placeGamePanel(gamePanel);
        placeSunflowerCard(gamePanel);
        placePeashooterCard(gamePanel);
        placeFreezePeashooterCard(gamePanel);

        setResizable(false);
        setVisible(true);
    }

    public void placeGamePanel(GamePanel gamePanel) {
        gamePanel.setLocation(0, 0);
        getLayeredPane().add(gamePanel, new Integer(0));
    }

    public void placeSunflowerCard(GamePanel gamePanel) {
        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(SUNFLOWER_CARD_POS_X, PLANT_POS_Y);
        sunflower.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));
    }

    public void placePeashooterCard(GamePanel gamePanel) {
        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(NORMALPEASHOOTER_CARD_POS_X, PLANT_POS_Y);
        peashooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.NormalPeashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));
    }

    public void placeFreezePeashooterCard(GamePanel gamePanel) {
        PlantCard freezePeaShooter = new PlantCard(new ImageIcon(this.getClass().getResource("../../images/cards/card_freezepeashooter.png")).getImage());
        freezePeaShooter.setLocation(FREEZEPEASHOOTER_CARD_POS_X, PLANT_POS_Y);
        freezePeaShooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezePeaShooter, new Integer(3));
    }

    public static GameFrame gameFrame;

    public static void begin() {
        menuFrame.dispose();
        gameFrame = new GameFrame();
    }

    public static void setMenuFrame(MenuFrame _menuFrame) {
        menuFrame = _menuFrame;
    }

    public static void main(String[] args) {
        setMenuFrame(new MenuFrame());
    }
}
