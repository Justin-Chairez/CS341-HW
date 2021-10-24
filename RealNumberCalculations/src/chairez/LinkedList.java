package chairez;

public class LinkedList {

	//DATA MEMBERS
	private Node head;
	private Node tail;
	
	/**
	 * Constructor for a LinkedList Object
	 */
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	
	/**
	 * Inserts the provided integer into the LinkedList. If there are no values it becomes the head and tail, else it is inserted at the tail
	 * @param n Integer that is to be added to the LinkedList as a new Node
	 */
	public void addNode(int n) {
		//CREATE A TEMP NODE
		Node temp = new Node(n);
		//ADD THE NODE TO THE BACK OF THE LIST
		//SCENARIO 1: THE LIST IS EMPTY
		if(head == null) {
			head = temp;
			tail = temp;
		}
		//SCENARIO 2: THE LIST IS NOT EMPTY
		else {
			tail.setNext(temp);
			temp.setPrev(tail);
			tail = temp;
		}
	}
	
	/**
	 * Returns a String of all the ints found in the provided file, in the order they were read
	 * @return Returns a String provided nums in given order
	 */
	public String toStringOrdered() {
		Node travelPointer = head;
		String ordered =  "";
		
		while( travelPointer != null ) {
			ordered += travelPointer.getNum().toString() + " ";
			travelPointer = travelPointer.getNext();
		}
		return ordered;
	}
	
	
	/**
	 * Transverses through the LinkedList keeping track of how many values and adding values together. Calculates mean as a double by dividing total by count and returns the results
	 * @return Returns a Double of the calculated LinkedList's Mean
	 */
	public Double meanCal() {
		Node travelPointer = head;
		double count = 0;
		int total = 0;
		double mean = 0.0;
		
		while( travelPointer != null ) {
			total += travelPointer.getNum();
			count++;
			travelPointer = travelPointer.getNext();
		}
		mean = (total/count);
		return mean;
	}
	
	/**
	 * Transverses through the LinkedList, subtracting the current location's value from the mean of the LinkedList. MeanCal() is called to find the mean. Each difference is then squared and added to a running total. Total is divided by total count of numbers and result is square rooted. Returns a double of the rest
	 * @return Returns a Double of the calculated LinkedList' StdDev
	 */
	public Double StdDevCal() {
		Node travelPointer = head;
		double StdDev = 0.0;
		double meanInt = meanCal();
		int StdMeanTotal = 0;
		double count = 0.0;
		
		while (travelPointer != null) {
			StdMeanTotal += Math.pow((travelPointer.getNum()-meanInt),2);
			count++;
			travelPointer = travelPointer.getNext();
		}
		
		StdDev = Math.sqrt(StdMeanTotal/count);
		return StdDev;
				
	}
	
	public Boolean verifyInput() {
		if(head == null || head == tail)
		{
			return false;
		} else {
			return true;
		}
	}
}
