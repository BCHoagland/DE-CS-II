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
	public ArrayList<Queen> queens = new ArrayList<Queen>();

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
	
	public void updateBoard() {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				spaces[x][y].setQueenStatus(false);
			}
		}
		for (Queen q : queens) {
			spaces[q.getRow()][q.getCol()].setQueenStatus(true);
		}
	}

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
	}
}