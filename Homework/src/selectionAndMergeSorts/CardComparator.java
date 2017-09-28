package selectionAndMergeSorts;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	
	public CardComparator() {
		super();
	}
	
	@Override
	public int compare(Card card1, Card card2) {
		return card1.compareTo(card2);
	}
	
	public boolean equals(Card card1, Card card2) {
		if (card1.equals(card2)) {
			return true;
		}
		return false;
	}
}