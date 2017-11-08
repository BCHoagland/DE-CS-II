package stockExchange;

public class Trader {
	
	private Brokerage brokerage;
	private String username;
	private String password;
	
	public Trader(Brokerage brokerage, String username, String password) {
		this.brokerage = brokerage;
		this.username = username;
		this.password = password;
	}
	
	public String toString() {
		//TODO fix this
		return brokerage + " ---- " + username + ": " + password;
	}
}