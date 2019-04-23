package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import clueGame.Board;

public class GameControlGUI extends JPanel{
	private Board board;
	
	
	public GameControlGUI() {
		//setSize(new Dimension(1000,400));
		board = Board.getInstance();
		setLayout(new GridLayout(2,1));
		JPanel turn = createTurnPanel();
		add(turn);
		JPanel nextPlayer = createNextButton("Next Player");
		add(nextPlayer);
		JPanel accuse = createMakeButton("Make an Accusation");
		add(accuse);
		JPanel die = createBorderedPanel("Die","Roll:");
		add(die);
		JPanel guess = createBorderedPanel("Guess","Guess:");
		add(guess);
		JPanel result = createBorderedPanel("Guess Result","Response:");
		add(result);
		/*JPanel topPanel = createTopPanel();
		add(topPanel);
		JPanel botPanel = createBottomPanel();
		add(botPanel);*/
	}
	
	private JPanel createMakeButton(String string) {
		JButton button = new JButton(string);
		button.addActionListener(new accuseListener());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(button);
		return panel;
	}

	private JPanel createTopPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,0));
		JPanel turn = createTurnPanel();
		panel.add(turn);
		JPanel nextPlayer = createNextButton("Next Player");
		panel.add(nextPlayer);
		JPanel accuse = createButton("Make an Accusation");
		panel.add(accuse);
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,0));
		JPanel die = createBorderedPanel("Die","Roll:");
		add(die);
		JPanel guess = createBorderedPanel("Guess","Guess:");
		add(guess);
		JPanel result = createBorderedPanel("Guess Result","Response:");
		add(result);
		return panel;
	}

	private JPanel createTurnPanel() {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel("Current Player:");
		JTextField outText = new JTextField(10);
		outText.setEditable(false);
		panel.add(title);
		panel.add(outText);
		return panel;
	}
	
	private JPanel createBorderedPanel(String border, String label) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(30,30));
		//panel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel(label);
		JTextField outText = new JTextField(10);
		outText.setText(" ");
		outText.setEditable(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(), border));
		panel.add(title);
		panel.add(outText);
		return panel;
	}
	
	private JPanel createButton(String input) {
		JButton button = new JButton(input);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(button);
		return panel;
	}
	private JPanel createNextButton(String input) {
		JButton button = new JButton(input);
		button.addActionListener(new nextListener());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		panel.add(button);
		return panel;
	}
	class nextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			board.nextPlayer();
			
		}
		
	}
	class accuseListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Guesser guess = new Guesser();
			guess.setVisible(true);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		GameControlGUI gui = new GameControlGUI();
		gui.setVisible(true);
	}
}
