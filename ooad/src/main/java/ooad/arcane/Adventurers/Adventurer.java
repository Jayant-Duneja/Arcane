package ooad.arcane.Adventurers;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

// FLOOR RULES :-
// -1: Starting Floor
// 0: Earth Floor
// 1: Fire Floor
// 2: Water Floor
// 3: Air Floor


// Abstraction:: Abstracting this class allowed us to define a common interface for all different types of adventurer.
public abstract class Adventurer {

    public Adventurer(String name, int health, double dodge_chance, List<Integer> current_room, int treasure,
    int damage_delta, int dice_roll_combat_delta, int dice_roll_treasure_delta)
    {
        this.name=name;
        this.health=health;
        this.dodge_chance=dodge_chance;
        this.current_room=current_room;
        this.treasure=treasure;
        this.dice_roll_combat_delta=dice_roll_combat_delta;
        this.dice_roll_treasure_delta=dice_roll_treasure_delta;
        this.damage_delta=damage_delta;
    }

    // Encapsulation:: Protecting the data and methods from outer class to access using 'private' access specifier.
    // Access to this is only applicable through Getters and Setters.
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
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
    @Getter
    @Setter
    private int treasure;
    public  boolean is_alive()
    {
        return this.health > 0;
    }
    void update_treasure()
    {
        this.treasure=this.treasure+1;
    }
    // This function is called when the Adventurer is under attack from a creature.
    public void under_attack()
    {
        if (Math.random() < this.dodge_chance) {
        } else {
            this.health=this.health-2+this.damage_delta;
        }
    }
    public void update_attributes() {
    }


    // Polymorphism (method-overloading) :: Both creature and adventure class uses the same function name,
    // but has different parameters passed and has different.
    // For each room, we have a list of connected rooms that the adventurer can go to. We choose 1 random room out of
    // this list as the next room.
    public void movement(List<List<Integer>> possible_movements){
        Random random = new Random();
        int outerListSize = possible_movements.size();

        // Generate a random index within the valid range
        int randomIndex = random.nextInt(outerListSize);

        // Retrieve the randomly chosen element
        List<Integer> next_room = possible_movements.get(randomIndex);
        this.current_room=next_room;
    }
}