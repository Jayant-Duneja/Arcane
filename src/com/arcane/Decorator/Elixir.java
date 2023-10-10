package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

public class Elixir extends Treasure_Decorator{
    Treasure treasure;
    public Elixir(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 500;
    }

    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Elixir";
        }
        else{
            return this.treasure.get_treasures() + ",Elixir";
        }
    }
    public void update_adventurer_attributes(Adventurer adventurer) {
        adventurer.setDodgeChance(adventurer.getDodgeChance()+10);
    }
}
