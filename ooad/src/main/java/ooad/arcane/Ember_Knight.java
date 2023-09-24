package ooad.arcane;

import java.util.ArrayList;

public class Ember_Knight extends Adventurer{
    public Ember_Knight(int health, int dodge_chance, String affinity, String discord, ArrayList<Integer> current_room, int treasure
    ,int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(health, dodge_chance, affinity, discord, current_room, treasure, dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    @Override
    void update_attributes(int floor_id){
        if(floor_id == 1){
            // increase by 2 if at fire floor
            this.setDice_roll_combat_delta(this.getDice_roll_combat_delta()+2);
        } else if (floor_id == 2) {
            //decrease by 2 if at water floor
            this.setDice_roll_combat_delta(this.getDice_roll_combat_delta()-2);
        }
    }
}
