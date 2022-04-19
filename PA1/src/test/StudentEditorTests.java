/**
*  A test program that could be used to test the functionalities of Editor class.
* 		
* Known Bugs: None
*
* @author Xiao Wanyue
* xwanyue@brandeis.edu
* Oct 7, 2021
* COSI 21A PA1
*/
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


import org.junit.Test;
import org.junit.Before;

import main.Editor;
import main.Node;

public class StudentEditorTests {
	
	/** Editor used for testing */
	Editor e;

	/**
	 * Before each test, the Editor is re-initialized
	 */
	@Before
	public void reset() {
		e = new Editor();
	}

	/**
	 * Building on example movement no. 1 on page 4 on the assignment PDF, tests if
	 * the cursor can be properly moved from before 'T' to before 'e' to before 's' 
	 * to before 'i' to before '!'.
	 */
	@Test
	public void testMoveRightBasic() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// point cur at head
		e.cur = e.head;
		e.curPos = 0;

		// move cur right several times
		e.moveRight();
		assertEquals(1, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		e.moveRight();
		assertEquals(2, e.getCursorPosition());
		assertEquals('s', e.cur.data);
		e.moveRight();
		e.moveRight();
		assertEquals(4, e.getCursorPosition());
		assertEquals('i', e.cur.data);
		e.moveRight();
		e.moveRight();
		e.moveRight();
		assertEquals(7, e.getCursorPosition());
		assertEquals('!', e.cur.data);
	}

	/**
	 * Building on example movement no. 2 on page 4 on the assignment PDF, tests if
	 * the cursor can be properly moved from before '!' to before 'g' to before 't' to before 'T'.
	 */
	@Test
	public void testMoveLeftBasic() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// point cur at tail
		e.cur = e.tail;
		e.curPos = 7;
		assertEquals('!', e.cur.data);

