package quadraticEquation;

import java.util.Comparator;

/**
 * <h1>QuadraticComparator class to order QuadraticEquation objects</h1><br/>
 * @author Braden Hoagland
 * @since September 12, 2017
 *
 */
public class QuadraticComparator implements Comparator<QuadraticEquation> {
	
	/**
	 * boolean that tracks whether the QuadraticEquation objects should be sorted in ascending order
	 */
	public static boolean ascending;
	
	/**
	 * Main constructor that initializes the ascending boolean to true
	 */
	public QuadraticComparator() {
		ascending = true;
	}
	
	/**
	 * Constructor that initializes the ascending boolean to a given value
	 * @param ascend
	 */
	public QuadraticComparator(boolean ascend) {
		ascending = ascend;
	}
	
	/**
	 * compares two QuadraticEquation objects and returns the result
	 */
	public int compare(QuadraticEquation eq1, QuadraticEquation eq2) {
		if (ascending) {
			return eq1.compareTo(eq2);
		} else {
			return eq2.compareTo(eq1);
		}
	}
}