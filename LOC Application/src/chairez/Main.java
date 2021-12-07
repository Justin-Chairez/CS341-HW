package chairez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args)
	{
		//TASK 1: CREATE A FILE FOR INPUT
		File file = new File("C:/Users/jtcha/Downloads/setPracticeTestSample.txt");
		//TASK 2: CREATE A SCANNER TO SCAN THE FILE
		Scanner fileScan = null;
		//TASK 3: USE A TRY/CATCH TO OPEN THE FILE AND READ IT. COUNT THE KEYWORDS
		try 
		{
			fileScan = new Scanner(file);			
			LOCCounter2 checker = new LOCCounter2();
			checker.addFile(fileScan);
			System.out.println(checker.linesCount());
			//checker.findMCS();
			System.out.println(checker.countMCS());
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("Error - The file is not found");
		}
		finally 
		{
			if(fileScan != null) 
			{
				fileScan.close();
			}
		}
	}
}
