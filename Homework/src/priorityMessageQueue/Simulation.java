package priorityMessageQueue;

import java.util.ArrayList;

public class Simulation {
	
	public static final int NUM_ITERS = 100000;
	public static final int NUM_PRIORITIES = 5;
	public static final int PROCESS_DELAY = 4;
	public static final int BASE_MSGS_IN_PQ = 100;
	
	public static ArrayList<ArrayList<Integer>> times = new ArrayList<ArrayList<Integer>>();
	
	//increment the arrival times of the messages in the queue, remove the top one, and store its arrival time data
	public static MessagePriorityQueue processQueue(MessagePriorityQueue pq) {
		pq.incrementAllArrivalTimes();
		Message removedMsg = pq.remove();
		times.get(removedMsg.getPriority()).add(removedMsg.getArrivalTime() + PROCESS_DELAY);
		return pq;
	}
	
	public static void main(String args[]) {
		
		//create the empty array lists that will store the arrival time data for each priority level
		for (int i = 0; i < NUM_PRIORITIES; i++) {
			times.add(new ArrayList<Integer>());
		}
		
		//create a new priority queue
		MessagePriorityQueue pq = new MessagePriorityQueue();
		
		//add and remove messages from the queue the specified number of times
		for (int i = 0; i < NUM_ITERS; i++) {
			//add a message of a random priority to the queue
			int priority = (int)(Math.random() * NUM_PRIORITIES);
			Message msg = new Message("message #" + i, priority);
			pq.add(msg);
			
			//only remove a message if the priority queue has been filled to the specified amount
			//we need a reasonable number of messages in the priority queue to start off with in order for the simulation's results to be accurate
			if (i > BASE_MSGS_IN_PQ - 1) {
				pq = processQueue(pq);
			}
		}
		
		//empty the priority queue once new messages are done being added to it
		while (!pq.isEmpty()) {
			pq = processQueue(pq);
		}
		
		//print out the average arrival time for each priority level
		int n = 0;
		for (ArrayList<Integer> q : times) {
			double total = 0;
			for (int i = 0; i < q.size(); i++) {
				total += q.get(i);
			}
			System.out.println("average arrival time for priority level " + n + ": " + (total / q.size()) + " minutes");
			n++;
		}
	}
}