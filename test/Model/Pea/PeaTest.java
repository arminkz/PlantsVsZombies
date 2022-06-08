package Model.Pea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Lane.Lane;
import Model.Zombie.NormalZombie;
import Model.Zombie.Zombie;
import View.Game.GamePanel;

class PeaTest {
    private Pea testPea;
    private GamePanel gamePanel;

	@BeforeEach
	void setUp() throws Exception {
        gamePanel = GamePanel.getInstance();
	}
	
	@AfterEach
    public void tearDown() throws Exception {
		testPea = null;
		Lane.getInstance().getLanePeas().get(0).remove(0);
        gamePanel = null;
    }

	/**
     * Purpose: test NormalPea.advance() function
     * Input: NormalPea.advance() gamePanel.advance()
     * Expected:
     *      return SUCCESS
     *      (xPositionOrigin + 15 == xPositionAfterAdvance) == True
     */
	
    @Test
    public void testNormalPeaAdvance() {
    	testPea = new NormalPea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        int xPositionOrigin = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        gamePanel.advance();

        int xPositionAfterAdvance = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        assertTrue(xPositionOrigin + 15 == xPositionAfterAdvance);
    }
    
    
    /**
     * Purpose: test FreezePea.advance() function
     * Input: FreezePea.advance() gamePanel.advance()
     * Expected:
     *      return SUCCESS
     *      (xPositionOrigin + 15 == xPositionAfterAdvance) == True
     */
	
    @Test
    public void testFreezePeaAdvance() {
    	testPea = new FreezePea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        int xPositionOrigin = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        gamePanel.advance();

        int xPositionAfterAdvance = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        assertTrue(xPositionOrigin + 15 == xPositionAfterAdvance);
    }
    
    
    /**
     * Purpose: Pea kills Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 299)
     * Expected:
     * 		return SUCCESS 
     *      Lane.getInstance().getLaneZombies().get(0).isEmpty() == True
     */
    @Test
    public void testPeaAttackZombie299() {
    	testPea = new NormalPea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        Zombie zombie = new NormalZombie(gamePanel, 0);
        zombie.setHealth(299);

        Lane.getInstance().getLaneZombies().get(0).add(zombie);

        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertTrue(Lane.getInstance().getLaneZombies().get(0).isEmpty());
    }
    
    /**
     * Purpose: Pea kills Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 300)
     * Expected:
     * 		return SUCCESS 
     *      Lane.getInstance().getLaneZombies().get(0).isEmpty() == True
     */
    @Test
    public void testPeaAttackZombie300() {
    	testPea = new NormalPea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        Zombie zombie = new NormalZombie(gamePanel, 0);
        zombie.setHealth(300);

        Lane.getInstance().getLaneZombies().get(0).add(zombie);

        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertTrue(Lane.getInstance().getLaneZombies().get(0).isEmpty());
    }
    
    
    /**
     * Purpose: Pea can't kill Zombie
     * Input: NormalPea(power == 300) and NormalZombie(health == 301)
     * Expected:
     * 		return SUCCESS 
     *      Lane.getInstance().getLaneZombies().get(0).isEmpty() == False
     */
    @Test
    public void testPeaAttackZombie301() {
    	testPea = new NormalPea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        Zombie zombie = new NormalZombie(gamePanel, 0);
        zombie.setHealth(301);

        Lane.getInstance().getLaneZombies().get(0).add(zombie);

        for(int i = 0; i < 100; i++)
            gamePanel.advance();

        assertFalse(Lane.getInstance().getLaneZombies().get(0).isEmpty());
    }

}
