package com.arcane.util;

public class ReturnType {
    public Boolean is_game_over;
    public int numCreatures;
    public int currHealth;
    public int numTreasures;

    public ReturnType(Boolean is_game_over, int numCreatures, int currHealth, int numTreasures) {
        this.is_game_over=is_game_over;
        this.numCreatures=numCreatures;
        this.currHealth=currHealth;
        this.numTreasures=numTreasures;
    }
}

