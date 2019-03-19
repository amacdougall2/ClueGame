package clueGame;

import java.awt.Color;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public void makeAccusation() {
		
	}
	
	public void createSuggestion() {
		//FIXME: TO BE DETERMINED
	}
	
	public BoardCell pickLocation(Set<BoardCell> targets) {
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
