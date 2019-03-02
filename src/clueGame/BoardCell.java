/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package clueGame;

public class BoardCell {
	//Stores the row and collumn of a cell, uses constructor to set theses values
	//No setters because Row and Collumn should never change
	private int row;
	private int col;
	private char initial;
	//default doorInitial to space because characters cant compare to null
	private DoorDirection doorInitial = DoorDirection.NONE;
	public BoardCell(int row, int col, char initial) {
		super();
		this.row = row;
		this.col = col;
		this.initial = initial;
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
