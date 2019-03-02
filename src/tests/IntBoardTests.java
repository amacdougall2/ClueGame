/*
 * Authors: Tyler Zudans, Allan MacDougall
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

public class IntBoardTests {

	IntBoard board;  // constructor should call calcAdjacencies() so you can test them
	
	//Start tests: Initialize a new Board
	@Before
    public void beforeAll() {
       board = new IntBoard();
    }
	
	// Start testing values for each space in the instructions
	@Test
	public void testAdjacency00()
	{
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdj(cell);
		//Test direct adjacencies
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		// test the size of targets
		assertEquals(6, targets.size());
		// test the values that are in GetTargets
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertFalse(targets.contains(board.getCell(3, 3)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency33()
	{
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdj(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertFalse(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertFalse(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency13()
	{
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdj(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(0, 3)));
		//tests to make sure we dont return out of bounds values
		assertFalse(testList.contains(board.getCell(1, 4)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertFalse(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertFalse(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacency30()
	{
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testList = board.getAdj(cell);
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertTrue(testList.contains(board.getCell(2, 0)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertFalse(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertFalse(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency11()
	{
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdj(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertFalse(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertFalse(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjacency22()
	{
		BoardCell cell = board.getCell(2,2);
		Set<BoardCell> testList = board.getAdj(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCell(3, 2)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertFalse(targets.contains(board.getCell(0, 0)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertFalse(targets.contains(board.getCell(3, 3)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertEquals(4, testList.size());
	}

}
