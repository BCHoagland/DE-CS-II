package Numbers;

import java.util.Scanner;

/**
* @author  Braden Hoagland
* @version 1.0
* @since   September 7, 2017
*/
public class Numbers {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		test();
		
		System.out.println("Enter in two numbers:");
		Scanner kb = new Scanner(System.in);
		
		int num1 = kb.nextInt();
		int num2 = kb.nextInt();
		
		getSum(num1, num2);
		getDifference(num1, num2);
		getProduct(num1, num2);
		getQuotient(num1, num2);
		getModulus(num1, num2);
		kb.close();
	}
	
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return sum of num1 and num2
	 */
	//find the sum of two given numbers, return it, and print it to the console
	public static int getSum(int num1, int num2) {
		int sum = num1 + num2;
		printOperation("sum", num1, num2, sum);
		return (sum);
	}
	
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return different of num1 and num2
	 */
	//find the modulus of two given numbers, return it, and print it to the console
	public static int getDifference(int num1, int num2) {
		int diff = num1 - num2;
		printOperation("difference", num1, num2, diff);
		return (diff);
	}
	
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return product of num1 and num2
	 */
	//print given nums and sum to the console as a formatted sentence
	public static int getProduct(int num1, int num2) {
		int product = num1 * num2;
		printOperation("product", num1, num2, product);
		return (product);
	}
	
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return quotient of num1 and num2
	 */
	//print given nums and sum to the console as a formatted sentence
	public static int getQuotient(int num1, int num2) {
		if (num2 != 0) {
			int quotient = num1 / num2;
			printOperation("quotient", num1, num2, quotient);
			return (quotient);
		} else {
			System.out.printf("Cannot divide %d by %d: division by 0\n", num1, num2);
			return 0;
		}
	}
	
	/**
	 * getModulus() finds the modulus of two numbers and prints it
	 * 
	 * @param num1
	 * @param num2
	 * @return modulus of num1 and num2
	 */
	//print given nums and sum to the console as a formatted sentence
	public static int getModulus(int num1, int num2) {
		if (num2 != 0) {
			int modulus = num1 % num2;
			printOperation("modulus", num1, num2, modulus);
			return (modulus);
		} else {
			System.out.printf("cannot find modulus of %d and %d: division by 0", num1, num2);
			return 0;
		}
	}
	
	/**
	 * 
	 * printOperations() formats and prints the given information
	 * 
	 * @param operation
	 * @param num1
	 * @param num2
	 * @param result
	 */
	//print given nums and sum to the console as a formatted sentence
	public static void printOperation(String operation, int num1, int num2, int result) {
		System.out.printf("The %s of %d and %d is %d\n", operation, num1, num2, result);
	}
	
	/**
	 * test() runs a series of test inputs through the class and prints the results
	 */
	//run through test values of the getSum() method
	public static void test() {
		System.out.println("TESTING\n-------");
		int[][] testValues = {
				{1, 2},
				{-4, 5},
				{4, -5},
				{-10, -4},
				{0, 0},
				{0, 10},
				{7, 0}
		};
		for (int[] nums : testValues) {
			getSum(nums[0], nums[1]);
			getDifference(nums[0], nums[1]);
			getProduct(nums[0], nums[1]);
			getQuotient(nums[0], nums[1]);
			getModulus(nums[0], nums[1]);
			System.out.println();
		}
		System.out.println("-------\n");
	}
}
