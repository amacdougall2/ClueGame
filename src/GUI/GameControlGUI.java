package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import clueGame.Board;

public class GameControlGUI extends JPanel{
	private Board board;
	public static JTextField die = new JTextField(20);
	public static JTextField guess = new JTextField(30);
	public static JTextField result = new JTextField(20);
	public static JTextField player = new JTextField(20);
	
	
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
		JPanel die = createDiePanel("Die","Roll:");
		add(die);
		JPanel guess = createGuessPanel("Guess","Guess:");
		add(guess);
		JPanel result = createResultPanel("Guess Result","Response:");
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

	private JPanel createTurnPanel() {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel("Current Player:");
		player.setEditable(false);
		panel.add(title);
		panel.add(player);
		return panel;
	}
	
	private JPanel createDiePanel(String border, String label) {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel(label);
		die.setText(" ");
		die.setEditable(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(), border));
		panel.add(title);
		panel.add(die);
		return panel;
	}
	
	private JPanel createGuessPanel(String border, String label) {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(2,1));
		//JLabel title = new JLabel(label);
		guess.setText(" ");
		guess.setEditable(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(), border));
		//panel.add(title);
		panel.add(guess);
		return panel;
	}
	
	private JPanel createResultPanel(String border, String label) {
		JPanel panel = new JPanel();
		//panel.setLayout(new GridLayout(2,1));
		JLabel title = new JLabel(label);
		result.setText(" ");
		result.setEditable(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(), border));
		panel.add(title);
		panel.add(result);
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
			if (board.getCurrentPlayer().isHuman) {
				Accuser guess = new Accuser();
				guess.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "You cannot accuse until it is your turn!");
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		GameControlGUI gui = new GameControlGUI();
		gui.setVisible(true);
	}
}
