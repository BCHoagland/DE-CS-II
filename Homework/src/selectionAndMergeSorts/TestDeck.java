package selectionAndMergeSorts;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestDeck {

	public static void main(String[] args) {
		
		//create PrintWriter to write to output file
		PrintWriter outputFile;
		try {
			outputFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open output file. Is it in the project folder?");
			outputFile = null;
		}
		
		if (outputFile != null) {
			
			//shuffled deck
			outputFile.println("-------------\nSHUFFLED DECK\n-------------");
			Deck testDeck = new Deck(false);
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			//test merge sort
			outputFile.println("---------------\nMERGE SORT DECK\n---------------");
			testDeck.mergeSort();
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			//test selection sort
			outputFile.println("-------------------\nSELECTION SORT DECK\n-------------------");
			testDeck = new Deck(false);
			testDeck.selectionSort();
			for (Card card : testDeck.getCards()) {
				outputFile.println(card);
			}
			outputFile.println();
			
			//test Deck toString method
			outputFile.println("-------------\nDECK TOSTRING\n-------------");
			outputFile.println(testDeck);
			outputFile.println();
			
			//test Deck deal method
			outputFile.println("----\nDEAL\n----");
//			testDeck.shuffle();
			Deck[] decks = testDeck.deal(3, 6);
			for (int i = 0; i < decks.length; i++) {
				outputFile.println("HAND " + (i + 1) + "\n" + decks[i] + "\n\n");
			}
			outputFile.println("REMAINING CARDS IN DECK (DECK SHOULD BE SHUFFLED):\n" + testDeck);
			outputFile.println();
			
			//test Deck pick method
			outputFile.println("----\nPICK\n----");
			testDeck = new Deck(true);
			outputFile.println("cards should be randomly selected: " + testDeck.pick() + ", " + testDeck.pick() + "\n");
			outputFile.println("REMAINING CARDS IN DECK:\n" + testDeck);
			outputFile.println();
			
			//test Deck equals method
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