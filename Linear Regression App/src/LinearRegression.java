import java.util.ArrayList;

public class LinearRegression 
{
	//DATA MEMBERS
	private ArrayList<Double> xData;
	private ArrayList<Double> yData;
	
	/*
	 * Constructor for the Linear Regression Model
	 * Takes in two ArrayLists of type Double. HD is representative of hours spent coding, while BD is representative of bugs encountered while coding
	 */
	public LinearRegression(ArrayList<Double> xValues, ArrayList<Double> yValues)
	{
		xData = xValues;
		yData = yValues;
	}
	
	/**
	 * 
	 * @param predictForDependentVariable An int provided by the user, repesentative of the number of coding hours for a future project prediction
	 * @return Returns an Integer representative of the predicated number of bugs, calculated from the given data about previous projects and a predicted number of hours going to be spent on the next
	 */
	public Integer predictForValue (int predictForDependentVariable)
	{
		if(predictForDependentVariable <= 0)
		{
			throw new IllegalStateException("Predict Value Must be greater than 0");
		}
		
		//PRELIMINARY WORK
		// VERIFY THE DATA SETS ARE CORRECT
		if (xData.size() != yData.size())
		{
			throw new IllegalStateException("Datasets must be equal in size.");
		}
		int nValues = xData.size();
		
		//TASK 1: SUM HOUR DATA. SUM BUG DATA
		double sumHourData = 0.0;
		double sumBugData = 0.0;
		
		for(int i = 0; i < nValues; i++)
		{
			sumHourData += xData.get(i);
			sumBugData += yData.get(i);
		}
		
		//TASK 2: COMPUTE THE MEANS OF THE HOUR DATA VALUES AND THE BUG DATA VALUES
		double hourDataBar = sumHourData/nValues;
		double bugDataBar = sumBugData/nValues;
		
		//TASK 3: COMPUTE THE LEAST SQUARES REGRESSION LINE
		// SUBTRACTS THE HOUR MEAN VALUE FROM THE HOUR DATA VALUES AND SQUARE IT
		double xxbar = 0.0;
		double xybar = 0.0;
		for(int i = 0; i < nValues; i++)
		{
			xxbar += (xData.get(i) - hourDataBar) * (xData.get(i) - hourDataBar);
			xybar += (xData.get(i) - hourDataBar) * (yData.get(i) - bugDataBar);
		}
		
		//TASK 4: COMPUTE THE SLOPE AND THE INTERCEPT
		double slope = xybar / xxbar;
		double intercept = bugDataBar - slope * hourDataBar;
		
		//TASK 5: COMPUTE THE PREDICTION
		double predictedBugs = intercept + slope * predictForDependentVariable;
		return (int) predictedBugs;
	}
	
}
