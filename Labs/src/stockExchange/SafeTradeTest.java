package stockExchange;

public class SafeTradeTest {

	public static void main(String[] args) {
		Stock s = new Stock("MSFT", "Microsoft", 4000);
		System.out.println(s + "\n");
		
		Brokerage testBrokerage = new Brokerage();
		Trader testTrader = new Trader(testBrokerage, "testUsername", "testPassword");
		TradeOrder testTradeOrder = new TradeOrder(testTrader, 4, 4000, "MSFT", true, true);
		System.out.println(testTradeOrder);
	}
}