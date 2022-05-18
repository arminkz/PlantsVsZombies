package View.Game;

import Model.Level.LevelData;
import Model.Pea.FreezePea;
import Model.Pea.Pea;
import Model.Plant.FreezePeashooter;
import Model.Plant.Peashooter;
import Model.Plant.Plant;
import Model.Plant.Sunflower;
import Model.Zombie.ConeHeadZombie;
import Model.Zombie.NormalZombie;
import Model.Zombie.Zombie;
import View.Collider;
import View.Element.Sun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Armin on 6/25/2016.
 */
public class GamePanel extends JLayeredPane implements MouseMotionListener {

    private Image backgroundImage;
    private Image peashooterImage;
    private Image freezePeashooterImage;
    private Image sunflowerImage;
    private Image peaImage;
    private Image freezePeaImage;

    private Image normalZombieImage;
    private Image coneHeadZombieImage;
    private Collider[] colliders;

    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Pea>> lanePeas;
    private ArrayList<Sun> activeSuns;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;
    private JLabel sunScoreboard;

    public static final int WINDOW_WIDTH = 1012;
    public static final int WINDOW_HEIGHT = 785;
    public static final int INIT_SCORE = 150;
    public static final int NUMBER_OF_ROW_BLOCK = 9;
    public static final int NUMBER_OF_COLUMN_BLOCK = 9;
    public static final int NUMBER_OF_BLOCK = NUMBER_OF_ROW_BLOCK * NUMBER_OF_COLUMN_BLOCK;
    public static final int REDRAW_DELAY = 25;
    public static final int ADVANCER_DELAY = 60;
    public static final int SUNFLOWER_COST = 50;
    public static final int PEASHOOTER_COST = 100;
    public static final int FREEZEPEASHOOTER_COST = 175;

    private GameFrame.PlantType activePlantingBrush = GameFrame.PlantType.None;

    private int mouseX, mouseY;

    private int sunScore;

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public GamePanel(JLabel sunScoreboard) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        addMouseMotionListener(this);
        this.sunScoreboard = sunScoreboard;
        setSunScore(INIT_SCORE);  //pool avalie

        backgroundImage = new ImageIcon(this.getClass().getResource("../../images/mainBG.png")).getImage();
        peashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("../../images/freezepea.png")).getImage();
        normalZombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie2.png")).getImage();

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

        colliders = new Collider[NUMBER_OF_BLOCK];

        for (int i = 0; i < NUMBER_OF_BLOCK; i++) {
            Collider collider = new Collider();
            collider.setLocation(44 + (i % NUMBER_OF_ROW_BLOCK) * 100, 109 + (i / NUMBER_OF_ROW_BLOCK) * 120);
            collider.setAction(new PlantActionListener((i % NUMBER_OF_ROW_BLOCK), (i / NUMBER_OF_ROW_BLOCK)));
            colliders[i] = collider;
            add(collider, new Integer(0));
        }

        activeSuns = new ArrayList<>();

        redrawTimer = new Timer(REDRAW_DELAY, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();

        advancerTimer = new Timer(ADVANCER_DELAY, (ActionEvent e) -> advance());
        advancerTimer.start();

        sunProducer = new Timer(5000, (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            activeSuns.add(sta);
            add(sta, new Integer(1));
        });
        sunProducer.start();

        zombieProducer = new Timer(7000, (ActionEvent e) -> {
            Random rnd = new Random();
            LevelData levelData = new LevelData();
            String[] Level = levelData.LEVEL_CONTENT[Integer.parseInt(levelData.LEVEL_NUMBER) - 1];
            int[][] LevelValue = levelData.LEVEL_VALUE[Integer.parseInt(levelData.LEVEL_NUMBER) - 1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie zombie = null;
            for (int i = 0; i < LevelValue.length; i++) {
                if (t >= LevelValue[i][0] && t <= LevelValue[i][1]) {
                    zombie = Zombie.getZombie(Level[i], GamePanel.this, l);
                }
            }
            laneZombies.get(l).add(zombie);
        });
        zombieProducer.start();

    }

    private void advance() {
        for (int i = 0; i < 5; i++) {
            for (Zombie z : laneZombies.get(i)) {
                z.advance();
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea p = lanePeas.get(i).get(j);
                p.advance();
            }

        }

        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).advance();
        }

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, null);

        //Draw Plants
        for (int i = 0; i < NUMBER_OF_BLOCK; i++) {
            Collider collider = colliders[i];
            if (collider.assignedPlant != null) {
                Plant p = collider.assignedPlant;
                if (p instanceof Peashooter) {
                    graphics.drawImage(peashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof FreezePeashooter) {
                    graphics.drawImage(freezePeashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof Sunflower) {
                    graphics.drawImage(sunflowerImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (Zombie zombie : laneZombies.get(i)) {
                if (zombie instanceof NormalZombie) {
                    graphics.drawImage(normalZombieImage, zombie.getPosX(), 109 + (i * 120), null);
                } else if (zombie instanceof ConeHeadZombie) {
                    graphics.drawImage(coneHeadZombieImage, zombie.getPosX(), 109 + (i * 120), null);
                }
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea pea = lanePeas.get(i).get(j);
                if (pea instanceof FreezePea) {
                    graphics.drawImage(freezePeaImage, pea.getPosX(), 130 + (i * 120), null);
                } else {
                    graphics.drawImage(peaImage, pea.getPosX(), 130 + (i * 120), null);
                }
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
            if (activePlantingBrush == GameFrame.PlantType.Sunflower) {
                if (getSunScore() >= SUNFLOWER_COST) {
                    colliders[x + y * 9].setPlant(new Sunflower(GamePanel.this, x, y));
                    setSunScore(getSunScore() - SUNFLOWER_COST);
                }
            }

            if (activePlantingBrush == GameFrame.PlantType.Peashooter) {
                if (getSunScore() >= PEASHOOTER_COST) {
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore() - PEASHOOTER_COST);
                }
            }

            if (activePlantingBrush == GameFrame.PlantType.FreezePeashooter) {
                if (getSunScore() >= FREEZEPEASHOOTER_COST) {
                    colliders[x + y * 9].setPlant(new FreezePeashooter(GamePanel.this, x, y));
                    setSunScore(getSunScore() - FREEZEPEASHOOTER_COST);
                }
            }
            activePlantingBrush = GameFrame.PlantType.None;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    static int progress = 0;

    public static void setProgress(int num) {
        progress = progress + num;
        System.out.println(progress);
        if (progress >= 150) {
            if ("1".equals(LevelData.LEVEL_NUMBER)) {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "Starting next LEVEL_CONTENT");
                GameFrame.gameFrame.dispose();
                LevelData.write("2");
                GameFrame.gameFrame = new GameFrame();
            } else {
                JOptionPane.showMessageDialog(null, "LEVEL_CONTENT Completed !!!" + '\n' + "More Levels will come soon !!!" + '\n' + "Resetting data");
                LevelData.write("1");
                System.exit(0);
            }
            progress = 0;
        }
    }

    public GameFrame.PlantType getActivePlantingBrush() {
        return activePlantingBrush;
    }

    public void setActivePlantingBrush(GameFrame.PlantType activePlantingBrush) {
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

    public ArrayList<Sun> getActiveSuns() {
        return activeSuns;
    }

    public void setActiveSuns(ArrayList<Sun> activeSuns) {
        this.activeSuns = activeSuns;
    }

    public Collider[] getColliders() {
        return colliders;
    }

    public void setColliders(Collider[] colliders) {
        this.colliders = colliders;
    }
}
