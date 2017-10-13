package selectionAndMergeSorts;

/**
 * <h1>Deck</h1> class to store, sort, shuffle, deal, and pick Cards<br/><br/>
 * @author Braden Hoagland
 */
public class Deck {
	
	/**
	 * cards field that stores the Cards in the deck
	 */
	private Card[] cards;
	
	/**
	 * topIndex field that stores the index of the last Card in the deck<br/>
	 * the first card in the deck is at cards[0]
	 */
	private int topIndex;
	
	private static final int MAX_CARDS = 52;
	private static final int NUM_SUITS = 4;
	private static final int MIN_RANK = 1;
	private static final int MAX_RANK = 13;
	
	/**
	 * default constructor that creates a standard deck of cards
	 */
	public Deck() {
		this.cards = createDeck();
		this.topIndex = cards.length - 1;
	}
	
	/**
	 * constructor that creates either a sorted or shuffled deck of cards, based on given boolean argument
	 * @param sorted whether or not the deck should be sorted
	 */
	public Deck(boolean sorted) {
		this.cards = createDeck();
		this.topIndex = cards.length - 1;
		if (!sorted) {
			shuffle();
		}
	}
	
	/**
	 * getter function for the cards field
	 * @return
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/**
	 * getter function for the topIndex field
	 * @return
	 */
	public int getTopIndex() {
		return topIndex;
	}
	
