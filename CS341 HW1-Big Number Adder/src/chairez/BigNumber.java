package chairez;

import java.util.ArrayList;

public class BigNumber {
	
	//Declare an array list of ints, which make up the big number object
	private ArrayList<Integer> num;
	
	//Zero arg constructor
	public BigNumber()
	{
		num= new ArrayList<Integer>();
	}
	
	//return BigNumber object as an array list
	public ArrayList<Integer> getArr() {
		return num;
	}
	
	//Converts JTextField from a string to an int, where it is than added to an array list of any size int
	public void arrayInput(String str) {
		
		//transverses through the JTextField String
		for( int i = 0; i < str.length(); i++) {
			
			//Validates the input a second time
			//Stops reading the String if the input is not a integer
			if(!Character.isDigit(str.charAt(i))) {
				num.clear();
				return;
			}
			
			//else the String must be an int
			int element = Character.getNumericValue(str.charAt(i));//converts each char into an int
			num.add(i, element);//adds each int to the ArrayList
		}
	}
	
	//compares x & y input to determine which needs zero added to it
	public void addZero(BigNumber number) {
		
		if(num.size() > number.getArr().size()) {
			
			while( num.size() > number.getArr().size()) {
				number.getArr().add(0,0);
			}
		} 
		
		else if(number.getArr().size() > num.size()) {
			while (num.size() < number.getArr().size()) {
				num.add(0, 0);
			}
		}
		
	}
	
	//Converts the ArrayList to a String for final output
	//Over rides the default toString() Method
	public String toString() {
		String numString = "";
		
		for( int i = 0; i < num.size(); i++) {
			numString += Integer.toString(num.get(i));
		}
		
		return numString;
	}
	
	//Custom method for adding two BigNumbers together
	//Recieves one bignumber to add to another, with return value being another big number
	public BigNumber add(BigNumber num2) {
		
		BigNumber totalSum = new BigNumber();//Final total of two big numbers
		int sum = 0;//Overflow variable for when an index is larger than 10
		addZero(num2);//assures each number is the same length
		
		//Begins at the end of the array
		for(int i = num.size()-1; i >= 0; i--) {
			
			//Sums each index position together
			//Converts the inputted BigNumber back into an ArrayList for addition process
			sum += num.get(i) + num2.getArr().get(i);
			
			 
			if(sum >= 10) {
				sum = sum % 10; //Leaves the remainder in the current index position
				totalSum.getArr().add(0, sum); //adding the sum of the current index position to the front of the result's array
				
				//accounts for when an additional index position needs to be added
				sum += 10;
				sum = sum/10;
			}
			
			//else the sum must be less than 10 & resets the current sum to continue process
			else {
				totalSum.getArr().add(0, sum);
				sum = 0;
			}
		}
		
		//Makes sure to add an extra index slot for when the last index positon results in a double digit number, i.e 99+1 = 100
		sum += 10;
		if(sum >= 10) {
			sum = sum%10;
			if(sum != 0) {
				totalSum.getArr().add(0, sum);
			}
		}
		
		//returns the sum of the two BigNumbers
		return totalSum;
	}
	
}
