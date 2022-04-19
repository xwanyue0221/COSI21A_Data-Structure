/**
* The HashMap implements a hash table which allows users to store, search, or update the graph and its corresponding index as a Entry object.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/
package main;

public class HashMap {
	public int size;
	public int fullSpace;
	public double factor;
	public Entry[] hashTable;
	
	public HashMap(int size){
		this.size = 10;
		this.fullSpace = 0;
		this.factor = this.fullSpace / this.size;
		this.hashTable = new Entry[10];
	}

	/***
	 * The self-dfined hash function which turns a list of string to integer
	 * @param key a unique ID string of each node
	 * @return the hashKey which represents the index of Entry in the hash table
	 * O(1)
	 */
	public int hashFunction(String key) {	    
	    int hashVal = 0;
	    for (int j = 0; j < key.length(); j++) {
	        hashVal += key.charAt(j);
	    }
		return hashVal % this.size;
	}
	
	/**
	 * Examines if the hash table contains a node
	 * @param g a graph node
	 * @return true or false
	 * O(1)
	 */
	public boolean hasKey(GraphNode g) {
		int i = 0;
		int insertKey = (hashFunction(g.getId()) + i) % this.size;
		while (i < this.size && hashTable[insertKey]!=null){
			if (hashTable[insertKey].getKey().getId().equals(g.getId())) {
				return true;
			}
			i++;
			insertKey = (hashFunction(g.getId()) + i) % this.size;
		}
		return false;
	}

	/***
	 * insert or update a entry in the hash table
	 * @param key a graph node
	 * @param value the index of the graph node storing in the heap array
	 * O(1)
	 */
	public void set(GraphNode key, int value) {
		int i = 0;
		int hashkey = (hashFunction(key.getId()) + i) % this.size;
		while (true){
			if (hashTable[hashkey]==null) {
				hashTable[hashkey] = new Entry(key, value);
				this.fullSpace++;
				if (this.fullSpace*1.0/this.size >= 0.5){
					reShape();
				}
				return;
			}
			else if (hashTable[hashkey].getValue()==-1) {
				hashTable[hashkey] = new Entry(key, value);
				return;
			}
			else if (hashTable[hashkey].getKey().getId().equals(key.getId())){
				hashTable[hashkey].setValue(value);
				return;
			}
			i++;
			hashkey = (hashFunction(key.getId()) + i) % this.size;
		}
	}
	
	/***
	 * Search and return the value associated with the input graph node g
	 * @param g a graph node g
	 * @return the true value or -1 if the node does not existed or has already been dequeued from the queue
	 * o(1)
	 */
	public int getValue(GraphNode g)  {
		int i = 0;
		int insertKey = (hashFunction(g.getId()) + i) % this.size;
		
		while (i < this.size && hashTable[insertKey]!=null){
			if (hashTable[insertKey].getKey().getId().equals(g.getId())) return hashTable[insertKey].getValue();
			i++;
			insertKey = (hashFunction(g.getId()) + i) % this.size;
		}
		return -1;
	}
	
	/**
	 * Reshape the whole hash table if the score is higher than 0.5
	 */
	public void reShape()  {
		Entry[] NewArray = new Entry[this.size * 2];
		this.size = this.size * 2;
		
		int k = 0;
		while (k < this.size/2) {
			if (hashTable[k] != null) {
				GraphNode key = hashTable[k].getKey();
				int value = hashTable[k].getValue();
				
				int i = 0;
				int insertKey = (hashFunction(key.getId()) + i) % this.size;
				while (NewArray[insertKey] != null) {
					i++;
					insertKey = (hashFunction(key.getId()) + i) % this.size;
				}
				NewArray[insertKey] = hashTable[k];
			}
			k++;
		}
		hashTable = NewArray;
		
	}
}
