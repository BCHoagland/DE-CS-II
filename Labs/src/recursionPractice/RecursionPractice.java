package recursionPractice;

public class RecursionPractice {
	
	public static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
	
	public static int factorial(int n) {
		if (n <= 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}
	
	public static int sumDigits(int n) {
		if (n < 10) {
			return n;
		}
		return n % 10 + sumDigits(n / 10);
	}
	
	public static int count5(int n) {
		if (n < 10) {
			if (n == 5) {
				return 1;
			} else {
				return 0;
			}
		}
		if (n % 10 == 5) {
			return 1 + count5(n / 10);
		} else {
			return count5(n / 10);
		}
	}
	
	public static void upAndDown(int n) {
		if (n < 1) {
			System.out.print(n + " ");
		}
		else {
			System.out.print(n + " ");
			upAndDown(n - 1);
			System.out.print(n + " ");
		}
	}
	
	public static double max(double[] v, int n) {
		double m = v[n - 1];
		if (n > 1) {
			double m2 = max(v, n - 1);
			if (m2 > m) {
				m = m2;
			}
		}
		return m;
	}
	
	public static boolean isDivisibleBy9(int n) {
		if (n < 9) {
			return false;
		} else if (n == 9) {
			return true;
		} else {
			return isDivisibleBy9(sumDigits(n));
		}
	}
	
	public static void printTitle(String str) {
		String title = "TESTING " + str.toUpperCase();
		String border = "\n";
		for (int i = 0; i < title.length(); i++) {
			border += "=";
		}
		System.out.println(border + "\n" + title + border);
	}
	
	public static void testFibonacci() {
		printTitle("fibonacci");
		for (int i = 0; i < 10; i++) {
			int num = (int)(Math.random() * 10);
			String ending = "th";
			if (num == 1) ending = "st";
			else if (num == 2) ending = "nd";
			else if (num == 3) ending = "rd";
			System.out.println("the " + num + ending + " fibonacci number is " + fibonacci(num));
		}
	}
	
	public static void testFactorial() {
		printTitle("factorial");
		for (int i = 0; i < 10; i++) {
			int num = (int)(Math.random() * 10);
			System.out.println(num + "! = " + factorial(num));
		}
	}
	
	public static void testSumDigits() {
		printTitle("sum digits");
		for (int i = 0; i < 10; i++) {
			int num = (int)(Math.random() * 100000);
			System.out.println("the sum of the digits in " + num + " is " + sumDigits(num));
		}
	}
	
	public static void testCount5() {
		printTitle("count 5");
		for (int i = 0; i < 10; i++) {
			int num = (int)(Math.random() * 100000);
			System.out.println("the number of 5s in " + num + " is " + count5(num));
		}
	}
	
	public static void testUpAndDown() {
		printTitle("up and down");
		for (int i = 0; i < 10; i++) {
			int num = (int)(Math.random() * 10);
			System.out.print("up and down " + num + ": ");
			upAndDown(num);
			System.out.println();
		}
	}
	
	public static void testMax() {
		printTitle("max");
		for (int i = 0; i < 10; i++) {
			double[] nums = new double[10];
			for (int j = 0; j < nums.length; j++) {
				nums[j] = Math.random() * 10;
			}
			int num = (int)(Math.random() * (nums.length - 1)) + 1;
			String numsString = "{";
			for (double n : nums) {
				numsString += n + ", ";
			}
			numsString += "}";
			System.out.println("the max number of the first " + num + " numbers in " + numsString + " is " + max(nums, num));
		}
	}
	
	public static void main(String[] args) {
		testFibonacci();
		testFactorial();
		testSumDigits();
		testCount5();
		testUpAndDown();
		testMax();
		System.out.println("\nI didn't make a test method for isDivisibleBy9() oops\n" + isDivisibleBy9(90));
	}
}