/**
 * 
 * Wanyue Xiao
 * xwanyue@brandeis.edu
 * Sep 9 2021
 * PA0
 * Explanation: The purpose of this class is to get info of a house
 * Known Bugs: explain any known bugs in the code or N/A if none.
 */

package main;

public class House {
	private int rooms;
	private int price;
	private boolean petsAllowed;
	
	/**
	 * Creates a House object with the given rooms number, price, and regulation about having a pet.
	 * 
	 * @param rooms the rooms number of the House to be created
	 * @param price the price of the House to be created
	 * @param petsAllowed the regulation about having a pet of the House to be created
	 */
	public House(int rooms, int price, boolean petsAllowed){
		this.rooms = rooms;
		this.price = price;
		this.petsAllowed = petsAllowed;
	}
    
    /**
     * returns the room number of this House
     *
     * @return a Integer representing the room number of this House
     */
    public int getRooms() {
    	return rooms;
    }

    /**
     * returns the price of this House
     *
     * @return a Integer representing the price of this House
     */
    public int getPrice(){
        return price;
    }

    /**
     * returns the boolean result regarding having a pet in the house 
     *
     * @return a Boolean representing the pet regulation of this House
     */
    public boolean petsAllowed() {
        return petsAllowed;
    }
    
    /**
     * returns a String representation of this House, including room number, price, and pet regulation.
     *
     * @return a String representation of this House
     */
    @Override
    public String toString(){
		String s;
    	if (petsAllowed() == true) {
    		s = "A pet friendly house with " + getRooms() + " rooms that worths " + getPrice() + "$";}
    	else {
    		s = "A non-pet house with " + getRooms() + " rooms that worths " + getPrice() + "$";}
		return s;
    }
}