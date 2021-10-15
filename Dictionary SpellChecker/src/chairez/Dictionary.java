package chairez;

import java.util.ArrayList;

public class Dictionary {
	
	//DATA MEMBER
	private WordNode root;
	
	//VARIABLE USED FOR ASSERT TESTING
	private ArrayList<String> userWords;
	
	public Dictionary() {
		root = null;
	}
	
	/**
	 * Adds a given word from the User to BST in alphabetical order. Does not allow duplicates of words.
	 * @param addW Pass in a String from the User to add the BST
	 */
	public void insertWordNode(String addW)
	{
		WordNode temp = new WordNode(addW);
		if(isEmpty())
		{
			root = temp;
		} else{
			insertAtLocation(root,temp);
			assert root != null: "Post-Condition: Root has been moved";
		}
	}
	
	/**
	 * Checks to see if the BST is empty. A helper method for the inserWordNode() method
	 * @return Returns whether the given word is empty or not
	 */
	private Boolean isEmpty()
	{
		return root == null;
	}
	
	/**
	 * Vists each word node and moves left or right dependent on if the word comes before or after the word it is being compared to. If the word already exists, it is not added to the BST.
	 * @param ptr Passes in a copy of the root as a pointer to transverse the tree
	 * @param wordToAdd Passes in the user's word to be placed in alphabetical order
	 */
	private void insertAtLocation(WordNode ptr, WordNode wordToAdd)
	{
		//BEGIN THE SEARCH BY VISITING EACH WORD NODE
		//MOVE LEFT IF GREAT THAN
		// negative: wordToAdd is less than ptr
		// zero: wordToAdd is equal to the ptr
		// positive: wordToAdd is greater than ptr
		//MOVE RIGHT IF LESS THAN
		while (true) {
			//SCENARIO 1: THE WORD ALREADY EXISTS IN THE DICTIONARY
			if(wordToAdd.getWord().compareToIgnoreCase(ptr.getWord()) == 0)
			{
				break;
			}
			
			//assert wordToAdd.getWord().compareToIgnoreCase(ptr.getWord()) != 0: "Duplicates can not exist in the tree";
			
			//SCENARIO 2: TRAVEL TO THE LEFT SIDE
			else if(wordToAdd.getWord().compareToIgnoreCase(ptr.getWord()) < 0)
			{
				if(ptr.getLeftNode() != null)
				{
					ptr = ptr.getLeftNode();
				}
				else {
					ptr.setLeftNode(wordToAdd);
					//System.out.println("Ptr value: " + ptr.getWord());
					//System.out.println("Left node value: " + ptr.getLeftNode().getWord());
					assert ptr.getWord().compareToIgnoreCase(ptr.getLeftNode().getWord()) > 0: "Post-condition: Inserted word is not smaller than it's parent";
					break;
				}
			}

			//SCENARIO 3: TRAVEL TO THE RIGHT SIDE
			else
			{
				if(ptr.getRightNode() != null)
				{
					ptr = ptr.getRightNode();
				}else {
					ptr.setRightNode(wordToAdd);
					assert ptr.getWord().compareToIgnoreCase(ptr.getRightNode().getWord()) < 0: "Post-condition: Inserted word is not larger than it's parent";
					break;
				}
			}
		}
		
	}
	
	/**
	 * 
	 * Creates a temporary root and uses the given String to transverse the tree until the node is found. Takes into account the node may have one child or two, and finds the next order succesor. Also takes into account the word may not exist in the BST. If word does not exist, no changes occur to the BST
	 * @param deleteWord Enter the String of the word that is to be delted from the BST
	 * 
	 */
	public void checkWord(String deleteWord)
	{
		WordNode temp = root;
		assert temp != null: "BST is currently empty";
		temp = deleteIterative(root,deleteWord);
	}
	
