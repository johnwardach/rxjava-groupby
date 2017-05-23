package com.example;

public class Card {
	
	private final String value;
	private final String suit;
	
	public Card(String value, String suit) {
		super();
		this.value = value;
		this.suit = suit;
	}
	
	public String getValue() {
		return value;
	}
	public String getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return "Card [value=" + value + ", suit=" + suit + "]";
	}

}
