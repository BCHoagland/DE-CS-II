package productionLine;

/**
 * <h1>Disk Class</h1>
 * Represents a Disk object in the production line
 * @author HoaglandB1
 *
 */
public class Disk implements Comparable<Disk> {
	
	/**
	 * size field that holds the width of each disk
	 */
	private int size;
	
	/**
	 * constructor that creates a disk with width r
	 * @param r
	 */
	public Disk(int r) {
		this.size = r;
	}
	
	/**
	 * return the width of the disk
	 * @return size: width of disk
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * returns the int representation of comparing this disk to another disk
	 */
	@Override
	public int compareTo(Disk other) {
		return (int)Math.signum(this.size - other.getSize());
	}
	
	/**
	 * return a String representation of the disk
	 */
	public String toString() {
		return String.valueOf(this.size);
	}
}