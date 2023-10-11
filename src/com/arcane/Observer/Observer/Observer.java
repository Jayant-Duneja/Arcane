package com.arcane.Observer.Observer;

import com.arcane.Observer.Event;

import java.util.List;

public interface Observer {
    default void update_for_current_event(List<Event> current_events){

    }
}
