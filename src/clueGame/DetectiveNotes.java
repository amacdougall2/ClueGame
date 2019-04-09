package clueGame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

public class DetectiveNotes extends JFrame {
	Board board;
	Set<String> players, rooms, weapons;
	Map<String,Boolean> seen;
	Set<String> playerGuess,roomGuess,weaponGuess;
	
	public static void main(String[] args) {
		DetectiveNotes d = new DetectiveNotes();
		d.setVisible(true);
	}
	public DetectiveNotes() {
		boardInit();
		setSize(new Dimension(800,1200));
		setLayout(new GridLayout(3,2));
		setTitle("Detective Notes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pBox = getOptions(players,"People");
		add(pBox);
		JPanel rBox = getOptions(rooms,"Rooms");
		add(rBox);
		JPanel wBox = getOptions(weapons,"Weapons");
		add(wBox);
		
		
	}
	

	private JPanel getOptions(Set<String> box, String string) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		for(String s: box) {
			JCheckBox b = new JCheckBox(s);
			panel.add(b);
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(),string));
		return panel;
	}

	private void boardInit() {// populates sets/comboboxes from board
		//Set up board
		board = Board.getInstance();
		board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");
		board.initialize();
		
		
		//Create players
		players = new HashSet<String>();
		for(Player p:board.getPlayers()) players.add(p.getPlayerName());// add playername to set
		
		//Create rooms
		rooms = new HashSet<String>();
		for(String r:board.legend.values())rooms.add(r);
		
		//Create weapons
		weapons = new HashSet<String>();
		for(Card c:board.getDeck()) if(c.getType()==CardType.Weapon)weapons.add(c.getCardName());
		
		//initialize Map default none seen
		for(String p:players)seen.put(p, false);
		for(String r:rooms)seen.put(r, false);
		for(String w:weapons)seen.put(w, false);
		
	}
	
}
