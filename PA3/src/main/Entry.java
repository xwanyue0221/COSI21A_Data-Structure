/**
* This file includes a Entry data structure that enables the user to store each node and its indexing position as a entry object.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/

package main;

public class Entry {
	public GraphNode key;
	public int value;
	
	public Entry(GraphNode key, int value){
		this.key = key;
		this.value = value;
		}
	
	/**
	 * return the value 
	 * @return the index of a node storing in the min heap array
	 * o(1)
	 */
	public int getValue(){
		return this.value;
	}
	
	/**
	 * return the key (graph node) of the entry object
	 * @return the graph node 
	 * o(1)
	 */
	public GraphNode getKey(){
		return this.key;
	}
	
	/**
	 * replace the old value with the new value
	 * o(1)
	 */
	public void setValue(int new_value){
		this.value = new_value;
	}
}
