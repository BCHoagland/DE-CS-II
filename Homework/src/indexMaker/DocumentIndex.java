package indexMaker;

import java.util.TreeMap;

/**
 * <h1>DocumentIndex</h1> class to house an AraryList of IndexEntry objects<br/><br/>
 * @author HoaglandB1
 * @since November 8, 2017
 */
public class DocumentIndex extends TreeMap<String, IndexEntry> {
	
	/**
	 * I have no idea what this is
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * default constructor to initialize an empty ArrayList for IndexEntry objects
	 */
	public DocumentIndex() {
		super();
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
		int index = foundOrInserted(word);
		this.get(index).add(num);
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
	
	/**
	 * find the index of the IndexEntry for the given word, creating a new IndexEntry if necessary
	 * @param word
	 * @return
	 */
	private int foundOrInserted(String word) {
		for (int i = 0; i < this.size(); i++) {
			String wordToFind = this.get(i).getWord().toLowerCase();
			
			//if IndexEntry already exists for the given word
			if (wordToFind.equals(word.toLowerCase())) {
				return i;
			}
			
			//if IndexEtnry does not exist for the given word and the proper insertion index has been found
			if (wordToFind.compareTo(word.toLowerCase()) > 0) {
				this.add(i, new IndexEntry(word));
				return i;
			}
		}
		
		//if IndexEntry does not exist for the given word and it fits at the end of the ArrayList
		int oldSize = this.size();
		this.add(this.size(), new IndexEntry(word));
		return oldSize;
	}
}