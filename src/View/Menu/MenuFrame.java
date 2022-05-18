package View.Menu;

import javax.swing.*;

public class MenuFrame extends JFrame {
    private static final int WINDOW_WIDTH = 1012;
    private static final int WINDOW_HEIGHT = 785;

    boolean isMenuFrame = true;

    public MenuFrame() {
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setLocation(0, 0);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getLayeredPane().add(menuPanel, new Integer(0));
        menuPanel.repaint();
        setResizable(false);
        setVisible(true);
    }

    public void disposeMenuFrame() {
        if(isMenuFrame) {
            dispose();
        }
        isMenuFrame = false;
    }
}
