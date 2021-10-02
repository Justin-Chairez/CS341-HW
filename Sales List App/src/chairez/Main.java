package chairez;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

/**
 * 
 * @author Justin Chairez
 * @version 1.0
 * Main Method for adding and calculating a running total of the user's Groceries application
 *
 */
public class Main {

	private JFrame frame;
	private JTextField textFieldItem;
	private JTextField textFieldCost;
	private JTextField textFieldQuantity;
	private JTextField textFieldtotalSales;
	private JButton btnAddItemTo;
	private JTextArea textAreaSalesSlip;
	
	
	//DATA MEMBERS
	private SalesSlip customerSalesList;
	private Integer salesTotal;

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
		customerSalesList = new SalesSlip();
		btnAddItemTo.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				buildOutput();
			}
		});
	}
	
	
	public void buildOutput()
	{
		//ADD ITEM TO THE CUSTOMER'S RUNNING LIST
		customerSalesList.addItem(textFieldItem.getText(), textFieldCost.getText(), textFieldQuantity.getText());
		
		//ADD ITEM TO THE VISUAL
		textAreaSalesSlip.setText(customerSalesList.toString());
		
		//FINDS THE CURRENT TOTAL OF THE CONSUMER'S LIST
		textFieldtotalSales.setText(customerSalesList.computeTotal().toString());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSalesList = new JLabel("Sales List");
		lblSalesList.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblSalesList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesList.setBounds(12, 13, 408, 25);
		frame.getContentPane().add(lblSalesList);
		
		JLabel lblName = new JLabel("Item:");
		lblName.setBounds(22, 51, 56, 16);
		frame.getContentPane().add(lblName);
		
		JLabel lblCost = new JLabel("Cost: $");
		lblCost.setBounds(22, 80, 56, 16);
		frame.getContentPane().add(lblCost);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(22, 109, 56, 16);
		frame.getContentPane().add(lblQuantity);
		
		textFieldItem = new JTextField();
		textFieldItem.setBounds(90, 51, 116, 22);
		frame.getContentPane().add(textFieldItem);
		textFieldItem.setColumns(10);
		
		textFieldCost = new JTextField();
		textFieldCost.setBounds(90, 77, 116, 22);
		frame.getContentPane().add(textFieldCost);
		textFieldCost.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setBounds(90, 106, 116, 22);
		frame.getContentPane().add(textFieldQuantity);
		textFieldQuantity.setColumns(10);
		
		textAreaSalesSlip = new JTextArea();
		textAreaSalesSlip.setBounds(218, 48, 202, 129);
		frame.getContentPane().add(textAreaSalesSlip);
		
		btnAddItemTo = new JButton("Add Item to the Sales List");
		btnAddItemTo.setHorizontalAlignment(SwingConstants.LEADING);
		btnAddItemTo.setBounds(22, 152, 184, 25);
		frame.getContentPane().add(btnAddItemTo);
		
		JLabel lblTotalSales = new JLabel("Total Sales:");
		lblTotalSales.setBounds(218, 212, 71, 16);
		frame.getContentPane().add(lblTotalSales);
		
		textFieldtotalSales = new JTextField();
		textFieldtotalSales.setBounds(304, 209, 116, 22);
		frame.getContentPane().add(textFieldtotalSales);
		textFieldtotalSales.setColumns(10);
	}
}
