package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		
		Deck[] decks = testDeck.deal(1,  8);
		
		for (Card card : decks[0].getCards()) {
			System.out.println(card);
		}
		System.out.println();
		
		decks[0].mergeSort();
		for (Card card : decks[0].getCards()) {
			System.out.println(card);
		}
	}
}