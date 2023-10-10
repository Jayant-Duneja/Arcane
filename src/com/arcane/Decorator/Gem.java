package com.arcane.Decorator;

public class Gem extends Treasure_Decorator{
    Treasure treasure;
    public Gem(Treasure treasure){
        this.treasure=treasure;
    }
    public int get_value(){
        return treasure.get_value() + 1000;
    }
}
