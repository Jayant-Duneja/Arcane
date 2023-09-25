package ooad.arcane.Creatures;

import ooad.arcane.Adventurers.Adventurer;

import java.util.List;

public class Aquarids extends Creature{

    public Aquarids(List<List<Integer>> initialPositions){
        super();
        setActive_positions(initialPositions);
    }
    @Override
    List<Integer> movement(List<Adventurer> adventurers){

        //Check if any adventurers are on water floor
        boolean adventurerOnSameFloor = isSameFloor(adventurers);

        //If adventurer is not on the same floor, then we can move
        if (!adventurerOnSameFloor) {
            moveAquarids(adventurers);
        }

        // Return the new positions of all Aquarids
        return getActive_positions().get(1);
    }


    private void moveAquarids(List<Adventurer> adventurers){
        for (List<Integer> aquaridPosition : getActive_positions()) {
            for (Adventurer adventurer : adventurers) {
                if (isAdjacentToAdventurer(adventurer, aquaridPosition)) {
                    moveToAdjacentAdventurer(adventurer, aquaridPosition);
                }
            }
        }
    }


    private boolean isAdjacentToAdventurer(Adventurer adventurer, List<Integer> aquaridPosition) {
        //Adventurer :: [floorId, xPos, yPos]
        //Creature :: [[xPos, yPos], [xPos, yPos], [xPos, yPos], [xPos, yPos]]

        int xDiff = Math.abs(adventurer.getCurrent_room().get(1) - aquaridPosition.get(0));
        int yDiff = Math.abs(adventurer.getCurrent_room().get(2) - aquaridPosition.get(1));
        return xDiff + yDiff == 1; // Checking if creature and adventurer is adjacent (left, right, top, or bottom)
    }

    private void moveToAdjacentAdventurer(Adventurer adventurer, List<Integer> aquaridPosition) {
        aquaridPosition.set(0, adventurer.getCurrent_room().get(1));
        aquaridPosition.set(1, adventurer.getCurrent_room().get(2));
    }

    //Checking if adventurer and creatures is on same floor
    private boolean isSameFloor(List<Adventurer> adventurers) {
        for (Adventurer adv : adventurers) {
            if (adv.getCurrent_room().get(0) == 2) {
                return true;
            }
        }
        return false;
    }
}
