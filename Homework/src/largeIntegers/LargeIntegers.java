package largeIntegers;

public class LargeIntegers {
	
	private final static int N = 30;
	
	public static void main(String[] args) {
		int[][][] tests = {
				{{2,3,4,6,3,4,9,8,6,0,1,2,1,1,9,0,7,6,5,4,0,8,6,3,8,6,9,4,6,2}, {0,5,6,3,8,1,5,6,7,0,1,2,6,9,9,0,6,5,4,3,2,7,7,8,5,4,6,9,3,1}},
				{{4,4,7,8,9,4,3,2,1,3,4,5,6,7,8,9,0,9,8,6,7,8,9,4,3,2,1,3,4,5}, {4,5,3,3,5,7,8,8,9,5,4,2,2,3,9,0,0,0,5,4,1,2,3,4,6,8,3,1,6,9}},
				{{0,0,6,4,3,2,1,3,4,7,6,5,4,3,3,9,9,8,6,4,3,2,1,1,3,4,5,6,7,7}, {5,3,2,2,2,5,6,6,7,8,9,4,3,3,5,2,8,4,8,0,5,2,5,7,9,4,3,6,9,7}}
		};
		for (int i = 0; i < tests.length; i ++) {
			int[] num1 = tests[i][0];
			int[] num2 = tests[i][1];
			int[] sum = add(num1, num2);
			
			String numStr1 = "  ";
			String numStr2 = "+ ";
			String sumStr = "  ";
			String line = "";
			
			for (int digit : num1) {numStr1 += String.valueOf(digit);}
			for (int digit : num2) {numStr2 += String.valueOf(digit);}
			for (int digit : sum) {sumStr += String.valueOf(digit);}
			for (int j = 0; j < (N + 2); j++) {
				line += "-";
			}
			
			System.out.println(numStr1);
			System.out.println(numStr2);
			System.out.println(line);
			System.out.println(sumStr);
			System.out.println("\n");
		}
	}
	
	private static int[] add(int[] a, int[] b) {
		int[] carried = new int[a.length];
		int[] sum = new int[a.length];
		
		for (int i = a.length - 1; i >= 0; i --) {
			int digitSum = a[i] + b[i] + carried[i];
			if (String.valueOf(digitSum).length() > 1) {
				int tens = digitSum / 10;
				int ones = digitSum % 10;
				sum[i] = ones;
				if (i > 0) {carried[i - 1] = tens;}
				//since the sum has to fit into N digits, we don't have to worry about carrying at the most significant digit (i = 0)
			} else {
				sum[i] = digitSum;
			}
		}
		
		return sum;
	}
}