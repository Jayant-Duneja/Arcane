package com.arcane.Factory;

import com.arcane.character.Character;
import com.arcane.character.creature.*;

public class CreatureFactory implements CharacterFactory{
    @Override
    public Creature createCharacter(String type, String displayName) {
        switch (type) {
            case "A" -> {
                return new Aquarid();
            }
            case "F" -> {
                return new FireBorn();
            }
            case "T" -> {
                return new TerraVore();
            }
            case "Z" -> {
                return new Zephyral();
            }
            default -> {
                System.out.println("Pass a valid name");
                return null;
            }
        }
    }
}
