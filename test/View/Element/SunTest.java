package View.Element;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import View.Game.GamePanel;

class SunTest {
    private GamePanel gamePanel;

	@BeforeEach
	void setUp() throws Exception {
        gamePanel = GamePanel.getInstance();
	}
	
    /**
     * Purpose: test Constructor of Sun Class
     * Input: State(10, 25, 53)
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25, endPositionY = 53
     */
	@Test
	public void testSun() {
		Sun sun = new Sun(gamePanel, 10, 25, 53);
		assertEquals(gamePanel, sun.getGamePanel());
		assertEquals(10, sun.getSunState().getPositionX());
		assertEquals(25, sun.getSunState().getPositionY());
		assertEquals(53, sun.getSunState().getEndPositionY());
	}
	
    /**
     * Purpose: test function getGamePanel() 
     * Input: getGamePanel()
     * Expected:
     * return SUCCESS
     * equal gamePanel and element get from getGamePanel()
     */
	@Test
	public void testGetGamePanel() {
		Sun sun = new Sun(gamePanel, 10, 25, 53);
		assertEquals(gamePanel, sun.getGamePanel());
	}
	
    /**
     * Purpose: test function getSunState
     * Input: getSunState()
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25, endPositionY = 53
     */
	@Test
	public void testGetSunState() {
		Sun sun = new Sun(gamePanel, 10, 25, 53);
		assertEquals(10, sun.getSunState().getPositionX());
		assertEquals(25, sun.getSunState().getPositionY());
		assertEquals(53, sun.getSunState().getEndPositionY());
	}
}
