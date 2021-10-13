package chairez;

public class WordNode {
	
	//DATA MEMBERS
	private String word;
	private WordNode leftNode;
	private WordNode rightNode;
	
	//CONSTRUCTOR
	public WordNode(String w)
	{
		word = w;
		leftNode = null;
		rightNode = null;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the leftNode
	 */
	public WordNode getLeftNode() {
		return leftNode;
	}

	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(WordNode leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * @return the rightNode
	 */
	public WordNode getRightNode() {
		return rightNode;
	}

	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(WordNode rightNode) {
		this.rightNode = rightNode;
	}
}