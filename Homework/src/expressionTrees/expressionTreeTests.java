package expressionTrees;

public class expressionTreeTests {
	
	public static void main(String[] args) {
		ExpressionTree tree = new ExpressionTree("1 2 +");
		System.out.println("\t" + tree.getValue());
		System.out.print(tree.getLeft().getValue() + "\t\t");
		System.out.print(tree.getRight().getValue());
	}
}