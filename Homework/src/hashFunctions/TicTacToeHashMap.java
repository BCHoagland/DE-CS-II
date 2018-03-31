package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * TicTacToeMyHashMap Class<br/>
 * tic tac toe hash map created using the default hash function
 * @author Braden Hoagland
 *
 */
public class TicTacToeHashMap {

	/**
	 * name of the file with the winning tic tac toe setups
	 */
	public static final String WINNERS_FILE_NAME = "TicTacToeWinners.txt";
	
	/**
	 * hash map that stores the board strings from the winners file with the board string as the key and true as the value
	 */
	HashMap<String, Boolean> winners = new HashMap<String, Boolean>();

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
	
	/**
	 * constructor that fills the hash map with the board strings in the winners file
	 */
	TicTacToeHashMap() {
		Scanner winnersFile = getScannerForFile(WINNERS_FILE_NAME);

		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String boardStr = winnersFile.nextLine();
				winners.put(boardStr, true);
			}
			winnersFile.close();
		}
	}

	/**
	 * finds the capacity of the hash map using reflection
	 * @return capacity of the hash map
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private int capacity() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return table == null ? 0 : table.length;   
	}
	
	/**
	 * reports on all required information about the hash map using reflection
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private void reportOnHashMap() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);

		ArrayList<Integer> chainLengths = new ArrayList<Integer>();

		for (Object tb : table) {
			int n = 0;
			if (tb != null) {
				Object entry = tb;
				while (entry != null) {
					n++;
//					Field keyField = entry.getClass().getDeclaredField("key");
//					keyField.setAccessible(true);
//					String boardStr = (String) keyField.get(entry);
//					System.out.print(boardStr + ", ");

					Field nextField = entry.getClass().getDeclaredField("next");
					nextField.setAccessible(true);

					entry = nextField.get(entry);
				}
				chainLengths.add(n);
			} else {
				chainLengths.add(0);
			}
//			System.out.println();
		}

		int capacity = capacity();

		int numEntries = 0;
		int numItems = 0;
		int numChains = 0;
		int numCollisions = 0;
		int maxChainLength = 0;
		for (int n : chainLengths) {
			numItems += n;
			if (n > 0) {
				numEntries++;
			}
			if (n > 1) {
				numChains++;
				numCollisions += n;
			}
			if (n > maxChainLength) maxChainLength = n;
		}

		double avgChainLength = ((double)numCollisions) / numChains;

		double loadFactor = (double)(numItems) / capacity;

		//entry data
		System.out.println("capacity of the table: " + capacity);
		System.out.println("number of entries in the table (indices filled): " + numEntries);
		System.out.println("number of collisions in the table: " + numCollisions);
		System.out.println("load factor (total item count / capacity of table): " + loadFactor);
		System.out.println();

		//num entries per quarter
		for (int i = 0; i < 4; i++) {
			int beginIndex = capacity * i / 4;
			int endIndex = capacity * (i + 1) / 4;
			int quarterItems = 0;
			for (int index = beginIndex; index < endIndex; index++) {
				quarterItems += chainLengths.get(index);
			}
			System.out.println("quarter #" + (i + 1) + " entries: " + quarterItems);
		}

		//num collisions per tenth
		System.out.println();
		for (int i = 0; i < 10; i++) {
			int beginIndex = capacity * i / 10;
			int endIndex = capacity * (i + 1) / 10;
			int tenthCollisions = 0;
			for (int index = beginIndex; index < endIndex; index++) {
				int n = chainLengths.get(index);
				if (n > 1) tenthCollisions += n;
			}
			System.out.println("tenth #" + (i + 1) + " collisions: " + tenthCollisions);
		}

		//chain length data
		System.out.println();
		System.out.println("average chain length: " + avgChainLength);
		System.out.println("max chain length: " + maxChainLength);
	}
	
	/**
	 * create and report on a new hash map
	 * @param args
	 * @throws java.io.FileNotFoundException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) throws java.io.FileNotFoundException, NoSuchFieldException, IllegalAccessException {

		TicTacToeHashMap m = new TicTacToeHashMap();

		m.reportOnHashMap();
	}
}