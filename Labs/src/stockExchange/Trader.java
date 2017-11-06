package stockExchange;

import java.util.LinkedList;
import java.util.Queue;

public class Trader implements Comparable<Trader> {
	
	private Brokerage brokerage;
	private String username;
	private String password;
	
	private Queue<String> mailbox;
	
	private TraderWindow window;
	
	public Trader(Brokerage brokerage, String username, String password) {
		this.brokerage = brokerage;
		this.username = username;
		this.password = password;
		this.mailbox = new LinkedList<String>();
	}
	
	public String getName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void getQuote(String symbol) {
		this.brokerage.getQuote(symbol, this);
	}
	
	public void placeOrder(TradeOrder order) {
		this.brokerage.placeOrder(order);
	}
	
	public boolean hasMessages() {
		if (mailbox.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void receiveMessage(String msg) {
		mailbox.add(msg);
		
		while (mailbox.size() > 0) {
			window.showMessage(mailbox.poll());
		}
	}
	
	public void openWindow() {
		window = new TraderWindow(this);
	}
	
	public void quit() {
		this.brokerage.logout(this);
		window = null;								//TODO is this even necessary?
	}

	@Override
	public int compareTo(Trader other) {
		return (int) Math.signum(this.getName().toLowerCase().compareTo(other.getName().toLowerCase()));
	}
	
	public boolean equals(Object other) {
		if (other instanceof Trader) {
			return this.compareTo((Trader)other) == 0;
		}
		return false;
	}
	
	public String toString() {
		//TODO fix this
		return /*brokerage + " ---- " + */username + " - " + password;
	}
}