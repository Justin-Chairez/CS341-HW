import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_A_GameObject extends GameObject implements KeyListener {

	public Type_A_GameObject(int x, int y) {
		super(x, y);
		setDirection(Direction.NONE);

		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_A_Up.png"));
		imageList.add(new ImageIcon("images/Type_A_Down.png"));
	}

	/**
	 * Moves the GameObject Left and Right
	 * Uses switch statements for KeyPressed Movements
	 * Uses If Statements to move automatically, according to the timer in  Canvas.java
	 */
	public void move(Canvas c) {
		Icon icon = getCurrentImage();

		int iconHeight = icon.getIconHeight();
		int canvasHeight = (int) c.getSize().getHeight();

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
					//setDirection(Direction.UP);
				}
				break;
			default:
				break;
			}
		} 
		else {
			if (getDirection() == Direction.UP) {
				setY(getY() - getVelocity());
				if (getY() < 0) {
					setY(0);
					setDirection(Direction.DOWN);
				}
			} else {
				setY(getY() + getVelocity());
				if (getY() + iconHeight > canvasHeight) {
					setY((int) (canvasHeight - iconHeight));
					setDirection(Direction.UP);
				}
			}
		}
		

	}

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
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {
		if (getHighLighted()) {
			if (e.getKeyCode() != KeyEvent.VK_TAB) {
				setDirection(Direction.NONE);
			}
		}
	}

	/**
	 * When Tab is pressed the object stops
	 * When Up is pressed, the object begins to move upwards until limit
	 * When the Down is pressed, the object begins to move downwards until limit
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
		}
	}
	
	/**
	 * Draws a red highlight around the current image of the choosen object
	 * @param C Takes in a component
	 * @param Takes in a graphic
	 */
	@Override
	public void highlight (Component c, Graphics g) {
		Icon icon = imageList.get(currentImage);
		int height = icon.getIconHeight();
		int width = icon.getIconWidth();
		
		g.setColor(Color.red);
		g.draw3DRect( getX(), getY(), width, height, true);
				
	}

}
