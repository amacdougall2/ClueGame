package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import experiment.BoardCell;
import experiment.IntBoard;

class IntBoardTests {

	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Before
    public void beforeAll() {
       IntBoard board = new IntBoard();  // constructor should call calcAdjacencies() so you can test them
    }
	@Test
	public void testAdjacency0()
	{
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

}
