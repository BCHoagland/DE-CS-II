package knapsack;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Knapsack {

	/*private static int[] testCapacities = {165, 26, 190, 750};
	private static int[][] testWeights = {
			{23, 31, 29, 44, 53, 38, 63, 85, 89, 82},
			{12, 7, 11, 8, 9},
			{56, 59, 80, 64, 75, 17},
			{70, 73, 77, 80, 82, 87, 90, 94, 98, 106, 110, 113, 115, 118, 120}};
	private static int[][] testValues = {
			{92, 57, 49, 68, 60, 43, 67, 84, 87, 72},
			{24, 13, 23, 15, 16},
			{50, 50, 64, 46, 50, 5},
			{135, 139, 149, 150, 156, 163, 173, 184, 192, 201, 210, 214, 221, 229, 240}};
	private static int[] testAnswerValues = {309, 51, 150, 1458};
	private static int[][] testAnswerSets = {
			{1, 1, 1, 1, 0, 1, 0, 0, 0, 0},
			{0, 1, 1, 1, 0},
			{1, 1, 0, 0, 1, 0},
			{1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1}};*/
	
	private static ArrayList<String> testFileNames = new ArrayList<String>();
	
	private static ArrayList<Integer> set = new ArrayList<Integer>();
	
	private static void setTestFileNames() {
		//TODO: ASK FOR USER INPUT TO DETERMINE NAME OF FILE WITH TEST FILES
		Scanner testFiles = null;
		try {
			testFiles = new Scanner(new File("tests.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}

		if (testFiles != null) {
			while (testFiles.hasNextLine()) {
				testFileNames.add(testFiles.nextLine().trim());
			}
			System.out.println(testFileNames);
			testFiles.close();
		}
	}
	
	private static void runKnapsackOnFile(String fileName) {
		
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

	/*private static void testKnapsackWithList() {
		for (int i = 0; i < testCapacities.length; i++) {
			set.clear();

			System.out.print("capacity: " + testCapacities[i] + "\nweights: ");
			for (int num : testWeights[i]) {
				System.out.print(num + " ");
			}
			System.out.print("\nvalues: ");
			for (int num : testValues[i]) {
				System.out.print(num + " ");
			}

			int numTypes = testWeights[i].length;
			System.out.print("\nmax value: " + knapsack(testWeights[i], testValues[i], numTypes - 1, testCapacities[i], set) + " -> correct max value: " + testAnswerValues[i]);

			System.out.print("\nobjects chosen: " + set + " -> " + "correct objects: [");
			for (int j = 0; j < numTypes; j++) {
				int multiplier = testAnswerSets[i][j];
				if (multiplier != 0) System.out.print(multiplier * testValues[i][j] + ", ");
			}
			System.out.println("]\n");
		}
	}*/
	
	public static void main(String[] args) {
//		testKnapsackWithList();
		setTestFileNames();
	}
}