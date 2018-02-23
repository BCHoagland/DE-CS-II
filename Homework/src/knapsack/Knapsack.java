package knapsack;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Knapsack {
	
	private static ArrayList<Integer> set = new ArrayList<Integer>();
	
	private static ArrayList<String> getTestFileNames() {
		//TODO: ASK FOR USER INPUT TO DETERMINE NAME OF FILE WITH TEST FILES
		ArrayList<String> testFileNames = new ArrayList<String>();
		
		Scanner testFiles = null;
		try {
			testFiles = new Scanner(new File("tests.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}

		if (testFiles != null) {
			while (testFiles.hasNextLine()) {
				testFileNames.add(testFiles.nextLine().trim());
			}
			System.out.println(testFileNames);
			testFiles.close();
		}
		return testFileNames;
	}
	
	private static int runKnapsackOnFile(String fileName) {
		int limit = -1;
		ArrayList<Integer> weights = new ArrayList<Integer>();
		ArrayList<Integer> values = new ArrayList<Integer>(); 
		
		Scanner lines = null;
		try {
			lines = new Scanner(new File(fileName));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}
		
		if (lines != null) {
			int n = 0;
			while (lines.hasNextInt()) {
				if (limit == -1) limit = lines.nextInt();
				else if (n % 2 == 0) {
					weights.add(lines.nextInt());
					n++;
				} else {
					values.add(lines.nextInt());
					n++;
				}
			}
		}
		
		int[] w = new int[weights.size()];
		int[] r = new int[values.size()];
		for (int i = 0; i < weights.size(); i++) {
			w[i] = weights.get(i);
			r[i] = values.get(i);
		}
		
		return knapsack(w, r, w.length - 1, limit, set);
	}
	
	private static void runAllTests() {
		ArrayList<String> testFiles = getTestFileNames();
		
		int maxValue;
		for (String fileName : testFiles) {
			maxValue = runKnapsackOnFile(fileName);
			System.out.println(maxValue + ", " + set);
		}
	}
	
	private static int knapsack(int[] w, int[] r, int n, int limit) {
		if (n < 0 || limit <= 0) {
			return 0;
		}

		if (w[n] > limit) {
			return knapsack(w, r, n - 1, limit);
		}
		
		int incCurrent = r[n] + knapsack(w, r, n - 1, limit - w[n]);
		int excCurrent = knapsack(w, r, n - 1, limit);
		return Math.max(incCurrent, excCurrent);
	}
	
	private static int knapsack(int[] w, int[] r, int n, int limit, ArrayList<Integer> list) {
		int maxValue = knapsackHelper(limit, n, w, r, list);
		Collections.reverse(set);
		return maxValue;
	}
	
	private static int knapsackHelper(int capacity, int n, int[] weights, int[] values, ArrayList<Integer> list) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
//		list1.add(0);
//		list2.add(1);
		
		if (n < 0) {
			return 0;
		}
		
		list2.add(values[n]);

		if (capacity <= 0) {
			list.addAll(list1);
			return 0;
		}

		if (weights[n] > capacity) {
			int excCurrent = knapsackHelper(capacity, n - 1, weights, values, list1);
			list.addAll(list1);
			return excCurrent;
		}
		
		int incCurrent = values[n] + knapsackHelper(capacity - weights[n], n - 1, weights, values, list2);
		int excCurrent = knapsackHelper(capacity, n - 1, weights, values, list1);
		if (incCurrent > excCurrent) {
			list.addAll(list2);
			return incCurrent;
		}
		list.addAll(list1);
		return excCurrent;
	}

	public static void main(String[] args) {
		runAllTests();
		System.out.println("Results should be: 309, 51, 150, 1458");
	}
}