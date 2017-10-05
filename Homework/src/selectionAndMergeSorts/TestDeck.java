package selectionAndMergeSorts;

public class TestDeck {

	public static void main(String[] args) {
		Deck testDeck = new Deck(false);
		testDeck.mergeSort();
		for (Card card : testDeck.getCards()) {
			System.out.println(card);
		}
		System.out.println();
		
		testDeck = new Deck(false);
		testDeck.selectionSort();
		for (Card card : testDeck.getCards()) {
			System.out.println(card);
		}
	}
}