package priorityMessageQueue;

import java.util.ArrayList;

public class MessageQueue {
	
	ArrayList<Message> queue = new ArrayList<Message>();
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public boolean add(Message msg) {
		queue.add(msg);
		return true;
	}
	
	public Message remove() {
		return queue.remove(0);
	}
	
	public Message poll() {
		if (queue.isEmpty()) return null;
		return queue.remove(0);
	}
	
	public Message peek() {
		if (queue.isEmpty()) return null;
		return queue.get(0);
	}
	
	public void incrementAllArrivalTimes() {
		for (Message msg : queue) {
			msg.incrementArrivalTime();
		}
	}
}