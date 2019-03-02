/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package clueGame;
import java.util.*;

public class Board {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Map<Character,String> Legend;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private String boardConfigFile;
	private String roomConfigFile;
	int numRows;
	int numCols;
	final int MAX_BOARD_SIZE = 70;
	
	// variable used for singleton pattern
		private static Board theInstance = new Board();
		// constructor is private to ensure only one can be created
		private Board() {}
		// this method returns the only Board
		public static Board getInstance() {
			return theInstance;
		}
	
	public void initialize() {
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[numRows][numCols];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
	}
	
	// CalcAdjacencies sets up the grid and sets the adjacencie matrix for each space
	private void calcAdjacencies() {
		for (int i = 0; i < numRows; i++) { //Setup Grid
			for (int j = 0; j < numCols; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		
		for (int i = 0; i < numRows; i++) { //Setup Adjacencies
			for (int j = 0; j < numCols; j++) {
				Set<BoardCell> temp = new HashSet<BoardCell>();
				
				if((i-1)>-1) {
					temp.add(grid[i-1][j]);
				}
				if((i+1)<numRows) {
					temp.add(grid[i+1][j]);
				}
				if((j-1)>-1) {
					temp.add(grid[i][j-1]);
				}
				if((j+1)<numCols) {
					temp.add(grid[i][j+1]);
				}
				adjMtx.put(grid[i][j], temp);
			}
		}
		
	}
	
	// Returns the value of the adjacency matrix for a specific spot
	public Set<BoardCell> getAdj(BoardCell input){
		return adjMtx.get(input);
	}
	
	//Setup for the recursive calls for the target calculation
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited.clear();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
		
	}
	
	//recursive segment of target calculation
	private void findAllTargets(BoardCell startCell,int pathLength) {
		for(BoardCell adjCell: adjMtx.get(startCell)) {
			if(!visited.contains(adjCell)) {
				visited.add(adjCell);
				if(pathLength==1) targets.add(adjCell);
				else findAllTargets(adjCell,pathLength-1);
				visited.remove(adjCell);
			}
		}
	}
	
	//returns the targets list
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	// returns the value of a cell
	public BoardCell getCell(int i, int j) {
		//If statement to prevent out of bound values
		if (i >= numRows || j >= numCols || i < 0 || j < 0) {
			return null;
		}
		return grid[i][j];
	}
	public void setConfigFiles(String layout, String legend) {
		boardConfigFile = layout;
		roomConfigFile = legend;
		
	}
	
	/* Failed Tests Below 
	private void calcAdjacencies() {
		
	}
	private Set<BoardCell> getAdj(){
		return null;
	}
	private void calcTarget(BoardCell startCell, int pathLength) {
		
	}
	private Set<BoardCell> getTargets(){
		return null;
	}
	
	public Set<BoardCell> getTargets(){
		return null;
	}
	 */
	
}
