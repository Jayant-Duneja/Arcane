package com.arcane.Decorator;

import com.arcane.character.adventurer.Adventurer;

import java.util.List;

abstract public class Treasure{
    public String getName(){return "";}
    public int get_value(){
        return 0;
    }
    public String get_treasures(){ return "";}
    public void update_adventurer_attributes(Adventurer adventurer){
    }
}
