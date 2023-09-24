package ooad.arcane.Creatures;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature {
    @Getter
    @Setter
    private ArrayList<Integer> health;
    @Getter
    @Setter
    private List<List<Integer>> active_positions;
    List<Integer> movement(){
        return null;
    }
    boolean are_present(){
        boolean flag=false;
        for(List<Integer> list_it:this.active_positions){
            if(list_it.get(0) != -1 && list_it.get(1) != -1){
                flag=true;
                break;
            }
        }
        return flag;
    }
}
