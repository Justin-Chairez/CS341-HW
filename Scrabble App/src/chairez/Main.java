package chairez;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * 
 * @author Justin-Chairez
 * @version 1.1
 *
 */

public class Main {

	private JFrame frame;
	private JTextField textFieldInput;
	private JTextArea textOutput;
	private JButton btnScramble;
	private Tiles tiles;
	private String results;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		btnScramble.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				buildOuput();
			}
		});
	}
	
	//Takes in input from the user and returns the results of it
	//Minor change
	public void buildOuput() 
	{
		tiles = new Tiles(textFieldInput.getText());
		if(tiles.validateInput())
		{
			results = tiles.Scramble();
			textOutput.setText(results);
		}else {
			textOutput.setText("Your input is not valid. Please make sure there are not more than 4 tiles and no numbers");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbTitle = new JLabel("Enter 4 tiles to be scrambled");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(37, 13, 339, 50);
		frame.getContentPane().add(lbTitle);
		
		textFieldInput = new JTextField();
		textFieldInput.setBounds(206, 76, 170, 59);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);
		
		btnScramble = new JButton("Scramble");
		btnScramble.setBounds(79, 93, 97, 25);
		frame.getContentPane().add(btnScramble);
		
		textOutput = new JTextArea();
		textOutput.setLineWrap(true);
		textOutput.setBounds(96, 162, 260, 50);
		frame.getContentPane().add(textOutput);
	}
}
