package selectionAndMergeSorts;

import java.util.Comparator;

/**
 * <h1>CardComparator</h1> class to compare Card objects<br/><br/>
 * @author Braden Hoagland
 */
public class CardComparator implements Comparator<Card> {
	
	/**
	 * default constructor that creates a CardComparator for rank
	 */
	public CardComparator() {
		super();
	}
	
	/**
	 * returns the value of comparing two Cards
	 */
	@Override
	public int compare(Card card1, Card card2) {
		return card1.compareTo(card2/*, rank*/);
	}
}