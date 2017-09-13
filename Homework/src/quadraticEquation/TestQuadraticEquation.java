package quadraticEquation;

import java.util.Arrays;
import java.util.Scanner;

public class TestQuadraticEquation {
	
	public static void main(String[] args) {
		test();
		
		/*Scanner kb = new Scanner(System.in);
		double a = kb.nextDouble();
		double b = kb.nextDouble();
		double c = kb.nextDouble();
		
		QuadraticEquation eq = new QuadraticEquation(a, b, c);
		
		System.out.println(eq);
		System.out.println(Arrays.toString(eq.getRoots()));
		
		kb.close();*/
	}
	
	public static void test() {
		int[][] testValues = {
				{1, -2, -8},
				{1, -6, 9},
				{1, -6, 10},
				{2, 4, 5},
				{0, 1, 2},
				{0, 0, 0},
				{0, 4, 0}
		};
		
		QuadraticEquation[] eqs = new QuadraticEquation[testValues.length];
		
		for (int i = 0; i < eqs.length; i++) {
			int[] values = testValues[i];
			QuadraticEquation eq = new QuadraticEquation(values[0], values[1], values[2]);
			eqs[i] = eq;
		}
		
		System.out.println(Arrays.toString(eqs));
		
		Arrays.sort(eqs, new QuadraticComparator(true));
		
		System.out.println(Arrays.toString(eqs));
	}
}