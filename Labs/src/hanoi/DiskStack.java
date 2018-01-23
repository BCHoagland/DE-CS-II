package hanoi;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Stack;

/**
 * A needle containing a stack of disks.
 * @author Barbara Lerner
 * @version Apr 27, 2010
 *
 */
public class DiskStack {
	// height of needles
	private static final int NEEDLE_HEIGHT = 100;

	// Height of disks
	private static final int DISK_HEIGHT = 10;

	// The actual disks
	private Stack<Rectangle2D> disks = new Stack<Rectangle2D>();
	
	// y coordinate for the top of the bottom disk
	private double baseline;
	
	// The x coordinate for the needle
	private int x;
	
	// y coordinate for the top of the top disk currently on the stack
	// When the stack is empty, this is the same as the baseline
	private double y;
	
	// Number of pixels between disks.
	private static final int DISK_GAP = 2;
	
	// The needle
	private Line2D needle;
	
	/**
	 * Creates an empty stack
	 * @param x the x coordinate where the needle is drawn.
	 * @param y the y coordinate for the bottom of the peg
	 */
	public DiskStack (int x, int y) {
		this.x = x;
		this.y = y;
		baseline = y;
		
		needle = new Line2D.Double(x, y, x, y - NEEDLE_HEIGHT);
	}
	
	/**
	 * Add a disk to the stack
	 * @param width width of the new disk
	 */
	public void pushDisk (int width) {
		Rectangle2D newDisk = new Rectangle2D.Double(0, 0, width, DISK_HEIGHT);
		pushDisk (newDisk);
	}
	
	/**
	 * Add a disk to the top of the stack
	 * @param newDisk the disk to add
	 */
	public void pushDisk (Rectangle2D newDisk) {
		double diskLeft = x - newDisk.getWidth()/2;
		double diskTop = y - DISK_HEIGHT - DISK_GAP;
		y = diskTop;
		newDisk.setFrame(diskLeft, diskTop, newDisk.getWidth(), newDisk.getHeight());
		disks.push(newDisk);
	}
	
	/**
	 * Remove the disk at the top of the stack.
	 * @return the disk that was removed.
	 */
	public Rectangle2D pop () {
		y = y + disks.peek().getHeight() + DISK_GAP;
		return disks.pop();
	}
	
	/**
	 * Remove all the disks from the stack.
	 *
	 */
	public void clear() {
		disks.clear();
		y = baseline;
	}
	
	/**
	 * Draws the needle and its disks
	 * @param g the graphics object to draw on
	 */
	public void paint(Graphics2D g) {
		g.draw(needle);

		// Violates the stack abstraction!  We need to do this
		// to draw all of the disks, not just the top one.
		Iterator<Rectangle2D> iter = disks.iterator();
		while (iter.hasNext()) {
			g.fill(iter.next());
		}
	}
}