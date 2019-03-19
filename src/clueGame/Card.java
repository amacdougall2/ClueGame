package clueGame;

public class Card {
	private String cardName;
	private CardType type;
	
	public boolean equals(Card other) {
		return false;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}
}
