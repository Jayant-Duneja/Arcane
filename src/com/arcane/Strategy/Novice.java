package com.arcane.Strategy;

public class Novice implements IExpertise {
    @Override
    public int combatBonus() {
        return 0;
    }

    @Override
    public int treasureBonus() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }
}