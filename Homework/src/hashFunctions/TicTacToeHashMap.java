package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class TicTacToeHashMap {

	/**
	 * name of the file with the winning tic tac toe setups
	 */
	public static final String WINNERS_FILE_NAME = "TicTacToeWinners.txt";

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

	TicTacToeHashMap() {
		// TODO Instantiate/fill your HashMap ... pay attention to initial capacity and load values
		Scanner winnersFile = getScannerForFile(WINNERS_FILE_NAME);
		
		ArrayList<String> strs = new ArrayList<String>();
		ArrayList<Integer> hashes = new ArrayList<Integer>();
		
		if (winnersFile != null) {
			while (winnersFile.hasNextLine()) {
				String boardStr = winnersFile.nextLine();
				winners.put(boardStr, true);
			}
			winnersFile.close();
		}
	}

	// TODO This method uses reflect to investigate the objects inside the HashMap
	// You should be able to update this with your information and determine 
	// Information about capacity (different than size()) and what is stored in the cells

	private int capacity() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		return table == null ? 0 : table.length;   
	}
	
	private void reportOnHashMap() throws NoSuchFieldException, IllegalAccessException {
		Field tableField = HashMap.class.getDeclaredField("table");
		tableField.setAccessible(true);
		Object[] table = (Object[]) tableField.get(winners);
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		for (Object tb : table) {
			int n = 0;
			if (tb != null) {
				Object entry = tb;
				while (entry != null) {
					n++;
					Field keyField = entry.getClass().getDeclaredField("key");
					keyField.setAccessible(true);
					String boardStr = (String) keyField.get(entry);
					System.out.print(boardStr + ", ");

					Field nextField = entry.getClass().getDeclaredField("next");
					nextField.setAccessible(true);

					entry = nextField.get(entry);
				}
				nums.add(n);
			}
			System.out.println();
		}
	}

	// TODO using the same code to get the table of entries as in the capacity method,
	// create a method that will evaluate the table as directed in the assignment.
	// note - if an entry is not null, then it has a value, it may have more than one value
	// see if you can determine how many values it has.  Using the debugger will assist.

	public static void main(String[] args) throws java.io.FileNotFoundException, NoSuchFieldException, IllegalAccessException {

		TicTacToeHashMap m = new TicTacToeHashMap();

		m.reportOnHashMap();
		
//		int capacity = m.capacity();		
//		int numItems = 0;
//
//		for (Entry<String, Boolean> entry : m.winners.entrySet()) {
//			String str = entry.getKey();
//			Boolean bool = entry.getValue();
//			numItems++;
//		}
//		
//		double loadFactor = ((double)numItems) / m.capacity();
//		
//		System.out.println("capacity: " + capacity);
//		System.out.println("number of entries in table: " + numItems);
//		System.out.println("load factor: " + loadFactor);
	}
}