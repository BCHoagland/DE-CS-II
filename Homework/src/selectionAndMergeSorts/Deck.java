package selectionAndMergeSorts;

public class Deck {
	private Card[] cards;
	private int topIndex;
	
	public Deck() {
		this.cards = createDeck();
	}
	
	public Deck(boolean sorted) {
		if (sorted) {
			this.cards = createDeck();
			selectionSort();
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
	
	private void swapCardsAtIndices(int index1, int index2) {
		Card temp = cards[index1];
		cards[index1] = cards[index2];
		cards[index2] = temp;
	}
	
	public void selectionSort() {
		for (int i = cards.length - 1; i >= 0; i--) {
			int maxIndex = i;
			for (int j = 0; j <= i; i++) {
				if (cards[j].compareTo(cards[maxIndex]) > 0) {
					maxIndex = j;
				}
			}
			
			swapCardsAtIndices(i, maxIndex);
		}
	}
	
	public void shuffle() {
		for (int i = 0; i < cards.length; i++) {
			int index1 = (int)(Math.random() * cards.length);
			int index2 = (int)(Math.random() * cards.length);
			swapCardsAtIndices(index1, index2);
		}
	}
}