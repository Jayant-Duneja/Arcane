package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Elixir extends Treasure_Decorator{
    Treasure treasure;
    public Elixir(Treasure treasure){
        this.name="Elixir";
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
    public int get_value(){
        return treasure.get_value() + this.value;
    }

    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setDodgeChance(adventurer.getDodgeChance()+10);
    }
}
