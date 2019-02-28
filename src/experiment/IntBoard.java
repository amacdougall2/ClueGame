
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
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 22; j++) {
				grid[i][j] = new BoardCell(i,j);
				Set<BoardCell> temp = new HashSet<BoardCell>();
				
				if((i-1)>-1) {
					temp.add(new BoardCell(i-1,j));
				}
				if((i+1)<24) {
					temp.add(new BoardCell(i+1,j));
				}
				if((j-1)>-1) {
					temp.add(new BoardCell(i,j-1));
				}
				if((j+1)<23) {
					temp.add(new BoardCell(i,j+1));
				}
				adjMtx.put(new BoardCell(i,j), temp);
			}
		}
		
	}
	private Set<BoardCell> getAdj(BoardCell input){
		return adjMtx.get(input);
	}
	private void calcTarget(BoardCell startCell, int pathLength) {
		
	}
	private Set<BoardCell> getTargets(){
		return targets;
	}
	
	
	/* Failed Tests Below 
	private void calcAdjacencies() {
		// TODO Auto-generated method stub
		
	}
	private Set<BoardCell> getAdj(){
		return null;
	}
	private void calcTarget(BoardCell startCell, int pathLength) {
		
	}
	private Set<BoardCell> getTargets(){
		return null;
	}
	 */
	
}
