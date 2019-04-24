package GUI;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.*;
import javax.swing.border.*;

import clueGame.Board;
import clueGame.Player;
import clueGame.Solution;

public class Accuser extends JDialog {
	Board board;
	JComboBox<String> two,four,six;
	public Accuser() {
		board = Board.getInstance();
		setSize(new Dimension(250,500));
		setLayout(new GridLayout(4,2));
		setTitle("Make an Accusation");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel one = new JLabel("Your Room");
		add(one);
		//JLabel two = new JLabel(board.getCurrentPlayer().getCurrentRoom());
		//add(two);
		two = getGuess3(board.getRooms());
		add(two);
		JLabel three = new JLabel("Person");
		add(three);
		four = getGuess(board.getPlayers());
		add(four);
		JLabel five = new JLabel("Weapon");
		add(five);
		six = getGuess2(board.getWeapons());
		add(six);
		JButton submit = new JButton("Submit");
		submit.addActionListener(new SubmitListener(two.getSelectedItem(),four.getSelectedItem(),six.getSelectedItem()));
		add(submit);
		JButton dispose = new JButton("Cancel");
		dispose.addActionListener(new close());
		add(dispose);
		
		
	}
	private JComboBox<String> getGuess(ArrayList<Player> arrayList) {
		// TODO Auto-generated method stub
		JComboBox<String> suggest = new JComboBox<String>();
		for(Player s:arrayList) {
			suggest.addItem(s.getPlayerName());
		}
		return suggest;
	}
	private JComboBox<String> getGuess2(Set<String> arrayList) {
		// TODO Auto-generated method stub
		JComboBox<String> suggest = new JComboBox<String>();
		for(String s:arrayList) {
			suggest.addItem(s);
		}
		return suggest;
	}
	private JComboBox<String> getGuess3(ArrayList<String> arrayList) {
		// TODO Auto-generated method stub
		JComboBox<String> suggest = new JComboBox<String>();
		for(String s:arrayList) {
			suggest.addItem(s);
		}
		return suggest;
	}
	public class SubmitListener implements ActionListener {
		String room,person,weapon;
		Board board;
		public SubmitListener( Object person,Object room, Object weapon) {
			this.room = room.toString();
			this.person=person.toString();
			this.weapon=weapon.toString();
			board=Board.getInstance();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			room = four.getSelectedItem().toString();
			person= two.getSelectedItem().toString();
			weapon= six.getSelectedItem().toString();
			board.handleAccusation(new Solution(room,person,weapon));
			// TODO Auto-generated method stub

		}

	}
	public class close implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}

	}

}
