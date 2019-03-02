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
	//default doorInitial to space because characters cant compare to null, or blank, or anything useful
	private char doorInitial = ' ';
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public boolean isWalkway() {
		return (initial == 'W');
	}
	
	public boolean isRoom() {
		return (initial != 'W');
	}
	
	public boolean isDoorWay() {
		return (doorInitial != ' ');
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	
}
