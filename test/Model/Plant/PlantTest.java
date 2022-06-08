package Model.Plant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;

import Model.Lane.Lane;
import View.Game.GamePanel;

public class PlantTest {
	private static GamePanel gamePanel;
	private static Lane lane;
	private static Plant plant;
	
	
	@BeforeClass
	public void setUp() throws Exception {
		gamePanel = GamePanel.getInstance();
		lane = Lane.getInstance();
	}
	
	@AfterClass
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
	
}