		// move cur left several times
		e.moveLeft();
		assertEquals(6, e.getCursorPosition());
		assertEquals('g', e.cur.data);
		e.moveLeft();
		e.moveLeft();
		e.moveLeft();
		assertEquals(3, e.getCursorPosition());
		assertEquals('t', e.cur.data);
		e.moveLeft();
		e.moveLeft();
		e.moveLeft();
		assertEquals(0, e.getCursorPosition());
		assertEquals('T', e.cur.data);
	}

	/**
	 * Using example movement no. 3 on page 4 on the assignment PDF, tests if the
	 * cursor can be properly moved from before 'i' to before 'T'.
	 */
	@Test
	public void testMoveToHead() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// put cur at 'u'
		e.cur = e.head.next.next.next.next;
		e.curPos = 4;

		// move to head
		e.moveToHead();

		// cur should now be at 'T'
		assertEquals(0, e.getCursorPosition());
		assertEquals('T', e.cur.data);
	}

	/**
	 * Using example movement no. 4 on page 4 on the assignment PDF, tests if the
	 * cursor can be properly moved from before 'i' to after '!'.
	 */
	@Test
	public void testMoveToTail() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// put cur at 'u'
		e.cur = e.head.next.next.next.next;
		e.curPos = 2;

		// move to tail
		e.moveToTail();

		// cur should now be after '!'
		assertEquals(8, e.getCursorPosition());
		assertNull(e.cur);
	}

	/**
	 * Builds on example insertions discussed on p. 5 and 6 of the assignment PDF,
	 * tests if the Editor properly inserts '$', '@', '%' after 'b'
	 */
	@Test
	public void testMultipleInsertMiddle() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// point cur at 'T'
		e.cur = e.head;
		e.curPos = 0;

		// insert $, @, %, and space to get "$T@est%ing ! "
		e.insert('$');
		e.cur = e.head.next.next;
		e.curPos = 2;
		e.insert('@');
		
		e.cur = e.head.next.next.next.next.next.next;
		e.curPos = 6;
		e.insert('%');
		
		e.cur = e.head.next.next.next.next.next.next.next.next.next.next;
		e.curPos = 10;
		e.insert(' ');
		
		e.cur = e.head.next.next.next.next.next.next.next.next.next.next.next.next;
		e.curPos = 12;
		e.insert(' ');
		
		assertEquals(13, e.getCursorPosition());
		assertNull(e.cur);
		assertEquals("$T@est%ing ! ", e.toString());
	}

	/**
	 * Builds on example deletions discussed on p. 6 of the assignment PDF, tests if
	 * the Editor properly deletes 't', then 'T', then '!'.
	 */
	@Test
	public void testDeleteMultipleMiddle() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// point cur at 'T'
		e.cur = e.head;
		e.curPos = 0;
		e.delete();

		assertEquals('e', e.head.data);
		assertEquals('!', e.tail.data);
		assertEquals(0, e.getCursorPosition());
		assertEquals(7, e.size());
		assertEquals('e', e.cur.data);
		
		e.cur = e.head.next.next;
		e.curPos = 2;
		e.delete();
		
		assertEquals('e', e.head.data);
		assertEquals('!', e.tail.data);
		assertEquals(2, e.getCursorPosition());
		assertEquals(6, e.size());
		assertEquals('i', e.cur.data);

		e.cur = e.head.next.next.next.next.next;
		e.curPos = 5;
		e.delete();
		
		assertEquals('e', e.head.data);
		assertEquals('g', e.tail.data);
		assertEquals(5, e.getCursorPosition());
		assertEquals(5, e.size());
		assertNull(e.cur);
	}

	/**
	 * Builds on example backspaces discussed on p. 6 - 7 of the assignment PDF,
	 * tests if the Editor properly backspaces '!', then 't', then 'T'.
	 */
	@Test
	public void testBackspaceMultipleMiddle() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// point cur after '!'
		e.cur = e.tail.next;
		e.curPos = 8;
		e.backspace();

		assertEquals('T', e.head.data);
		assertEquals('g', e.tail.data);
		assertEquals(7, e.getCursorPosition());
		assertEquals(7, e.size());
		assertNull(e.cur);
		
		e.cur = e.tail.prev.prev;
		e.curPos = 4;
		e.backspace();

		assertEquals('T', e.head.data);
		assertEquals('g', e.tail.data);
		assertEquals(3, e.getCursorPosition());
		assertEquals(6, e.size());
		assertEquals('i', e.cur.data);

		e.cur = e.head.next;
		e.curPos = 1;
		e.backspace();

		assertEquals('e', e.head.data);
		assertEquals('g', e.tail.data);
		assertEquals(0, e.getCursorPosition());
		assertEquals(5, e.size());
		assertEquals('e', e.cur.data);
	}

	/**
	 * Tests the toString() method described on page 7.
	 */
	@Test
	public void testToString() {
		// create editor storing "Testing!"
		loadEditorTesting(e);

		// check toString
		assertEquals("Testing!", e.toString());
	}

	/**
	 * Helper method that loads the characters 'T', 'e', 's', 't', 'i', 'n', 'g', and '!'
	 * into an Editor object without using the insert() method. The cursor and cursor position are
	 * left at their default values (null, 0). numChars is updated to be 8.
	 * 
	 * @postcondition It is necessary that curPos, cur are set following the call of
	 *                this method. Otherwise, the Editor will be in an invalid state.
	 * 
	 * @param editor an Editor to load with "Testing!".
	 */
	public static void loadEditorTesting(Editor editor) {
		Node T = new Node('T');
		Node e = new Node('e');
		Node s = new Node('s');
		Node t = new Node('t');
		Node i = new Node('i');
		Node n = new Node('n');
		Node g = new Node('g');
		Node exl = new Node('!');

		T.next = e;
		e.prev = T;
		e.next = s;
		s.prev = e;
		s.next = t;
		t.prev = s;
		t.next = i;
		i.prev = t;
		i.next = n;
		n.prev = i;
		n.next = g;
		g.prev = n;
		g.next = exl;
		exl.prev = g;

		editor.head = T;
		editor.tail = exl;

		editor.numChars = 8;
	}

}
