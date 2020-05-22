package com.mfitz.javapoker.card;

import java.util.HashMap;

public class ValueDict {

    HashMap valueDict;

    public ValueDict() {

        HashMap<Integer, String> valueDict = new HashMap<>();

        valueDict.put(0, "2");
        valueDict.put(1, "3");
        valueDict.put(2, "4");
        valueDict.put(3, "5");
        valueDict.put(4, "6");
        valueDict.put(5, "7");
        valueDict.put(6, "8");
        valueDict.put(7, "9");
        valueDict.put(8, "10");
        valueDict.put(9, "J");
        valueDict.put(10, "Q");
        valueDict.put(11, "K");
        valueDict.put(12, "A");

        this.valueDict = valueDict;

    }

    public String get(Integer value){
        return valueDict.get(value).toString();
    }


}
