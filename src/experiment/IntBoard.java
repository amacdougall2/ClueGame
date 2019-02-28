
package experiment;
import java.util.*;

public class IntBoard {
	private Map<BoardCell, Set<BoardCell>> adjMtx;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	final int NUM_ROWS = 4;
	final int NUM_COLS = 4;
	public IntBoard() {
		adjMtx= new HashMap<BoardCell, Set<BoardCell>>();
		grid = new BoardCell[NUM_ROWS][NUM_COLS];
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		calcAdjacencies();
	}
	private void calcAdjacencies() {

		for (int i = 0; i < NUM_ROWS; i++) {
			for (int j = 0; j < NUM_COLS; j++) {

				grid[i][j] = new BoardCell(i,j);
				Set<BoardCell> temp = new HashSet<BoardCell>();
				
				if((i-1)>-1) {
					temp.add(new BoardCell(i-1,j));
				}
				if((i+1)<NUM_ROWS+1) {
					temp.add(new BoardCell(i+1,j));
				}
				if((j-1)>-1) {
					temp.add(new BoardCell(i,j-1));
				}
				if((j+1)<NUM_COLS+1) {
					temp.add(new BoardCell(i,j+1));
				}
				adjMtx.put(new BoardCell(i,j), temp);
			}
		}
		
	}
	public Set<BoardCell> getAdj(BoardCell input){
		return adjMtx.get(input);
	}
	private void calcTarget(BoardCell startCell, int pathLength) {
		visited.clear();
		visited.add(startCell);
		
		
	}
	private void findAllTargets(BoardCell startCell,int pathLength) {
		for(BoardCell adjCell: adjMtx.get(startCell)) {
			if(!visited.contains(adjCell)) {
				visited.add(adjCell);
				if(pathLength==1) targets.add(adjCell);
				else findAllTargets(adjCell,pathLength-1);
				visited.remove(adjCell);
			}
		}
	}
	private Set<BoardCell> getTargets(){
		return targets;
	}
	public BoardCell getCell(int i, int j) {
		return grid[i][j];
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
