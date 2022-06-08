package Model.Zombie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Model.Plant.Plant;
import Model.Zombie.*;
import View.Game.*;

class ZombieTest {
	private Zombie testZombie;
	private GamePanel gamePanel;
	
	@Before
	public void setUp() throws Exception {
		gamePanel = GamePanel.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		testZombie = null;
		gamePanel = null;
	}
	
	/**
     * Purpose: test Zombie.getZombie function
     * Input: getZombie("Null", gamePanel, 0),
     * 		  getZombie("NormalZombie", gamePanel, 3),
     * 		  getZombie("ConeHeadZombie", gamePanel, 3)
     * Expected:
     *      return SUCCESS
     *      getZombie("Null", gamePanel, 0) == Null
     *      getZombie("NormalZombie", gamePanel, 3) == NormalZombie
     *      getZombie("ConeHeadZombie", gamePanel, 3) == ConeHeadZombie
     */
	
	@Test
	void testGetZombie() {
		testZombie = Zombie.getZombie("Null", gamePanel, 0);
		assertNull(testZombie);
		
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 3);
		assertNotNull(testZombie);
		assertEquals(NormalZombie.class.getName(), testZombie.getClass().getName());
		assertEquals(3, testZombie.getLane());

		testZombie = Zombie.getZombie("ConeHeadZombie", gamePanel, 3);
		assertNotNull(testZombie);
		assertEquals(ConeHeadZombie.class.getName(), testZombie.getClass().getName());
		assertEquals(3, testZombie.getLane());
	}
	
	/**
	 * Purpose: test Zombie.getHealth function
	 * Input: getHealth();
	 * Expected:
	 * 		return SUCCESS
	 * 		getHealth() == 1000
	 */

	@Test
	void testGetHealth() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		assertEquals(1000, testZombie.getHealth());
	}
}
