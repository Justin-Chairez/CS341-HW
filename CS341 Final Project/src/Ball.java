import javax.swing.ImageIcon;

public class Ball extends GameObject{
	
	//DATA MEMBER
	private int imageWidth;
	
	//CONSTRUCTOR
	public Ball (int x, int y) {
		super(x,y);
		setVelocity(3);
		myImage = new ImageIcon("images/ballRed.png");
		imageWidth = myImage.getIconWidth();
	}
	
	
	//IMPLEMENTS THE ABSTRACT METHOD MOVE
	public void move(Canvas c) {
		//MOVE THE BALL AND CHECK FOR COLLISION
		if (getDirection() == Direction.LEFT) {
			setX(getX() + getVelocity());
			if(getX() + imageWidth > c.getSize().getWidth()) {
				setX( (int) (c.getSize().getWidth() - imageWidth));
				setDirection(Direction.RIGHT);
			}
		}
		else {
			setX(getX() - getVelocity());
			if(getX() < 0) {
				setX(0);
				setDirection(Direction.LEFT);
			}
		}
	}

}
