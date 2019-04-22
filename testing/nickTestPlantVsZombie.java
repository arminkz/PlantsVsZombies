
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

	
	@Test
	public void testPeaAdvanceThree() {

		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int lane = 1;
		int xPos = 1;
		boolean[] zombInLane =  {false,true,true,false,true};
		int[] zombXLoc = {0,50,2,0,60};
		gamePanel.setLaneZombies(createZombieList(2,zombInLane,zombXLoc,gamePanel));
		Pea pea = new Pea(gamePanel, xPos, lane);

		pea.advance();

		assertEquals("pea.getPosX() should return \"16 \" ", 16, pea.getPosX());
	}

	
	
	private ArrayList<ArrayList<Zombie>> createZombieList(int x, boolean[] lane, int[] xLoc, GamePanel gp) {
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
