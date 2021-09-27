package chairez;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Main {

	//Main layout elements
	private JFrame frame;
	private JTextField textFieldNum1;
	private JTextField textFieldNum2;
	private JTextField textFieldResults;
	private JLabel lblNum1;
	private JLabel lblNum2;
	private JButton btnAdd;


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

	//Constructor
	public Main() {
		initialize();
		
		//listener event for when the addition button is clicked
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e) {
				addNums();
			}
		});
	}
	
	//adds and validates input of two BigNumbers objects
	public void addNums() {
		
		//Initalize & declare data elements to reset values each time
		BigNumber x = new BigNumber();
		BigNumber y = new BigNumber();
		
		//Pull values from text fields for x & y
		x.arrayInput(textFieldNum1.getText());
		y.arrayInput(textFieldNum2.getText());
		
		//Validates input to make sure each is not empty
		//If x or y is empty, say there is an error
		//Else complete the action 
		if(x.getArr().isEmpty() || y.getArr().isEmpty()) {
			textFieldResults.setText("input error oops");
		}
		
		
		else{
			//Declare & initalize z value here to set each time button is clicked
			BigNumber z = x.add(y);
			textFieldResults.setText(z.toString());
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
		
		lblNum1 = new JLabel("X");
		lblNum1.setBounds(12, 46, 56, 16);
		frame.getContentPane().add(lblNum1);
		
		lblNum2 = new JLabel("Y");
		lblNum2.setBounds(12, 75, 56, 16);
		frame.getContentPane().add(lblNum2);
		
		textFieldNum1 = new JTextField();
		textFieldNum1.setBounds(80, 43, 340, 22);
		frame.getContentPane().add(textFieldNum1);
		textFieldNum1.setColumns(10);
		
		textFieldNum2 = new JTextField();
		textFieldNum2.setBounds(80, 72, 340, 22);
		frame.getContentPane().add(textFieldNum2);
		textFieldNum2.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(184, 117, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		textFieldResults = new JTextField();
		textFieldResults.setBounds(12, 170, 408, 53);
		frame.getContentPane().add(textFieldResults);
		textFieldResults.setColumns(10);
	}

}
