
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samsung
 */
public class Wallnut extends Plant {
    
    Image image = new ImageIcon(this.getClass().getResource("images/plants/wallnut.png")).getImage();
    
    public Wallnut(GamePanel parent, int x, int y) {
        super(parent, x, y);
        super.image = this.image;
        super.setHealth(1000);
    }
    
}
