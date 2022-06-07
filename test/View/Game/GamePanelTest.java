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
}