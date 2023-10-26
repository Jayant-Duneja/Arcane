package com.arcane.Observer.Observer;

import com.arcane.Decorator.Treasure;
import com.arcane.Decorator.Treasure_Bag;
import com.arcane.Observer.Event;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;
import com.arcane.character.creature.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arcane.Decorator.Treasure_Factory.createObject;

public class Tracker implements Observer {
    // Singleton implementation of the Tracker
    // Eager initialization of the Tracker
    private static Tracker uniqueTracker = new Tracker();
    int turn_number;
    private int total_treasure_value;
    private final List<String> active_adventurers;
    private final Map<String, String> adventurer_positions;
    private final Map<String, Integer> adventurer_health;
    private final List<String> current_discord;
    private final List<String> current_resonance;
    private Map<String, Integer> active_creatures;
    GameBoard gameBoard;

    private final Map<String, Treasure> treasure_bag_hashmap;
    private Tracker(){
        gameBoard=null;
        turn_number=0;
        total_treasure_value = 0;
        treasure_bag_hashmap = new HashMap<>();
        adventurer_health = new HashMap<>();
        adventurer_positions = new HashMap<>();
        active_creatures = new HashMap<>();
        current_discord = new ArrayList<>();
        current_resonance = new ArrayList<>();
        active_adventurers = new ArrayList<>();
    }
    public void initialize(List<Adventurer> adventurers, List<Creature> creatures){
        for(Adventurer adventurer:adventurers){
            active_adventurers.add(adventurer.getAcronym().acronym);
            treasure_bag_hashmap.put(adventurer.getAcronym().acronym, new Treasure_Bag());
            adventurer_health.put(adventurer.getAcronym().acronym, adventurer.getHealth());
            adventurer_positions.put(adventurer.getAcronym().acronym, "SR");
        }
        for(Creature creature:creatures){
            String acronym =creature.getAcronym().acronym;
            if(active_creatures.containsKey(acronym)){
                active_creatures.put(acronym, active_creatures.get(acronym)+1);
            }
            else{
                active_creatures.put(acronym, 1);
            }
        }
    }
    public static Tracker getInstance(){
        return uniqueTracker;
    }
    @Override
    public void update_for_current_event(List<Event> current_events){
        String[] parts;
        int temp_health;
        for(Event event:current_events){
            switch(event.getType()){
                case FIND_TREASURE:
                    parts = event.getName().split("-");
                    Treasure curr = treasure_bag_hashmap.get(parts[0]);
                    this.treasure_bag_hashmap.put(parts[0], createObject(parts[1],curr));
                    break;
                case Adventurer_enter_room:
                    this.adventurer_positions.put(event.getName(), event.getRoom());
                    break;
                case Creature_enter_room:
                    this.gameBoard=event.getGameBoard();
                    break;
                case GAIN_ELEMENTAL_DISCORD:
                    if(!this.current_discord.contains(event.getName())){
                        this.current_discord.add(event.getName());
                    }
                    break;
                case GAIN_ELEMENTAL_RESONANCE:
                    if(!this.current_resonance.contains(event.getName())){
                        this.current_resonance.add(event.getName());
                    }
                    break;
                case LOSE_HEALTH:
                    parts = event.getName().split("-");
                    temp_health = this.adventurer_health.get(parts[0]);
                    this.adventurer_health.put(parts[0], temp_health - Integer.parseInt(parts[1]));
                    break;
                case DEFEAT:
                    if(this.active_adventurers.contains(event.getName())){
                        this.active_adventurers.remove(event.getName());
                    }
                    else if(this.active_creatures.containsKey(event.getName())){
                        temp_health = this.active_creatures.get(event.getName());
                        this.active_creatures.put(event.getName(), temp_health-1);
                    }
                    break;
                default:
                    break;
            }
        }
        turn_number+=1;
        print_summary();

    }
    void get_total_treasure_value(){
        int temp=0;
        for(String name: active_adventurers){
            temp+=treasure_bag_hashmap.get(name).get_value();
        }
        this.total_treasure_value=temp;
    }
    void print_summary(){
        get_total_treasure_value();
        for(String name: active_adventurers) {
            System.out.println(name + "          " + adventurer_positions.get(name) + "          " + adventurer_health.get(name)
                    +  "           " + treasure_bag_hashmap.get(name).get_treasures() + "            " +
                    treasure_bag_hashmap.get(name).get_value());
        }
        System.out.println();
        System.out.println("Elemental Resonance ");
        for(String name:current_resonance) {
            if(active_adventurers.contains(name)){
                System.out.println(name);
            }
        }
        System.out.println();
        System.out.println("Elemental Discord ");
        for(String name:current_discord) {
            if(active_adventurers.contains(name)) {
                System.out.println(name);
            }
        }
        System.out.println();
        int temp=0;
        for (String key : active_creatures.keySet()) {
            temp+=active_creatures.get(key);
        }
        System.out.println("Total Active Creatures " + temp);
        System.out.println();
        System.out.println("Creatures                 " + "Room");
        List<Creature> remaining_creatures = gameBoard.getRemainingCreatures();
        for(Creature creature:remaining_creatures){
            System.out.println(creature.getAcronym().acronym + "                     "  + creature.getCurrentRoomId());
        }
        }
}



