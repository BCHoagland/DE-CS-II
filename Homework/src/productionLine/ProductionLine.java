package productionLine;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <h1>ProductionLine Class</h1>
 * Represents two queues of disks and towers that are being processed by a robot arm
 * @author HoaglandB1
 *
 */
public class ProductionLine {
	
	/**
	 * queue of disks to be processed
	 */
	private Queue<Disk> inputQueue;
	
	/**
	 * disks of processed towers
	 */
	private Queue<Tower> outputQueue;
	
	/**
	 * tower that is currently being processed by the robot arm
	 */
	private Tower currentPyramid;
	
	/**
	 * constructor that instantiates the queues and the robot arm's tower
	 */
	public ProductionLine() {
		this.inputQueue = new LinkedList<Disk>();
		this.outputQueue = new LinkedList<Tower>();
		this.currentPyramid = new Tower();
	}
	
	/**
	 * sets the robot arm's tower to the given Tower t
	 * @param t tower to be given to the robot arm
	 */
	public void setPyramid(Tower t) {
		this.currentPyramid = t;
	}
	
	/**
	 * adds a disk to the input queue
	 * @param disk to be added to queue for processing
	 */
	public void addDisk(Disk disk) {
		inputQueue.add(disk);
	}
	
	/**
	 * moves the robot arm's tower to the output queue
	 */
	public void unloadRobot() {
		this.currentPyramid.flip();
		outputQueue.add(this.currentPyramid);
		this.currentPyramid = new Tower();
	}
	
	/**
	 * processes all disks, moving them into towers in the output queue
	 */
	public void process() {
		while (!inputQueue.isEmpty()) {
			Disk tempDisk = inputQueue.remove();
			if (!this.currentPyramid.isEmpty()) {
				if (tempDisk.compareTo(this.currentPyramid.peek()) < 0) {
					unloadRobot();
				}
			}
			currentPyramid.push(tempDisk);
		}
		if (!currentPyramid.isEmpty()) {
			unloadRobot();
		}
	}
	
	/**
	 * removes and returns the top tower in the output queue
	 * @return top tower in the output queue
	 */
	public Tower removeTower() {
		if (!outputQueue.isEmpty()) {
			return outputQueue.remove();
		} else {
			return null;
		}
	}
	
	/**
	 * returns a String representation of the production line
	 */
	public String toString() {
		return "input: " + this.inputQueue + "\nloaded: " + this.currentPyramid + "\noutput: " + this.outputQueue;
	}
}