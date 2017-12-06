package linkedList;

public class Node {
	
	private Card card;
	private Node next;
	
	public Node(Card c) {
		card = c;
		next = null;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	
	public Node getNext() {
		return next;
	}
	
	public Card getCard() {
		return card;
	}
}