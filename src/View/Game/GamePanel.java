package View.Game;

import Model.Plant.NormalPeashooter;
import Model.Plant.Plant;
import Model.Plant.PlantFactory;
import View.Game.GameFrame.PlantType;

import Model.Lane.Lane;
import Model.Level.LevelData;
import Model.Pea.FreezePea;
import Model.Pea.Pea;
import Model.Plant.FreezePeashooter;
import Model.Plant.Sunflower;
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
    public static final int WINDOW_WIDTH = 1012;
    public static final int WINDOW_HEIGHT = 785;
    public static final int INIT_SCORE = 150;
    public static final int NUMBER_OF_ROW_BLOCK = 9;
    public static final int NUMBER_OF_COLUMN_BLOCK = 5;
    public static final int NUMBER_OF_BLOCK = NUMBER_OF_ROW_BLOCK * NUMBER_OF_COLUMN_BLOCK;
    public static final int REDRAW_DELAY = 25;
    public static final int ADVANCER_DELAY = 60;
    public static final int PRODUCE_SUN_DELAY = 5000;
    public static final int PRODUCE_ZOMBIE_DELAY = 7000;
    public static final int SUN_POS_X = 37;
    public static final int SUN_POS_Y = 80;
    public static final int SUN_WIDTH = 60;
    public static final int SUN_HEIGHT = 20;

    private static GamePanel gamePanel = null;

    private Image backgroundImage = new ImageIcon(this.getClass().getResource("../../images/mainBG.png")).getImage();

    private Collider[] colliders;

    private ArrayList<Sun> activeSuns;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;

    private JLabel sunScoreboard;

    private PlantType activePlantingBrush = PlantType.None;

    private int mouseX, mouseY;
    private int sunScore;

    public GamePanel() {
        initializeLayout();
        setSunScore(INIT_SCORE);

        colliders = makeColliders();
        activeSuns = new ArrayList<>();

        setTimer();
    }

    public static GamePanel getInstance() {
        if(gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }

    private void initializeLayout() {
        JLabel sun = new JLabel("SUN");
        sun.setLocation(SUN_POS_X, SUN_POS_Y);
        sun.setSize(SUN_WIDTH, SUN_HEIGHT);

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        this.sunScoreboard = sun;
        add(this.sunScoreboard, new Integer(2));
    }

    public int getSunScore() {
        return sunScore;
    }

    public void setSunScore(int sunScore) {
        this.sunScore = sunScore;
        sunScoreboard.setText(String.valueOf(sunScore));
    }

    public Collider[] makeColliders() {
        Collider[] colliders = new Collider[NUMBER_OF_BLOCK];
        for (int i = 0; i < NUMBER_OF_BLOCK; i++) {
            Collider collider = new Collider();
            collider.setLocation(44 + (i % NUMBER_OF_ROW_BLOCK) * 100, 109 + (i / NUMBER_OF_ROW_BLOCK) * 120);
            collider.setAction(new PlantActionListener((i % NUMBER_OF_ROW_BLOCK), (i / NUMBER_OF_ROW_BLOCK)));
            colliders[i] = collider;
            add(collider, new Integer(0));
        }
        return colliders;
    }

    public void setTimer() {
        redrawTimer = new Timer(REDRAW_DELAY, handleRedraw());
        advancerTimer = new Timer(ADVANCER_DELAY, handleAdvancer());
        sunProducer = new Timer(PRODUCE_SUN_DELAY, handleProduceSun());
        zombieProducer = new Timer(PRODUCE_ZOMBIE_DELAY, handleProduceZombie());

        redrawTimer.start();
        advancerTimer.start();
        sunProducer.start();
        zombieProducer.start();
    }

    public ActionListener handleRedraw() {
        return (ActionEvent e) -> repaint();
    }
    public ActionListener handleAdvancer() {
        return (ActionEvent e) -> advance();
    }
    public ActionListener handleProduceSun() {
        return (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(this, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            activeSuns.add(sta);
            add(sta, new Integer(1));
        };
    }
    public ActionListener handleProduceZombie() {
        return (ActionEvent e) -> {
            Random rnd = new Random();
            LevelData levelData = new LevelData();
            String[] Level = levelData.LEVEL_CONTENT[Integer.parseInt(levelData.LEVEL_NUMBER) - 1];
            int[][] LevelValue = levelData.LEVEL_VALUE[Integer.parseInt(levelData.LEVEL_NUMBER) - 1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie zombie = null;
            for (int i = 0; i < LevelValue.length; i++) {
                if (t < LevelValue[i][0] || t > LevelValue[i][1]) continue;
                zombie = Zombie.getZombie(Level[i], GamePanel.this, l);
            }
            Lane.getInstance().getLaneZombies().get(l).add(zombie);
        };
    }

    private void advance() {
        for (int i = 0; i < 5; i++) {
            zombieAdvance(i);
            peaAdvance(i);
        }
        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).advance();
        }
    }

    private void peaAdvance(int laneIndex) {
        for (int j = 0; j < Lane.getInstance().getLanePeas().get(laneIndex).size(); j++) {
            Pea pea = Lane.getInstance().getLanePeas().get(laneIndex).get(j);
            Rectangle peaRectangle = new Rectangle(pea.getPositionX(), 130 + pea.getMyLane() * 120, 28, 28);
            for (int zombieIndex = 0; zombieIndex < Lane.getInstance().getLaneZombies().get(pea.getMyLane()).size(); zombieIndex++) {
                Zombie zombie = Lane.getInstance().getLaneZombies().get(pea.getMyLane()).get(zombieIndex);
                Rectangle zombieRectangle = new Rectangle(zombie.getPosX(), 109 + pea.getMyLane() * 120, 400, 120);
                if (peaRectangle.intersects(zombieRectangle)) {
                    zombie.setHealth(zombie.getHealth() - pea.getPower());
                    if (pea instanceof FreezePea)
                        zombie.slow();
                    boolean exit = false;

                    Lane.getInstance().getLaneZombies().get(pea.getMyLane()).remove(pea);
                    if (exit) break;
                }
            }
            pea.advance();
        }
    }

    private void zombieAdvance(int laneIndex) {
        for (Zombie z : Lane.getInstance().getLaneZombies().get(laneIndex)) {
            z.advance();
            if (z.getHealth() < 0) {
                killZombie(laneIndex, z);
                break;
            }
        }
    }

    private void killZombie(int i, Zombie z) {
        System.out.println("ZOMBIE DIED");
        Lane.getInstance().getLaneZombies().get(i).remove(z);
        setProgress(10);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(backgroundImage, 0, 0, null);

        //Draw Plants
        for (int i = 0; i < NUMBER_OF_BLOCK; i++) {
            if (colliders[i].assignedPlant == null) continue;
            colliders[i].assignedPlant.draw(i, graphics);
        }
        for (int i = 0; i < 5; i++) {
            for (Zombie zombie : Lane.getInstance().getLaneZombies().get(i)) {
                zombie.draw(zombie.getPosX(), 109 + (i * 120), graphics);
            }
            for (int j = 0; j < Lane.getInstance().getLanePeas().get(i).size(); j++) {
                Pea pea = Lane.getInstance().getLanePeas().get(i).get(j);
                pea.draw(pea.getPositionX(), 130 + (i * 120), graphics);
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

            PlantFactory.getInstance();
            Plant plant = PlantFactory.getPlant(activePlantingBrush.toString(), x, y);
            if(getSunScore() >= plant.getCost()) {
                colliders[x + y * 9].setPlant(plant);
                setSunScore(getSunScore() - plant.getCost());
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
        if(progress < 150) return;
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

    public GameFrame.PlantType getActivePlantingBrush() {
        return activePlantingBrush;
    }

    public void setActivePlantingBrush(GameFrame.PlantType activePlantingBrush) {
        this.activePlantingBrush = activePlantingBrush;
    }

    public ArrayList<Sun> getActiveSuns() {
        return activeSuns;
    }

    public Collider[] getColliders() {
        return colliders;
    }

    public Timer getRedrawTimer() {
        return redrawTimer;
    }

    public Timer getAdvancerTimer() {
        return advancerTimer;
    }

    public Timer getSunProducer() {
        return sunProducer;
    }

    public Timer getZombieProducer() {
        return zombieProducer;
    }
}
