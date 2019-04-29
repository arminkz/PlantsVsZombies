import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Armin on 6/25/2016.
 */
public class ColliderActual extends JPanel implements MouseListener, Collider {

    private ActionListener al;

    public ColliderActual() {
        //setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        //setBackground(Color.green);
        setSize(100, 120);
    }

    public Plant assignedPlant;

    /* (non-Javadoc)
	 * @see ColliderInterface#setPlant(Plant)
	 */
    @Override
	public void setPlant(Plant p) {
        assignedPlant = p;
    }

    /* (non-Javadoc)
	 * @see ColliderInterface#removePlant()
	 */
    @Override
	public void removePlant() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    /* (non-Javadoc)
	 * @see ColliderInterface#isInsideCollider(int)
	 */
    @Override
	public boolean isInsideCollider(int tx) {
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    /* (non-Javadoc)
	 * @see ColliderInterface#setAction(java.awt.event.ActionListener)
	 */
    @Override
	public void setAction(ActionListener al) {
        this.al = al;
    }


    /* (non-Javadoc)
	 * @see ColliderInterface#mouseClicked(java.awt.event.MouseEvent)
	 */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /* (non-Javadoc)
	 * @see ColliderInterface#mousePressed(java.awt.event.MouseEvent)
	 */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /* (non-Javadoc)
	 * @see ColliderInterface#mouseReleased(java.awt.event.MouseEvent)
	 */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (al != null) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    /* (non-Javadoc)
	 * @see ColliderInterface#mouseEntered(java.awt.event.MouseEvent)
	 */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /* (non-Javadoc)
	 * @see ColliderInterface#mouseExited(java.awt.event.MouseEvent)
	 */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
