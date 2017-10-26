package stockExchange;

import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {
	
	private String symbol;
	private String name;
	private double lowPrice, lastPrice, highPrice;
	
	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;
	
	private static DecimalFormat money = new DecimalFormat("$#,##0.00");
	
	Stock(String s, String n, double p) {
		this.symbol = s;
		this.name = n;
		this.lastPrice = p;
		this.lowPrice = p;
		this.highPrice = p;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public String toString() {
		return this.getSymbol() + ", " + this.getName() + ", " + money.format(this.getLastPrice());
	}
	
	public void placeOrder(TradeOrder order) {
		
	}

	@Override
	public int compareTo(Stock s) {
		//TODO finish this
		return (int)Math.signum(this.getLastPrice() - s.getLastPrice());
	}
}