package com.arcane.Decorator;

public class Portal extends Treasure_Decorator{
    Treasure treasure;
    public Portal(Treasure treasure){
        this.name="Portal";
        this.value=1200;
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

}
