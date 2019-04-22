import java.awt.event.MouseEvent;
import java.util.ArrayList;

public interface GamePanel {

	int getSunScore();

	void setSunScore(int sunScore);

	void mouseDragged(MouseEvent e);

	void mouseMoved(MouseEvent e);

	GameWindow.PlantType getActivePlantingBrush();

	void setActivePlantingBrush(GameWindow.PlantType activePlantingBrush);

	ArrayList<ArrayList<Zombie>> getLaneZombies();

	void setLaneZombies(ArrayList<ArrayList<Zombie>> laneZombies);

	ArrayList<ArrayList<Pea>> getLanePeas();

	void setLanePeas(ArrayList<ArrayList<Pea>> lanePeas);

	ArrayList<Sun> getActiveSuns();

	void setActiveSuns(ArrayList<Sun> activeSuns);

	Collider[] getColliders();

	void setColliders(Collider[] colliders);

}