/**
* This file holds a single JUnit test case that mimics the user interaction described on the example output in the PDF.
* Known Bugs: None
*
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Dec 13, 2021>
* COSI 21A PA3
*/

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import main.HashMap;
import main.MinHeap;
import main.PriorityQueue;
import main.GraphWrapper;
import main.GraphNode;
import main.Entry;
import main.FindMinPath;

public class MinQueueTest {

	@Test
	void test() {
		
		PriorityQueue Q = new PriorityQueue(5);
		GraphNode g1 = new GraphNode("c4dbe07b-f0b5-4b8a-bf11-28780d609a91", false);
		assertEquals(true, Q.isEmpty());
		Q.insert(g1);
		assertEquals(false, Q.isEmpty());
		
		GraphNode g2 = new GraphNode("794a689b-5cba-4c24-8a08-8abee1d17eb9", false);
		Q.insert(g2);
		assertEquals(g1, Q.pullHighestPriorityElement());
		assertEquals(g2, Q.pullHighestPriorityElement());
	}

}
