package clueGame;

import java.awt.Color;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public Solution makeAccusation() {
		return null;
	}
	
	public Solution createSuggestion() {
		return new Solution("","","");
		//FIXME: TO BE DETERMINED
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets, BoardCell startCell) {
		if (startCell.isRoom()) {
			return randomSpace(targets);
		}
		
		for (BoardCell cell : targets) {
			if (cell.isRoom() == true) {
				return cell;
			}
		}
		
		return randomSpace(targets);
	}

	private BoardCell randomSpace(Set<BoardCell> targets) {
		//This code will cycle randomly through the Elements in a set
		//The code will then return the randomly chosen value
		Random rand = new Random();
		int randInt = rand.nextInt(targets.size());
		int index = 0; 
	    for(BoardCell element : targets){
	    	if(index == randInt){
	    		return element;
	    	}
	    	index++;
	    }
	    return null;
	}
	
	public ComputerPlayer(String playerName, int row, int column, Color color) {
		super(playerName, row, column, color);
		// TODO Auto-generated constructor stub
	}
	
	public ComputerPlayer(String playerName, int row, int column, String color) {
		super(playerName, row, column, color);
		// TODO Auto-generated constructor stub
	}
}
