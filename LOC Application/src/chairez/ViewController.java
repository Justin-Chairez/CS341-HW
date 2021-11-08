package chairez;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ViewController 
{

	private JButton btnLOC;
	private JFileChooser fileChooser;
	private JButton btnSelectFileHere;
	private JFrame frame;
	private JTextArea textArea;

	private Scanner fileScan;
	private LOCCounter2 checker;
	
	/**
	 * Launch the application.
	 */
	public static void main (String[] args) 
	{
		EventQueue.invokeLater (new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ViewController window = new ViewController();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	/**
	 * Create the application.
	 */
	public ViewController() 
	{
		initialize();
		/**
		 * On button click opens a JFileChooser
		 * When a file was clicked, the file is read and valid integers are put into the LinkedList
		 * An ouput will be given, if an error occurs
		 */
		btnSelectFileHere.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Initalizes a JFileChooser object
				fileChooser = new JFileChooser();
				//Initalize a LOC2 Object
				checker = new LOCCounter2();
				
				int response = fileChooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION)
				{
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					
					try 
					{
						fileScan = new Scanner(file);			
						checker.addFile(fileScan);
					}
					catch (FileNotFoundException e) 
					{
						System.out.println("Error - The file is not found");
					}
					finally 
					{
						if (fileScan != null) 
						{
							fileScan.close();
						}
					}
				}
			}
		}
		);
		
		btnLOC.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				textArea.setText(checker.linesCount() + checker.countMCS());
			}
		}
		);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnSelectFileHere = new JButton("Select File Here");
		btnSelectFileHere.setBounds(12, 26, 129, 25);
		frame.getContentPane().add(btnSelectFileHere);
		
		btnLOC= new JButton("Calculate LOC");
		btnLOC.setBounds(265, 26, 129, 25);
		frame.getContentPane().add(btnLOC);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(12, 97, 56, 16);
		frame.getContentPane().add(lblResults);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(12, 141, 408, 99);
		frame.getContentPane().add(textArea);
	}
}
