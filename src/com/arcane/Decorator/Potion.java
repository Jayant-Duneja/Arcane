package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Potion extends Treasure_Decorator{
    Treasure treasure;
    public Potion(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 500;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Potion";
        }
        else{
            return this.treasure.get_treasures() + ",Potion";
        }
    }
    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setHealth(adventurer.getHealth() + 1);
    }
}
