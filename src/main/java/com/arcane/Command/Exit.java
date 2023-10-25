package com.arcane.Command;

import com.arcane.Observer.Subject.Subject;
import com.arcane.board.GameBoard;
import com.arcane.character.adventurer.Adventurer;

public class Exit implements Command{
    Adventurer adventurer;
    GameBoard gameBoard;
    Subject currentSubject;
    public Exit(Adventurer adventurer, GameBoard gameBoard, Subject currentSubject){
        this.adventurer = adventurer;
        this.gameBoard=gameBoard;
        this.currentSubject=currentSubject;
    }
    @Override
    public void execute() {
        String currentRoom = this.adventurer.getCurrentRoomId();

        // Only exit from the starting room
        if(currentRoom.equals("SR")){
            exit(currentRoom);
        }
    }

    public void exit(String currentRoom){

        // remove adventurer from the game and end the game
        this.gameBoard.getRoom(currentRoom).getAdventurers().remove(this.adventurer);

        //end game
        this.gameBoard.setStartingRoomExitFlag(Boolean.TRUE);
    }
}
