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
			return n;
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
	
	public static void main(String[] args) {
//		System.out.println(fibonacci(6));
//		System.out.println(factorial(4));
//		System.out.println(sumDigits(123));
//		System.out.println(count5(45456789));
//		upAndDown(5);
		
		double[] nums = {4, 5, 6, 7, 3, 6};
		System.out.println(max(nums, 5));
		
		System.out.println(isDivisibleBy9(90));
	}
}