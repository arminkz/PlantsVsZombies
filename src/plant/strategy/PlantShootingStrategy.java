package plant.strategy;

import Lane.model.Lane;

public interface PlantShootingStrategy {
    public void shoot(int x, int y, Lane lanes);
}
