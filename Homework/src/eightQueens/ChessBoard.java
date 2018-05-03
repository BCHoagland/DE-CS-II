package eightQueens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Color;

public class ChessBoard {

	private static final int N = 8;
	private static final int HEIGHT = 120*N;
	private static final int WIDTH = 120*N;

	private JFrame window;
	private JPanel grid;
	private ChessSquarePanel[][] spaces = new ChessSquarePanel[N][N];
	private ArrayList<Queen> queens = new ArrayList<Queen>();

	ChessBoard() {
		buildFrame();
		grid = buildGridPanels();
		window.add(grid);
		window.setVisible(true);
	}

	private void buildFrame() {
		window = new JFrame("Eight Queens");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
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

	private void updateQueens(ArrayList<Queen> qs) {
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

	private ArrayList<Queen> findOne() {
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

	private ArrayList<ArrayList<Queen>> findAll() {
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

	public static void main(String[] args) throws InterruptedException {
		ChessBoard board = new ChessBoard();

		ArrayList<Queen> solution = board.findOne();
		System.out.println(solution);
		board.updateQueens(solution);
		System.out.println("\n---------------------");

		ArrayList<ArrayList<Queen>> solutions = board.findAll();
		System.out.println("all solutions found: " + solutions.size());
		//		System.out.println("all solutions: " + solutions);

		for (ArrayList<Queen> qs : solutions) {
			if (!board.isCorrect(qs)) System.out.println("this isn't right!");
		}

		//		Thread.sleep(4000);
	}
}