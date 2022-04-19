/**
* The FindMinPath implements a Dijkstra algorithm enables the user to find the shortest path from the home node to target node.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class FindMinPath {
	public boolean findGoal;
	public PriorityQueue PQ;
	public GraphNode finalGoal;
	public GraphNode home;
	
	public FindMinPath(int initialSize){
		this.findGoal = false;
		this.finalGoal = null;
		this.PQ = new PriorityQueue(initialSize);
		}
	
	/***
	 * The GetShortestPath takes a home node as a input parameter. By starting at the home node, it traversal the adjacent node one by one untill
	 *     it finds the shortest path from home to the target node.
	 * @param home The starting node 
	 * Runtime: o(V+E)
	 */
	public void GetShortestPath(GraphNode home) {
		this.home = home;
		PQ.insert(this.home);
		
		while (PQ.isEmpty() == false) {
			GraphNode curr = PQ.pullHighestPriorityElement();
			
			if (curr.isGoalNode() == true) {
				this.findGoal = true;
				this.finalGoal = curr;
				relax(curr);
				}
			else 
				relax(curr);
			}
		}
	
	/***
	 * The relax function takes a node named curr as a input parameter and examines its four direction one by one. If it has a adjacent node in one of the
	 *     direction, then the distance between the starting node (home) to the recently discovered node. If the distance is shorter than the previous one,
	 *     the information shall be updated.
	 * @param curr a node inside the graph
	 * Runtime: o(nlogn)
	 */
	public void relax(GraphNode curr) {
		if (curr.hasNorth() == true) {
			GraphNode North = curr.getNorth();
			int x = curr.priority + curr.getNorthWeight();
			if(PQ.getHashMap().hasKey(North) == false && PQ.isVisited(North) == false) {
				North.priority = x;
				North.previousNode = curr;
				North.previousDirection = "North";
				PQ.insert(North);
				}
			else if(PQ.getHashMap().getValue(North) >= 0){
				if (x < North.priority){
					North.priority = x;
					North.previousNode = curr;
					North.previousDirection = "North";
					PQ.rebalance(North);}				
				}
			}
		
		if (curr.hasSouth() == true) {
			GraphNode South = curr.getSouth();
			int x = curr.priority + curr.getSouthWeight();
			if(PQ.getHashMap().hasKey(South) == false && PQ.isVisited(South) == false) {
				South.priority = x;
				South.previousNode = curr;
				South.previousDirection = "South";
				PQ.insert(South);
				}
			else if(PQ.getHashMap().getValue(South) >= 0){
				if (x < South.priority){
					South.priority = x;
					South.previousNode = curr;
					South.previousDirection = "South";
					PQ.rebalance(South);}				
				}
			}
		
		if (curr.hasEast() == true) {
			GraphNode East = curr.getEast();
			int x = curr.priority + curr.getEastWeight();
			if(PQ.getHashMap().hasKey(East) == false && PQ.isVisited(East) == false) {
				East.priority = x;
				East.previousNode = curr;
				East.previousDirection = "East";
				PQ.insert(East);
				}
			else if(PQ.getHashMap().getValue(East) >= 0){
				if (x < East.priority){
					East.priority = x;
					East.previousNode = curr;
					East.previousDirection = "East";
					PQ.rebalance(East);
					}				
				}
			}
		
		if (curr.hasWest() == true) {
			int x = curr.priority + curr.getWestWeight();
			GraphNode West = curr.getWest();
			if(PQ.getHashMap().hasKey(West) == false && PQ.isVisited(West) == false) {
				West.priority = x;
				West.previousNode = curr;
				West.previousDirection = "West";
				PQ.insert(West);
				}
			else if(PQ.getHashMap().getValue(West) >= 0){
				if (x < West.priority){
					West.priority = x;
					West.previousNode = curr;
					West.previousDirection = "West";
					PQ.rebalance(West);
					}				
				}
			}
	}
	
	/***
	 * This findPath function allows user to print out and save the final path.
	 * @throws FileNotFoundException
	 * Runtime: o(n)
	 * 
	 */
	public void findPath() throws FileNotFoundException {
		if (this.findGoal == false) 
			System.out.println("Could not find a path");
		else if (this.home.getId().equals(this.finalGoal.getId())) {
			System.out.println("The starting point (home) is the goal.");
		}
		else {
			PrintStream writeFile = new PrintStream(new File ("Result.txt"));
			System.out.println("The shortest path is");
			GraphNode track = this.finalGoal;
			String tracePath = "";
			
			while (track.previousDirection != null && track != null) {
				tracePath = track.previousDirection + "\n" + tracePath;
				track = track.previousNode;
				}
			System.out.println(tracePath);
			writeFile.print(tracePath);
			}
		}
	
	/**
	 * return the final goal if possible
	 * @return final goal
	 * o(1)
	 */
	public GraphNode getFinalGoal() {
		return this.finalGoal;
	}
	
	/**
	 * The main function
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		int initial_size = 100000;
		GraphWrapper GW = new GraphWrapper(true);
		FindMinPath GS = new FindMinPath(initial_size);
		GraphNode home = GW.getHome();
		home.priority = 0;
		GS.GetShortestPath(home);
		GS.findPath();
	}
	}
