package priorityMessageQueue;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MessagePriorityQueue {
	ArrayList<MessageQueue> queue = new ArrayList<MessageQueue>();
	
	public MessagePriorityQueue() {
		for (int i = 0; i < 5; i++) queue.add(new MessageQueue());
	}
	
	public boolean add(Message msg) {
		queue.get(msg.getPriority()).add(msg);
		return true;
	}
	
	public Message remove() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) {
			throw new NoSuchElementException();
		}
		return queue.get(headIndex).remove();
	}
	
	public Message peek() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) return null;
		return queue.get(headIndex).peek();
	}
	
	public Message poll() {
		int headIndex = getHeadIndex();
		if (headIndex == -1) return null;
		return queue.get(headIndex).remove();
	}
	
	public boolean isEmpty() {
		for (MessageQueue q : queue) {
			if (!q.isEmpty()) return false;
		}
		return true;
	}
	
	public void incrementAllArrivalTimes() {
		for (MessageQueue q : queue) {
			q.incrementAllArrivalTimes();
		}
	}
	
	public int getHeadIndex() {
		int i = 0;
		while (queue.get(i).peek() == null) {
			i++;
			if (i >= queue.size()) return -1;
		}
		return i;
	}
}