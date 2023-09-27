package ooad.arcane.Creatures;

import ooad.arcane.Adventurers.Adventurer;

import java.util.List;

public class Aquarids extends Creature{

    public Aquarids(String name,List<List<Integer>> initialPositions) {
        super(name, initialPositions);
    }
    @Override
    public void movement(List<Adventurer> adventurers){
        moveAquarids(adventurers);
    }


    private void moveAquarids(List<Adventurer> adventurers){
        for (List<Integer> aquaridPosition : this.getActive_positions()) {
            for (Adventurer adventurer : adventurers) {
                boolean adventurerOnSameFloor = isSameFloor(adventurer);
                if (adventurerOnSameFloor && isAdjacentToAdventurer(adventurer, aquaridPosition)) {
                    moveToAdjacentAdventurer(adventurer, aquaridPosition);
                }
            }
        }
    }
    // Check if the Aquarid is adjacent to the Adventurer.
    private boolean isAdjacentToAdventurer(Adventurer adventurer, List<Integer> aquaridPosition) {
        //Adventurer :: [floorId, xPos, yPos]
        //Creature :: [[xPos, yPos], [xPos, yPos], [xPos, yPos], [xPos, yPos]]
        // Adv: [0,1]
        //
        int xDiff = Math.abs(adventurer.getCurrent_room().get(1) - aquaridPosition.get(0));
        int yDiff = Math.abs(adventurer.getCurrent_room().get(2) - aquaridPosition.get(1));
        return xDiff + yDiff == 1; // Checking if creature and adventurer is adjacent (left, right, top, or bottom)
    }
    // Move to the adjacent room that the Adventurer is present in
    private void moveToAdjacentAdventurer(Adventurer adventurer, List<Integer> aquaridPosition) {
        aquaridPosition.set(0, adventurer.getCurrent_room().get(1));
        aquaridPosition.set(1, adventurer.getCurrent_room().get(2));
    }

    // Checking if the Adventurer is on the same floor as Aquarids
    private boolean isSameFloor(Adventurer adventurer) {
        if (adventurer.getCurrent_room().get(0) == 2) {
                return true;
            }
        return false;

    }
}
