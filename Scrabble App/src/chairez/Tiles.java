package chairez;

/**
 * 
 * @author Justin Chairez
 * @version 1.0
 *
 */
public class Tiles{
	
	private String tiles;
	private String permutations;
	
	/**
	 * 
	 * @param letters A String provided to the user and converted into a Tiles Object
	 */
	public Tiles(String letters)
	{
		tiles = letters;
	}
	
	/**
	 * @return Returns all the possible permutations of the given Srabble tiles
	 */
	public String Scramble() 
	{
		permutations = " ";
		permuteTiles(tiles,"");
		return permutations;
		
		/**
		String results = " ";
		results = permuteIM(tiles);
		return results;
		**/
	}
	
	/**
	 * 
	 * @return Validates if the user entered the correct amount of Scrabble tiles. Returns true if conditions are met and false if not
	 */
	public boolean validateInput() {
		if( tiles.length() > 4 || tiles.contains("0") || tiles.contains("1") || tiles.contains("2") || tiles.contains("3") || tiles.contains("4") || tiles.contains("5") || tiles.contains("6") || tiles.contains("7") || tiles.contains("8") || tiles.contains("9")  ){
			return false;
			}
		else
		{
			return true;
		}
	}
	
	/**
	 * 
	 * @param userInput Tiles given by the user, which form a "word"
	 * @param left Start of where a letter will be swapped
	 * @param right End of the Scrabble word
	 * Attempt at recursive way
	 */
	
	public void permute(String userInput, int left, int right)
	{	

		if( left != right )
		{
			for(int i = left; i < right; i++)
			{
				userInput = swap(userInput,left,i+1);
				permutations += userInput + " ";
				permute(userInput,left+1,right);
			}
		}
		
	}
	
	/**
	 * 
	 * @param userInput Takes in the user's tiles to create all possible words
	 * @return Returns a String of all possible words that can be formed with all tiles provide. Iterative method used here
	 * Iterative method of finding all possible words from the given tiles
	 */
	public String permuteIM(String userInput)
	{
		//Combos will store all the possible words from the tiles given
		String combos = "";
		//Acts as a placeholder to compare for later results
		String org = userInput;
		
		for( int i = 0; i < userInput.length(); i++)
		{
			for(int j = 0; j < userInput.length(); j++)
			{
				userInput = swap(userInput,j,i);
				//Makes sure to not include the original "word" in the combinations
				if(!org.equals(userInput)) {
					combos += userInput + " ";
				}
			}
		}
		return combos;
	}
	
	/**
	 * 
	 * @param userTiles A Word that is provided by the user
	 * @param choosen	The output of the current permutation assigned to the output variable
	 * Solution for recursively finding all permutations
	 */
	public void permuteTiles(String userTiles, String choosen)
	{
		if(userTiles.length() == 0)
		{
			permutations += choosen + " ";
		}else {
			for(int i = 0; i < userTiles.length(); i++)
			{
				char c = userTiles.charAt(i);
				choosen += c;
				userTiles = userTiles.substring(0,i) + userTiles.substring(i+1);
				
				permuteTiles(userTiles,choosen);
				
				userTiles = userTiles.substring(0,i) + c + userTiles.substring(i);
				
				choosen = choosen.substring(0,choosen.length()-1);
			}
		}
	}

	

	/**
	 * 
	 * @param inpt Tiles given by the user
	 * @param i First tile to be swapped
	 * @param j Second tile to be swapped
	 * @return Returns a String with the swapped chars
	 */
	public String swap(String inpt, int i, int j)
	{
		String result = "";
		char temp;
		char[] chars = inpt.toCharArray();
		
		temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		
		result = String.valueOf(chars);
		
		return result;
	}

}
