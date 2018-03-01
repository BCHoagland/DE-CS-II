package expressionTrees;

import java.util.Arrays;
import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {

	public static final String[] OPERATORS = {"*", "+"};
	
	public boolean isOperator(String str) {
		return Arrays.asList(OPERATORS).contains(str);
	}
	
	public int runOperation(String operand, int n1, int n2) {
		switch (operand) {
			case "*":
				return n1 * n2;
			case "+":
				return n1 + n2;
			default:
				return -1;
		}
	}
	
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

	@Override
	public int evalTree() {
		// TODO Auto-generated method stub
		return 0;
	}
	
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

	@Override
	public String toPrefixNotation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void toPrefixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			str += nodeValue;
			toPrefixNotation(node.getLeft(), str);
			toPrefixNotation(node.getRight(), str);
		}
	}

	@Override
	public String toInfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void toInfixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			toInfixNotation(node.getLeft(), str);
			str += nodeValue;
			toInfixNotation(node.getRight(), str);
		}
	}

	@Override
	public String toPostfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void toPostfixNotation(TreeNode node, String str) {
		if (node != null) {
			String nodeValue = node.getValue().toString();
			toPostfixNotation(node.getLeft(), str);
			str += nodeValue;
			toPostfixNotation(node.getRight(), str);
		}
	}

	@Override
	public int postfixEval(String[] exp) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String ch : exp) {
			int value;
			if (isOperator(ch)) {
				value = runOperation(ch, stack.pop(), stack.pop());
			} else {
				value = Integer.parseInt(ch);
			}
			stack.push(value);
		}
		return stack.pop();
	}
	
	
}