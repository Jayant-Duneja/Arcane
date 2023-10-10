package com.arcane.Decorator;

public class Portal extends Treasure_Decorator{
    Treasure treasure;
    public Portal(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 1200;
    }
    public String get_treasures(){
        if(this.treasure.get_treasures().equals("")){
            return "Portal";
        }
        else{
            return this.treasure.get_treasures() + ",Portal";
        }
    }
}
