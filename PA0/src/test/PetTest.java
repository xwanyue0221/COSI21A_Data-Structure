/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: A test program that could be used to test the functionalities of Pet class.
 * Known Bugs: N/A
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Pet;

class PetTest {
		
	Pet examplePet = new Pet("Oreo", "Dog", 3);
	
	/**
	 * This is an example test case. It tests all of the getters in the Pet class.  
	 */
	@Test
	void testPetInit() {
		assertEquals("Oreo", examplePet.getName());
		assertEquals("Dog", examplePet.getSpecies());
		assertEquals(3, examplePet.getAge());
	}
	
	@Test
	void testMakeSound()  {
		if (examplePet.getSpecies() == "Dog") {
			assertEquals("bark!", examplePet.makeSound(examplePet.getSpecies()));}
		else if (examplePet.getSpecies() == "Cat"){
			assertEquals("meow!", examplePet.makeSound(examplePet.getSpecies()));}
		else{
			assertEquals("squak!", examplePet.makeSound(examplePet.getSpecies()));}
		}
	
	@Test
	void testToString() {
		assertEquals(examplePet.getName() + "is a " + examplePet.getAge() + " years old " + examplePet.getSpecies() + ".", examplePet.toString());
	}

}
