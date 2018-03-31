package hashFunctions;

public class FunkyString {
	private String str;
	
	public FunkyString(String s) {
		str = s;
	}
	
	public String getStr() {
		return str;
	}
	
	public int prehash(char ch) {
		switch (ch) {
		case ' ':
			return 1;
		case 'o':
			return 0;
		case 'x':
			return 103;
		default:
			return -1;
		}
	}
	
	public int hash(String str) {
		int[] pows3 = {1, 3, 9, 27, 81, 243, 729, 2187, 6561};
		int hash = 0;
		int i = 0;
		for (char ch: str.toCharArray()) {
			hash += prehash(ch) * pows3[i];
			i++;
		}
		return hash % 2048;
	}
	
	public int hashCode() {
		return hash(str);
	}
}