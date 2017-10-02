package selectionAndMergeSorts;

public class Deck {
	private Card[] cards;
	private int topIndex;
	
	public Deck() {
		this.cards = createDeck();
		this.topIndex = cards.length - 1;
	}
	
	public Deck(boolean sorted) {
		this.cards = createDeck();
		if (sorted) {
			selectionSort();
		} else {
			shuffle();
		}
		this.topIndex = cards.length - 1;
	}
	
	public Card[] getCards() {
		return cards;
	}
	
	public int getTopIndex() {
		return topIndex;
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
	
	private void replaceCards(int newCardsLength) {
		Card[] newCards = new Card[newCardsLength];
		for (int i = 0; i < newCardsLength; i++) {
			newCards[i] = cards[i];
		}
		cards = newCards;
	}
	
	private Card remove(int index) {
		Card card = this.cards[index];
		replaceCards(cards.length - 1);
		topIndex--;
		return card;
	}
	
	public void shuffle() {
		for (int i = 0; i < cards.length; i++) {
			int index1 = (int)(Math.random() * cards.length);
			int index2 = (int)(Math.random() * cards.length);
			swapCardsAtIndices(index1, index2);
		}
	}
	
	public String toString() {
		int[] suitLengths = new int[4];
		for (Card card : this.cards) {
			suitLengths[card.getSuitInt(card.getSuit())]++;
		}
		int maxSuitLength = Math.max(Math.max(suitLengths[0], suitLengths[1]), Math.max(suitLengths[2], suitLengths[3]));
		
		Card[][] cols = new Card[4][maxSuitLength];
		
		int[] counters = new int[4];
		for (Card card : this.cards) {
			int suitNum = card.getSuitInt(card.getSuit());
			cols[suitNum][counters[suitNum]] = card;
			counters[suitNum]++;
		}
		
		int cardNameLength = 20;
		int maxIndex = Math.max(Math.max(cols[0].length, cols[1].length), Math.max(cols[2].length, cols[3].length));
		String outputStr = "";
		for (int i = 0; i < maxIndex; i++) {
			String tempStr = "";
			for (int j = 0; j < 4; j++) {
				if (cols[j][i] != null) {
					String cardName = cols[j][i].toString();
					while (cardName.length() < cardNameLength) {
						cardName += " ";
					}
					tempStr += cardName;
				} else {
					String nullCardName = "";
					for (int k = 0; k < cardNameLength; k++) {
						nullCardName += " ";
					}
					tempStr += nullCardName;
				}
				if (j < 3) tempStr += "\t";
			}
			if (i < (maxIndex - 1)) tempStr = tempStr + "\n";
			outputStr = outputStr + tempStr;
		}
		
		return outputStr;
	}
	
	/**
	 * determines if two decks are equal to each other
	 * @param otherDeck
	 * @return true if decks are equal, false otherwise
	 */
	public boolean equals(Object otherDeck) {
		if (otherDeck instanceof Deck) {
			Card[] cards1 = this.getCards();
			Card[] cards2 = ((Deck)otherDeck).getCards();
			
			if (cards1.length == cards2.length) {
				
				CardComparator rankComp = new CardComparator(true);
				CardComparator suitComp = new CardComparator(false);
				
				for (int i = 0; i < cards1.length; i++) {
					if (!rankComp.equals(cards1[i], cards2[i]) || !suitComp.equals(cards1[i], cards2[i])) {
						return false;
					}
				}
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * deals out hands (decks) of cards based on the given parameters
	 * @param hands
	 * @param cardsPerHand
	 * @return
	 */
	public Deck[] deal(int hands, int cardsPerHand) {
		if (hands * cardsPerHand > this.cards.length) return null;
		else {
			Deck[] decks = new Deck[hands];
			for (int i = 0; i < decks.length; i ++) {
				decks[i] = new Deck();
				decks[i].cards = new Card[cardsPerHand];
			}
			
			int[] counters = new int[hands];
			for (int i = 0; i < cardsPerHand; i++) {
				for (int j = 0; j < hands; j++) {
					decks[j].cards[counters[j]] = remove(getTopIndex());
					counters[j]++;
				}
			}
			return decks;
		}
	}
	
	/**
	 * removes a random card from the deck and collapses the deck
	 * @return card that was randomly selected
	 */
	public Card pick() {
		int index = (int)(Math.random() * (topIndex + 1));
		
		Card card = cards[index];
		
		for (int i = index; i <= topIndex; i++) {
			cards[index] = cards[i];
		}
		
		topIndex--;
		replaceCards(topIndex + 1);
		
		return card;
	}
	
	/**
	 * uses the selection sort algorithm to sort the deck
	 */
	public void selectionSort() {
		
		CardComparator comp = new CardComparator();
		
		for (int i = cards.length - 1; i >= 0; i--) {
			int maxIndex = i;
			for (int j = 0; j <= i; j++) {
				if (!comp.equals(cards[j], cards[maxIndex])) {
					int comparison = comp.compare(cards[j], cards[maxIndex]);
					if (comparison > 0) {
						maxIndex = j;
					}
				}
			}
			
			swapCardsAtIndices(i, maxIndex);
		}
	}
	
	
	//////////////////////////////////////////MAKE THESE PRIVATE
	
	public Card[] divide(Card[] arr) {
		return new Card[0];
	}
	
	public Card[] merge(Card[] arr1, Card[] arr2) {
		for (Card card1 : arr1) {
			int index = findMergeIndex(card1, arr2);
			Card[] tempArr = new Card[arr2.length + 1];
			
			for (int i = 0; i < index; i++) {
				tempArr[i] = arr2[i];
			}
			tempArr[index] = card1;
			for (int i = index + 1; i < arr2.length; i++) {
				tempArr[i] = arr2[i - 1];
			}
			
			arr2 = tempArr;
		}
		return arr2;
	}
	
	public int findMergeIndex(Card card, Card[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getRank() >= card.getRank()) {
				return i;
			}
		}
		return arr.length;
	}
}