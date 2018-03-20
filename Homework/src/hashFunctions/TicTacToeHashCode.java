package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

	boolean[] winners;  // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);

		winners = new boolean[TicTacToe.POSSIBILITIES];

		Scanner winnersFile = null;
		try {
			winnersFile = new Scanner(new File("TicTacToeWinners.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}

		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String line = winnersFile.nextLine();
				super.setBoardString(line);
				int hash = myHashCode();
				winners[hash] = true;
			}
		}

	}

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

	// TODO - write the myHashCode function.  It must create a unique hashcode for all of the 
	//   possible values the game board (3 ^ 9) and it MUST use the super.charAt(row, col) function
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

	public boolean isWin(String s) {
		super.setBoardString(s);
		int hash = myHashCode();
		return winners[hash];
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");

		//READ IN TEST BOARD STRINGS
		String[] boardStrs = {"  xoxoxox", "         ", "ooooooooo", "xxxxxxxxx"};
		//TODO this line no longer works
		//  String currentBoard = board.boardValues[(int)(Math.random()* board.boardValues.length)];


		for (String boardStr : boardStrs) {
//			board.setBoardString(boardStr);
			board.setWinnerLabel(board.isWin(boardStr));
			board.setHashCodeLabel(board.myHashCode());
			Thread.sleep(4000);
		}


//		board.displayRandomString();
//		board.setHashCodeLabel(board.myHashCode());
//		// TODO Update this line to call your isWin method.
//		board.setWinner(TicTacToe.isWin(currentBoard));
	}
}