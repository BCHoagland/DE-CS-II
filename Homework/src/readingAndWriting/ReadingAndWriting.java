package readingAndWriting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingAndWriting {

	public static Scanner inputFile1;
	public static Scanner inputFile2;
	public static Scanner inputFile3;
	public static PrintWriter outputFile;
	private static Scanner kb;

	public static void createInputFile(String inputFileName, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException ex) {
			System.out.println("Part" + part + ": Unable to Open File");
		}
	}
	
	public static void createInputFile(String inputFileName1, String inputFileName2, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName1));
			inputFile2 = new Scanner(new File(inputFileName2));
		} catch (FileNotFoundException ex) {
			System.out.println("Part " + part + ": Unable to Open File");
		}
	}
	
	public static void createInputFile(String inputFileName1, String inputFileName2, String inputFileName3, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName1));
			inputFile2 = new Scanner(new File(inputFileName2));
			inputFile3 = new Scanner(new File(inputFileName3));
		} catch (FileNotFoundException ex) {
			System.out.println("Part " + part + ": Unable to Open File");
		}
	}
	
	public static void createOutputFile() {
		try {
			outputFile = new PrintWriter("output.txt");
		} catch (FileNotFoundException ex) {
			System.out.println("Part 1: Unable to open output file. Is it in the src folder for this project?");
		}
	}

	public static void closeFiles() {
		if (inputFile2 != null) {inputFile1.close();}
		if (inputFile2 != null) {inputFile2.close();}
		outputFile.close();
	}

	public static String checkBraces() {
		createInputFile("input1.txt", 1);
		
		int total = 0;
		while (inputFile1.hasNextLine()) {
			String line = inputFile1.nextLine();
			for (char ch : line.toCharArray()) {
				String letter = String.valueOf(ch);
				if (letter.equals("{")) {
					total++;
				} else if (letter.equals("}")) {
					total--;
				}
			}
		}
		
		if (total == 0) {
			return "Braces Balanced";
		} else {
			return "Braces Not Balanced";
		}
	}
	
	public static String checkIdentical() {
		createInputFile("input1.txt", "input2.txt", 2);
		
		while (inputFile1.hasNextLine()) {
			if (!inputFile2.hasNextLine()) {
				return "Files Not Identical";
			}
			String line1 = inputFile1.nextLine();
			String line2 = inputFile2.nextLine();
			if (!line1.equals(line2)) {
				return "Files Not Identical";
			}
		}
		if (inputFile2.hasNextLine()) {
			return "Files Not Identical";
		}
		return "Files Identical";
	}
	
	public static void shortStory() {
		createInputFile("input1.txt", "input2.txt", "input3.txt", 3);
		
		ArrayList<String> userWords = new ArrayList<String>();
		
		while (inputFile3.hasNextLine()) {
			boolean addToPrompt = false;
			String prompt = "";
			
			for (char ch : inputFile3.nextLine().toCharArray()) {
				String letter = String.valueOf(ch);
				if (letter.equals(">")) {
					addToPrompt = false;
					
					System.out.println("Enter a " + prompt + ":");
					kb = new Scanner(System.in);
					String userWord = kb.nextLine().trim();
					
					userWords.add(userWord);
					
					prompt = "";
				}
				
				if (addToPrompt) {
					prompt += letter;
				}
				
				if (letter.equals("<")) {
					addToPrompt = true;
				}
			}
		}
		
		System.out.println(userWords);
	}

	public static void main(String[] args) {
		
		createOutputFile();
		
		//part 1
		/*if (args.length > 0) {
			String inputFileName = args[0];
			createFiles(inputFileName);
		} else if (args.length == 1) {
		} else if (args.length == 2) {
		} else if (args.length == 3) [
		}*/
		
		outputFile.println(checkBraces());
		
		//part 2
		outputFile.println("");
		
		//part 3
		outputFile.print(checkIdentical());
		
		//part 4
		outputFile.println("");
		
		//part 5
		shortStory();
		
		closeFiles();
	}
}