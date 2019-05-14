
// Author: Nick Patchen last modified 5/13/2019

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.JLabel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
//import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class nickTestPlantVsZombie {

	@Rule
	public Timeout globalTimeout = new Timeout(20, SECONDS);

	/*
	 * This tests the add method - ascending and the normal iterator
	 */
	@Test
	public void testPeaAdvanceOne() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 0;
		int peaXPos = 1;
		// boolean[] zombInLane = {false,false,true,true,false};
		// int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = new Zombie(gamePanel, peaLane);
		zomb.setPosX(peaXPos);
		zomb.setHealth(301);

		// gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		// assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return
		// false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		// assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should
		// return true", gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1 ", 1, zomb.getHealth());
	}

	@Test
	public void testPeaAdvanceTwo() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int lane = 1;
		int xPos = 1;
		Pea pea = new Pea(gamePanel, xPos, lane);

		pea.advance();

		assertEquals("pea.getPosX() should return \"16 \" ", 16, pea.getPosX());
	}

	/**
	 * This test has 3 zombies, but none are in the lane that the pea is, so only
	 * change should be to move the pea's xPos
	 */
	@Test
	public void testPeaAdvanceThree() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		boolean[] zombInLane = { false, true, true, false, true };
		int[] zombXLoc = { -1, 50, peaXPos, -1, 60 };

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
		ArrayList<Pea> originalPeaList = gamePanel.getLanePeas().get(peaLane);
		ArrayList<ArrayList<Zombie>> originalZombieList = gamePanel.getLaneZombies();

		pea.advance();

		assertEquals("pea.getPosX() should return \"16 \" ", 16, pea.getPosX());
		assertTrue("gamePanel.getLanePeas().get(peaLane).contains should return true ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertEquals("gamePanel.getLaneZombies() should return the same zombie list ", originalZombieList,
				gamePanel.getLaneZombies());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 1
	 */
	@Test
	public void testPeaAdvanceFour() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 2
	 */
	@Test
	public void testPeaAdvanceFive() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 2;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, false, true, true };
		int[] zombXLoc = { -1, -1, -1, 20, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 4
	 */
	@Test
	public void testPeaAdvanceSix() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 3;
		int peaXPos = 1;
		boolean[] zombInLane = { false, true, false, false, true };
		int[] zombXLoc = { -1, 20, -1, -1, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 4
	 */
	@Test
	public void testPeaAdvanceSeven() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 4;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, true, true, false };
		int[] zombXLoc = { -1, -1, 20, 60, -1 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 0
	 */
	@Test
	public void testPeaAdvanceEight() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 0;
		int peaXPos = 1;
		// boolean[] zombInLane = {false,false,true,true,false};
		// int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		// gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health and should remove the pea. Lane 1
	 */
	@Test
	public void testPeaAdvanceNine() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health so low that it is removed and should remove the
	 * pea. Lane 1
	 */
	@Test
	public void testPeaAdvanceTen() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);
		zomb.setHealth(200);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertFalse("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return false",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return -100 ", -100, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should
	 * lower the zombie's health so low that it is removed and should remove the
	 * pea. Lane 1
	 */
	@Test
	public void testPeaAdvanceEleven() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(peaLane, peaXPos, gamePanel);
		zomb.setHealth(300);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);

		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);

		pea.advance();

		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",
				gamePanel.getLanePeas().get(peaLane).contains(pea));
		assertEquals("zomb.getHealth() should return 0 ", 0, zomb.getHealth());
		assertFalse("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return false",
				gamePanel.getLaneZombies().get(peaLane).contains(zomb));

	}

	/*
	 * This tests the FreezePea Advance method - ascending and the normal iterator
	 */
	@Test
	public void testFreezePeaAdvanceOne() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 0;
		int freezePeaXPos = 1;
		// boolean[] zombInLane = {false,false,true,true,false};
		// int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = new Zombie(gamePanel, freezePeaLane);
		zomb.setPosX(freezePeaXPos);
		zomb.setHealth(301);

		// gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		// assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(FreezePea)
		// should return false
		// ",gamePanel.getLanePeas().get(freezePeaLane).contains(FreezePea) );
		// assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)
		// should return true",
		// gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1 ", 1, zomb.getHealth());
	}

	/**
	 * This test makes sure that the freezePea advances forward instead of backward
	 */
	@Test
	public void testFreezePeaAdvanceTwo() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int lane = 1;
		int xPos = 1;
		FreezePea freezePea = new FreezePea(gamePanel, xPos, lane);

		freezePea.advance();

		assertEquals("freezePea.getPosX() should return \"16 \" ", 16, freezePea.getPosX());
	}

	/**
	 * This test has 3 zombies, but none are in the lane that the FreezePea is, so
	 * only change should be to move the FreezePea's xPos
	 */
	@Test
	public void testFreezePeaAdvanceThree() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, true, true, false, true };
		int[] zombXLoc = { -1, 50, freezePeaXPos, -1, 60 };

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
		ArrayList<Pea> originalFreezePeaList = gamePanel.getLanePeas().get(freezePeaLane);
		ArrayList<ArrayList<Zombie>> originalZombieList = gamePanel.getLaneZombies();

		freezePea.advance();

		assertEquals("freezePea.getPosX() should return \"16 \" ", 16, freezePea.getPosX());
		assertTrue("gamePanel.getLanePeas().get(freezePeaLane).contains should return true ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertEquals("gamePanel.getLaneZombies() should return the same zombie list ", originalZombieList,
				gamePanel.getLaneZombies());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceFour() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 2
	 */
	@Test
	public void testFreezePeaAdvanceFive() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 2;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, false, true, true };
		int[] zombXLoc = { -1, -1, -1, 20, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 4
	 */
	@Test
	public void testFreezePeaAdvanceSix() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 3;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, true, false, false, true };
		int[] zombXLoc = { -1, 20, -1, -1, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 4
	 */
	@Test
	public void testFreezePeaAdvanceSeven() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 4;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, true, true, false };
		int[] zombXLoc = { -1, -1, 20, 60, -1 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 0
	 */
	@Test
	public void testFreezePeaAdvanceEight() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 0;
		int freezePeaXPos = 1;
		// boolean[] zombInLane = {false,false,true,true,false};
		// int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		// gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health and should remove the FreezePea. Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceNine() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return 1000-300 ", 1000 - 300, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health so low that it is removed and should remove
	 * the FreezePea. Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceTen() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);
		zomb.setHealth(200);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertFalse("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return false",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));
		assertEquals("zomb.getHealth() should return -100 ", -100, zomb.getHealth());
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so
	 * should lower the zombie's health so low that it is removed and should remove
	 * the FreezePea. Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceEleven() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos = 1;
		boolean[] zombInLane = { false, false, true, false, true };
		int[] zombXLoc = { -1, -1, 20, -1, 60 };
		Zombie zomb = createZombie(freezePeaLane, freezePeaXPos, gamePanel);
		zomb.setHealth(300);

		gamePanel.setLaneZombies(createZombieList(zombInLane, zombXLoc, gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);

		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);

		freezePea.advance();

		assertFalse("gamePanel.getLaneFreezePeas().get(freezePeaLane).contains(freezePea) should return false ",
				gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea));
		assertEquals("zomb.getHealth() should return 0 ", 0, zomb.getHealth());
		assertFalse("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return false",
				gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb));

	}

	/**
	 * This will test the getPosX method - Pea
	 */
	@Test
	public void testPeaGetPosXOne() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		assertEquals("pea.getPosX() should return 1 ", 1, pea.getPosX());

	}

	/**
	 * This will test the getPosX method - FreezePea
	 */
	@Test
	public void testFreezePeaGetPosXOne() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		FreezePea pea = new FreezePea(gamePanel, peaXPos, peaLane);
		assertEquals("pea.getPosX() should return 1 ", 1, pea.getPosX());

	}

	/**
	 * This will test the setPosX method- Pea
	 */
	@Test
	public void testPeaSetPosXOne() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		pea.setPosX(50);
		assertEquals("pea.getPosX() should return 50 ", 50, pea.getPosX());

	}

	/**
	 * This will test the setPosX method - FreezePea
	 */
	@Test
	public void testFreezePeaSetPosXOne() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		FreezePea pea = new FreezePea(gamePanel, peaXPos, peaLane);
		pea.setPosX(50);
		assertEquals("pea.getPosX() should return 50 ", 50, pea.getPosX());

	}

	/**
	 * This will test the getMyLane method - Pea
	 */
	@Test
	public void testPeaGetMyLane() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		assertEquals("pea.getMyLane() should return 1 ", 1, pea.getMyLane());

	}

	/**
	 * This will test the getMyLane method - FreezePea
	 */
	@Test
	public void testFreezePeaGetMyLane() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		FreezePea pea = new FreezePea(gamePanel, peaXPos, peaLane);
		assertEquals("pea.getMyLane() should return 1 ", 1, pea.getMyLane());

	}

	/**
	 * This will test the setMyLane method - Pea
	 */
	@Test
	public void testPeaSetMyLaneOne() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		pea.setMyLane(3);
		assertEquals("pea.getMyLane() should return 3 ", 3, pea.getMyLane());

	}

	/**
	 * This will test the setMyLane method - FreezePea
	 */
	@Test
	public void testFreezePeaSetMyLane() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos = 1;
		FreezePea pea = new FreezePea(gamePanel, peaXPos, peaLane);
		pea.setMyLane(3);
		assertEquals("pea.getMyLane() should return 3 ", 3, pea.getMyLane());

	}
	/**
	 * This will test the collider class - .removePlant method
	 */
	@Test
	public void testColliderOne() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);
		collide.removePlant();

		assertEquals("collide.getPlant() should return null ", null, collide.getPlant());

	}
	
	/**
	 * This will test the collider class - .setPlant method
	 */
	@Test
	public void testColliderTwo() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);


		assertEquals("collide.getPlant() should return the plant object ", plant, collide.getPlant());

	}
	
	
	/**
	 * This will test the collider class - .isInsideCollider method - true
	 */
	@Test
	public void testColliderThree() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);
		

		assertEquals("collide.isInsideCollider(1) should return true ", true, collide.isInsideCollider(1));

	}
	/**
	 * This will test the collider class - .isInsideCollider method - False
	 */
	@Test
	public void testColliderFour() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);
		

		assertEquals("collide.isInsideCollider(100) should return false ", false, collide.isInsideCollider(100));

	}
	
	/**
	 * This will test the collider class - .isInsideCollider method - true, high value edge
	 */
	@Test
	public void testColliderFive() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);
		

		assertEquals("collide.isInsideCollider(99) should return true", true, collide.isInsideCollider(99));

	}
	
	/**
	 * This will test the collider class - .isInsideCollider method - false, low value edge
	 */
	@Test
	public void testColliderSix() {
		
		Collider collide = new ColliderActual();
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Sunflower plant = new Sunflower(gp,1,1);
	
		collide.setPlant(plant);
		

		assertEquals("collide.isInsideCollider(0) should return false", false, collide.isInsideCollider(0));

	}
	
	
	/**
	 * This will test the GamePanel class - .getSunScore and .setSunScore method
	 */
	@Test
	public void testGamePanelOne() {
		
		GamePanelActual gp =new GamePanelActual(new JLabel("testing"));
		gp.setSunScore(100);
		assertEquals("gp.getSunScore() should return 100", 100, gp.getSunScore());

	}

	/**
	 * This will test the GamePanel class - .getSunScore method
	 */
	@Test
	public void testGamePanelTwo() {
		
		GamePanelActual gp =new GamePanelActual(new JLabel("testing"));
		assertEquals("gp.getSunScore() should return 150", 150, gp.getSunScore());

	}
	
	/**
	 * This will test the GameWindow class - Ensures compiles
	 */
	@Test
	public void testGameWindowOne() {
		
		GameWindow gw = new GameWindow();
		assertNotNull("gw should not be null ", gw);

	}
	
	
	/**
	 * This will test the GameWindow class - Ensures other constructor compiles
	 */
	@Test
	public void testGameWindowTwo() {
		
		GameWindow gw = new GameWindow(true);
		assertNotNull("gw should not be null ", gw);

	}

	/**
	 * This will test the Zombie class - .getSunScore and .setSunScore method
	 */
	@Test
	public void testZombieOne() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zombZero = new Zombie(gp,0);
		Zombie zombOne = new Zombie(gp,1);
		Zombie zombTwo = new Zombie(gp,2);
		Zombie zombThree = new Zombie(gp,3);
		Zombie zombFour = new Zombie(gp,4);
		
		assertNotNull("zombZero should not be null",zombZero);
		assertNotNull("zombOne should not be null",zombOne);
		assertNotNull("zombTwo should not be null",zombTwo);
		assertNotNull("zombThree should not be null",zombThree);
		assertNotNull("zombFour should not be null",zombFour);
