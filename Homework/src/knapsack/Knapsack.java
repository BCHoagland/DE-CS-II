package knapsack;

import java.util.ArrayList;
import java.util.Collections;

public class Knapsack {

	private static int[] testCapacities = {165, 26, 190, 750};
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
			{1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1}};

	private static ArrayList<Integer> sets = new ArrayList<Integer>();
	
	private static int knapsack(int capacity, int n, int[] weights, int[] values) {
		int maxValue = knapsack(capacity, n, weights, values, sets);
		Collections.reverse(sets);
		return maxValue;
	}
	
	private static int knapsack(int capacity, int n, int[] weights, int[] values, ArrayList<Integer> list) {
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
			list.addAll(list1);
			return knapsack(capacity, n - 1, weights, values, list1);
		}
		
		int incCurrent = values[n] + knapsack(capacity - weights[n], n - 1, weights, values, list2);
		int excCurrent = knapsack(capacity, n - 1, weights, values, list1);
		if (incCurrent > excCurrent) {
			list.addAll(list2);
			return incCurrent;
		}
		list.addAll(list1);
		return excCurrent;
	}

	public static void main(String[] args) {
		for (int i = 0; i < testCapacities.length; i++) {
			sets.clear();

			System.out.print("capacity: " + testCapacities[i] + "\nweights: ");
			for (int num : testWeights[i]) {
				System.out.print(num + " ");
			}
			System.out.print("\nvalues: ");
			for (int num : testValues[i]) {
				System.out.print(num + " ");
			}

			int numTypes = testWeights[i].length;
			System.out.print("\nmax value: " + knapsack(testCapacities[i], numTypes - 1, testWeights[i], testValues[i]) + " -> correct max value: " + testAnswerValues[i]);

			System.out.print("\nobjects chosen: " + sets + " -> " + "correct objects: [");
			for (int num : testAnswerSets[i]) {
				System.out.print(num + ", ");
			}
			System.out.println("]\n");
		}
	}
}