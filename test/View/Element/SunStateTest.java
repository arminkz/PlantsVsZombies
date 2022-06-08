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

    /**
     * Purpose: test function setDestructionTime() 
     * Input: setDestructionTime(6)
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25, endPositionY = 53, destructTime = 6
     */
	@Test
	public void testSetDestructionTime() {
		SunState sunState = new SunState(10, 25, 53);
		sunState.setDestructionTime(6);
		assertEquals(10, sunState.getPositionX());
		assertEquals(25, sunState.getPositionY());
		assertEquals(53, sunState.getEndPositionY());
		assertEquals(6, sunState.getDestructionTime());
	}

    /**
     * Purpose: test function getDestructionTime() 
     * Input: getDestructionTime()
     * Expected:
     * return SUCCESS
     * destructTime = 8
     */
	@Test
	public void testGetDestructionTime() {
		SunState sunState = new SunState(10, 25, 53);
		sunState.setDestructionTime(8);
		assertEquals(8, sunState.getDestructionTime());
	}

    /**
     * Purpose: test function updateState() 
     * Input: updateState()
     * Expected:
     * return SUCCESS
     * first updateState : positionX = 10, positionY = 25, endPositionY = 54, 
     *                     destructionTim = 6
     * second updateState with EndPosition = 29 : positionX = 10, positionY = 29, endPositionY = 29, 
     *                                            destructionTime = 5 
     */
	@Test
	public void testUpdateState() {
		SunState sunState = new SunState(10, 25, 53);
		sunState.setDestructionTime(6);
		sunState.updateState();
		
		assertEquals(10, sunState.getPositionX());
		assertEquals(29, sunState.getPositionY());
		assertEquals(53, sunState.getEndPositionY());
		assertEquals(6, sunState.getDestructionTime());
		
		sunState.setEndPositionY(29);
		sunState.updateState();
		assertEquals(10, sunState.getPositionX());
		assertEquals(29, sunState.getPositionY());
		assertEquals(29, sunState.getEndPositionY());
		assertEquals(5, sunState.getDestructionTime());
	}
}
