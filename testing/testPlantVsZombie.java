// Author: Nick Patchen

import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JLabel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
public class testPlantVsZombie {

	@Rule
	public Timeout globalTimeout = new Timeout(20, SECONDS);

	
	
	// The first class to test is the Pea class
	//Nick notes
	// Will mock gameplay, because out of scope for this class
	/*
	 * This tests the add method - ascending and the normal iterator
	 */
	@Test
	public void testPeaAdvanceOne() {
		
		
		GamePanel gamePanel = new GamePanel(new JLabel ("hello"));
		int lane = 1;
		int xPos = 1;
//		when(gamePanelMock.getLaneZombies().get(lane).size()).thenReturn(1);
//		when(gamePanelMock.getLaneZombies().get(lane).get(0)).thenReturn(new Zombie(gamePanelMock,2));
		Pea pea = new Pea(gamePanel,xPos,lane);
		Zombie[] zomb = new Zombie[2];
		zomb[0] = new Zombie(gamePanel,1);
		zomb[1] = new Zombie(gamePanel,2);
//		Zombie zomb = new Zombie(gamePanelMock,1);
		pea.advance(zomb);

		assertEquals("pea.getPosX() should return \"16 \" ", 16 , pea.getPosX());
	}

	@Test
	public void testPeaAdvanceTwo() {
		
		
		GamePanel gamePanel = new GamePanel(new JLabel ("hello"));
		int lane = 1;
		int xPos = 1;
		Pea pea = new Pea(gamePanel,xPos,lane);

		pea.advance();

		assertEquals("pea.getPosX() should return \"16 \" ", 16 , pea.getPosX());
	}
	
}
