package hashFunctions;

import java.util.Arrays;

/**
 * TicTacToe Class<br/>
 * Provides functions to create and manage a tic tac toe board
 * @author Braden Hoagland
 *
 */
public class TicTacToe {
	
	/**
	 * constant representing the number of rows of the board
	 */
	public final static int ROWS = 3;
	
	/**
	 * constant representing the number of columns of the board
	 */
	public final static int COLS = 3;
	
	/**
	 * constant representing the total number of combinations on the board (not necessarily possible board setups)
	 */
	public final static int POSSIBILITIES = (int) Math.pow(3,9);
	
	/**
	 * constant representing the number of possible characters to be used on the board
	 */
	public final static int CHAR_POSSIBILITIES = 3; // x, o or space

	/**
	 * find the number of spaces on the board that are filled with the given char
	 * @param b 2d array representing the characters currently on the board
	 * @param ch character to search for
	 * @return number of times the given char is found on the board
	 */
	private static int numChars(char[][] b, char ch) {
		int total = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++)
				if (ch == b[r][c]) 
					total++;
		return total;
	}

	/**
	 * determine if the given board setup is possible
	 * @param board 2d array representing the layout of a board
	 * @return true if the board is possible, false otherwise
	 */
	public static boolean valid(char[][] board) {

		// Ensure there are at least 3 xs and 2 os, or 3 os and 2 xs
		// Make sure there are at least one more x or one more o
		int numX = numChars(board, 'x');
		int numO = numChars(board, 'o');
		if (! (numX > 2 || numO > 2)) return false;
		if ((numX == numO + 1) || (numO == numX + 1)) return true;
		return false;
	}

	/**
	 * converts a 2d array representing a board to a string representing the same board
	 * @param b 2d array representing a board
	 * @return string of the characters on the board in order by row
	 */
	public static String boardToString(char[][] b) {
		String result = "";
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				result += b[r][c];
			//     result += "\n";
		}
		return result;
	}

	/**
	 * converts a string representing a board to a 2d array representing the same board
	 * @param board string representing a board
	 * @return 2d array of the chars on the board
	 */
	public static char[][] stringToBoard(String board) {
		char[][] b = new char[ROWS][COLS];
		int index = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) 
				b[r][c] = whichLetter(board.charAt(index++));
		}
		return b;
	}

	/**
	 * convert a character to a number (space -> 0, x -> 1, o -> 2)
	 * @param ch character to convert
	 * @return number representation of the given character
	 */
	public static char whichLetter(char ch) {
		switch (ch) {
		case '1' : return 'x';
		case '2' : return 'o';
		case '0'  : return ' ';
		default: return ch;
		}
	}

	/**
	 * create a 2d array representing a board given a string of 0s, 1s, and 2s
	 * @param s string of numbers representing a board
	 * @return 2d array of characters representing the given board
	 */
	public static char[][] makeBoard(String s) {
		char[][] b = new char[ROWS][COLS];
		int ch = 0;
		for (int r = 0; r < ROWS; r++)
			for (int c = 0; c < COLS; c++){         
				b[r][c] = whichLetter(s.charAt(ch));
				ch++;
			}
		return b;
	}

	/**
	 * add one to the given string of numbers, maintaining base 3
	 * @param s number string to increment
	 * @return incremented number string
	 */
	private static String addOne(String s) {
		// s is a 9 character string, composed of 0s, 1s, and 2s.  Add 1 to the last char, adjusting
		// all the rest of the characters as necessary.
		boolean carry = false;
		char ch[] = s.toCharArray();
		ch[ch.length-1] =  (char)((int)(ch[ch.length-1])+1);
		for (int n = ch.length-1; n >=0; n--) {
			if (carry) ch[n] = (char)((int)(ch[n])+1);
			if (ch[n] == '3') {
				carry = true;
				ch[n] = '0';
			}
			else
				carry = false;      
		}
		return new String(ch);
	}

	/**
	 * generate all board combinations (not necessarily valid ones)
	 * @return array of all possible board strings
	 */
	public static String[] fillValues() {
		String strBoard = "000000000";
		String[] values = new String[POSSIBILITIES];
		int index = 0;
		values[index++] = strBoard;
		while (!strBoard.equals("222222222") ) {
			strBoard = addOne(strBoard);
			values[index++] = strBoard;
		}
		return values;
	}

	/**
	 * determine if a given board has a three of the same character diagonally
	 * @param board 2d array of characters representing a board
	 * @return true if the board has a diagonal win, false otherwise
	 */
	private static boolean diagonalWin(char[][] board) {

		if ((board[0][0] == 'x' && board[1][1] == 'x' && board[2][2] == 'x') || 
				(board[0][0] == 'o' && board[1][1] == 'o' && board[2][2] == 'o')) {
			return true;
		}
		else
			if ((board[0][2] == 'x' && board[1][1] == 'x' && board[2][0] == 'x') ||
					(board[0][2] == 'o' && board[1][1] == 'o' && board[2][0] == 'o')) {
				return true;
			}
		return false;
	}

	/**
	 * determine if a given board has a three of the same character in a horizontal row
	 * @param board 2d array of characters representing a board
	 * @return true if the board has a row win, false otherwise
	 */
	private static boolean rowWin(char[][] board) {
		char ch;
		for (int r = 0; r < ROWS; r++) {
			ch = board[r][0];
			for (int c = 0; c < COLS; c++) 
				if (ch != board[r][c]) return false;
		} 
		return true;
	}
	
	/**
	 * determine if a given board has a three of the same character in a vertical column
	 * @param board 2d array of characters representing a board
	 * @return true if the board has a column win, false otherwise
	 */
	private static boolean colWin(char[][] board) {
		char ch;
		for (int c = 0; c < COLS; c++) {
			ch = board[0][c];
			for (int r = 0; r < ROWS; r++) 
				if (ch != board[r][c]) return false;
		} 
		return true;
	} 

	/**
	 * check if a given board is valid and has a winning setup
	 * @param b 2d array of characters representing a board
	 * @return true if the board is valid and has a winning setup, false otherwise
	 */
	public static boolean isWin(char[][]b) {
		return valid(b) && (rowWin(b) || colWin(b) || diagonalWin(b));
	}

	/**
	 * check if a given board is valid and has a winning setup
	 * @param b string representing a board
	 * @return true if the board is valid and has a winning setup, false otherwise
	 */
	public static boolean isWin(String s) {
		char[][] b = stringToBoard(s);
		return isWin(b);
	}
}