package chairez;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class LOCCounter2 
{
	
	//DATA MEMBER
	private ArrayList<String> fileText;
	
	/**
	 * Constructor for new LOC2 Objects
	 */
	public LOCCounter2()
	{
		fileText = new ArrayList<String>();
	}
	
	/**
	 * Transverses through the choosen file and adds the context to a String ArrayList to enact custom methods on
	 * @param fileScan Adds the context of a file to the LOC2 Object
	 */
	public void addFile(Scanner fileScan)
	{
		while (fileScan.hasNextLine())
		{
			fileText.add(fileScan.nextLine());
		}
	}
	
	/**
	 * Counts all lines in the file that are not blank and comment lines. Three variations of comments were accounted for
	 * @return Returns a String statement of total lines of code
	 */
	public String linesCount()
	{
		Integer linesOfCode = 0;
		for (int i = 0; i < fileText.size(); i++)
		{
			String line = fileText.get(i).trim();
			String[] lineWords = line.split(" ");
			if (lineWords.length > 0 && !lineWords[0].equals(""))
			{
				if (!lineWords[0].equals("//") && !lineWords[0].equals("/*") && !lineWords[0].equals("/**") && !lineWords[0].equals("*/") && !lineWords[0].equals("*"))
				{
					//System.out.println(lineWords[0]);
					linesOfCode++;
				}
			}
		}
		String total = linesOfCode.toString();
		return "Total lines of code: " + total;
	}
	
	/**
	 * Private helper method to find the main control structures in a file. Finds controls structures by using {} as parameters for determining a MCS
	 * Keeps track of the previous line of code and current line of code to define what type of control structure is.
	 * Uses stacks to account for nested MCS
	 * @return 
	 */
	private ArrayList<String> findMCS()
	{
		//TASK 1: CREATE A STACK TO IDENTIFY MCSs
		Stack <String> myStack = new Stack<String>();
		
		ArrayList<String> list = new ArrayList<String>();
		
		//TASK 2: CREATE VARAIBLES TO HOLD CURRENT AND PREVIOUS INSTRCUTIONS
		String currentCodeLine = "";
		String previousCodeLine = "";

		//TASL 3: SCANE THE CODE LINE BY LINE. PUSH IF { POP IF }
		for(int i = 0; i < fileText.size(); i++)
		{
			currentCodeLine = fileText.get(i);
			currentCodeLine = currentCodeLine.trim();

			//IS IT A CODELINE
			if (currentCodeLine.length() > 0)
			{
				//SCENARIO 1: JUST A REGULAR LINE 
				if (!currentCodeLine.equals("{") && !currentCodeLine.equals("}"))
				{
					previousCodeLine = currentCodeLine;
				}
				//SCENARIO 2: { INDICATES MCSs
				else if (currentCodeLine.equals("{"))
				{
					String[] previousCodeLineArray = previousCodeLine.split(" ");
					myStack.push(previousCodeLineArray[0]);
				}
				//SCENARIO 3: } INDICATES COMPLETION OF MCSs
				else 
				{
					if (!list.contains(myStack.peek()))
					{
						list.add(0, myStack.pop());
					}
				}
			}

		}
		return list;
	}
	
	/**
	 * Uses just the keyword to count a MCS. Splits each line of the file and looks at just the first word to account for MCS keywords being in comments
	 * @return Returns a table of the MCS and the count of each found
	 */
	public String countMCS ()
	{
		//Calls helper method to find MCS
		Set <String> keywordSet = new HashSet<>(findMCS());
		
		//Creates a Map for MCS and number of times they are found
		Map<String, Integer> keywordsTreeMap = new TreeMap<>();
		
		//Searches through the ArrayList of the file's Strings one line at a time
		for (int i = 0; i < fileText.size(); i++)
		{
			//SUBTASK: GRAB A Line FROM THE FILE
			String line = fileText.get(i).trim();
			
			//Looks at just the first word to account for MCS keywords being in comments
			String[] lineWords = line.split(" ");
			
			if (keywordSet.contains(lineWords[0]))
			{
				//SCENARIO 1: THE KEYWORD IS NOT YET ON THE TREEMAP
				if (!keywordsTreeMap.containsKey(lineWords[0])) 
				{
					keywordsTreeMap.put(lineWords[0], 1);
				}
				
				//SCENARIO 2: ADD COUNT FOR FINDING KEYWORD
				else 
				{
					int value = keywordsTreeMap.get(lineWords[0]);
					value++;
					keywordsTreeMap.put(lineWords[0],value);
				}
			}
		}
		
		//Outputs the Map to a String and returns the String
		String outputTable = "";
		for (Map.Entry<String, Integer> entry : keywordsTreeMap.entrySet() )
		{
			outputTable += "\n" + entry.getKey() + " statement: " + entry.getValue();
		}
		
		return outputTable;
	}
	

}
