package stockExchange;

import java.util.HashMap;

public class StockExchange {
	
	private HashMap<String, Stock> stocks;
	
	public StockExchange() {
		stocks = new HashMap<String, Stock>();
	}
	
	public String getQuote(String symbol) {
		if (stocks.containsKey(symbol)) {
			return stocks.get(symbol).toString();
		} else {
			return "This stock does not exist rip";
		}
	}
	
	public void listStock(String symbol, String name, double price) {
		if (!stocks.containsKey(symbol)) {
			Stock s = new Stock(symbol, name, price);
			stocks.put(symbol, s);
		}
	}
	
	public void placeOrder(TradeOrder order) {
		String sym = order.getSymbol();
		if (stocks.containsKey(sym)) {
			stocks.get(sym).placeOrder(order);
		}
	}
}