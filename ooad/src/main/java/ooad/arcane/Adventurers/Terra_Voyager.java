package ooad.arcane.Adventurers;

import java.util.List;

public class Terra_Voyager extends Adventurer {
    public Terra_Voyager(String name, int health, double dodge_chance, List<Integer> current_room, int treasure
            , int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(name, health, dodge_chance, current_room, treasure, damage_delta,dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    @Override
    public void update_attributes(){
        int floor_id = this.getCurrent_room().get(0);
        // less damage when at earth floor
        // more damage when at fire floor
        if(floor_id == 0){
            this.setDamage_delta(1);
        } else if (floor_id == 1) {
            this.setDamage_delta(-1);
        }
    }
}
