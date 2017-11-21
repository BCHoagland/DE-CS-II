package productionLine;

public class Disk implements Comparable<Disk> {
	
	private int size;
	
	public Disk(int r) {
		this.size = r;
	}
	
	public int getSize() {
		return size;
	}

	@Override
	public int compareTo(Disk other) {
		return (int)Math.signum(this.size - other.getSize());
	}
	
	public String toString() {
		return "r -> " + size;
	}
}