package hashFunctions;

public class Obj {
	
	public int id;
	public String name;
	
	public Obj(int i, String n) {
		id = i;
		name = n;
	}
	
	public int hashCode() {
		return id;
	}
}