package com.arcane.Strategy;

public class Master implements IExpertise {
    @Override
    public int combatBonus() {
        return 3;
    }
    @Override
    public int treasureBonus() {
        return 3;
    }
    @Override
    public int getId() {
        return 3;
    }
}
