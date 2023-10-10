package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Ether extends Treasure_Decorator{
    Treasure treasure;
    public Ether(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 900;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Ether";
        }
        else{
            return this.treasure.get_treasures() + ",Ether";
        }
    }
    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setBaseTreasureRoll(adventurer.getBaseTreasureRoll()+1);
    }
}
