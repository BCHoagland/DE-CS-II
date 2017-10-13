package selectionAndMergeSorts;

import java.util.Comparator;

/**
 * <h1>CardComparator</h1> class to compare Card objects<br/><br/>
 * @author Braden Hoagland
 */
public class CardComparator implements Comparator<Card> {
	
	/**
	 * default constructor that creates a CardComparator
	 */
	public CardComparator() {
		super();
	}
	
	/**
	 * returns the value of comparing two Cards
	 * @param card1
	 * @param card2
	 * @return int value of comparison
	 */
	@Override
	public int compare(Card card1, Card card2) {
		return card1.compareTo(card2/*, rank*/);
	}
}