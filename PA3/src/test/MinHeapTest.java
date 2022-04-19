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
import main.GraphWrapper;
import main.GraphNode;
import main.Entry;
import main.FindMinPath;

public class MinHeapTest {

	@Test
	void test() {
		
		MinHeap hashTable = new MinHeap(10);
		assertEquals(-1, hashTable.getVisitedSize());
		assertEquals(-1, hashTable.TakenSize);
		assertEquals(10, hashTable.HeapSize);
		
		GraphNode g1 = new GraphNode("c4dbe07b-f0b5-4b8a-bf11-28780d609a91", false);
		g1.priority = 0;
		hashTable.insert(g1);
		
		assertEquals(0, hashTable.TakenSize);
		GraphNode g2 = new GraphNode("794a689b-5cba-4c24-8a08-8abee1d17eb9", false);
		GraphNode g3 = new GraphNode("1143c9aa-e3d5-4158-809a-c455d0984c0a", false);
		g2.priority = 10;
		g3.priority = 20;
		
		hashTable.insert(g2);
		hashTable.insert(g3);
		assertEquals("c4dbe07b-f0b5-4b8a-bf11-28780d609a91", hashTable.poll().getId());
		hashTable.heapifyDown(0);
		assertEquals("794a689b-5cba-4c24-8a08-8abee1d17eb9", hashTable.poll().getId());
	}

}
