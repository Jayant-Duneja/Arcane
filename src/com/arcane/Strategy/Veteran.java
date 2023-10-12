package com.arcane.Strategy;

public class Veteran implements IExpertise {
    @Override
    public int combatBonus() {
        return 2;
    }

    @Override
    public int treasureBonus() {
        return 2;
    }

    @Override
    public int getId() {
        return 2;
    }
}
