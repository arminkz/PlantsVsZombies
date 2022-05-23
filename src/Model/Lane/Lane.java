package Model.Lane;

import Model.Pea.*;
import java.util.ArrayList;


public class Lane {
    private static Lane lane = null;
    private ArrayList<ArrayList<Pea>> lanePeas;
    final int NUM_LINE = 5;
    private Lane() {
        initializeLanePeas();
    }

    public static Lane getInstance() {
        if(lane==null){
            lane = new Lane();
        }
        return lane;
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

}