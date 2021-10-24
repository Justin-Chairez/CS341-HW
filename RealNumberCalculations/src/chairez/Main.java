package chairez;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main {

	//DATA MEMBERS
	private JFrame frame;
	private JFileChooser fileChooser;
	private JButton btnSelectFileHere;
	private JTextField textFieldNums;
	private JTextField textFieldMean;
	private JTextField textFieldStdDev;
	private JButton btnCalculate;
	
	private Scanner numScanner;
	private LinkedList numsLL;

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
		
		/**
		 * On button click opens a JFileChooser
		 * If a file was clicked, the file is read and valid integers are put into the LinkedList
		 * If an error occurs, an ouput will be given
		 */
		btnSelectFileHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Initalizes a JFileChooser object
				fileChooser = new JFileChooser();
				//Initalize a LinkedList object
				numsLL = new LinkedList();

				
				//RESPONSE CAN BE 0 OR 1. 0 MEANS A FILE WAS CHOOSEN, WHILE 1 MEANS IT WAS CANCELLED, CLOSED, OR NOT CHOOSEN
				int response = fileChooser.showOpenDialog(null);
				//CHEEKS RESPONSE TO SEE IF FILE WAS CHOOSEN & IF SO, CREATES A FILE TO BE READ
				if(response == JFileChooser.APPROVE_OPTION) {
					//Creates a file object to hold the location path of the selected file
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					
					//Tries to read the given file from the provided path
					try { 
						//Initalize a scanner to read the given file
						numScanner = new Scanner(file);
						
						//While there are still lines in the file, keep reading
						while(numScanner.hasNextLine()) {
							
							//If the next line contains a valid int, read it and add it to the numsLL
							if(numScanner.hasNextInt()) {
								int num = numScanner.nextInt();
								numsLL.addNode(num);
							}
							//Else the next line can be a blank or contain invalid input like chars or Strings. Continues onto next line to validate input
							else {
								numScanner.nextLine();
							}
						}
					} 
					//Outputs error if file can not be opended
					catch(IOException IOE)
					{
						System.out.println("File could not be read!");
					}
				}
			}
		});
		
		/**
		 * On Click, calculates the mean of the LinkedList values and the Standard Deviaiton
		 * Converts from Doubles to Strings to display them in text fields
		 */
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				boolean isTrue = numsLL.verifyInput();
				textFieldNums.setText(numsLL.toStringOrdered());
				//Checks if the numsLL has 2 or more inputs. If not, calculations can not be preformed properly so an ouput statement is given
				if(isTrue)
				{
					textFieldMean.setText(numsLL.meanCal().toString());
					textFieldStdDev.setText(numsLL.StdDevCal().toString());
				} 
				//Else input can have calculations conducted properly
				else
				{
					textFieldMean.setText("Input must have at least 2 input");
					textFieldStdDev.setText("Input must have at least 2 input");
				}

			}
		});
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnSelectFileHere = new JButton("Select File Here");
		btnSelectFileHere.setBounds(12, 13, 145, 25);
		frame.getContentPane().add(btnSelectFileHere);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(275, 13, 145, 25);
		frame.getContentPane().add(btnCalculate);
		
		JLabel lblProvidedNumbers = new JLabel("Provided Numbers:");
		lblProvidedNumbers.setBounds(12, 77, 115, 25);
		frame.getContentPane().add(lblProvidedNumbers);
		
		JLabel lblMean = new JLabel("Mean: ");
		lblMean.setBounds(12, 134, 56, 16);
		frame.getContentPane().add(lblMean);
		
		JLabel lblStdDev = new JLabel("Std. Dev: ");
		lblStdDev.setBounds(12, 186, 56, 16);
		frame.getContentPane().add(lblStdDev);
		
		textFieldNums = new JTextField();
		textFieldNums.setBounds(139, 78, 281, 46);
		frame.getContentPane().add(textFieldNums);
		textFieldNums.setColumns(10);
		
		textFieldMean = new JTextField();
		textFieldMean.setBounds(139, 131, 281, 46);
		frame.getContentPane().add(textFieldMean);
		textFieldMean.setColumns(10);
		
		textFieldStdDev = new JTextField();
		textFieldStdDev.setBounds(139, 186, 281, 40);
		frame.getContentPane().add(textFieldStdDev);
		textFieldStdDev.setColumns(10);
	}
}
