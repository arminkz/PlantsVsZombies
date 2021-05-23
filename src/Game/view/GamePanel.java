package Game.view;

import Game.Collider;
import Game.LevelData;
import Pea.model.FreezePea;
import Pea.model.Pea;
import plant.model.FreezePeashooter;
import plant.model.Peashooter;
import plant.model.Plant;
import plant.model.Sunflower;
import sun.model.Sun;
import sun.presenter.SunPresenter;
import sun.view.SunView;
import zombie.model.ConeHeadZombie;
import zombie.model.NormalZombie;
import zombie.model.Zombie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * 원래는 MouseMotionListener interface를 implement 하고 있었는데
 * 하지만 window.GamePanel Class가 구현하는 화면에서는 마우스 입력을 각각의 객체에서 처리하도록 하고 삭제하였다.
 */
public class GamePanel extends JLayeredPane {

    private static GamePanel gamePanel = null;

    private Image bgImage;
    private Image sunflowerImage;
    private Image peashooterImage;
    private Image freezePeashooterImage;
    private Image peaImage;
    private Image freezePeaImage;

    private Image normalZombieImage;
    private Image coneHeadZombieImage;
    private Collider[] colliders;

    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Pea>> lanePeas;

    private Timer redrawTimer;
    private Timer advancerTimer;
    private Timer sunProducer;
    private Timer zombieProducer;
    private JLabel sunScoreboard;

    private GameWindow.PlantType activePlantingBrush = GameWindow.PlantType.None;

    private int sunScore;

/**
* 복잡하게 구현되어 있던 하나의 메소드를 Extract Method Refactoring을 통하여
* Code의 Readablity와 understandavility를 높혔다.
 * Design Pattern을 적용시키기 전에 Class의 동작을 이해하기 쉽도록 하였다.
* */
    private GamePanel() {
        JLabel sun = new JLabel("SUN");
        sun.setLocation(37, 80);
        sun.setSize(60, 20);

        initializeLayout(sun);
        loadImages();

        initializeLaneZombies();
        initializeLanePeas();
        initializeCollider();
        setSunScore(150);  //pool avalie


        setRedrawTimer();
        setAdvancerTimer();
        setZombieProducerTimer();
    }

    public static GamePanel getInstance(){
        if(gamePanel == null) {gamePanel=new GamePanel();}
        return gamePanel;
    }

