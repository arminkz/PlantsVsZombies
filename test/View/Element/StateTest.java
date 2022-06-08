package View.Element;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StateTest {

    /**
     * Purpose: test Constructor of State Class
     * Input: State(10, 25)
     * Expected:
     * return SUCCESS
     * positionX = 10, positionY = 25
     */
	@Test
	public void testState() {
		State state = new State(10, 25);
		assertEquals(10, state.getPositionX());
		assertEquals(25, state.getPositionY());
	}

    /**
     * Purpose: test function setPositionX() 
     * Input: setPositionX(15)
     * Expected:
     * return SUCCESS
     * positionX = 15, positionY = 25
     */
	@Test
	public void testSetPositionX() {
		State state = new State(10, 25);
		state.setPositionX(15);
		assertEquals(15, state.getPositionX());
		assertEquals(25, state.getPositionY());
	}
}
