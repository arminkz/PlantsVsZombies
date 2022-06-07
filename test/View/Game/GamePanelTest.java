package View.Game;

import Model.Lane.Lane;
import Model.Zombie.Zombie;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GamePanelTest {
    private static GamePanel gamePanel;

    @BeforeAll
    static void setup() {
        gamePanel = GamePanel.getInstance();

        gamePanel.getAdvancerTimer().stop();
        gamePanel.getRedrawTimer().stop();
        gamePanel.getZombieProducer().stop();
        gamePanel.getSunProducer().stop();
    }

    /**
     * Purpose: Check isRunning Timers in gamePanel
     * Input: isRunning()
     * Expected:
     * return SUCCESS
     * isRunning() = true
     */
    @Test
    @Order(1)
    void setTimer() {
        gamePanel.setTimer();
        assertEquals(true, gamePanel.getAdvancerTimer().isRunning());
        assertEquals(true, gamePanel.getRedrawTimer().isRunning());
        assertEquals(true, gamePanel.getZombieProducer().isRunning());
        assertEquals(true, gamePanel.getSunProducer().isRunning());
        gamePanel.getAdvancerTimer().stop();
        gamePanel.getRedrawTimer().stop();
        gamePanel.getZombieProducer().stop();
        gamePanel.getSunProducer().stop();
    }

    /**
     * Purpose: Check set SunScore in GamePanel
     * Input: getSunScore()
     * Expected:
     * return SUCCESS
     * sunScore = 150
     */
    @Test
    @Order(2)
    void getSunScore() {
        assertEquals(150, gamePanel.getSunScore());
    }

    /**
     * Purpose: Check set SunScore in GamePanel
     * Input: setSunScore sunScore 150 -> 200
     * Expected:
     * return SUCCESS
     * sunScore = 200
     */
    @Test
    @Order(3)
    void setSunScore() {
        gamePanel.setSunScore(200);
        assertEquals(200, gamePanel.getSunScore());
    }

    /**
     * Purpose: Check colliders length after make colliders
     * Input: getColliders()
     * Expected:
     * return SUCCESS
     * length = 45
     */
    @Test
    @Order(4)
    void makeColliders() {
        assertEquals(45, gamePanel.getColliders().length);
    }

    /**
     * Purpose: Check ActiveSun after produceSun
     * Input: handleProduceSun
     * Expected:
     * return SUCCESS
     * activeSuns.length = 1
     */
    @Test
    @Order(5)
    void handleProduceSun() {
        JButton button = new JButton();

        button.addActionListener(gamePanel.handleProduceSun());
        button.doClick();

        assertEquals(1, gamePanel.getActiveSuns().size());
    }

    /**
     * Purpose: Check Zombies after produce Zombie
     * Input: handleProduceZombie()
     * Expected:
     * return SUCCESS
     * zombie count = 1
     */
    @Test
    @Order(6)
    void handleProduceZombie() {
        JButton button = new JButton();

        button.addActionListener(gamePanel.handleProduceZombie());
        button.doClick();

        int count = 0;
        Zombie zombie = null;

        for(int i=0; i<5; i++) {
            List<Zombie> zombies = Lane.getInstance().getLaneZombies().get(i);
            if(zombies.size() <= 0) continue;
            count += zombies.size();
            zombie = zombies.get(0);
        }

        assertEquals(1, count);

        zombie.setHealth(-1);
    }

    /**
     * Purpose: Check Move Zombie after advance zombie
     * Input: handleProduceZombie()
     * Expected:
     * return SUCCESS
     * zombie x = 1000 -> x = 999
     */
    @Test
    @Order(7)
    void handleAdvancer() {
        JButton makeZombie = new JButton();
        JButton advance = new JButton();

        makeZombie.addActionListener(gamePanel.handleProduceZombie());
        makeZombie.doClick();
        advance.addActionListener(gamePanel.handleAdvancer());
        advance.doClick();

        Zombie zombie = null;

        for(int i=0; i<5; i++) {
            List<Zombie> zombies = Lane.getInstance().getLaneZombies().get(i);
            if(zombies.size() <= 0) continue;
            zombie = zombies.get(0);
        }

        assertEquals(999, zombie.getPosX());
    }

    /**
     * Purpose: Check add ActivePlantingBrush after setActivePlantingBrush
     * Input: setActivePlantingBrush GameFrame.PlantType.NormalPeashooter
     * Expected:
     * return SUCCESS
     * activePlantingBrush None -> NormalPeashooter
     */
    @Test
    @Order(8)
    void setActivePlantingBrush() {
        gamePanel.setActivePlantingBrush(GameFrame.PlantType.NormalPeashooter);
        assertEquals(GameFrame.PlantType.NormalPeashooter, gamePanel.getActivePlantingBrush());
    }
}