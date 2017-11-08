package indexMaker;

import java.util.TreeMap;

/**
 * <h1>DocumentIndex</h1> class to house an AraryList of IndexEntry objects<br/><br/>
 * @author HoaglandB1
 * @since November 8, 2017
 */
public class DocumentIndex extends TreeMap<String, IndexEntry> {
	
	/**
	 * I have no idea what this is but Eclipse gets mad if I don't have it
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * default constructor to initialize an empty ArrayList for IndexEntry objects
	 */
	public DocumentIndex() {
		super(new IndexEntryComparator());
	}
	
	/**
	 * constructor that creates an ArrayList of a certain size for IndexEntry objects
	 * @param size
	 */
//	public DocumentIndex(int size) {
//		super(size);
//	}
	
	/**
	 * add the given line number to the IndexEntry for the given word
	 * @param word
	 * @param num
	 */
	void addWord(String word, int num) {
		if (!this.containsKey(word)) {
			this.put(word, new IndexEntry(word));
		}
		this.get(word).add(num);
	}
	
	/**
	 * add the given line number to the IndexEntry objects for all words in the given String
	 * @param str
	 * @param num
	 */
	void addAllWords(String str, int num) {
		String[] words = str.split("\\W+");
		for (String word : words) {
			if (!word.equals("")) {
				addWord(word, num);
			}
		}
	}
}