package quadraticEquation;

import java.util.Arrays;

public class quadraticEquation {

	public static void main(String[] args) {
		double[][] testValues = {{1, -2, -8}, {1, -6, 9}, {2, 4, 5}, {0, 1, 2}};
		for (double[] values : testValues) {
			double[] roots = getRoots(values[0], values[1], values[2]);
			String equation = getEquation(values[0], values[1], values[2]);
			System.out.println(equation + ": " + Arrays.toString(roots));
		}
	}
	
	//return the equation as a String
	public static String getEquation(double a, double b, double c) {
		String aStr = formatNum(a, true);
		String bStr = formatNum(b, true);
		String cStr = formatNum(c, false);
		
		return aStr + "x^2 + " + bStr + "x + " + cStr;
	}
	
	//make numbers into an integer if possible
	//if number is a coefficient and has a magnitude of 1, remove the number and keep the sign
	public static String formatNum(double num, boolean isCoefficient) {
		String str = (num % 1 == 0) ? String.valueOf((int)num) : String.valueOf(num);
		if (isCoefficient) {
			if (str.equals("1") || str.equals("-1")) {
				str = str.substring(0, (str.length() - 1));
			}
		}
		return str;
	}
	
	//calculate and return the roots of a quadratic equation with given vars a, b, and c
	//returns an array with length of 2 if there are real roots
	//return null if there are no real roots
	public static double[] getRoots(double a, double b, double c) {
		if (a == 0) {
			throw new IllegalArgumentException(getEquation(a, b, c) + ": a = 0");
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