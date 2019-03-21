package tests;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class Player_Deck_Deal_Tests {
	
	private static Board board;
	
	@BeforeClass
	public void initializeTests() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void loadPlayers() {
		
	}
	
	@Test
	public void loadDeck() {
		
	}
	
	@Test
	public void dealDeck() {
		
	}
}