	/**
	 * Helper method to interatively transverse the BST to find and replace the word to be deleted
	 * @param tempRoot Passes in a copy of the BST's root to be used as a starting spot
	 * @param deleteWord Passes in a String of the word the user wants delted
	 * @return Returns the new BST with the deleted word replaced by the next alphabetical word in the tree
	 */
	private WordNode deleteIterative(WordNode tempRoot, String deleteWord)
	{
		//CREATE A TEMPORARY POINTER FOR THE ROOT TO TRANSVERSE THROUGH IT'S CHILDREN
		WordNode ptr = tempRoot;
		WordNode pastPtr = null;
		
		//KEEP LOOKING UNTIL NODE IS FOUND
		//STOPS ONLY IF THE WORD DOES NOT EXIST OR IS FOUND
		while(ptr != null && ptr.getWord() != deleteWord)
		{
			pastPtr = ptr;
			if(deleteWord.compareTo(ptr.getWord()) > 0)
			{
				ptr = ptr.getRightNode();
			}
			else
			{
				ptr = ptr.getLeftNode();
			}
		}
		
		//SCENARIO 0: WORD DOES NOT EXIST IN THE TREE
		if( ptr == null )
		{
			return tempRoot;
		}
		
		//CHECK TO SEE IF THE NODE HAS A CHILD
		//SCENARIO 1: HAS AT LEAST ONE CHILD
		if(ptr.getLeftNode() == null || ptr.getRightNode() == null)
		{
			WordNode newCurrLocation = null;
			
			//CHECKS TO SEE IF THE NODE TO BE REMOVED HAS A LEFT CHILD
			//IF NULL, CHILD MUST BE ON THE LEFT
			if(ptr.getLeftNode() == null)
			{
				newCurrLocation = ptr.getRightNode();
			}
			//IF NOT, THE CHILD MUST BE ON THE LEFT SIDE
			else
			{
				newCurrLocation = ptr.getLeftNode();
			}
			
			//CHECKS TO SEE IF THE DELETED WORD IS THE ROOT OF THE BST
			//IF THE ROOT ONLY HAS ONE CHILD, THE NEXT NODE IN ALPHABETICAL ORDER WILL REPLACE IT
			if(pastPtr == null)
			{
				return newCurrLocation;
			}
			
			//REPLACES DELETED WORD WITH IT'S CORRELATING CHILD
			if(ptr == pastPtr.getLeftNode())
			{
				pastPtr.setLeftNode(newCurrLocation);
			}
			else
			{
				pastPtr.setRightNode(newCurrLocation);
			}
			
			//DELETES THE WORD WHICH REPLACED THE PREVIOUSLY DELETED WORD
			ptr = null;
		}
		
		//CHECK TO SEE IF THE NODE HAS TWO CHILDREN
		//SCENARIO 2: NODE TO BE REMOVED HAS TWO CHILDREN
		//HAVE TO MOVE THE NEXT HIGHEST SUCCESOR TO THE REMOVED NODE'S LOCATION
		else {
			
			//assert ptr.getLeftNode() != null && ptr.getRightNode() != null: "Pre-condition: Node to be removed must have two children";
			WordNode parent = null;
			WordNode tempPtr = null;
			
			//FINDS THE NEXT HIGHEST ALPHABETICAL VALUE IN THE BST
			tempPtr = pastPtr.getRightNode();
			while(tempPtr.getLeftNode() != null)
			{
				parent = tempPtr;
				tempPtr = tempPtr.getLeftNode();
			}
			
			//MAKES SURE THE NODE BEING LOOKED AT IS NOT THE ROOT
			//IF NOT, SET THE LEFT NODE OF PARENT EQUAL TO THE RIGHT SIDE
			if( parent != null )
			{
				parent.setLeftNode(tempPtr.getRightNode());
				 
			} 
			
			//IF THE NODE WAS THE ROOT, SET THE RIGHT NODE EQUAL TO THE RIGHT NODE SINCE IT IS THE NEXT HIGHEST VALUE
			else
			{
				tempPtr.setRightNode(tempPtr.getRightNode());
				assert tempPtr.getWord().compareToIgnoreCase(tempPtr.getLeftNode().getWord()) < 0 && tempPtr.getWord().compareToIgnoreCase(tempPtr.getRightNode().getWord()) > 0: "Post condition: Children are not placed correctly";
				
			}
			
			tempPtr.setWord(tempPtr.getWord());
			tempPtr = null;
		}
		
		return tempRoot;
	}

	
	/**
	 * Method for prtining out the Binary Search Tree in Alphabetical Order
	 * Uses a Helper Method called inOrderRecursive to recursively traverse, from Left to Right, and print out each Node
	 */
	public void displayDictionary()
	{
		userWords = new ArrayList<String>();
		
		assert alphabeticalOrder(): "Binary Search Tree is not in Alphabetical Order";
		inOrderRecursive(root);
		//System.out.println("ArrayList Words: " + userWords.toString());

	}

