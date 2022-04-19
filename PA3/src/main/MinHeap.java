/**
* The MinHeap is a class which enables user to create a min heap array and provide extraction and deletion within O(nlogn) runtime.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/
package main;

public class MinHeap {
	public int HeapSize;
	public int TakenSize;
	public GraphNode[] HeapArray;
	public HashMap HashTable;
	private GraphNode[] dequeued;
	private int visited; 
	
	public MinHeap(int Hsize){
		this.HeapSize = Hsize;
		this.TakenSize = -1;
		this.visited = -1;
		this.HeapArray = new GraphNode[HeapSize];
		this.HashTable = new HashMap(HeapSize);
		this.dequeued = new GraphNode[HeapSize];
	}
	
	/**
	 * returns the index of left child
	 * @param i a integer shows the index of a node inside the min heap array
	 * @return the index of left child
	 * O(1)
	 */
	public int Left(int i) {
        return (2*i + 1);
    }

	/**
	 * returns the index of right child
	 * @param i a integer shows the index of a node inside the min heap array
	 * @return the index of right child
	 * O(1)
	 */
	public int Right(int i) {
        return (2*i + 2);
    }
	
	/**
	 * returns the index of node parent
	 * @param i a integer shows the index of a node inside the min heap array
	 * @return the index of right child
	 * O(1)
	 */
	private int parent(int i){
		if (i == 0) return 0;
        return (i - 1) / 2;
    }
	
	/**
	 * insert a graph node inside the min heap array
	 * @param g a graph node
	 * O(nlogn)
	 */
	public void insert(GraphNode g){
		if (HashTable.hasKey(g) == false) {
			this.TakenSize++;
			HeapArray[this.TakenSize] = g;
			this.HashTable.set(g, this.TakenSize);
			heapifyUp(g);
			}
		}
	
	/***
	 * swapping the position between two node
	 * @param child a graph node
	 * @param parent a graph node
	 * O(1)
	 */
	public void swap(int child, int parent) {
		GraphNode largger = this.HeapArray[child];
		this.HeapArray[child] = this.HeapArray[parent];
		this.HeapArray[parent] = largger;
	}
	
	/***
	 * Heapify up the node to its correct position
	 * @param g a graph node
	 * O(logn)
	 */
	public void heapifyUp(GraphNode g) {
		int i = this.HashTable.getValue(g);
		while (i >0 && this.HeapArray[parent(i)].priority > this.HeapArray[i].priority){
			HashTable.set(HeapArray[i], parent(i));
			HashTable.set(HeapArray[parent(i)], i);
			swap(i, parent(i));
			i = parent(i);
			}
	}
	
	/***
	 * Heapify down a node to its correct position
	 * @param g a graph node
	 * O(logn)
	 */
	public void heapifyDown(int i) {
		int left = Left(i);
        int right = Right(i);

        int smallest = i;
        if (left <= this.TakenSize && this.HeapArray[left].priority < this.HeapArray[i].priority) {
        	smallest = left;
        }
        if (right <= this.TakenSize && this.HeapArray[right].priority < this.HeapArray[smallest].priority) {
        	smallest = right;
        }
        if (smallest != i){
            HashTable.set(this.HeapArray[i], smallest);
            HashTable.set(this.HeapArray[smallest], i);
            swap(i, smallest);
            heapifyDown(smallest);
            }
	}
	
	/***
	 * returns the element with a smallest priority and replace the node with the node storing at the least position
	 * @param g a graph node
	 * O(logn)
	 */
	public GraphNode poll()
    {
		try {
			if (isEmpty() == true) 
				throw new Exception("Fail to get the smallest node. Index is out of range (Heap underflow)");
		
			GraphNode smallest = this.HeapArray[0];
			this.HashTable.set(smallest, -1);
			recordDequeued(smallest);
			HeapArray[0] = HeapArray[this.TakenSize];
			this.TakenSize--;
			
			if (this.TakenSize >= 0) {
				HashTable.set(HeapArray[0], 0);
				heapifyDown(0);
			}
			
			return smallest;
			}
		catch (Exception ex){
			System.out.println(ex);
			return null;
			}
		}
	
	/***
	 * return the hashTable initialized in this class
	 * @return a hashTable
	 * o(1)
	 */
	public HashMap getHashTable(){
		return this.HashTable;
	}
	
	/***
	 * Insert a graphnode g inside the dequeued array
	 * @param g a graph node
	 * o(1)
	 */
	public void recordDequeued(GraphNode g) {
		this.visited++;
		this.dequeued[visited] = g;
	}
	
	/***
	 * return the dequeued array
	 * @return an array storing all the elements that has been dequeued from the queue
	 * o(1)
	 */
	public GraphNode[] getDequeueArray() {
		return this.dequeued;
	}
	
	/***
	 * return the number of number of elements that has been dequeued from the queue
	 * @return a integer indicates the number of elements that has been dequeued from the queue
	 * o(1)
	 */
	public int getVisitedSize() {
		return this.visited;
	}
	
	/***
	 * check if the heap array is empty or not
	 * @return true of false
	 * o(1)
	 */
	public boolean isEmpty(){
		if (this.TakenSize == -1) return true;
		return false;
	}
}
