import java.awt.Component;
import java.awt.Graphics;
import java.util.List;

import javax.swing.Icon;

public abstract class GameObject {
	
	//DATA MEMBERS
	private int x;
	private int y;
	private int velocity;
	private int direction;
	
	
	protected Icon myImage;
	protected List<Icon> imageList;

	public GameObject(int x, int y) {
		this.x = x;
		this.y = y;
		velocity = 1;
	}
	
	public void draw(Component c, Graphics g)
	{
		myImage.paintIcon(c, g, x, y);
	}
	
	//ABSTRACT METHOD: MOVEMENT OF THE GAME OBJECT
	public abstract void move (Canvas c);

	//SETTERS AND GETTERS
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
		
}
