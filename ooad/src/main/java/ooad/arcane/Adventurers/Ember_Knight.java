package ooad.arcane.Adventurers;

import java.util.List;

public class Ember_Knight extends Adventurer{
    public Ember_Knight(String name, int health, double dodge_chance, List<Integer> current_room, int treasure
            , int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(name, health, dodge_chance, current_room, treasure, damage_delta,dice_roll_combat_delta, dice_roll_treasure_delta);
    }

    // Updating the attributes based on the resonance and the discord that the adventurer has
    @Override
    public void update_attributes(){
        int floor_id = this.getCurrent_room().get(0);
        if(floor_id == 1){
            // increase by 2 if at fire floor
            this.setDice_roll_combat_delta(this.getDice_roll_combat_delta()+2);
        } else if (floor_id == 2) {
            //decrease by 2 if at water floor
            this.setDice_roll_combat_delta(this.getDice_roll_combat_delta()-2);
        }
    }
}
