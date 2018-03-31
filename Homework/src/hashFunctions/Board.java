package hashFunctions;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Board Class <br/>
 * used to create, display, and change a tic tac toe board
 * @author Braden Hoagland
 */
abstract class Board extends JFrame implements ActionListener {

	/**
	 * array representing the spaces of the board
	 */
	private JButton buttons[][];

	/**
	 * label displaying the hash code of the current board
	 */
	private JLabel lblHashCode;

	/**
	 * label displaying whether or not the current board is a winner
	 */
	private JLabel lblWinTitle;

	/**
	 * string representing the current board with spaces, o's, and x's
	 */
	private String boardString = "";

	/**
	 * constructor that sets up the GUI for the board
	 * @param title
	 */
	public Board(String title) {
		super(title);
		setupFrame();
	}

	/**
	 * set the hash code label to the given number
	 * @param hashcode hash code to display
	 */
	public void setHashCodeLabel(int hashcode) {
		lblHashCode.setText("" + hashcode);
	}

	/**
	 * set the winner label to the given string
	 * @param result winner or loser value to display
	 */
	public void setWinnerLabel(String result) {
		lblWinTitle.setText(result);
	}

	/**
	 * set the winner label based on the given boolean value
	 * @param result true if the board is a winner, false otherwise
	 */
	public void setWinnerLabel(boolean result) {
		if (result)
			setWinnerLabel("Winner");
		else
			setWinnerLabel("Loser");
	}

	// required because of abstract method, but not used
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * create the panel with the labels for the board
	 * @return JPanel that was just created
	 */
	JPanel setupPanelOne() {
		JPanel panel = new JPanel();
		JLabel lblHCTitle = new JLabel("Hash Code");
		;
		lblHashCode = new JLabel("" + myHashCode());
		lblWinTitle = new JLabel(""); // Will say either Winner or Loser
		setWinnerLabel(TicTacToe.isWin(boardString));
		panel.setLayout(new FlowLayout());
		panel.add(lblHCTitle);
		panel.add(lblHashCode);
		panel.add(lblWinTitle);
		return panel;
	}

	/**
	 * create the panel with the buttons representing the spaces on the board
	 * @return JPanel that was just created
	 */
	private JPanel setupPanelTwo() {
		JButton b;
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(TicTacToe.ROWS, TicTacToe.COLS));
		buttons = new JButton[TicTacToe.ROWS][TicTacToe.COLS];

		int count = 1;

		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				b = new JButton("" + ch);
				boardString += ch;
				b.setActionCommand("" + r + ", " + c);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton) e.getSource();
						btn.setText("" + cycleValue(btn.getText().charAt(0)));
						resetBoardString();
						setHashCodeLabel(myHashCode());
						setWinnerLabel(isWin());

					}
				});
				panel.add(b);
				buttons[r][c] = b;
			}

		return panel;
	}

	/**
	 * iterate through to the next character based on the given character (doesn't actually update button)
	 * called on button press
	 * @param ch current character on the button
	 * @return next character to display on the button
	 */
	private static char cycleValue(char ch) {
		switch (ch) {
		case 'x':
			return 'o';
		case 'o':
			return ' ';
		default:
			return 'x';
		}
	}

	/**
	 * setup the two panels of the board GUI
	 */
	private void setupFrame() {
		JPanel panel2 = new JPanel();

		// Setup Frame
		super.setSize(250, 200);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		// Setup Panels
		panel2 = setupPanelTwo(); // panelOne displays a value that requires panelTwo to be ready
		super.add(setupPanelOne());
		super.add(panel2);

		super.setVisible(true);
	}

	/**
	 * randomly return either a space, o, or x
	 * @return randomly chosen character
	 */
	private char randomXO() {
		int rnd = (int) (Math.random() * TicTacToe.CHAR_POSSIBILITIES);
		switch (rnd) {
		case 1:
			return 'x';
		case 2:
			return 'o';
		default:
			return ' ';
		}
	}

	/**
	 * hash code function to be implemented in TicTacToeHashCode
	 * @return hash of the current board
	 */
	abstract int myHashCode();

	/**
	 * function to be implemented in TicTacToeHashCode that determines if the given board string is a winning setup
	 * @param s board string to check
	 * @return true if the given board is a winner, false otherwise
	 */
	abstract boolean isWin(String s);

	/**
	 * function to be implemented in TicTacToeHashCode that determines if the current board is a winning setup
	 * @return true if the current board is a winner, false otherwise
	 */
	abstract boolean isWin();

	/**
	 * find the character at the given coordinates
	 * @param row row of the character
	 * @param col column of the character
	 * @return character at the intersection of the given row and column
	 */
	public char charAt(int row, int col) {
		String value = buttons[row][col].getText();
		if (value.length() > 0)
			return value.charAt(0);
		else
			return '*';
	}

	/**
	 * find the character at the given coordinate of the board representing by the given string
	 * @param s string representing a board
	 * @param row row of the character
	 * @param col column of the character
	 * @return character at the row-col intersection of the given board
	 */
	public char charAt(String s, int row, int col) {
		int pos = row * TicTacToe.COLS + col;
		if (s.length() >= pos)
			return s.charAt(pos);
		else
			return '*';   
	}

	/**
	 * update the board buttons to display the given string
	 * @param s string of characters to display on the buttons
	 */
	public void show(String s) {
		int pos = 0;
		String letter;
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = s.charAt(pos);
				switch (ch) {
				case '1':
					letter = "x";
					break;
				case '2':
					letter = "o";
					break;
				case '0':
					letter = " ";
					break;
				default:
					letter = "" + ch;
				}
				buttons[r][c].setText(letter);
				pos++;
			}
	}

	/**
	 * set the board string to the current characters on the board's buttons
	 */
	public void resetBoardString() {
		boardString = "";
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				boardString += buttons[r][c].getText();
			}
	}

	/**
	 * set the board string field to the given string and update the board buttons to display it
	 * @param s board string to display
	 */
	public void setBoardString(String s) {
		boardString = s;
		show(s);
	}

	/**
	 * get the board string field of the current board
	 * @return board string field of the current board
	 */
	public String getBoardString() {
		return boardString;
	}

	/**
	 * display a random string on the board buttons and update the labels to show its hash code and its winner/loser status
	 */
	public void displayRandomString() {
		for (int r = 0; r < TicTacToe.ROWS; r++)
			for (int c = 0; c < TicTacToe.COLS; c++) {
				char ch = randomXO();
				boardString += ch;
				buttons[r][c].setText("" + ch);
			}
		setHashCodeLabel(myHashCode());
		setWinnerLabel(isWin());
	}

}