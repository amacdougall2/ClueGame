package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.*;

import clueGame.*;

public class gameActionTests {
	private static Board board;
	@BeforeClass
	public static void setup() {
		board = Board.getInstance();
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	@Test
	public void selectTarget() {
		//tests that computer player selects random room from possible targets
		ComputerPlayer player = (ComputerPlayer) board.getPlayers().get(1);//Phil at 0,6
		board.calcTargets(player.getRow(),player.getColumn(),3);
		Set<BoardCell> targets = board.getTargets();
		BoardCell endLocation = player.pickLocation(targets);
		assertTrue(targets.contains(endLocation));
		
		//If room is available and not just visited, select it
		player = (ComputerPlayer) board.getPlayers().get(1);//Phil at 0,6
		board.calcTargets(player.getRow(),player.getColumn(),4);
		targets = board.getTargets();
		endLocation = player.pickLocation(targets);
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 1
		endLocation = player.pickLocation(targets);
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 2
		endLocation = player.pickLocation(targets);
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 3
		
		//Show that each cell is selected and that no invalid ones are
		// Run the test a large number of times
		boolean loc_2_5 = false;
		boolean loc_3_6 = false;

		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(2, 5))
				loc_2_5 = true;
			else if (selected == board.getCellAt(3, 6))
				loc_3_6 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_2_5);
		assertTrue(loc_3_6);
		
		
		
		
	}
	@Test
	public void makeAccusation() {
		
	}
	@Test
	public void createSuggestion() {
		
	}
	@Test
	public void disproveSuggestion() {
		
	}
	@Test
	public void handleSuggestion() {
		
	}
}
