package QuadraticEquation;

//import java.util.Arrays;

public class QuadraticEquation {
	
	/*
	public static void main(String[] args) {
		double[][] testValues = {{1, -2, -8}, {1, -6, 9}, {2, 4, 5}, {0, 1, 2}};
		for (double[] values : testValues) {
			double[] roots = getRoots(values[0], values[1], values[2]);
			String equation = getEquation(values[0], values[1], values[2]);
			System.out.println(equation + ": " + Arrays.toString(roots));
		}
	}
	*/
	
	public static int a;
	public static int b;
	public static int c;
	
	/**
	 * @return the a
	 */
	public static int getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public static void setA(int a) {
		QuadraticEquation.a = a;
	}

	/**
	 * @return the b
	 */
	public static int getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public static void setB(int b) {
		QuadraticEquation.b = b;
	}

	/**
	 * @return the c
	 */
	public static int getC() {
		return c;
	}

	/**
	 * @param c the c to set
	 */
	public static void setC(int c) {
		QuadraticEquation.c = c;
	}

	public QuadraticEquation(int a, int b, int c) {
		setA(a);
		setB(b);
		setC(c);
	}

	//return the equation as a String
	public static String getEquation() {
		return a + "x^2 + " + b + "x + " + c;
	}
	
	//calculate and return the roots of a quadratic equation with given vars a, b, and c
	//returns an array with length of 2 if there are real roots
	//return null if there are no real roots
	public static double[] getRoots(double a, double b, double c) {
		if (a == 0) {
			throw new IllegalArgumentException("a = 0");
		} else {
			double d = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
			if (d >= 0) {
				double root1 = (-b + d) / (2 * a);
				double root2 = (-b - d) / (2 * a);
				double[] roots = {root1, root2};
				return roots;
			}
		}
		return null;
	}

}