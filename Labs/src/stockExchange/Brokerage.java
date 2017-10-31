package stockExchange;

import java.util.TreeMap;
import java.util.TreeSet;

public class Brokerage implements Login {
	
	private TreeMap<String, Trader> traders;
	private TreeSet<Trader> activeTraders;
	
	public Brokerage(StockExchange exchange) {
		traders = new TreeMap<String, Trader>();
	}
	
	@Override
	public int addUser(String name, String password) {
		if (name.length() < 4 || name.length() > 10) {
			return -1;
		}
		
		if (password.length() < 2 || password.length() > 10) {
			return -2;
		}
		
		if (traders.containsKey(name)) {
			return -3;
		}
		
		traders.put(name, new Trader(this, name, password));
		return 0;
	}
	
	@Override
	public int login(String name, String password) {
		Trader trader = registeredTraders.get(name);
		
		if (trader == null) {
			return -1;
		}
		
		String storedPassword = trader.getPassword();
		if (!storedPassword.equals(password)) {
			return -2;
		}
		
		if (loggedInTraders.contains(trader)) {
			return -3;
		}
		
		if (!trader.hasMessages()) trader.receiveMessage("");
		
		trader.openWindow();
		loggedInTraders.add(trader);
		return 0;
	}
	
	
}