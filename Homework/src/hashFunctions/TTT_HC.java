package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TTT_HC {
	
	/**
	 * name of the file with the winning tic tac toe setups
	 */
	public static final String WINNERS_FILE_NAME = "TicTacToeWinners.txt";
	
	public static final int MAX_HASH = 2048;
	
	public HashBoolean[] winners;
	
	public static int[] pv = {13, 2, 199};
	
	public TTT_HC() {
		winners = new HashBoolean[MAX_HASH];

		Scanner winnersFile = getScannerForFile(WINNERS_FILE_NAME);

		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String line = winnersFile.nextLine();
				int hash = hash(line);
				if (winners[hash] == null) {
					winners[hash] = new HashBoolean(line, true);
				} else {
					HashBoolean hb = winners[hash];
					winners[hash] = new HashBoolean(line, true, hb);
				}
			}
			winnersFile.close();
		}
	}
	
	public TTT_HC(int a, int b, int c) {
		winners = new HashBoolean[MAX_HASH];
		
		pv[0] = a;
		pv[1] = b;
		pv[2] = c;
		
		Scanner winnersFile = getScannerForFile(WINNERS_FILE_NAME);

		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String line = winnersFile.nextLine();
				int hash = hash(line);
				if (winners[hash] == null) {
					winners[hash] = new HashBoolean(line, true);
				} else {
					HashBoolean hb = winners[hash];
					winners[hash] = new HashBoolean(line, true, hb);
				}
			}
			winnersFile.close();
		}
	}
	
	/**
	 * get a scanner for the file with the given name, if possible
	 * @param fileName name of the file to return a scanner for
	 * @return scanner for the given file; if no file exists, exit with an error
	 */
	public Scanner getScannerForFile(String fileName) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(fileName));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}

		return sc;
	}
	
	public int prehash(char ch) {
		switch (ch) {
		case ' ':
			return pv[0];
		case 'o':
			return pv[1];
		case 'x':
			return pv[2];
		default:
			return -1;
		}
	}
	
	public int hash(String str) {
		int[] pows3 = {1, 3, 9, 27, 81, 243, 729, 2187, 6561};
		int hash = 0;
		int i = 0;
		for (char ch: str.toCharArray()) {
			hash += prehash(ch) * pows3[i];
			i++;
		}
		return hash % MAX_HASH;
	}
	
	public static double getAvgChainLength(TTT_HC t) {
		int numCollisions = 0;
		int numChains = 0;
		for (HashBoolean hb : t.winners) {
			int n = 0;
			while (hb != null) {
				n++;
				hb = hb.getNext();
			}
			if (n > 1) {
				numCollisions += n;
				numChains++;
			}
		}
		
		double avgChainLength = ((double)numCollisions) / numChains;
		return avgChainLength;
	}
	
	/**
	 * determines the prehash values needed to minimize the number of empty spaces in the array
	 * I'm okay with my hash function having lots of collisions. I just want them to be as spread out as possible so all my chains can be searched in constant time
	 */
	public static void optimizeHashFunction() {
		int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199};
		int len = primes.length;
		double min = 100;
		int A = 100;
		int B = 100;
		int C = 100;
		for (int a = 0; a < len; a++) {
			for (int b = 0; b < len; b++) {
				for (int c = 0; c < len; c++) {
					TTT_HC t = new TTT_HC(primes[a], primes[b], primes[c]);
					double num = getAvgChainLength(t);
					if (num < min) {min = num;A = a;B = b;C = c;}
				}
			}
		}
		System.out.println("(" + primes[A] + ", " + primes[B] + ", " + primes[C] + "): " + min);
	}
	
	public static void reportOnHash() {
		TTT_HC t = new TTT_HC();
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (HashBoolean hb : t.winners) {
			int n = 0;
			while (hb != null) {
				n++;
				hb = hb.getNext();
			}
			nums.add(n);
		}
		System.out.println(nums);
		
		int numEmpty = 0;
		int numItems = 0;
		int numChains = 0;
		int numCollisions = 0;
		int maxChainLength = 0;
		for (int n : nums) {
			numItems += n;
			if (n > 1) {
				numChains++;
				numCollisions += n;
				if (n > maxChainLength) maxChainLength = n;
			}
			if (n == 0) numEmpty++;
		}
		
		System.out.println("array size: " + MAX_HASH);
//		System.out.println(numItems + " items in lookup table");
		System.out.println("load factor: " + ((double)(numItems) / MAX_HASH));
		System.out.println("number of empty indices: " + numEmpty);
		
		System.out.println("\nnum chains: " + numChains);
		System.out.println("avg chain length: " + ((double)numCollisions / numChains));
		System.out.println("max chain length: " + maxChainLength);
		
		System.out.println();
		for (int i = 0; i < 4; i++) {
			int beginIndex = MAX_HASH * i / 4;
			int endIndex = MAX_HASH * (i + 1) / 4;
			int quarterItems = 0;
			for (int index = beginIndex; index < endIndex; index++) {
				quarterItems += nums.get(index);
			}
			System.out.println("quarter #" + (i + 1) + " entries: " + quarterItems);
		}
		
		System.out.println();
		for (int i = 0; i < 10; i++) {
			int beginIndex = MAX_HASH * i / 10;
			int endIndex = MAX_HASH * (i + 1) / 10;
			int tenthCollisions = 0;
			for (int index = beginIndex; index < endIndex; index++) {
				int n = nums.get(index);
				if (n > 1) tenthCollisions += n;
			}
			System.out.println("tenth #" + (i + 1) + " collisions: " + tenthCollisions);
		}
		
		System.out.println("\ntotal collisions: " + numCollisions);
	}
	
	public static void main(String[] args) {
		optimizeHashFunction();
//		reportOnHash();
	}
}