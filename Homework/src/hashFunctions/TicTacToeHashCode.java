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

	//IDK HOW TO DO THIS WITHOUT CHANGING BOARD
	//CAN I DO MY OWN HASH FUNCTION AGAIN IN HERE? IDK
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
	
	@Override
	boolean isWin() {
		int hash = myHashCode();
		return winners[hash];
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");

		//READ IN TEST BOARD STRINGS
		String[] boardStrs = {"  xoxoxox", "         ", "ooooooooo", "xxxxxxxxx"};

		for (String boardStr : boardStrs) {
			if (board.isWin(boardStr)) {
				board.setBoardString(boardStr);
				board.setWinnerLabel(true);
				board.setHashCodeLabel(board.myHashCode());
				Thread.sleep(4000);
			}
		}
	}
}