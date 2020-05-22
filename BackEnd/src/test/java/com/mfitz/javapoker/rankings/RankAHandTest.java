package com.mfitz.javapoker.rankings;

import com.mfitz.javapoker.card.Card;
import com.mfitz.javapoker.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RankAHandTest {

    private RankAHand rankAHand = new RankAHand();

    List<Card> royalFlushHandAndBoard = Arrays.asList(new Card(8, Suit.Spade), new Card(9, Suit.Spade),
            new Card(10, Suit.Spade), new Card(11, Suit.Spade), new Card(12, Suit.Spade),
            new Card(1, Suit.Club), new Card(2,Suit.Heart));

    List<Card> flushHandAndBoard = Arrays.asList(new Card(6, Suit.Spade), new Card(9, Suit.Spade),
            new Card(10, Suit.Spade), new Card(11, Suit.Spade), new Card(12, Suit.Spade),
            new Card(1, Suit.Club), new Card(2,Suit.Heart));

    List<Card> straightHandAndBoard = Arrays.asList(new Card(8, Suit.Heart), new Card(9, Suit.Spade),
            new Card(10, Suit.Spade), new Card(11, Suit.Spade), new Card(12, Suit.Spade),
            new Card(1, Suit.Club), new Card(2,Suit.Heart));

    List<Card> fullHouseHandAndBoard = Arrays.asList(new Card(1, Suit.Heart), new Card(1, Suit.Spade),
            new Card(3, Suit.Heart), new Card(3, Suit.Spade), new Card(3, Suit.Club),
            new Card(1, Suit.Club), new Card(2,Suit.Heart));

    List<Card> royalFlushHand = Arrays.asList(new Card(8, Suit.Spade), new Card(9, Suit.Spade),
            new Card(10, Suit.Spade), new Card(11, Suit.Spade), new Card(12, Suit.Spade));

    List<Card> straightFlushHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(4, Suit.Spade));

    List<Card> flushHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(6, Suit.Spade));

    List<Card> straightHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(4, Suit.Club));

    List<Card> pairHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(3, Suit.Club));

    List<Card> twoPairHand = Arrays.asList(new Card(0, Suit.Spade), new Card(2, Suit.Club),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(3, Suit.Club));

    List<Card> tripsHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(3, Suit.Heart), new Card(3, Suit.Spade), new Card(3, Suit.Club));

    List<Card> quadsHand = Arrays.asList(new Card(0, Suit.Spade), new Card(3, Suit.Diamond),
            new Card(3, Suit.Heart), new Card(3, Suit.Spade), new Card(3, Suit.Club));

    List<Card> fullHouseHand = Arrays.asList(new Card(1, Suit.Heart), new Card(1, Suit.Spade),
            new Card(3, Suit.Heart), new Card(3, Suit.Spade), new Card(3, Suit.Club));

    List<Card> highCardHand = Arrays.asList(new Card(0, Suit.Spade), new Card(1, Suit.Spade),
            new Card(2, Suit.Spade), new Card(3, Suit.Spade), new Card(5, Suit.Club));



    @Test
    @DisplayName("doesHandDetectFlush should detect flushes")
    void testDoesHandDetectFlushSuccess() {
        assert rankAHand.doesHandContainFlush(straightFlushHand);
        assert rankAHand.doesHandContainFlush(royalFlushHand);
        assert rankAHand.doesHandContainFlush(flushHand);
    }

    @Test
    @DisplayName("doesHandDetectFlush should fail when flush isn't there")
    void testDoesHandDetectFlushFailure() {
        assert !rankAHand.doesHandContainFlush(straightHand);
        assert !rankAHand.doesHandContainFlush(pairHand);
        assert !rankAHand.doesHandContainFlush(quadsHand);
    }

    @Test
    @DisplayName("doesHandDetectStraight should detect straights")
    void testDoesHandDetectStraightSuccess() {
        assert rankAHand.doesHandContainStraight(straightHand);
        assert rankAHand.doesHandContainStraight(royalFlushHand);
        assert rankAHand.doesHandContainStraight(straightFlushHand);
    }

    @Test
    @DisplayName("doesHandDetectStraight should fail when no straight available")
    void testDoesHandDetectStraightFailure() {
        assert !rankAHand.doesHandContainStraight(pairHand);
        assert !rankAHand.doesHandContainStraight(flushHand);
        assert !rankAHand.doesHandContainStraight(twoPairHand);
    }

    @Test
    @DisplayName("doesHandContainPairs detects pairs successfully")
    void testDoesHandContainPairs() {
        assert rankAHand.doesHandContainPairs(highCardHand) == 0;
        assert rankAHand.doesHandContainPairs(pairHand) == 1;
        assert rankAHand.doesHandContainPairs(twoPairHand) == 2;
        assert rankAHand.doesHandContainPairs(tripsHand) == 3;
        assert rankAHand.doesHandContainPairs(fullHouseHand) == 6;
        assert rankAHand.doesHandContainPairs(quadsHand) == 7;
    }

    @Test
    @DisplayName("rankHand works as designed")
    void testRankHand() {
        assert rankAHand.rankHand(highCardHand) == 0;
        assert rankAHand.rankHand(pairHand) == 1;
        assert rankAHand.rankHand(twoPairHand) == 2;
        assert rankAHand.rankHand(tripsHand) == 3;
        assert rankAHand.rankHand(straightHand) == 4;
        assert rankAHand.rankHand(flushHand) == 5;
        assert rankAHand.rankHand(fullHouseHand) == 6;
        assert rankAHand.rankHand(quadsHand) == 7;
        assert rankAHand.rankHand(straightFlushHand) == 8;
        assert rankAHand.rankHand(royalFlushHand) == 9;
    }

    @Test
    @DisplayName("iterateThroughPossibleHands works for Holdem")
    void testIterateThroughPossibleHands() {
        assert (Integer) rankAHand.iterateThroughPossibleHands(straightHandAndBoard).get("Rank") == 4;
        assert (Integer) rankAHand.iterateThroughPossibleHands(flushHandAndBoard).get("Rank") == 5;
        assert (Integer) rankAHand.iterateThroughPossibleHands(fullHouseHandAndBoard).get("Rank") == 6;
        assert (Integer) rankAHand.iterateThroughPossibleHands(royalFlushHandAndBoard).get("Rank") == 9;

    }

}
