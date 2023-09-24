package ooad.arcane.Adventurers;

import java.util.ArrayList;

public class Zephyr_Rogue extends Adventurer {

    public Zephyr_Rogue(int health, int dodge_chance, String affinity, String discord, ArrayList<Integer> current_room, int treasure,int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(health, dodge_chance, affinity, discord, current_room, treasure,damage_delta, dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    @Override
    void update_attributes(){
        int floor_id = this.getCurrent_room().get(0);
        if(floor_id == 3){
            // increase by 2 if at Air floor
            this.setDice_roll_treasure_delta(this.getDice_roll_treasure_delta()+2);
        } else if (floor_id == 0) {
            //decrease by 2 if at Earth floor
            this.setDice_roll_treasure_delta(this.getDice_roll_treasure_delta()-2);
        }
    }

}
