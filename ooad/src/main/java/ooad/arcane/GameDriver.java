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
    // We have 4 types of Creature objects. Each object has the 4 creatures as an attribute of the class
    List<Creature> active_creature_objects;
    List<Floor> floors;
    int treasure_found_so_far;
    public GameDriver() {
        create_adventurers();
        create_creature_objects();
        create_floors();
        treasure_found_so_far=0;
    }

    // Initialize Adventurers
    public void create_adventurers(){
        this.active_adventurers = new ArrayList<>();
        // Creating the 4 adventurers and adding them to the active adventurers list
        this.active_adventurers.add(new Ember_Knight("EmberKnight", 5, 0.2, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Mist_Walker("MistWalker", 3, 0.5, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Terra_Voyager("TerraVoyager", 7, 0.1, List.of(-1, 0, 0), 0, 0, 0, 0));
        this.active_adventurers.add(new Zephyr_Rogue("ZephyrRogue", 3, 0.25, List.of(-1, 0, 0), 0, 0, 0, 0));
    }

    // Initialize Creatures
    public void create_creature_objects(){
        this.active_creature_objects = new ArrayList<>();
        // We are assuming that the 4 creatures on each floor spawn on the 4 rooms on the edges
        this.active_creature_objects.add(new Terravores("TerraVores", new ArrayList<>(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(0, 2), Arrays.asList(2, 0), Arrays.asList(2, 2)))));
        this.active_creature_objects.add(new Fireborns("FireBorns", new ArrayList<>(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(0, 2), Arrays.asList(2, 0), Arrays.asList(2, 2)))));
        this.active_creature_objects.add(new Aquarids("Aquarids", new ArrayList<>(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(0, 2), Arrays.asList(2, 0), Arrays.asList(2, 2)))));
        this.active_creature_objects.add(new Zephyrals("Zephyrals", new ArrayList<>(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(0, 2), Arrays.asList(2, 0), Arrays.asList(2, 2)))));
    }

    // Initialize Floors
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

    // Moving adventurers
    public void move_adventurers(){
        // Get the connected rooms for the adventurer and move it to a random room out of these.
        List<Integer> current_room;
        List<List<Integer>> connected_rooms;
        int current_floor;
        for(Adventurer adv : this.active_adventurers){
            current_room = adv.getCurrent_room();
            current_floor = current_room.get(0);
            connected_rooms = this.floors.get(current_floor+1).getRoomConnections().get(current_room);
            //Random adventurer movement
            adv.movement(connected_rooms);
        }
    }

    // Moving creatures
    public void move_creatures(){
        for(Creature creature:this.active_creature_objects){
            creature.movement(this.active_adventurers);
        }
    }

    public void update_adventurer_attributes(){
        for(Adventurer adv: this.active_adventurers){
            adv.update_attributes();
        }
    }

    // returns the indexes of all the creatures that are present in the same room as the
    // adventurer on the floor. we can access by floor.creature.active_pos.get(i)
    // After this, we use these indices only for battles with the adventurers
    public List<Integer> get_creature_in_same_room(Adventurer adventurer) {
        List<Integer> creatures_in_same_room_indices = new ArrayList<>();
        List<Integer> adventurer_current_position = adventurer.getCurrent_room();
        int current_floor = adventurer_current_position.get(0);
        if(current_floor != -1) { // not checking for creatures if I am on the starting floor. Not going to do anything on current floor. Turn waste
            for (int i = 0; i < this.floors.get(current_floor + 1).getCreature().getActive_positions().size(); i++) {
                List<Integer> current_creature_position = this.floors.get(current_floor + 1).getCreature().getActive_positions().get(i);
                if (current_creature_position.get(0) == adventurer_current_position.get(1)
                        && current_creature_position.get(1) == adventurer_current_position.get(2)) {
                    creatures_in_same_room_indices.add(i);
                }
            }
        }
        return creatures_in_same_room_indices;
    }

    // Check if 'adventurer' and 'creature' are in same room.
    // From adventurer POV:- fight(): If same room ; searchForTreasure() : If empty room
    public void check_adventurer_creature_same_room(List<Adventurer> adventurers){
        // Creating a list of adventurers which might be deleted
        List<Adventurer> adventurers_to_be_removed = new ArrayList<>();
        for(Adventurer adv : adventurers){
            List<Integer> creaturesInSameRoom = get_creature_in_same_room(adv);
            int current_floor = adv.getCurrent_room().get(0);
            Creature creature = floors.get(current_floor + 1).getCreature();

            // Creature found
            if(current_floor != -1 && !creaturesInSameRoom.isEmpty()){
                for(int creaturePositionIndex : creaturesInSameRoom){
                    // Fight adventurer with creature
                    // Fight flag confirms that an already dead adventurer is not fighting
                    boolean fight_flag = fight(adv, creature, creaturePositionIndex);
                    if(!fight_flag){
                        adventurers_to_be_removed.add(adv);
                        break;
                    }
                }
            }

            // Empty room
            else if(current_floor != -1){
                searchForTreasure(adv);
            }
        }
        for(Adventurer adv: adventurers_to_be_removed){
            this.active_adventurers.remove(adv);
        }
    }

    public boolean fight(Adventurer adventurer, Creature creature, int creatureInFightIdx){

        int adventurerDiceScore = roll_dice() + adventurer.getDice_roll_combat_delta();
        int creatureDiceScore = roll_dice();

        // Adventurer wins the fight
        if(adventurerDiceScore > creatureDiceScore){
            killCreature(creature, creatureInFightIdx);
        }

        //Adventurer loses the fight
        else if(adventurerDiceScore < creatureDiceScore){
            // Updating the attributes based on the resonance and the discord that the adventurer has
            adventurer.under_attack();
            if(adventurer.getHealth() <= 0){
                return false;
            }
        }
        return true;
    }


    public void searchForTreasure(Adventurer adventurer){
        int sumOfDieRoll = roll_dice() + adventurer.getDice_roll_treasure_delta();
        if(sumOfDieRoll >= 11){
            adventurer.setTreasure(adventurer.getTreasure()+1);
            //updating the global treasure only. not using adventurer treasure
            this.treasure_found_so_far+=1;
        }
    }


    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ UTILITY FUNCTIONS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    // Rolling two dice to get their sum
    private int roll_dice(){
        Random random = new Random();
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;

        return die1 + die2;
    }

    //Remove the creature from the active list
    private void killCreature(Creature creature, int creatureInFightIdx) {
        // update the health for the creature as well
        if (creatureInFightIdx >= 0 && creatureInFightIdx < creature.getActive_positions().size()) {
            creature.getActive_positions().remove(creatureInFightIdx);
        }
    }
    // game terminate condition; true: game stops
    // Either we collect 50 treasures, or there are no creatures or adventurers.
    public boolean isGameOver() {

        // Elimination survey
        boolean allAdventurersEliminated = active_adventurers.isEmpty();
        // For creatures, we would need to check the active positions of the creature object and not the active_creature_objects of the arrays
        boolean noActiveCreatures = true;
        for(Creature creature: this.active_creature_objects){
            noActiveCreatures = noActiveCreatures && creature.is_empty();
        }
        boolean enoughTreasuresFound = this.treasure_found_so_far >= 50;

        return allAdventurersEliminated || enoughTreasuresFound || noActiveCreatures;
    }
}