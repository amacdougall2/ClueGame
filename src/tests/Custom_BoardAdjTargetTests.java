package tests;

/*
 * This program tests that adjacencies and targets are calculated correctly.
 */

import java.util.Set;

//Doing a static import allows me to write assertEquals rather than
//assertEquals
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class Custom_BoardAdjTargetTests {
	// We make the Board static because we can load it one time and 
	// then do all the tests. 
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	@Test
	public void testAdjacenciesInsideRooms()
	{

		// Test inside a room
		Set<BoardCell> testList = board.getAdjList(3, 5);
		assertEquals(0, testList.size());
		
		// Edge of Board, Inside Room
		testList = board.getAdjList(9, 0);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(11, 6);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(11, 7)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(10, 17);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(10, 16)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(5, 15);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 15)));
		//TEST DOORWAY UP
		testList = board.getAdjList(7, 20);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(6, 20)));
		//TEST DOORWAY RIGHT, WHERE THERE'S A WALKWAY BELOW
		testList = board.getAdjList(4, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(4, 4)));
		
	}
	
	// Test adjacency at entrance to rooms
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction UP
		Set<BoardCell> testList = board.getAdjList(2, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(board.getCellAt(1, 5).isDoorway());
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(2, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(board.getCellAt(1, 5).isDoorway());
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(2, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(board.getCellAt(1, 5).isDoorway());
		assertEquals(3, testList.size());
		// Test beside a door direction RIGHT
		testList = board.getAdjList(2, 5);
		assertTrue(testList.contains(board.getCellAt(1, 5)));
		assertTrue(board.getCellAt(1, 5).isDoorway());
		assertEquals(3, testList.size());
	}

	// Test a variety of walkway scenarios
	@Test
	public void testAdjacencyWalkways() 
	{
		
		// Test on top edge of board, just one walkway piece
		//Only walkways as adjacent locations
		Set<BoardCell> testList = board.getAdjList(3, 5);
		assertTrue(testList.contains(board.getCellAt(2, 5)));
		assertTrue(testList.contains(board.getCellAt(4, 5)));
		assertTrue(testList.contains(board.getCellAt(3, 6)));
		assertTrue(testList.contains(board.getCellAt(3, 4)));
		assertEquals(4, testList.size());
		
		// Edge Walkway
		testList = board.getAdjList(6, 0);
		assertTrue(testList.contains(board.getCellAt(6, 1)));
		assertEquals(1, testList.size());

		// Next to Room, not a doorway
		testList = board.getAdjList(3, 5);
		assertTrue(testList.contains(board.getCellAt(6, 20)));
		assertTrue(testList.contains(board.getCellAt(6, 22)));
		assertEquals(2, testList.size());
		
		// Next to Room, not a doorway
		testList = board.getAdjList(16, 9);
		assertTrue(testList.contains(board.getCellAt(6, 20)));
		assertTrue(testList.contains(board.getCellAt(6, 22)));
		assertEquals(2, testList.size());
		
	}
	
	
	// Tests of just walkways, 1,2,4,6 steps, includes on edge of board
	// and beside room
	@Test
	public void testTargetsAlongWalkway() {
		board.calcTargets(21, 16, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(21, 16)));
		assertTrue(targets.contains(board.getCellAt(21, 6)));	
		
		board.calcTargets(13, 17, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 15)));	
		
		board.calcTargets(13, 18, 4);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 17)));
		
		board.calcTargets(14, 12, 6);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 11)));
	}
	
	// Test getting into a room

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 1 away
		board.calcTargets(19, 11, 1);
		Set<BoardCell> targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(19, 12)));
		
		// One room is exactly 2 away
		board.calcTargets(18, 16, 2);
		targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(17, 15)));
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// One room is exactly 1 away
		board.calcTargets(16, 9, 1);
		Set<BoardCell> targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(15, 9)));
				
		// One room is exactly 2 away
		board.calcTargets(14, 21, 2);
		targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(13, 20)));
	}

}
