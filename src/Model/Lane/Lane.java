package Model.Lane;

import Model.Pea.Pea;
import Model.Zombie.Zombie;

import java.util.ArrayList;


/**
 * gamePanel�뿉�꽌 �떇臾쇰뱾�씠 �떖寃⑥졇 �엳�뒗 Lane�쓽 紐⑤뜽�씠 �떞寃⑥졇 �엳�쓣 �븘�슂媛� �뾾�떎.
 * �뵲濡� class瑜� 留뚮뱾�뼱�꽌 gamePanelClass�쓽 cohesion�쓣 �넂���떎.
 * Lane �겢�옒�뒪�룄 singletone�쑝濡� 留뚮뱾�뿀�떎.
 */
public class Lane {
    private static Lane lane = null;
    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Pea>> lanePeas;
    final int NUM_LINE = 5;
    private Lane() {
        initializeLanePeas();
        initializeLaneZombies();
    }

    public static Lane getInstance() {
        if(lane==null){
            lane = new Lane();
        }
        return lane;
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

    private void initializeLanePeas() {
        lanePeas = new ArrayList<>();
        for(int i = 0 ; i < NUM_LINE ; i++) {
            lanePeas.add(new ArrayList<>());
        }


    }

    private void initializeLaneZombies() {
        laneZombies = new ArrayList<>();
        for(int i = 0 ; i < NUM_LINE ; i++) {
            laneZombies.add(new ArrayList<>());
        }
    }
}