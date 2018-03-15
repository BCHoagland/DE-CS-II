package hashFunctions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlgorithmTester {
	
	public static final int MAX = 30;
	public static final int DIV = 4;
	
	public static int hash(char ch) {
		switch(ch) {
		case ' ':
			return 0;
		case 'o':
			return 3;
		case 'x':
			return 5;
		default:
			return -1;
		}
	}
	
	public static int hash(String str) {
		int hash = 0;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			hash += (i + 1) * 2 * hash(chars[i]);
		}
		
		int adder = 0;
		if (hash > MAX / 2) adder = 1;
		return (hash / DIV) + adder;
	}
	
	public static void main(String[] args) {
		Scanner file = null;
		try {
			file = new Scanner(new File("TicTacToeWinners.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		ArrayList<String> winners = new ArrayList<String>();
		
		while(file.hasNextLine()) {
			System.out.println(hash(file.nextLine()));
		}
	}
}