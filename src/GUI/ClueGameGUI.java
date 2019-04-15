/*
 * Authors: Allan MacDougal and Tyler Zudans
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clueGame.*;

public class ClueGameGUI extends JFrame{

	private Board board;
	private JMenu fileMenu;
	private JMenuBar bar;
	private GameControlGUI controls;
	private MyCards hand;
	private JOptionPane popup;
	
	public ClueGameGUI() {
		setup();
	}
	
	private void setup() {
		//initialize board
		board = Board.getInstance();
		controls = new GameControlGUI();
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");
		board.initialize();
		
		//set up menu bar
		bar = new JMenuBar();
		popup = new JOptionPane();
		popup.showMessageDialog(this,"Welcome to Clue!", "ClueGame", JOptionPane.INFORMATION_MESSAGE);
		fileMenu=new JMenu("File");
		fileMenu.add(openDetective());
		fileMenu.add(exit());
		bar.add(fileMenu);
		hand = new MyCards(board.getPlayers().get(0));
	
		GUI_Setup();
	}
	
	private JMenuItem openDetective() {//open detective notes upon selection from menu
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
		setSize(800, 800);
		add(this.board, BorderLayout.CENTER);
		add(bar,BorderLayout.NORTH);
		add(controls, BorderLayout.SOUTH);
		add(hand, BorderLayout.EAST);
		

	}
	
	public static void main(String[] args) {
		ClueGameGUI GUI = new ClueGameGUI();
		GUI.setVisible(true);
	}
}
