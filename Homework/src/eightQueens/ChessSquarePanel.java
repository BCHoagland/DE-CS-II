package eightQueens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel {
	
	private final static int FONTSIZE = 60;
	
	private Color bgColor;
	private boolean hasQueen;
	private String letter;
	
	public ChessSquarePanel(Color c, boolean b) {
		bgColor = c;
		hasQueen = b;
		letter = "Q";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("Calibri", Font.BOLD, FONTSIZE));
		this.setBackground(bgColor);
		g.setColor(Color.RED);

		if (hasQueen) {
			int x = (this.getWidth() / 2) - FONTSIZE/3;
			int y = (this.getHeight() / 2) + FONTSIZE/4;
			g.drawString(letter, x, y);
		}
	}
	
	public void setColor(Color c) {
		bgColor = c;
		repaint();
	}
	
	public void setQueenStatus(boolean b) {
		hasQueen = b;
		repaint();
	}
	
//	public void setLetter(String str) {
//		letter = str;
//		repaint();
//	}
}