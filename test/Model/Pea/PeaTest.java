package Model.Pea;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Lane.Lane;
import View.Game.GamePanel;

class PeaTest {
    private Pea testPea;
    private GamePanel gamePanel;

	@BeforeEach
	void setUp() throws Exception {
        gamePanel = GamePanel.getInstance();
	}

	/**
     * Purpose: test NormalPea.advance() function
     * Input: NormalPea.advance() gamePanel.advance()
     * Expected:
     *      return SUCCESS
     *      (xPositionOrigin + 15 == xPositionAfterAdvance) == True
     */
	
    @Test
    public void testNormalPeaAdvance() {
    	testPea = new NormalPea(gamePanel, 0, 1);
    	Lane.getInstance().getLanePeas().get(0).add(testPea);

        int xPositionOrigin = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        gamePanel.advance();

        int xPositionAfterAdvance = Lane.getInstance().getLanePeas().get(0).get(0).getPositionX();

        assertTrue(xPositionOrigin + 15 == xPositionAfterAdvance);
    }

}
