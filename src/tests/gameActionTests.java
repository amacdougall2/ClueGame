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
	public void makeAccusationTest() {
		Solution accusation = new Solution("Phil","Koorstek","Spoon");
		Solution answer = new Solution("Phil","Koorstek","Spoon");
		
		//solution that is correct
		answer = new Solution("Phil","McDonalds","Spoon");
		assertTrue(accusation.equals(answer));
		
		//solution with wrong person
		answer = new Solution("Dave","Koorstek","Spoon");
		assertFalse(accusation.equals(answer));
		
		//solution with wrong weapon
		answer = new Solution("Phil","Koorstek","Spork");
		assertFalse(accusation.equals(answer));
		
		//solution with wrong room
		answer = new Solution("Phil","Conservatory","Spoon");
		assertFalse(accusation.equals(answer));
	}
	@Test
	public void createSuggestion() {
		//Computer Player enters room
		ComputerPlayer player = (ComputerPlayer) board.getPlayers().get(1);		
		
		
		//Computer Player Creates Sugggestion
		Solution suggestion = player.createSuggestion();
		
		//Room matches current location
		//If only one weapon not seen, it's selected
		//If only one person not seen, it's selected (can be same test as weapon)
		//If multiple weapons not seen, one of them is randomly selected
		//If multiple persons not seen, one of them is randomly selected
	}
	@Test
	public void disproveSuggestion() {
		
	}
	@Test
	public void handleSuggestion() {
		
	}
}
