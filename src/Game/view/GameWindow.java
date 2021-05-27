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

  
  private GamePanel gamePanel;
  static public GameWindow gameWindow;
  private static SunProducer sunProducer;
  private static MenuFrame menuFrame;
  PlantType activePlantingBrush = PlantType.None;

  
  public GameWindow() {
    setSize(1012, 785);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLayout(null);
    setResizable(false);
    setVisible(true);
    initPlantCard();
    sunProducer = new RandomSunProducer(5000);
  }


  
  // Attach Card into Panel 재사용성을 높이기 위해 분리 Parameter : GamePanel , X-coordinate, Image_path
  private void AttachCard(GamePanel gamepanel, int xCoordinate, String imagePath, PlantType plantType) {
    PlantCard newCard =
        new PlantCard(new ImageIcon(this.getClass().getResource(imagePath)).getImage());
    newCard.setLocation(xCoordinate, 8);
    newCard.setAction((ActionEvent e) -> {
      gamepanel.setActivePlantingBrush(plantType);
    });
    getLayeredPane().add(newCard, new Integer(3));
  }

  
  private void initPlantCard() {
    gamePanel = GamePanel.getInstance();
    gamePanel.setLocation(0, 0);
    getLayeredPane().add(gamePanel, new Integer(0));
    AttachCard(gamePanel, 110, "../../images/cards/card_sunflower.png", PlantType.Sunflower);
    AttachCard(gamePanel, 175, "../../images/cards/card_peashooter.png", PlantType.Peashooter);
    AttachCard(gamePanel, 240, "../../images/cards/card_freezepeashooter.png", PlantType.FreezePeashooter);
  }


  
  public static void gameStart() {
    menuFrame.disposeMenuFrame();
    gameWindow = new GameWindow();
    sunProducer.start();
  }

  
  public static void main(String[] args) {
    menuFrame = new MenuFrame();
  }

  
}

