package selectionAndMergeSorts;

import java.util.Comparator;

/**
 * <h1>CardComparator</h1> class to compare Card objects<br/><br/>
 * @author Braden Hoagland
 */
public class CardComparator implements Comparator<Card> {
	
	/**
	 * rank field to store whether the comparator should compare Cards using rank or suit
	 */
	private boolean rank;
	
	/**
	 * default constructor that creates a CardComparator for rank
	 */
	public CardComparator() {
		super();
		this.rank = true;
	}
	
	/**
	 * constructor that sorts for rank or suit, based on given argument
	 * @param rank whether or not to sort by rank
	 */
	public CardComparator(boolean rank) {
		super();
		this.rank = rank;
	}
	
	/**
	 * returns the value of comparing two Cards
	 */
	@Override
	public int compare(Card card1, Card card2) {
		return card1.compareTo(card2, rank);
	}
}