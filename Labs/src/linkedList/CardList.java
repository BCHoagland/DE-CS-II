package linkedList;

public class CardList {
	
	private Node head;
	private Node tail;
	
	public CardList(Card c) {
		Node card = new Node(c);
		head = card;
		tail = card;
	}
	
	//insert card at beginning
	public void add(Card c) {
		Node card = new Node(c);
		card.setNext(head);
		head = card;
	}
	
	//insert card at end
	public void addAtEnd(Card c) {
		Node card = new Node(c);
		tail.setNext(card);
		tail = card;
	}
	
	public static void main(String[] args) {
		CardList myList = new CardList(new Card());
		myList.add(new Card(11, "Diamonds"));
		System.out.println(myList);
	}
}