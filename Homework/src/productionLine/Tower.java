package productionLine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tower extends Stack<Disk> {
	
	private static final long serialVersionUID = 1L;
	
	//	private Stack<Disk> pyramid;
	private boolean isRegular;
	
	public Tower() {
//		this.pyramid = new Stack<Disk>();
		super();
//		this = new Stack<Disk>();
		this.isRegular = false;
	}
	
//	public Stack<Disk> getPyramid() {
//		return this.pyramid;
//	}
	
	public boolean isRegular() {
		return this.isRegular;
	}
	
	public boolean isInverted() {
		return !this.isRegular;
	}
	
	public void addDisk(Disk disk) {
		this.push(disk);
	}
	
	public void flip() {
		Queue<Disk> temp = new LinkedList<Disk>();
		while (!this.isEmpty()) {
			temp.add(this.pop());
		}
		while(!temp.isEmpty()) {
			this.push(temp.remove());
		}
		
		if (this.isRegular) {
			this.isRegular = false;
		} else {
			this.isRegular = true;
		}
	}
}