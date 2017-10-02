package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		
		Deck[] decks = testDeck.deal(2,  4);
		
		decks[0].selectionSort();
		decks[1].selectionSort();
		
		for (Card card : decks[0].getCards()) {
			System.out.println(card);
		}
		System.out.println();
		for (Card card : decks[1].getCards()) {
			System.out.println(card);
		}
		System.out.println();
		
		Card[] mergedCards = testDeck.merge(decks[0].getCards(), decks[1].getCards());
		
		for (Card card : mergedCards) {
			System.out.println(card);
		}
	}
}