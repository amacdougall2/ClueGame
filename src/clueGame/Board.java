/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package clueGame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Board {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Map<Character,String> legend;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private String boardConfigFile;
	private String roomConfigFile;
	private int numRows;
	private int numCols;
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
		legendSetup();
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[numRows][numCols];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
		
	}
	
	private void legendSetup() {
		FileReader legendFile;
		try {
			legendFile = new FileReader(roomConfigFile);
			String line = "";
			String csvSplit = ",";
			BufferedReader scan = new BufferedReader(legendFile);
			try {
				while ((line = scan.readLine()) != null) {
					String[] inputValues = line.split(csvSplit);
					legend.put(inputValues[0].charAt(0), inputValues[1]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
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
	public BoardCell getCellAt(int i, int j) {
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
	public Map<Character, String> getLegend() {
		return legend;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numCols;
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
