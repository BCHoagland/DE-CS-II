package Numbers;

import java.util.Scanner;

public class Numbers {

	public static void main(String[] args) {
		test();
		
		System.out.println("Enter in two numbers:");
		Scanner kb = new Scanner(System.in);
		
		int num1 = kb.nextInt();
		int num2 = kb.nextInt();
		
		getSum(num1, num2);
	}
	
	//find the sum of two given numbers, return it, and print it to the console
	public static int getSum(int num1, int num2) {
		int sum = num1 + num2;
		printSum(num1, num2, sum);
		return (sum);
	}
	
	//print given nums and sum to the console as a formatted sentence
	public static void printSum(int num1, int num2, int sum) {
		System.out.printf("The sum of %d and %d is %d\n", num1, num2, sum);
	}
	
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
		}
		System.out.println("-------\n");
	}
}