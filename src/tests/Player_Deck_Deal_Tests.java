package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
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
		Player testPlayer = board.findPlayer("Dave");
		//board.printPlayers(); //This is used for debugging
		assertTrue(testPlayer != null);
		assertEquals(5, testPlayer.getRow());
		assertEquals(0, testPlayer.getColumn());
		assertTrue(testPlayer.getColumn() == 0);
		assertTrue(testPlayer.getColor() == Color.RED);
		assertTrue(players.size() == board.NUM_PLAYERS);
	}
	
	@Test
	public void loadDeck() {
		Set<Card> deck = board.getDeck();
		Card test = board.findCard("Spoon");
		assertTrue(test != null);
		assertTrue(test.getType() == CardType.Weapon);
		test = board.findCard("Sherpa House");
		assertTrue(test != null);
		assertTrue(test.getType() == CardType.Room);
		test = board.findCard("Dave");
		assertTrue(test != null);
		assertTrue(test.getType() == CardType.Person);
		assertEquals(deck.size(), board.DECK_SIZE);
		
	}
	
	@Test
	public void dealDeck() {
		board.dealCards();
		ArrayList<Player> players = board.getPlayers();
		Player testPlayer = board.findPlayer("Dave");
		assertEquals(testPlayer.getMyCards().size(), board.DECK_SIZE/board.NUM_PLAYERS, 1);
		for (Player p : players) {
			assertEquals(p.getMyCards().size(), board.DECK_SIZE/board.NUM_PLAYERS, 1);
		}
	}
}
