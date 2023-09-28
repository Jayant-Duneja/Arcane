package ooad.arcane.Adventurers;

import java.util.List;

// Inheritance :: Mist_Walker inherits Adventurer
public class Mist_Walker extends Adventurer {

    public Mist_Walker(String name, int health, double dodge_chance, List<Integer> current_room, int treasure
            , int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta) {
        super(name, health, dodge_chance, current_room, treasure, damage_delta, dice_roll_combat_delta, dice_roll_treasure_delta);
    }
    // Updating the attributes based on the resonance and the discord that the adventurer has
    @Override
    public void update_attributes(){
        int floor_id = this.getCurrent_room().get(0);
        //increase dodge chance when at water floor
        //dec when at air floor
        if(floor_id == 2){
            this.setDodge_chance(this.getDodge_chance() + 0.25);
        } else if (floor_id == 3) {
            this.setDodge_chance(this.getDodge_chance() - 0.25);
        }
    }
}
