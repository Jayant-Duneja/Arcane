package com.arcane.expertise;

import com.arcane.character.adventurer.Adventurer;

public interface IExpertiseStrategy {

    void increaseCombatExpertise(Adventurer adventurer);

    void increaseSearchExpertise(Adventurer adventurer);
}
