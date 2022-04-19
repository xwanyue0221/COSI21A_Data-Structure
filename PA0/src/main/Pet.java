/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: The pet class provides a constructor and methods to store and retrieve pet information.
 * Known Bugs: N/A
 */

package main;

public class Pet {
	
	private String name;
	private String species;
	private int age;
	
	/**
	 * Creates a Pet object with the given name, species, and age
	 * 
	 * @param name the name of the Pet to be created
	 * @param species the species of the Pet to be created
	 * @param age the age of the Pet to be created
	 */
	public Pet(String name, String species, int age) {
		//TODO: Implement the constructor
		this.name = name;
		this.species = species;
		this.age = age;
	}
	
    /**
     * returns the name of this Pet
     *
     * @return a String representing the name of this Pet
     */
	public String getName() {
		return name;
	}
	
    /**
     * returns the species of this Pet
     *
     * @return a String representing the species of this Pet
     */
	public String getSpecies() {
		return species;
	}
	
    /**
     * returns the age of this Pet
     *
     * @return a Integer representing the age of this Pet
     */
	public int getAge() {
		return age;
	}
	
    /**
     * returns the string said by this Pet when they make sound
     *
     * @return String representation of what this Pet says
     */
	public String makeSound(String species) {
		String s = "squak!";
		
		if (species.equals("Cat")) s = "meow!";
		if (species.equals("Dog")) s = "bark!";
		
		return s;
	}
	
    /**
     * returns a String representation of this Pet, including name, species, and age.
     *
     * @return a String representation of this Pet
     */
	@Override
	public String toString() {
		String s = name + "is a " + getAge() + " years old " + getSpecies() + ".";
        return s;
	}
}