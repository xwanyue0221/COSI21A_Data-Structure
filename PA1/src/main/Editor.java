/**
* The Editor class defines several methods to manipulate the text editor. Those functionalities include creating a new file, 
* 	reading existential files, displaying content, inserting content, deleting content in different fashions, curser moving 
* 	and tracking, content size tracking, and providing quick keyboard access such as remove content and moving to the head or tail.
* 		
* Known Bugs: None
*
* @author Xiao Wanyue
* xwanyue@brandeis.edu
* Oct 7, 2021
* COSI 21A PA1
*/
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Editor {
	
	public int numChars; /** KEEP THIS PUBLIC : use this to store the number of characters in your Editor */
	public int curPos; /** KEEP THIS PUBLIC : use this to store the current cursor index in [0, numChars] */
	
	public Node cur; /** KEEP THIS PUBLIC : use this to reference the node that is after the visual cursor or null if curPos = numChars */
	public Node head; /** KEEP THIS PUBLIC : use this to reference the first node in the Editor's doubly linked list */
	public Node tail; /** KEEP THIS PUBLIC : use this to reference the last node in the Editor's doubly linked list */
	
	/**
	 * Initialize a empty editor
	 */
	public Editor() {
		numChars =0;
		curPos = 0;
		cur = null;
		head = null;
		tail = null;
	}
	/**
	 * Reading and displaying content from an existential file using the provided file path. The run time is O(1).
	 * @param filepath A string represents the location the file belongs to and the name the file possesses.
	 * @throws FileNotFoundException If the filepath is invalid, the system will throw out an error regarding file not found exception.
	 */
	public Editor(String filepath) throws FileNotFoundException {
		
		File textFile = new File(filepath); 
		Scanner tf = new Scanner(textFile);
		
		while (tf.hasNextLine()) {
			String data = tf.nextLine();
			
			for (char c : data.toCharArray()) {
				insert(c);}
			
			if (tf.hasNextLine() == true) {
				insert('\n');}
			}
		tf.close();
	}
	
	/**
	 * Tracking the current position of the inner cursor. Specifically, if the text editor contains a string of n characters, the cursor can be placed in n + 1 possible locations. The run time is O(1).
	 * @return The location the cursor locates at. If the text editor does not contain any characters, the return value will be zero.
	 */
	public int getCursorPosition() {
		if (numChars == 0) {
			return 0;} 
		else {
			return curPos;}
	}
		
	/**
	 * Tracking the number of characters the text editor contains inner cursor. The run time is O(1).
	 * @return The number of characters the text editor contains. If the text editor does not contain any characters, the return value will be zero.
	 */
	public int size() {
		if (numChars == 0) {
			return 0;} 
		else {
			return numChars;}
	}
	
	/**
	 * Manipulating the cursor and current node to move right until the cursor reaches its limit.
	 */
	public void moveRight() {
		if (curPos + 1 <= size()) {
			curPos ++;
			cur = cur.next;
			}
	}
	
	/**
	 * Manipulating the cursor and current node to move left until the cursor reaches its limit.
	 */
	public void moveLeft() {
		if (curPos - 1 >= 0) {
			curPos --;
			if (cur == null) {
				cur = tail;} 
			else {
				cur = cur.prev;}}
	}
	
	/**
	 * Moving the cursor and current node to the leftmost position.
	 */
	public void moveToHead() {
		curPos = 0;
		cur = head;
	}
	
	/**
	 * Moving the cursor and current node to the rightmost position.
	 */
	public void moveToTail() {
		curPos = numChars;
		cur = tail.next;
	}
	
	/**
	 * Inserting a new character to the list. The new inserted character shall always be followed by the cursor. The user should be able to insert 
	 * 		characters at any cursor index in the editor’s string.
	 * @param c A new character waiting to be inserted into the list.
	 */
	public void insert(char c) { 
		Node new_node = new Node(c);
		
		if (head == null) {	
			head = new_node;		
			tail = new_node;
			head.prev = null;
			tail.next = null; 
			cur = head.next;
			}
		else {
			if (curPos == 0 && head != null) {;
				new_node.next = head;
				head.prev = new_node;
				new_node.prev = null;
				head = new_node;
				} 
			else if (cur != null && cur.prev != null) {
				new_node.prev = cur.prev;
				(new_node.prev).next = new_node;
				cur.prev = new_node;
				new_node.next = cur;
				}
			else if (cur == null) {
				(tail).next = new_node;
				new_node.prev = tail;
				tail = new_node;
				}
			}
		
		curPos++;
		numChars++;
	}
	
	/**
	 * Using delete method, the user can remove the character that is after the cursor. 
	 */
	public void delete() {
		if (numChars > 0 && head != null && cur != null) {
			if (head != tail && cur == tail) {
				(cur.prev).next = cur.next;
				tail = cur.prev;
				cur = cur.next;
				numChars--;} 
			else if (head != tail && cur != tail) {
				if (cur == head) {
					(cur.next).prev = cur.prev;
					head = cur.next;
					cur = cur.next;
					numChars--;}
				else {
					(cur.prev).next = cur.next;
					(cur.next).prev = cur.prev;
					cur = cur.next;
					numChars--;}}
			else if (head == tail) {
				head = null;
				tail = null;
				numChars--;}
		}
	}
	
	/**
	 * Using backspace method, the user can remove the character that is before the cursor. 
	 */
	public void backspace() {
		if (numChars > 0 && head != null) {
			if (head != tail && cur == null) {
				(tail.prev).next = null;
				tail = tail.prev;}
			else if (head != tail && cur != null) {
				if (cur.prev == head) {
					cur.prev = cur.prev.prev;
					head = cur;} 
				else {
					(cur.prev.prev).next = cur;
					cur.prev = cur.prev.prev;}}
			else if (head == tail) {
				head = null;
				tail = null;}
			curPos--;
			numChars--;
		}
	}
	
    /**
     * returns the textual content inserted by user or reading a existential file
     *
     * @return String representation of the content the text editor possesses.
     */
	public String toString() {
		String output = "";
		
		if (head == null) {
			output = null;
		} else {
		    Node currNode = head;
		    output = output + currNode.data;
		    while(currNode.next != null){
		    	currNode = currNode.next;
		        output = output + currNode.data;}
		   }
		return output;
		}
	
	/**
	 * Removing all of the characters stored in the text editor. The run time is O(1).
	 */
	public void clear() {
		head = null;
		tail = null;
		cur = null;
		curPos = 0;
		numChars = 0;
	}
	
	/**
	 * Saving the contents of the text editor to a file at the provided save path. The run time is O(n).
	 * @param savepath A string represents the location to and the file name. If the user does not provide a full system 
	 * 		file path, the file will be in the same folder as the Eclipse project.
	 * @throws FileNotFoundException If the filepath is invalid, the system will throw out an error regarding file not found exception.
	 */
	public void save(String savepath) throws FileNotFoundException {
		PrintStream writeFile = new PrintStream(new File (savepath));
		writeFile.append(toString());
		writeFile.close();
	}
}
