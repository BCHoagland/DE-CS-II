package selectionAndMergeSorts;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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





		PrintWriter outputFile;

		try {
			outputFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open output file. Is it in the project folder?");
			outputFile = null;
		}

		if (outputFile != null) {
			outputFile.println();
		}
	}
}