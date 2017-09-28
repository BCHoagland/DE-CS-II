package selectionAndMergeSorts;

public class Card implements Comparable<Card> {
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
	
	public String getRankStr(int rank) {
		/*switch (rank) {
		case 1:
			return ACE;
		case 11:
			return JACK;
		case 12:
			return QUEEN;
		case 13:
			return KING;
		default:
			return String.valueOf(rank);
		}*/
		return numWords[rank];
	}
	
	public int getRankInt(String rank) {
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
	
	@Override
	public int compareTo(Card otherCard) {
		if (otherCard instanceof Card) {
			return this.getRank() - ((Card) otherCard).getRank();
		}
		return 1;	//IS THIS OKAY????????????????
	}
	
	public boolean equals(Object otherCard) {
		return (this.rank == ((Card) otherCard).getRank());
	}
}