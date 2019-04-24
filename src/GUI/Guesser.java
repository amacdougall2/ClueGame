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

public class Guesser extends JDialog {
	Board board;
	public Guesser() {
		board = Board.getInstance();
		setSize(new Dimension(250,500));
		setLayout(new GridLayout(4,2));
		setTitle("Make a Suggestion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel one = new JLabel("Your Room");
		add(one);
		JLabel two = new JLabel(board.getCurrentPlayer().getCurrentRoom());
		add(two);
		JLabel three = new JLabel("Person");
		add(three);
		JComboBox<String> four = getGuess(board.getPlayers());
		add(four);
		JLabel five = new JLabel("Weapon");
		add(five);
		JComboBox<String> six = getGuess2(board.getWeapons());
		add(six);
		JButton submit = new JButton("Submit");
		submit.addActionListener(new SubmitListener(two.getText(),four.getName(),six.getName()));
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
	public class SubmitListener implements ActionListener {
		String room,person,weapon;
		Board board;
		public SubmitListener(String room, String person, String weapon) {
			this.room = room;
			this.person=person;
			this.weapon=weapon;
			board=Board.getInstance();
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			board.handleSuggestion(new Solution(person,room,weapon), board.getCurrentPlayer());
			dispose();
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
