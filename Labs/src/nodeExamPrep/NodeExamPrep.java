package nodeExamPrep;

public class NodeExamPrep {
	
	public static void accumulate(Node root) {
		Node left = root.getLeft();
		Node right = root.getRight();
		
		int leftValue = 0;
		int rightValue = 0;
		
		if (left != null) {
			accumulate(left);
			leftValue = left.getValue();
		}
		if (right != null) {
			accumulate(right);
			leftValue = right.getValue();
		}
		
		if (leftValue != 0 || rightValue != 0) {
			root.setValue(leftValue + rightValue);
		}
	}
	
	public static void main(String[] args) {		
//		Node root = new Node();
		
//		accumulate(root);
	}
}