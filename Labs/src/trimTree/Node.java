package trimTree;

public class Node {
	
	private Node left;
	private Node right;
	private Node top;
	private int value;
	
	public Node(Node l, Node r, int v) {
		left = l;
		right = r;
		top = null;
		value = v;
	}
	
	public Node(Node l, Node r, Node t, int v) {
		left = l;
		right = r;
		top = t;
		value = v;
	}
	
	public Node getLeft() {return left;}
	public Node getRight() {return right;}
	public Node getTop() {return top;}
	public int getValue() {return value;}
	
	public void setLeft(Node n) {left = n;}
	public void setRight(Node n) {right = n;}
	public void setTop(Node n) {top = n;}
	public void setValue(int v) {value = v;}
	
	public String toString() {return String.valueOf(value);}
}