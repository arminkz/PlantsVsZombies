import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;



public class GamePanelTesting implements GamePanel {


	 
	    private Collider[] colliders;

	    private ArrayList<ArrayList<Zombie>> laneZombies;
	    private ArrayList<ArrayList<Pea>> lanePeas;
	    private ArrayList<Sun> activeSuns;


	    private GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

	
	    /* (non-Javadoc)
		 * @see GamePanel#getSunScore()
		 */
	    public int getSunScore() {
	        return -1;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setSunScore(int)
		 */
	    public void setSunScore(int sunScore) {
	        
	    }

	    public GamePanelTesting(JLabel sunScoreboard) {
	       
	        setSunScore(150);  //pool avalie

	      
	        laneZombies = new ArrayList<>();
	        laneZombies.add(new ArrayList<>()); //line 1
	        laneZombies.add(new ArrayList<>()); //line 2
	        laneZombies.add(new ArrayList<>()); //line 3
	        laneZombies.add(new ArrayList<>()); //line 4
	        laneZombies.add(new ArrayList<>()); //line 5

	        lanePeas = new ArrayList<>();
	        lanePeas.add(new ArrayList<>()); //line 1
	        lanePeas.add(new ArrayList<>()); //line 2
	        lanePeas.add(new ArrayList<>()); //line 3
	        lanePeas.add(new ArrayList<>()); //line 4
	        lanePeas.add(new ArrayList<>()); //line 5

//	        colliders = new Collider[45];
//	        for (int i = 0; i < 45; i++) {
//	            Collider a = new Collider();
//	            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
//	            a.setAction(new PlantActionListener((i % 9), (i / 9)));
//	            colliders[i] = a;
////	            add(a, new Integer(0));
//	        }

	        //colliders[0].setPlant(new FreezePeashooter(this,0,0));
	/*
	        colliders[9].setPlant(new Peashooter(this,0,1));
	        laneZombies.get(1).add(new NormalZombie(this,1));*/

	        activeSuns = new ArrayList<>();

	        
	        
	        

	      
	       

	    }

	    private void advance() {
//	        for (int i = 0; i < 5; i++) {
//	            for (Zombie z : laneZombies.get(i)) {
//	                z.advance();
//	            }
//
//	            for (int j = 0; j < lanePeas.get(i).size(); j++) {
//	                Pea p = lanePeas.get(i).get(j);
//	                p.advance();
//	            }
//
//	        }
//
//	        for (int i = 0; i < activeSuns.size(); i++) {
//	            activeSuns.get(i).advance();
//	        }

	    }

		public void paintComponent(Graphics g) {
	       
	     

	       

	    }

	    private class PlantActionListener implements ActionListener {

	        int x, y;

	        public PlantActionListener(int x, int y) {
	            this.x = x;
	            this.y = y;
	        }

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (activePlantingBrush == GameWindow.PlantType.Sunflower) {
	                if (getSunScore() >= 50) {
	                    colliders[x + y * 9].setPlant(new Sunflower(GamePanelTesting.this, x, y));
	                    setSunScore(getSunScore() - 50);
	                }
	            }
	            if (activePlantingBrush == GameWindow.PlantType.Peashooter) {
	                if (getSunScore() >= 100) {
	                    colliders[x + y * 9].setPlant(new Peashooter(GamePanelTesting.this, x, y));
	                    setSunScore(getSunScore() - 100);
	                }
	            }

	            if (activePlantingBrush == GameWindow.PlantType.FreezePeashooter) {
	                if (getSunScore() >= 175) {
	                    colliders[x + y * 9].setPlant(new FreezePeashooter(GamePanelTesting.this, x, y));
	                    setSunScore(getSunScore() - 175);
	                }
	            }
	            activePlantingBrush = GameWindow.PlantType.None;
	        }
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#mouseDragged(java.awt.event.MouseEvent)
		 */
	    @Override
	    public void mouseDragged(MouseEvent e) {

	    }

	    /* (non-Javadoc)
		 * @see GamePanel#mouseMoved(java.awt.event.MouseEvent)
		 */
	    @Override
	    public void mouseMoved(MouseEvent e) {
	     
	    }

	    static int progress = 0;

	    public static void setProgress(int num) {
	        progress = progress + num;
	        System.out.println(progress);
	        if (progress >= 150) {
	            if ("1".equals(LevelData.LEVEL_NUMBER)) {
	                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "Starting next LEVEL_CONTENT");
	                GameWindow.gw.dispose();
	                LevelData.write("2");
	                GameWindow.gw = new GameWindow();
	            } else {
	                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
	                LevelData.write("1");
	                System.exit(0);
	            }
	            progress = 0;
	        }
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#getActivePlantingBrush()
		 */
	    public GameWindow.PlantType getActivePlantingBrush() {
	        return activePlantingBrush;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setActivePlantingBrush(GameWindow.PlantType)
		 */
	    public void setActivePlantingBrush(GameWindow.PlantType activePlantingBrush) {
	        this.activePlantingBrush = activePlantingBrush;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#getLaneZombies()
		 */
	    public ArrayList<ArrayList<Zombie>> getLaneZombies() {
	        return laneZombies;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setLaneZombies(java.util.ArrayList)
		 */
	    public void setLaneZombies(ArrayList<ArrayList<Zombie>> laneZombies) {
	        this.laneZombies = laneZombies;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#getLanePeas()
		 */
	    public ArrayList<ArrayList<Pea>> getLanePeas() {
	        return lanePeas;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setLanePeas(java.util.ArrayList)
		 */
	    public void setLanePeas(ArrayList<ArrayList<Pea>> lanePeas) {
	        this.lanePeas = lanePeas;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#getActiveSuns()
		 */
	    public ArrayList<Sun> getActiveSuns() {
	        return activeSuns;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setActiveSuns(java.util.ArrayList)
		 */
	    public void setActiveSuns(ArrayList<Sun> activeSuns) {
	        this.activeSuns = activeSuns;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#getColliders()
		 */
	    public Collider[] getColliders() {
	        return colliders;
	    }

	    /* (non-Javadoc)
		 * @see GamePanel#setColliders(Collider[])
		 */
	    public void setColliders(Collider[] colliders) {
	        this.colliders = colliders;
	    }

		@Override
		public void remove(Sun sun) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(Sun sta, Integer integer) {
			// TODO Auto-generated method stub
			
		}

}
