package priorityMessageQueue;

/**
 * Message class that represents a message to be processed in my priority queue
 * @author Braden Hoagland
 *
 */
public class Message {
	
	/**
	 * field that stores the priority level of the message
	 */
	private int priority;
	
	/**
	 * field that stores the arrival time of the message
	 */
	private int arrival;
	
	/**
	 * field that stores the actual message of the message
	 */
	private String msg;
	
	/**
	 * constructor that sets the msg and priority to the given values and sets the arrival time to 0
	 * @param s string that will be the message's msg
	 * @param p priority level of the message
	 */
	public Message(String s, int p) {
		msg = s;
		priority = p;
		arrival = 0;
	}
	
	/**
	 * get the priority level of the message
	 * @return priority level of the message as an int
	 */
	public int getPriority() {return priority;}
	
	/**
	 * get the arrival time of the message
	 * @return arrival time of the message as an int
	 */
	public int getArrivalTime() {return arrival;}
	
	/**
	 * get the msg of the message
	 * @return msg of the message as a string
	 */
	public String getMsg() {return msg;}
	
	/**
	 * increment the arrival time of the message by 1
	 * this will be called by the queue that the message is a part of
	 */
	public void incrementArrivalTime() {
		arrival += 1;
	}
}