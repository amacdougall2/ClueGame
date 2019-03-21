package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Set;

public class Player {
	

	private String playerName;
	private int row;
	private int column;
	private Color color;
	private Set<Card> myCards;
	private Set<Card> seenCards;
	
	public Card disproveSuggestion(Solution suggestion) {
		return null;
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

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", row=" + row + ", column=" + column + ", color=" + color + "]";
	}
	
}
