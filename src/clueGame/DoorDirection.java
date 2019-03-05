/*
 * Authors: Allan MacDougall, Tyler Zudans
 * Note: To get the failed tests, simply remove implementations from successful test Classes.
 */
package clueGame;

public enum DoorDirection {

	UP('U'),DOWN('D'),LEFT('L'),RIGHT('R'),NONE(' ');
	private char side;
	
	private DoorDirection(char input) {
		this.side = input;
	}
	
	public String toString() {
		return "" + side;
	}
	
}
