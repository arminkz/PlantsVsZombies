package zombie.producer;

import Game.LevelData;
import Game.view.GamePanel;
import zombie.model.Zombie;

import java.util.Random;

public class ZombieProducer {
    public Zombie createNewZombie(int lane){
        Random rnd = new Random();
        LevelData lvl = new LevelData();
        String[] Level = lvl.LEVEL_CONTENT[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
        int[][] LevelValue = lvl.LEVEL_VALUE[Integer.parseInt(lvl.LEVEL_NUMBER) - 1];
        int t = rnd.nextInt(100);
        Zombie zombie = null;
        for (int i = 0; i < LevelValue.length; i++) {
            if (t >= LevelValue[i][0] && t <= LevelValue[i][1]) {
                zombie = Zombie.getZombie(Level[i],  lane);
            }
        }
        return zombie;
    }
}
