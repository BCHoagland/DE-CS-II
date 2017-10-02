package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(true);
		
		Deck[] decks = testDeck.deal(2,  4);
		
		for (Card card : decks[0].getCards()) {
			System.out.println(card);
		}
		System.out.println();
		for (Card card : decks[1].getCards()) {
			System.out.println(card);
		}
		System.out.println();
	}
}