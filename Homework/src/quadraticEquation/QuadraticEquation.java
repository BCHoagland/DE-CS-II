package quadraticEquation;

//import java.util.Arrays;

public class QuadraticEquation implements Comparable<QuadraticEquation> {

	public int a;
	public int b;
	public int c;

	/**
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(int a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public int getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(int b) {
		this.b = b;
	}

	/**
	 * @return the c
	 */
	public int getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(int c) {
		this.c = c;
	}

	/**
	 * constructor to initialize the three coefficient values
	 * 
	 * @param a
	 * @param b
	 * @param c
	 */
	public QuadraticEquation(int a, int b, int c) {
		setA(a);
		setB(b);
		setC(c);
	}

	/**
	 * return a string representation of the QuadraticEquation
	 */
	public String toString() {
		String aStr = String.valueOf(a) + "x^2";
		String bStr = String.valueOf(b) + "x";
		String cStr = String.valueOf(c);
		
		if (a == 1) {
			aStr = "x^2";
		}
		if (b == 1) {
			bStr = "x";
		}
		
		if (b >= 0) {
			bStr = "+" + bStr;
		}
		if (c >= 0) {
			cStr = "+" + cStr;
		}
		
		return "\n" + aStr + bStr + cStr;
//		return "\n" + a + "x^2" + b + "x + " + c;
	}

	/**
	 * calculate and return the roots of a quadratic equation with given coefficients
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return roots an array of the two roots or null
	 */
	public double[] getRoots() {
		if (a == 0) {
			throw new IllegalArgumentException("a = 0");
		} else {
			double d = Math.sqrt(Math.pow(b, 2) - (4 * a * c));
			if (d >= 0) {
				double root1 = (-b + d) / (2 * a);
				double root2 = (-b - d) / (2 * a);
				double[] roots = { root1, root2 };
				return roots;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object anyObject) {
		QuadraticEquation otherEq = (QuadraticEquation) anyObject;
		if ((this.getA() == otherEq.getA()) && (this.getB() == otherEq.getB()) && (this.getC() == otherEq.getC())) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(QuadraticEquation anotherEq) {
		int diffA = (int)(this.getA() - anotherEq.getA());
		int diffB = (int)(this.getB() - anotherEq.getB());
		int diffC = (int)(this.getC() - anotherEq.getC());
		
		if (this.equals(anotherEq)) {
			return 0;
		} else {
			if (diffA != 0) {
				return diffA;
			} else if (diffB != 0) {
				return diffB;
			} else {
				return diffC;
			}
		}
	}
}