package priorityMessageQueue;

import java.util.ArrayList;

/**
 * MessageQueue class that will be used to compose the priority queue (my priority queue will be an arrayList of MessageQueues)
 * @author Braden Hoagland
 *
 */
public class MessageQueue {
	
	/**
	 * arrayList that will act as the queue of messages
	 */
	ArrayList<Message> queue = new ArrayList<Message>();
	
	/**
	 * determines if the queue has no messages in it
	 * @return true if queue has no messages, false otherwise
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * adds a message to the end of the queue
	 * @param msg message to be added
	 * @return true
	 */
	public boolean add(Message msg) {
		queue.add(msg);
		return true;
	}
	
	/**
	 * removes the message at the front of the queue
	 * throws exception if the queue is empty
	 * @return first message in queue
	 */
	public Message remove() {
		return queue.remove(0);
	}
	
	/**
	 * removes the message at the front of the queue if the queue is not empty
	 * @return first message in queue if queue is not empty; null if queue is empty
	 */
	public Message poll() {
		if (queue.isEmpty()) return null;
		return queue.remove(0);
	}
	
	/**
	 * see the first message in the queue if the queue is not empty
	 * @return first message in queue if queue is not empty; null if queue is empty
	 */
	public Message peek() {
		if (queue.isEmpty()) return null;
		return queue.get(0);
	}
	
	/**
	 * increment the arrival times of all messages in the queue
	 */
	public void incrementAllArrivalTimes() {
		for (Message msg : queue) {
			msg.incrementArrivalTime();
		}
	}
}