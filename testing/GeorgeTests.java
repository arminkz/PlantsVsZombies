//George Harrington

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
	
	//Was unable to figure out how to properly test GUI classes PlantCard and Menu in time
	//Upon further inspection, Nick included tests in his test class.
	
	//Plant is an abstract class and cannot be instantiated. I was not aware of this at the
	//time of the writing of the test plan.
	
	@Test
	public void testSunflower() throws InterruptedException {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		Sunflower s = new Sunflower(gamePanel, 1, 1);
		int suns = gamePanel.getActiveSuns().size();
		TimeUnit.SECONDS.sleep(15);
		assertFalse("Sunflower did not generate sun", suns == gamePanel.getActiveSuns().size());
	}
	
	//PlantButton is just an empty class at this point. No idea what its purpose is.
	//Instantiating it for code coverage.
	@Test
	public void testPlantButton() {
		PlantButton p = new PlantButton();
		fail("PlantButton not implemented");
	}
	
	//Zombie not landing in same lane, and/or shootTimer not registering Zombie?
	//Confusion
	@Test
	public void testPeaShooter() throws InterruptedException {
		GamePanel gamePanel = new GamePanelTesting(new JLabel("hello"));
		Peashooter p = new Peashooter(gamePanel, 1, 1);
		int peas = gamePanel.getLanePeas().size();
		Zombie z = new Zombie(gamePanel, 1);
		TimeUnit.SECONDS.sleep(2);
		assertFalse("Pea not shot", peas == gamePanel.getLanePeas().size());
	}
}
