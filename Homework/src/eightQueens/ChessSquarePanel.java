package eightQueens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel {
	
	private final static int FONTSIZE = 20;
	
	private Color bgColor;
	private boolean hasQueen;
	
	public ChessSquarePanel(Color c, boolean b) {
		bgColor = c;
		hasQueen = b;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
		this.setBackground(bgColor);
		g.setColor(Color.RED);

		if (hasQueen) {
			int x = (this.getWidth() / 2) - FONTSIZE/4;
			int y = (this.getHeight() / 2) + FONTSIZE/4;
			g.drawString("Q", x, y);
		}
	}
	
	public void setColor(Color c) {
		bgColor = c;
		repaint();
	}
}