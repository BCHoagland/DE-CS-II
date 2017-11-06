package stockExchange;

import java.text.DecimalFormat;
import java.util.PriorityQueue;

public class Stock implements Comparable<Stock> {
	
	private String symbol, name;
	private double lowPrice, lastPrice, highPrice, dayVolume;
	
	private PriorityQueue<TradeOrder> buyOrders, sellOrders;
	
	private static DecimalFormat money = new DecimalFormat("$#,##0.00");
	
	Stock(String symbol, String name, double price) {
		this.symbol = symbol;
		this.name = name;
		this.lastPrice = price;
		this.lowPrice = price;
		this.highPrice = price;
		this.dayVolume = 0;
		
		buyOrders = new PriorityQueue<TradeOrder>(new PriceComparator(false));
		sellOrders = new PriorityQueue<TradeOrder>(new PriceComparator(true));
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
	
	public String getQuote() {
		//TODO finish this
		return this.name + " (" + this.symbol + ")\nPrice: " + money.format(this.lastPrice) + " hi: " + money.format(this.highPrice) + " low: " + money.format(this.lowPrice) + " vol: " + this.dayVolume + "\nAsk: " + this.sellOrders.peek().getPrice() + " size: " + this.sellOrders.peek().getNumShares() + " Bid: " + this.buyOrders.peek().getPrice() + " size: " + this.buyOrders.peek().getNumShares();
	}
	
	public void placeOrder(TradeOrder order) {
		if (order.isBuy()) {
			buyOrders.add(order);
		} else {
			sellOrders.add(order);
		}
		
		Trader trader = order.getTrader();
		
		String buyOrSellStr = global.BUY;
		if (order.isSell()) buyOrSellStr = global.SELL;
		
		String marketOrLimitStr = global.MARKET;
		if (order.isLimit()) marketOrLimitStr = money.format(order.getPrice());
		String msg ="New Order: " + buyOrSellStr + " " + this.symbol + " (" + this.name + ")\n" + order.getNumShares() + " shares at " + marketOrLimitStr;
		trader.receiveMessage(msg);
	}

	@Override
	public int compareTo(Stock s) {
		//TODO finish this
		return (int)Math.signum(this.getLastPrice() - s.getLastPrice());
	}
}