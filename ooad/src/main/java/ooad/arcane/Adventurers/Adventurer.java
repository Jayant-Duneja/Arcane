package ooad.arcane.Adventurers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;
//-1: Starting Floor
//        0: Earth Floor
//        1: Fire Floor
//        2: Water Floor
//        3: Air Floor

//@Getter
//@Setter
public abstract class Adventurer {

    public Adventurer(int health, double dodge_chance, List<Integer> current_room, int treasure,
    int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta)
    {
        this.health=health;
        this.dodge_chance=dodge_chance;
        this.current_room=current_room;
        this.treasure=treasure;
        this.dice_roll_combat_delta=dice_roll_combat_delta;
        this.dice_roll_treasure_delta=dice_roll_treasure_delta;
        this.damage_delta=damage_delta;
    }
    private int health;
    @Getter
    @Setter
    private double dodge_chance;
    @Getter
    @Setter
    private int dice_roll_combat_delta;
    @Getter
    @Setter
    private int dice_roll_treasure_delta;
    @Getter
    @Setter
    private int damage_delta;
    @Getter
    @Setter
    private List<Integer> current_room;
    private int treasure;
    boolean is_alive()
    {
        return this.health > 0;
    }
    void update_treasure()
    {
        this.treasure=this.treasure+1;
    }
    void update_health()
    {
        if (Math.random() < this.dodge_chance) {
            System.out.println("I have dodged the attack");
        } else {
            this.health=this.health-2+this.damage_delta;
        }
    }
    void update_attributes() {
    }
    List<Integer> movement(List<List<Integer>> possible_movements){
        Random random = new Random();
        int outerListSize = possible_movements.size();

        // Generate a random index within the valid range
        int randomIndex = random.nextInt(outerListSize);

        // Retrieve the randomly chosen element
        List<Integer> next_room = possible_movements.get(randomIndex);
        this.current_room=next_room;
        return next_room;

    }
}
