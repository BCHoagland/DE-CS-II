package linkedList;

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
		setSuit(0);
		setRank(0);
	}
	
	/**
	 * constructor that sets the suit and rank with the given integer inputs
	 * @param suit int
	 * @param rank int
	 */
	public Card(int suit, int rank) {
		setSuit(suit);
		setRank(rank);
	}
	
	/**
	 * constructor that sets the suit and rank with the given String inputs
	 * @param suit String
	 * @param rank String
	 */
	public Card(String suit, String rank) {
		setSuit(suit);
		setRank(rank);
	}
	
	/**
	 * constructor that sets the suit and rank with the given String and integer inputs
	 * @param suit String
	 * @param rank int
	 */
	public Card(String suit, int rank) {
		setSuit(suit);
		setRank(rank);
	}
	
	/**
	 * constructor that sets the suit and rank with the given integer and String inputs
	 * @param suit int
	 * @param rank String
	 */
	public Card(int suit, String rank) {
		setSuit(suit);
		setRank(rank);
	}
	
	/**
	 * getter for the suit field
	 * @return suit as String
	 */
	public String getSuit() {
		return this.suit;
	}
	
	/**
	 * getter for the rank field
	 * @return rank as int
	 */
	public int getRank() {
		return this.rank;
	}
	
	/**
	 * setter for the suit field with String argument
	 * @param suit String
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	/**
	 * setter for the suit field with int argument
	 * @param suit int
	 */
	public void setSuit(int suit) {
		String toSet;
		switch (suit) {
		case 0:
			toSet = CLUBS;
			break;
		case 1:
			toSet = DIAMONDS;
			break;
		case 2:
			toSet = HEARTS;
			break;
		case 3:
			toSet = SPADES;
			break;
		default:
			toSet = CLUBS;
			break;
		}
		this.suit = toSet;
	}
	
	/**
	 * setter for the rank field with String argument
	 * @param rank String
	 */
	public void setRank(String rank) {
		int toSet;
		switch (rank) {
		case "One":
			toSet = 1;
			break;
		case ACE:
			toSet = 1;
			break;
		case "Two":
			toSet = 2;
			break;
		case "Three":
			toSet = 3;
			break;
		case "Four":
			toSet = 4;
			break;
		case "Five":
			toSet = 5;
			break;
		case "Six":
			toSet = 6;
			break;
		case "Seven":
			toSet = 7;
			break;
		case "Eight":
			toSet = 8;
			break;
		case "Nine":
			toSet = 9;
			break;
		case "Ten":
			toSet = 10;
			break;
		case JACK:
			toSet = 11;
			break;
		case "Eleven":
			toSet = 11;
			break;
		case QUEEN:
			toSet = 12;
			break;
		case "Twelve":
			toSet = 11;
			break;
		case KING:
			toSet = 13;
			break;
		case "Thirteen":
			toSet = 11;
			break;
		default:
			toSet = 0;
			break;
		}
		this.rank = toSet;
	}
	
	/**
	 * setter for the rank field with int argument
	 * @param rank int
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * returns a String representation of the Card
	 */
	public String toString() {
		return getRankStr() + " of " + suit;
	}
	
	/**
	 * returns the String representation of the Card's rank
	 */
	public String getRankStr() {
		return numWords[this.rank];
	}
	
	/**
	 * returns the integer representation of the Card's suit
	 */
	public int getSuitInt() {
		switch (this.suit) {
		case CLUBS:
			return 0;
		case DIAMONDS:
			return 1;
		case HEARTS:
			return 2;
		case SPADES:
			return 3;
		default:
			return 0;
		}
	}
	
	/**
	 * returns the integer result of comparing this card to another card<br/>
	 * compares suit and rank
	 * @param otherCard
	 * @return int value of comparison
	 */
	@Override
	public int compareTo(Card otherCard) {
		if (otherCard instanceof Card) {
			if (this.getSuitInt() == otherCard.getSuitInt()) {
				return this.getRank() - ((Card)otherCard).getRank();
			} else {
				return this.getSuitInt() - otherCard.getSuitInt();
			}
		}
		return 1;
	}
	
	/**
	 * returns the integer result of comparing this card to another card<br/>
	 * compares suit or rank, based on given arguments
	 * @param otherCard
	 * @return int value of comparison
	 */
	public int compareTo(Card otherCard, boolean rank) {
		if (otherCard instanceof Card) {
			if (rank) {
				return this.getRank() - ((Card)otherCard).getRank();
			} else {
				return this.getSuitInt() - ((Card)otherCard).getSuitInt();
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
}