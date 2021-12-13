import java.awt.event.KeyEvent;

/**
 * An adapter that changes the capabilities of the Move, setImage, and KeyPressed method of Type A GameObject
 * Allows for the left and right movement, as well as the original movement of Up & Down from Type A GameObject
 * @author jtcha
 *
 */
public interface BAdapterSB 
{
	public void move(Canvas c);
	public void setImage();
	public void keyPressed(KeyEvent e);
}
	