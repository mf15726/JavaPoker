package com.mfitz.javapoker.rankings;

import com.mfitz.javapoker.card.Card;
import com.mfitz.javapoker.card.Deck;
import com.mfitz.javapoker.card.Suit;

import java.util.*;
import java.util.stream.Collectors;

public class RankAHand {

    private HandValues handValues = new HandValues();

    private Deck deckMethods = new Deck();


    public HashMap<String, Object> iterateThroughPossibleHands(List<Card> cards) {
        CombinationIterator handIterator = new CombinationIterator(cards, 5);
        Integer bestHandRank = 0;
        List<Card> bestHand = new ArrayList<>();
        while (handIterator.hasNext()) {
            final List<Card> handToBeEvaluated = handIterator.next();
            Integer handRank = rankHand(handToBeEvaluated);

            if (doesCurrentHandBeatBestHand(handRank, bestHandRank, handToBeEvaluated, bestHand)) {
                bestHandRank = handRank;
                bestHand = handToBeEvaluated;
            }
        }
        final HashMap<String, Object> returnedMap = new HashMap();
        returnedMap.put("Rank", bestHandRank);
        returnedMap.put("Used cards", bestHand);
        return returnedMap;
    }

    private Boolean doesCurrentHandBeatBestHand(Integer handRank, Integer bestHandRank, List<Card> handToBeEvaluated,
                                             List<Card> bestHand) {
        return handRank >= bestHandRank &&
                (deckMethods.getCardValues(handToBeEvaluated).stream().mapToInt(Integer::intValue).sum() >
                        deckMethods.getCardValues(bestHand).stream().mapToInt(Integer::intValue).sum());
    }

    public Integer rankHand(List<Card> cards) {
        Integer handRank = doesHandContainPairs(cards);
        if (doesHandContainFlush(cards)) {
            if (doesHandContainStraight(cards)) {
                if (deckMethods.getCardValues(cards).equals(Arrays.asList(8, 9, 10, 11, 12))) {
                    return 9;
                }
                else {
                    return 8;
                }
            }
            else {
                if (isHandBetterThanFlush(handRank)){
                    return handRank;
                }
                else {
                    return 5;
                }
            }
        }
        else if(doesHandContainStraight(cards)){
            if (isHandBetterThanFlush(handRank)){
                return handRank;
            }
            else {
                return 4;
            }
        }
        else {
            return handRank;
        }
    }

    private boolean isHandBetterThanFlush(Integer handRank) {
        return handRank > 5;
    }

    public Boolean doesHandContainFlush(List<Card> cards) {
        Suit suit = cards.get(0).getSuit();
        for(Card card: cards){
            if (card.getSuit() != suit){
                return false;
            }
        };
        return true;
    }

    public Boolean doesHandContainStraight(List<Card> cards) {
        List<Integer> sortedCardValues = deckMethods.getCardValues(cards);
        for(int i = 1; i < sortedCardValues.size(); i++){
            if (sortedCardValues.get(i) != sortedCardValues.get(i - 1) + 1){
                return false;
            }
        }
        return true;
    }

    public Integer doesHandContainPairs(List<Card> cards) {
        List<Integer> sortedCardValues = deckMethods.getCardValues(cards);
        Set<Integer> sortedCardValuesSet = new HashSet<>(sortedCardValues);
        Integer returnedVal = 0;
        if (sortedCardValuesSet.size() != 5) {
            returnedVal = evaluatePairedHand(sortedCardValues, sortedCardValuesSet);
        }
        return returnedVal;
    }

    public Integer evaluatePairedHand(List<Integer> cardValues, Set<Integer> valueSet) {
        List<Integer> repetitionList = valueSet.stream().map(value -> Collections.frequency(cardValues, value))
                                       .collect(Collectors.toList());
        Integer maxNumberOfRepeatedValues = Collections.max(repetitionList);
        if (maxNumberOfRepeatedValues == 2) {
            if (repetitionList.size() == 4) {
                return 1;
            }
            else {
                return 2;
            }
        }
        else if (maxNumberOfRepeatedValues == 3) {
            if (repetitionList.size() == 2) {
                return 6;
            }
            else {
                return 3;
            }
        }
        else {
            return 7;
        }
    }

}
