package com.arcane.Factory;

import com.arcane.character.Character;
import com.arcane.character.creature.Creature;

public interface CharacterFactory {
    public Character createCharacter(String type, String displayName);
}
