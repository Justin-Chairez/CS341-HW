import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Allows for a Type B GameObject to have the same capabilities as a Type A GameObject, plus additional movement abilities and associated functions
 * Extends the Type A GameObject to refrence this object's functions and implements Adapter for additional capabilities
 * @author jtcha
 *
 */
public class Type_B_GameObject extends Type_A_GameObject implements BAdapter {

	/**
	 * @param a Takes a Type A GameObject from the User
	 * Creates new images to be used specifically for the B GameObject
	 */
	public Type_B_GameObject(Type_A_GameObject a) {
		super(a.getX(), a.getY());
		super.setHighLighted(false);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_B__Up.png"));
		imageList.add(new ImageIcon("images/Type_B_Down.png"));
		imageList.add(new ImageIcon("images/Type_B_Left.png"));
		imageList.add(new ImageIcon("images/Type_B_Right.png"));

	}

	/**
	 * Uses Switch statements to move the Object around the Canvas when highlighted, and if else statements to move the Object on the Canva's timer
	 * @param c Takes in a Canvas Object, to know where the move the object as well as boundaries
	 * Specified by the Adapter Interface, and overrides the move method in A
	 */
	@Override
	public void move(Canvas c) {

		//If the movement is that of Type A's GameObject, pass this to Type A. Else, pass the movement ability to Type B's method that allows for left and right movement
		//If the Object is not highlighted,
		if (getHighLighted()) {
			switch (getDirection()) {
			case (Direction.UP):
				super.move(c);
			case (Direction.DOWN):
				super.move(c);
			default:
				moveLeftRight(c);
			}
		} else {
			automaticMovement(c);
		}
	}

	/**
	 * Moves the Object Left and Right according the Canva's boundaries
	 * @param c Takes in a Canvas Object in order to move the object
	 */
	private void moveLeftRight(Canvas c) {
		Icon icon = getCurrentImage();

		int iconWidth = icon.getIconWidth();
		int canvasWidth = (int) c.getSize().getWidth();

		switch (getDirection()) {
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

	/**
	 * Moves the Object according to the Canva's timer using If Statements
	 * @param c Takes in a Canvas Object as to know where to move the Object
	 */
	public void automaticMovement(Canvas c) {
		Icon icon = getCurrentImage();

		int iconWidth = icon.getIconWidth();
		int canvasWidth = (int) c.getSize().getWidth();

		if (getDirection() == Direction.RIGHT) {
			setX(getX() + getVelocity());
			if (getX() + iconWidth > canvasWidth) {
				setX((int) (canvasWidth - iconWidth));
				this.setDirection(Direction.DOWN);
			}
		} else if (getDirection() == Direction.DOWN) {
			setY(getY() + getVelocity());
			if (getY() + iconWidth > canvasWidth) {
				setY((int) (canvasWidth - iconWidth));
				setDirection(Direction.LEFT);
			}
		} else if (getDirection() == Direction.LEFT) {
			setX(getX() - getVelocity());
			if (getX() < 0) {
				setX(0);
				setDirection(Direction.UP);
			}
		} else {
			setY(getY() - getVelocity());
			if (getY() < 0) {
				setY(0);
				setDirection(Direction.RIGHT);
			}
		}
	}

	/**
	 * Calls Super for Directions recognized in A & Adds LEFT and RIGHT Direction within the else statement to set additional images for Directions
	 * Specified by the Adapter Interface, and overrides the setImage method in A
	 */
	@Override
	public void setImage() {

		if (getDirection() == Direction.NONE || getDirection() == Direction.UP || getDirection() == Direction.DOWN) {
			super.setImage();
		} else {
			if (getDirection() == Direction.LEFT) {
				currentImage = 2;
			} else {
				currentImage = 3;
			}
		}
	}

	/**
	 * Changes the image's direction based on the button that was pressed, only when it is highlighted
	 * @param e Takes notes of the KeyEvent that occurred, to map the button pressed to an action
	 * Specified by the Adapter Interface, and overrides the keyPressed method in A
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setDirection(Direction.NONE);
		}
		if (getHighLighted()) {
			if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_UP) {
				super.keyPressed(e);
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setDirection(Direction.LEFT);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				setDirection(Direction.RIGHT);
			}
		}
	}
}
