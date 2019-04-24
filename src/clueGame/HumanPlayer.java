// Authors: Allan MacDougall, Tyler Zudans
package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class HumanPlayer extends Player {

	public HumanPlayer(String playerName, int row, int column, Color color) {
		super(playerName, row, column, color);
		super.isHuman=true;
		// TODO Auto-generated constructor stub
	}
	public HumanPlayer(String playerName, int row, int column, String color) {
		super(playerName, row, column, color);
		super.isHuman=true;
		// TODO Auto-generated constructor stub
	}
	@Override
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> options = new ArrayList<Card>();//cards that can be used to disprove
		for(Card c:this.seenCards) {
			String cName=c.getCardName();
			if(cName.equals(suggestion.person)||cName.equals(suggestion.room)||cName.equals(suggestion.weapon)) options.add(c);
		}
		if(options.isEmpty()==true) return null;
		else {
			//If only one card available, force Card
			if(options.size()==1) return options.get(0);
			
			//If multiple cards prompt
			else {
				System.out.println("FIX-ME: human disprove suggestion prompt required");
			}
		}
		return null;
	}
	
	public void showLocations(Board board) {
		finished = false;
		
		board.highlightSquare(true);
	}
	
	private BoardCell randomSpace(Set<BoardCell> targets) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void finishedTurn(BoardCell clicked) {
		row = clicked.getRow();
		column = clicked.getCol();
		finished = true;
		}
	
	//dummy method
	@Override
	public BoardCell pickLocation(Set<BoardCell> targets, BoardCell startCell) {
		// TODO Auto-generated method stub
		return null;
	}
}
