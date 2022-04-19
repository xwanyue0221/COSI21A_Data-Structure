package main;

/**
* This file includes a node data structure that enables the user to store work entries as a node.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

public class Node {
	
	/**
	 * Next is a pointer which points to the next node;
	 * Value is a WorkEntry Object which stores all WorkEnrty values
	 */
	
	public Node next;
	public WorkEntry value;
	
}