package Lane.model;

import Pea.model.Pea;
import zombie.model.Zombie;

import java.util.ArrayList;


/**
 * gamePanel에서 식물들이 심겨져 있는 Lane의 모델이 담겨져 있을 필요가 없다.
 * 따로 class를 만들어서 gamePanelClass의 cohesion을 높였다.
 * Lane 클래스도 singletone으로 만들었다.
 */
public class Lane {
    private static Lane lane = null;
    private ArrayList<ArrayList<Zombie>> laneZombies;
    private ArrayList<ArrayList<Pea>> lanePeas;

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
}
