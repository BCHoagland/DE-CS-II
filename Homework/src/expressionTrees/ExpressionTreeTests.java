package expressionTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Braden Hoagland
 * <h1>ExpressionTreeTests</h1>
 * <h2>Class to test the ExpressionTree class</h2>
 */
public class ExpressionTreeTests {
	
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
		
		for (String exp : exps) {
			ExpressionTree tree = new ExpressionTree(exp);
			System.out.println(tree.evalTree());
			System.out.println(tree.toPrefixNotation());
			System.out.println(tree.toInfixNotation());
			System.out.println(tree.toPostfixNotation());
			System.out.println(tree.postfixEval(exp.split("\\s+")) + "\n\n");
		}
	}
}