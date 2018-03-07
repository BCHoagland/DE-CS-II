package expressionTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Braden Hoagland
 * <h1>ExpressionTreeTests</h1>
 * <h2>Class to test the ExpressionTree class</h2>
 */
public class ExpressionTreeTests {
	
	public static final String DEFAULT_INPUT_FILE_NAME = "postFixExpressions.txt";
	
	public static final String OUTPUT_FILE_NAME = "Hoagland_Expressions.txt";
	
	/**
	 * get scanner for a given file, prompting the user until they give a valid name
	 * @param kb Scanner for System.in
	 * @param givenFileName name of file to be read
	 * @return scanner for the given file
	 */
	public static Scanner getScannerForFile(Scanner kb, String givenFileName) {
		String inputFileName = givenFileName;
		
		Scanner exps = null;
		while (exps == null) {
			try {
				exps = new Scanner(new File(inputFileName));
			} catch (FileNotFoundException ex) {
				System.out.println("Please enter the name of an actual file:");
				inputFileName = kb.next();
			}
		}
		kb.close();
		
		return exps;
	}
	
	/**
	 * get an array of expressions for the given file
	 * @param file scanner for the file with the expressions
	 * @return String[] array of expressions from the file
	 */
	public static String[] getExpsArray(Scanner file) {
		ArrayList<String> exps = new ArrayList<String>();
		while (file.hasNextLine()) {
			String line = file.nextLine().trim();
			if (!line.equals("")) {
				exps.add(line);
			}
		}
		
		return (String[]) exps.toArray(new String[0]);
	}
	
	/**
	 * run all necessary functions for the tree created by the given expression
	 * @param exp String representation of the expression
	 * @return output of all the function calls on the expression tree
	 */
	public static String evalExp(String exp) {
		ExpressionTree tree = new ExpressionTree(exp);
		String str = tree.evalTree() + "\n" + tree.toPrefixNotation() + "\n" + tree.toInfixNotation() + "\n" + tree.toPostfixNotation() + "\n" + tree.postfixEval(exp.split("\\s+")) + "\n\n\n";
		return str;
	}
	
	/**
	 * evaluate each expression in the given array of expressions
	 * @param exps String[] array of expressions
	 * @return combined String output of each expression
	 */
	public static String evalAllExps(String[] exps) {
		String output = "";
		for (String exp : exps) {
			output += evalExp(exp);
		}
		return output.trim();
	}
	
	/**
	 * print given String to the output file
	 * @param output String to be printed to the output file
	 */
	public static void printToOutputFile(String output) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(new File(OUTPUT_FILE_NAME));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}
		
		if (file != null) {
			file.print(output);
			file.close();
		}
	}
	
	/**
	 * evaluate all expressions from the given input file, prompting the user for the input file name if necessary
	 * @param args command-line argument -> name of the input file
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to \n__________.__                       /\\        __________                                   .___   ________      .__.__  .__   \r\n" + 
				"\\______   \\  |__   _________     ___)/ ______ \\______   \\_____ _______  _____    ____    __| _/  /  _____/______|__|  | |  |  \r\n" + 
				" |    |  _/  |  \\ /  _ \\__  \\   / ___\\/  ___/  |    |  _/\\__  \\\\_  __ \\ \\__  \\  /    \\  / __ |  /   \\  __\\_  __ \\  |  | |  |  \r\n" + 
				" |    |   \\   Y  (  <_> ) __ \\_/ /_/  >___ \\   |    |   \\ / __ \\|  | \\/  / __ \\|   |  \\/ /_/ |  \\    \\_\\  \\  | \\/  |  |_|  |__\r\n" + 
				" |______  /___|  /\\____(____  /\\___  /____  >  |______  /(____  /__|    (____  /___|  /\\____ |   \\______  /__|  |__|____/____/\r\n" + 
				"        \\/     \\/           \\//_____/     \\/          \\/      \\/             \\/     \\/      \\/          \\/                    ");
		
		Scanner expFile = null;
		if (args.length > 0) {
			String inputFileName = args[0];
			Scanner kb = new Scanner(System.in);
			expFile = getScannerForFile(kb, inputFileName);
			kb.close();
		} else {
			Scanner kb = new Scanner(System.in);
			expFile = getScannerForFile(kb, DEFAULT_INPUT_FILE_NAME);
			kb.close();
		}
		
		String[] exps = getExpsArray(expFile);
		String output = evalAllExps(exps);
		printToOutputFile(output);
		System.out.println("All expressions processed");
	}
}