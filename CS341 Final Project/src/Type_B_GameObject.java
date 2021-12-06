import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Type_B_GameObject implements TypeBAdapter {
	
	private Type_D_GameObject d;
	
	public Type_B_GameObject(Type_D_GameObject d)
	{
		this.d = d;
		
	}

	public void move(Canvas c) {
		Icon icon = d.getCurrentImage();

		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int canvasHeight = (int) c.getSize().getHeight();
		int canvasWidth = (int) c.getSize().getWidth();

		switch (d.getDirection()) {
		case Direction.UP:
			d.setY(d.getY() - d.getVelocity());
			if (d.getY() < 0) {
				d.setY(0);
				d.setDirection(Direction.DOWN);

			}
			break;
		case Direction.DOWN:
			d.setY(d.getY() + d.getVelocity());
			if (d.getX() + iconHeight > canvasHeight) {
				d.setX((int) (canvasHeight - iconHeight));
				d.setDirection(Direction.UP);
			}
			break;
		case Direction.LEFT:
			d.setX(d.getX() + d.getVelocity());
			if (d.getX() + iconWidth > canvasWidth) {
				d.setX((int) (canvasWidth - iconWidth));
				d.setDirection(Direction.RIGHT);
			}
			break;
		case Direction.RIGHT:
			d.setX(d.getX() - d.getVelocity());
			if (d.getX() < 0) {
				d.setX(0);
				d.setDirection(Direction.LEFT);
			}
			break;
		default:
			break;
		}
	}

}
