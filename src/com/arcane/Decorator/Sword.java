package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Sword extends Treasure_Decorator{
    Treasure treasure;
    public Sword(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 1100;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Sword";
        }
        else{
            return this.treasure.get_treasures() + ",Sword";
        }
    }
    public void update_adventurer_attributes(Adventurer adventurer){
        adventurer.setBaseCombatRoll(adventurer.getBaseCombatRoll()+1);
    }
}
