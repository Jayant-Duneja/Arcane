package ooad.arcane.Creatures;

import ooad.arcane.Adventurers.Adventurer;

import java.util.List;
import java.util.Random;

public class Zephyrals extends Creature{
    public Zephyrals(List<List<Integer>> initialPositions){
        super();
        setActive_positions(initialPositions);
    }

    @Override
    List<Integer> movement(List<Adventurer> adventurers) {
        // Check if any adventurers are on the air floor
        boolean adventurerOnSameFloor = isSameFloor(adventurers);

        // If no adventurers are on the same floor, move Zephyrals can move randomly
        if (!adventurerOnSameFloor) {
            moveZephyralsRandomly();
        }

        // Return the new positions of all Zephyrals
        return getActive_positions().get(1);
    }

    private void moveZephyralsRandomly() {
        Random random = new Random();

        for (List<Integer> zephyralPosition : getActive_positions()) {

            // Generating random directions: 0 (left), 1 (right), 2 (up), 3 (down)
            int direction = random.nextInt(4);

            int currentRoomX = zephyralPosition.get(0);
            int currentRoomY = zephyralPosition.get(1);


            switch (direction) {
                case 0: // Move left
                    if (currentRoomX > 0) {
                        currentRoomX--;
                    }
                    break;
                case 1: // Move right
                    if (currentRoomX < 2) {
                        currentRoomX++;
                    }
                    break;
                case 2: // Move up
                    if (currentRoomY > 0) {
                        currentRoomY--;
                    }
                    break;
                case 3: // Move down
                    if (currentRoomY < 2) {
                        currentRoomY++;
                    }
                    break;
            }

            // Update the position
            zephyralPosition.set(0, currentRoomX);
            zephyralPosition.set(1, currentRoomY);
        }
    }

    private boolean isSameFloor(List<Adventurer> adventurers) {
        for (Adventurer adv : adventurers) {
            if (adv.getCurrent_room().get(0) == 3) {
                return true;
            }
        }
        return false;
    }
}
