package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

public class Seasoned implements IExpertiseStrategy{
    @Override
    public void increaseCombatExpertise(Adventurer adventurer) {
        adventurer.setCombatExpertiseBonus(1);
    }

    @Override
    public void increaseSearchExpertise(Adventurer adventurer){
        adventurer.setSearchExpertiseBonus(1);
    }
}
