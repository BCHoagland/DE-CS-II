package eightQueens;

public class Queen {
	
	private int row;
	private int col;
	
	public Queen(int x, int y) {
		col = x;
		row = y;
	}
	
	public int getCol() {return col;}
	public int getRow() {return row;}
	
	public void incrementRow() {row++;}
	
	public String toString() {return "(" + col + ", " + row + ")";}
	
	public Queen clone() {return new Queen(col, row);}
}