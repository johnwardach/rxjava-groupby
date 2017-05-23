package com.example;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Test;

import rx.Observable;
import rx.observables.GroupedObservable;

public class DeckPartitionerTest {
	
	Map<String, List<Card>> partitionedCards = new HashMap<String, List<Card>>();

	@Test
	public void testCreateCardDeck() {
		Card card1 = new Card("2","hearts");
		Card card2 = new Card("3","hearts");
		Card card3 = new Card("10","spades");
		Card card4 = new Card("jack","hearts");
		Card card5 = new Card("five","spades");
		Card card6 = new Card("king","clubs");
		Card card7 = new Card("four","diamonds");
		Card card8 = new Card("2","spades");

		
		Observable<Card> deck = Observable
				.from(new ArrayList<Card>(Arrays.asList(card1, card2, card3, card4, card5, card6, card7, card8)));
		
		Observable<GroupedObservable<String, Card>> deckObservable = DeckPartitioner.groupCards(deck);
		
		deckObservable.subscribe(card -> {
			
			card.subscribe(s-> {
				if (!this.partitionedCards.containsKey(s.getSuit())){
					this.partitionedCards.put(s.getSuit(), new ArrayList<Card>());
				}
				this.partitionedCards.get(s.getSuit()).add(s);
			});
		});
	}
	
	
	@After
	public void suitGroupsShouldHaveCorrectNumberOfCards() {
		System.out.println(this.partitionedCards);
		assertTrue(this.partitionedCards.get("hearts").size() == 3);
		assertTrue(this.partitionedCards.get("spades").size() == 3);
		assertTrue(this.partitionedCards.get("diamonds").size() == 1);
		assertTrue(this.partitionedCards.get("clubs").size() == 1);
	}
	
}
