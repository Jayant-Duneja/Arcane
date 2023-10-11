package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

public class Novice implements IExpertiseStrategy{

    @Override
    public void increaseCombatExpertise(Adventurer adventurer) {
        adventurer.setCombatExpertiseBonus(0);
    }

    @Override
    public void increaseSearchExpertise(Adventurer adventurer){
        adventurer.setSearchExpertiseBonus(0);
    }

}
