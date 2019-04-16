// Authors: Allan MacDougall, Tyler Zudans
package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Player {
	

	protected String playerName;
	protected String currentRoom = "Walkway"; //Defaults to walkway, as all players spawn at walkways
	protected int row;
	protected int column;
	protected Color color;
	protected Set<Card> myCards = new HashSet<Card>();
	protected Set<Card> seenCards = new HashSet<Card>();
	protected Set<Card> deck = new HashSet<Card>();
	protected int roll = 0;
	
	public void roll() {
		Random rand = new Random();
		roll= rand.nextInt()%6+1;
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
	}
	
	public void addCard(Card newCard) {
		myCards.add(newCard);
		seenCards.add(newCard);
	}
	
	public void addCard(String s, CardType c) {
		addCard(new Card(s,c));
	}
	
	public Color convertColor(String strColor) {
		 Color color;
		 try {
			 // We can use reflection to convert the string to a color
			 Field field = Class.forName("java.awt.Color").getField(strColor.trim());
		 	color = (Color)field.get(null);
		 } catch (Exception e) {
			 color = null; // Not defined
		 }
		 return color;
	}
	
	public Player(String playerName, int row, int column, Color color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = color;
	}
	
	public Player(String playerName, int row, int column, String color) {
		super();
		this.playerName = playerName;
		this.row = row;
		this.column = column;
		this.color = convertColor(color);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Color getColor() {
		return color;
	}

	public Set<Card> getMyCards() {
		return myCards;
	}

	public Set<Card> getSeenCards() {
		return seenCards;
	}
	
	public String getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void setDeck(Set<Card> inDeck) {
		deck = inDeck;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", row=" + row + ", column=" + column + ", color=" + color + "]";
	}

	public Solution createSuggestion() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void makeAccusation(Solution s) {
		
	}
	
	/*
	 * GUI Code Below
	 */
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(column*25, row*25, 25, 25);
		g.setColor(Color.black);
		g.drawOval(column*25, row*25, 25, 25);
	}

	public void setLocation(BoardCell selectedTarget) {
		// TODO Auto-generated method stub
		row = selectedTarget.getRow();
		column = selectedTarget.getCol();
		
	}
	
}
