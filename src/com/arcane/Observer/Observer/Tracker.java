package com.arcane.Observer.Observer;

import com.arcane.Decorator.Treasure;
import com.arcane.Decorator.Treasure_Bag;
import com.arcane.Observer.Event;
import com.arcane.character.creature.Creature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arcane.Decorator.Treasure_Factory.createObject;
import static java.lang.System.in;

public class Tracker implements Observer {
    private int total_treasure_value;
    private Map<String, String> adventurer_positions;
    private Map<String, Integer> adventurer_health;
    private Map<String, Integer> adventurer_treasures;
    private List<String> current_discord;
    private List<String> current_resonance;
    private Map<String, List<Creature>> active_creatures;
    private Map<String, Treasure> treasure_bag_hashmap;
    public Tracker(){
        total_treasure_value = 0;
        treasure_bag_hashmap = new HashMap<>(){
            {
                put("EK", new Treasure_Bag());
                put("MW", new Treasure_Bag());
                put("ZR", new Treasure_Bag());
                put("TV", new Treasure_Bag());
            }};
        adventurer_health = new HashMap<>(){
            {
                put("EK", 5);
                put("MW", 3);
                put("ZR", 3);
                put("TV", 7);
            }};

        adventurer_positions = new HashMap<>(){
            {
                put("EK", "SR");
                put("MW", "SR");
                put("ZR", "SR");
                put("TV", "SR");

            }};
        current_discord = new ArrayList<>();
        current_resonance = new ArrayList<>();
    }
    @Override
    public void update_for_current_event(List<Event> current_events){
        for(Event event:current_events){
            switch(event.getType()){
                case FIND_TREASURE:
                    String[] parts = event.getName().split("-");
                    Treasure curr = treasure_bag_hashmap.get(parts[0]);
                    this.treasure_bag_hashmap.put(parts[0], createObject(parts[1],curr));
                case Adventurer_enter_room:
                    this.adventurer_positions.put(event.getName(), event.getRoom());
                case Creature_enter_room:
                    break;
                case GAIN_ELEMENTAL_DISCORD:
                    if(!this.current_discord.contains(event.getName())){
                    this.current_discord.add(event.getName());
                }
                case GAIN_ELEMENTAL_RESONANCE:
                    if(!this.current_resonance.contains(event.getName())){
                    this.current_resonance.add(event.getName());
                }
                case LOSE_HEALTH:
                    break;
                case DEFEAT:
                    break;
                default:
                    break;
            }
//            if(event.getType() == EventType.FIND_TREASURE){
//                String[] parts = event.getName().split("-");
//                Treasure curr = treasure_bag_hashmap.get(parts[0]);
//                this.treasure_bag_hashmap.put(parts[0], createObject(parts[1],curr));
//            }

        }
    }
    void print_summary(){
        System.out.println("Inside print summary. Please implement");
    }
}
