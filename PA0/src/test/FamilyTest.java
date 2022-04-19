/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: A test program that could be used to test the functionalities of Family class.
 * Known Bugs: N/A
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Family;
import main.Person;
import main.Pet;

class FamilyTest {
	
	Family exampleFamily = new Family(1, 1);
//	Person[] familyMembers = new Person[1];
//	Pet[] familyPets = new Pet[1];
	
	Pet examplePet = new Pet("Oreo", "Dog", 3);
	Pet examplePet2 = new Pet("Jerry", "Cat", 1);
	Person examplePerson = new Person("Example Person", 50, 50000);
	Person examplePerson2 = new Person("Lisa", 5, 0);
	
	
	@Test
	void testFamilyInit() {
		exampleFamily.getPeople()[0] = examplePerson;
		exampleFamily.getPets()[0] = examplePet;
		
		assertEquals(examplePerson, exampleFamily.getPeople()[0]);
		assertEquals(examplePet, exampleFamily.getPets()[0]);
	}
	
	@Test
	void testGetPeople() {
		exampleFamily.getPeople()[0] = examplePerson;
		assertEquals(examplePerson, exampleFamily.getPeople()[0]);
	}
	
	@Test
	void testGetPets() {
		exampleFamily.getPets()[0] = examplePet;
		assertEquals(examplePet, exampleFamily.getPets()[0]);
	}
	
	@Test
	void testAddPeople() {
		assertEquals(true, exampleFamily.addMember(examplePerson2));
		exampleFamily.getPeople()[0] = examplePerson;
		assertEquals(false, exampleFamily.addMember(examplePerson2));
	}
	
	@Test
	void testAddPets() {
		assertEquals(true, exampleFamily.addPet(examplePet2));
		exampleFamily.getPets()[0] = examplePet;
		assertEquals(false, exampleFamily.addPet(examplePet2));
	}

	
	@Test
	void testGetBudget() {
		exampleFamily.getPeople()[0] = examplePerson;
		exampleFamily.getPets()[0] = examplePet;
		assertEquals(examplePerson.getSalary(), exampleFamily.getBudget());
	}
	
	@Test
	void testNumberOfPeople() {
		assertEquals(1, exampleFamily.numberOfPeople());
	}
	
	@Test
	void testNumberOfPets() {
		assertEquals(1, exampleFamily.numberOfPets());
	}
	
	@Test
	void testToString() {
		exampleFamily.getPeople()[0] = examplePerson;
		exampleFamily.getPets()[0] = examplePet;
		assertEquals(exampleFamily.numberOfPeople() + " persons and " + exampleFamily.numberOfPets() + " pets with " + exampleFamily.getBudget() + "$ income", exampleFamily.toString());
	}

}
