package Model.Plant;

public class PlantFactory {
    static private PlantFactory plantFactory;
    private PlantFactory() {}

    public static PlantFactory getInstance() {
        if(plantFactory == null)
            plantFactory = new PlantFactory();
        return plantFactory;
    }

    public static Plant getPlant(String plantID, int x, int y) {
        Plant plant = null;
        switch(plantID) {
            case "Sunflower":
                plant = new Sunflower(x, y);
                break;
            case "NormalPeashooter":
                plant = new NormalPeashooter(x, y);
                break;
            case "FreezePeashooter":
                plant = new FreezePeashooter(x, y);
                break;
        }
        return plant;
    }
}
