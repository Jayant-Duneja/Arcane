package ooad.arcane.Creatures;

import lombok.Getter;
import lombok.Setter;
import ooad.arcane.Adventurers.Adventurer;

import java.util.List;
import java.util.Random;


public abstract class Creature {
    private List<Integer> health;

    @Getter
    @Setter
    private List<List<Integer>> active_positions;

    public Creature(){
        Random random = new Random();
        for(int i=0;i<4;i++){
            health.add(1);
            int random_x =random.nextInt(2);
            int random_y = random.nextInt(2);
            active_positions.add(List.of(random_x, random_y));
        }

    }
    List<Integer> movement(List<Adventurer> adventurers){
        return null;
    }
    boolean are_present(){
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
