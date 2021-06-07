package Game.view;

import Game.Collider;
import Game.LevelData;
import Pea.model.FreezePea;
import Pea.model.NormalPea;
import Pea.model.Pea;
import plant.creator.PlantFactory;
import plant.model.FreezePeashooter;
import plant.model.Peashooter;
import plant.model.Plant;
import plant.model.Sunflower;
import sun.producer.RandomSunProducer;
import sun.producer.SunProducer;
import zombie.model.Zombie;
import zombie.producer.ZombieProducer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JLayeredPane {
    private final int NUM_LINES=5; 
    public static final int SUN_PRODUCE_DELAY = 5000;
    public static final int ZOMBIE_PRODUCE_DELAY = 7000;
    public static final int ADVANCE_DELAY = 60;
    public static final int REDRAW_DELAY = 25;
    private static GamePanel gamePanel = null;

    private Image backgroundImage;

    private Collider[] colliders;

    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Pea>> lanePeas;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer zombieProducerTimer;
    private Timer sunProducerTimer;
    private JLabel sunScoreboard;

    private GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

    private int sunScore;
    private SunProducer sunProducer;
    private ZombieProducer zombieProducer;


    private GamePanel() {
        initializeLayout();
        loadImages();

        initializeLaneZombies();
        initializeLanePeas();
        initializeCollider();
        setSunScore(150);  //pool avalie

        setRedrawTimer();
        setAdvancerTimer();
        setZombieProducerTimer();
        setSunProducerTimer();
    }

    public static GamePanel getInstance(){
        if(gamePanel == null) {gamePanel=new GamePanel();}
        return gamePanel;
    }

    private void setSunProducerTimer() {
        sunProducer = new RandomSunProducer();
        sunProducerTimer = new Timer(SUN_PRODUCE_DELAY,(ActionEvent e)->{sunProducer.createSunView();});
        sunProducerTimer.start();
    }

    private void setZombieProducerTimer() {
        zombieProducerTimer = new Timer(ZOMBIE_PRODUCE_DELAY, (ActionEvent e) -> {
            Random rnd = new Random();
            zombieProducer = new ZombieProducer();

            int lane = rnd.nextInt(5);

            Zombie zombie = zombieProducer.createNewZombie(lane);
            addZombie(lane, zombie);
        });
        zombieProducerTimer.start();
    }

    private void setAdvancerTimer() {
        advancerTimer = new Timer(ADVANCE_DELAY, (ActionEvent e) -> advance());
        advancerTimer.start();
    }

    private void setRedrawTimer() {
        redrawTimer = new Timer(REDRAW_DELAY, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();
    }


    private void addZombie(int lane, Zombie zombie) {
        if(zombie!=null) {
            laneZombies.get(lane).add(zombie);
        }
    }

    private void initializeCollider() {
        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider collider = new Collider();
            collider.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            collider.setAction(new PlantActionListener((i % 9), (i / 9)));
            colliders[i] = collider;
            add(collider, new Integer(0));
        }
    }

    private void initializeLanePeas() {
        lanePeas = new ArrayList<>();
        for(int i = 0; i < NUM_LINES ; i++ ) {
          lanePeas.add(new ArrayList<>()); 
        }
    }

    private void initializeLaneZombies() {
        laneZombies = new ArrayList<>();
        for(int i = 0; i < NUM_LINES ; i++ ) {
          laneZombies.add(new ArrayList<>());  
        }
    }

    private void initializeLayout() {
        JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);

        setSize(1000, 752);
        setLayout(null);
        this.sunScoreboard = sun;
        add(this.sunScoreboard, new Integer(2));
    }

    private void loadImages() {
        backgroundImage = new ImageIcon(this.getClass().getResource("../../images/mainBG.png")).getImage();
    }

    public void advance() {
        for (int laneIndex = 0; laneIndex < 5; laneIndex++) {
            zombieAdvance(laneIndex);
            peaAdvance(laneIndex);
            colliderAdvance();
        }
    }

    private void colliderAdvance() {
        for (Collider c: colliders) {
            if(c.getPlant()!=null && c.getPlant().getHealth() <= 0) {
                c.removePlant();
            }
        }
    }

    private void zombieAdvance(int laneIndex) {
        for (Zombie z : laneZombies.get(laneIndex)) {
            z.advance();
            if (z.getXPosition() < 0) {
                gameOver();
            }
            if (!z.getAlive()) {
                killZombie(laneIndex, z);
                break;
            }
        }
    }

    private void peaAdvance(int laneIndex) {
        for (int j = 0; j < lanePeas.get(laneIndex).size(); j++) {
            Pea pea = lanePeas.get(laneIndex).get(j);
            Rectangle peaRectangle = new Rectangle(pea.getXPosition(), 130 + pea.getMyLane() * 120, 28, 28);
            for (int zombieIndex = 0; zombieIndex < gamePanel.getLaneZombies().get(pea.getMyLane()).size(); zombieIndex++) {
                Zombie zombie = gamePanel.getLaneZombies().get(pea.getMyLane()).get(zombieIndex);
                Rectangle zombieRectangle = new Rectangle(zombie.getXPosition(), 109 + pea.getMyLane() * 120, 400, 120);
                if (peaRectangle.intersects(zombieRectangle)) {
                    zombie.setHealth(zombie.getHealth() - pea.getPower());
                    if (pea instanceof FreezePea)
                        zombie.slow();
                    boolean exit = false;
                    
                    gamePanel.getLaneZombies().get(pea.getMyLane()).remove(pea);
                    if (exit) break;
                }
            }
            pea.advance();
        }
    }

    private void killZombie(int i, Zombie z) {
        System.out.println("ZOMBIE DIED");
        laneZombies.get(i).remove(z);
        setProgress(10);
    }

    private void gameOver() {
        JOptionPane.showMessageDialog(this, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
        gamePanel = null;
        GameWindow.gameWindow.dispose();
        GameWindow.gameWindow = new GameWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Collider collider = colliders[i];
            Plant plant = collider.getPlant();
            if (plant != null) {
               plant.draw(g);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (Zombie zombie : laneZombies.get(i)) {
                zombie.draw(g);
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea pea = lanePeas.get(i).get(j);
                g.drawImage(Pea.getImage(), pea.getXPosition(), 130 + (i * 120), null);
            }
        }
    }

    private class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Plant plant = PlantFactory.getInstance().getPlant(activePlantingBrush,x,y);
            if(plant.getPrice() <= getSunScore()) {
                colliders[x + y * 9].setPlant(plant);
                setSunScore(sunScore - plant.getPrice());
            }
            activePlantingBrush = GameWindow.PlantType.None;
        }
    }

    static int progress = 0;

    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if (progress >= 150) {
            if ("1".equals(LevelData.LEVEL_NUMBER)) {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "Starting next LEVEL_CONTENT");
                gamePanel = null;
                GameWindow.gameWindow.dispose();
                LevelData.write("2");
                GameWindow.gameWindow.gameStart();
            } else {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
                LevelData.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }

    public GameWindow.PlantType getActivePlantingBrush() {
        return activePlantingBrush;
    }

    public void setActivePlantingBrush(GameWindow.PlantType activePlantingBrush) {
        this.activePlantingBrush = activePlantingBrush;
    }

    public ArrayList<ArrayList<Zombie>> getLaneZombies() {
        return laneZombies;
    }

    public void setLaneZombies(ArrayList<ArrayList<Zombie>> laneZombies) {
        this.laneZombies = laneZombies;
    }

    public ArrayList<ArrayList<Pea>> getLanePeas() {
        return lanePeas;
    }

    public void setLanePeas(ArrayList<ArrayList<Pea>> lanePeas) {
        this.lanePeas = lanePeas;
    }

    public Collider[] getColliders() {
        return colliders;
    }

    public void setColliders(Collider[] colliders) {
        this.colliders = colliders;
    }
    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }
}