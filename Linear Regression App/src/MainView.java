import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainView {

	//DATA MEMBERS
	private JFrame frame;
	private JButton btnFileSelection;
	private JTextArea textAreaXValues;
	private JTextArea textAreaYValues;
	private JTextArea textArea;
	private JButton btnCalculateResults;
	private JTextField textFieldPredictValue;


	
	private LinearRegression model;
	private JFileChooser fileChooser;
	private Scanner fileScanner;
	private ArrayList<Double> xValues;
	private ArrayList<Double> yValues;
	private JLabel lblPred;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * Carries out the actions of the button clicks
	 * File selection opens a JFileChooser Window to select and import values from a txt file
	 * Calculate Results uses the provided values and the value being predicted for to calculate the number of bugs expected usign linear regression and outputs results to GUI
	 */
	public MainView() {
		initialize();
		
		btnFileSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Initalizes a JFileChooser object
				fileChooser = new JFileChooser();

				
				//RESPONSE CAN BE 0 OR 1. 0 MEANS A FILE WAS CHOOSEN, WHILE 1 MEANS IT WAS CANCELLED, CLOSED, OR NOT CHOOSEN
				int response = fileChooser.showOpenDialog(null);
				//CHEEKS RESPONSE TO SEE IF FILE WAS CHOOSEN & IF SO, CREATES A FILE TO BE READ
				if(response == JFileChooser.APPROVE_OPTION) {
					//Creates a file object to hold the location path of the selected file
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					
					//Tries to read the given file from the provided path
					try { 
						//Initalize a scanner to read the given file
						fileScanner = new Scanner(file);
						
						//Reinitalize the lists everytime a file is choosen
						xValues = new ArrayList<Double>();
						yValues = new ArrayList<Double>();

						
						//While there are still lines in the file, keep reading
						while(fileScanner.hasNextLine()) 
						{
							
							//If the next line contains a valid int, read it and add it to temporary lists
							if(fileScanner.hasNextInt()) {
								xValues.add((double) fileScanner.nextInt());
								yValues.add((double) fileScanner.nextInt());
							}
							
							//Else the next line can be a blank or contain invalid input like chars or Strings. Continues onto next line to validate input
							else 
							{
								fileScanner.nextLine();
							}
						}
						
						//Adds data to the model
						model = new LinearRegression(xValues,yValues);
						
						//Adds the values to the GUI for the user to see
						textAreaXValues.setText(xValues.toString());
						textAreaYValues.setText(yValues.toString());
					} 
					//Outputs error if file can not be opended
					catch(IOException IOE)
					{
						System.out.println("File could not be read!");
					}
				}
			}
		});
		
		btnCalculateResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Creates a temp variable to store the results of the calculations
				Integer predictedValue = model.predictForValue(Integer.parseInt(textFieldPredictValue.getText()));
				//Displays the results in the GUI
				textArea.setText(predictedValue.toString() + " were predicted for " + textFieldPredictValue.getText() + " hours of code");
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
		
		JLabel lblLinearRegressionBug = new JLabel("Linear Regression Bug Prediciton");
		lblLinearRegressionBug.setBounds(124, 13, 194, 26);
		frame.getContentPane().add(lblLinearRegressionBug);
		
		btnFileSelection = new JButton("File Selection");
		btnFileSelection.setBounds(28, 45, 137, 25);
		frame.getContentPane().add(btnFileSelection);
		
		JLabel lblTimeValues = new JLabel("Time Values:");
		lblTimeValues.setBounds(28, 120, 76, 16);
		frame.getContentPane().add(lblTimeValues);
		
		JLabel lblBugValues = new JLabel("Bug Values:");
		lblBugValues.setBounds(38, 161, 68, 16);
		frame.getContentPane().add(lblBugValues);
		
		textAreaXValues = new JTextArea();
		textAreaXValues.setBounds(123, 117, 255, 26);
		frame.getContentPane().add(textAreaXValues);
		
		textAreaYValues = new JTextArea();
		textAreaYValues.setBounds(123, 158, 255, 26);
		frame.getContentPane().add(textAreaYValues);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(60, 207, 56, 16);
		frame.getContentPane().add(lblResults);
		
		textArea = new JTextArea();
		textArea.setBounds(123, 204, 255, 26);
		frame.getContentPane().add(textArea);
		
		btnCalculateResults = new JButton("Calculate Results");
		btnCalculateResults.setBounds(28, 82, 137, 25);
		frame.getContentPane().add(btnCalculateResults);
		
		lblPred = new JLabel("Hours of coding expected:");
		lblPred.setBounds(169, 66, 155, 16);
		frame.getContentPane().add(lblPred);
		
		textFieldPredictValue = new JTextField();
		textFieldPredictValue.setBounds(321, 63, 56, 22);
		frame.getContentPane().add(textFieldPredictValue);
		textFieldPredictValue.setColumns(10);
		
	}
}
