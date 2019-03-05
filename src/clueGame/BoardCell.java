/*
 * Authors: Allan MacDougall, Tyler Zudans
 * Note: To get the failed tests, simply remove implementations from successful test Classes.
 */
package clueGame;

public class BoardCell {
	//Stores the row and collumn of a cell, uses constructor to set theses values
	private int row;
	private int col;
	private char initial;
	//default doorInitial to space because characters cant compare to null
	private DoorDirection doorInitial = DoorDirection.NONE;
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	//Sets the initial for the boardCell, and chooses the door's setting too.
	public void setInitial(char intial, char door) {
		this.initial = intial;
		switch (door) {
		case('U'):
			doorInitial = DoorDirection.UP;
			break;
		case('D'):
			doorInitial = DoorDirection.DOWN;
			break;
		case('L'):
			doorInitial = DoorDirection.LEFT;
			break;
		case('R'):
			doorInitial = DoorDirection.RIGHT;
			break;
		case(' '):
			doorInitial = DoorDirection.NONE;
			break;
		}
	}
	
	//Returns True if the area is a walkway, false otherwise
	public boolean isWalkway() {
		return (initial == 'W');
	}
	
	//Returns true if the area is not a walkway or closet
	public boolean isRoom() {
		return (initial != 'W' && initial != 'X');
	}
	
	//Returns true if the enum value for Doorway is not NONE
	public boolean isDoorway() {
		return (doorInitial != DoorDirection.NONE);
	}
	
	//Returns the direction of the Door (Will return the word NONE if the space is not a door
	public DoorDirection getDoorDirection() {
		return doorInitial;
	}
	
	//Returns the int value of the row
	public int getRow() {
		return row;
	}
	//Returns the int value of the collumn
	public int getCol() {
		return col;
	}

	//returns the Char value of the initial
	public char getInitial() {
		return initial;
	}
	
	
}
