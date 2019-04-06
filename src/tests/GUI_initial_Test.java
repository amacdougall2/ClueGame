package tests;

import javax.swing.JFrame;

import clueGame.*;

public class GUI_initial_Test extends JFrame{

	private Board board;
	
	public GUI_initial_Test() {
		setup();
	}
	
	private void setup() {
		board = Board.getInstance();
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");
		board.initialize();
		
		GUI_Setup();
	}
	
	private void GUI_Setup() {
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		add(this.board);
	}
	
	public static void main(String[] args) {
		GUI_initial_Test GUI = new GUI_initial_Test();
		GUI.setVisible(true);
	}
}
