// Authors: Allan MacDougall, Tyler Zudans
package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private Solution mySuggestion = null;
	
	
	public Solution makeAccusation() {
		return null;
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
			Random rand = new Random();
			int index = rand.nextInt(options.size());
			return options.get(index);
		}
	}
	
	@Override
	public Solution createSuggestion() {
		Set<Card> unseenCards = new HashSet<Card>();
		Set<Card> unseenPeople = new HashSet<Card>();
		Set<Card> unseenWeapons = new HashSet<Card>();
		
		//Place all unseen, usable cards into a set
		for (Card card : this.deck) {
			if(!(this.seenCards.contains(card))) {
				unseenCards.add(card);
			}
		}
		//Places the Unseen cards into two sets based on if they are people or weapons
		for(Card card : unseenCards) {
			if(card.getType() == CardType.Person) {
				unseenPeople.add(card);
			}else if (card.getType().equals(CardType.Weapon)) {
				unseenWeapons.add(card);
			}
		}
		String person = randomCard(unseenPeople).getCardName();
		String weapon = randomCard(unseenWeapons).getCardName();
		mySuggestion = new Solution(person, currentRoom, weapon);
		
		return mySuggestion;
	}
	
	@Override
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
	
	private Card randomCard(Set<Card> cardSet) {
		//This code will cycle randomly through the Elements in a set
		//The code will then return the randomly chosen value
		Random rand = new Random();
		int randInt = rand.nextInt(cardSet.size());
		int index = 0; 
	    for(Card element : cardSet){
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
	
	public void printHand() {
		for (Card c : myCards) {
			System.out.println(c);
		}
	}
	public void move() {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void showLocations(Board board) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finishedTurn(BoardCell clicked) {
		// TODO Auto-generated method stub
		
	}
}