    private void setZombieProducerTimer() {
        zombieProducer = new Timer(7000, (ActionEvent e) -> {
            Random rnd = new Random();
            LevelData lvl = new LevelData();
            String[] Level = lvl.LEVEL_CONTENT[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
            int[][] LevelValue = lvl.LEVEL_VALUE[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            Zombie z = null;
            for (int i = 0; i < LevelValue.length; i++) {
                if (t >= LevelValue[i][0] && t <= LevelValue[i][1]) {
                    z = Zombie.getZombie(Level[i], GamePanel.this, l);
                }
            }
            laneZombies.get(l).add(z);
        });
        zombieProducer.start();
    }

    private void setSunProducerTimer() {
        sunProducer = new Timer(5000, (ActionEvent e) -> {
            Random rnd = new Random();
            Sun sta = new Sun(rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            SunView sunView = new SunView(sta.getXPosition(), sta.getYPosition());
            SunPresenter sunPresenter = new SunPresenter(sunView,sta);
            sunView.init(sunPresenter);
            sunPresenter.start();
        });
        sunProducer.start();
    }

    private void setAdvancerTimer() {
        advancerTimer = new Timer(60, (ActionEvent e) -> advance());
        advancerTimer.start();
    }

    private void setRedrawTimer() {
        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });
        redrawTimer.start();
    }

    private void initializeCollider() {
        colliders = new Collider[45];
        for (int i = 0; i < 45; i++) {
            Collider a = new Collider();
            a.setLocation(44 + (i % 9) * 100, 109 + (i / 9) * 120);
            a.setAction(new PlantActionListener((i % 9), (i / 9)));
            colliders[i] = a;
            add(a, new Integer(0));
        }
    }

    private void initializeLanePeas() {
        lanePeas = new ArrayList<>();
        lanePeas.add(new ArrayList<>()); //line 1
        lanePeas.add(new ArrayList<>()); //line 2
        lanePeas.add(new ArrayList<>()); //line 3
        lanePeas.add(new ArrayList<>()); //line 4
        lanePeas.add(new ArrayList<>()); //line 5
    }

    private void initializeLaneZombies() {
        laneZombies = new ArrayList<>();
        laneZombies.add(new ArrayList<>()); //line 1
        laneZombies.add(new ArrayList<>()); //line 2
        laneZombies.add(new ArrayList<>()); //line 3
        laneZombies.add(new ArrayList<>()); //line 4
        laneZombies.add(new ArrayList<>()); //line 5
    }

    private void initializeLayout(JLabel sunScoreboard) {
        setSize(1000, 752);
        setLayout(null);
        this.sunScoreboard = sunScoreboard;
        add(sunScoreboard, new Integer(2));
    }

    private void loadImages() {
        bgImage = new ImageIcon(this.getClass().getResource("../../images/mainBG.png")).getImage();

        peashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/peashooter.gif")).getImage();
        freezePeashooterImage = new ImageIcon(this.getClass().getResource("../../images/plants/freezepeashooter.gif")).getImage();
        sunflowerImage = new ImageIcon(this.getClass().getResource("../../images/plants/sunflower.gif")).getImage();
        peaImage = new ImageIcon(this.getClass().getResource("../../images/pea.png")).getImage();
        freezePeaImage = new ImageIcon(this.getClass().getResource("../../images/freezepea.png")).getImage();

        normalZombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie1.png")).getImage();
        coneHeadZombieImage = new ImageIcon(this.getClass().getResource("../../images/zombies/zombie2.png")).getImage();
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);

        //Draw Plants
        for (int i = 0; i < 45; i++) {
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant p = c.assignedPlant;
                if (p instanceof Peashooter) {
                    g.drawImage(peashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof FreezePeashooter) {
                    g.drawImage(freezePeashooterImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
                if (p instanceof Sunflower) {
                    g.drawImage(sunflowerImage, 60 + (i % 9) * 100, 129 + (i / 9) * 120, null);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (Zombie z : laneZombies.get(i)) {
                if (z instanceof NormalZombie) {
                    g.drawImage(normalZombieImage, z.getPosX(), 109 + (i * 120), null);
                } else if (z instanceof ConeHeadZombie) {
                    g.drawImage(coneHeadZombieImage, z.getPosX(), 109 + (i * 120), null);
                }
            }

            for (int j = 0; j < lanePeas.get(i).size(); j++) {
                Pea pea = lanePeas.get(i).get(j);
                if (pea instanceof FreezePea) {
                    g.drawImage(freezePeaImage, pea.getPosX(), 130 + (i * 120), null);
                } else {
                    g.drawImage(peaImage, pea.getPosX(), 130 + (i * 120), null);
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
            if (activePlantingBrush == GameWindow.PlantType.Sunflower) {
                if (getSunScore() >= 50) {
                    colliders[x + y * 9].setPlant(new Sunflower( x, y));
                    setSunScore(getSunScore() - 50);
                }
            }
            if (activePlantingBrush == GameWindow.PlantType.Peashooter) {
                if (getSunScore() >= 100) {
                    colliders[x + y * 9].setPlant(new Peashooter( x, y));
                    setSunScore(getSunScore() - 100);
                }
            }

            if (activePlantingBrush == GameWindow.PlantType.FreezePeashooter) {
                if (getSunScore() >= 175) {
                    colliders[x + y * 9].setPlant(new FreezePeashooter( x, y));
                    setSunScore(getSunScore() - 175);
                }
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
