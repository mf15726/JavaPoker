package com.mfitz.javapoker.card;

public class Card {

    Integer value;
    Suit suit;
    String displayValue;
    Integer rankValue;

    public Card(Integer value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

//    public Integer getRankValue() {
//        return rankValue;
//    }
//
//    public void setRankValue(Integer rankValue) {
//        this.rankValue = rankValue;
//    }
}
