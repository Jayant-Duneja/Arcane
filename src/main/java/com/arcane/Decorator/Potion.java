package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Potion extends Treasure_Decorator{
    Treasure treasure;
    public Potion(Treasure treasure){
        this.name="Potion";
        this.value=500;
        this.treasure=treasure;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return this.name;
        }
        else{
            return this.treasure.get_treasures() + "," + this.name;
        }
    }
    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setHealth(adventurer.getHealth() + 1);
    }
    public int get_value(){
        return treasure.get_value() + this.value;
    }
}
