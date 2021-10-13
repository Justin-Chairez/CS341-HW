package chairez;

public class Main {

	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		
		dictionary.insertWordNode("batt");
		dictionary.insertWordNode("cat");
		dictionary.insertWordNode("elephant");
		dictionary.insertWordNode("donut");
		dictionary.insertWordNode("future");
		dictionary.insertWordNode("girl");
		dictionary.insertWordNode("apple");
		dictionary.insertWordNode("soda");
		dictionary.insertWordNode("random");
		//dictionary.insertWordNode("random");
		
		dictionary.displayDictionary();
		
		//dictionary.checkWord("heal");
		dictionary.checkWord("elephant");
		System.out.println();
		dictionary.displayDictionary();
		
		System.out.println("Spell check for apple: " + dictionary.spellCheck("apple"));
		System.out.println("All tests pass!");


	}

}