	/**
	 * sets the cards field to a standard deck of cards
	 * @return
	 */
	private Card[] createDeck() {
		Card[] cards = new Card[MAX_CARDS];
		int index = 0;
		for (int suit = 0; suit < NUM_SUITS; suit++) {
			for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
				Card card = new Card(suit, rank);
				cards[index] = card;
				index++;
			}
		}
		return cards;
	}
	
	/**
	 * swap two Cards in the cards field at the given indices
	 * @param index1
	 * @param index2
	 */
	private void swapCardsAtIndices(int index1, int index2) {
		Card temp = cards[index1];
		cards[index1] = cards[index2];
		cards[index2] = temp;
	}
	
	/**
	 * trim the cards array to the given length
	 * @param newCardsLength
	 */
	private void replaceCards(int newCardsLength) {
		Card[] newCards = new Card[newCardsLength];
		for (int i = 0; i < newCardsLength; i++) {
			newCards[i] = cards[i];
		}
		cards = newCards;
	}
	
	/**
	 * removes and returns a Card from the cards field at the given index
	 * @param index
	 * @return
	 */
	private Card remove(int index) {
		Card card = this.cards[index];
		replaceCards(cards.length - 1);
		topIndex--;
		return card;
	}
	
	/**
	 * switches cards at random indices to shuffle the deck
	 */
	public void shuffle() {
		for (int i = 0; i <= topIndex; i++) {
			int rand = (int)(Math.random() * (topIndex + 1));
			for (int j = 0; j < rand; j++) {
				int index1 = (int)(Math.random() * (topIndex + 1));
				int index2 = (int)(Math.random() * (topIndex + 1));
				swapCardsAtIndices(index1, index2);
			}
		}
	}
	
	/**
	 * finds the largest number from a given array of integers
	 * @param nums
	 * @return largest integer in nums
	 */
	public int findMax(int[] nums) {
		if (nums.length > 0) {
			int min = nums[0];
			for (int num : nums) {
				if (num > min) {
					min = num;
				}
			}
			return min;
		}
		
		return 0;
	}

	/**
	 * prints the cards in the deck in four columns, separated by suit
	 */
	public String toString() {
		//determine the max number of cards per suit
		int[] suitLengths = new int[NUM_SUITS];
		for (Card card : this.cards) {
			suitLengths[card.getSuitInt()]++;
		}
		int maxSuitLength = findMax(suitLengths);
		
		//set the number of rows per column to the max number of cards per suit
		Card[][] cols = new Card[NUM_SUITS][maxSuitLength];
		
		//add cards to their proper columns
		int[] counters = new int[NUM_SUITS];
		for (Card card : this.cards) {
			int suitNum = card.getSuitInt();
			cols[suitNum][counters[suitNum]] = card;
			counters[suitNum]++;
		}
		
		//create a string representing the four columns of cards
		int cardNameLength = 20;
		int[] colsLengths = new int[NUM_SUITS];
		for (int i = 0; i < NUM_SUITS; i++) {
			colsLengths[i] = cols[i].length;
		}
		int maxIndex = findMax(colsLengths);
		String outputStr = "";
		for (int i = 0; i < maxIndex; i++) {
			String tempStr = "";
			for (int j = 0; j < NUM_SUITS; j++) {
				if (cols[j][i] != null) {
					String cardName = cols[j][i].toString();
					
					//add white space to card names to ensure each card name is the same length
					//this will ensure proper formatting
					while (cardName.length() < cardNameLength) {
						cardName += " ";
					}
					tempStr += cardName;
					
					//add empty space for cards that don't exist
					//this ensures that columns without the same number of rows still print well
				} else {
					String nullCardName = "";
					for (int k = 0; k < cardNameLength; k++) {
						nullCardName += " ";
					}
					tempStr += nullCardName;
				}
				
				//add tab after the first three columns
				if (j < (NUM_SUITS - 1)) tempStr += "\t";
			}
			
			//move to the next row of cards
			if (i < (maxIndex - 1)) tempStr = tempStr + "\n";
			outputStr = outputStr + tempStr;
		}
		
		return outputStr;
	}
	
	/**
	 * determines if two decks are equal to each other<br/>decks are equal if they have the same cards (not necessarily in the same order)
	 * @param otherDeck
	 * @return true if decks are equal, false otherwise
	 */
	public boolean equals(Object otherDeck) {
		if (otherDeck instanceof Deck) {
			Card[] cards1 = this.divide(this.getCards());
			Card[] cards2 = this.divide(((Deck)otherDeck).getCards());
			
			if (cards1.length == cards2.length) {
				
				for (int i = 0; i < cards1.length; i++) {
					if (!cards1[i].equals(cards2[i]) || !cards1[i].equals(cards2[i])) {
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
	 * deals out hands (decks) of cards
	 * @param hands number of hands to deal to
	 * @param cardsPerHand numbers of cards in each hand
	 * @param alternateHands whether to alternate card distribution to each hand or to deal all cards to one hand at a time
	 * @return Deck[] of the new hands
	 */
	public Deck[] deal(int hands, int cardsPerHand, boolean alternateHands) {
		if (hands * cardsPerHand > (topIndex + 1)) return null;
		Deck[] decks = new Deck[hands];
		for (int i = 0; i < decks.length; i ++) {
			decks[i] = new Deck();
			decks[i].cards = new Card[cardsPerHand];
		}

		//deal cards one at a time to each hand
		if (alternateHands) {
			for (int i = 0; i < cardsPerHand; i++) {
				for (int j = 0; j < hands; j++) {
					decks[j].cards[i] = remove(getTopIndex());
				}
			}
			//deal all the cards for each deck at once
		} else {
			for (int i = 0; i < hands; i++) {
				for (int j = 0; j < cardsPerHand; j++) {
					decks[i].cards[j] = remove(getTopIndex());
				}
			}
		}

		return decks;
	}

	/**
	 * removes a random card from the deck and collapses the deck
	 * @return Card that was randomly selected
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
	 * uses the selection sort algorithm to sort the cards array by suit and rank
	 */
	public void selectionSort() {
		int min;
		CardComparator comp = new CardComparator();
		
		for (int i = 0; i < cards.length; i++) {
			min = i;
			for (int j = i + 1; j < cards.length; j++) {
				if (comp.compare(cards[j], cards[min]) < 0) {
					min = j;
				}
			}
			if (min != i) {
				swapCardsAtIndices(i, min);
			}
		}
	}

	/**
	 * sorts the cards array by suit and rank using merge sort
	 */
	public void mergeSort() {
		this.cards = divide(this.getCards());
	}

	/**
	 * recursively splits and merges an array of Cards using the merge sort algorithm
	 * @param arr
	 * @return the sorted array of Cards
	 */
	private Card[] divide(Card[] arr) {
		if (arr.length > 1) {
			int arrLengthExtra = 0;
			if (arr.length % 2 == 1) {
				arrLengthExtra = 1;
			}

			Card[] arr1 = new Card[(int)(arr.length / 2) + arrLengthExtra];
			Card[] arr2 = new Card[(int)(arr.length / 2)];

			for (int i = 0; i < arr.length; i++) {
				if (i < ((arr.length / 2) + arrLengthExtra)) {
					arr1[i] = arr[i];
				} else {
					arr2[i - (arr.length / 2 + arrLengthExtra)] = arr[i];
				}
			}
			
			Card[] test = merge(divide(arr1), divide(arr2));
			return test;
		}
		
		return arr;
	}
	
	/**
	 * merges two sorted arrays into one sorted array
	 * @param arr1
	 * @param arr2
	 * @return the combined sorted array
	 */
	private Card[] merge(Card[] arr1, Card[] arr2) {
		for (Card card1 : arr1) {
			int index = findMergeIndex(card1, arr2);
			Card[] tempArr = new Card[arr2.length + 1];
			
			for (int i = 0; i < index; i++) {
				tempArr[i] = arr2[i];
			}
			tempArr[index] = card1;
			for (int i = index + 1; i < arr2.length + 1; i++) {
				tempArr[i] = arr2[i - 1];
			}
			
			arr2 = tempArr;
		}
		return arr2;
	}
	
	/**
	 * finds the index at which to insert a card during the merge portion of the merge sort
	 * @param card
	 * @param arr
	 * @return index of where to insert value
	 */
	private int findMergeIndex(Card card, Card[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (card.getSuitInt() == arr[i].getSuitInt()) {
				if (card.getRank() <= arr[i].getRank()) {
					return i;
				}
			} else if (card.getSuitInt() < arr[i].getSuitInt()) {
				return i;
			}
		}
		return arr.length;
	}
}