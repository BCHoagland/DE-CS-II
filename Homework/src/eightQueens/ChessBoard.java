package eightQueens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import java.awt.Dimension;
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
	
	private void updateBoard() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				spaces[x][y].setQueenStatus(false);
			}
		}
		for (Queen q : queens) {
			spaces[q.getRow()][q.getCol()].setQueenStatus(true);
		}
	}
	
	private boolean isCorrect(ArrayList<Queen> list) {
		if (list.size() != N) return false;
		
		ArrayList<Integer> rowList = new ArrayList<Integer>();
		ArrayList<Integer> colList = new ArrayList<Integer>();
		ArrayList<Integer> diagList = new ArrayList<Integer>();
		
		for (Queen q : list) {
			if (rowList.contains(q.getRow())) return false;
			if (colList.contains(q.getCol())) return false;
			if (diagList.contains(q.getCol() - q.getRow())) return false;
			rowList.add(q.getRow());
			colList.add(q.getCol());
			diagList.add(q.getCol() - q.getRow());
		}
		return true;
	}
	
	private boolean isSafe(Queen q, ArrayList<Queen> qs) {
		for (Queen otherQ : qs) {
			if (otherQ.getCol() != q.getCol()) {
				if (otherQ.getRow() == q.getRow()) return false;
				if (otherQ.getCol() - otherQ.getRow() == q.getCol() - q.getRow()) return false;
			}
		}
		return true;
	}
	
	private boolean moveToSafeSpot(Queen q, ArrayList<Queen> qs) {
		q.incrementRow();
		while (!isSafe(q, qs) && q.getRow() < N) {
			q.incrementRow();
		}
		if (!isSafe(q, qs)) return false;
		return true;
	}
	
	private ArrayList<Queen> findOne() {
		ArrayList<Queen> qs = new ArrayList<Queen>();
		Queen q = null;
		while (true) {
//			for (Queen q2 : qs) {
//				System.out.print("(" + q2.getCol() + ", " + q2.getRow() + "), ");
//			}
//			System.out.println();
			
			if (qs.size() < N) {
				q = new Queen(qs.size(), 0);
				qs.add(q);
			} else {
				q = qs.get(qs.size() - 1);
				q.incrementRow();
//				if (q.getRow() > 10) return null;
			}
			
			if (isCorrect(qs)) return qs;
			
			if (!isSafe(q, qs)) {
				while(!moveToSafeSpot(q, qs)) {
					qs.remove(q);
					if (queens.size() == 0) return null;
					q = qs.get(qs.size() - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		System.out.println(board.findOne());
		System.out.println("done");
	}
}