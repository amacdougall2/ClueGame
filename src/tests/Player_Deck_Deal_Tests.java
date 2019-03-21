package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Set;

import org.junit.*;
import org.junit.Test;

import clueGame.*;


public class Player_Deck_Deal_Tests {
	
	private static Board board;
	
	@BeforeClass
	public static void initializeTests() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test
	public void loadPlayers() {
		ArrayList<Player> players = board.getPlayers();
		Player testPlayer = new HumanPlayer("Dave",1,1,"Red");
		assertTrue(players.contains(testPlayer));
		assertTrue(players.size() == board.NUM_PLAYERS);
	}
	
	@Test
	public void loadDeck() {
		Set<Card> deck = board.getDeck();
		assertTrue(deck.contains(new Card("Spoon", CardType.Weapon)));
		assertTrue(deck.contains(new Card("Sherpa House", CardType.Room)));
		assertTrue(deck.contains(new Card("Dave", CardType.Person)));
		assertTrue(deck.size() == board.DECK_SIZE);
		
	}
	
	@Test
	public void dealDeck() {
		
	}
}
