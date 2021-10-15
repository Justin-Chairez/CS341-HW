package chairez;

public class Main {

	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		Dictionary argDictionary = new Dictionary();
		
		for(int i = 0; i < args.length; i++)
		{
			argDictionary.insertWordNode(args[i]);
		}
		
		//Testing to see if args Dictionary is in alphabetical order when entire script is added
		System.out.println("Args Dictionary Values: ");
		argDictionary.displayDictionary();
		System.out.println();
		
		//Testing to see if pointers are correct when remove words from args dictionary
		argDictionary.checkWord("any");
		argDictionary.checkWord("closet");
		argDictionary.checkWord("bed");
		argDictionary.checkWord("location");
		argDictionary.checkWord("your");
		System.out.println("Args Dictionary Values after removal: ");
		argDictionary.displayDictionary();

		//Testing to see if spell check functions properly
		System.out.println("Spell check for first: " + argDictionary.spellCheck("first"));
		System.out.println("Spell check for dishes: " + argDictionary.spellCheck("dishes"));
		System.out.println("Spell check for your: " + argDictionary.spellCheck("your"));


		
		System.out.println("Original Dictionary Values: ");
		dictionary.insertWordNode("batt");
		dictionary.insertWordNode("cat");
		dictionary.insertWordNode("random");
		dictionary.insertWordNode("donut");
		dictionary.insertWordNode("future");
		
		dictionary.displayDictionary();
		
		System.out.println();
		System.out.println("Added values to Dictionary: ");
		dictionary.insertWordNode("girl");
		dictionary.insertWordNode("apple");
		dictionary.insertWordNode("soda");
		dictionary.insertWordNode("elephant");
		
		dictionary.displayDictionary();


		//dictionary.insertWordNode("random");
		
		
		//dictionary.checkWord("heal");
		//dictionary.checkWord("elephant");
		//System.out.println();
		//dictionary.displayDictionary();
		
		System.out.println();
		System.out.println("Spell check for apple: " + dictionary.spellCheck("apple"));
		
		System.out.println();
		System.out.println("All tests pass!");


	}

}
