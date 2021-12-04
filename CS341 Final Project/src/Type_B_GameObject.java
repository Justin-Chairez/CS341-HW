import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject extends GameObject implements KeyListener,TypeBAdapter {
	
	private Type_C_GameObject objC;
	private Type_A_GameObject objA;
	
	public Type_B_GameObject(int x, int y)
	{
		super(x,y);
		setDirection(Direction.NONE);
		
		imageList = new LinkedList<Icon>();
		imageList.add(new ImageIcon("images/Type_B_Up.png"));
		imageList.add(new ImageIcon("images/Type_B_Down.png"));
		imageList.add(new ImageIcon("images/Type_B_Left.png"));
		imageList.add(new ImageIcon("images/Type_B_Right.png"));
	}

	public void move(Canvas c) {
		switch (getDirection()) 
		{
		case Direction.UP:
			objA.move(c);
			break;
		case Direction.DOWN:
			objA.move(c);			
			break;
		case Direction.LEFT:
			objC.move(c);
			break;
		case Direction.RIGHT:
			objC.move(c);
			break;
		default:
			break;
		}
		
	}

	public void setImage() {
		switch (getDirection()) {
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
	

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			setDirection(Direction.UP);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			setDirection(Direction.DOWN);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setDirection(Direction.LEFT);
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			setDirection(Direction.RIGHT);
		}		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_TAB) {
			setDirection(Direction.NONE);
		}		
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
