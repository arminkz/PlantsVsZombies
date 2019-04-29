import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public interface Collider {


	
	void setPlant(Plant p);

	void removePlant();

	boolean isInsideCollider(int tx);

	void setAction(ActionListener al);

	void mouseClicked(MouseEvent e);

	void mousePressed(MouseEvent e);

	void mouseReleased(MouseEvent e);

	void mouseEntered(MouseEvent e);

	void mouseExited(MouseEvent e);

}