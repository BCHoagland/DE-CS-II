package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		
		for (Card card : testDeck.getCards()) {
			System.out.println(card);
		}
		System.out.println();
		
		testDeck.mergeSort();
		
		System.out.println(testDeck);
		
		
		
		
		testDeck = new Deck(false);
		
		for (Card card : testDeck.getCards()) {
			System.out.println(card);
		}
		System.out.println();
		
		testDeck.mergeSort();
		
		System.out.println(testDeck);
	}
}