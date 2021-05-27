package Game.view;

import plantCard.model.PlantCard;
import sun.producer.RandomSunProducer;
import sun.producer.SunProducer;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Armin on 6/25/2016.
 */
public class GameWindow extends JFrame {

  enum PlantType {
    None, Sunflower, Peashooter, FreezePeashooter
  }

  private GamePanel gp;
  static public GameWindow gw;
  public static MenuFrame mw;
  PlantType activePlantingBrush = PlantType.None;

  public GameWindow() {
    setSize(1012, 785);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);
    setResizable(false);
    setVisible(true);
    initPlantCard();
  }


  // Attach Card into Panel ���뼺�� ���̱� ���� �и� Parameter : GamePanel , X-coordinate, Image_path
  private void AttachCard(GamePanel gp, int xCoordinate, String imagePath, PlantType plantType) {
    // gp = gp.getInstance();
    // gp.setLocation(0, 0);
    // getLayeredPane().add(gp, new Integer(0));
    PlantCard newCard =
        new PlantCard(new ImageIcon(this.getClass().getResource(imagePath)).getImage());
    newCard.setLocation(xCoordinate, 8);
    newCard.setAction((ActionEvent e) -> {
      gp.setActivePlantingBrush(plantType);
    });
    getLayeredPane().add(newCard, new Integer(3));
  }

  private void initPlantCard() {
    gp = GamePanel.getInstance();
    gp.setLocation(0, 0);
    getLayeredPane().add(gp, new Integer(0));
    AttachCard(gp, 110, "../../images/cards/card_sunflower.png", PlantType.Sunflower);
    AttachCard(gp, 175, "../../images/cards/card_peashooter.png", PlantType.Peashooter);
    AttachCard(gp, 240, "../../images/cards/card_freezepeashooter.png", PlantType.FreezePeashooter);
  }


  public static void gameStart() {
    mw.disposeMenuFrame();
    gw = new GameWindow();
  }

  public static void main(String[] args) {
    mw = new MenuFrame();
  }

}


class MenuFrame extends JFrame {
  boolean isTrue = true;


  public MenuFrame() {
    Menu menu = new Menu();
    menu.setLocation(0, 0);
    setSize(1012, 785);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    getLayeredPane().add(menu, new Integer(0));
    menu.repaint();
    setResizable(false);
    setVisible(true);
  }

  public void disposeMenuFrame() {
    if (isTrue) {
      this.dispose();
    }
    this.isTrue = false;
  }

}

