package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.InputFileReader;
import main.WorkEntry;
import main.WorkTimeAnalysisTool;

/**
* This file holds a single JUnit test case that mimics the user interaction described on the example output in the PDF.
* @author Wanyue Xiao
* xwanyue@brandeis.edu
* <Nov 14, 2021>
* COSI 21A PA2
*/

class StudentTreeTest {
	
	/* Student test: this tests your tool based on the user inputs outlined in the PDF
	 */
	@Test
	void test() {
		WorkEntry[] entries = InputFileReader.getWorkEntriesFromCSV("pdf_table_csv.txt");
		WorkTimeAnalysisTool tool = new WorkTimeAnalysisTool(entries);
				
		assertEquals("", tool.parse("search \"grading\"").trim());
		// not an activity in the input data, nothing should be returned
		assertTrue(tool.parse("search \"grading\"").trim().isEmpty()); 
		assertEquals("grading exam\noffice hours\ngrading hw\nmet with tas", tool.parse("list r").trim());
		assertEquals("grading exam\ngrading hw\nmet with tas\noffice hours", tool.parse("list l").trim());
		
		// haven't called search, illegal state exception thrown
		assertThrows(IllegalStateException.class, () -> {  
			tool.parse("del 0");
		});
		
		// search for "grading hw" and return work entires
		assertEquals("[9/19/2019] grading hw (0.5 h): grading late hw\n[9/25/2019] grading hw (2.0 h): graded pa1\nTotal: 2.5 h", tool.parse("search \"grading hw\"").trim());
		tool.parse("del 0");
		assertEquals("[9/25/2019] grading hw (2.0 h): graded pa1\nTotal: 2.0 h", tool.parse("search \"grading hw\"").trim());
		// only 1 entries associated with activity (indexed 0, 1) 
		assertThrows(IndexOutOfBoundsException.class, () -> {
			tool.parse("del 2");
		});
		
		// Now search "grading" again
		assertEquals("", tool.parse("search \"grading\"").trim());
		// the deletion shall be rejected since the new search has been conducted and the search function return null
		assertThrows(IllegalStateException.class, () -> {
			tool.parse("del 0");
		});
		
		// Delete the final work entry from "grading exam" node. The node shall be removed since it does not contain any data inside.
		assertEquals("[9/20/2019] grading exam (3.0 h): graded exam #1\nTotal: 3.0 h", tool.parse("search \"grading exam\"").trim());
		tool.parse("del 0");
		assertEquals("grading hw\nmet with tas\noffice hours", tool.parse("list l").trim());
		assertEquals("grading hw\noffice hours\nmet with tas", tool.parse("list r").trim());
		
		// Delete the final work entry from "grading hw" node. The node shall be removed since it does not contain any data inside.
		// The grading hw currently is the root of the splay tree. To remove this node, the splay tree need a left rotation to enable "office hours" node becoming the new root.
		assertEquals("[9/25/2019] grading hw (2.0 h): graded pa1\nTotal: 2.0 h", tool.parse("search \"grading hw\"").trim());
		tool.parse("del 0");
		assertEquals("met with tas\noffice hours", tool.parse("list l").trim());
		assertEquals("office hours\nmet with tas", tool.parse("list r").trim());
		
		// Delete office hours from the tree
		assertEquals("", tool.parse("search \"grading hw\"").trim());
		assertEquals("met with tas\noffice hours", tool.parse("list r").trim());
		assertThrows(IllegalStateException.class, () -> {
			tool.parse("del 0");
		});
		assertEquals("[9/18/2019] office hours (1.5 h): helped students with pa1\n[10/1/2019] office hours (1.0 h):\nTotal: 2.5 h", tool.parse("search \"office hours\"").trim());
		tool.parse("del 0");
		assertEquals("[10/1/2019] office hours (1.0 h):\nTotal: 1.0 h", tool.parse("search \"office hours\"").trim());
		tool.parse("del 0");
		assertEquals("met with tas", tool.parse("list l").trim());
		assertEquals("met with tas", tool.parse("list r").trim());
		
		// After deleting every node, the program shall return empty string.
		assertEquals("[9/22/2019] met with tas (1.0 h):\nTotal: 1.0 h", tool.parse("search \"met with tas\"").trim());
		tool.parse("del 0");
		assertTrue(tool.parse("list r").trim().isEmpty()); 
		assertTrue(tool.parse("list l").trim().isEmpty()); 
	}
}