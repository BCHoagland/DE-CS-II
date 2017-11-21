package productionLine;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tower extends Stack<Disk> {
	
	private static final long serialVersionUID = 1L;
	
//	private boolean isRegular;
	
	//THIS NEVER DOES ANYTHING^^^^^^^^^^^^^^^^^^^^
	
	public Tower() {
		super();
//		this.isRegular = false;
	}
	
//	public boolean isRegular() {
//		return this.isRegular;
//	}
	
//	public boolean isInverted() {
//		return !this.isRegular;
//	}
	
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
		
//		if (this.isRegular) {
//			this.isRegular = false;
//		} else {
//			this.isRegular = true;
//		}
	}
}