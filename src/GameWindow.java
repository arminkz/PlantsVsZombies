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

    //PlantType activePlantingBrush = PlantType.None;

    public GameWindow() {
        setSize(1012, 785);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);

        GamePanel gamePanel = new GamePanel(sun);
        gamePanel.setLocation(0, 0);
        getLayeredPane().add(gamePanel, new Integer(0));

        PlantCard sunflower = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_sunflower.png")).getImage());
        sunflower.setLocation(110, 8);
        sunflower.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.Sunflower);
        });
        getLayeredPane().add(sunflower, new Integer(3));

        PlantCard peashooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_peashooter.png")).getImage());
        peashooter.setLocation(175, 8);
        peashooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.Peashooter);
        });
        getLayeredPane().add(peashooter, new Integer(3));

        PlantCard freezePeaShooter = new PlantCard(new ImageIcon(this.getClass().getResource("images/cards/card_freezepeashooter.png")).getImage());
        freezePeaShooter.setLocation(240, 8);
        freezePeaShooter.setAction((ActionEvent e) -> {
            gamePanel.setActivePlantingBrush(PlantType.FreezePeashooter);
        });
        getLayeredPane().add(freezePeaShooter, new Integer(3));

        getLayeredPane().add(sun, new Integer(2));
        setResizable(false);
        setVisible(true);
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

    static GameWindow gameWindow;

    public static void begin() {
        gameWindow.dispose();
        gameWindow = new GameWindow();
    }

    public static void main(String[] args) {
        gameWindow = new GameWindow(true);
    }

}
