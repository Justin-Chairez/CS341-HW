package chairez;

/**
 * 
 * @author Justin Chairez
 * @version 1.0
 * Represents a Password Strength checker
 */

public class Password {
	
	//Password String where the user's input will be stored
	private String password;
	
	/**
	 * @param userInput Method takes in the user's password as String and creates a Password Object
	 */
	public Password(String userInput)
	{
		password = userInput;
	}
	
	/**
	 * @return Method Validates if the Password Object is the correct length, less than 12 characters and more than 8, as well as does not contain any spaces. Returns true if all the conditions are met and false if not.
	 */
	public boolean validate()
	{
		if(password.length() >= 8 && password.length() <= 12 && !password.contains(" "))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @return Checks for the largest block of a character . A block is defined as characters which are the same value, case sensitive, and proceed one another. When the block is larger than 2, the method will method will suggest to shrink it by x value. If block length is 2 or less, the password will pass as decent. When validate is false, tell the user the password does not match the required Strength conditions.
	 */
	public String blockLength()
	{
		int count = 1;
			for(int i = 0; i < password.length()-1; i++ )
			{
				if(password.charAt(i) == password.charAt(i+1))
				{
					count++;
				}
			}
			
			if( count <= 2 )
			{
				return "The largest block in the password is " + count + ". This is a decent password.";
			}
			else
			{
				return "The largest block in the password is " + count + ". This password can be made stronger by reducing this block by " + (count-2);
			}
	}
}
