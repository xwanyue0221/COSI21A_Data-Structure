/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: The person class provides a constructor and methods to store and retrieve people's information.
 * Known Bugs: N/A
 */

package main;

public class Person {
    private String name;
    private int Age;
	private int Salary;

    /**
     * Creates a Person object with the given name, age, and salary
     *
     * @param name   the name of the Person to be created
     * @param age    the age of the Person to be created
     * @param salary the salary of the Person to be created
     */
    public Person(String name, int Age, int Salary) {
        this.name = name;
        this.Age = Age;
        this.Salary = Salary;
    }
    
    /**
     * returns the name of this Person
     *
     * @return a String representing the name of this Person
     */
    public String getName() {
    	return name;
    }

    /**
     * returns the age of this Person
     *
     * @return an integer representing the age of this Person
     */
    public int getAge(){
        return Age;
    }

    /**
     * returns the salary of this Person
     *
     * @return an integer representing the salary of this Person
     */
    public int getSalary() {
        return Salary;
    }
    
    /**
     * returns the string said by this Person when they speak
     *
     * @return String representation of what this Person says
     */
    public String speak(int age) {
       	String s = "";
        if (age >= 0 && age <= 18){
            s = "I want a bigger house!";}
        else {
        	s = "This house does not have enough rooms to accommodate my family. I would like my family to be assigned to a house with more rooms.";}
		return s;
    }
    
    /**
     * returns a String representation of this Person, including name, age, and salary.
     *
     * @return a String representation of this Person
     */
    @Override
    public String toString(){
    	String s = name + "is a " + getAge() + " years old person whose salary is " + getSalary() + ".";
        return s;
    }
}


