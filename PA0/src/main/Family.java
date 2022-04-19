/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: The family class provides a constructor and methods to store and retrieve person and pet typed object.
 * Known Bugs: N/A
 */

package main;

public class Family {
	
	/**
	 * Person[] familyMembers – an array of the persons read from familyUnits.txt
	 * Pet[] familyPets – an array of the pets read from housingUnits.txt
	 */
	private Person[] familyMembers;
	private Pet[] familyPets;
	
	/**
	 * @param humans A integer number that represents the number of members for each family.
	 * @param pets A integer number that represents the number of pet for each family.
	 * This constructor will initialize two arrays to store person and pet typed object respectively.
	 */
	public Family(int humans, int pets) {
		//TODO: Implement the constructor
		familyMembers = new Person[humans];
		familyPets = new Pet[pets];
	}
	
	/**
	 * This method returns the content of familyMembers
	 * 
	 * @return A Person representing the familyMembers
	 */
	public Person[] getPeople() {
		return familyMembers;
	}
	
	/**
	 * returns the content of familyPets
	 * 
	 * @return A Pet representing the familyPets
	 */
	public Pet[] getPets() {
		return familyPets;
	}
	
	/**
	 * returns the total number of incomes from a family.
	 * 
	 * @return A Integer representing the total budget
	 */
	public int getBudget() {
		int sum = 0;
		for (int i = 0; i < familyMembers.length; i++) {
			int salary = familyMembers[i].getSalary();
			sum = sum + salary;
		}
		return sum;
	}

	/**
	 * returns the total number of members from a family.
	 * 
	 * @return A Integer representing the total number of members from a family.
	 */
	public int numberOfPeople() {
		return familyMembers.length;
	}
	
	/**
	 * returns the total number of pets from a family
	 * 
	 * @return A Integer representing the total number of pets from a family
	 */
	public int numberOfPets() {
		return familyPets.length;
	}
	
	/**
	 * @param p A person typed object
	 * returns boolean result of adding a new member to the family array. The result would be true if the action succeeds. Otherwise, it will return false.
	 * 
	 * @return A boolean representing the result of adding member
	 */
	public boolean addMember(Person p) {
		boolean reachMaxCapacity = true;
		
		for(int i = 0; i < familyMembers.length; i++){
			if(familyMembers[i] == null){
				reachMaxCapacity = false;
		    }
		}
		
		if(p!=null && reachMaxCapacity == false) {
			return true;
			}
		else {
			return false;
			}
		}
	
	/**
	 * @param p A pet typed object
	 * returns boolean result of adding a new pet to the family array. The result would be true if the action succeeds. Otherwise, it will return false.
	 * 
	 * @return A boolean representing the result of adding pet
	 */
	public boolean addPet(Pet p) {
		boolean reachMaxCapacity = true;
		
		for(int i = 0; i < familyPets.length; i++){
			if(familyPets[i] == null){
				reachMaxCapacity = false;
		    }
		}
		
		if(p!=null && reachMaxCapacity == false) {
			return true;
			}
		else {
			return false;
			}
		}
	
    /**
     * Return the detailed information regarding the family, including number of members, number of pet, and total budget.
     *
     * @return a String representation of this Pet
     */
    @Override
    public String toString() {
    	String s = numberOfPeople() + " persons and " + numberOfPets() + " pets with " + getBudget() + "$ income";
		return s;
    }
}

