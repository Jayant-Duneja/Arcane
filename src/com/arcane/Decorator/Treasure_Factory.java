package com.arcane.Decorator;

public class Treasure_Factory {
    public static Treasure createObject(String className, Treasure treasure){
        switch(className){
            case "Armor":
                System.out.println("Hello. I am in class: " + className);
                return new Armor(treasure);
            case "Elixir":
                System.out.println("Hello. I am in class: " + className);
                return new Elixir(treasure);
            case "Ether":
                System.out.println("Hello. I am in class: " + className);
                return new Ether(treasure);
            case "Gem":
                System.out.println("Hello. I am in class: " + className);
                return new Gem(treasure);
            case "Portal":
                System.out.println("Hello. I am in class: " + className);
                return new Portal(treasure);
            case "Sword":
                System.out.println("Hello. I am in class: " + className);
                return new Sword(treasure);
            case "Potion":
                System.out.println("Hello. I am in class: " + className);
                return new Potion(treasure);
            default:
                System.out.println("Hello. I am in default");
                return null;
        }
    }
}
