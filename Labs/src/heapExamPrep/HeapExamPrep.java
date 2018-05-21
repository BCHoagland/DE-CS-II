package heapExamPrep;

public class HeapExamPrep {
	
	public static Object[] toArray(Node root, int numNodes) {
		Object[] arr = new Object[numNodes + 1];
		
		toArray(root, arr, 1);
		return arr;
	}
	
	public static void toArray(Node node, Object[] values, int i) {
		if (node != null) {
			values[i] = node;
			
			toArray(node.getLeft(), values, 2 * i);
			toArray(node.getRight(), values, 2 * i + 1);
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
		
		Object[] arr = toArray(n0, 8);
		for (Object obj : arr) {
			Node n = (Node) obj;
			System.out.print(n + ",");
		}
	}
}