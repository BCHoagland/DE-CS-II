package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * <h1>TicTacToeHashCode Class</h1><br/>
 * Represents a special version of the Board class that is used to calculate hash values for winning tic tac toe board setups
 * @author Braden Hoagland
 */
public class TicTacToeHashCode extends Board {
	
	/**
	 * name of the file with the winning tic tac toe setups
	 */
	public static final String WINNERS_FILE_NAME = "TicTacToeWinners.txt";
	
	/**
	 * name of the file with my test tic tac toe strings
	 */
	public static final String TESTS_FILE_NAME = "TTT_Tests.txt";
	
	/**
	 * time (in milliseconds) that passes before switching to the next winning tic tac toe test string
	 */
	public static final int DELAY = 4000;
	
	/**
	 * boolean array that stores a value of true at all indices mapped to by running my hash function on winning tic tac toe setups
	 */
	boolean[] winners;
	
	/**
	 * constructor for the TicTacToeHashCode class that instantiates and fills the winners array with the proper T/F values
	 * @param s title of the JFrame for the board
	 */
	TicTacToeHashCode(String s) {
		super(s);
		
		winners = new boolean[TicTacToe.POSSIBILITIES];

		Scanner winnersFile = getScannerForFile(WINNERS_FILE_NAME);

		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String line = winnersFile.nextLine();
				super.setBoardString(line);
				int hash = myHashCode();
				winners[hash] = true;
			}
			winnersFile.close();
		}
	}
	
	/**
	 * get a scanner for the file with the given name, if possible
	 * @param fileName name of the file to return a scanner for
	 * @return scanner for the given file; if no file exists, exit with an error
	 */
	public Scanner getScannerForFile(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}

		return sc;
	}

	/**
	 * prehash function that maps each valid tic tac toe character to a certain int
	 * @param ch character to be mapped to an int
	 * @return int representing the given character
	 */
	public int prehash(char ch) {
		switch(ch) {
		case ' ':
			return 0;
		case 'o':
			return 1;
		case 'x':
			return 2;
		default:
			return -1;
		}
	}

	/**
	 * my hash code function that calculates a hash for the current boardString
	 * @return hash value of the current boardString
	 */
	@Override
	public int myHashCode() {
		int[] pows3 = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};
		int hash = 0;
		int i = 0;
		int numSpaces = TicTacToe.ROWS * TicTacToe.COLS;
		for (int y = 0; y < TicTacToe.ROWS; y++) {
			for (int x = 0; x < TicTacToe.COLS; x++) {
				hash += prehash(super.charAt(y, x)) * pows3[numSpaces - 1 - i];
				i++;
			}
		}
		return hash;
	}

	/**
	 * determine if the given board setup is a winning setup by checking it against the winners array
	 * @param s board setup to check
	 * @return true if s is a winning setup, false otherwise
	 */
	@Override
	public boolean isWin(String s) {
		//		super.setBoardString(s);
		//		int hash = myHashCode();
		//		return winners[hash];
		
		int[] pows3 = {1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683};
		int hash = 0;
		int i = 0;
		int numSpaces = TicTacToe.ROWS * TicTacToe.COLS;
		for (int y = 0; y < TicTacToe.ROWS; y++) {
			for (int x = 0; x < TicTacToe.COLS; x++) {
				hash += prehash(super.charAt(s, y, x)) * pows3[numSpaces - 1 - i];
				i++;
			}
		}
		return winners[hash];
	}

	/**
	 * determine if the current boardString is a winning setup by checking it against the winners array
	 * @return true if the current boardString is a winning setup, false otherwise
	 */
	@Override
	boolean isWin() {
		int hash = myHashCode();
		return winners[hash];
	}

	/**
	 * main method that displays all winning board setups from the test file at regular intervals
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");

		int correctStrLength = TicTacToe.ROWS * TicTacToe.COLS;

		Scanner testFile = board.getScannerForFile(TESTS_FILE_NAME);

		if (testFile != null) {
			while (testFile.hasNextLine()) {
				String boardStr = testFile.nextLine();
				if (boardStr.length() != correctStrLength) {
//					System.out.println("\"" + boardStr + "\" is not a valid board string");
					continue;
				}
				if (!TicTacToe.valid(TicTacToe.stringToBoard(boardStr))) {
//					System.out.println("\"" + boardStr + "\" is not a valid board string");
					continue;
				}
				if (board.isWin(boardStr)) {
//					System.out.println("winning board string: \"" + boardStr + "\" with hash " + board.myHashCode() + ". Updating the GUI");
					board.setBoardString(boardStr);
					board.setWinnerLabel(true);
					board.setHashCodeLabel(board.myHashCode());
					Thread.sleep(DELAY);
				} else {
//					System.out.println("losing board string: \"" + boardStr + "\" with hash " + board.myHashCode() + ". Not updating the GUI");
				}
			}
			testFile.close();
		}
	}
}