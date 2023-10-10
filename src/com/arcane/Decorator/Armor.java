package com.arcane.Decorator;

public class Armor extends Treasure_Decorator{
    Treasure treasure;
    public Armor(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 800;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Armor";
        }
        else{
            return this.treasure.get_treasures() + ",Armor";
        }
    }
}
