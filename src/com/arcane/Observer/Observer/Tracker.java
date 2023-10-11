package com.arcane.Observer.Observer;

import com.arcane.Observer.Event;
import com.arcane.character.creature.Creature;

import java.util.List;
import java.util.Map;

public class Tracker implements Observer {
    private int total_treasure_value;
    private Map<String, List<Integer>> adventurer_positions;
    private Map<String, Integer> adventurer_health;
    private Map<String, Integer> adventurer_treasures;
    private Map<String, Boolean> current_discord;
    private Map<String, Boolean> current_resonance;
    private Map<String, List<Creature>> active_creatures;
    public Tracker(){
        System.out.println("Inside Constructer. Please implement");
    }
    @Override
    public void update_for_current_event(List<Event> current_events){
        System.out.println("Inside current event update. Please implement");
    }
    void print_summary(){
        System.out.println("Inside print summary. Please implement");
    }
}
