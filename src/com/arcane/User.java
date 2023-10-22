package com.arcane;

import com.arcane.Command.Command;
import com.arcane.Factory.AdventurerFactory;
import com.arcane.Factory.CreatureFactory;
import com.arcane.engine.GameEngine;
import java.util.Scanner;

public class User {
    Command command;
    GameEngine gameEngine;
    // user will enter the type of adventurer
    String adventurerType;
    // user will enter the custom name of the adventurer
    String adventurerCustomName;
    // user will enter the comma seperated string of the creatures he wants in the game
    String creatures;
    public User(){
        gameEngine =  new GameEngine();
        adventurerType = getInput("Please enter the type of Adventurer");
        adventurerCustomName = getInput("Please enter the custom name of the Adventurer");
        creatures = getInput("Enter a comma seperated list of creatures you want");
        gameEngine.initialiseGame(adventurerType, adventurerCustomName, creatures);
        for(int i=0; i< 10; i++){
            command = getCommand();
        }
    }
    private String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt + ": ");
        String ret = scanner.nextLine();
//        System.out.println("Hello, " + name + "!");
        return ret;
    }
    private Command getCommand(){
        // Take user input and get the input command
        // If the command is an exit command, break the loop and end the game
        // Note: Exit command should only happen in the starting room
        return null;
    }

}
