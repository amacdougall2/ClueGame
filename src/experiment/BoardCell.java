/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package experiment;

public class BoardCell {
	//Stores the row and collumn of a cell, uses constructor to set theses values
	//No setters because Row and Collumn should never change
	private int row;
	private int col;
	public BoardCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	
	
}
