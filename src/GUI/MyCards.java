package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.*;

public class MyCards extends JPanel{
	private JPanel rooms = new JPanel();
	private JPanel weapons = new JPanel();
	private JPanel people = new JPanel();
	
	public MyCards(Player p) {
		fillCards(p);
		setLayout(new GridLayout(3,1));
		rooms.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		weapons.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		people.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		add(people);
		add(rooms);
		add(weapons);
		setBorder(new TitledBorder(new EtchedBorder(), "Cards"));

	}
	
	
	private void fillCards(Player player) {
		for (Card c: player.getMyCards()) {
			JPanel cardPanel = createCard(c);
			if(c.getType() == CardType.Person) {
				people.add(cardPanel);
			}else if(c.getType() == CardType.Room) {
				rooms.add(cardPanel);
			}else if(c.getType() == CardType.Weapon) {
				weapons.add(cardPanel);
			}
		}
	}
	
	private JPanel createCard(Card card) {
		JPanel panel = new JPanel();
		JLabel title = new JLabel(card.getCardName());
		panel.add(title);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder());
		return panel;
	}
}


