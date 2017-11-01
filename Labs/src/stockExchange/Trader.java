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
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String toString() {
		//TODO fix this
		return /*brokerage + " ---- " + */username + " - " + password;
	}

	@Override
	public int compareTo(Trader other) {
		return (int) Math.signum(this.getUsername().toLowerCase().compareTo(other.getUsername().toLowerCase()));
	}
}