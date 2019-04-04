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
		BoardCell endLocation = player.pickLocation(targets, board.getCellAt(0, 6));
		assertTrue(targets.contains(endLocation));
		
		//If room is available and not just visited, select it
		player = (ComputerPlayer) board.getPlayers().get(1);//Phil at 0,6
		board.calcTargets(player.getRow(),player.getColumn(),4);
		targets = board.getTargets();
		endLocation = player.pickLocation(targets, board.getCellAt(0, 6));
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 1
		endLocation = player.pickLocation(targets, board.getCellAt(0, 6));
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 2
		endLocation = player.pickLocation(targets, board.getCellAt(0, 6));
		assertTrue(endLocation.isRoom());//endLocation Room is C Test 3
		
		//Show that each cell is selected and that no invalid ones are
		// Run the test a large number of times
		boolean loc_2_5 = false;
		boolean loc_3_6 = false;

		board.calcTargets(player.getRow(),player.getColumn(),3);
		
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets(), board.getCellAt(0, 6));
			if (selected.equals(board.getCellAt(2, 5))) {
				loc_2_5 = true;
			}else if (selected.equals(board.getCellAt(3, 6))) {
				loc_3_6 = true;
			}else {
				assertEquals(board.getCellAt(1, 6), selected);
				System.out.println(selected);
				fail("Invalid target selected");
			}
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
		char playerRoomC = board.getCellAt(player.getRow(),player.getColumn()).getInitial();
		assertEquals(board.legend(playerRoomC),suggestion.room);
		//add all but 2 weapons
		player.addCard(new Card("Spoon",CardType.Weapon));
		player.addCard(new Card("Spork",CardType.Weapon));
		player.addCard(new Card("Fpoon",CardType.Weapon));
		player.addCard(new Card("Ladle",CardType.Weapon));
		suggestion = player.createSuggestion();
		//If multiple weapons not seen, one of them is randomly selected
		assertTrue(suggestion.weapon.equals("Blender")||suggestion.weapon.equals("Rifle"));
		//add all but 2 people
		player.addCard(new Card("Dave",CardType.Person));
		player.addCard(new Card("Phil",CardType.Person));
		player.addCard(new Card("Billy",CardType.Person));
		player.addCard(new Card("Jimothy",CardType.Person));
		suggestion = player.createSuggestion();
		
		//If multiple persons not seen, one of them is randomly selected
		assertTrue(suggestion.person.equals("Bob")||suggestion.person.equals("Fransisca"));
		
		//add 1 weapon and 1 person, redo suggestion
		player.addCard(new Card("Bob",CardType.Person));
		player.addCard(new Card("Rifle",CardType.Weapon));
		suggestion = player.createSuggestion();
		
		//If only one weapon and person not seen, it's selected
		assertEquals(suggestion.weapon,"Blender");
		assertEquals(suggestion.person,"Fransisca");
		
	}
	
	@Test
	public void disproveSuggestion() {
		ComputerPlayer player = (ComputerPlayer) board.getPlayers().get(0);	
		
		
		
		
		
		player.addCard(new Card("Bob",CardType.Person));
		Solution suggestion = new Solution("Bob","Koorstek","Spoon");
		Card refute = player.disproveSuggestion(suggestion);
		//If player has only one matching card it should be returned
		assertTrue(false);
		assertEquals(refute.getCardName(),"Bob");
		
		player.addCard(new Card("Spoon",CardType.Weapon));
		//check that refute chooses cards randomly when >1 options available
		boolean sPick = false;
		boolean bPick = false;
		for(int i= 0;i<100;i++) {
			refute = player.disproveSuggestion(suggestion);
			if(refute.getCardName()=="Spoon") sPick = true;
			if(refute.getCardName()=="Spoon") bPick = true;
		}
		assertTrue(sPick);
		assertTrue(bPick);
		
		//If player has no matching cards, null is returned
		ComputerPlayer player2 = new ComputerPlayer("Kate",0,0,"black");
		assertEquals(player2.disproveSuggestion(suggestion),null);
	}
	@Test
	public void handleSuggestion() {
		//Null if no one can disprove suggestion
		Player player = board.getPlayers().get(0);
		Solution suggestion = new Solution("Drax","Space","Axe");
		Card handle = board.handleSuggestion(suggestion,board.getPlayers().get(0));
		assertTrue(handle==null);
		
		//Card from suggestors deck
		suggestion = ((ComputerPlayer)player).createSuggestion();
		Set<Card> privates = player.getSeenCards();
		handle = board.handleSuggestion(suggestion,player);
		assertTrue(handle==null);
		
		//Card only human can dissprove return card
		Player bill = new HumanPlayer("bill",0,0,"green");
		board.addPlayer(bill);
		Card c1 = new Card("Dude",CardType.Person);
		bill.addCard(c1);
		suggestion = new Solution("Dude","Place","Thing");
		handle = board.handleSuggestion(suggestion,player);
		assertTrue(c1.equals(handle));
		
		//Card only human can dissprove, as the accuser, returns null
		handle = board.handleSuggestion(suggestion,bill);
		assertTrue(handle==null);
		
		//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer
		suggestion = new Solution("Ethan","Lab","Fire");
		Player p1 = board.getPlayers().get(1);
		Player p2 = board.getPlayers().get(2);
		Card c0=new Card("Ethan",CardType.Person);
		Card c2=new Card("Lab",CardType.Room);
		p1.addCard(c0);
		p2.addCard(c2);
		handle = board.handleSuggestion(suggestion, player);
		assertEquals(c0,handle);
		
		//Suggestion that human and another player can disprove, other player is next in list, ensure other player returns answer
		HumanPlayer p3 = new HumanPlayer("Thomas",0,0,"blue");
		Card c3= new Card("Fire",CardType.Weapon);
		p3.addCard(c3);
		suggestion = new Solution("Jenkins","Lab","Fire");
		handle = board.handleSuggestion(suggestion, player);
		assertTrue(handle.equals(c2));
		
	}
	
}
