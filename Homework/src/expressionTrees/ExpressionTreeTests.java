package expressionTrees;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionTreeTests {
	
	public static void main(String[] args) {
//		ExpressionTree tree = new ExpressionTree("3 1 2 5 7 * + *");
//		System.out.println("\t\t" + tree.getValue());
//		System.out.print("\t" + tree.getLeft().getValue() + "\t\t");
//		System.out.println(tree.getRight().getValue());
//		System.out.print("\t\t" + tree.getRight().getLeft().getValue());
//		System.out.println("\t\t" + tree.getRight().getRight().getValue());
//		
//		System.out.println("\nevalTree(): " + tree.evalTree());
//		System.out.println("prefix: " + tree.toPrefixNotation());
//		System.out.println("infix: " + tree.toInfixNotation());
//		System.out.println("postfix: " + tree.toPostfixNotation());
//		String[] expArr = {"3", "1", "2", "5", "7", "*", "+", "*"};
//		System.out.println("postfixEval(): " + tree.postfixEval(expArr));
		
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