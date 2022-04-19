/**
* This file includes a splay tree data structure which enables user to store activity and its corresponding work entries as a tree node.
* This class includes several basic functions, including Node construction, searching, insertion, inorder level traversal, level-order traversal, tree structure visualization,
*      and tree rotation.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

package main;

public class WorkEntrySearchNode implements Comparable<WorkEntrySearchNode> {
	
	public WorkEntrySearchNode left; // KEEP THIS PUBLIC 
	public WorkEntrySearchNode right; // KEEP THIS PUBLIC 
	public WorkEntrySearchNode parent; // KEEP THIS PUBLIC
	public String key; 
	public LinkList WELinkList;
	
	/**
	 * The constructor of the WorkEntrySearchNode class
	 * @param activity a string which represents the key field
	 */
	public WorkEntrySearchNode(String activity) {
		this.key = activity;
		this.left = null;
		this.right = null;
		this.parent = null;
		this.WELinkList = null;
	}
	
	/**
	 * This function compares two strings lexicographically and return a integer to classify which string is larger 
	 * @param a WorkEntrySearchNode other
	 * @return a positive integer if the root's key is larger than that of the other object; or a negative integer if 
	 *         the root's key is smaller than that of the other object
	 * @runtime O(1)
	 */
	public int compareTo(WorkEntrySearchNode other) {
		return this.key.compareTo(other.key);
	}
	
	/**
	 * This function takes a WorkEntrySearchNode object and search for a node in the splay tree rooted at this node and 
	 *    return the node that matches or the last node encountered in the search. 
	 * @param WorkEntrySearchNode node which the program will used to search
	 * @return the node that matches or the last node encountered in the search
	 * @runtime O(logn)
	 */
	public WorkEntrySearchNode search(WorkEntrySearchNode node) {
		WorkEntrySearchNode curr = this;
		WorkEntrySearchNode y = null;
		boolean findNode = false;
		
		while (curr != null && findNode == false) {
			y = curr;
			if(curr.compareTo(node) == 0) {
				findNode = true;
				}
			else if(curr.compareTo(node) > 0) {
				curr = curr.left;
				}
			else if(curr.compareTo(node) < 0) {
				curr = curr.right;
				}
			}
		
		if (findNode == true) {
			curr.splay();
			return curr;
			}
		else {
			y.splay();
			return y;
			}
		}
	
	/**
	 * This function takes a workentrysearchnode and insert the node into the splay tree rooted at this node 
	 *     and return what gets inserted or a node already in the tree that matches this key
	 * @param a WorkEntrySearchNode node which shall be insert into the splay tree
	 * @return  the root node of the splay tree
	 * @runtime O(logn)
	 */
	public WorkEntrySearchNode insert(WorkEntrySearchNode node) {
		WorkEntrySearchNode y = null;
		WorkEntrySearchNode curr = this;
		boolean findDuplictae = false;
		
		while (curr != null && findDuplictae == false) {
			y = curr;
			if (curr.compareTo(node) < 0) {
				curr = curr.right;
				}
			else if (curr.compareTo(node) > 0) {
				curr = curr.left;
				}
			else {
				curr.WELinkList.insert(node.WELinkList.getValue());
				findDuplictae = true;
				node = curr;
				}
			}
		if (findDuplictae == false) {
			node.parent = y;
			if(y == null) {
				return node;
				}
			else if(y.compareTo(node) < 0) {
				y.right = node;
				}
			else if(y.compareTo(node) > 0) {
				y.left = node;
				}
		}

		node.splay();	
		return node;
	}
	
	/**
	 * @return This function return an inorder traversal of the tree rooted at this node with the nodes’ keys on separate lines.
	 * @runtime O(n)
	 */
	public String toString() {
		String output = "";
		if (this.left != null) {
			output += this.left.toString();
		}
		output += this.key + "\n";
		if (this.right != null) {
			output += this.right.toString();
		}
		return output;
	}
	
	/**
	 * @return A string representation return using parentheses to display the structure of the tree as described in the examples below.
	 * @runtime O(n)
	 */
	public String getStructure() {
		String output = "(";
		if (this.left != null) {
			output += this.left.getStructure();
		}
		output += this.key;
		if (this.right != null) {
			output += this.right.getStructure();
		}
		return output + ")";
	}
	
	/**
	 * This function adds a WorkEntry to this node. Entries should be stored in the same order in which they were added from the input CSV file
	 * @param e a WorkEntry object e
	 * @runtime O(1)
	 */
	public void add(WorkEntry e) {
		if (this.WELinkList == null) {
			LinkList newLL = new LinkList();
			newLL.insert(e);
			this.WELinkList = newLL;
		}else {
			this.WELinkList.insert(e);
		}
	}
	
	/**
	 * This function find the node which has the maximum value
	 * @return a WorkEntrySearchNode
	 * @runtime O(logn)
	 */
	public WorkEntrySearchNode maximum() {
		WorkEntrySearchNode curr;
		curr = this;
		while(curr.right != null)
			curr = curr.right;
		return curr;
		}
	
	/**
	 * This function checks if the WorkEnrtySearchNode contains any WorkEntry objects
	 * @return a boolean result; true if the node contains any objects or false if it does not
	 * @runtime O(1)
	 */
	public boolean hasWorkEntry() {
		if (this.WELinkList.getSzie() > 0) {
			return true;}
		else {
			return false;}
		}
	
	/**
	 * This function should remove the ith WorkEntry stored in this node. If the removal results in the node containing no more entries, 
	 *               the node should be removed from the tree
	 * @param i a integer which represents the position of WorkEntry that shall be removed
	 * @return the root of the splay tree
	 * @runtime O(1)
	 */
	public WorkEntrySearchNode del(int i) {
		boolean hasWorkEntry;
		this.splay();
		hasWorkEntry = this.hasWorkEntry();
		
		if (hasWorkEntry == true) {
			if (i+1 > this.WELinkList.getSzie()) {
				throw new IndexOutOfBoundsException();}
			else {
				this.WELinkList.delete(i);}
			}
		
		hasWorkEntry = this.hasWorkEntry();
		
		if (hasWorkEntry == false) {
			WorkEntrySearchNode leftSubtree = this.left;
			if(leftSubtree != null) {
			    leftSubtree.parent = null;}
			
			WorkEntrySearchNode rightSubtree = this.right;
			if(rightSubtree != null) {
				rightSubtree.parent = null;}
			
			if(leftSubtree != null) {
				WorkEntrySearchNode m = leftSubtree.maximum();
				m.splay();
				m.right = rightSubtree;
				if (rightSubtree != null) {
					rightSubtree.parent = leftSubtree;
				}
				return m;}
			else {
				return rightSubtree;
				}
			}
		
		return this;		
	}
	
	/**
	 * This function should return the String representations of the WorkEntry objects 
	 *          associated with this node on separate lines followed by the total time spent
	 *          on these entries rounded to one decimal place
	 * @return a string representation
	 * @runtime O(1)
	 */
	public String getEntryData() {
		String ouput = "";
		LinkList content = this.WELinkList;
		
		if (content.isEmpty() == false) {
			ouput = content.printLinkedList();
		}
		return ouput;
	}
	
	/**
	 * This function returns a level order traversal of the tree where each node’s key is displayed on a separate 
	 *             line. Do not put nodes on the same tree level on the same String line
	 * @return a string representation of levelorder traversal
	 * @runtime O(n)
	 */
	public String getByRecent() {
		String output = "";
		Queue <WorkEntrySearchNode> q = new Queue<WorkEntrySearchNode>();
		q.enqueue(this);
		
		while (q.size() != 0) {
			WorkEntrySearchNode node = q.dequeue();
			output += node.key + "\n";
			if (node.left != null) {
				q.enqueue(node.left);
			}
			if (node.right != null) {
				q.enqueue(node.right);
			}
		}
		return output.substring(0, output.length() - 1);
	}
	
	/**
	 * This function will move the node to the root of the slay tree after several rounds of rotation
	 * @runtime O(logn)
	 */
	public void splay() {
		while(this.parent != null) { 
			if(this.parent.parent == null) {
				if(this == this.parent.left) { 
					this.parent.rightRotate();}
				else if (this.parent.right == this) {
					this.parent.leftRotate();}
				}
			else {
				WorkEntrySearchNode p = this.parent;
				WorkEntrySearchNode g = p.parent;
				
				if(this.parent.left == this && p.parent.left == p) { 
					g.rightRotate();
					p.rightRotate();}
				else if(this.parent.right == this && p.parent.right == p) { 
					g.leftRotate();
					p.leftRotate();
					}
				else if(this.parent.right == this && p.parent.left == p) {
					p.leftRotate();
					g.rightRotate();
					}
				else if(this.parent.left == this && p.parent.right == p) {
					p.rightRotate();
					g.leftRotate();}
				}
			}
		}
	
	/**
	 * This function will do a left rotation
	 * @runtime O(1)
	 */
	public void leftRotate() {
		WorkEntrySearchNode y = this.right;
		this.right = y.left;
		if(y.left != null) {
			y.left.parent = this;}
		y.parent = this.parent;
		if(this.parent == null) { //x is root
		  }
		else if(this == this.parent.left) {
			this.parent.left = y;}
		else {
			this.parent.right = y;}
		y.left = this;
		this.parent = y;}
	
	/**
	 * This function will do a right rotation
	 * @runtime O(1)
	 */
	public void rightRotate() {
		WorkEntrySearchNode y = this.left;
		this.left = y.right;
		
		if(y.right != null) {
			y.right.parent = this;}
		y.parent = this.parent;
		if(this.parent == null) { //x is root
		  }
		else if(this == this.parent.right) { //x is left child
			this.parent.right = y;}
		else { //x is right child
			this.parent.left = y;}
		y.right = this;
		this.parent = y;}
}
