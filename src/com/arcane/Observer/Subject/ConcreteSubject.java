package com.arcane.Observer.Subject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.arcane.Observer.Event;
import com.arcane.Observer.EventType;
import com.arcane.Observer.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{
    private List<Observer> current_observers;
    private List<Event> event_current_turn;
    public ConcreteSubject(){
        this.current_observers = new ArrayList<>();
        this.event_current_turn = new ArrayList<>();
    }
    public void add_event_to_current_turn(EventType eventType, String name){
        this.event_current_turn.add(new Event(eventType, name, null));
    }
    public void add_event_to_current_turn(EventType eventType, String name, String room){
        this.event_current_turn.add(new Event(eventType, name, room));
    }

    public void clear_previous_turn_events(){
        this.event_current_turn.clear();
    }
    @Override
    public void register_observer(Observer observer) {
        this.current_observers.add(observer);
    }

    @Override
    public void remove_observer(Observer observer) {
        this.current_observers.remove(observer);
    }

    @Override
    public void notify_all_observers() {
        for(Observer observer: this.current_observers){
            observer.update_for_current_event(this.event_current_turn);
        }
    }
}
