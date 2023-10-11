package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Gem extends Treasure_Decorator{
    Treasure treasure;
    public Gem(Treasure treasure){
        this.name="Gem";
        this.value=1000;
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
        adventurer.setbaseCreatureCombatRoll(adventurer.getBaseCreatureCombatRoll()+1);
    }
}
