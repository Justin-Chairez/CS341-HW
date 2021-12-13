import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Type D GameObject with movement for any directions. Only move the GameObject when highlighted and a key is pressed
 * @author jtcha
 *
 */
public class Type_D_GameObject extends GameObject implements KeyListener {

	/**
	 * Constructor for the D GameObject. Creates imageList for the Object and calls the Super constructor from GameObject
	 * @param x X cordinate for where to place on the Canvas
	 * @param y Y Coordinate for where to place on the Canvas
	 */
	public Type_D_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.NONE);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_D_Up.png"));
		imageList.add(new ImageIcon("images/Type_D_Down.png"));
		imageList.add(new ImageIcon("images/Type_D_Left.png"));
		imageList.add(new ImageIcon("images/Type_D_Right.png"));
	}

	/**
	 * Allows the GameObject the capability to move any direction when a key is pressed
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
			case Direction.UP:
				setY(getY() - getVelocity());
				if (getY() < 0) {
					setY(0);
					setDirection(Direction.DOWN);
				}
				break;
			case Direction.DOWN:
				setY(getY() + getVelocity());
				if (getY() + iconHeight > canvasHeight) {
					setY((int) (canvasHeight - iconHeight));
					setDirection(Direction.UP);
				}
				break;
			case Direction.LEFT:
				setX(getX() + getVelocity());
				if (getX() + iconWidth > canvasWidth) {
					setX((int) (canvasWidth - iconWidth));
					setDirection(Direction.RIGHT);
				}
				break;
			case Direction.RIGHT:
				setX(getX() - getVelocity());
				if (getX() < 0) {
					setX(0);
					setDirection(Direction.LEFT);
				}
				break;
			default:
				break;
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
		case Direction.UP:
			currentImage = 0;
			break;
		case Direction.DOWN:
			currentImage = 1;
			break;
		case Direction.LEFT:
			currentImage = 2;
			break;
		case Direction.RIGHT:
			currentImage = 3;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	/**
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
	 * Moves the image in the direction associated with the key pressed
	 * @param e Keeps track of what key was released
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setDirection(Direction.NONE);
		}
		if (getHighLighted()) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				setDirection(Direction.UP);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setDirection(Direction.DOWN);
			}
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
