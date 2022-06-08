package View.Element;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SunStateTest {

    /**
     * Purpose: test Constructor of SunState Class
     * Input: State(10, 25, 53)
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25, endPositionY = 53
     */
	@Test
	public void testSunState() {
		SunState sunState = new SunState(10, 25, 53);
		assertEquals(10, sunState.getPositionX());
		assertEquals(25, sunState.getPositionY());
		assertEquals(53, sunState.getEndPositionY());
	}

    /**
     * Purpose: test function setEndPositionY() 
     * Input: setEndPositionY(60)
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25, endPositionY = 60
     */
	@Test
	public void testSetEndPositionY() {
		SunState sunState = new SunState(10, 25, 53);
		sunState.setEndPositionY(60);
		assertEquals(10, sunState.getPositionX());
		assertEquals(25, sunState.getPositionY());
		assertEquals(60, sunState.getEndPositionY());
	}

    /**
     * Purpose: test function getEndPositionY() 
     * Input: getEndPositionY()
     * Expected:
     * return SUCCESS
     * endPositionY = 53
     */
	@Test
	public void testGetEndPositionY() {
		SunState sunState = new SunState(10, 25, 53);
		assertEquals(53, sunState.getEndPositionY());
	}
}
