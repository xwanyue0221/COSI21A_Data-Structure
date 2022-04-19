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
import main.GraphWrapper;
import main.GraphNode;
import main.Entry;
import main.FindMinPath;

public class StudentJunitTest {

	@Test
	void test() throws FileNotFoundException {
		int initial_size = 100000;
		GraphWrapper gw = new GraphWrapper(true);
		FindMinPath GS = new FindMinPath(initial_size);
		GraphNode home = gw.getHome();
		home.priority = 0;
		GS.GetShortestPath(home);
		GS.findPath();
		
		GraphNode target = GS.getFinalGoal();
		assertEquals("East", target.previousDirection);
		assertEquals("South", target.previousNode.previousDirection);
		
	}

}
