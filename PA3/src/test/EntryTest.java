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

public class EntryTest {

	@Test
	void test() {
		int initial_size = 100000;
		GraphWrapper gw = new GraphWrapper(true);
		FindMinPath GS = new FindMinPath(initial_size);
		GraphNode home = gw.getHome();
		home.priority = 0;
		
		Entry homeEntry = new Entry(home, 0);
		assertEquals("0fd76b04-1df7-4838-b854-e270f42a5dd6", homeEntry.getKey().getId());
		assertEquals(0, homeEntry.getValue());
	}

}
