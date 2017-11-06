package stockExchange;

public class TradeOrder {
	
	private Trader trader;
	private int numShares;
	private double price;
	private String symbol;
	private boolean buy;
	private boolean market;
	
	public TradeOrder(Trader trader, String symbol, boolean buy, boolean market, int numShares, double price) {
		this.trader = trader;
		this.symbol = symbol;
		this.buy = buy;
		this.market = market;
		this.numShares = numShares;
		this.price = price;
	}
	
	public Trader getTrader() {
		return trader;
	}

	public int getNumShares() {
		return numShares;
	}

	public double getPrice() {
		return price;
	}

	public String getSymbol() {
		return symbol;
	}

	public boolean isBuy() {
		return buy;
	}
	
	public boolean isSell() {
		return !buy;
	}

	public boolean isMarket() {
		return market;
	}
	
	public boolean isLimit() {
		return !market;
	}
	
	public void substractShares(int shares) {
		if (shares <= this.numShares) {
			this.numShares -= shares;
		} else {
			throw new java.lang.IllegalArgumentException();
		}
	}

	public String toString() {
		String buyOrSell, marketOrLimit;
		if (buy) buyOrSell = global.BUY; else buyOrSell = global.SELL;
		if (market) marketOrLimit = global.MARKET; else marketOrLimit = global.LIMIT;
		
		return trader + "\n" + numShares + "\n" + price + "\n" + symbol + "\n" + buyOrSell + "\n" + marketOrLimit;
	}
}