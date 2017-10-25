package indexMaker;

import java.util.ArrayList;

/**
 * <h1>IndexEntry</h1> class to house the line numbers a word is found on in a text file<br/><br/>
 * @author HoaglandB1
 * @since September 13, 2017
 */
public class IndexEntry {
	
	/**
	 * word field to store the current word
	 */
	private String word;
	
	/**
	 * numsList field to store an ArrayList of line numbers for the current word
	 */
	private ArrayList<Integer> numsList;
	
	/**
	 * constructor to create a new IndexEntry object for the given word
	 * @param word the word to create an IndexEntry object for
	 */
	public IndexEntry(String word) {
		this.word = word.toUpperCase();
		this.numsList = new ArrayList<Integer>();
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
	public String getWord() {
		return word;
	}
	
	public String getNumsListAsString() {
		String str = "";
		for (int num : numsList) {
			str += " " + num + ",";
		}
		return str.substring(0, str.length() - 1);
	}
	
	/**
	 * override of the object's default toString function
	 * @return a string representation of the IndexEntry object
	 */
	@Override
	public String toString() {
		return getWord() + getNumsListAsString();
	}
}