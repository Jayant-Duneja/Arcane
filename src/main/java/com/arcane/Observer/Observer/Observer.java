package com.arcane.Observer.Observer;

import com.arcane.Observer.Event;

import java.util.List;
// OBSERVER PATTERN IMPLEMENTATION - OBSERVER INTERFACE
public interface Observer {
    default void update_for_current_event(List<Event> current_events){

    }
}
