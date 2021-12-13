import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Type C GameObject with restricted movement, going only LEFT and RIGHT
 * Moves according to the key pressed when highlighted, and moves automatically when not highlighted
 * @author jtcha
 *
 */
public class Type_C_GameObject extends GameObject implements KeyListener {

	/**
	 * Constructor for the C GameObject. Creates imageList for the Object and calls the Super constructor from GameObject
	 * @param x X cordinate for where to place on the Canvas
	 * @param y Y Coordinate for where to place on the Canvas
	 */
	public Type_C_GameObject(int x, int y) {

		super(x, y);
		setDirection(Direction.NONE);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_C_Left.png"));
		imageList.add(new ImageIcon("images/Type_C_Right.png"));
	}

	/**
	 * Allows for the GameObject to move left and right, under the user's control when the object is highlighted
	 * Moves the object automatically when not highlighted
	 * @param C Takes in the Canvas Object in wich to draw the image
	 */
	public void move(Canvas c) {
		Icon icon = getCurrentImage();

		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int canvasHeight = (int) c.getSize().getHeight();
		int canvasWidth = (int) c.getSize().getWidth();

		if (getHighLighted()) {
			switch (getDirection()) {
			case Direction.LEFT:
				setX(getX() + getVelocity());
				if (getX() + iconWidth > canvasWidth) {
					setX((int) (canvasWidth - iconWidth));
					//setDirection(Direction.RIGHT);
				}
				break;
			case Direction.RIGHT:
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
					//setDirection(Direction.LEFT);
				}
				break;
			default:
				break;
			}
		} else {
			if (getDirection() == Direction.LEFT) {
				setX(getX() + getVelocity());
				if (getX() + iconWidth > canvasWidth) {
					setX((int) (canvasWidth - iconWidth));
					setDirection(Direction.RIGHT);
				}
			} else {
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
					setDirection(Direction.LEFT);
				}
			}
		}
	}

	/**
	 * Changes the image when a the object direction changes
	 */
	public void setImage() {
		switch (getDirection()) {
		case Direction.NONE:
			break;
		case Direction.LEFT:
			currentImage = 0;
			break;
		case Direction.RIGHT:
			currentImage = 1;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
	
	/**
	 * Wwhen the object becomes highlighted, only moves the object when a key is pressed
	 * @param e Keeps track of what key was released
	 */
	public void keyReleased(KeyEvent e) {
		if (getHighLighted()) {
			if (e.getKeyCode() != KeyEvent.VK_TAB) {
				setDirection(Direction.NONE);
			}
		}
	}

	/**
	 * Stops the image where it is at when it becomes highighted and moves in the direction of whatever key is pressed
	 * @param e Keeps track of what key was released
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setDirection(Direction.NONE);
		}
		if (getHighLighted()) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.RIGHT);
			}
		}
	}
	
	/**
	 * Draws a red highlight around the current image of the choosen object
	 * @param C Takes in a component
	 * @param Takes in a graphic
	 */
	@Override
	public void highlight(Component c, Graphics g) {
		Icon icon = imageList.get(currentImage);
		int height = icon.getIconHeight();
		int width = icon.getIconWidth();
		
		g.setColor(Color.red);
		g.draw3DRect( getX(), getY(), width, height, true);
				
	}
}
