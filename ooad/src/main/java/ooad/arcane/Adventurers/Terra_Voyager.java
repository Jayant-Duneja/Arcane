package ooad.arcane.Adventurers;

import java.util.ArrayList;

public class Terra_Voyager extends Adventurer {
    public Terra_Voyager(int health, int dodge_chance, String affinity, String discord, ArrayList<Integer> current_room, int treasure, int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(health, dodge_chance, affinity, discord, current_room, treasure, damage_delta,dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    @Override
    void update_attributes(int floor_id){
        if(floor_id == 0){

        }
    }
}
