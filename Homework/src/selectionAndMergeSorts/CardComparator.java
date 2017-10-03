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
	
	/**
	 * returns if two Cards are equal
	 * checks for rank or suit equality, based on given arguments
	 * @param card1
	 * @param card2
	 * @return whether or not the given Cards are equal
	 */
	public boolean equals(Card card1, Card card2) {
		if (rank) {
			if (card1.getRank() == card2.getRank()) {
				return true;
			}
			return false;
		} else {
			if (card1.getSuitInt(card1.getSuit()) == card2.getSuitInt(card2.getSuit())) {
				return true;
			} else {
				return false;
			}
		}
	}
}