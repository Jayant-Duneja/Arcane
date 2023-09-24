package ooad.arcane.Adventurers;

import java.util.ArrayList;

public class Mist_Walker extends Adventurer {

    public Mist_Walker(int health, int dodge_chance, String affinity, String discord, ArrayList<Integer> current_room, int treasure, int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(health, dodge_chance, affinity, discord, current_room, treasure, damage_delta, dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    @Override
    void update_attributes(int floor_id){
        //increase dodge chance when at water floor
        //dec when at air floor
        if(floor_id == 2){
            this.setDodge_chance(this.getDodge_chance() + 0.25);
        } else if (floor_id == 3) {
            this.setDodge_chance(this.getDodge_chance() - 0.25);
        }
    }
}
