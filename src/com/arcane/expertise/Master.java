package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

public class Master implements IExpertiseStrategy{
    @Override
    public void increaseCombatExpertise(Adventurer adventurer) {
        adventurer.setCombatExpertiseBonus(3);
    }

    @Override
    public void increaseSearchExpertise(Adventurer adventurer){
        adventurer.setSearchExpertiseBonus(3);
    }
}
