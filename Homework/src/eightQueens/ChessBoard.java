package eightQueens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.Color;

public class ChessBoard {

	private static final int N = 8;
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int SIDE = (int) Math.min(N * 120, SCREEN_SIZE.getHeight());
	private static final int DELAY = 100;
	
	private JFrame window;
	private JPanel grid;
	private ChessSquarePanel[][] spaces = new ChessSquarePanel[N][N];
	private ArrayList<Queen> queens = new ArrayList<Queen>();
	private ArrayList<ArrayList<Queen>> recursiveSolutions = new ArrayList<ArrayList<Queen>>();

	ChessBoard() {
		buildFrame();
		grid = buildGridPanels();
		window.add(grid);
		window.setVisible(true);
	}
	
	public int getN() {return N;}
	
	public ArrayList<ArrayList<Queen>> getRecursiveSolutions() {return recursiveSolutions;}

	private void buildFrame() {
		window = new JFrame("Eight Queens");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(SIDE, SIDE));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	}

	private boolean isEven(int x) {
		return x % 2 == 0;
	}

	private Color getPanelColor(int row, int col) {
		if (isEven(row - col)) return Color.BLACK;
		else return Color.WHITE;
	}

	private JPanel buildGridPanels() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(N,N));
		Color bg;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				bg = getPanelColor(r,c);           
				ChessSquarePanel sq = new ChessSquarePanel(bg, false);
				spaces[r][c] = sq;
				p.add(sq);
			}
		}
		return p;
	}
	
	public void updateQueens(ArrayList<Queen> qs) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				spaces[x][y].setQueenStatus(false);
			}
		}
		queens = qs;
		for (Queen q : queens) {
			spaces[q.getRow()][q.getCol()].setQueenStatus(true);
		}
	}

	private boolean isCorrect(ArrayList<Queen> list) {
		if (list.size() != N) return false;

		for (Queen q : list) {
			if (!isSafe(q, list)) return false;
		}
		return true;
	}

	private boolean isSafe(Queen q, ArrayList<Queen> qs) {
		if (q.getCol() >= N || q.getRow() >= N) return false;
		for (Queen otherQ : qs) {
			if (otherQ.getCol() != q.getCol()) {
				if (otherQ.getRow() == q.getRow()) return false;
				if (otherQ.getCol() - otherQ.getRow() == q.getCol() - q.getRow()) return false;
				if (otherQ.getCol() + otherQ.getRow() == q.getCol() + q.getRow()) return false;
			}
		}
		return true;
	}

	/**
	 * moves the given queen down until it finds a safe space; if no safe space can be found, the queen is left in the last row and the method returns false
	 * @param q queen to be moved
	 * @param qs all queens on the current board
	 * @return true if a safe space is found; false otherwise
	 */
	private boolean moveToSafeSpot(Queen q, ArrayList<Queen> qs) {
		q.incrementRow();
		while (!isSafe(q, qs) && q.getRow() < N - 1) {
			q.incrementRow();
		}
		if (!isSafe(q, qs)) return false;
		return true;
	}
	
	/**
	 * update the queens on the board and display them, pausing for a set amount of time; if the board displays a solution to the n queens problem, pause longer and make the black spaces flash green
	 * @param qs all queens on the current board
	 * @throws InterruptedException
	 */
	private void showMove(ArrayList<Queen> qs) throws InterruptedException {
		updateQueens(qs);
		if (isCorrect(qs)) {
			notifyCorrect();
		} else Thread.sleep(DELAY);
	}

	/**
	 * change all colored panels on the board to the given color
	 * @param c color to set the colored panels to
	 */
	private void changeColoredPanels(Color c) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (getPanelColor(row, col) != Color.WHITE) spaces[row][col].setColor(c);
			}
		}
	}
	
	/**
	 * makes the colored panels flash green for a longer amount of time than the move delay time
	 * @throws InterruptedException
	 */
	private void notifyCorrect() throws InterruptedException {
		changeColoredPanels(Color.GREEN);
		Thread.sleep(DELAY * 10);
		changeColoredPanels(Color.BLACK);
	}
	
	//NON RECURSIVE
	
	/**
	 * finds one solution to the n queens problem non-recursively
	 * @return arrayList of Queens that stores a solution to the n queens problem
	 */
	public ArrayList<Queen> findOne() {
		ArrayList<Queen> qs = new ArrayList<Queen>();
		Queen q = null;
		while (true) {

			if (qs.size() < N) {
				q = new Queen(qs.size(), 0);
				qs.add(q);
			} else {
				q = qs.get(qs.size() - 1);
				q.incrementRow();
			}

			if (!isSafe(q, qs)) {
				while(!moveToSafeSpot(q, qs)) {
					qs.remove(qs.size() - 1);
					if (qs.size() == 0) {
						return null;
					}
					q = qs.get(qs.size() - 1);
				}
			}

			if (isCorrect(qs)) return qs;
		}
	}

	/**
	 * finds all solutions to the n queens problem non-recursively
	 * @return arrayList of arrayLsit of Queens that stores all solutions to the n queens problem
	 */
	public ArrayList<ArrayList<Queen>> findAll() {
		ArrayList<ArrayList<Queen>> solutions = new ArrayList<ArrayList<Queen>>();
		ArrayList<Queen> qs = new ArrayList<Queen>();
		Queen q = null;
		while (true) {

			if (qs.size() < N) {
				q = new Queen(qs.size(), 0);
				qs.add(q);
			} else {
				q = qs.get(qs.size() - 1);
				q.incrementRow();
			}

			if (!isSafe(q, qs)) {
				while(!moveToSafeSpot(q, qs)) {
					qs.remove(qs.size() - 1);
					if (qs.size() == 0) {
						return solutions;
					}
					q = qs.get(qs.size() - 1);
				}
			}

			if (isCorrect(qs)) {
				ArrayList<Queen> newQs = new ArrayList<Queen>();
				for (Queen q2 : (ArrayList<Queen>) qs.clone()) {
					newQs.add(q2.clone());
				}
				solutions.add((ArrayList<Queen>) newQs.clone());
			}
		}
	}
	
	//RECURSIVE
	
	/**
	 * finds one solution to the n queens problem recursively
	 * @param qs arrayList of queens that stores all queens that have already been added to the board during the method; when calling this method, pass an empty arrayList into it
	 * @return arrayList of queens that stores a solution to the n queens problem
	 * @throws InterruptedException
	 */
	public ArrayList<Queen> findOne(ArrayList<Queen> qs) throws InterruptedException {
		if (qs.size() == N) {
			if (isCorrect(qs)) {
				return qs;
			}
			else return null;
		} else {
			Queen q = new Queen(qs.size(), 0);
			qs.add(q);
			
			while (q.getRow() < N) {
				if (isSafe(q, qs) && findOne(qs) != null) return qs;
				if (!moveToSafeSpot(q, qs)) break;
			}
			qs.remove(qs.size() - 1);
			return null;
		}
	}
	
	/**
	 * finds all solutions to the n queens problem recursively
	 * @param qs arrayList of queens that stores all queens that have already been added to the board during the method; when calling this method, pass an empty arrayList into it
	 * @param showMoves true if you want to display each move with a slight delay; false if you want to just calculate all solutions without displaying the recursive process
	 * @throws InterruptedException
	 */
	public void findAll(ArrayList<Queen> qs, boolean showMoves) throws InterruptedException {
		if (qs.size() == N) {
			if (isCorrect(qs)) {
				ArrayList<Queen> tempQs = new ArrayList<Queen>();
				for (Queen q : qs) tempQs.add(q.clone());
				recursiveSolutions.add(tempQs);
			}
		} else {
			Queen q = new Queen(qs.size(), 0);
			qs.add(q);
			if (showMoves) showMove(qs);

			while (q.getRow() < N) {
				if (isSafe(q, qs)) findAll(qs, showMoves);
				if (!moveToSafeSpot(q, qs)) {
					break;
				}
				if (showMoves) showMove(qs);
			}
			qs.remove(qs.size() - 1);
			if (showMoves) showMove(qs);
		}
	}
}