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
		testBrokerage();
		testTrader();
	}
	
	
	
	//WHAT ABOUT CONSTRUCTORS
	
	
	
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
		
		//toString()
		System.out.println("TOSTRING(): " + testStock + "\n");
		
		//placeOrder()
		System.out.println("PLACEORDER(): " + "\n");
		
		//compareTo()
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
	
	public static void testBrokerage() {
		printTitle("TESTING BROKERAGE");
		
		//addUser
		System.out.println("ADDUSER():\nnew user: " + testBrokerage.addUser("Braden", "password"));
		System.out.println("ADDUSER():\nanother new user: " + testBrokerage.addUser("Charles", "p8ssw0rd"));
		System.out.println("name too short: " + testBrokerage.addUser("abc", "password"));
		System.out.println("name too long: " + testBrokerage.addUser("abcdefghijk", "password"));
		System.out.println("password too short: " + testBrokerage.addUser("Braden", "a"));
		System.out.println("password too long: " + testBrokerage.addUser("Braden", "abcdefghijk"));
		System.out.println("repeat user: " + testBrokerage.addUser("Braden", "password") + "\n");
		
		//toString
		System.out.println("TOSTRING():\n" + testBrokerage);
		
		//login
		System.out.println("LOGIN():" + "\n");
	}
	
	public static void testTrader() {
		printTitle("TESTING TRADER");
		
		//toString
		System.out.println("TOSTRING(): " + testTrader);
	}
}