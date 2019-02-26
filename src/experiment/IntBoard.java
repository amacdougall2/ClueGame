
package experiment;
import java.util.*;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	public IntBoard() {
		calcAdjacencies();
	}
	private void calcAdjacencies() {
		// TODO Auto-generated method stub
		
	}
	private Set<BoardCell> getAdj(){
		return null;
	}
	private void calcTarget(BoardCell startCell, int pathLength) {
		
	}
	private Set<BoardCell> getTargets(){
		return targets;
	}
	
}
