/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package experiment;
import java.util.*;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	final int NUM_ROWS = 4;
	final int NUM_COLS = 4;
	public IntBoard() {
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[NUM_ROWS][NUM_COLS];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
	}
	
	// CalcAdjacencies sets up the grid and sets the adjacencie matrix for each space
	private void calcAdjacencies() {
		for (int i = 0; i < NUM_ROWS; i++) { //Setup Grid
			for (int j = 0; j < NUM_COLS; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		
		for (int i = 0; i < NUM_ROWS; i++) { //Setup Adjacencies
			for (int j = 0; j < NUM_COLS; j++) {
				Set<BoardCell> temp = new HashSet<BoardCell>();
				
				if((i-1)>-1) {
					temp.add(grid[i-1][j]);
				}
				if((i+1)<NUM_ROWS) {
					temp.add(grid[i+1][j]);
				}
				if((j-1)>-1) {
					temp.add(grid[i][j-1]);
				}
				if((j+1)<NUM_COLS) {
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
		if (i >= NUM_ROWS || j >= NUM_COLS || i < 0 || j < 0) {
			return null;
		}
		return grid[i][j];
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