	/**
	 * Helper method for displayDictionary that recursively
	 * @param ptr Passes in the Root node as a ptr to transverse the tree
	 */
	
	private void inOrderRecursive(WordNode ptr)
	{
		if(ptr != null )
		{
			inOrderRecursive(ptr.getLeftNode());
			System.out.println(ptr.getWord());
			userWords.add(ptr.getWord());
			inOrderRecursive(ptr.getRightNode());
		}
	}
	
	/**
	 * Assumes the word to be checked exists in the current Dictionary Values. Iteratively passes through the Dictionary From left to right, until the value is either found or not.
	 * @param addW Passes in the word to be checked as String to compare against the current Dictionary Values
	 * @return Retruns true if the word was found. Returns false if the word was not found or does not exist in the three
	 */
	public Boolean spellCheck(String addW)
	{
		WordNode temp = new WordNode(addW);
		Boolean isFound = insepct(root,temp);
		return isFound;
		
	}
	
	/**
	 * 
	 * @param ptr Passes in a Ptr that is a copy of the BST'S Root Node
	 * @param wordToCheck Passes in the Word that is being SpellChecked
	 * @return Returns True if the Word in the Dictionary was spelled correctly. Returns False if the Word in the Dictionary was Spelled Wrong
	 */
	private Boolean insepct(WordNode ptr, WordNode wordToCheck)
	{
		//BEGIN THE SEARCH BY VISITING EACH WORD NODE
		//MOVE LEFT IF GREAT THAN
		// negative: word to check is less than ptr
		// zero: word to check is equal to the ptr
		//MOVE RIGHT IF LESS THAN
		// positive: word to check is greater than ptr
		while (true) {
			//SCENARIO 1: THE WORD ALREADY EXISTS IN THE DICTIONARY
			if(wordToCheck.getWord().compareToIgnoreCase(ptr.getWord()) == 0)
			{
				return true;
			}
			//SCENARIO 2: TRAVEL TO THE LEFT SIDE
			else if(wordToCheck.getWord().compareToIgnoreCase(ptr.getWord()) < 0)
			{
				if(ptr.getLeftNode() != null)
				{
					ptr = ptr.getLeftNode();
				}
				else {
					return false;
				}
			}
			//SCENARIO 3: TRAVEL TO THE RIGHT SIDE
			else
			{
				if(ptr.getRightNode() != null)
				{
					ptr = ptr.getRightNode();
				}
				else {
					return false;
				}
				//assert (ptr.getRightNode() != null || ptr.getRightNode() != null) && (wordToCheck.getWord().compareToIgnoreCase(ptr.getWord()) == 0): "Word does not exist in the current Dictionary";
			}

		}
	}

	/**
	 * Assert helper method to assure all words in the BST are in alphabetical order
	 * @return Returns true if all words in the BST are in alphabetical order. Else it will return false if a words is found to not be alphabetical order
	 */
	private Boolean alphabeticalOrder()
	{
		Boolean isTrue = true;
		for(int i = 0; i < userWords.size()-1; i++ )
		{
			if( userWords.get(i).compareTo(userWords.get(i+1)) < 0 )
			{
				isTrue = true;
			}
			else
			{
				isTrue = false;
			}
		}
		
		return isTrue;
	}
}
