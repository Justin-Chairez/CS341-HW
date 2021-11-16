
public class Main {
	
	public static void main(String[] args) 
	{
		//DECLARE DATA SETS
		double[] hoursData = {2.0, 3.0, 5.0, 7.0, 9.0, 11.0, 14.0};
		double[] bugData = {4.0, 5.0, 7.0, 10.0, 15.0, 20.0, 30.0};
		
		//DECLARE A LINEAR REGRESSION OBJECT
		LinearRegression linearReg = new LinearRegression(hoursData, bugData);
		
		
		int predictedBugs = linearReg.predictForValue(20);
		System.out.println("There will be " + predictedBugs + "  predicated bugs for 20 hours of coding");
		
	}

}
