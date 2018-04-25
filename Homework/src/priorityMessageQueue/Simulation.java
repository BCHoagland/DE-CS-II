package priorityMessageQueue;

import java.util.ArrayList;

/**
 * Simulation class that uses the MessagePriorityQueue to report on average arrival time of messages of different priorities
 * @author Braden Hoagland
 *
 */
public class Simulation {
	
	/**
	 * constant that stores the number of different priority levels
	 */
	public static final int NUM_PRIORITIES = 5;
	
	/**
	 * constant that stores how long it takes to process a message once it's made its way through the priority queue
	 */
	public static final int PROCESS_DELAY = 4;
	
	/**
	 * constant that stores how many messages should be used to pro-populate the priority queue
	 */
	public static final int BASE_MSGS_IN_PQ = 50;
	
	/**
	 * array list of array lists representing the arrivals times of all messages of each priority level
	 */
	public static ArrayList<ArrayList<Integer>> times;

	/**
	 * increment the arrival times of the messages in the queue, remove the top one, and store its arrival time data
	 * @param pq priority queue to process
	 * @return the updated priority queue
	 */
	public static MessagePriorityQueue processQueue(MessagePriorityQueue pq) {
		Message removedMsg = pq.remove();
		times.get(removedMsg.getPriority()).add(removedMsg.getArrivalTime() + PROCESS_DELAY);
		pq.incrementAllArrivalTimes();
		return pq;
	}
	
	/**
	 * run a simulation with the given number of iterations
	 * @param numIters number of messages to create during the simulation
	 */
	public static void runSimulation(int numIters) {
		//create the empty array lists that will store the arrival time data for each priority level
		times = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < NUM_PRIORITIES; i++) {
			times.add(new ArrayList<Integer>());
		}

		//create a new priority queue
		MessagePriorityQueue pq = new MessagePriorityQueue();

		//add and remove messages from the queue the specified number of times
		for (int i = 0; i < numIters; i++) {
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
		System.out.println("simulation with " + numIters + " iterations");
		int n = 0;
		for (ArrayList<Integer> q : times) {
			double total = 0;
			for (int i = 0; i < q.size(); i++) {
				total += q.get(i);
			}
			System.out.println("average arrival time for priority level " + n + ": " + (total / q.size()) + " minutes");
			n++;
		}
		System.out.println();
	}
	
	/**
	 * main method that runs simulations with 100, 1000, 10000, and 100000 messages
	 * @param args command line arguments (none used)
	 */
	public static void main(String args[]) {
		for (int i = 100; i <= 100000; i *= 10) {
			runSimulation(i);
		}
	}
}