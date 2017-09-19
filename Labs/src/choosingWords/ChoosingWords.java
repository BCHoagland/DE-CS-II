package choosingWords;

import java.io.File;
import java.io.FileNotFoundException;
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
	
	public static void main(String[] args) {
		if (args.length < 1)  {
			System.out.println("You did not supply a file to read");
			System.exit(1);
		}
		
		Scanner in = openWords(args[0]);
		if (in == null) System.exit(1);
		System.out.println(in.nextLine());
		in.close();
	}
}