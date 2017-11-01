package stockExchange;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Brokerage implements Login {
	
	private StockExchange currentStockExchange;
	private TreeMap<String, Trader> traders;
	private TreeSet<Trader> activeTraders;
	
	public Brokerage(StockExchange exchange) {
		currentStockExchange = exchange;
		traders = new TreeMap<String, Trader>();
		activeTraders = new TreeSet<Trader>();
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
		Trader trader = traders.get(name);
		
		if (trader == null) {
			return -1;
		}
		
		String storedPassword = trader.getPassword();
		if (!storedPassword.equals(password)) {
			return -2;
		}
		
		if (activeTraders.contains(trader)) {
			return -3;
		}
		
		//if (!trader.hasMessages()) trader.receiveMessage("");
		//WHAT DOES THIS DO^^^^^^^^^^^^^^^
		
		trader.openWindow();
		
		activeTraders.add(trader);
		return 0;
	}
	
	public void logout(Trader trader) {
		if (activeTraders.contains(trader)) {
			activeTraders.remove(trader);
		}
	}
	
	public void getQuote(String symbol, Trader trader) {
		String quote = currentStockExchange.getQuote(symbol);
		trader.receiveMessage(quote);
	}
	
	public void placeOrder(TradeOrder order) {
		currentStockExchange.placeOrder(order);
	}
	
	public String toString() {
		String str = "All Traders:\n";
		for(Map.Entry<String, Trader> entry : traders.entrySet()) {
			String key = entry.getKey();
			Trader value = entry.getValue();
			str += "\t" + key + " => " + value + "\n";
		}
		
		str += "Active Traders:\n";
		for (Trader t : activeTraders) {
			str += t;
		}
		return str;
	}
}