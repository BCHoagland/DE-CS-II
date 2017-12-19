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
	
	//RETURN??????
	public void remove(Card c) {
		 Node card = head;
		 if (head.getCard().equals(c)) {
			 if (head.getNext() != null) {
				 head = head.getNext();
			 } else {
				 head = null;
			 }
			 return;
		 }
		 while (card.getNext() != null) {
			 if (card.getNext().getCard().equals(c)) {
				 card.setNext(card.getNext().getNext());
				 return;
			 }
			 card = card.getNext();
		 }
	}
	
	public String toString() {
		if (head == null) {
			return "[empty list]";
		}
		Node card = head;
		String str = card + "\n";
		while (card.getNext() != null) {
			card = card.getNext();
			str += card + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		CardList myList = new CardList(new Card());
		myList.add(new Card("Diamonds", 11));
		myList.add(new Card("Clubs", 12));
		myList.add(new Card("Hearts", 13));
		myList.add(new Card("Spades", 4));
		System.out.println(myList);
		
		myList.remove(new Card());
		System.out.println(myList);
		
		myList.remove(new Card("Clubs", 12));
		System.out.println(myList);
		
		myList.remove(new Card("Spades", 4));
		myList.remove(new Card("Hearts", 13));
		System.out.println(myList);
		
		myList.remove(new Card("Diamonds", 11));
		System.out.println(myList);
	}
}