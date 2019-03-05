/*
 * Authors: Allan MacDougall, Tyler Zudans
 */

package clueGame;

public class BadConfigFormatException extends Exception {

	/**
	 * Added SerialVersion cuz Eclipse was complaining.
	 */
	private static final long serialVersionUID = 1L;

	public BadConfigFormatException() {
		super("Incorrect File Formatting Detected");
		// TODO Auto-generated constructor stub
	}

	public BadConfigFormatException(String fileName) {
		super("Incorrect file formatting in" + fileName);
		// TODO Auto-generated constructor stub
	}

}
