package selectionAndMergeSorts;

public class Card {
	private String suit;
	private int rank;
	
	private static final String CLUBS = "Clubs";
	private static final String DIAMONDS = "Diamonds";
	private static final String HEARTS = "Hearts";
	private static final String SPADES = "Spades";
	
	private static final String ACE = "Ace";
	private static final String JACK = "Jack";
	private static final String QUEEN = "Queen";
	private static final String KING = "King";
	
	public Card() {
		//DEFAULT SETTING
	}
	
	
	
	//CHECK IF RESULTS RETURNED FROM CONVERTERS ARE NOT NULL OR -1
	
	
	public Card(int suit, int rank) {
		this.suit = getSuitStr(suit);
		this.rank = rank;
	}
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = getRankInt(rank);
	}
	
	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Card(int suit, String rank) {
		this.suit = getSuitStr(suit);
		this.rank = getRankInt(rank);
	}
	
	public String getSuit() {
		return this.suit;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return getRankStr(rank) + " of " + suit;
	}
	
	private String getRankStr(int rank) {
		switch (rank) {
		case 1:
			return "ace";
		case 11:
			return "jack";
		case 12:
			return "queen";
		case 13:
			return "king";
		default:
			return String.valueOf(rank);
		}
	}
	
	private int getRankInt(String rank) {
		switch (rank) {
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

	private int getSuitInt(String suit) {
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
	
	private String getSuitStr(int suit) {
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
	
	public int compareTo(Object otherCard) {
		if (otherCard != null) {
			return this.getRank() - ((Card) otherCard).getRank();
		}
		return 1;	//IS THIS OKAY????????????????
	}
}