package intPriorityQueue;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@SuppressWarnings("serial")
public class IntPriorityQueue extends ArrayList<Integer> {

	private int numItems;

	public boolean isEmpty() {
		return numItems == 0;
	}

	public Integer peek() {
		if (numItems == 0) throw new NoSuchElementException();
		return get(1);
	}

	public boolean add(Integer n) {
		if (numItems == 0 && size() == 0) super.add(null);			//if array list is completely empty, fill up index 0
		super.add(n);
		numItems++;
		reheapUp();
		return true;
	}

	public Integer remove() {
		if (numItems == 0) throw new NoSuchElementException();
		Integer minNum = get(1);
		set(1, remove(numItems));
		numItems--;
		reheapDown();
		return minNum;
	}

	private void swap(int i1, int i2) {
		Integer temp = get(i1);
		set(i1, get(i2));
		set(i2, temp);
	}

	private void reheapUp() {
		//start at bottom right
		int index = numItems;

		//check if current index is greater than parent
		//if it is, swap them
		//if not, we're done
		int parent = index / 2;

		while (parent >= 1 && get(index) > get(parent)) {
			swap(parent, index);
			index = parent;
			parent /= 2;
		}
	}

	private void reheapDown() {
		//start at the root
		int index = 1;
		while (index < numItems) {

			//neither child exists
			if (2 * index > numItems) break;

			//only the left child exists
			if (2 * index + 1 > numItems) {
				//left child is <= parent
				if (get(2 * index) <= get(index)) break;
				//left child is > parent
				else {
					swap(2 * index, index);
					break;
				}
			}

			//both children exist
			int left = 2 * index;
			int right = 2 * index + 1;

			//check if current root > children
			//if it is, we're done
			if (get(left) <= get(index) && get(right) <= get(index)) break;

			//if not, then swap with the greatest child that it's less than

			//right is greatest child
			if (get(right) > get(left)) {
				//right is greater than current
				if (get(right) > get(index)) {
					swap(right, index);
					index = right;
				//left is greater than current and right is not
				} else {
					swap(left, index);
					index = left;
				}
			//left is greatest child
			} else {
				//left is greater than current
				if (get(left) > get(index)) {
					swap(left, index);
					index = left;
				//right is greater than current and left is not
				} else {
					swap(right, index);
					index = right;
				}
			}
		}
	}

	public static void main(String[] args) {
		IntPriorityQueue pq = new IntPriorityQueue();

		int[] valuesToAdd = {1, 4, 2, 10, 0};
		int timesToRemove = 3;

		for (int n : valuesToAdd) {
			pq.add(n);
			System.out.println("after adding " + n + ", the array is: " + pq);
		}

		System.out.println("--------------------");

		for (int i = 0; i < timesToRemove; i++) {
			pq.remove();
			System.out.println("after removing the root, the array is: " + pq);
		}

	}
}