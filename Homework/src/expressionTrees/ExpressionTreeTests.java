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
	
	public static final String outputFileName = "Hoagland_Expressions.txt";
	
	public static String runTestsOnExpression(String exp) {
		ExpressionTree tree = new ExpressionTree(exp);
		String str = tree.evalTree() + "\n" + tree.toPrefixNotation() + "\n" + tree.toInfixNotation() + "\n" + tree.toPostfixNotation() + "\n" + tree.postfixEval(exp.split("\\s+")) + "\n\n\n";
		return str;
	}
	
	public static void printToOutputFile(String output) {
		PrintWriter file = null;
		try {
			file = new PrintWriter(new File(outputFileName));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
			System.exit(1);
		}
		
		if (file != null) {
			file.print(output);
			file.close();
		}
	}
	
	public static void main(String[] args) {
		
		//EXPS IS ARRAY NOT ARRAYLIST????
		ArrayList<String> exps = new ArrayList<String>();
		
		Scanner input = null;
		try {
			input = new Scanner(new File("myExpressions.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		
		if (input != null) {
			while (input.hasNextLine()) {
				exps.add(input.nextLine());
			}
		}
		
		String output = "";
		for (String exp : exps) {
			output += runTestsOnExpression(exp);
		}
		printToOutputFile(output.trim());
		System.out.println("All expressions processed");
	}
}