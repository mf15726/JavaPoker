package com.mfitz.javapoker.rankings;

import java.util.HashMap;

public class HandValues {

    HashMap handValues;

    public HandValues() {

        HashMap<Integer, HandNames> handValues = new HashMap<>();

        handValues.put(0, HandNames.HighCard);
        handValues.put(1, HandNames.Pair);
        handValues.put(2, HandNames.TwoPair);
        handValues.put(3, HandNames.ThreeOfAKind);
        handValues.put(4, HandNames.Straight);
        handValues.put(5,HandNames.Flush);
        handValues.put(6, HandNames.FullHouse);
        handValues.put(7, HandNames.FourOfAKind);
        handValues.put(8, HandNames.StraightFlush);
        handValues.put(9, HandNames.RoyalFlush);

        this.handValues = handValues;

    }

    public String get(Integer value){
        return handValues.get(value).toString();
    }

}
