package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO Make sure you remove all of the TODO comments from this file before turning it in

public class TicTacToeHashCode extends Board {

	boolean [] winners;  // True if the hash string that maps to this index is a winner, false otherwise
	
	TicTacToeHashCode(String s) {
		super(s);
		setupWinnersArray();
	}
	
	public void setupWinnersArray() {
		winners = new boolean[TicTacToe.POSSIBILITIES];
		
		Scanner file = null;
		try {
			file = new Scanner(new File("TicTacToeWinners.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println("you should probably fix this braden");
			System.exit(1);
		}
		
		if (file != null) {
			while (file.hasNextLine()) {
				String boardConfig = file.nextLine();
				
				//somehow get hash for the string even though myHashCode takes no parameters MRS KELLY WHAT IS THIS
				int hash = 0; //get rid of this
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
		for (int y = 0; y < TicTacToe.ROWS; y++) {
			for (int x = 0; x < TicTacToe.COLS; x++) {
				hash += prehash(super.charAt(y, x)) * pows3[pows3.length - 1 - i];
				i++;
			}
		}
		return hash;
	}

	public boolean isWin(String s) {
		//get hash somehow
		int hash = 0;
		return winners[hash];
	}

	public static void main(String[] args) throws InterruptedException {
		TicTacToeHashCode board = new TicTacToeHashCode("Tic Tac Toe");
		
		while (true) {

			String currentBoard = board.boardValues[(int)(Math.random()* board.boardValues.length)];
			board.show(currentBoard);
			board.setHashCode(board.myHashCode());
//			board.setWinner(board.isWin(TicTacToe.boardToString(TicTacToe.stringToBoard(currentBoard))));
			// TODO Update this line to call your isWin method.
			board.setWinner(TicTacToe.isWin(currentBoard));

			Thread.sleep(4000);      
		}
	}
}