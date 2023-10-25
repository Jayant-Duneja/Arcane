package com.arcane.Observer.Observer;

import com.arcane.Observer.Event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Logger implements Observer {
    private static Logger logger;

    private int turn;
    private List<Event> current_events;
    String file_name;
    private Logger(){
        turn=-1;
        current_events=new ArrayList<>();
        file_name="";
    }
    public static Logger getInstance(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }
    public void instantiate(int turn_number){
        this.turn = turn_number;
        current_events.clear();
        file_name = System.getProperty("user.dir") + "/Logger-Outputs/ " + "Logger -- " + turn_number + ".txt";
    }
    public void close(){
    this.turn = -1;
    }
    @Override
    public void update_for_current_event(List<Event> current_events){
        this.current_events = current_events;
        for(Event event:this.current_events){
            appendEventsToFile(event, this.file_name);
        }
    }
    public void appendEventsToFile(Event event, String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
       writer.write("--------------------------------------------------------------------");
       writer.newLine();
       writer.write("Event type: " + event.getType());
       writer.newLine();
       writer.write("Name: " + event.getName());
       writer.newLine();
       if ( event.getRoom() != null ) {
           writer.write("Current Room type: " + event.getRoom().toString());
           writer.newLine();
       }
    } catch (IOException e) {
        e.printStackTrace(); // Handle the IOException appropriately
        }
    }
}
