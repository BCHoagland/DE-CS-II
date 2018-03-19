package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO Make sure you remove all of the TODO comments from this file before turning itin

public class TicTacToeHashCode extends Board {

	boolean[] winners;  // True if the hash string that maps to this index is a winner, false otherwise

	TicTacToeHashCode(String s) {
		super(s);
		// TODO Instantiate/fill winners array.

		winners = new boolean[TicTacToe.POSSIBILITIES];

		Scanner inputFile = null;
		try {
			inputFile = new Scanner(new File("TicTacToeWinners.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}
		
		int n = 0;
		if (inputFile != null) {
			while (inputFile.hasNextLine() && n < 1) {
				String line = inputFile.nextLine();
				super.setBoardString(line);
				System.out.println("\n\nboard string: " + line);
				System.out.println("final2: " + myHashCode() + "\n\n\n");
				n++;
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
		for (int y = 0; y < TicTacToe.ROWS; y++) {
			for (int x = 0; x < TicTacToe.COLS; x++) {
				System.out.println(x + ", " + y + ": " + super.charAt(y, x));
				hash += prehash(super.charAt(y, x)) * pows3[pows3.length - 1 - i];
				i++;
			}
		}
		System.out.println("final: " + hash);
		return hash;
	}

	public boolean isWin(String s) {
		// return the value in the winner array for the hash code of the board string sent in.
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode ("Tic Tac Toe");

		/*while (true) {

			//TODO this line no longer works
			//  String currentBoard = board.boardValues[(int)(Math.random()* board.boardValues.length)];

			board.displayRandomString();
			board.setHashCodeLabel(board.myHashCode());
			// TODO Update this line to call your isWin method.
			board.setWinner(TicTacToe.isWin(currentBoard));

			Thread.sleep(4000);      
		}*/
	}
}  
