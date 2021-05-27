package Game.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

class MenuFrame extends JFrame {
  boolean isMenuFrame = true;

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
    if (isMenuFrame) {
      this.dispose();
    }
    this.isMenuFrame = false;
  }

}
