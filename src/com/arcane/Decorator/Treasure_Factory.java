package com.arcane.Decorator;

public class Treasure_Factory {
    public static Treasure createObject(String className, Treasure treasure){
        switch(className){
            case "Armor":
                return new Armor(treasure);
            case "Elixir":
                return new Elixir(treasure);
            case "Ether":
                return new Ether(treasure);
            case "Gem":
                return new Gem(treasure);
            case "Portal":
                return new Portal(treasure);
            case "Sword":
                return new Sword(treasure);
            case "Potion":
                return new Potion(treasure);
            default:
                return null;
        }
    }
}
