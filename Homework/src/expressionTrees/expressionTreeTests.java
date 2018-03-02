package expressionTrees;

public class expressionTreeTests {
	
	public static void main(String[] args) {
		ExpressionTree tree = new ExpressionTree("3 1 2 + *");
		System.out.println("\t\t" + tree.getValue());
		System.out.print("\t" + tree.getLeft().getValue() + "\t\t");
		System.out.println(tree.getRight().getValue());
		System.out.print("\t\t" + tree.getRight().getLeft().getValue());
		System.out.println("\t\t" + tree.getRight().getRight().getValue());
		
		System.out.println("\nevalTree(): " + tree.evalTree());
		System.out.println("prefix: " + tree.toPrefixNotation());
		System.out.println("infix: " + tree.toInfixNotation());
		System.out.println("postfix: " + tree.toPostfixNotation());
	}
}