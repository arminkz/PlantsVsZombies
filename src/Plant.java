/**
 * Created by Armin on 6/25/2016.
 */
public abstract class Plant {

    public int health = 200;

    public int x;
    public int y;

    public GamePanel gp;


    public Plant(GamePanel parent,int x,int y){
        this.x = x;
        this.y = y;
        gp = parent;
    }

    public void stop(){}

}
