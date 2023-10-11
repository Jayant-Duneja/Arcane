package com.arcane.Observer;

import java.util.List;

public class Event {
        EventType type;
        String name;
        //enter an object of type floor
        //convert this to floor object
        String room;
        //enter an object of type treasure.
        public Event(EventType type, String name, String room){
            this.type=type;
            this.name = name;
            this.room=room;
        }
        public EventType getType(){return this.type;}
        public String getName(){return  this.name;}
        public String getRoom(){return this.room;}

}

