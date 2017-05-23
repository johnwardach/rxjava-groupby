package com.example;

import rx.Observable;
import rx.observables.GroupedObservable;

public interface DeckPartitioner {
	
	/**
	 * Receives cards one at a time, partitions them by suit
	 * @param observable cards (deck)
	 * @return GroupedObservable consisting of the suit string and observable card stream
	 */
	static Observable<GroupedObservable<String, Card>> groupCards(Observable<Card> deck) {
		return deck.groupBy(card -> card.getSuit());
		
	}

}
