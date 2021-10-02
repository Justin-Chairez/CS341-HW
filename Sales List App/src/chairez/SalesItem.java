package chairez;

/**
 * 
 * @author Justin Chairez
 * @version 1.0
 * The SalesItem class represents a Sales item which has a name, price, and quantity. There are get methods for each part of the item and a toString() method to print out each SalesItem
 *
 */
public class SalesItem {
	
	//DATA MEMBERS
	private String name;
	private double price;
	private int quantity;
	
	/**
	 * Constructor for making a SalesItem object
	 * @param n Takes in the item's name as a String
	 * @param p Takes in the item's price as a String and converts it to a double
	 * @param q Takes in the item's quantity as a String and converts it to an int
	 */
	public SalesItem(String n, String p, String q)
	{
		name = n;
		price = Double.parseDouble(p);
		quantity = Integer.parseInt(q);
	}
	
	/**
	 * 
	 * @return Returns the name of the SalesItem as a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return Returns the price of the SalesItem as a double
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @return Returns the quanityt of the SalesItem as an int
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * @return Returns the SalesItem's name, price and quantity as a one line String
	 */
	public String toString()
	{
		String itemString = "";
		itemString = name + "\t" + " $" + price + "\t" + quantity;
		return itemString;
	}
}
