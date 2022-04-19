/**
* This file includes a class that transforms input to nodes stored in a splay tree and executes corresponding functions based on the user's input.
* This class includes several essential functions, including listing currently available nodes, searching nodes in the splay tree, and deleting a work entry (or a node if it does not contain any
*      work entry) associated with a specific node.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

package main;

public class WorkTimeAnalysisTool {
	public boolean recentSearch;
	public WorkEntrySearchNode root;
	
	/**
	 * The constructor of WorkTimeAnalysisTool class, which constructs a splay tree based on the input entries
	 * @param entries a array of WorkEntries objects
	 * @runtime O(n)
	 */
	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		if (entries.length >0) {
			recentSearch = false;
			root = new WorkEntrySearchNode(entries[0].getActivity());
			root.add(entries[0]);
			for (int i = 1; i < entries.length; i++) {
				WorkEntrySearchNode newNode = new WorkEntrySearchNode(entries[i].getActivity());
				newNode.add(entries[i]);
				root = root.insert(newNode);
				}
			}
	}
	
	/**
	 * This function takes user's input as a parameter, and implement several functions (such as searching, deletion, and listing) 
	 *      based on the input.
	 * @param cmd a string representation
	 * @return a string which shows the result of operations
	 * @runtime O(n)
	 */
	public String parse(String cmd) {
		cmd = cmd.replaceAll("^[ \t]+|[ \t]+$", "");
		String[] splited = cmd.split(" ", 2);
		String out = "";
		String argument1 = splited[0];
		String argument2 = splited[1];
		
		// if the root is null, return empty string (or null)
		if (root != null) {
			// if the argument starts with list, returns either a string representation of a inorder level traversal or level order traversal
			if (argument1.equals("list")) {
				if (argument2.equals("r")) {
					out = root.getByRecent();
					}
				else {
					String output = root.toString();
					out = output.substring(0, output.length()-1);
					}
				}
			// if the input starts with search, return the WorkEntry objects associated with the node or return empty string if the node does not existed
			else if (argument1.equals("search")) {
				argument2 = argument2.substring(1, argument2.length()-1);
				WorkEntrySearchNode search = new WorkEntrySearchNode(argument2);
				root = root.search(search);		
				
				if (root.key.equals(argument2)) {
					recentSearch = true;
					out = root.getEntryData();}
				else {
					recentSearch = false;
					}
				}
			// if the input starts with del, return empty string (or null)
			else if (argument1.equals("del")) {
				if (recentSearch == false) {
					throw new IllegalStateException();
					}
				else {
					String currRoot = root.key;
					root = root.del(Integer.parseInt(argument2));
					
					if (root != null) {
						if (!root.key.equals(currRoot)) {
							recentSearch = false;}
						}
					out = "";
					}
				}
			}
		else {
			out = "";
			}
		return out;
		}
	}
