/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: A test program that could be used to test the functionalities of House class.
 * Known Bugs: N/A
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.House;

class HouseTest {
	
	House exampleHouse = new House(2, 10000, false);
	
	/**
	 * This is an example test case. It tests all of the getters in the House class.  
	 */
	@Test
	void testHouseInit() {
		assertEquals(2, exampleHouse.getRooms());
		assertEquals(10000, exampleHouse.getPrice());
		assertEquals(false, exampleHouse.petsAllowed());
	}
	
	@Test
	void testToString() {
    	if (exampleHouse.petsAllowed() == true) {
    		assertEquals("A pet friendly house with " + exampleHouse.getRooms() + " rooms that worths " + exampleHouse.getPrice() + "$", exampleHouse.toString());}
    	else {
    		assertEquals("A non-pet house with " + exampleHouse.getRooms() + " rooms that worths " + exampleHouse.getPrice() + "$", exampleHouse.toString());}	
	}

}
