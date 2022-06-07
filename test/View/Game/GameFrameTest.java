package View.Game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameFrameTest {
    static GameFrame gameFrame;

    @BeforeAll
    static void setup() {
        GameFrame.main(new String[]{});
        GameFrame.begin();
        gameFrame = GameFrame.gameFrame;
    }

    /**
     * Purpose: Check place GamePanel in Window
     * Input: getLocation() getComponentsInLayer(0)
     * Expected:
     * return SUCCESS
     * x = 0 y = 0
     * length = 1
     */
    @Test
    void placeGamePanel() {
        assertEquals(0, GamePanel.getInstance().getLocation().x);
        assertEquals(0, GamePanel.getInstance().getLocation().y);
        assertEquals(1, gameFrame.getLayeredPane().getComponentsInLayer(0).length);
    }


}