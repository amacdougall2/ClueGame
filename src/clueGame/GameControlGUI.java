package clueGame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class GameControlGUI extends JFrame{
	
	public GameControlGUI() {
		setSize(new Dimension(700,300));
		setLayout(new GridLayout(2,3));
		setTitle("Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		JPanel turn = createTurnPanel();
		add(turn);
		JPanel nextPlayer = createButton("Next Player");
		add(nextPlayer);
		JPanel accuse = createButton("Make an Accusation");
		add(accuse);
		JPanel die = createBorderedPanel("Die","Roll:");
		add(die);
		JPanel guess = createBorderedPanel("Guess","Guess:");
		add(guess);
		JPanel result = createBorderedPanel("Guess Result","Response:");
		add(result);
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
	
	
	public static void main(String[] args) {
		GameControlGUI gui = new GameControlGUI();
		gui.setVisible(true);
	}
}
