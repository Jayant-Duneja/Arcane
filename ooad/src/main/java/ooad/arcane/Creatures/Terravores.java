package ooad.arcane.Creatures;
import ooad.arcane.Adventurers.Adventurer;

import java.util.List;

public class Terravores extends Creature {
    public Terravores(List<List<Integer>> initialPositions) {
        super();
        setActive_positions(initialPositions); // Set the initial positions for Terravores
    }

    @Override
    List<List<Integer>> movement(List<Adventurer> adventurers){

        // Return the new positions of all Terravores
        return getActive_positions();
    }
}
