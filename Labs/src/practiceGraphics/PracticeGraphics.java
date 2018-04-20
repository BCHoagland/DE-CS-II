package practiceGraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PracticeGraphics {
	
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenDimension = Math.min(screenSize.width, screenSize.height);
		
		JFrame window = new JFrame("testWindow");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(screenDimension / 2, screenDimension / 2);
		window.setLocationRelativeTo(null);
//		window.setLayout(new GridLayout(4, 4));
		window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
		window.setVisible(true);
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		p1.setBackground(new Color(255, 100, 100));
		p2.setBackground(new Color(100, 100, 255));
		p3.setBackground(new Color(100, 255, 100));
		
		p1.add(new JButton());
		p2.add(new JButton());
		p3.add(new JButton());
		
		window.add(p1);
		window.add(p2);
		window.add(p3);
	}
}