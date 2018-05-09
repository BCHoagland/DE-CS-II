package eightQueens;

import java.util.ArrayList;

public class AllSolutions {

	/**
	 * runs non recursive methods and prints the results
	 * @param board ChessBoard to run the methods on
	 */
	public static void testNonRecursive(ChessBoard board) {
		System.out.println("--------------------TESTING NON RECURSIVE METHODS--------------------");

		ArrayList<Queen> solution = board.findOne();
		System.out.println("find one solution: " + solution);

		ArrayList<ArrayList<Queen>> solutions = board.findAll();
		System.out.println(solutions.size() + " solutions found: " + solutions);

		System.out.println("\n");
	}

	/**
	 * runs recursive methods and prints the results
	 * @param board ChessBoard to run the methods on
	 * @param showMoves true if you want to display each move with a slight delay; false if you want to just calculate all solutions without displaying the recursive process
	 * @throws InterruptedException
	 */
	public static void testRecursive(ChessBoard board, boolean showMoves) throws InterruptedException {
		System.out.println("--------------------TESTING RECURSIVE METHODS--------------------");

		ArrayList<Queen> solution = board.findOne(new ArrayList<Queen>());
		System.out.println("find one solution: " + solution);

		board.findAll(new ArrayList<Queen>(), showMoves);
		System.out.println(board.getRecursiveSolutions().size() + " solutions found: " + board.getRecursiveSolutions());
	}

	public static void main(String[] args) throws InterruptedException {
		ChessBoard board = new ChessBoard();

		testNonRecursive(board);
		testRecursive(board, true);
	}

}
