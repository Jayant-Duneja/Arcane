package com.arcane;

import com.arcane.Command.Command;
import com.arcane.Factory.AdventurerFactory;
import com.arcane.Factory.CreatureFactory;
import com.arcane.engine.GameEngine;
import java.util.Scanner;

public class User {


    private static void simulateSingleRun(){
        String command;
        GameEngine gameEngine;
        // user will enter the type of adventurer
        String adventurerType;
        // user will enter the custom name of the adventurer
        String adventurerCustomName;
        // user will enter the comma separated string of the creatures he wants in the game
        String creatures;
        gameEngine =  new GameEngine();
        adventurerType = getInput("Please enter the type of Adventurer");
        adventurerCustomName = getInput("Please enter the custom name of the Adventurer");
        creatures = getInput("Enter a comma separated list of creatures you want");
        gameEngine.initialiseGame(adventurerType, adventurerCustomName, creatures);
        Boolean prev=Boolean.TRUE;
        for(int i=0; i< 50; i++){
            if(!prev)
                break;
            command = getInput("Enter the command you want");
            if(command.equals("Move"))
            {
                String room  = getInput("Enter the Room you want to go to");
                command = command + ":" + room;
            }
            prev = gameEngine.simulateTurn(Boolean.TRUE, command);

        }
    }
    private static String getInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt + ": ");
        String ret = scanner.nextLine();
        return ret;
    }
    public static void main(String[] args) {
    simulateSingleRun();
  }

}
