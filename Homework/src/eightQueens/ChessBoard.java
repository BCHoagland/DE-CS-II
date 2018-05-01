package eightQueens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;

public class ChessBoard {

	private static final int ROWS = 8;
	private static final int COLS = 8;   
	private static final int HEIGHT = 120*ROWS;
	private static final int WIDTH = 120*COLS;

	private JFrame window;
	private JPanel grid;
	ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS];

	ChessBoard() {
		buildFrame();
		grid = buildGridPanels();
		window.add(grid);
		window.setVisible(true);
	}

	private void buildFrame() {
		window = new JFrame("Practicing");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH, HEIGHT));
		// could set min, max, and preferred dimensions, I think
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
		p.setLayout(new GridLayout(ROWS,COLS));
		Color bg;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				bg = getPanelColor(r,c);           
				ChessSquarePanel sq = new ChessSquarePanel(bg, false);
				spaces[r][c] = sq;
				p.add(sq);
			}
		}
		return p;
	}

	public static void main(String[] args) {
		ChessBoard pg = new ChessBoard();
	}
}