package ooad.arcane.Creatures;

import ooad.arcane.Adventurers.Adventurer;

import java.util.List;

public class Terravores extends Creature {
    public Terravores(String name,List<List<Integer>> initialPositions) {
        super(name,initialPositions);
    }

    @Override
    public void movement(List<Adventurer> adventurers){
        // Return the new positions of all Terravores

    }
}
