package Test3;

import Game.view.GamePanel;
import Pea.model.FreezePea;
import Pea.model.NormalPea;
import Pea.model.Pea;

import javax.swing.*;
import java.awt.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zombie.model.NormalZombie;
import zombie.model.Zombie;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PeaTest {
    private Pea testItem;
    private GamePanel gamePanel;

    @Before
    public void setUp() throws Exception {
        gamePanel = GamePanel.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        testItem = null;
        gamePanel.getLanePeas().get(0).remove(0);
    }

    /**
     * Purpose: test NormalPea.advance() function
     * Input: execute NormalPea.advance() through gamePanel.advance()
     * Expected:
     *      return SUCCESS
     *      (xPositionOrigin + 15 == xPositionAfterAdvance) == True
     */
    @Test
    public void testNormalPeaAdvance() {
        testItem = new NormalPea(0, 1);
        gamePanel.getLanePeas().get(0).add(testItem);

        int xPositionOrigin = gamePanel.getLanePeas().get(0).get(0).getXPosition();

        gamePanel.advance();

        int xPositionAfterAdvance = gamePanel.getLanePeas().get(0).get(0).getXPosition();

        assertTrue(xPositionOrigin + 15 == xPositionAfterAdvance);
    }

    /**
     * Purpose: test NormalPea.advance() function
     * Input: execute FreezePea.advance() through gamePanel.advance()
     * Expected:
     *      return SUCCESS
     *      (xPositionOrigin + 15 == xPositionAfterAdvance) == True
     */
    @Test
    public void testFreezePeaAdvance() {
        testItem = new FreezePea(0, 1);
        gamePanel.getLanePeas().get(0).add(testItem);

        int xPositionOrigin = gamePanel.getLanePeas().get(0).get(0).getXPosition();

        gamePanel.advance();

        int xPositionAfterAdvance = gamePanel.getLanePeas().get(0).get(0).getXPosition();

        System.out.println(gamePanel.getLanePeas().get(0).get(0).getXPosition());

        assertTrue(xPositionOrigin + 15 == xPositionAfterAdvance);
    }

    /**
     * Purpose: Pea kills Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 299)
     * Expected:
     *      gamePanel.getLaneZombies().get(0).isEmpty() == True
     */
    @Test
    public void testPeaAttackZombie299() {
        testItem = new NormalPea(0, 1);
        gamePanel.getLanePeas().get(0).add(testItem);

        Zombie zombie = new NormalZombie(0);
        zombie.setHealth(299);

        gamePanel.getLaneZombies().get(0).add(zombie);

        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertTrue(gamePanel.getLaneZombies().get(0).isEmpty());
    }

    /**
     * Purpose: Pea kills Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 300)
     * Expected:
     *      gamePanel.getLaneZombies().get(0).isEmpty() == True
     */
    @Test
    public void testPeaAttackZombie300() {
        testItem = new NormalPea(0, 1);
        gamePanel.getLanePeas().get(0).add(testItem);

        Zombie zombie = new NormalZombie(0);
        zombie.setHealth(300);

        gamePanel.getLaneZombies().get(0).add(zombie);
        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertTrue(gamePanel.getLaneZombies().get(0).isEmpty());
    }

    /**
     * Purpose: Pea can't kill Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 301)
     * Expected:
     *      gamePanel.getLaneZombies().get(0).isEmpty() == False
     */
    @Test
    public void testPeaAttackZombie301() {
        testItem = new NormalPea(0, 1);
        gamePanel.getLanePeas().get(0).add(testItem);

        Zombie zombie = new NormalZombie(0);
        zombie.setHealth(301);

        gamePanel.getLaneZombies().get(0).add(zombie);
        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertFalse(gamePanel.getLaneZombies().get(0).isEmpty());
    }
}
