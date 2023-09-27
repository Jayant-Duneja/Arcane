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
    private List<List<Integer>> active_positions;

    public Creature(String name, List<List<Integer>> active_positions){
        this.name=name;
        this.active_positions=active_positions;
    }
    public void movement(List<Adventurer> adventurers){}
    public boolean is_empty(){
        return this.active_positions.isEmpty();
    }

}