//		assertEquals("zomb.getSlowInt() should return \"0\" ", 0, zomb.getSlowInt());

	}
	
	
	/**
	 * This will test the Zombie class - getZombie("ConeHeadZombie", gp, 0-4);
	 */
	@Test
	public void testZombieTwo() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zombZero = Zombie.getZombie("ConeHeadZombie", gp, 0);
		Zombie zombOne = Zombie.getZombie("ConeHeadZombie", gp, 1);
		Zombie zombTwo = Zombie.getZombie("ConeHeadZombie", gp, 2);
		Zombie zombThree = Zombie.getZombie("ConeHeadZombie", gp, 3);
		Zombie zombFour = Zombie.getZombie("ConeHeadZombie", gp, 4);
		

		assertNotNull("zombZero should not be null",zombZero);
		assertNotNull("zombOne should not be null",zombOne);
		assertNotNull("zombTwo should not be null",zombTwo);
		assertNotNull("zombThree should not be null",zombThree);
		assertNotNull("zombFour should not be null",zombFour);
		
		assertTrue("zombZero instanceof ConeHeadZombie is true" , zombZero instanceof ConeHeadZombie);
		assertTrue("zombOne instanceof ConeHeadZombie is true" , zombOne instanceof ConeHeadZombie);
		assertTrue("zombTwo instanceof ConeHeadZombie is true" , zombTwo instanceof ConeHeadZombie);
		assertTrue("zombThree instanceof ConeHeadZombie is true" , zombThree instanceof ConeHeadZombie);
		assertTrue("zombFour instanceof ConeHeadZombie is true" , zombFour instanceof ConeHeadZombie);
	
		assertFalse("zombZero instanceof NormalZombie is false" , zombZero instanceof NormalZombie);
		assertFalse("zombOne instanceof NormalZombie is false" , zombOne instanceof NormalZombie);
		assertFalse("zombTwo instanceof NormalZombie is false" , zombTwo instanceof NormalZombie);
		assertFalse("zombThree instanceof NormalZombie is false" , zombThree instanceof NormalZombie);
		assertFalse("zombFour instanceof NormalZombie is false" , zombFour instanceof NormalZombie);
	}
	
	
	/**
	 * This will test the Zombie class - getZombie("NormalZombie", gp, 0-4);
	 */
	@Test
	public void testZombieThree() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zombZero = Zombie.getZombie("NormalZombie", gp, 0);
		Zombie zombOne = Zombie.getZombie("NormalZombie", gp, 1);
		Zombie zombTwo = Zombie.getZombie("NormalZombie", gp, 2);
		Zombie zombThree = Zombie.getZombie("NormalZombie", gp, 3);
		Zombie zombFour = Zombie.getZombie("NormalZombie", gp, 4);
		

		assertNotNull("zombZero should not be null",zombZero);
		assertNotNull("zombOne should not be null",zombOne);
		assertNotNull("zombTwo should not be null",zombTwo);
		assertNotNull("zombThree should not be null",zombThree);
		assertNotNull("zombFour should not be null",zombFour);
		
		assertTrue("zombZero instanceof NormalZombie is true" , zombZero instanceof NormalZombie);
		assertTrue("zombOne instanceof NormalZombie is true" , zombOne instanceof NormalZombie);
		assertTrue("zombTwo instanceof NormalZombie is true" , zombTwo instanceof NormalZombie);
		assertTrue("zombThree instanceof NormalZombie is true" , zombThree instanceof NormalZombie);
		assertTrue("zombFour instanceof NormalZombie is true" , zombFour instanceof NormalZombie);
	
		assertFalse("zombZero instanceof ConeHeadZombie is false" , zombZero instanceof ConeHeadZombie);
		assertFalse("zombOne instanceof ConeHeadZombie is false" , zombOne instanceof ConeHeadZombie);
		assertFalse("zombTwo instanceof ConeHeadZombie is false" , zombTwo instanceof ConeHeadZombie);
		assertFalse("zombThree instanceof ConeHeadZombie is false" , zombThree instanceof ConeHeadZombie);
		assertFalse("zombFour instanceof ConeHeadZombie is false" , zombFour instanceof ConeHeadZombie);
		
		
	}
	
	
	/**
	 * This will test the Zombie class - Tests the .setHealth() and .getHealth method
	 */
	@Test
	public void testZombieFour() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
		
		zomb.setHealth(50);
		zomb.setHealth(100);
		assertEquals("zomb.getHealth() should return 100 ", 100, zomb.getHealth());
		
	}

	/**
	 * This will test the Zombie class - Tests the .setSpeed and .getSpeed method
	 */
	@Test
	public void testZombieFive() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		zomb.setSpeed(50);
		zomb.setSpeed(100);
		assertEquals("zomb.getSpeed() should return 100 ", 100, zomb.getSpeed());
		
	}
	
	/**
	 * This will test the Zombie class - Tests the .setGp and .getGp method
	 */
	@Test
	public void testZombieSix() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		GamePanelTesting gpNew =new GamePanelTesting(new JLabel("new testing"));
		zomb.setGp(gpNew);
		
		assertEquals("zomb.getGp() should return gpNew ", gpNew, zomb.getGp());
		
	}
	
	/**
	 * This will test the Zombie class - Tests the .setPosX and .getPosX method
	 */
	@Test
	public void testZombieSeven() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		zomb.setPosX(10);
		
		assertEquals("zomb.getPosX() should return 10 ", 10, zomb.getPosX());
		
	}
	
	/**
	 * This will test the Zombie class - Tests the .setMyLane and .getMyLane method
	 */
	@Test
	public void testZombieEight() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zombZero = new Zombie(gp,0);
		Zombie zombOne = new Zombie(gp,1);
		Zombie zombTwo = new Zombie(gp,2);
		Zombie zombThree = new Zombie(gp,3);
		Zombie zombFour = new Zombie(gp,4);
		
		zombZero.setMyLane(1);
		zombOne.setMyLane(2);
		zombTwo.setMyLane(3);
		zombThree.setMyLane(4);
		zombFour.setMyLane(0);
		
		
		assertEquals("zombZero.getMyLane() should return 1 ",1 , zombZero.getMyLane());
		assertEquals("zombOne.getMyLane() should return 2 ", 2, zombOne.getMyLane());
		assertEquals("zombTwo.getMyLane() should return 3 ", 3, zombTwo.getMyLane());
		assertEquals("zombThree.getMyLane() should return 4 ", 4, zombThree.getMyLane());
		assertEquals("zombFour.getMyLane() should return 5 ", 0, zombFour.getMyLane());
		
		
	}
	
	/**
	 * This will test the Zombie class - Tests the .setMoving and .isMoving method - true
	 */
	@Test
	public void testZombieTen() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		zomb.setMoving(true);
		
		assertEquals("zomb.isMoving() should return true ", true, zomb.isMoving());
		
	}
	
	/**
	 * This will test the Zombie class - Tests the .setMoving and .isMoving method - false
	 */
	@Test
	public void testZombieEleven() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		zomb.setMoving(false);
		
		assertEquals("zomb.isMoving() should return false ", false, zomb.isMoving());
	}
	
	/**
	 * This will test the Zombie class - Tests the .setSlowInt and .getSlowInt method
	 */
	@Test
	public void testZombieTwelve() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		zomb.setSlowInt(10);
		
		assertEquals("zomb.getSlowInt() should return 10 ", 10, zomb.getSlowInt());
		
	}
	
	/**
	 * This will test the Zombie class, NormalZombie - Tests the initial values after instantiation
	 */
	@Test
	public void testZombieThirteen() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("NormalZombie", gp, 0);
	
		assertEquals("zomb.getHealth() should return 1000 ", 1000, zomb.getHealth());
		assertEquals("zomb.getSpeed() should return 1 ", 1, zomb.getSpeed());
		assertEquals("zomb.getPosX() should return 1000 ", 1000, zomb.getPosX());
		assertEquals("zomb.getGp() should return gp ", gp, zomb.getGp());
		assertEquals("zomb.getMyLane() should return 0 ", 0, zomb.getMyLane());
		assertEquals("zomb.isMoving() should return true ", true, zomb.isMoving());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie class, ConeHeadZombie - Tests the initial values after instantiation
	 */
	@Test
	public void testZombieFourteen() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, 0);
	
		assertEquals("zomb.getHealth() should return 1000 ", 1800, zomb.getHealth());
		assertEquals("zomb.getSpeed() should return 1 ", 1, zomb.getSpeed());
		assertEquals("zomb.getPosX() should return 1000 ", 1000, zomb.getPosX());
		assertEquals("zomb.getGp() should return gp ", gp, zomb.getGp());
		assertEquals("zomb.getMyLane() should return 0 ", 0, zomb.getMyLane());
		assertEquals("zomb.isMoving() should return true ", true, zomb.isMoving());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceOne() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);
		int origHealth = ps.getHealth();
		collide = testSetColliderArr(zombLane, collide, ps);
		gp.setColliders(collide);
		
		zomb.advance();
	
		assertEquals("ps.getHealth() should return ps.getHealth()-10 ", origHealth-10, ps.getHealth());
		assertEquals("zomb.getPosX() should return 1000 ", 1000, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}



	/**
	 * This will test the Zombie.advance() method - testing the false part of if statement
	 */
	@Test
	public void testZombieAdvanceTwo() {
		
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, 0);
		
		int xPos = 50;
		zomb.setPosX(xPos);
		zomb.setMoving(false);
		zomb.advance();

		assertEquals("zomb.getPosX() should return 50 ", xPos, zomb.getPosX());
		assertEquals("zomb.getHealth() should return 1000 ", 1800, zomb.getHealth());
		assertEquals("zomb.isMoving() should return true ", false, zomb.isMoving());
		
	}
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceThree() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);
		int origHealth = ps.getHealth();
		collide = testSetColliderArr(3, collide, ps);
		gp.setColliders(collide);
		
		zomb.advance();
	
		assertEquals("ps.getHealth() should return origHealth ", origHealth, ps.getHealth());
		assertEquals("zomb.getPosX() should return 999 ", 999, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceFour() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);
		int origHealth = ps.getHealth();
		collide = testSetColliderArr(3, collide, ps);
		gp.setColliders(collide);
		zomb.slow();
		zomb.advance();
	
		assertEquals("ps.getHealth() should return origHealth ", origHealth, ps.getHealth());
		assertEquals("zomb.getPosX() should return 999 ", 999, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 999 ", 999, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceFive() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);
		int origHealth = ps.getHealth();
		collide = testSetColliderArr(3, collide, ps);
		gp.setColliders(collide);
		zomb.setSlowInt(999);
		zomb.advance();
	
		assertEquals("ps.getHealth() should return origHealth ", origHealth, ps.getHealth());
		assertEquals("zomb.getPosX() should return 1000 ", 1000, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 998 ", 998, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceSix() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);
		ps.setHealth(9);
		int origHealth = ps.getHealth();
		collide = testSetColliderArr(zombLane, collide, ps);
		gp.setColliders(collide);
		zomb.advance();
	
		assertEquals("ps.getHealth() should return -1 ", -1, ps.getHealth());
		assertEquals("zomb.getPosX() should return 1000 ", 1000, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}
	
	/**
	 * This will test the Zombie.advance() 
	 */
	@Test
	public void testZombieAdvanceSeven() {
		
       
		GamePanelTesting gp =new GamePanelTesting(new JLabel("testing"));
		int zombLane = 0;
		Zombie zomb = Zombie.getZombie("ConeHeadZombie", gp, zombLane);
		Collider [] collide = getColliderArr(); 
		zomb.setPosX(0);
		Peashooter ps = new Peashooter(gp,zomb.getPosX(),0);

		int origHealth = ps.getHealth();
		collide = testSetColliderArr(zombLane, collide, ps);
		gp.setColliders(collide);
		zomb.advance();
	
		assertEquals("ps.getHealth() should return origHealth-10 ", origHealth-10, ps.getHealth());
		assertEquals("zomb.getPosX() should return 0 ", 0, zomb.getPosX());
		assertEquals("zomb.getSlowInt() should return 0 ", 0, zomb.getSlowInt());
	}

	/**
	 * This is a helper method that creates zombieLists
	 * @param lane
	 * @param xLoc
	 * @param gp
	 * @return
	 */
	private ArrayList<ArrayList<Zombie>> createZombieList(boolean[] lane, int[] xLoc, GamePanel gp) {
		ArrayList<ArrayList<Zombie>> laneZombies;
		laneZombies = new ArrayList<>();
		laneZombies.add(new ArrayList<>()); // line 1
		laneZombies.add(new ArrayList<>()); // line 2
		laneZombies.add(new ArrayList<>()); // line 3
		laneZombies.add(new ArrayList<>()); // line 4
		laneZombies.add(new ArrayList<>()); // line 5
		for (int i = 0; i < lane.length; i++) {

			if (lane[i]) {
				laneZombies.get(i).add(createZombie(i, xLoc[i], gp));

			}
		}

		return laneZombies;
	}

	/**
	 * This is a helper method that creates zombies in the specific lane and loc
	 * @param lane
	 * @param xLoc
	 * @param gp
	 * @return
	 */
	private Zombie createZombie(int lane, int xLoc, GamePanel gp) {

		Zombie zomb = Zombie.getZombie("NormalZombie", gp, lane);
				
//				new Zombie(gp, lane);
		zomb.setPosX(xLoc);
		return zomb;

	}
	
	
	/**
	 * This helper method creates the collider array
	 */
	private Collider[] getColliderArr() {
		Collider [] colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            ColliderTesting a = new ColliderTesting();
            colliders[i] = a;
            }
        return colliders;
	}
	
	/**
	 * This helper method sets up the plants into each spot of the collider arr 
	 * @param zombLane
	 * @param collide
	 * @param ps
	 */
	private Collider[] testSetColliderArr(int zombLane, Collider[] collide, Peashooter ps) {
		for (int i = zombLane * 9; i < (zombLane + 1) * 9; i++) {
            collide[i].setPlant(ps);
        }
		return collide;
	}

}
