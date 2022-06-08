package Model.Plant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;

import Model.Lane.Lane;
import View.Game.GamePanel;

public class PlantTest {
	private static GamePanel gamePanel;
	private static Lane lane;
	private static Plant plant;
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		gamePanel = GamePanel.getInstance();
		lane = Lane.getInstance();
	}
	
	@AfterEach
	public static void tearDown() {
		gamePanel = null;
		lane = null;
		plant = null;
	}
	
	/**
	 * Purpose: test NormalPeashooter creator
	 * Input: NormalPeashooter(x, y)
	 * 		x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		getX() = x = 40
	 * 		getY() = y = 10
	 */
	@Test
	public void testCreateNormalPeashooter() {
		int x = 40;
		int y = 10;
		plant = new NormalPeashooter(x, y);
		assertEquals(plant.getX(), x);
		assertEquals(plant.getY(), y);
	}
	
	/**
	 * Purpose: test FreezePeashooter creator
	 * Input: FreezePeashooter(x, y)
	 * 		x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		getX() = x = 40
	 * 		getY() = y = 10
	 */
	@Test
	public void testCreateFreezePeashooter() {
		int x = 40;
		int y = 10;
		plant = new FreezePeashooter(x, y);
		assertEquals(plant.getX(), x);
		assertEquals(plant.getY(), y);
	}
	
	/**
	 * Purpose: test Sunflower creator
	 * Input: Sunflower(x, y)
	 * 		x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		getX() = x = 40
	 * 		getY() = y = 10
	 */
	@Test
	public void testCreateSunflower() {
		int x = 40;
		int y = 10;
		plant = new Sunflower(x, y);
		assertEquals(plant.getX(), x);
		assertEquals(plant.getY(), y);
	}
	
	
}
