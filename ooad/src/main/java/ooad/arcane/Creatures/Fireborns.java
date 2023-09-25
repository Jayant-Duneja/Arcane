package ooad.arcane.Creatures;
import ooad.arcane.Adventurers.Adventurer;

import java.util.*;


public class Fireborns extends Creature {
    private boolean movingClockwise;

    public Fireborns(String name, List<Integer> health,List<List<Integer>> initialPositions) {
        super(name, health, initialPositions);
        this.movingClockwise = new Random().nextBoolean();
    }

    @Override
    public void movement(List<Adventurer> adventurers) {
        movingClockwise = new Random().nextBoolean(); // Random clockwise or anti-clockwise movement

        //Check if any adventurers are on fire floor
        boolean adventurerOnSameFloor = isSameFloor(adventurers);

        //If adventurer is not on the same floor, then we can move
        if (!adventurerOnSameFloor) {
            moveFireborn(movingClockwise);
        }
    }


    private void moveFireborn(boolean isClockwise){
        for (List<Integer> firebornPosition : getActive_positions()) {
            if (isClockwise) {
                moveClockwise(firebornPosition);
            } else {
                moveCounterclockwise(firebornPosition);
            }
        }
    }

    private void moveClockwise(List<Integer> position) {
        int currentRoomX = position.get(0);
        int currentRoomY = position.get(1);

        if (currentRoomX == 0 && currentRoomY == 0) {
            currentRoomX++;
        } else if (currentRoomX == 2 && currentRoomY == 0) {
            currentRoomY++;
        } else if (currentRoomX == 2 && currentRoomY == 2) {
            currentRoomX--;
        } else if (currentRoomX == 0 && currentRoomY == 2) {
            currentRoomY--;
        } else {
            if (currentRoomX == 0) {
                currentRoomY--;
            } else if (currentRoomY == 0) {
                currentRoomX++;
            } else if (currentRoomX == 2) {
                currentRoomY++;
            } else if (currentRoomY == 2) {
                currentRoomX--;
            }
        }

        position.set(0, currentRoomX);
        position.set(1, currentRoomY);
    }

    private void moveCounterclockwise(List<Integer> position) {
        int currentRoomX = position.get(0);
        int currentRoomY = position.get(1);

        if (currentRoomX == 0 && currentRoomY == 0) {
            currentRoomY++;
        } else if (currentRoomX == 2 && currentRoomY == 0) {
            currentRoomX--;
        } else if (currentRoomX == 2 && currentRoomY == 2) {
            currentRoomY--;
        } else if (currentRoomX == 0 && currentRoomY == 2) {
            currentRoomX++;
        } else {
            if (currentRoomX == 0) {
                currentRoomX++;
            } else if (currentRoomY == 0) {
                currentRoomY--;
            } else if (currentRoomX == 2) {
                currentRoomX--;
            } else if (currentRoomY == 2) {
                currentRoomY++;
            }
        }

        position.set(0, currentRoomX);
        position.set(1, currentRoomY);
    }

    //Checking if adventurer and creatures is on same floor
    private boolean isSameFloor(List<Adventurer> adventurers) {
        for (Adventurer adv : adventurers) {
            if (adv.getCurrent_room().get(0) == 1) {
                return true;
            }
        }
        return false;
    }
}
