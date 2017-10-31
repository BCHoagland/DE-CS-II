package stockExchange;

public class SafeTradeTest {
	
	private static Stock s = new Stock("MSFT", "Microsoft", 4000);
	private static StockExchange testExchange = new StockExchange();
	private static Brokerage testBrokerage = new Brokerage(testExchange);
	private static Trader testTrader = new Trader(testBrokerage, "testUsername", "testPassword");
	private static TradeOrder testTradeOrder = new TradeOrder(testTrader, 4, 4000, "MSFT", true, true);
	
	public static void main(String[] args) {
		testStock();
		testStockExchange();
	}
	
	public static void printTitle(String title) {
		String str = "";
		for (int i = 0; i < 64; i++) {
			str += "=";
		}
		str += "\n" + title + "\n";
		for (int i = 0; i < 64; i++) {
			str += "=";
		}
		System.out.println(str);
	}
	
	public static void testStock() {
		printTitle("TESTING STOCK");
		Stock testStock = new Stock("GOOG", "Google", 1234);
		System.out.println("TOSTRING(): " + testStock + "\n");
		System.out.println("PLACEORDER(): " + "\n");
		System.out.println("COMPARETO(): " + "\n");
	}
	
	public static void testStockExchange() {
		printTitle("TESTING STOCK EXCHANGE");
		//getQuote()
		System.out.println("GETQUOTE(): " + testExchange.getQuote("MSFT") + "\n");
		
		//listStock()
		testExchange.listStock("MSFT", "Microsoft", 50000);
		System.out.println("LISTSTOCK(): " + testExchange.getQuote("MSFT") + "\n");
		
		//placeOrder()
		TradeOrder testTradeOrder = new TradeOrder(testTrader, 4, 4000, "MSFT", true, true);
		testExchange.placeOrder(testTradeOrder);
		//ADD TEST OUTPUT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}
}