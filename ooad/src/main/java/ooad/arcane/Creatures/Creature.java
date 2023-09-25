package ooad.arcane.Creatures;

import lombok.Getter;
import lombok.Setter;
import ooad.arcane.Adventurers.Adventurer;

import java.util.List;


public abstract class Creature {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private List<Integer> health;

    @Getter
    @Setter
    private List<List<Integer>> active_positions;

    public Creature(String name, List<Integer> health,List<List<Integer>> active_positions){
        this.name=name;
        this.health=health;
        this.active_positions=active_positions;
    }
    public void movement(List<Adventurer> adventurers){}
    public boolean are_present(){
        boolean flag=false;
        for(Integer i:this.health){
            if(i == 1){
                flag=true;
                break;
            }
        }
        return flag;
    }

}
