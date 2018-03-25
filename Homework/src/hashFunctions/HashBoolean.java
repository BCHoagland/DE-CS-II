package hashFunctions;

public class HashBoolean {
	private String str;
	private boolean value;
	private HashBoolean next;
	
	public HashBoolean(String s, boolean v) {
		str = s;
		value = v;
		next = null;
	}
	
	public HashBoolean(String s, boolean v, HashBoolean next) {
		str = s;
		value = v;
		setNext(next);
	}
	
	public String getStr() {return str;}
	public boolean getValue() {return value;}
	
	public HashBoolean getNext() {return next;}
	public void setNext(HashBoolean hb) {next = hb;}
}