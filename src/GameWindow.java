import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class GameWindow extends JFrame {
    public static final int WINDOW_WIDTH = 1012;
    public static final int WINDOW_HEIGHT = 785;
    public static final int SUNFLOWER_CARD_POS_X = 110;
    public static final int PEASHOOTER_CARD_POS_X = 175;
    public static final int FREEZEPEASHOOTER_CARD_POS_X = 240;
    public static final int PLANT_POS_Y = 8;
    public static final int SUN_POS_X = 37;
    public static final int SUN_POS_Y = 80;
    public static final int SUN_WIDTH = 60;
    public static final int SUN_HEIGHT = 20;

    private static MenuFrame menuFrame;

    enum PlantType { None, Sunflower, Peashooter, FreezePeashooter }

    public GameWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(SUN_POS_X, SUN_POS_Y);
        sun.setSize(SUN_WIDTH, SUN_HEIGHT);

        GamePanel gamePanel = new GamePanel(sun);
        gamePanel.setLocation(0, 0);
        getLayeredPane().add(gamePanel, new Integer(0));

        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(SUNFLOWER_CARD_POS_X, PLANT_POS_Y);
        sunflower.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(PEASHOOTER_CARD_POS_X, PLANT_POS_Y);
        peashooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        PlantCard freezePeaShooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezePeaShooter.setLocation(FREEZEPEASHOOTER_CARD_POS_X, PLANT_POS_Y);
        freezePeaShooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezePeaShooter, new Integer(3));

        getLayeredPane().add(sun, new Integer(2));
        setResizable(false);
        setVisible(true);
    }

    static GameWindow gameWindow;

    public static void begin() {
        menuFrame.dispose();
        gameWindow = new GameWindow();
    }

    public static void setMenuFrame(MenuFrame _menuFrame) {
        menuFrame = _menuFrame;
    }

    public static void main(String[] args) {
        setMenuFrame(new MenuFrame());
    }
}
