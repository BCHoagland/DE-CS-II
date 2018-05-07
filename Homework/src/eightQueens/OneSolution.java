package eightQueens;

import java.util.ArrayList;

public class OneSolution {
	
	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		
		//UPDATE TO SHOW WHAT YOU PUT IN YOUR WRITE UP
		int[] cols = {0, 1, 2, 3, 4, 5, 6, 7};
		ArrayList<Queen> qs = new ArrayList<Queen>();
		for (int row = 0; row < board.getN(); row++) {
			qs.add(new Queen(row, cols[row]));
		}
		
		board.updateQueens(qs);
	}
}