/*
 * Authors: Allan MacDougall, Tyler Zudans
 * Note: To get the failed tests, simply remove implementations from successful test Classes.
 */
package clueGame;
import java.io.BufferedReader;
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
	
	// Initializes the internal Variables and calculates the adjacencies
	public void initialize(){
		legend = new HashMap<Character,String>();
		try {
			legendSetup();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}
		grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		try {
			gridSetup();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
	}
	
	//This is setup this way so that legendSetup can be left private after Testing is finished
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException{
		legend = new HashMap<Character,String>();
		legendSetup();
	}
	
	//This is setup this way so that gridSetup can be left private after Testing is finished
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		gridSetup();
	}
		
	//Sets up the legend Matrix
	private void legendSetup() throws FileNotFoundException, BadConfigFormatException {
		FileReader legendFile;
		legendFile = new FileReader(roomConfigFile);
		String line = "";
		String csvSplit = ",";
		BufferedReader scan = new BufferedReader(legendFile);
		try {
			while ((line = scan.readLine()) != null) {
				String[] inputValues = line.split(csvSplit);
				legend.put(inputValues[0].charAt(0), inputValues[1].substring(1));
				if (inputValues[2] != "Card" || inputValues[2] != "Other") {
					throw new BadConfigFormatException(roomConfigFile);
				}
			}
			legendFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Sets up the grid and preps room matrix
	private void gridSetup() throws FileNotFoundException, BadConfigFormatException {
		String[][] tempMap = new String[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		
		FileReader boardFile;
			boardFile = new FileReader(boardConfigFile);
			String line = "";
			String csvSplit = ",";
			BufferedReader scan = new BufferedReader(boardFile);
			try {
				int counter = 0;
				int j =0;
				while ((line = scan.readLine()) != null) {
					String[] inputValues = line.split(csvSplit);
					for (j = 0; j<inputValues.length; j++) {
						tempMap[counter][j] = inputValues[j];
					}
					counter++;
				}
				numCols = j;
				numRows = counter;
				boardFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				scan.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			for (int i = 0; i < numRows; i++) { //Setup Grid
				for (int j = 0; j < numCols; j++) {
					grid[i][j] = new BoardCell(i,j);
					if (tempMap[i][j] == null) {
						throw new BadConfigFormatException(boardConfigFile);
					}
					if (!legend.containsKey(tempMap[i][j].charAt(0))){
						throw new BadConfigFormatException(boardConfigFile);
					}
					if (tempMap[i][j].length() >1) {
						grid[i][j].setInitial(tempMap[i][j].charAt(0), tempMap[i][j].charAt(1));
					}else {
						grid[i][j].setInitial(tempMap[i][j].charAt(0), ' ');
					}
				}
			}
	}
	
	// CalcAdjacencies sets up the grid and sets the adjacencie matrix for each space
	private void calcAdjacencies() {
		
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
	
	//Allows choosing of config Files
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
