package chairez;

import java.util.ArrayList;

/**
 * 
 * @author Justin Chairez
 * @version 1.0
 * The SalesSlip class represents an ArrayList of SalesItem objects. There are methods to add, compute the total of all items, and prints out the entire ArrayList as a String
 *
 */

public class SalesSlip {

	//DATA MEMBER
	private ArrayList<SalesItem> itemList;
	
	/**
	 * Constructor for the SalesSlip Item
	 */
	public SalesSlip()
	{
		itemList = new ArrayList<SalesItem>();
	}
	
	/**
	 * Creates a SalesItem object to be added to the ArrayList
	 * @param n Takes in a String of the item's name
	 * @param p Takes in a String of the item's price
	 * @param q Takes in a String of the item's quantity
	 */
	public void addItem(String n, String p, String q)
	{
		SalesItem consumerItem = new SalesItem(n,p,q);
		itemList.add(consumerItem);
	}
	
	/**
	 * ComputeTotal() iterates through the SalesSlip's ArrayList, multiplying the item's price by quantity
	 * @return Returns a Double, representing the total price in dollars
	 */
	public Double computeTotal()
	{
		Double runningTotal = 0.0;
		for( int i = 0; i < itemList.size(); i++ )
		{
			runningTotal += itemList.get(i).getPrice() * itemList.get(i).getQuantity();
		}
		return runningTotal;
	}
	
	/**
	 * toString() Method iterates through the entire SalesSlip, calling SalesItem's toString() method for each new line
	 * @return Returns a String of all SalesItem objects in the SalesSlip
	 */
	public String toString()
	{
		String salesList = "";
		for( int i = 0; i < itemList.size(); i++ )
		{
			salesList += itemList.get(i).toString() + "\n";
		}
		return salesList;
	}
}
