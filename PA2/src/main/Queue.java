/**
* This file includes a queue data structure which enables user to store WorkEntrySearchNode objects.
* This class includes several basic functions, including enqueue, dequeue, front, and size.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

package main;

public class Queue<T> {
    private WorkEntryNode<T> front;      // front points to the front element in the queue
    private WorkEntryNode<T> rear;       // rear points to the last element in the queue
    private int count;      // current size of the queue

    /**
     * The constructor of queue class
     */
	public Queue() {
		front = rear = null;
		count = 0;
	}
	
	/**
	 * This function takes a T-typed data and stores the last as a rear node inside the queue object
	 * @param data a T type object
	 * @runtime O(1)
	 */
	public void enqueue(T data) {
		
		if (rear != null) {
			WorkEntryNode<T> input = new WorkEntryNode<T>(data);
            rear.next = input;
            rear = rear.next;
        } else {
        	WorkEntryNode<T> input = new WorkEntryNode<T>(data);
        	rear = input;
            front = rear;
        }
        count++;
	}

	/**
	 * This function removes the first element inside the queue object and returns it; If the queue does not contain any element, it will return null;
	 * @return a WorkEntryNode
	 * @runtime O(1)
	 */
	public T dequeue() {
		if (isEmpty()) {
            return null;
        }

		T value = (T) front.value;
		front = front.next;
        count--;
        
        if (front == null) {
            rear = null;
        }
        
        return value;
	}
	
	/**
	 * This function checks whether the queue object contain any elements
	 * @return a boolean result; true if the queue object is empty
	 * @runtime O(1)
	 */
	public boolean isEmpty() {
        return front == null;
    }
	
	/**
	 * This function returns the value associated with the first node inside of the queue object
	 * @return null if the queue is empty or a T type object that associated with the first element
	 * @runtime O(1)
	 */
	public T front() {
		return (T) front.value;
	}
	
	/**
	 * This function returns an integer which represents the number of elements inside the queue obejct
	 * @return a integer count
	 * @runtime O(1)
	 */
	public int size() {
		return count;
	}
	
}
