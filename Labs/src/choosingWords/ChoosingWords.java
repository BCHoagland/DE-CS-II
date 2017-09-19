package choosingWords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChoosingWords {
	
	public static Scanner openWords(String fileName) {
		File file = new File(fileName);
		Scanner input = null;
		
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException ex) {
			System.out.println("Can't open file: " + fileName);
			return null;
		}
		
		return input;
	}
	
	public static PrintWriter openDictionary(String fileName) {
		File file = new File(fileName);
		PrintWriter output = null;
		
		try {
			output = new PrintWriter(file);
		} catch (FileNotFoundException ex) {
			System.out.println("Can't open file: " + fileName);
			return null;
		}
		
		return output;
	}
	
	public static void writeJava(Scanner input, PrintWriter output) {
		output.println("public class RamblecsDictionary {");
		output.println("\tprivate String[] words = {");
		
		while (input.hasNextLine()) {
			String word = input.nextLine().toUpperCase();
			if (word.length() >= 3 && word.length() <= 5) {
				output.println("\t\t\"" + word + "\",");
			}
		}
		
		output.println("\t};");
		output.println("}");
	}
	
	public static void main(String[] args) {
		if (args.length < 2)  {
			System.out.println("You did not supply a file to read");
			System.exit(1);
		}
		
		Scanner in = openWords(args[0]);
		if (in == null) System.exit(1);
		
		PrintWriter out = openDictionary(args[1]);
		writeJava(in, out);
		
		in.close();
		out.close();
	}
}