package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		for (Card card : testDeck.getCards()) {
			//System.out.println(card);
		}
		//System.out.println();
		
		//System.out.println("top index: " + testDeck.getTopIndex());
		
		//System.out.println(testDeck);
		
		Deck[] decks = testDeck.deal(2, 4);
		if (decks == null) System.out.println("oops");
		else {
			for (Deck deck : decks) {
				System.out.println(deck + "\n");
				System.out.println("----------------------------------------------------------------------------");
			}
		}
	}
}