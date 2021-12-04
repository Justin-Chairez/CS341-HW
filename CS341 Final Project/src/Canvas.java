import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Canvas extends JComponent implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	
	//DATA MEMBERS: WINDOW, GAME LOOP, LIST OF GAME OBJECTS
	private JFrame frame;
	private Timer gameLoopTimer;
	private List <GameObject> gameObjectList;
	private int highlighted = 0;
	
	//CONSTRUCTOR
	public Canvas() {
		//TASK 1: CREATE THE LIST OF GAME OBJECTS
		gameObjectList = new LinkedList<GameObject>();
		
		
		//TASK 2: CREATE THE WINDOW (THE CANVAS)
		frame = new JFrame("FinalProject Experiment");
		frame.setSize(800,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		//TASK 3: CONSTRUCT A TIMER AND START IT	
		gameLoopTimer = new Timer(25,this);
		gameLoopTimer.start();
		
		setFocusTraversalKeysEnabled(false);
		addKeyListener((KeyListener) this);
		
		//TASK 4: MAKE THE WINDOW VISIBLE
		frame.setVisible(true);
	}
	
	//METHOD: ADDING GAME OBJECT
	public synchronized void addGameObject(GameObject gObject) {
		gameObjectList.add(gObject);
	}
	
	//METHOD PAINT
	public synchronized void paint(Graphics g) {
		for (GameObject gObject: gameObjectList) 
		{
			gObject.draw(this, g);
		}
	}
	
	//METHODS WE NEED
	//Canvas must implement the inherited abstract method
	//ActionListener.actionPerformed
	public synchronized void actionPerformed(ActionEvent e) {
		//task: loop through all the game objects and display them
		for(GameObject gameObject: gameObjectList)
		{
			gameObject.move(this);
			gameObject.setImage();
		}
		repaint();
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_TAB) {
			highlighted = highlighted + 1;
			if (highlighted == gameObjectList.size()) {
				highlighted = 0;
			}
		}
		GameObject s = gameObjectList.get(highlighted);
		s.setVelocity(s.getVelocity()+1);
	}
	
}
