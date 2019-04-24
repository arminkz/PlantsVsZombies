//George Harrington
//testing commit, no testing done yet

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

import org.junit.Test;

public class GeorgeTests {
	
	@Test
	public void testSunSetup() {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		int startX = 1;
		int startY = 1;
		int endY = 10; //startY < endY
		Sun s = new Sun(gamePanel, startX, startY, endY);
		gamePanel.add(s, 0);
		assertEquals("One sun active", 1, gamePanel.getActiveSuns().size());
		
		//Unable to do much other testing??
		//Setup doesn't work because sun is never properly added to the GamePanel 
		//(in the unimplemented add method). The only sun in the game is the starting 
		//150 sun in the sunScore. Playing the game, I have not seen any sun spawn 
		//naturally, as a direct result of missing spawn code.
	}
	
	@Test
	public void testLevelData() throws IOException {
		//NOTE: changes made to LevelData class to better allow for testing.
		new File("LEVEL_CONTENT_TEST.vbhv").delete();
		
		//testing new file creation
		LevelData ld = new LevelData("LEVEL_CONTENT_TEST.vbhv");
		File lct = new File("LEVEL_CONTENT_TEST.vbhv");
		BufferedReader br = new BufferedReader(new FileReader(lct));
		assertEquals("File creation", "1", br.readLine());
		
		//testing existing file read-in
		ld = new LevelData("LEVEL_CONTENT_TEST.vbhv");
		br = new BufferedReader(new FileReader(lct));
		assertEquals("File read-in", "1", br.readLine());
		
		//make new file, test read-in on non-default number
		lct.delete();
		lct = new File("LEVEL_CONTENT_TEST.vbhv");
		BufferedWriter bwr = new BufferedWriter(new FileWriter(lct));
        bwr.write("15");
        bwr.close();
        ld = new LevelData("LEVEL_CONTENT_TEST.vbhv");
		br = new BufferedReader(new FileReader(lct));
		assertEquals("File read-in", "15", br.readLine());
		
		//NOTE: IOExceptions not handled in current setup, "catch" statement is empty
		
		//testing write
		ld.write("20");
		br = new BufferedReader(new FileReader(lct));
		assertEquals("File read-in", "20", br.readLine());
		
		//Not sure how to cause IOException to finish coverage
	}
}
