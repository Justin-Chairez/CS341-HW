package chairez;

public class Node {
	
	private Integer num;
	private Node next;
	private Node prev;
	
	/**
	 * 
	 * @param n Inserts an int at the head of the LinkedList. Connecting nodes are considered null with initial declaration
	 */
	public Node(int n)
	{
		num = n;
		next = null;
		prev = null;
	}

	/**
	 * 
	 * @param n Sets the current node's value to argument's int
	 */
	public void setNum(int n)
	{
		num = n;
	}
	
	/**
	 * 
	 * @return Returns the value of the current Node
	 */
	public Integer getNum()
	{
		return num;
	}
	
	/**
	 * 
	 * @param n Sets the previous Node from the current location equal to the provided argument
	 */
	public void setPrev(Node p)
	{
		prev = p;
	}
	
	/**
	 * 
	 * @return Returns the next Node from the current location
	 */
	public Node getNext()
	{
		return next;
	}
	
	/**
	 * 
	 * @param n Sets the next Node from the current location equal to the provided argument
	 */
	public void setNext(Node n)
	{
		next = n;
	}
	
	/**
	 * 
	 * @return Returns the previous Node from the current location
	 */
	public Node getPrev()
	{
		return prev;
	}
	
	

}
