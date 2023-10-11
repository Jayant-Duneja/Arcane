package com.arcane.Decorator;

import com.arcane.Element;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.util.Constants;
import com.arcane.util.RandomHelper;
import java.util.ArrayList;
import java.util.List;

public abstract class Treasure_Decorator extends Treasure{
    protected String RoomId;
    protected String name;
    protected Treasure treasure;
    protected int value;

    public String getRoom() {
        this.RoomId = this.getRandomRoom();
        return this.RoomId;
    }
    public String getName(){
        return this.name;
    }

    protected String getRandomRoom() {
        Element curr_element = RandomHelper.getRandomElementFromList(Constants.all_elements);
        int row = RandomHelper.getInt(Constants.VERTICAL_ROOMS - 1);
        int column = RandomHelper.getInt(Constants.HORIZONTAL_ROOMS - 1);
        return curr_element.name() + "-" + row + "-" + column;
    }
//    public String get_treasures(){
//        if(this.treasure.get_treasures().equals("")){
//            return this.name;
//        }
//        else{
//            return this.treasure.get_treasures() + "," + this.name;
//        }
//    }
//    public int get_value(){
//        return treasure.get_value() + this.value;
//    }
}
