package com.arcane.Observer;

import com.arcane.board.GameBoard;

import java.util.List;

public class Event {
        EventType type;
        String name;
        //enter an object of type floor
        //convert this to floor object
        String room;
        GameBoard gameBoard;
        //enter an object of type treasure.
        public Event(EventType type, String name, String room, GameBoard gameBoard){
            this.type=type;
            this.name = name;
            this.room=room;
            this.gameBoard=gameBoard;
        }
        public Event(EventType type, String name){
            this.type=type;
            this.name = name;
            this.room=null;
            this.gameBoard=null;
        }
        public Event(EventType type, String name, String room){
            this.type=type;
            this.name = name;
            this.room=room;
            this.gameBoard=null;
        }

        public EventType getType(){return this.type;}
        public String getName(){return  this.name;}
        public String getRoom(){return this.room;}
        public GameBoard getGameBoard(){return this.gameBoard;}

}

