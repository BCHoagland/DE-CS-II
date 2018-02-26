package knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Knapsack {
	
	private static final String OUTPUT_FILE_NAME = "knapsack.txt";
	
	private static ArrayList<Integer> set = new ArrayList<Integer>();
	private static String outputStr = "";
	
	private static Scanner getScannerForFile(Scanner kb, String givenFileName) {
		String inputFileName = givenFileName;
		
		Scanner testFiles = null;
		while (testFiles == null) {
			try {
				testFiles = new Scanner(new File(inputFileName));
			} catch (FileNotFoundException ex) {
				System.out.println("Please enter the name of an actual file:");
				inputFileName = kb.next();
			}
		}
		kb.close();
		
		return testFiles;
	}
	
	private static ArrayList<String> getTestFileNames(Scanner testFiles) {
		ArrayList<String> testFileNames = new ArrayList<String>();

		if (testFiles != null) {
			while (testFiles.hasNextLine()) {
				testFileNames.add(testFiles.nextLine().trim());
			}
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
			System.out.println(fileName + " isn't an actual file so it's been skipped");
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
		
		int maxValue = knapsackSum(w, r, w.length - 1, limit, set);
		
		outputStr += getOutputStr(fileName, limit, weights, values);
		
		return maxValue;
	}
	
	private static String getOutputStr(String fileName, int limit, ArrayList<Integer> weights, ArrayList<Integer> values) {
		String finalStr = "";
		
		if (limit == -1) {
			return finalStr;
		}

		String weightsStr = weights.toString();
		String valuesStr = values.toString();
		finalStr += fileName + "\t" + limit + "\t" + weightsStr.substring(1, weightsStr.length() - 1) + "\t" + valuesStr.substring(1, valuesStr.length() - 1) + "\n";

		ArrayList<Integer> weightsWithoutRepeats = new ArrayList<Integer>();
		ArrayList<Integer> amounts = new ArrayList<Integer>();
		for (int weight : weights) {
			if (!weightsWithoutRepeats.contains(weight)) {
				weightsWithoutRepeats.add(weight);
				if (set.contains(weight)) {
					amounts.add(1);
				} else {
					amounts.add(0);
				}
			} else {
				int index = weightsWithoutRepeats.indexOf(weight);
				amounts.set(index, amounts.get(index) + 1);
			}
		}
		for (int i = 0; i < amounts.size(); i++) {
			finalStr += "\n" + amounts.get(i) + " " + weightsWithoutRepeats.get(i) + " pound watermelons";
		}

		finalStr += "\n\n\n";
		return finalStr;
	}
	
	private static void runAllTests(Scanner testFilesScanner) {
		ArrayList<String> testFiles = getTestFileNames(testFilesScanner);
		
		for (String fileName : testFiles) {
			runKnapsackOnFile(fileName);
		}
		
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(OUTPUT_FILE_NAME));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}
		
		if (output != null) {
			output.print(outputStr.trim());
			output.close();
		}
	}
	
	private static int knapsackSum(int[] w, int[] r, int n, int limit) {
		if (n < 0 || limit <= 0) {
			return 0;
		}

		if (w[n] > limit) {
			return knapsackSum(w, r, n - 1, limit);
		}
		
		int incCurrent = r[n] + knapsackSum(w, r, n - 1, limit - w[n]);
		int excCurrent = knapsackSum(w, r, n - 1, limit);
		return Math.max(incCurrent, excCurrent);
	}
	
	private static int knapsackSum(int[] w, int[] r, int n, int limit, ArrayList<Integer> list) {
		int maxValue = knapsackHelper(limit, n, w, r, list);
		Collections.reverse(set);
//		System.out.println(maxValue);
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
		
		list2.add(weights[n]);

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
		if (args.length > 0) {
			String inputFileName = args[0];
			Scanner kb = new Scanner(System.in);
			Scanner testFiles = getScannerForFile(kb, inputFileName);
			kb.close();
			runAllTests(testFiles);
		} else {
			Scanner kb = new Scanner(System.in);
			System.out.println("Please enter your file name: ");
			String inputFileName = kb.next();
			Scanner testFiles = getScannerForFile(kb, inputFileName);
			kb.close();
			
			runAllTests(testFiles);
			System.out.println("All tests run");
		}
	}
}