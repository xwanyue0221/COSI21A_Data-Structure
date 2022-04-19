/**
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 10, 2021
 * PA0
 * Explanation of the program/class: The main class aims to access, transform, and store Family and House information. 
 * 								     Next, it will assign each family to a proper house once all the requirements are satisfied. The result of assignment could also be displayed.
 * 								     Finally, a result checking method is available for evaluation.
 * Known Bugs: N/A
 */

package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	/**
	 * Family[] f – an array of the families read from familyUnits.txt
	 * House[] h – an array of the houses read from housingUnits.txt
	 * boolean[][] assignments – a 2D matrix that represents the assignments of families to houses
	 */
	public static Family[] f;
	public static House[] h;
	public static boolean[][] assignments;

	public static void main(String[] args) throws FileNotFoundException {
		
		try {
			File houseText = new File("/Users/wanyuexiao/Documents/study/Brandeis/COSI 21A Data Structure/Assignment 1/XiaoWanyuePA0/housingUnits.txt"); 
			Scanner hs = new Scanner(houseText);
			int hTotal = Integer.parseInt(hs.nextLine());
			h = new House[hTotal];
			createHomes(hs);
			
			File familyText = new File("/Users/wanyuexiao/Documents/study/Brandeis/COSI 21A Data Structure/Assignment 1/XiaoWanyuePA0/familyUnits.txt"); 
			Scanner fs = new Scanner(familyText);
			int fTotal = Integer.parseInt(fs.nextLine());
			f = new Family[fTotal];
			createFamilies(fs);
			
			assignments = new boolean[fTotal][hTotal];
			assignFamiliesToHomes();
			displayAssignments();
			
			try {
				for(int each = 0; each < f.length; each++) {
					checkAssignment(each);
				}}
			catch (IllegalArgumentException ia) {
				System.out.println(ia); 
				}
			} 
		catch (FileNotFoundException e) {
			String e1 = "File Searching Error: the input file could not be found.";
			System.out.println(e1);
			}
		}
	
	/**
	 * @param s s is a Scanner object that extracts text information from a file
	 * @throws FileNotFoundException Throwing out an exception once the file could not be found.
	 * The createFamilies aims to transform the extracted textual family information to a proper typed object, 
	 *     which is a self-defined Family class (consisting of Person class and Pet class) in this case.  
	 */
	public static void createFamilies(Scanner s) throws FileNotFoundException {
		for (int i = 0; i < f.length; i++) {
			s.nextLine();
			int Fnum = Integer.parseInt(s.nextLine());
			int Pnum = Integer.parseInt(s.nextLine());
			f[i] = new Family(Fnum, Pnum);
			
			/**
			 * Transforming all the people related information to Person typed object
			 */
			for (int j = 0; j< Fnum; j++) {
				String line = s.nextLine();
			    String[] data = line.split("\\s+");
			    Person member = new Person(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
			    
			    if(f[i].addMember(member) == true && f[i].numberOfPeople() >= j) {
			    	f[i].getPeople()[j] = member;}
			    }
			
			/**
			 * Transforming all the pet related information to Pet typed object
			 */
			for (int k = 0; k< Pnum; k++) {
				String line = s.nextLine();
			    String[] data = line.split("\\s+");
			    Pet pet = new Pet(data[0], data[1], Integer.parseInt(data[2]));
			    
			    if(f[i].addPet(pet) == true && f[i].numberOfPets() >= k) {
			    	f[i].getPets()[k] = pet;}
			    }
			}
		}

	/**
	 * @param s s is a Scanner object which extracts text from a file
	 * @throws FileNotFoundException Throwing out an exception once the file could not be found.
	 * The createHomes aims to transform the extracted textual information to a proper typed object, 
	 *     which is a self-defined class House in this case.  
	 */
	public static void createHomes(Scanner s) throws FileNotFoundException {
		for (int i = 0; i < 8; i++) {
			s.nextLine();
			int num = Integer.parseInt(s.nextLine());
			int cost = Integer.parseInt(s.nextLine());
			boolean pets = Boolean.parseBoolean(s.nextLine());
			h[i] = new House(num, cost, pets);
			}
		}

	/**
	 * The assignFamiliesToHomes function can compare the requirements of families and conditions of homes and then assign the family 
	 *     to a qualified home if all requirements are satisfied.
	 */
	public static void assignFamiliesToHomes() {
		for (int i = 0; i < f.length; i++)
			for (int j = 0; j < h.length; j++) {
				assignments[i][j] = false;
			}
		
		/**
		 * This section enables the program to construct an integer array filled with a specific number serving as a placeholder.
		 * In this case, the intArray will be used to store the House number once the house has been assigned to another family, which could solve the potential issue of duplicated assignment.
		 */
		int MaxNumberFamily = f.length+1;
		int[] recordHouse = new int[f.length];
		int[] recordFamily = new int[f.length];
		Arrays.fill(recordHouse, MaxNumberFamily);
		Arrays.fill(recordFamily, MaxNumberFamily);
		boolean foundHouse;
		boolean foundFamily;
		
		for(int i = 0; i < f.length; i++) {
			int j = 0;
			
			while(j < h.length) {
				foundHouse = false;
				foundFamily = false;
				
				/**
				 * If the house has already been assigned to a family, the foundHouse would be True.
				 */
				for(int iter : recordHouse){
					if(iter == j){
						foundHouse = true;
						}
					}
				
				/**
				 * If the family has already been assigned to a house, the foundFamily would be True.
				 */
				for(int iter : recordFamily){
					if(iter == i){
						foundFamily = true;
						}
					}
				
				/**
				 * This section includes four logical statements determining whether the family has enough budget to rent the house with enough room and proper pet regulation. 
				 * Under the assumption that all those logical statements are valid, the family can purchase this house if this house has not been occupied.
				 */
				if (foundHouse == false && foundFamily == false) {
					
					boolean enoughRoom = false;
					if (f[i].numberOfPeople() <= h[j].getRooms()) {
						enoughRoom = true;}
					
					boolean enoughMoney = false;
					if (f[i].getBudget() >= h[j].getPrice()) {
						enoughMoney = true;}
					
					boolean petCheck = true;
					if (f[i].numberOfPets() > 0 && h[j].petsAllowed() == false) {
						petCheck = false;}	
					
					if (enoughRoom == true && enoughMoney == true && petCheck == true) {
						assignments[i][j] = true;
						recordHouse[i] = j;
						recordFamily[i] = i;}
					}
				j++;
				}
			}
		}
	
	/**
	 * displayAssignments() aims to display the assignment results of each family. 
	 */
	public static void displayAssignments() {
		System.out.println("Display The Result of Assignment: ");
		boolean houseMatch = false;
		
		for(int i = 0; i < f.length; i++){
			int j = 0;
			houseMatch = false;
			
			while(j < h.length) {
				if (assignments[i][j]) {
					houseMatch = true;
					System.out.println("\t Family " + i + " (" + f[i].toString()  + ") has been assigned to House " + j + " (" + h[j].toString() + ") .");}
				j++;}
			
			if (houseMatch == false && j == h.length) {
				System.out.println("\t Family " + i + " could not find a proper house.");}
			}
		System.out.println("\n");
		}
	
	/**
	 * This function aims to check various types of exceptions, including not having enough room numbers, budget overrun, duplicated assignments, 
	 *      and having pets when the house is not pet-friendly.
	 * 
	 * @param familyIndex A Integer representing the index of a family
	 */
	public static void checkAssignment(int familyIndex) {
		System.out.println("Start To Check The Assigment for Family " + familyIndex + " :");
		
		/**
		 * The input value familyIndex has to be 0 or a positive integer that is lower than the number of families. Otherwise, the program will throw out a exception.
		 */
	    if (familyIndex < 0 || familyIndex > f.length){
	        throw new IllegalArgumentException("The number is out of range. Please select 0 or a positive integer that is smaller than " + f.length + ".");
	    }
	    else {
		    int houseIndex = h.length+1;
		    int assignNum = 0;
		    
			/**
			 * If the family has been assigned to a house, find out the house number. 
			 * If the family has been assigned to exact one house, the houseIndex would be the house number. The assignNum would increase by 1.
			 * If the family has been assigned to more than one house, the houseIndex would be the nearest house number. The assignNum would be bigger than 1.
			 * If the family has not been assigned a house, the houseIndex would be the number of total houses plus one. The assignNum would be 0.
			 */
		    for(int hIdx = 0; hIdx < h.length; hIdx++) {
		    	if(assignments[familyIndex][hIdx] == true) {
		    		houseIndex = hIdx;
		    		assignNum++;}
		    	}
		    
		    /**
		     * If the assigNum is 0, the program will print that "No house has been assigned".
		     * If the assigNum is bigger than 1, the program will print that "Family assigned to more than one house".
		     * If the assigNum equals to 1, the program will continue".
		     */
		    if (assignNum == 0) {
		    	System.out.println("\t No house has been assigned to family " + familyIndex + ".");
		    }
		    else if (assignNum > 1) {
		    	System.out.println("\t Family assigned to more than one house.");
		    }
		    else {
			    /**
			     * If the family has more family members than the number of rooms available, the program will print each member's sound.
			     */
		    	if (f[familyIndex].numberOfPeople() > h[houseIndex].getRooms()) {
		    		for(int iter = 0; iter < f[familyIndex].numberOfPeople(); iter++) 
		    			System.out.println("\t" + f[familyIndex].getPeople()[iter].getName() + " said that \"" + f[familyIndex].getPeople()[iter].speak(f[familyIndex].getPeople()[iter].getAge()) + "\".");}
			    
			    /**
			     * If the family has any pets while the house is not pet friendly, the program will print each pet's sound.
			     */
		    	if (f[familyIndex].numberOfPets() > 0 && h[houseIndex].petsAllowed() == false) {
		    		for(int iter = 0; iter < f[familyIndex].numberOfPets(); iter++) {
		    			String type = f[familyIndex].getPets()[iter].getSpecies();
		    			System.out.println("\t" + f[familyIndex].getPets()[iter].getName() + " the " + type + " made sound \"" + f[familyIndex].getPets()[iter].makeSound(type) + "\".");}}
			    
			    /**
			     * If the house price is higher than budget, the program will print that "The house is over budget".
			     */
			    if (f[familyIndex].getBudget() < h[houseIndex].getPrice()) {
			    	System.out.println("\t The house is over budget.");}
			    	
		    }
	    }
	}
}

