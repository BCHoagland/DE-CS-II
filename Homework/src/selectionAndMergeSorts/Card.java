package selectionAndMergeSorts;

/**
 * <h1>Card</h1> class to store the information of a card in a deck<br/><br/>
 * @author Braden Hoagland
 */
public class Card implements Comparable<Card> {
	
	/**
	 * suit field to store the suit of the card as a String
	 */
	private String suit;
	
	/**
	 * rank field to store the rank of the card as an integer
	 */
	private int rank;
	
	/**
	 * constants to represent the names of the suits
	 */
	private static final String CLUBS = "Clubs";
	private static final String DIAMONDS = "Diamonds";
	private static final String HEARTS = "Hearts";
	private static final String SPADES = "Spades";
	
	/**
	 * constants to represent the names of the face cards
	 */
	private static final String ACE = "Ace";
	private static final String JACK = "Jack";
	private static final String QUEEN = "Queen";
	private static final String KING = "King";
	
	/**
	 * array that stores the word representations of the ranks
	 */
	private static final String[] numWords = {
			"Zero",		//never used, just a placeholder
			ACE,
		    "Two",
		    "Three",
		    "Four",
		    "Five",
		    "Six",
		    "Seven",
		    "Eight",
		    "Nine",
		    "Ten",
		    JACK,
		    QUEEN,
		    KING
	};
	
	/**
	 * default constructor
	 */
	public Card() {
		this.rank = 1;
		this.suit = CLUBS;
	}
	
	/**
	 * constructor that sets the suit and rank with the given integer inputs
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, int rank) {
		this.suit = getSuitStr(suit);
		this.rank = rank;
	}
	
	/**
	 * constructor that sets the suit and rank with the given String inputs
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = getRankInt(rank);
	}
	
	/**
	 * constructor that sets the suit and rank with the given String and integer inputs
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * constructor that sets the suit and rank with the given integer and String inputs
	 * @param suit
	 * @param rank
	 */
	public Card(int suit, String rank) {
		this.suit = getSuitStr(suit);
		this.rank = getRankInt(rank);
	}
	
	/**
	 * getter for the suit field
	 * @return
	 */
	public String getSuit() {
		return this.suit;
	}
	
	/**
	 * getter for the rank field
	 * @return
	 */
	public int getRank() {
		return this.rank;
	}
	
	/**
	 * returns a String representation of the Card
	 */
	public String toString() {
		return getRankStr(rank) + " of " + suit;
	}
	
	/**
	 * returns the String representation of the given rank
	 * @param rank
	 */
	public String getRankStr(int rank) {
		return numWords[rank];
	}
	
	/**
	 * returns the integer representation of the given rank
	 * @param rank
	 */
	public int getRankInt(String rank) {
		switch (rank.toLowerCase()) {
		case "Two":
			return 2;
		case "Three":
			return 3;
		case "Four":
			return 4;
		case "Five":
			return 5;
		case "Six":
			return 6;
		case "Seven":
			return 7;
		case "Eight":
			return 8;
		case "Nine":
			return 9;
		case "Ten":
			return 10;
		case ACE:
			return 1;
		case JACK:
			return 11;
		case QUEEN:
			return 12;
		case KING:
			return 13;
		default:
			return -1;
		}
	}
	
	/**
	 * returns the integer representation of the given suit
	 * @param suit
	 */
	public int getSuitInt(String suit) {
		switch (suit) {
		case CLUBS:
			return 0;
		case DIAMONDS:
			return 1;
		case HEARTS:
			return 2;
		case SPADES:
			return 3;
		default:
			return -1;
		}
	}
	
	/**
	 * returns the String representation of the given suit
	 * @param suit
	 */
	public String getSuitStr(int suit) {
		switch (suit) {
		case 0:
			return CLUBS;
		case 1:
			return DIAMONDS;
		case 2:
			return HEARTS;
		case 3:
			return SPADES;
		default:
			return null;
		}
	}
	
	/**
	 * default compareTo() method that returns a positive number if the rank of this card is greater than the rank of the other card, 0 if the cards have the same rank, and a negative number otherwise
	 */
	@Override
	public int compareTo(Card otherCard) {
		if (otherCard instanceof Card) {
			if (this.getSuitInt(this.getSuit()) == otherCard.getSuitInt(otherCard.getSuit())) {
				return this.getRank() - ((Card)otherCard).getRank();
			} else {
				return this.getSuitInt(this.getSuit()) - otherCard.getSuitInt(otherCard.getSuit());
			}
		}
		return 1;
	}
	
	/**
	 * returns the integer result of comparing this card to another card<br/>
	 * compares suit or rank, based on given arguments
	 */
	public int compareTo(Card otherCard, boolean rank) {
		if (otherCard instanceof Card) {
			if (rank) {
				return this.getRank() - ((Card)otherCard).getRank();
			} else {
				return this.getSuitInt(this.getSuit()) - this.getSuitInt(((Card)otherCard).getSuit());
			}
		}
		return 1;
	}
	
	/**
	 * returns if two Cards are equal
	 * checks for rank and suit equality
	 * @param otherCard
	 * @return whether or not the given Cards are equal
	 */
	public boolean equals(Card otherCard) {
			if ((this.getRank() == otherCard.getRank()) && (this.getSuit().equals(otherCard.getSuit()))) {
				return true;
			}
			return false;
	}
	
	/**
	 * returns if two Cards are equal
	 * checks for rank or suit equality, based on given arguments
	 * @param otherCard
	 * @return whether or not the given Cards are equal
	 */
	public boolean equals(Card otherCard, boolean rank) {
		if (rank) {
			if (otherCard.getRank() == otherCard.getRank()) {
				return true;
			}
			return false;
		} else {
			if (this.getSuitInt(this.getSuit()) == otherCard.getSuitInt(otherCard.getSuit())) {
				return true;
			} else {
				return false;
			}
		}
	}
}