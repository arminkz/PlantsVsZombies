package Model.Plant;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;

import Model.Lane.Lane;
import View.Game.GamePanel;

public class PlantTest {
	private static GamePanel gamePanel;
	private static Lane lane;
	private static Plant plant;
	private static PlantFactory plantFactory;
	private static Graphics graphics;
	
	@BeforeClass
	public static void setUp() throws Exception {
		gamePanel = GamePanel.getInstance();
		lane = Lane.getInstance();
	}
	
	@After
	public void tearDown() {
		gamePanel = null;
		lane = null;
		plant = null;
		plantFactory = null;
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
	
	/**
	 * Purpose: test PlantFactory getInstance
	 * Input: PlantFactory getInstance()
	 * Expected:
	 * 		return SUCCESS
	 * 		plantFacotry != null
	 */
	@Test
	public void testPlantFactoryGetInstance() {
		plantFactory = PlantFactory.getInstance();
		assertNotNull(plantFactory);
	}
	
	/**
	 * Purpose: test PlantFactory getInstance when it already exists.
	 * Input: PlantFactory getInstance()
	 * Expected:
	 * 		return SUCCESS
	 * 		plantFacotry == plantFactory2
	 */
	@Test
	public void testPlantFactoryGetInstancAlreadyExists() {
		plantFactory = PlantFactory.getInstance();
		PlantFactory plantFactory2 = PlantFactory.getInstance();
		assertEquals(plantFactory, plantFactory2);
	}

	/**
	 * Purpose: test PlantFactory getPlant, plant type is Sunflower
	 * Input: PlantFactory getPlant(type, x, y)
	 * 		type = "Sunflower", x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		testPlant.getCost() = Sunflower.SUNFLOWER_COST
	 * 		getX() = 40
	 * 		getY() = 10
	 */
	@Test
	public void testPlantFactoryMakeSunflower() {
		plantFactory = PlantFactory.getInstance();
		int x = 40;
		int y = 10;
		Plant testPlant = plantFactory.getPlant("Sunflower", x, y);
		assertEquals(testPlant.getCost(), Sunflower.SUNFLOWER_COST);
		assertEquals(testPlant.getX(), 40);
		assertEquals(testPlant.getY(), 10);
	}
	
	/**
	 * Purpose: test PlantFactory getPlant, plant type is NormalPeashooter
	 * Input: PlantFactory getPlant(type, x, y)
	 * 		type = "NormalPeashooter", x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		testPlant.getCost() = NormalPeashooter.NORMAL_PEASHOOTER_COST
	 * 		getX() = 40
	 * 		getY() = 10
	 */
	@Test
	public void testPlantFactoryMakeNormalPeashooter() {
		plantFactory = PlantFactory.getInstance();
		int x = 40;
		int y = 10;
		Plant testPlant = plantFactory.getPlant("NormalPeashooter", x, y);
		assertEquals(testPlant.getCost(), NormalPeashooter.NORMAL_PEASHOOTER_COST);
		assertEquals(testPlant.getX(), 40);
		assertEquals(testPlant.getY(), 10);
	}
	
	/**
	 * Purpose: test PlantFactory getPlant, plant type is FreezePeashooter
	 * Input: PlantFactory getPlant(type, x, y)
	 * 		type = "FreezePeashooter", x = 40, y = 10
	 * Expected:
	 * 		return SUCCESS
	 * 		testPlant.getCost() = FreezePeashooter.FREEZE_PEASHOOTER_COST
	 * 		getX() = 40
	 * 		getY() = 10
	 */
	@Test
	public void testPlantFactoryMakeFreezePeashooter() {
		plantFactory = PlantFactory.getInstance();
		int x = 40;
		int y = 10;
		Plant testPlant = plantFactory.getPlant("FreezePeashooter", x, y);
		assertEquals(testPlant.getCost(), FreezePeashooter.FREEZE_PEASHOOTER_COST);
		assertEquals(testPlant.getX(), 40);
		assertEquals(testPlant.getY(), 10);
	}
	
	/**
	 * Purpose: test plant value setting
	 * Input: NormalPeashooter(40, 10) setX(newX), setY(newY), setHealth(newHealth)
	 * 		newX = 400, newY = 100, newHealth = 123
	 * Expected:
	 * 		return SUCCESS
	 * 		getX() = 400
	 * 		getY() = 100
	 * 		getHealth() = 123
	 */
	@Test
	public void testPlantSetValue() {
		plant = new NormalPeashooter(40, 10);
		int newX = 400;
		int newY = 100;
		int newHealth = 123;
		
		plant.setX(newX);
		plant.setY(newY);
		plant.setHealth(newHealth);
		
		assertEquals(plant.getX(), 400);
		assertEquals(plant.getY(), 100);
		assertEquals(plant.getHealth(), 123);
	}
	
	/**
	 * Purpose: test drawing Sunflower, but graphics is null.
	 * 		--> Test before plant action runs.
	 * Input: Sunflower(10, 20)
	 * 		graphics = null
	 * Expected:
	 * 		NullPointerException
	 */
	@Test(expected = NullPointerException.class)
	public void testDrawSunflower() {
		plant = new Sunflower(10, 20);
		plant.draw(0, graphics);
	}
}
