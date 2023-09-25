package ooad.arcane;

import ooad.arcane.Adventurers.*;
import ooad.arcane.Creatures.*;

import java.util.*;
//-1: Starting Floor
//        0: Earth Floor
//        1: Fire Floor
//        2: Water Floor
//        3: Air Floor
// All the lists we have will have the creatures and floors based on these indices only, i.e. the 0th element
// of the floors list is the Earth floor and 0th element of the creature list is the

public class GameDriver {
    List<Adventurer> active_adventurers;
    List<Creature> active_creature_objects;
    List<Floor> floors;
    public GameDriver() {
        create_adventurers();
        create_creature_objects();
        create_floors();
    }


    public int roll_dice(){
        Random random = new Random();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }
    public void create_adventurers(){
        this.active_adventurers = new ArrayList<>();
        // Creating the 4 adventurers and adding them to the active adventurers list
        this.active_adventurers.add(new Ember_Knight("EK", 5, 0.2, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Mist_Walker("MW", 3, 0.5, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Terra_Voyager("TV", 7, 0.1, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Zephyr_Rogue("ZR", 3, 0.25, List.of(-1, 0, 0), 0, 0, 0, 0));
    }
    public void create_creature_objects(){
        this.active_creature_objects = new ArrayList<>();
        // We are assuming that the 4 creatures on each floor spawn on the 4 rooms on the edges
        List<List<Integer>> initial_positions = new ArrayList<>(Arrays.asList(
                Arrays.asList(0, 0),
                Arrays.asList(0, 2),
                Arrays.asList(2, 0),
                Arrays.asList(2, 2)
        ));
        List<Integer> health = Collections.nCopies(4, 1);
        this.active_creature_objects.add(new Terravores("TV", health,initial_positions));
        this.active_creature_objects.add(new Fireborns("FB", health,initial_positions));
        this.active_creature_objects.add(new Aquarids("AQ", health,initial_positions));
        this.active_creature_objects.add(new Zephyrals("ZP", health,initial_positions));
    }
    public void create_floors(){
        // Adding 5 floors here
        // Will be accessed by adventurer.current_floor + 1
        // Staring floor does not have creature object
        this.floors = new ArrayList<>();
        floors.add(new Floor(-1,null));
        floors.add(new Floor(0, this.active_creature_objects.get(0)));
        floors.add(new Floor(1, this.active_creature_objects.get(1)));
        floors.add(new Floor(2, this.active_creature_objects.get(2)));
        floors.add(new Floor(3, this.active_creature_objects.get(3)));
    }

    public void move_adventurers(){
        List<Integer> current_room = new ArrayList<>();
        List<List<Integer>> connected_rooms = new ArrayList<>();
        int current_floor;
        for(Adventurer adv:this.active_adventurers){
            current_room = adv.getCurrent_room();
            System.out.println("Adv Name: " + adv.getName());
//            System.out.println("Current room is: " + current_room);
            current_floor = current_room.get(0);
            connected_rooms = this.floors.get(current_floor+1).getRoomConnections().get(current_room);
            adv.movement(connected_rooms);
            System.out.println("New Room is: " + adv.getCurrent_room());
        }
        System.out.println("------------------------------------------------------");

    }
    public void move_creatures(){
        for(Creature creature:this.active_creature_objects){
            System.out.println("Name: " + creature.getName());
            System.out.println("Previous positions: " + creature.getActive_positions());
            creature.movement(this.active_adventurers);
            System.out.println("New Positions: " + creature.getActive_positions());
            System.out.println("------------------------------------------------------");
        }
    }



}