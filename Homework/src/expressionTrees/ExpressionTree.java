package expressionTrees;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Braden Hoagland
 * <h1>ExpressionTree</h1>
 * <h2>Class representing the root of an expression tree</h2>
 */
public class ExpressionTree extends TreeNode implements Expressions {
	
	/**
	 * constant array containing all possible operators
	 */
	public static final String[] OPERATORS = {"*", "/", "+", "-"};
	
	/**
	 * constructor for the ExpressionTree that takes a postfix expression as an argument
	 * @param initValue postfix expression as a String with spaces between each operator and number
	 */
	public ExpressionTree(Object initValue) {
		super(initValue);
		String[] exp = initValue.toString().split("\\s+");
		
		TreeNode node = buildTree(exp);
		this.setValue(node.getValue());
		this.setLeft(node.getLeft());
		this.setRight(node.getRight());
	}
	
	/**
	 * checks if a given string is a valid operator
	 * @param str String to be checked to see if it's a valid operator
	 * @return whether or not the given String is a valid operator
	 */
	public boolean isOperator(String str) {
		return Arrays.asList(OPERATORS).contains(str);
	}
	
	/**
	 * run mathematical operation given an operator and two numbers
	 * @param operand operator to be used
	 * @param n1 first number for the operation
	 * @param n2 second number for the operation
	 * @return int result of the operation
	 */
	public int runOperation(String operand, int n1, int n2) {
		switch (operand) {
			case "*":
				return n1 * n2;
			case "/":
				return n1 / n2;
			case "+":
				return n1 + n2;
			case "-":
				return n1 - n2;
			default:
				return -1;
		}
	}
	
	/**
	 * build an expression tree given a postfix expression
	 * @param exp postfix expression as an array of Strings
	 * @return root of the new expression tree
	 */
	@Override
	public TreeNode buildTree(String[] exp) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (String ch : exp) {
			if (isOperator(ch)) {
				TreeNode operatorNode = new TreeNode(ch);
				operatorNode.setRight(stack.pop());
				operatorNode.setLeft(stack.pop());
				stack.push(operatorNode);
			} else {
				stack.push(new TreeNode(ch));
			}
		}
		return stack.pop();
	}
	
	/**
	 * calculate the result of the current expression tree
	 */
	@Override
	public int evalTree() {
		return evalTree(this);
	}
	
	/**
	 * calculate the result of the tree with the given node as the root
	 * @param node root of the expression tree
	 * @return result of running all operations in the tree
	 */
	public int evalTree(TreeNode node) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			if (isOperator(nodeValue)) {
				return runOperation(nodeValue, evalTree(node.getLeft()), evalTree(node.getRight()));
			} else {
				return Integer.parseInt(nodeValue);
			}
		} else {
			return 0;
		}
	}
	
	/**
	 * return a prefix representation of the current expression tree
	 * @return String prefix expression
	 */
	@Override
	public String toPrefixNotation() {
		String str = "";
		str = toPrefixNotation(this, str).trim();
		return str;
	}
	
	/**
	 * add onto the given prefix expression with the given node
	 * @param node node to add to prefix expression
	 * @param str current prefix expression
	 * @return updated prefix expression
	 */
	public String toPrefixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			str += nodeValue + " ";
			str = toPrefixNotation(node.getLeft(), str);
			str = toPrefixNotation(node.getRight(), str);
		}
		return str;
	}
	
	/**
	 * return a infix representation of the current expression tree
	 * @return String infix expression
	 */
	@Override
	public String toInfixNotation() {
		String str = "";
		str = toInfixNotation(this, str);
		str = str.substring(1, str.length() - 1);
		return str;
	}
	
	/**
	 * add onto the given infix expression with the given node, adding parentheses as necessary
	 * @param node node to add to infix expression
	 * @param str current infix expression
	 * @return updated infix expression
	 */
	public String toInfixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			TreeNode left = node.getLeft();
			TreeNode right = node.getRight();
			
			if (left != null) str = toInfixNotation(left, str + "(");
			str += nodeValue;
			if (right != null) str = toInfixNotation(right, str) + ")";
		}
		return str;
	}
	
	/**
	 * return a postfix representation of the current expression tree
	 * @return String postfix expression
	 */
	@Override
	public String toPostfixNotation() {
		String str = "";
		str = toPostfixNotation(this, str).trim();
		return str;
	}
	
	/**
	 * add onto the given postfix expression with the given node
	 * @param node node to add to postfix expression
	 * @param str current postfix expression
	 * @return updated postfix expression
	 */
	public String toPostfixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			str = toPostfixNotation(node.getLeft(), str);
			str = toPostfixNotation(node.getRight(), str);
			str += nodeValue + " ";
		}
		return str;
	}
	
	/**
	 * evaluate a postfix expression
	 * @param exp postfix expression as a String array
	 * @return result of running the postfix expression
	 */
	@Override
	public int postfixEval(String[] exp) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String ch : exp) {
			int value;
			if (isOperator(ch)) {
				int n2 = stack.pop();
				int n1 = stack.pop();
				value = runOperation(ch, n1, n2);
			} else {
				value = Integer.parseInt(ch);
			}
			stack.push(value);
		}
		return stack.pop();
	}
}