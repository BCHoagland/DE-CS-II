package indexMaker;

import java.util.TreeSet;

/**
 * <h1>IndexEntry</h1> class to house the line numbers a word is found on in a text file<br/><br/>
 * @author HoaglandB1
 * @since November 8, 2017
 */
public class IndexEntry {
	
	/**
	 * word field to store the current word
	 */
	private String word;
	
	/**
	 * numsList field to store a TreeSet of line numbers for the current word
	 */
	private TreeSet<Integer> numsList;
	
	/**
	 * constructor to create a new IndexEntry object for the given word
	 * @param word the word to create an IndexEntry object for
	 */
	public IndexEntry(String word) {
		this.word = word;
		this.numsList = new TreeSet<Integer>();
	}
	
	/**
	 * adds the given line number to numsList
	 * @param num the given line number
	 */
	void add(int num) {
		if (!numsList.contains(num)) {
			numsList.add(num);
		}
	}
	
	/**
	 * getter function for the word field
	 * @return the IndexEntry's word field
	 */
	String getWord() {
		return word;
	}
	
	/**
	 * override of the object's default toString function
	 * @return a string representation of the IndexEntry object
	 */
	@Override
	public String toString() {
		String nums = numsList.toString().substring(1, numsList.toString().length() - 1);
		return getWord() + " " + nums;
	}
}