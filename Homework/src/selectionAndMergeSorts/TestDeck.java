package selectionAndMergeSorts;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestDeck {

	public static void main(String[] args) {





		PrintWriter outputFile;

		try {
			outputFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open output file. Is it in the project folder?");
			outputFile = null;
		}

		if (outputFile != null) {
			Deck testDeck = new Deck(false);
			
			outputFile.println("-------------\nUNSORTED DECK\n-------------");
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			outputFile.println("---------------\nMERGE SORT DECK\n---------------");
			testDeck.mergeSort();
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			outputFile.println("-------------------\nSELECTION SORT DECK\n-------------------");
			testDeck = new Deck(false);
			testDeck.selectionSort();
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			outputFile.println("-------------\nDECK TOSTRING\n-------------");
			outputFile.println(testDeck);
			outputFile.println();
			
			outputFile.println("----\nDEAL\n----");
			testDeck.shuffle();
			Deck[] decks = testDeck.deal(3, 6);
			for (int i = 0; i < decks.length; i++) {
				outputFile.println("HAND " + (i + 1) + "\n" + decks[i] + "\n\n");
			}
			
			outputFile.println("----\nPICK\n----");
			outputFile.println(testDeck.pick());
			outputFile.println();
			
			outputFile.println("------\nEQUALS\n------");
			testDeck = new Deck(true);
			Deck testDeck2 = new Deck(true);
			Deck testDeck3 = new Deck(false);
			outputFile.println("two sorted decks should be equal. Result of equals(): " + testDeck.equals(testDeck2));
			outputFile.println("one sorted deck and one shuffled deck should not be equal. Result of equals(): " + testDeck2.equals(testDeck3));
		}
		
		outputFile.close();
	}
}