package readingAndWriting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>Reading and Writing</h1> class to take in file inputs from a user and use them in three separate ways:<br/>
 * 1 - check to see if a file's braces are balanced<br/>
 * 2 - check to see if two files are identical<br/>
 * 3 - fill in a short story using a provided file of words and/or user input
 * @author Braden Hoagland
 * @since September 18, 2017
 */
public class ReadingAndWriting {
	
	/**
	 * inputFile1 field to store one of the two Scanners used in the class
	 */
	public static Scanner inputFile1;
	
	/**
	 * inputFile2 field to store one of the two Scanners used in the class
	 */
	public static Scanner inputFile2;
	
	/**
	 * PrintWriter field to be used to write to the output file
	 */
	public static PrintWriter outputFile;
	
	/**
	 * kb field to be used to take user input
	 */
	private static Scanner kb;
	
	/**
	 * method that determines if an input file given by the user exists, and returns a Scanner for that file if it does
	 * @param inputFileName the name of the input file given by the user
	 * @param part the current part of the program (either 1 for braces, 2 for identity, or 3 for short story)
	 * @return the Scanner to be used to parse the input file
	 */
	public static Scanner createInputFile(String inputFileName, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName));
			return inputFile1;
		} catch (FileNotFoundException ex) {
			outputFile.println("Part " + part + ": Unable to Open File");
			closeInputFiles();
		}
		
		return null;
	}
	
	/**
	 * method that determines if two input files given by the user exist, and returns an array of Scanners - one for each file - if they do
	 * @param inputFileName1 the name of the first input file given by the user
	 * @param inputFileName2 the name of the second input file given by the user
	 * @param part (either 1 for braces, 2 for identity, or 3 for short story)
	 * @return the array of Scanners to be used to parse the input file
	 */
	public static Scanner[] createInputFile(String inputFileName1, String inputFileName2, int part) {
		try {
			inputFile1 = new Scanner(new File(inputFileName1));
			inputFile2 = new Scanner(new File(inputFileName2));
			Scanner[] scanners = {inputFile1, inputFile2};
			return scanners;
		} catch (FileNotFoundException ex) {
			outputFile.println("Part " + part + ": Unable to Open File");
			closeInputFiles();
		}
		
		return null;
	}
	
	/**
	 * method to set up the PrintWriter for the output file "output.txt"
	 */
	public static PrintWriter createOutputFile() {
		try {
			outputFile = new PrintWriter("output.txt");
			return outputFile;
		} catch (FileNotFoundException ex) {
			System.out.println("Part 1: Unable to open output file. Is it in the src folder for this project?");
		}
		
		return null;
	}
	
	/**
	 * closes the input files to save changes
	 */
	public static void closeInputFiles() {
		if (inputFile2 != null) {inputFile1.close();}
		if (inputFile2 != null) {inputFile2.close();}
	}
	
	/**
	 * checks to see if a given file's braces are balanced
	 * @param fileName the name of the input file given by the user
	 * @return a String stating whether or not the braces in the file are balanced
	 */
	public static String checkBraces(String fileName) {
		Scanner scanner = createInputFile(fileName, 1);
		
		if (scanner == null) {
			return null;
		}
		
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
				
				if (total < 0) {
					return "Braces Not Balanced";
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
	
	/**
	 * checks if two given files are identical
	 * @param fileName1 the name of the first input file given by the user
	 * @param fileName2 the name of the second input file given by the user
	 * @return a String stating whether or not the given files are identical
	 */
	public static String checkIdentical(String fileName1, String fileName2) {
		Scanner[] scanners = createInputFile(fileName1, fileName2, 2);
		
		if (scanners == null) {
			return null;
		}
		
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
	
	/**
	 * gets a list of words from the user to fill the short story
	 * @param wordsLeft the number of words for the short story already given in a separate file. This method takes those into account and fills out any that are remaining
	 * @param fileName the name of the short story file given by the user
	 * @return an ArrayList<String> of the words to be used in the short story
	 */
	public static ArrayList<String> getShortStoryWordsFromUser(int wordsLeft, String fileName) {
		Scanner scanner = createInputFile(fileName, 3);
		
		if (scanner == null) {
			return null;
		}
		
		ArrayList<String> userWords = new ArrayList<String>();
		
		while (inputFile1.hasNextLine()) {
			boolean addToPrompt = false;
			String prompt = "";
			
			for (char ch : inputFile1.nextLine().toCharArray()) {
				String letter = String.valueOf(ch);
				if (letter.equals(">") && wordsLeft < 0) {
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
					if (wordsLeft <= 0) {
						addToPrompt = true;
					}
					wordsLeft--;
				}
			}
		}
		
		inputFile1.close();
		
		return userWords;
	}
	
	/**
	 * gets a list of words from a file to fill the short story
	 * @param scanner the Scanner for the input file of short story words
	 * @return an ArrayList<String> of words to be used in the short story
	 */
	public static ArrayList<String> getShortStoryWordsFromFile(Scanner scanner) {
		if (scanner == null) {
			return null;
		}
		
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
	
	/**
	 * fill in the short story using a given list of words
	 * @param words an ArrayList<String> of words to be used in the short story
	 * @param fileName the name of the short story file given by the user
	 */
	public static void fillShortStory(ArrayList<String> words, String fileName) {
		createInputFile(fileName, 3);
		
		int index = 0;
		
		//the while loop is based off the short story file instead of the ArrayList so extra words in the ArrayList don't cause an issue
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
	
	/**
	 * checks if braces are balanced
	 * @param args a String[] of length 1 containing the file to be checked
	 */
	public static void part1(String[] args) {
		String msg = checkBraces(args[0]);
		if (msg != null) {
			outputFile.println(msg);
		}
	}
	
	/**
	 * checks if two files are identical
	 * @param args a String[] of length 2 containing the two files to be compared
	 */
	public static void part2(String[] args) {
		String msg = checkIdentical(args[0], args[1]);
		if (msg != null) {
			outputFile.println(msg);
		}
	}
	
	/**
	 * fills out a short story using user input
	 * @param args a String[] of length 3 containing the two files for parts one and two and one other file with the short story template
	 */
	public static void part3(String[] args) {
		ArrayList<String> words = getShortStoryWordsFromUser(0, args[2]);
		if (words != null) {
			fillShortStory(words, args[2]);
		}
	}
	
	/**
	 * fills out a short story using a list of words and/or user input
	 * @param args a String[] of length 4 containing the two files for parts one and two, another file with the short story template, and one other file with provided words for the short story
	 */
	public static void part4(String[] args) {
		ArrayList<String> words1 = getShortStoryWordsFromFile(createInputFile(args[3], 3));
		if (words1 != null) {
			ArrayList<String> words2 = getShortStoryWordsFromUser(words1.size(), args[2]);
			if (words2 != null) {
				words1.addAll(words2);
				fillShortStory(words1, args[2]);
			}
		}
	}
	
	/**
	 * determines which parts of the program to run, based on the number of arguments given to the program from the user
	 * @param args the command line arguments given by the user
	 */
	public static void determineActions(String[] args, PrintWriter outputFile) {
		
		//if user enters no arguments
		if (args.length < 1) {
			outputFile.println("Part 1: Unable to Open File");
		}
		
		//part 1
		if (args.length == 1) {
			part1(args);
		}

		//part 2
		if (args.length == 2) {
			part1(args);
			outputFile.println("");
			part2(args);
		}

		//part 3
		if (args.length == 3) {
			part1(args);
			outputFile.println("");
			part2(args);
			outputFile.println("");
			part3(args);
		}
		if (args.length == 4) {
			part1(args);
			outputFile.println("");
			part2(args);
			outputFile.println("");
			part4(args);
		}
	}

	/**
	 * main method that determines which parts of the program to run
	 * @param args the command line arguments given by the user
	 */
	public static void main(String[] args) {
		//create the PrintWriter to be used for the output file during the program
		PrintWriter out = createOutputFile();
		
		//run the proper parts of the program for the given arguments
		determineActions(args, out);
		
		//close all files to save them before the program finishes
		closeInputFiles();
		outputFile.close();
	}
}