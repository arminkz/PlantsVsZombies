package View;

import java.awt.*;

public abstract class View {
    protected Image plantImage;

    public View() {
        this.plantImage = getImage();
    }

    public void draw(int x, int y, Graphics graphics) {
        graphics.drawImage(this.plantImage, x, y, null);
    }

    protected abstract Image getImage();
}
