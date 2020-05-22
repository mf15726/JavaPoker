package com.mfitz.javapoker.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeckTest {

    Deck deckMethods = new Deck();

    List<Card> deck = deckMethods.makeDeck();

    @Test
    @DisplayName("Deck should have 52 cards")
    void testNumberOfCardsInDeck() {
        assert deck.size() == 52;
    }

    @Test
    @DisplayName("There should be 13 of a suit")
    void testSuits() {
        final Integer[] spadeCount = {0};
        deck.forEach(card -> {
            if (card.getSuit() == Suit.Spade) {
                spadeCount[0]++;
            }
        });
        assert spadeCount[0] == 13;
    }

    @Test
    @DisplayName("There should be 4 of each type of card")
    void testValue() {
        final Integer[] aceCount = {0};
        final Integer[] twoCount = {0};
        final Integer[] sevenCount = {0};
        deck.forEach(card -> {
            if (card.getValue() == 0) {
                twoCount[0]++;
            }
            if (card.getValue() == 5) {
                sevenCount[0]++;
            }
            if (card.getValue() == 12) {
                aceCount[0]++;
            }
        });
        assert twoCount[0] == 4;
        assert aceCount[0] == 4;
        assert sevenCount[0] == 4;
    }

    @Test
    @DisplayName("DisplayNames should map correctly")
    void testValueDict() {
        deck.forEach(card -> {
            if (card.getValue() < 9) {
                final Integer cardValue = card.getValue() + 2;
                assert card.getDisplayValue().equals(cardValue.toString());
            }
            else {
                if (card.getValue() == 9) {
                    assert card.getDisplayValue() == "J";
                }
                else if (card.getValue() == 10) {
                    assert card.getDisplayValue() == "Q";
                }
                else if (card.getValue() == 11) {
                    assert card.getDisplayValue() == "K";
                }
                else {
                    assert card.getDisplayValue() == "A";
                }
            }
        });
    }

    @Test
    @DisplayName("Deck should not be the same after shuffling")
    void testShuffle() {
        List<Card> originalDeck = new ArrayList<>(deck);
        List<Card> shuffledDeck = deckMethods.shuffle(deck);
        assert !originalDeck.equals(shuffledDeck);
    }

    @Test
    @DisplayName("Dealing should result in less than 52 in the deck")
    void testHandDealing() {
        List<Card> dealthand = deckMethods.dealHand(2, deck);
        assert(deck.size() == 50);
    }

    @Test
    @DisplayName("Dealt cards should not appear in the deck")
    void testHandNotInDeck() {
        List<Card> dealthand = deckMethods.dealHand(2, deck);
        dealthand.forEach(card -> {
            assert !deck.contains(card);
        });
    }

    @Test
    @DisplayName("The getCardValues method should return a sorted list of values")
    void testGetCardValues() {
        List<Card> dealthand = deckMethods.dealHand(2, deck);
        List<Integer> sortedHand = deckMethods.getCardValues(dealthand);
        assert sortedHand.get(0) <= sortedHand.get(1);
    }


}
