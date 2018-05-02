package eightQueens;

public class Queen {
	
	private int row;
	private int col;
	
	public Queen(int x, int y) {
		col = x;
		row = y;
	}
	
	public int getRow() {return row;}
	public int getCol() {return col;}
}