//Authors: Allan MacDougall, Tyler Zudans
package clueGame;

public enum CardType {

	Person('P'),Weapon('W'),Room('R');
	private char symbol;
	
	private CardType(char input) {
		this.symbol = input;
	}
	
	public String toString() {
		return "" + symbol;
	}
}
