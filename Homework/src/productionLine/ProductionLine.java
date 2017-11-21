package productionLine;

import java.util.LinkedList;
import java.util.Queue;

public class ProductionLine {
	
	private Queue<Disk> inputQueue;
	private Queue<Tower> outputQueue;
	private Tower currentPyramid;
	
	public ProductionLine() {
		this.inputQueue = new LinkedList<Disk>();
		this.outputQueue = new LinkedList<Tower>();
		this.currentPyramid = new Tower();
	}
	
	public void addDisk(Disk disk) {
		inputQueue.add(disk);
	}
	
	public void unloadRobot() {
		this.currentPyramid.flip();
		outputQueue.add(this.currentPyramid);
		this.currentPyramid = new Tower();
	}
	
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
		unloadRobot();
	}

	public Tower removeTower() {
		if (!outputQueue.isEmpty()) {
			return outputQueue.remove();
		} else {
			return null;
		}
	}
	
	public String toString() {
		return "input: " + this.inputQueue + "\nloaded: " + this.currentPyramid + "\noutput: " + this.outputQueue;
	}
}