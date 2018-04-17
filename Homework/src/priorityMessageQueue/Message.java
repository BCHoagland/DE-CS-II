package priorityMessageQueue;

public class Message {
	
	private int priority;
	private int arrival;
	private String msg;
	
	public Message(String s, int p) {
		msg = s;
		priority = p;
		arrival = 0;
	}
	
	public int getPriority() {return priority;}
	public int getArrivalTime() {return arrival;}
	public String getMsg() {return msg;}
	
	public void incrementArrivalTime() {
		arrival += 1;
	}
}