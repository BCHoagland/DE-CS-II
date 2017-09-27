package selectionAndMergeSorts;

public class Deck {
	private Card[] cards;
	private int topIndex;
	
	public Deck() {
		this.cards = createDeck();
	}
	
	public Deck(boolean sorted) {
		if (sorted) {
			this.cards = selectionSort(createDeck());
		} else {
			//this.cards = shuffle(createDeck());
		}
	}
	
	private Card[] createDeck() {
		Card[] cards = new Card[52];
		int index = 0;
		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 1; rank < 14; rank++) {
				Card card = new Card(suit, rank);
				cards[index] = card;
				index++;
			}
		}
		return cards;
	}
	
	public Card[] selectionSort(Card[] cards) {
		for (int i = cards.length - 1; i >= 0; i--) {
			int maxIndex = i;
			for (int j = 0; j <= i; i++) {
				if (cards[j].compareTo(cards[maxIndex]) > 0) {
					maxIndex = j;
				}
			}
			
			Card temp = cards[i];
			cards[i] = cards[maxIndex];
			cards[maxIndex] = cards[i];
		}
		return cards;
	}
}