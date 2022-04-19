/**
* The FindMinPath implements a min priority queue by invoking function created in MinHeap class.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/
package main;

public class PriorityQueue {
	private MinHeap MH;
	private int size;
	
	public PriorityQueue(int MaxSize){
		this.MH = new MinHeap(MaxSize);
		this.size = 0;
	}
	
	/***
	 * Insert a node to the min priority queue.
	 * @param g a node inside the graph
	 * o(1)
	 */
	public void insert(GraphNode g) {
		this.size++;
		MH.insert(g);
	}
	
	/***
	 * Returns the min heap
	 * @return the min heap initialized in this class
	 * o(1)
	 */
	public MinHeap getMH() {
		return MH;
	}
	
	/***
	 * returns the element in the queue with the smallest priority
	 * @return the element in the queue with the smallest priority or null if the queue is empty
	 * o(1)
	 */
	public GraphNode pullHighestPriorityElement() {
		try {
			if (isEmpty()) 
				throw new ArrayIndexOutOfBoundsException("pullHighestPriorityElement command failed. Queue is empty.");
			this.size--;
			return MH.poll();
			}
		catch (ArrayIndexOutOfBoundsException ex){
			System.out.println(ex);
			return null;
			}
	}

	/***
	 * Heapify up the node g to the correct position.
	 * @param g graph node inside the input
	 * o(nlogn)
	 */
	public void rebalance(GraphNode g) {
		MH.heapifyUp(g);
	}
	
	/**
	 * returns true if the queue is empty else false
	 * @return true or false
	 * o(1)
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/***
	 * returns the hashMap object created in the min heap class
	 * @return the HashTable object initialized in the minheap class
	 * o(1)
	 */
	public HashMap getHashMap(){
		return MH.getHashTable();
	}
	
	/**
	 * Check if the node has been visited before. returns true if it has already been visited and dequeued from the queue.
	 * @param g a node g inside the graph
	 * @return true or false
	 * o(1)
	 */
	public boolean isVisited(GraphNode g) {
		for (int i = 0; i < MH.getVisitedSize(); i++) {
			if (MH.getDequeueArray()[i].getId().equals(g.getId())) {
				return true;
			}
		}
		return false;
	}
}
