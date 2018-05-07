package eightQueens;

import java.util.ArrayList;

public class OneSolution {
	
	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		
		//UPDATE TO SHOW WHAT YOU PUT IN YOUR WRITE UP
		int[] cols = {3, 1, 7, 5, 0, 2, 4, 6};
		ArrayList<Queen> qs = new ArrayList<Queen>();
		for (int row = 0; row < board.getN(); row++) {
			qs.add(new Queen(row, cols[row]));
		}
		
		board.updateQueens(qs);
	}
}