/*
 * Authors: Allan MacDougal and Tyler Zudans
 */
package tests;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import clueGame.*;

public class ClueGameGUI extends JFrame{

	private Board board;
	private JMenu fileMenu;
	private JMenuBar bar;
	
	public ClueGameGUI() {
		setup();
	}
	
	private void setup() {
		//initialize board
		board = Board.getInstance();
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");
		board.initialize();
		
		//set up menu bar
		bar = new JMenuBar();
		fileMenu=new JMenu("File");
		fileMenu.add(openDetective());
		fileMenu.add(exit());
		bar.add(fileMenu);
		
	
		GUI_Setup();
	}
	
	private JMenuItem openDetective() {//open detectivenotes upon selection from menu
		JMenuItem item = new JMenuItem("Detective Notes");
		class DetectiveOpener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				DetectiveNotes dNotes = new DetectiveNotes();
				dNotes.setVisible(true);
			}
			
		}
		item.addActionListener(new DetectiveOpener());
		return item;
	}
	private JMenuItem exit() {//exit upon selection from menu
		JMenuItem item = new JMenuItem("Exit");
		class DetectiveOpener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		}
		item.addActionListener(new DetectiveOpener());
		return item;
	}

	private void GUI_Setup() {
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1000);
		add(this.board);
		add(bar,BorderLayout.NORTH);

	}
	
	public static void main(String[] args) {
		ClueGameGUI GUI = new ClueGameGUI();
		GUI.setVisible(true);
	}
}
