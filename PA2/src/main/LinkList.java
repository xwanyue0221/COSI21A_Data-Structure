/**
* This file includes a single linked list data structure which enables user to store work entries in the format of a linked list.
* This class includes several basic functions, including node insertion, deletion, and value printing.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

package main;

public class LinkList {

	private Node head;
	int size;
	
	/**
	 * This function isEmpty() checks whether the Linklist contains any elements. 
	 * @return a boolean result which indicates whether the Linklist contains any elements. 
	 * @runtime O(1)
	 */
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**
	 * This function insert() takes a WorkEntry objecct and insert this object to the Linklist.
	 * @param e a WorkEntry object
	 * @runtime O(n)
	 */
	public void insert(WorkEntry e) {
		if (head == null) {
			Node newNode = new Node();
			newNode.value = e;
			newNode.next = head;
			head = newNode;
			size = 1;
			}
		else {
			Node curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			Node newNode = new Node();
			newNode.value = e;
			curr.next = newNode;
			size++;
		}
	}
	
	/**
	 * This function delete() takes a integer i and deletes the a WorkEntry object at the ith position.
	 * @param i an integer which tells which object shall be deleted
	 * @runtime O(n)
	 */
	public void delete(int i) {
		Node curr = head; 
		Node prev = null;
		for (int c = 0; c < i; c++) {
			prev = curr;
			curr = curr.next;
			}
		if (prev == null) {
			head = curr.next;}
		else {
			prev.next = curr.next;}
		size--;
	}
	
	/**
	 * This function getSzie() returns the number of elements stored in the Linklist.
	 * @return a integer number which indicates the number of elements in the Linklist
	 * @runtime O(1)
	 */
	public int getSzie() {
    	return this.size;
    	}
	
	/**
	 * This function getValue() returns value possessed by the head node.
	 * @return a WorkEntry object which stored in the head node
	 * @runtime O(1)
	 */
    public WorkEntry getValue() {
    	return this.head.value;
    	}
    
	/**
	 * This function printLinkedList() returns a String representations of the WorkEntry objects associated with this node on separate lines followed by the total time spent 
	 * 									on these entries rounded to one decimal place
	 * @return a String representations of the WorkEntry objects 
	 * @runtime O(n)
	 */
    public String printLinkedList() {
    	String output = "";
    	double Total = 0;
    	Node curr = head;
    	while (curr != null) {
    		Total += curr.value.getTimeSpent();
    		output += curr.value.toString() + "\n";
    		curr = curr.next;
    		}
    	output += "Total: ";
    	output += Total;
    	output += " h";
    	return output;
    	}
}
