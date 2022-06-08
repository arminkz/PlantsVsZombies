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
	
	/**
	 * Purpose: test Zombie.setHealth function
	 * Input: setHealth(0);, setHealth(100);, setHealth(500);
	 * Expected:
	 * 		return SUCCESS
	 * 		setHealth(0) == 0
	 * 		setHealth(100) == 100
	 * 		setHealth(500) == 500
	 */

	@Test
	void testSetHealth() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		
		testZombie.setHealth(0);
		assertEquals(0, testZombie.getHealth());
		
		testZombie.setHealth(100);
		assertEquals(100, testZombie.getHealth());
		
		testZombie.setHealth(500);
		assertEquals(500, testZombie.getHealth());
	}
	
	/**
	 * Purpose: test Zombie.getSpeed function
	 * Input: getSpeed();
	 * Expected:
	 * 		return SUCCESS
	 * 		getSpeed() == 1
	 */

	@Test
	void testGetSpeed() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		assertEquals(1, testZombie.getSpeed());
	}
	
	/**
	 * Purpose: test Zombie.setSpeed function
	 * Input: setSpeed(10);
	 * Expected:
	 * 		return SUCCESS
	 * 		setSpeed(10) == 10
	 */

	@Test
	void testSetSpeed() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		
		testZombie.setSpeed(10);
		assertEquals(10, testZombie.getSpeed());
	}
	
	/**
	 * Purpose: test Zombie.getPosX function
	 * Input: getPosX();
	 * Expected:
	 * 		return SUCCESS
	 * 		getPosX() == 1000
	 */

	@Test
	void testGetPosX() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		assertEquals(1000, testZombie.getPosX());
	}
	
	/**
	 * Purpose: test Zombie.setPosX function
	 * Input: setPosX(500);, setPosX(0);
	 * Expected:
	 * 		return SUCCESS
	 * 		setPosX(500) == 500
	 * 		setPosX(0) == 0
	 */

	@Test
	void testSetPosX() {
		testZombie = Zombie.getZombie("NormalZombie", gamePanel, 0);
		
		testZombie.setPosX(500);
		assertEquals(500, testZombie.getPosX());
		
		testZombie.setPosX(0);
		assertEquals(0, testZombie.getPosX());
	}
}
