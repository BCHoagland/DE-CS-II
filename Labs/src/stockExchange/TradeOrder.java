package stockExchange;

public class TradeOrder {
	
	private static final String BUY = "buy";
	private static final String SELL = "sell";
	
	private static final String MARKET = "market";
	private static final String LIMIT = "limit";
	
	private Trader trader;
	private int numShares;
	private double price;
	private String symbol;
	private boolean buy;
	private boolean market;
	
	public TradeOrder(Trader trader, int numShares, double price, String symbol, boolean buy, boolean market) {
		this.trader = trader;
		this.numShares = numShares;
		this.price = price;
		this.symbol = symbol;
		this.buy = buy;
		this.market = market;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public String toString() {
		String buyOrSell, marketOrLimit;
		if (buy) buyOrSell = BUY; else buyOrSell = SELL;
		if (market) marketOrLimit = MARKET; else marketOrLimit = LIMIT;
		
		return trader + "\n" + numShares + "\n" + price + "\n" + symbol + "\n" + buyOrSell + "\n" + marketOrLimit;
	}
}