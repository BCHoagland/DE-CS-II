package selectionAndMergeSorts;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * TestDeck Class
 * @author Braden Hoagland
 *
 */

public class TestDeck {
	
	public static PrintWriter outputFile;
	
	public static void main(String[] args) {
		
		//create PrintWriter to write to output file
		try {
			outputFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open output file. Is it in the project folder?");
			outputFile = null;
		}
		
		if (outputFile != null) {
			
			//comparator for later
			CardComparator comp = new CardComparator();
			
			
			
			//card constructors
			int[] testSuitInts = {0, 1, 2, 3};
			String[] testSuitStrings = {"Clubs", "Diamonds", "Hearts", "Spades"};
			
			int[] testRankInts = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
			String[] testRankStrings = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
			
			outputFile.println("-----------------\nCARD CONSTRUCTORS\n-----------------\nPrints out every card using every constructor\n");
			for (int i = 0; i < testSuitInts.length; i++) {
				for (int j = 0; j < testRankInts.length; j++) {
					outputFile.println(new Card(testSuitInts[i], testRankInts[j]) + ", " + new Card(testSuitInts[i], testRankStrings[j]) + ", " + new Card(testSuitStrings[i], testRankInts[j]) + ", " + new Card(testSuitStrings[i], testRankStrings[j]));
				}
			}
			outputFile.println();
			
			
			
			//card comparisons
			Card testCard1 = new Card(3, 1);
			Card testCard2 = new Card(2, 1);
			Card testCard3 = new Card(2, 2);
			Card testCard4 = new Card(3, 2);
			Card testCard5 = new Card(3, 1);
			
			outputFile.println("----------------\nCARD COMPARISONS\n----------------");
			outputFile.println("higher suit and same rank - equals(): " + testCard1.equals(testCard2) + ", compareTo(): " + testCard1.compareTo(testCard2));
			outputFile.println("higher suit and lower rank - equals(): " + testCard1.equals(testCard3) + ", compareTo(): " + testCard1.compareTo(testCard3));
			outputFile.println("same suit and lower rank - equals(): " + testCard1.equals(testCard4) + ", compareTo(): " + testCard1.compareTo(testCard4));
			outputFile.println("same suit and rank - equals(): " + testCard1.equals(testCard5) + ", compareTo(): " + testCard1.compareTo(testCard5));
			outputFile.println();
			
			
			
			//base deck
			outputFile.println("---------\nBASE DECK\n---------");
			Deck testDeck = new Deck();
			printCards(testDeck);
			
			
			
			//shuffled deck
			outputFile.println("-------------\nSHUFFLED DECK\n-------------");
			testDeck = new Deck(false);
			printCards(testDeck);
			
			
			
			//test selection sort
			outputFile.println("-------------------\nSELECTION SORT DECK\n-------------------");
			testDeck.selectionSort();
			printCards(testDeck);
			
			Deck checkDeck = new Deck(false);
			Arrays.sort(checkDeck.getCards(), comp);
			boolean deckEquals = true;
			for (int i = 0; i < checkDeck.getCards().length; i++) {
				if (!testDeck.getCards()[i].equals(checkDeck.getCards()[i])) {
					deckEquals = false;
					break;
				}
			}
			outputFile.println("selection sort compared to Arrays.sort - equals(): " + deckEquals + "\n");
			
			
			
			//test merge sort
			outputFile.println("---------------\nMERGE SORT DECK\n---------------");
			testDeck = new Deck(false);
			testDeck.mergeSort();
			printCards(testDeck);
			
			checkDeck = new Deck(false);
			Arrays.sort(checkDeck.getCards(), comp);
			deckEquals = true;
			for (int i = 0; i < checkDeck.getCards().length; i++) {
				if (!testDeck.getCards()[i].equals(checkDeck.getCards()[i])) {
					deckEquals = false;
					break;
				}
			}
			outputFile.println("merge sort compared to Arrays.sort - equals(): " + deckEquals + "\n");
			
			
			
			//test Deck toString method
			outputFile.println("-------------\nDECK TOSTRING\n-------------");
			outputFile.println(testDeck + "\n");
			
			
			
			//Deck deal with alternating hands
			outputFile.println("----------------------\nDEAL ALTERNATING HANDS\n----------------------");
			Deck[] decks = testDeck.deal(3, 6, true);
			for (int i = 0; i < decks.length; i++) {
				outputFile.println("HAND " + (i + 1) + " TOSTRING()\n" + decks[i] + "\n\nHAND " + (i + 1) + " CARDS (BOTTOM CARD TO TOP CARD)\n");
				printCards(decks[i]);
			}
			outputFile.println("REMAINING CARDS IN DECK (DECK SHOULD BE SHUFFLED):\n" + testDeck);
			outputFile.println();
			
			
			
			//Deck deal without alternating hands
			outputFile.println("--------------------------\nDEAL NOT ALTERNATING HANDS\n--------------------------");
			testDeck = new Deck();
			decks = testDeck.deal(3, 6, false);
			for (int i = 0; i < decks.length; i++) {
				outputFile.println("HAND " + (i + 1) + " TOSTRING()\n" + decks[i] + "\nHAND" + (i + 1) + " CARDS (BOTTOM CARD TO TOP CARD)\n");
				printCards(decks[i]);
			}
			outputFile.println("REMAINING CARDS IN DECK (DECK SHOULD BE SHUFFLED):\n" + testDeck);
			outputFile.println();
			
			
			
			//test Deck pick method
			outputFile.println("----\nPICK\n----");
			testDeck = new Deck(true);
			outputFile.println("Two cards should be randomly selected: " + testDeck.pick() + ", " + testDeck.pick() + "\n");
			outputFile.println("REMAINING CARDS IN DECK:\n" + testDeck);
			outputFile.println();
			
			
			
			//test Deck equals method
			outputFile.println("------\nEQUALS\n------");
			testDeck = new Deck(true);
			Deck testDeck2 = new Deck(true);
			Deck testDeck3 = new Deck(false);
			outputFile.println("two sorted decks should be equal. Result of equals(): " + testDeck.equals(testDeck2));
			outputFile.println("one sorted deck and one shuffled deck should be equal. Result of equals(): " + testDeck2.equals(testDeck3));
			testDeck3.pick();
			outputFile.println("One card has been taken from the second deck so they shouldn't be equal. Result of equals(): " + testDeck2.equals(testDeck3));
		}
		
		outputFile.close();
	}
	
	public static void printCards(Deck deck) {
		for (Card card : deck.getCards()) {
			outputFile.println(card);
		}
		outputFile.println();
	}
}