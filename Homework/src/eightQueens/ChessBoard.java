package eightQueens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Color;

public class ChessBoard {

	private static final int N = 8;
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int SIDE = (int) Math.min(N * 120, SCREEN_SIZE.getHeight());

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

	private boolean moveToSafeSpot(Queen q, ArrayList<Queen> qs) {
		q.incrementRow();
		while (!isSafe(q, qs) && q.getRow() < N - 1) {
			q.incrementRow();
		}
		if (!isSafe(q, qs)) return false;
		return true;
	}

	//NON RECURSIVE
	
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

	public ArrayList<ArrayList<Queen>> findAll() {
		ArrayList<ArrayList<Queen>> solutions = new ArrayList<ArrayList<Queen>>();
		ArrayList<Queen> qs = new ArrayList<Queen>();
		Queen q = null;
		int n = 0;
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
				n++;
				//				System.out.println("adding " + qs);
				//				System.out.println(n + ":    " + solutions);
			}
		}
	}
	
	//RECURSIVE
	
	public ArrayList<Queen> findOne(ArrayList<Queen> qs) throws InterruptedException {
		if (qs.size() == N) {
			if (isCorrect(qs)) {
				return qs;
			}
			else return null;
		} else {
			Queen q = new Queen(qs.size(), 0);
			qs.add(q);
			
			if (!isSafe(q, qs)) moveToSafeSpot(q, qs);
			while (true) {
				if (findOne(qs) != null) return qs;
				moveToSafeSpot(q, qs);
				if (q.getRow() >= N) {
					qs.remove(q);
					break;
				}
			}
			return null;
		}
	}
	
	public void findAll(ArrayList<Queen> qs) throws InterruptedException {
		if (qs.size() == N) {
			if (isCorrect(qs)) {
				ArrayList<Queen> tempQs = new ArrayList<Queen>();
				for (Queen q : qs) tempQs.add(q.clone());
				recursiveSolutions.add(tempQs);
//				System.out.println(recursiveSolutions);
			}
		} else {
			Queen q = new Queen(qs.size(), 0);
			qs.add(q);
			
			if (!isSafe(q, qs)) moveToSafeSpot(q, qs);
			while (true) {
				findAll(qs);
				moveToSafeSpot(q, qs);
				if (q.getRow() >= N) {
					qs.remove(q);
					break;
				}
			}
		}
	}
	
	public static void testNonRecursive(ChessBoard board) {
		ArrayList<Queen> solution = board.findOne();
		System.out.println(solution);
		System.out.println("---------------------");

		ArrayList<ArrayList<Queen>> solutions = board.findAll();
		System.out.println(solutions.size() + " solutions found nonrecursively: " + solutions);
	}
	
	public static void testRecursive(ChessBoard board) throws InterruptedException {
		ArrayList<Queen> solution = board.findOne(new ArrayList<Queen>());
		System.out.println(solution);
		System.out.println("---------------------");
		
		board.findAll(new ArrayList<Queen>());
		System.out.println(board.recursiveSolutions.size() + " solutions found recursively: " + board.recursiveSolutions);
	}

	public static void main(String[] args) throws InterruptedException {
		ChessBoard board = new ChessBoard();

		testNonRecursive(board);
		System.out.println("---------------------------------\n---------------------------------");
		testRecursive(board);
	}
}