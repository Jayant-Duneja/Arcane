package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

public class Veteran implements IExpertiseStrategy{
    @Override
    public void increaseCombatExpertise(Adventurer adventurer) {
        adventurer.setCombatExpertiseBonus(2);
    }

    @Override
    public void increaseSearchExpertise(Adventurer adventurer){
        adventurer.setSearchExpertiseBonus(2);
    }

}
