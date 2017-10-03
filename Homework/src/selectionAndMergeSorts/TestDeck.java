package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		
		testDeck.selectionSort();
		
		System.out.println(testDeck);
		System.out.println();
		
		
		
		for (Card card : testDeck.getCards()) {
			System.out.println(card);
		}
		
		
		
//		testDeck = new Deck(false);
//		
//		testDeck.mergeSort();
//		
//		System.out.println(testDeck);
	}
}