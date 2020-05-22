package com.mfitz.javapoker.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    List<Card> deck;

    public ValueDict valueDict = new ValueDict();

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }


    public List<Card> makeDeck() {
        List<Card> deck = new ArrayList<>();
        for(int i = 0; i < 13; i++) {
            final int val = i;
//            final int[] rankingVal = {4 * val};
            Arrays.asList(Suit.values()).forEach(suit -> {
                Card card = new Card(val, suit);
                addDisplayValue(card);
                deck.add(card);
//                rankingVal[0]++;
            });
        }
        return deck;
    }

    public List<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public List<Card> dealHand(Integer numberOfCardsDealt, List<Card> deck) {
        List<Card> handDealt = new ArrayList<>();
        for(int i = 0; i < numberOfCardsDealt; i++) {
            Card card = deck.remove(i);
            handDealt.add(card);
        }
        return handDealt;
    }

    public List<Integer> getCardValues(List<Card> cards) {
        List<Integer> sortedCardValues = (cards.stream().map(card -> card.getValue()).collect(Collectors.toList()));
        Collections.sort(sortedCardValues);
        return sortedCardValues;
    }

    private void addDisplayValue(Card card) {
        card.setDisplayValue(valueDict.get(card.getValue()));
    }



}
