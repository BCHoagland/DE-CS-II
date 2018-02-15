package knapsack;

public class Knapsack {
	
//	private static int[] weights = {10, 20, 30};
//	private static int[] values = {60, 100, 120};
	
	private static int[] testCapacities;
	private static int[][] testWeights;
	private static int[][] testValues;
	
	private static int knapsack(int capacity, int n, int[] weights, int[] values) {
		if (n < 0 || capacity <= 0) return 0;
		if (weights[n] > capacity) return knapsack(capacity, n - 1, weights, values);
		return Math.max(knapsack(capacity, n - 1, weights, values), values[n] + knapsack(capacity - weights[n], n, weights, values));
	}
	
	private static void setTestValues(int n) {
		int[] capacities = new int[n];
		int[][] weights = new int[n][];
		int[][] values = new int[n][];
		int numItems;
		for (int i = 0; i < n; i++) {
			capacities[i] = (int)(Math.random() * 100);
			
			numItems = (int)(Math.random() * 10) + 1;
			int[] weightsInner = new int[numItems];
			int[] valuesInner = new int[numItems];
			for (int j = 0; j < numItems; j++) {
				weightsInner[j] = (int)(Math.random() * 50) + 1;
				valuesInner[j] = (int)(Math.random() * 100) + 1;
			}
			weights[i] = weightsInner;
			values[i] = valuesInner;
		}
		
		testCapacities = capacities;
		testWeights = weights;
		testValues = values;
	}
	
	public static void main(String[] args) {
		setTestValues(4);
		for (int i = 0; i < testCapacities.length; i++) {
			System.out.print("capacity: " + testCapacities[i] + "\nweights: ");
			for (int num : testWeights[i]) {
				System.out.print(num + " ");
			}
			System.out.print("\nvalues: ");
			for (int num : testValues[i]) {
				System.out.print(num + " ");
			}
			System.out.println("\nmax value: " + knapsack(testCapacities[i], testWeights[i].length - 1, testWeights[i], testValues[i]));
			System.out.println();
		}
	}
}