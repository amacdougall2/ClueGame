/*
 * Authors: Tyler Zudans, Allan MacDougall
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
		this.initial = initial;
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
	
	public boolean isWalkway() {
		return (initial == 'W');
	}
	
	public boolean isRoom() {
		return (initial != 'W');
	}
	
	public boolean isDoorway() {
		return (doorInitial != DoorDirection.NONE);
	}
	
	public DoorDirection getDoorDirection() {
		return doorInitial;
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

	public char getInitial() {
		return initial;
	}
	
	
}
