package nodeExamPrep;

public class NodeExamPrep {

	public static void accumulate(Node root) {
		Node left = root.getLeft();
		Node right = root.getRight();

		int leftValue = 0;
		int rightValue = 0;

		if (left != null || right != null) {
			if (left != null) {
				accumulate(left);
				leftValue = left.getValue();
			}
			if (right != null) {
				accumulate(right);
				rightValue = right.getValue();
			}

			root.setValue(leftValue + rightValue);
		}
	}

	public static void main(String[] args) {
		Node n7 = new Node(null, null, 7);
		Node n6 = new Node(null, null, 6);
		Node n5 = new Node(null, null, 5);
		Node n4 = new Node(null, null, 4);
		Node n3 = new Node(n7, null, 3);
		Node n2 = new Node(n5, n6, 2);
		Node n1 = new Node(n3, n4, 1);
		Node n0 = new Node(n1, n2, 0);
		
		n7.setTop(n3);
		n6.setTop(n2);
		n5.setTop(n2);
		n4.setTop(n1);
		n3.setTop(n1);
		n2.setTop(n0);
		n1.setTop(n0);
		
		System.out.println("\t\t\t\t" + n0);
		System.out.println("\t\t" + n1 + "\t\t\t\t" + n2);
		System.out.println("\t" + n3 + "\t\t" + n4 + "\t\t" + n5 + "\t\t" + n6 + "\t\t");
		System.out.println(n7 + "\n\n\n\n");
		
		accumulate(n0);
		
		System.out.println("\t\t\t\t" + n0);
		System.out.println("\t\t" + n1 + "\t\t\t\t" + n2);
		System.out.println("\t" + n3 + "\t\t" + n4 + "\t\t" + n5 + "\t\t" + n6 + "\t\t");
		System.out.println(n7);
	}
}