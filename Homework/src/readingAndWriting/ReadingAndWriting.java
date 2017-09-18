package readingAndWriting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadingAndWriting {

	public static Scanner inputFile1;
	public static Scanner inputFile2;
	public static PrintWriter outputFile;
	private static Scanner kb;

	public static Scanner createInputFile(String inputFileName, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException ex) {
			System.out.println("Part " + part + ": Unable to Open File");
		}
		
		return inputFile1;
	}
	
	public static void createInputFile(String inputFileName1, String inputFileName2, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName1));
			inputFile2 = new Scanner(new File(inputFileName2));
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

	public static String checkBraces(String fileName) {
		createInputFile(fileName, 1);
		
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
		
		inputFile1.close();
		
		if (total == 0) {
			return "Braces Balanced";
		} else {
			return "Braces Not Balanced";
		}
	}
	
	public static String checkIdentical(String fileName1, String fileName2) {
		createInputFile(fileName1, fileName2, 2);
		
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
		
		inputFile1.close();
		inputFile2.close();
		return "Files Identical";
	}
	
	public static ArrayList<String> getShortStoryWords(int index, String fileName) {
		createInputFile(fileName, 3);
		
		ArrayList<String> userWords = new ArrayList<String>();
		
		while (inputFile1.hasNextLine()) {
			boolean addToPrompt = false;
			String prompt = "";
			
			for (char ch : inputFile1.nextLine().toCharArray()) {
				String letter = String.valueOf(ch);
				if (letter.equals(">") && index < 0) {
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
					if (index <= 0) {
						addToPrompt = true;
					}
					index--;
				}
			}
		}
		
		inputFile1.close();
		
		return userWords;
	}
	
	public static void fillShortStory(ArrayList<String> words, String fileName) {
		createInputFile(fileName, 3);
		
		int index = 0;
		while (inputFile1.hasNextLine()) {
			String line = inputFile1.nextLine();
			while (line.indexOf("<") > -1) {
				int startIndex = line.indexOf("<");
				int endIndex = line.indexOf(">");
				line = line.substring(0, startIndex) + words.get(index) + line.substring(endIndex + 1);
				index++;
				if (index == words.size()) {
					break;
				}
			}
			
			outputFile.println(line);
		}
		
		inputFile1.close();
	}
	
	public static ArrayList<String> getShortStoryWordsFromFile(Scanner scanner) {
		ArrayList<String> lines = new ArrayList<String>();
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			while (line.isEmpty()) {
				line = scanner.nextLine();
			}
			lines.add(line);
		}
		
		scanner.close();
		
		return lines;
	}

	public static void main(String[] args) {
		
		createOutputFile();
		
		if (args.length == 1) {
			outputFile.println(checkBraces(args[0]));
			outputFile.println("");
		}
		
		if (args.length == 2) {
			outputFile.println(checkIdentical(args[0], args[1]));
			outputFile.println("");
		}
		
		if (args.length == 4) {
			ArrayList<String> words2 = getShortStoryWordsFromFile(createInputFile(args[3], 3));
			ArrayList<String> words3 = getShortStoryWords(words2.size(), args[2]);
			words2.addAll(words3);
			fillShortStory(words2, args[2]);
		} else if (args.length == 3) {
			ArrayList<String> words1 = getShortStoryWords(0, args[2]);
			fillShortStory(words1, args[2]);
		}
		
		closeFiles();
	}
}