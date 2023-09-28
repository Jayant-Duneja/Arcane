package ooad.arcane.Creatures;

import lombok.Getter;
import lombok.Setter;
import ooad.arcane.Adventurers.Adventurer;

import java.util.List;


// Abstraction:: Abstracting this class allowed us to define a common interface for all different types of creatures.
public abstract class Creature {

    // Encapsulation:: Protecting the data and methods from outer class to access using 'private' access specifier.
    // Access to this is only applicable through Getters and Setters.

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<List<Integer>> active_positions;

    public Creature(String name, List<List<Integer>> active_positions){
        this.name=name;
        this.active_positions=active_positions;
    }

    // Polymorphism (method-overloading) :: Both creature and adventure class uses the same function name,
    // but has different parameters passed and has different.
    public void movement(List<Adventurer> adventurers){}
    public boolean is_empty(){
        return this.active_positions.isEmpty();
    }

}
