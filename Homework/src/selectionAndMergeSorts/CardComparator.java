package selectionAndMergeSorts;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	
	private boolean rank;
	
	public CardComparator() {
		super();
		this.rank = false;
	}
	
	public CardComparator(boolean rank) {
		super();
		this.rank = rank;
	}
	
	@Override
	public int compare(Card card1, Card card2) {
		return card1.compareTo(card2);
	}
	
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