package quadraticEquation;

import java.util.Comparator;

public class QuadraticComparator implements Comparator<QuadraticEquation> {
	
	public static boolean ascending;
	
	public QuadraticComparator() {
		ascending = true;
	}
	
	public QuadraticComparator(boolean ascend) {
		ascending = ascend;
	}
	
	public int compare(QuadraticEquation eq1, QuadraticEquation eq2) {
		return eq1.compareTo(eq2);
	}
}