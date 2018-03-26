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
	
	public static final int MAX_HASH = 300;
	
	public HashBoolean[] winners;
	
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
		}
	}
	
	/**
	 * get a scanner for the file with the given name, if possible
	 * @param fileName name of the file to return a scanner for
	 * @return scanner for the given file; if no file exists, exit with an error
	 */
	//IS HAVING THIS THROW AN ERROR OKAY?
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
			return 0;
		case 'o':
			return 1;
		case 'x':
			return 2;
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
	
	public static void main(String[] args) {
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
		}
		
		System.out.println("array size: " + MAX_HASH);
//		System.out.println(numItems + " items in lookup table");
		System.out.println("load factor: " + ((double)(numItems) / MAX_HASH));
		
		//HOW BIG IS TOO BIG OF A LOAD FACTOR????
		//HOW MANY COLLISIONS IS TOO MANY COLLISIONS????
		
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
	}
}