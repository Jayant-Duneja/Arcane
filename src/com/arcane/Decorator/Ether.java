package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Ether extends Treasure_Decorator{
    Treasure treasure;
    public Ether(Treasure treasure){
        this.name="Ether";
        this.value=900;
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
    public int get_value(){
        return treasure.get_value() + this.value;
    }
    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setBaseTreasureRoll(adventurer.getBaseTreasureRoll()+1);
    }
}
