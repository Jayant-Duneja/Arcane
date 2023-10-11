package com.arcane.expertise;

public class Seasoned implements IExpertise {
    @Override
    public int combatBonus() {
        return 1;
    }

    @Override
    public int treasureBonus() {
        return 1;
    }

    @Override
    public int getId() {
        return 1;
    }
}
