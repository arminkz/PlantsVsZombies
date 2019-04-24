
// Author: Nick Patchen

import static org.junit.Assert.fail;

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
		int peaXPos= 1;
//		boolean[] zombInLane =  {false,false,true,true,false};
//		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = new Zombie(gamePanel,peaLane);
		zomb.setPosX(peaXPos);
		zomb.setHealth(301);
		
//		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


//		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
//		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1 ",  1,zomb.getHealth()) ;
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
	 * This test has 3 zombies, but none are in the lane that the pea is, so only change should be
	 * to move the pea's xPos
	 */
	@Test
	public void testPeaAdvanceThree() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,true,true,false,true};
		int[] zombXLoc = {-1,50,peaXPos,-1,60};
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
		ArrayList<Pea> originalPeaList = gamePanel.getLanePeas().get(peaLane);
		ArrayList<ArrayList<Zombie>> originalZombieList = gamePanel.getLaneZombies();
		
		pea.advance();

		assertEquals("pea.getPosX() should return \"16 \" ", 16, pea.getPosX());
		assertTrue("gamePanel.getLanePeas().get(peaLane).contains should return true ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertEquals("gamePanel.getLaneZombies() should return the same zombie list ", originalZombieList, gamePanel.getLaneZombies() );
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 1
	 */
	@Test
	public void testPeaAdvanceFour() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 2
	 */
	@Test
	public void testPeaAdvanceFive() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 2;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,false,true, true};
		int[] zombXLoc = {-1,-1,-1,20,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 4
	 */
	@Test
	public void testPeaAdvanceSix() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 3;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,true,false,false,true};
		int[] zombXLoc = {-1,20,-1,-1,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 4
	 */
	@Test
	public void testPeaAdvanceSeven() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 4;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,true,false};
		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 0
	 */
	@Test
	public void testPeaAdvanceEight() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 0;
		int peaXPos= 1;
//		boolean[] zombInLane =  {false,false,true,true,false};
//		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
//		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 * and should remove the pea.  Lane 1
	 */
	@Test
	public void testPeaAdvanceNine() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertTrue("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 *  so low that it is removed and should remove the pea.  Lane 1
	 */
	@Test
	public void testPeaAdvanceTen() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		zomb.setHealth(200);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertFalse("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return false",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return -100 ",  -100,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the pea is, so should lower the zombie's health
	 *  so low that it is removed and should remove the pea.  Lane 1
	 */
	@Test
	public void testPeaAdvanceEleven() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 1;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(peaLane,peaXPos,gamePanel);
		zomb.setHealth(300);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(peaLane).add(zomb);
		
		Pea pea = new Pea(gamePanel, peaXPos, peaLane);
		gamePanel.getLanePeas().get(peaLane).add(pea);
			
		pea.advance();


		assertFalse("gamePanel.getLanePeas().get(peaLane).contains(pea) should return false ",gamePanel.getLanePeas().get(peaLane).contains(pea) );
		assertEquals("zomb.getHealth() should return 0 ",  0,zomb.getHealth()) ;
		assertFalse("gamePanel.getLaneZombies().get(peaLane).contains(zomb) should return false",  gamePanel.getLaneZombies().get(peaLane).contains(zomb)) ;
		
	}
	
	/*
	 * This tests the FreezePea Advance method - ascending and the normal iterator
	 */
	@Test
	public void testFreezePeaAdvanceOne() {
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 0;
		int freezePeaXPos= 1;
//		boolean[] zombInLane =  {false,false,true,true,false};
//		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = new Zombie(gamePanel,freezePeaLane);
		zomb.setPosX(freezePeaXPos);
		zomb.setHealth(301);
		
//		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


//		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(FreezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(FreezePea) );
//		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1 ",  1,zomb.getHealth()) ;
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
	 * This test has 3 zombies, but none are in the lane that the FreezePea is, so only change should be
	 * to move the FreezePea's xPos
	 */
	@Test
	public void testFreezePeaAdvanceThree() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,true,true,false,true};
		int[] zombXLoc = {-1,50,freezePeaXPos,-1,60};
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
		ArrayList<Pea> originalFreezePeaList = gamePanel.getLanePeas().get(freezePeaLane);
		ArrayList<ArrayList<Zombie>> originalZombieList = gamePanel.getLaneZombies();
		
		freezePea.advance();

		assertEquals("freezePea.getPosX() should return \"16 \" ", 16, freezePea.getPosX());
		assertTrue("gamePanel.getLanePeas().get(freezePeaLane).contains should return true ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertEquals("gamePanel.getLaneZombies() should return the same zombie list ", originalZombieList, gamePanel.getLaneZombies() );
	}

	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceFour() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 2
	 */
	@Test
	public void testFreezePeaAdvanceFive() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 2;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,false,true, true};
		int[] zombXLoc = {-1,-1,-1,20,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 4
	 */
	@Test
	public void testFreezePeaAdvanceSix() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 3;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,true,false,false,true};
		int[] zombXLoc = {-1,20,-1,-1,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 4
	 */
	@Test
	public void testFreezePeaAdvanceSeven() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 4;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,true,true,false};
		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 0
	 */
	@Test
	public void testFreezePeaAdvanceEight() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 0;
		int freezePeaXPos= 1;
//		boolean[] zombInLane =  {false,false,true,true,false};
//		int[] zombXLoc = {-1,-1,20,60,-1};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
//		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 * and should remove the FreezePea.  Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceNine() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertTrue("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return true",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return 1000-300 ",  1000-300,zomb.getHealth()) ;
	}
	
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 *  so low that it is removed and should remove the FreezePea.  Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceTen() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		zomb.setHealth(200);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertFalse("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return false",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		assertEquals("zomb.getHealth() should return -100 ",  -100,zomb.getHealth()) ;
	}
	/**
	 * This test has 3 zombies, and one is in the lane that the FreezePea is, so should lower the zombie's health
	 *  so low that it is removed and should remove the FreezePea.  Lane 1
	 */
	@Test
	public void testFreezePeaAdvanceEleven() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int freezePeaLane = 1;
		int freezePeaXPos= 1;
		boolean[] zombInLane =  {false,false,true,false,true};
		int[] zombXLoc = {-1,-1,20,-1,60};
		Zombie zomb = createZombie(freezePeaLane,freezePeaXPos,gamePanel);
		zomb.setHealth(300);
		
		gamePanel.setLaneZombies(createZombieList(zombInLane,zombXLoc,gamePanel));
		gamePanel.getLaneZombies().get(freezePeaLane).add(zomb);
		
		FreezePea freezePea = new FreezePea(gamePanel, freezePeaXPos, freezePeaLane);
		gamePanel.getLanePeas().get(freezePeaLane).add(freezePea);
			
		freezePea.advance();


		assertFalse("gamePanel.getLaneFreezePeas().get(freezePeaLane).contains(freezePea) should return false ",gamePanel.getLanePeas().get(freezePeaLane).contains(freezePea) );
		assertEquals("zomb.getHealth() should return 0 ",  0,zomb.getHealth()) ;
		assertFalse("gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb) should return false",  gamePanel.getLaneZombies().get(freezePeaLane).contains(zomb)) ;
		
	}

	
	
	private ArrayList<ArrayList<Zombie>> createZombieList( boolean[] lane, int[] xLoc, GamePanel gp) {
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

	private Zombie createZombie(int lane, int xLoc, GamePanel gp) {

		Zombie zomb = new Zombie(gp, lane);
		zomb.setPosX(xLoc);
		return zomb;

	}

}
