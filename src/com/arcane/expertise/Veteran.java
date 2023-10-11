package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

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
