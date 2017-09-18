package quadraticEquation;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <h1>TestQuadraticEquation class to test the functionalities of the QuadraticEquation class and the QuadraticComparator class</h1><br/>
 * @author Braden Hoagland
 * @since September 12, 2017
 *
 */
public class TestQuadraticEquation {
	
	/**
	 * Main method to run through test values and prompt for user input
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		//check if user wants to run through tests
		System.out.println("Test? y/n");
		String test = kb.nextLine();
		if (test.equals("y")) {
			test();
		}
		
		//read in a, b, and c values from user
		System.out.println("Enter in three integers for the quadratic equation coefficients:");
		int a = kb.nextInt();
		int b = kb.nextInt();
		int c = kb.nextInt();
		
		//close the keyboard
		kb.close();
		
		//use inputted values to make a new QuadraticEquation object and print its roots
		QuadraticEquation eq = new QuadraticEquation(a, b, c);
		System.out.println(eq);
		System.out.println(Arrays.toString(eq.getRoots()));
	}
	
	/**
	 * Method to run through test values and print out the results of the operations
	 */
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
		
		//create and print a list of test QuadraticEquation objects
		System.out.print("NORMAL: \t");
		for (int i = 0; i < eqs.length; i++) {
			int[] values = testValues[i];
			QuadraticEquation eq = new QuadraticEquation(values[0], values[1], values[2]);
			eqs[i] = eq;
			System.out.print(eq + "\t");
		}
		
		//sort and print an ascending list
		Arrays.sort(eqs, new QuadraticComparator(true));
		
		System.out.println();
		System.out.print("ASCENDING: \t");
		for (QuadraticEquation eq : eqs) {
			System.out.print(eq + "\t");
		}
		
		//sort and print a descending list
		Arrays.sort(eqs, new QuadraticComparator(false));
		
		System.out.println();
		System.out.print("DESCENDING: \t");
		for (QuadraticEquation eq : eqs) {
			System.out.print(eq + "\t");
		}
		
		//print out the roots of each of the test QuadraticEquation objects
		System.out.println("\n");
		for (QuadraticEquation eq: eqs) {
			System.out.println(eq.toString() + ": " + Arrays.toString(eq.getRoots()));
		}
	}
}