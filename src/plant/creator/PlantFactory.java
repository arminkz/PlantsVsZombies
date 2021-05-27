package plant.creator;

import Game.view.GameWindow;
import plant.model.FreezePeashooter;
import plant.model.Peashooter;
import plant.model.Plant;
import plant.model.Sunflower;

public class PlantFactory {
    static private PlantFactory plantFactory = null;
    private PlantFactory(){};

    static public PlantFactory getInstance(){
        if(plantFactory == null){
            plantFactory = new PlantFactory();
        }
        return plantFactory;
    }
    static public Plant getPlant(GameWindow.PlantType plantType, int XPosition, int YPosition){
        Plant plant = null;
        switch (plantType) {
            case Sunflower:
                plant = new Sunflower(XPosition,YPosition);
                break;
            case Peashooter:
                plant = new Peashooter(XPosition, YPosition);
                break;
            case FreezePeashooter:
                plant = new FreezePeashooter(XPosition,YPosition);
                break;
        }
        return plant;
    }
}
