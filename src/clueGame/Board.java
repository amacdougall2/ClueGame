/*
 * Authors: Allan MacDougall, Tyler Zudans
 * Note: To get the failed tests, simply remove implementations from successful test Classes.
 */
package clueGame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

public class Board extends JPanel{
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	public Map<Character,String> legend;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	private String boardConfigFile;
	private String roomConfigFile;
	private String playerConfigFile;
	private String weaponConfigFile;
	private int numRows;
	private int numCols;
	public Solution theAnswer;
	public ArrayList<Player> players;
	public Set<Card> deck;
	public final int DECK_SIZE = 21; //18 is a temporary value for now
	public final int NUM_PLAYERS = 6;
	final int MAX_BOARD_SIZE = 70;
	int currPlayer = 0;
	
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
		players = new ArrayList<Player>();
		deck = new HashSet<Card>();
		grid = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		//Try-Catch phases in this function so that they can be handled here
		loadConfigFiles();
		setPlayerDecks();
		calcAdjacencies();
		dealCards();
		this.setPreferredSize(new Dimension(25*numCols,25*numRows));
	}
	
	public void loadConfigFiles() { //Used for testing configuration loading
		try {
			playerSetup();
			loadRoomConfig();
			loadBoardConfig();
			weaponSetup();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			e.printStackTrace();
		}
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
	
	//Sets up Player data
	private void playerSetup() throws FileNotFoundException {
		FileReader playerFile = new FileReader(playerConfigFile);
		String line;
		BufferedReader scan = new BufferedReader(playerFile);
		try {
			while ((line = scan.readLine()) != null) {//Loads in the Values for each player
				String[] inputValues = line.split(",");
				players.add(new ComputerPlayer(inputValues[0],Integer.parseInt(inputValues[1].substring(1)), Integer.parseInt(inputValues[2].substring(1)), inputValues[3].substring(1)));
				deck.add(new Card(inputValues[0], CardType.Person));
			}
			playerFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Sets up Weapon Data
	private void weaponSetup() throws FileNotFoundException {
		FileReader weaponFile = new FileReader(weaponConfigFile);
		String line;
		BufferedReader scan = new BufferedReader(weaponFile);
		try {
			while ((line = scan.readLine()) != null) {//Loads in the Values for each player
				String[] inputValues = line.split(",");
				deck.add(new Card(inputValues[0], CardType.Weapon));
			}
			weaponFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	//Sets up the legend Matrix
	private void legendSetup() throws FileNotFoundException, BadConfigFormatException {
		FileReader legendFile = new FileReader(roomConfigFile);
		String line;
		@SuppressWarnings("resource")
		BufferedReader scan = new BufferedReader(legendFile);
		try {
			while ((line = scan.readLine()) != null) {//Loads in the three values of each legend space
				String[] inputValues = line.split(",");
				legend.put(inputValues[0].charAt(0), inputValues[1].substring(1));
				if (inputValues[2].substring(1).equals("Card")) {
					deck.add(new Card(inputValues[1].substring(1), CardType.Room));
				}
				if (inputValues[2].substring(1).equals("Card")|| inputValues[2].substring(1).equals("Other")) {
					//System.out.println(inputValues[2].substring(1));
				}else {
					System.out.println(inputValues[2]);
					throw new BadConfigFormatException(roomConfigFile);
				}
			}
			legendFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Sets up the grid and preps room matrix
	private void gridSetup() throws FileNotFoundException, BadConfigFormatException {
			String[][] tempMap = new String[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
			
			FileReader boardFile = new FileReader(boardConfigFile);
			String line;
			BufferedReader scan = new BufferedReader(boardFile);
			try {
				int counter = 0;
				int j =0;
				while ((line = scan.readLine()) != null) { //Continues to read lines into an array until the file is done
					String[] inputValues = line.split(",");
					for (j = 0; j<inputValues.length; j++) { //places the value of each space into the same spot in grid
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
				e.printStackTrace();
			}
			
		
			for (int i = 0; i < numRows; i++) { //Checks to make sure we have loaded a valid grid, otherwise throws an error
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
		
		//For each valid cell on the board
		for (int i = 0; i < numRows; i++) { 
			for (int j = 0; j < numCols; j++) {
				Set<BoardCell> temp = new HashSet<BoardCell>();
				
				//if the cell at i,j is a doorway, add adj cell in it's specified direction to map
				if(grid[i][j].isDoorway()) {
					switch(grid[i][j].getDoorDirection()) {
						case RIGHT:
							temp.add(grid[i][j+1]);
							break;
						case LEFT:
							temp.add(grid[i][j-1]);
							break;
						case UP:
							temp.add(grid[i-1][j]);
							break;
						case DOWN:
							temp.add(grid[i+1][j]);
							break;
						case NONE:
							System.out.println("Error: None should not be evaluated in this case statement");
							break;
						 
					}
				}
				
				//if its not in a room check in all 4 direction for valid adj cells and add them to the map
				if(!grid[i][j].isRoom()) {
					if((i-1)>-1) {//test cell above
						BoardCell item = check_add(i,j,DoorDirection.DOWN);
						if (item!=null) temp.add(item);
					}
					if((i+1)<numRows) {//test cell below
						BoardCell item = check_add(i,j,DoorDirection.UP);
						if (item!=null) temp.add(item);
					}
					if((j-1)>-1) {//test cell to left
						BoardCell item = check_add(i,j,DoorDirection.RIGHT);
						if (item!=null) temp.add(item);
					}
					if((j+1)<numCols) {//test cell to right
						BoardCell item = check_add(i,j,DoorDirection.LEFT);
						if (item!=null) temp.add(item);
					}
				}
				
				//push the key value pair of the cell and its valid adj cell(s) to the boards adjMtx map
				adjMtx.put(grid[i][j], temp);//temp empty by default if it's a room and not a doorway
			}
		}
		
	}
	
	public BoardCell check_add(int i, int j, DoorDirection d) {
		int tar_i=0;//row offset for the one adjacent cell checked from cell i,j
		int tar_j=0;//col offset for the one adjacent cell checked from cell i,j
		
		//set row and col offsets according to direction being checked (opposite of door direction)
		switch (d){
			case DOWN://evaluating cell above i,j
				tar_i=-1;
				break;
			case UP://evaluating cell below i,j
				tar_i=1;
				break;
			case LEFT:// evaluating cell to right of i,j
				tar_j=1;
				break;
			case RIGHT:// evaluating cell to left of i,j
				tar_j=-1;
				break;
			case NONE:
				System.out.println("This case in check_add should not be run");
				break;
		}
		
		//evaluate adjacent cell for validity
		if(grid[i+tar_i][j+tar_j].isDoorway()||grid[i+tar_i][j+tar_j].isWalkway()) {//it is a walkway or a door
			if(grid[i+tar_i][j+tar_j].isDoorway()){//is door
				if(grid[i+tar_i][j+tar_j].getDoorDirection()==d) {//is in correct direction
					return(grid[i+tar_i][j+tar_j]);
				}//otherwise it will return null
			}
			else {//is a walkway
				return(grid[i+tar_i][j+tar_j]);
			}
			
		}
		return(null);
	}
	
	//Setup for the recursive calls for the target calculation
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited = new HashSet<BoardCell>(); //initialized the visited and targets sets
		targets = new HashSet<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	
	//Alternate method added because the tests we are given are never consistent in their syntax
	public void calcTargets(int startRow, int startCol, int pathLength) {
		calcTargets(grid[startRow][startCol], pathLength);
	}
	
	//recursive segment of target calculation
	private void findAllTargets(BoardCell startCell,int pathLength) {
		for(BoardCell adjCell: adjMtx.get(startCell)) {
			if(!visited.contains(adjCell)) {
				visited.add(adjCell);
				if(pathLength==1) { //If this is the end of the path, add to targets
					targets.add(adjCell);
				}else if(adjCell.isDoorway()) { //if the space is a door, we can use it
					targets.add(adjCell);
				}else { //Otherwise, test all targets of the next space with one less dice roll
					findAllTargets(adjCell,pathLength-1);
				}
				visited.remove(adjCell); //Removes current location, since this may be recalculated in later steps
			}
		}
	}
	
	public void dealCards() {
		int counter = 0; //Counter that keeps track of current player
		for (Card c : deck) {
			players.get(counter).addCard(c);
			if (counter < NUM_PLAYERS-1) {
				counter++;
			}else {
				counter = 0;
			}
		}
	}
	
	public void selectAnswer() {
		
	}
	
	public Card handleSuggestion(Solution suggestion,Player p) { //TO BE DETERMINED
		int pIndex = getPlayers().indexOf(p);
		int numP = getPlayers().size();
		for(int i=pIndex+1;i<pIndex+numP;i++) {//cycles around the accuser not inclusive
			int j = i%numP;
			Card potDisprove = getPlayers().get(j).disproveSuggestion(suggestion);
			if(potDisprove!=null) return potDisprove;
		}
		return null;
	}
	
	public boolean checkAccusation(Solution accusation) {
		return accusation.equals(theAnswer);
	}
	
	//returns the targets list
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public Set<Card> getDeck(){
		return deck;
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
	public void setConfigFiles(String layout, String legend, String players, String weapons) {
		boardConfigFile = layout;
		roomConfigFile = legend;
		playerConfigFile = players;
		weaponConfigFile = weapons;
	}
	
	public void setConfigFiles(String layout, String legend) {
		boardConfigFile = layout;
		roomConfigFile = legend;
	}
	
	public Card findCard(String name) {
		for (Card c : deck) {
			if (c.getCardName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	public Player findPlayer(String name) {
		for (Player p : players) {
			if (p.getPlayerName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public Player setPlayerDecks() {
		for (Player p : players) {
			p.setDeck(deck);
		}
		return null;
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
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Set<BoardCell> getAdj(BoardCell input){
		return adjMtx.get(input);
	}
	
	public Set<BoardCell> getAdjList(int x, int y){
		return adjMtx.get(grid[x][y]);
	}
		
	public void printPlayers() {
		for (Player p : players) {
			System.out.println(p);
		}
	}
	
	public void printDeck() {
		for (Card d : deck) {
			System.out.println(d);
		}
	}
	public String legend(char c) {
		return legend.get(c);
	}
	public void addPlayer(Player p) {
		// TODO Auto-generated method stub
		players.add(p);
		
	}
	public void setAnswer (Solution s) {
		theAnswer=s;
	}
	
	/*
	 * GUI CODE BELOW
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setLayout(new GridLayout(numRows,numCols));
		
		//Draw all cells
		for(int i = 0; i < numRows; i++) {
			BoardCell[] segment = grid[i];
			for (int j = 0; j < numCols; j++) {
				BoardCell cell = segment[j];
				cell.draw(g);
			}
		}
		
		//Draw all Players
		for(Player p : players) {
			p.draw(g);
		}
		
		//Draw the Name of each Room above the room
		drawName(g,"Coolbaugh Hall", 10, 35);
		drawName(g, "Koorstek", 10, 450);
		drawName(g, "Blaster's", 15, 260);
		drawName(g, "Brew", 15, 280);
		drawName(g, "Rec", 200, 450);
		drawName(g, "Center", 200, 470);
		drawName(g, "Mount Zion", 230, 50);
		drawName(g, "Aspen", 325, 450);
		drawName(g, "Hall", 325, 470);
		drawName(g, "Sherpa House", 475, 450);
		drawName(g, "Woody's Pizza", 450, 250);
		drawName(g, "PCJ's House", 450, 75);
	}
	
	private void drawName(Graphics g, String name,  int col, int row) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.BLACK);
		g.drawString(name, col, row);
	}
	public void nextPlayer() {
		currPlayer = (currPlayer+1)%NUM_PLAYERS;
		Player current = getPlayers().get(currPlayer);
		if(current.getClass()== ComputerPlayer.class) {//move randomly for computer
			current.roll();
			calcTargets(grid[current.getColumn()][current.getRow()],current.roll);
			BoardCell selectedTarget = new BoardCell(0,0);
			
			Random rand = new Random();
			int index = rand.nextInt(targets.size());
			int i = 0;
			debug_print(targets);
			for(BoardCell b: targets) {
				if(i==index) selectedTarget = b;
				i++;
			}
			System.out.println(selectedTarget);
			current.setLocation(selectedTarget);
		}
	}
	private void debug_print(Set<BoardCell> targets2) {
		// TODO Auto-generated method stub
		for(BoardCell b: targets2) {
			System.out.print(b);
			System.out.print(" ");
		}
		System.out.println();
		
	}
	
}
