package com.mfitz.javapoker.player;

public class Player {

    String playerName;
    Integer stackSize;
    Integer tablePosition;

    public Player(String playerName, Integer stackSize, Integer tablePosition) {
        this.playerName = playerName;
        this.stackSize = stackSize;
        this.tablePosition = tablePosition;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getStackSize() {
        return stackSize;
    }

    public void setStackSize(Integer stackSize) {
        this.stackSize = stackSize;
    }

    public Integer getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(Integer tablePosition) {
        this.tablePosition = tablePosition;
    }
}
