
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
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class nickTestPlantVsZombie {

	@Rule
	public Timeout globalTimeout = new Timeout(20, SECONDS);


	/*
	 * This tests the add method - ascending and the normal iterator
	 */
	@Test
	public void testPeaAdvanceOne() {

		GamePanelTesting gamePanel = new GamePanelTesting(new JLabel("Hello"));

		int lane = 1;
		int xPos = 1;
		// when(gamePanelMock.getLaneZombies().get(lane).size()).thenReturn(1);
		// when(gamePanelMock.getLaneZombies().get(lane).get(0)).thenReturn(new
		// Zombie(gamePanelMock,2));
		Pea pea = new Pea(gamePanel, xPos, lane);

		Zombie[] zomb = new Zombie[2];
		zomb[0] = new Zombie(gamePanel, 1);
		zomb[0].setPosX(xPos);
		zomb[1] = new Zombie(gamePanel, 2);
		boolean[] collide = new boolean[2];
		for (boolean i : collide) {
			i = false;
		}
		// Zombie zomb = new Zombie(gamePanelMock,1);
		// pea.testAdvance(zomb,collide);

		for (int i = 0; i < collide.length; i++) {

			if (i == 0) {
				assertEquals("collide[i] should return \"true \" ", true, collide[i]);
			} else {
				assertEquals("collide[i] should return \"false \" ", false, collide[i]);
			}
			assertNotNull("zomb[i] should return a zombie with lowered health ", zomb[i]);
		}
		assertEquals("pea.getPosX() should return \"16 \" ", 16, pea.getPosX());
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
	 * and should remove the pea.  Lane 4
	 */
	@Test
	public void testPeaAdvanceSix() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 3;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,true,false,false,true};
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
	 * and should remove the pea.  Lane 4
	 */
	@Test
	public void testPeaAdvanceSeven() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 4;
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
	 * and should remove the pea.  Lane 0
	 */
	@Test
	public void testPeaAdvanceEight() {
		
		
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int peaLane = 0;
		int peaXPos= 1;
		boolean[] zombInLane =  {false,false,true,true,false};
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
	
	private ArrayList<ArrayList<Pea>> createPeaList(int x, boolean[] lane, int[] xLoc, GamePanel gp) {
		ArrayList<ArrayList<Pea>> lanePeas;
		lanePeas = new ArrayList<>();
		lanePeas.add(new ArrayList<>()); // line 1
		lanePeas.add(new ArrayList<>()); // line 2
		lanePeas.add(new ArrayList<>()); // line 3
		lanePeas.add(new ArrayList<>()); // line 4
		lanePeas.add(new ArrayList<>()); // line 5
		for (int i = 0; i < lane.length; i++) {

			if (lane[i]) {
				lanePeas.get(i).add(createPea(i, xLoc[i], gp));
			}
		}

		return lanePeas;
	}

	private Pea createPea(int lane, int xLoc, GamePanel gp) {

		Pea pea = new Pea(gp, lane, xLoc);
		
		return pea;

	}
}
