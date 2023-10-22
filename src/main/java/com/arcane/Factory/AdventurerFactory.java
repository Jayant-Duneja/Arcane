package com.arcane.Factory;

import com.arcane.character.adventurer.*;
import com.arcane.character.creature.Creature;

public class AdventurerFactory implements CharacterFactory{
    @Override
    public Adventurer createCharacter(String type, String displayName) {
        switch (type) {
            case "EK" -> {
                return new EmberKnight(displayName);
            }
            case "MW" -> {
                return new MistWalker(displayName);
            }
            case "TV" -> {
                return new TerraVoyager(displayName);
            }
            case "ZR" -> {
                return new ZephyrRogue(displayName);
            }
            default -> {
                System.out.println("Pass a valid name");
                return null;
            }
        }
    }
}
