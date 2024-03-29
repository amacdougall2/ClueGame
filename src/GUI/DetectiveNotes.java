package GUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;

public class DetectiveNotes extends JDialog {
	Board board;
	Set<String> players, rooms, weapons;
	Map<String,Boolean> seen;//Clicked
	Set<String> playerGuess,roomGuess,weaponGuess;
	JPanel pBox,pGuess,rBox,rGuess,wBox,wGuess;
	
	public static void main(String[] args) {
		DetectiveNotes d = new DetectiveNotes();
		d.setVisible(true);
	}
	public DetectiveNotes() {
		boardInit();
		setSize(new Dimension(500,1000));
		setLayout(new GridLayout(3,2));
		setTitle("Detective Notes");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//Set up player checkboxes and dropdown
		pBox = getOptions(players,"People");
		add(pBox);
		pGuess = getGuess(playerGuess,"Person Guess");
		add(pGuess);
		
		//Rooms
		rBox = getOptions(rooms,"Rooms");
		add(rBox);
		rGuess = getGuess(roomGuess,"Room Guess");
		add(rGuess);
		
		//Weapons
		wBox = getOptions(weapons,"Weapons");
		add(wBox);
		wGuess = getGuess(weaponGuess,"Weapon Guess");
		add(wGuess);
		
		
	}
	private JPanel getGuess(Set<String> playerGuess2, String string) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		JComboBox<String> suggest = new JComboBox<String>();
		for(String s:playerGuess2) {
			suggest.addItem(s);
		}
		panel.add(suggest);
		panel.setBorder(new TitledBorder(new EtchedBorder(),string));
		return panel;
	}
	public void updateSeen() {
		playerGuess.clear();
		roomGuess.clear();
		weaponGuess.clear();
		
		//unseen players added
		for(String p:players) {
			if(seen.get(p)==false) {
				playerGuess.add(p);
			}
		}
		//unseen rooms added
		for(String r:rooms) {
			if(seen.get(r)==false) {
				roomGuess.add(r);
			}
		}
		//unseen weapons added
		for(String w:weapons) {
			if(seen.get(w)==false) {
				weaponGuess.add(w);
			}
		}
	}
	public void updateBoxes() {
		pGuess = getGuess(playerGuess,"Person Guess");
		rGuess = getGuess(roomGuess,"Room Guess");
		wGuess = getGuess(weaponGuess,"Weapon Guess");
		
	}
	public void updateDisplay(){
		updateSeen();
		updateBoxes();
		
	}
		
		
	private JPanel getOptions(Set<String> box, String string) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		for(String s: box) {
			JCheckBox b = new JCheckBox(s);
			if(seen.containsKey(s)) {
				b.setSelected(seen.get(s));
			}
			b.addActionListener(new boxListener(s));
			panel.add(b);
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(),string));
		return panel;
	}

	private void boardInit() {// populates sets/comboboxes from board
		//Set up board
		board = Board.getInstance();
		//board.setConfigFiles("ClueRooms.csv", "ClueRooms.txt", "Names.txt", "Weapons.txt");
		//board.initialize();
		
		//Create empty guessSets
		playerGuess= new HashSet<String>();
		roomGuess= new HashSet<String>();
		weaponGuess= new HashSet<String>();
		
		
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
		
		if(board.getSeen()==null) {
			seen = new HashMap<String,Boolean>();
		}
		else {
			seen = board.getSeen();
			System.out.println("Seen loaded");
		}
		for(String p:players)seen.put(p, false);
		for(String r:rooms)seen.put(r, false);
		for(String w:weapons)seen.put(w, false);
		
		//update
		updateDisplay();
		
	}
	private class boxListener implements ActionListener{
		String name;
		public boxListener(String string) {
			// TODO Auto-generated constructor stub
			name=string;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			//change name seen to opposite state
			seen.replace(name, !(seen.get(name)));
			//copy seen to board
			board.setSeen(seen);
			updateDisplay();
			
		}
		
	}
	
}
