package hanoi;

/* Program hanoi
 * Graphic program to solve towers of hanoi puzzle
 * WRITTEN BY: Kim Bruce			DATE LAST MODIFIED: 10/18/88
 * INPUT: Number of golden disks
 * OUTPUT:  Instructions on how to solve puzzle, and animation of
 * solution.  
 * 
 * Barbara Lerner   March 17, 2005
 * Modified to use recursive data structures to manage the needles.
 * 
 * Barbara Lerner   April 25, 2007
 * Modified to use Java's stack generic to manage the needles.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * Component that draws and animates the Towers of Hanoi game
 * @author Barbara Lerner
 * @version Apr 27, 2010
 *
 */
public class HanoiPanel extends JComponent implements Runnable
{
	// milliseconds per frame.  Actual speed of animation can be adjusted by the
	// use of a slider in the GUI.  This is its slowest speed.
	private static final int SPEED_CONST = 12000;
	
	// Width of smallest disk
	private static final int SMALLEST_DISK_WIDTH = 6;	
	
	// Difference in width between successive disks
	private static final int DISK_WIDTH_UNIT = 15;		

	// pause between moves
	private int delay;				
	
	// number of disks currently in use
	private int numberOfDisks;		
	
	// The number of needles.
	private static final int NUM_NEEDLES = 3;
	
	// The needles that the disks go on.
	private DiskStack[] needles = new DiskStack[NUM_NEEDLES];
	
	// Controls whether text trace should be displayed
	private boolean printing = true;
	
	/**
	 * Create a panel displaying the Towers of Hanoi
	 * @param numDisks number of disks to use
	 * @param windowWidth width of the window
	 * @param baseLine height where bottom disk should be drawn
	 */
	public HanoiPanel(int numDisks, int windowWidth, int baseLine)
	{
		int firstNeedleX = windowWidth/ 6;
		
		// Create the stacks to hold the disks and draw the lines to represent the needles
		// that the disks are placed on.
		for (int i = 0; i < NUM_NEEDLES; i++) {
			int x = firstNeedleX + 2 * i * firstNeedleX;
			needles[i] = new DiskStack(x, baseLine);
		}
		
		setDisks(numDisks);
	}
	
	/**
	 * Draw the disk stacks
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		// Draw white background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw the disk stacks and needles
		g.setColor(Color.BLACK);
		for (int i = 0; i < NUM_NEEDLES; i++) {
			needles[i].paint((Graphics2D) g); 
		}
	}
	
	/**
	 * initiate animation of towers of hanoi
	 */
	public void run()
	{
		try {
			Thread.sleep(2*delay);
			recHanoi(numberOfDisks, needles[0], needles[2], needles[1]);
		} catch (InterruptedException e) {
			// Just return
		}
		
	}
	
	/**
	 * Recursively solves puzzle.  Moves all but the bottom disk from the first to last needle.
	 * Then moves the bottom disk.  Then moves the pile first moved to be on top of the bottom 
	 * disk.<P>
	 * 
	 * Base case is when there is only one disk on a needle, in which case it can be moved
	 * by itself.
	 * @param numDisks number of disks to move
	 * @param first needle to move from
	 * @param last needle to move to
	 * @param helper needle to put top n-1 disks on
	 * @throws InterruptedException if the animation is interrupted
	 */
	private void recHanoi (int numDisks, DiskStack first, DiskStack last, DiskStack helper) throws InterruptedException
	{
		// Base case.  Simply move the 1 disk
	   // Move top n-1 disks to the helper needle
   	// Move the bottom disk
		// Move the n-1 disks on top of the bottom disk in its new location
		
		
	}
	
	/**
	 * Moves top from first needle to last
	 * @param first stack to move from
	 * @param last stack to move to
	 * @throws InterruptedException if the animation is interrupted
	 */
	private void moveDisk (DiskStack first, DiskStack last) throws InterruptedException
	{
		// print move if desired
		if (printing) {
			System.out.println("Move disk from needle " + first + " to needle " + last + ".");
		}
		
		// Move the disk
		Rectangle2D diskToMove = first.pop();
		last.pushDisk(diskToMove);
		
		// Repaint and delay to let user see movement
		repaint();
		Thread.sleep(delay);
	}
	
	/**
	 * Controls the speed of the animation
	 * @param speed higher numbers will speed the animation
	 */
	public void setSpeed(int speed)
	{
		delay = SPEED_CONST/speed;
	}
	
	/**
	 * Remove all the disks from all the needles.
	 *
	 */
	public void clear()
	{
		for (int i = 0; i < NUM_NEEDLES; i++) {
			needles[i].clear();
		}
		repaint();
	}

	/**
	 * Initializes the needles 
	 * @param numDisks number of disks to put on first needle.  All other needles are empty.
	 */
	public void setDisks(int numDisks) {
		assert numDisks > 0;
		this.numberOfDisks = numDisks;
		
		// Place disks on first needle
		for (int diskNum = numberOfDisks; diskNum > 0; diskNum--)
		{
			int diskOffset = SMALLEST_DISK_WIDTH + (diskNum * DISK_WIDTH_UNIT) / 2;
			needles[0].pushDisk (2*diskOffset);
		}
		repaint();
	}
	
}
