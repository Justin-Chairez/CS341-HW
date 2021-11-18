import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.Icon;

public class Type_D_GameObject extends GameObject implements KeyListener {
	
	public Type_D_GameObject(int x, int y) {
		super(x,y);
		setDirection(Direction.NONE);
		
		imageList - new LinkedList<Icon>();
		imagesList.add(new ImageIcon("images/Type_D_Up.png"));
	}

}
