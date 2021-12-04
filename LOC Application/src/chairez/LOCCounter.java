package chairez;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class LOCCounter {
	
	private Scanner userFile;
	private Map<String, Integer> keywordsTreeMap;
	private Integer linesOfCodeCount;
	
	public LOCCounter(Scanner fileScan)
	{
		userFile = fileScan;
		keywordsTreeMap = new TreeMap<>();
		linesOfCodeCount = 0;
	}
	
	
	public void countControlStructures()
	{
		//TASK 1: CREATE A LIST OF KEYWORDS AND STORE THEM IN A SET
		String[] keywords = {"public", "if", "catch", "import", "for", "while"};
		Set <String> keywordSet = new HashSet<>(Arrays.asList(keywords));
					
		//TASK 3: BUILD THE TREEMAP. ADD A KEYWORD AND/OR INCREMENT THE COUNT
		while (userFile.hasNext())
		{
			//SUBTASK: GRAB A WORD FROM THE FILE
			String word = userFile.next();
			
			if (keywordSet.contains(word))
			{
				//SCENARIO 1: THE KEYWORD IS NOT YET ON THE TREEMAP
				if (!keywordsTreeMap.containsKey(word)) 
				{
					keywordsTreeMap.put(word, 1);
				}
				else 
				{
					int value = keywordsTreeMap.get(word);
					value++;
					keywordsTreeMap.put(word,value);
				}
			}
		}
	}
	

	public void countTotalLines()
	{		
		String currentCodeLine = "";
		
		while(userFile.hasNextLine())
		{
			currentCodeLine = userFile.nextLine();
			System.out.println("Test: " + currentCodeLine);
			currentCodeLine = currentCodeLine.trim();
			
			//ENSURES BLANK LINES ARE NOT BEING READ
			if(currentCodeLine.length() > 0)
			{
				//CASE 1: NORMAL ONE LINE COMMENT (//)
				if(!currentCodeLine.equals("//"))
				{
					linesOfCodeCount++;
				}
			}
		}
	}
	
	public String toString()
	{
		String outputTable = "";
		outputTable += "Total lines of code: " + linesOfCodeCount;
		for(Map.Entry<String, Integer> entry : keywordsTreeMap.entrySet() )
		{
			outputTable += "\n" + entry.getKey() + " statement: " + entry.getValue();
		}
		
		return outputTable;
		
	}
	
	
	
	public ArrayList<String> TestHelper(Scanner fileScan)
	{
		//TASK 1: CREATE A STACK TO IDENTIFY MCSs
		Stack <String> myStack = new Stack<String>();
		//TASK 2: CREATE VARAIBLES TO HOLD CURRENT AND PREVIOUS INSTRCUTIONS
		String currentCodeLine = "";
		String previousCodeLine = "";
		//TASK 3: CREAE AN ARRAYLIST TO STORE ALL THE MCSs
		ArrayList<String> list = new ArrayList<String>();
		//TASL 4: SCANE THE CODE LINE BY LINE. PUSH IF { POP IF }
		while (fileScan.hasNextLine()) 
		{
			currentCodeLine = fileScan.nextLine();
			//System.out.println("Current Code Line: " + currentCodeLine);
			currentCodeLine = currentCodeLine.trim();
			
			//IS IT A CODELINE
			if(currentCodeLine.length() > 0)
			{
				//SCENARIO 1: JUST A REGULAR LINE 
				if(!currentCodeLine.equals("{") && !currentCodeLine.equals("}"))
				{
					previousCodeLine = currentCodeLine;
				}
				//SCENARIO 2: { INDICATES MCSs
				else if(currentCodeLine.equals("{"))
				{
					myStack.push(previousCodeLine.substring(0, previousCodeLine.indexOf(' ')));
				}
				//SCENARIO 3: } INDICATES COMPLETION OF MCSs
				else 
				{
					list.add(0, myStack.pop());
				}
			}

		}
		
		return list;
	}
	
	public void Test()
	{		
		Scanner tempFile = userFile;
		Set <String> keywordSet = new HashSet<>(TestHelper(tempFile));
		System.out.println(keywordSet);
		//TASK 3: BUILD THE TREEMAP. ADD A KEYWORD AND/OR INCREMENT THE COUNT
		while (userFile.hasNext())
		{
			//SUBTASK: GRAB A WORD FROM THE FILE
			String word = userFile.next();
			
			if (keywordSet.contains(word))
			{
				//SCENARIO 1: THE KEYWORD IS NOT YET ON THE TREEMAP
				if (!keywordsTreeMap.containsKey(word)) 
				{
					keywordsTreeMap.put(word, 1);
				}
				else 
				{
					int value = keywordsTreeMap.get(word);
					value++;
					keywordsTreeMap.put(word,value);
				}
			}
		}
	}
	

}
