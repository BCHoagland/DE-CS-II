package expressionTrees;

import java.util.Arrays;
import java.util.Stack;

public class ExpressionTree extends TreeNode implements Expressions {

	public static final String[] OPERATORS = {"*", "+"};
	
	public boolean isOperator(String str) {
		return Arrays.asList(OPERATORS).contains(str);
	}
	
	@Override
	public TreeNode buildTree(String[] exp) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (String ch : exp) {
			if (isOperator(ch)) {
				//make operator a node that's a parent to the two top nodes on the stack
				//push operator node to stack
			} else {
				stack.push(new TreeNode(ch));
			}
		}
		return null;
	}

	@Override
	public int evalTree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toPrefixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toInfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPostfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int postfixEval(String[] exp) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}