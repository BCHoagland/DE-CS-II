package productionLine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <h1>Tower Class</h1>
 * Represents towers of Disk objects
 * @author HoaglandB1
 *
 */
public class Tower extends Stack<Disk> {
	
	/**
	 * serial version because Eclipse really wanted me to add this
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor that creates the Tower as an empty Stack
	 */
	public Tower() {
		super();
	}
	
	/**
	 * adds a Disk to the tower
	 * @param disk disk to be added to the tower
	 */
	public void addDisk(Disk disk) {
		this.push(disk);
	}
	
	/**
	 * reverses the order of all Disks in the tower
	 */
	public void flip() {
		Queue<Disk> temp = new LinkedList<Disk>();
		while (!this.isEmpty()) {
			temp.add(this.pop());
		}
		while(!temp.isEmpty()) {
			this.push(temp.remove());
		}
	}
}