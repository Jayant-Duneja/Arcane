package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Sword extends Treasure_Decorator{
    Treasure treasure;
    public Sword(Treasure treasure){
        this.name="Sword";
        this.value=1100;
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
    public void update_adventurer_attributes(Adventurer adventurer){
        adventurer.setBaseCombatRoll(adventurer.getBaseCombatRoll()+1);
    }
    public int get_value(){
        return treasure.get_value() + this.value;
    }
}
