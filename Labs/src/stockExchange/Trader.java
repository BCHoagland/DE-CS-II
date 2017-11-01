package stockExchange;

public class Trader implements Comparable<Trader> {
	
	private Brokerage brokerage;
	private String username;
	private String password;
	
	public Trader(Brokerage brokerage, String username, String password) {
		this.brokerage = brokerage;
		this.username = username;
		this.password = password;
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
		//TODO return true if they have messages in their mailbox; false otherwise
		return false;		//FIX THIS
	}
	
	public void receiveMessage(String msg) {
		//TODO do this
	}
	
	public void openWindow() {
		//TODO idk how this works
	}
	
	public void quit() {
		this.brokerage.logout(this);
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