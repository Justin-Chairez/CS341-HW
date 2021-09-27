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
 * @author Justin Chairez
 * @version	1.0
 *
 */

public class Main {

	//DATA MEMBERS
	private JFrame frame;
	private JTextField textFieldInput;
	private JButton btnPasswordCheck;
	private Password userPassword;
	private JTextArea textAreaOutput;

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
		btnPasswordCheck.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	/**
	 * Takes in the user's password and creates a Password Object
	 * Check's the Password's block length and validates it at the same time0
	 */
	public void buildOutput()
	{
		userPassword = new Password(textFieldInput.getText());
		
		if(userPassword.validate() == true)
		{
			textAreaOutput.setText(userPassword.blockLength());
		}
		else
		{
			textAreaOutput.setText("");
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
		
		JLabel lblEnter = new JLabel("Password Strength App");
		lblEnter.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnter.setBounds(34, 30, 359, 37);
		frame.getContentPane().add(lblEnter);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(34, 80, 68, 16);
		frame.getContentPane().add(lblPassword);
		
		textFieldInput = new JTextField();
		textFieldInput.setBounds(114, 77, 262, 22);
		frame.getContentPane().add(textFieldInput);
		textFieldInput.setColumns(10);
		
		btnPasswordCheck = new JButton("Password Check");
		btnPasswordCheck.setBounds(141, 112, 140, 30);
		frame.getContentPane().add(btnPasswordCheck);
		
		textAreaOutput = new JTextArea();
		textAreaOutput.setLineWrap(true);
		textAreaOutput.setBounds(34, 155, 342, 54);
		frame.getContentPane().add(textAreaOutput);
	}
}
