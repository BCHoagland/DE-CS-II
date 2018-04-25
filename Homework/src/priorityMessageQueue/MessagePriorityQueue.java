package priorityMessageQueue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * MessagePriorityQueue class that serves as a priority queue
 * @author Braden Hoagland
 * 
 */
public class MessagePriorityQueue {
	
	/**
	 * constant that stores the number of different priority levels
	 */
	public static final int NUM_PRIORITIES = 5;
	
	/**
	 * priority queue that is composed of an arrayList of MessageQueues
	 */
	ArrayList<MessageQueue> queue = new ArrayList<MessageQueue>();
	
	/**
	 * constructor that initializes an empty MessageQueue for each priority level
	 */
	public MessagePriorityQueue() {
		for (int i = 0; i < NUM_PRIORITIES; i++) queue.add(new MessageQueue());
	}
	
	/**
	 * adds a message to the proper MessageQueue in the priority queue, based on its priority level
	 * @param msg message to be added to the priority queue
	 * @return true
	 */
	public boolean add(Message msg) {
		queue.get(msg.getPriority()).add(msg);
		return true;
	}
	
	/**
	 * removes the first message in priority queue; throws an exception if the priority queue is empty
	 * @return the first message in the first MessageQueue in the priority queue
	 */
	public Message remove() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) {
			throw new NoSuchElementException();
		}
		return queue.get(headIndex).remove();
	}
	
	/**
	 * see the first message in the priority queue, if the priority queue is not empty
	 * @return first message in the first MessageQueue in the priority queue if the priority queue is not empty; null if it is empty
	 */
	public Message peek() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) return null;
		return queue.get(headIndex).peek();
	}
	
	/**
	 * removes the first message in priority queue, if the priority queue is not empty
	 * @return first message in the first MessageQueue in the priority queue if the priority queue is not empty; null if it is empty
	 */
	public Message poll() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) return null;
		return queue.get(headIndex).remove();
	}
	
	/**
	 * determine if the priority queue has no elements
	 * @return true if all queues inside the priority queue are empty; false otherwise
	 */
	public boolean isEmpty() {
		for (MessageQueue q : queue) {
			if (!q.isEmpty()) return false;
		}
		return true;
	}
	
	/**
	 * increment the arrival times in each message in each queue in the priority queue
	 */
	public void incrementAllArrivalTimes() {
		for (MessageQueue q : queue) {
			q.incrementAllArrivalTimes();
		}
	}
	
	/**
	 * helper method to determine the queue of most important priority that has messages in it
	 * @return index of the queue with elements whose priority level is the most important; -1 if all queues are empty
	 */
	public int getHeadIndex() {
		int i = 0;
		while (queue.get(i).peek() == null) {
			i++;
			if (i >= queue.size()) return -1;
		}
		return i;
	}
}